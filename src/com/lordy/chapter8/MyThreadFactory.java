package com.lordy.chapter8;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyThreadFactory implements ThreadFactory {

    private final String pool;

    public MyThreadFactory(String pool) {
        this.pool = pool;
    }

    @Override
    public Thread newThread(Runnable r) {
        return null;
    }
}

class MyAppThread extends Thread{
    public static final String DEFAULT_NAME = "MyAppThread";

    private static volatile boolean debugLifeCycle = false;

    private static final AtomicInteger created = new AtomicInteger();

    private static final AtomicInteger alive = new AtomicInteger();

    private static final Logger log = Logger.getAnonymousLogger();

    public MyAppThread(Runnable target) {
        super(target, DEFAULT_NAME);
    }

    public MyAppThread(Runnable target, String name) {
        super(target, name + "-" + created.getAndIncrement());
        setUncaughtExceptionHandler(
                new Thread.UncaughtExceptionHandler(){
                    public void uncaughtException(Thread t,  Throwable e){
                        log.log(Level.SEVERE, "UNCAUGHT in thread " + t.getName(), e);
                    }
                }
        );
    }

    public void run(){
        boolean debug = debugLifeCycle;
        if(debug) log.log(Level.FINE, "Created " + getName());
        try {
            alive.incrementAndGet();
            super.run();
        }finally {
            alive.decrementAndGet();
            if(debug) log.log(Level.FINE, "Exiting " + getName());
        }
    }

    public static int getThreadCreated(){
        return created.get();
    }

    public static int getThreadAlive(){
        return alive.get();
    }

    public static boolean getDebug(){
        return debugLifeCycle;
    }

    public static void setDebugLifeCycle(boolean b){
        debugLifeCycle = b;
    }
}
