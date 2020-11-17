package com.lordy;


import net.jcip.annotations.NotThreadSafe;

/**
 * 最终结果不是200000
 * volatile只能保证可见性 不能保证原子性
 * race++不是原子操作
 */

@NotThreadSafe
public class VolatileTest {

    public static volatile int race = 0;

    public static void increace(){
        race++;
    }

    private static final int THREAD_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREAD_COUNT];
        for(int i = 0; i < THREAD_COUNT; i++){
            threads[i] = new Thread(() -> {
                for(int j = 0; j < 10000; j++) {
                    increace();
                }
            });
            threads[i].start();
        }
        //判断所有线程都结束
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(race);
    }
}
