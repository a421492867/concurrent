package com.lordy.chapter7;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 通过设置 "已请求取消"标志来协作  必须为volatile类型
 *
 * 一种简单的取消策略 客户端代码调用cancel来取消 generator在每次搜索钱检查是否存在取消请求
 *
 * 问题 ： 如果任务调用了一个阻塞方法 如BlockingQueue的put 任务可能永远不会检查取消标志
 */
public class PrimeGenerator implements Runnable{

    private final List<BigInteger> primes = new ArrayList<>();

    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled){
            p = p.nextProbablePrime();
            synchronized (this){
                primes.add(p);
            }
        }
    }

    public void cancel(){
        cancelled = true;
    }

    public synchronized List<BigInteger> get(){
        return new ArrayList<>(primes);
    }

    public static void main(String[] args) throws InterruptedException{
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();
        try {
            SECONDS.sleep(1);
        }finally {
            generator.cancel();
        }
        System.out.println(generator.get().size());
    }
}


