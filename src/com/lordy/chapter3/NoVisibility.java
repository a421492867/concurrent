package com.lordy.chapter3;

import net.jcip.annotations.NotThreadSafe;

/**
 * 输出可能是0（重排序） 可能一直执行 也可能42
 */

@NotThreadSafe
public class NoVisibility {

    private static boolean ready;

    private static int num;

    public static void main(String[] args) {
        new Thread(() ->{
            while (!ready){
                Thread.yield();
            }
            System.out.println(num);
        }).start();
        num = 42;
        ready = true;
    }
}
