package com.lordy.chapter5.concurrent_utils;


import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * FutureTask作为闭锁
 *
 *
 */
public class Preloader {

    private final FutureTask<ProductInfo> future = new FutureTask<ProductInfo>(
            new Callable<ProductInfo>() {
                @Override
                public ProductInfo call() throws Exception {
                    return loadProductInfo();
                }
            }
    );

    ProductInfo loadProductInfo() throws DataLoadException, InterruptedException{
        System.out.println("load start");
        Thread.sleep(10000);
        System.out.println("load end");
        return null;
    }

    private final Thread thread = new Thread(future);
    public void start(){
        thread.start();
    }

    public ProductInfo get() throws DataLoadException, InterruptedException{
        try {
            System.out.println("get start");
            ProductInfo p = future.get();
            System.out.println("get end");
            return p;
        }catch (Exception e){
            Throwable cause = e.getCause();
            if(cause instanceof DataLoadException){
                throw (DataLoadException) cause;
            }else {
                throw laundefThrowable(cause);
            }
        }
    }

    public static RuntimeException laundefThrowable(Throwable t){
        if(t instanceof RuntimeException){
            return (RuntimeException) t;
        }else if (t instanceof Error){
            throw (Error) t;
        }else {
            throw new IllegalStateException("Not unchecked", t);
        }
    }


    interface ProductInfo{

    }

    class DataLoadException extends Exception{

    }

    public static void main(String[] args) throws DataLoadException, InterruptedException{
        Preloader preloader = new Preloader();
        preloader.start();
        preloader.get();
    }
}
