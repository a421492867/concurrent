package com.lordy.chapter6;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class LifecycleWebServer {

    private final ExecutorService exec = Executors.newCachedThreadPool();

    public void start() throws IOException{
        ServerSocket socket = new ServerSocket(80);
        while (!exec.isShutdown()){
            try {
                final Socket conn = socket.accept();
                exec.execute(new Runnable() {
                    @Override
                    public void run() {
                        handleRequest(conn);
                    }
                });
            }catch (RejectedExecutionException e){
                if(!exec.isShutdown()){
                    System.out.println("task submission rejected" + e);
                }
            }
        }
    }

    public void stop(){
        exec.shutdown();
    }

    boolean isShutdownRequest(Request req){
        return false;
    }


    //可以以客户端请求形式向Web服务器发送特定请求来达到关闭Web服务器效果
    void handleRequest(Socket conn){
        Request req = null; //readRequest
        if(isShutdownRequest(req)){
            stop();
        }else {
            //处理具体请求
        }
    }

    class Request{

    }
}
