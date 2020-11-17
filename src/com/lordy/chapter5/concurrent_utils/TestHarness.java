package com.lordy.chapter5.concurrent_utils;

import java.util.concurrent.CountDownLatch;


/**
 * Latch 闭锁
 *
 */
public class TestHarness {

    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException{
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        for(int i = 0; i < nThreads; i++){
            Thread t = new Thread(){
                @Override
                public void run() {
                    try{
                        startGate.await();
                        try {
                            task.run();
                        }finally {
                            endGate.countDown();
                        }
                    }catch (InterruptedException ignored){

                    }
                }
            };
            t.start();
        }

        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) throws Exception{
        System.out.println(new TestHarness().timeTasks(10, new PrintTask()));
    }

}

class PrintTask implements Runnable{

    @Override
    public void run() {
        System.out.println("task " + Thread.currentThread().getName());
    }
}
