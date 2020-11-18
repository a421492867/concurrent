package com.lordy.chapter6.html_render;

import com.lordy.chapter5.concurrent_utils.Preloader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


/**
 * Callable可以返回一个值 而Runnable无法返回值 两者都是抽象的计算任务
 *
 * Future表示一个任务的生命周期 任务生命周期只能前进 不能后退
 *
 * 两个任务 一个负责渲染文本 一个负责下载图像  （异构任务）
 * 如果渲染文本速度远高于下载图像速度 程序性能与串行执行性能可能差别不大
 *
 * 只有当大量相互独立且同构的任务可以并发进行处理时，才能体现出将程序的工作负载分配到多个任务中带来的真正性能提升
 */
public class FutureRenderer {

    private final ExecutorService executor = Executors.newCachedThreadPool();

    void renderPage(CharSequence source){
        final List<SingleThreadRenderer.ImageInfo> imageInfos = scanForImageInfos(source);
        Callable<List<SingleThreadRenderer.ImageData>> task = new Callable<List<SingleThreadRenderer.ImageData>>() {
            @Override
            public List<SingleThreadRenderer.ImageData> call() throws Exception {
                List<SingleThreadRenderer.ImageData> result = new ArrayList<>();
                for(SingleThreadRenderer.ImageInfo imageInfo : imageInfos){
                    result.add(imageInfo.downloadImage());
                }
                return result;
            }
        };
        //ExecutorService中的所有submit方法都会返回一个Future
        Future<List<SingleThreadRenderer.ImageData>> future = executor.submit(task);
        renderText(source);
        try {
            List<SingleThreadRenderer.ImageData> imageData = future.get();
            for(SingleThreadRenderer.ImageData data : imageData){
                renderImage(data);
            }
        }catch (InterruptedException e){
            //重新设置线程的中断状态
            Thread.currentThread().interrupt();
            //由于不需要结果， 因此取消任务
            future.cancel(true);
        }catch (ExecutionException e){
            throw Preloader.laundefThrowable(e.getCause());
        }
    }

    List<SingleThreadRenderer.ImageInfo> scanForImageInfos(CharSequence source){
        return null;
    }
    void renderText(CharSequence source){
    }
    void renderImage(SingleThreadRenderer.ImageData imageData){

    }
}
