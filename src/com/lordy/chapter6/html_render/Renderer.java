package com.lordy.chapter6.html_render;

import com.lordy.chapter5.concurrent_utils.Preloader;

import java.util.List;
import java.util.concurrent.*;

/**
 * CompletionService
 *
 * take 和 polld的区别 :  take 为空阻塞  poll为空返回null
 *
 * 为每一幅图像创建一个独立的任务并在线程池执行它们，下载从串行变为并行
 * 通过CompletionService中获取结果使每张图片下载完成后立刻显示出来
 *
 * CompletionService BlockingQueue存计算结果（Future）
 *
 * 多个ExecutorCompletionService可以共享一个Excutor
 */
public class Renderer {

    private final ExecutorService executor;

    public Renderer(ExecutorService executor) {
        this.executor = executor;
    }

    void renderPage(CharSequence source){
        List<SingleThreadRenderer.ImageInfo> info = scanForImageInfo(source);
        CompletionService<SingleThreadRenderer.ImageData> completionService = new ExecutorCompletionService<>(executor);
        for(final SingleThreadRenderer.ImageInfo imageInfo : info){
            completionService.submit(new Callable<SingleThreadRenderer.ImageData>() {
                @Override
                public SingleThreadRenderer.ImageData call() throws Exception {
                    return imageInfo.downloadImage();
                }
            });
        }
        renderText(source);

        try {
            for(int i = 0; i < info.size(); i++){
                Future<SingleThreadRenderer.ImageData> f = completionService.take();
                SingleThreadRenderer.ImageData imageData = f.get();
                renderImage(imageData);
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }catch (ExecutionException e){
            throw Preloader.laundefThrowable(e.getCause());
        }
    }

    void renderText(CharSequence source){

    }
    void renderImage(SingleThreadRenderer.ImageData imageData){

    }

    List<SingleThreadRenderer.ImageInfo> scanForImageInfo(CharSequence source){
        return null;
    }
}
