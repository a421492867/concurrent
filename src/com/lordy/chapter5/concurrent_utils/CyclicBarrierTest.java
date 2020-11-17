package com.lordy.chapter5.concurrent_utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


/**
 * 栏栅
 * 所有线程必须到达栏栅位置才能继续运行
 *
 * Exchanger  两方栏栅  在栏栅位置处交换数据
 */
public class CyclicBarrierTest {
    private static final int NUM = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUM, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":" + " all sub thead end");//所有子线程执行完毕时执行
            }
        });
        for(int i = 0; i < NUM; i++) {
            new Thread(new SubThread(cyclicBarrier)).start();
        }
    }

    private static class SubThread implements Runnable{

        private CyclicBarrier barrier;

        public SubThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": sub thread starting running");
            try {
                barrier.await();//计数器加1，等待其他线程执行到同样的地方
                //子线程全部继续执行
                System.out.println(Thread.currentThread().getName() + ":" +" all sub thread start doing other things");
            }catch (InterruptedException ex){
                return;
            }catch (BrokenBarrierException ex){
                return;
            }
        }
    }
}
