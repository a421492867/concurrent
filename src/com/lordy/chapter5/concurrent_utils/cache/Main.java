package com.lordy.chapter5.concurrent_utils.cache;

import java.math.BigInteger;

public class Main {

    private static final Memoizer1<String, BigInteger> m1 = new Memoizer1<>(new ExpensiveFunction());

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < 5; i++){
            int num = i;
            new Thread(() -> {
                try {
//                    m1.compute(String.valueOf(num));
                    //不使用缓存
                    Thread.sleep(5 * 1000);
                    new BigInteger(String.valueOf(num));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }).start();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

    }
}
