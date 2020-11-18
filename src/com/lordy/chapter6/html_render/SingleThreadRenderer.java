package com.lordy.chapter6.html_render;

import java.util.ArrayList;
import java.util.List;

/**
 * 串行页面渲染器
 * 先绘制文本同时为图像预留图像的占位空间
 *
 * 图像下载过程大部分时间等待I/O操作 这期间CPU不做工作 没有充分利用CPU
 * 用户要等待很长时间才能看到页面
 */
public class SingleThreadRenderer {

    void renderPage(CharSequence source){
        renderText(source);
        List<ImageData> imageData = new ArrayList<>();
        for(ImageInfo imageInfo : scanForImageInfo(source)){
            imageData.add(imageInfo.downloadImage());
        }
        for(ImageData data : imageData){
            renderImage(data);
        }

    }

    List<ImageInfo> scanForImageInfo(CharSequence source){
        return null;
    }

    void renderText(CharSequence source){

    }
    void renderImage(ImageData data){

    }

    interface ImageData{

    }

    interface ImageInfo{
        ImageData downloadImage();
    }
}
