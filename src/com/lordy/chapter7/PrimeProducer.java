package com.lordy.chapter7;

import java.math.BigInteger;
import java.util.concurrent.BlockingDeque;

/**
 * 如果在调用 interrupted时返回true 除非想屏蔽这个中断 否则必须对它进行处理：
 *  可以抛出InterruptedException或者通过再次调用interrupt来恢复中断状态
 *
 *  通常中断是实现取消的最合理方式
 *
 *  线程中断可以在线程内部设置一个中断标识，同时让处于(可中断)阻塞的线程抛出InterruptedException中断异常，使线程跳出阻塞状态
 *
 *  阻塞库方法都会检查线程何时中断
 */
public class PrimeProducer extends Thread{

    private final BlockingDeque<BigInteger> queue;

    public PrimeProducer(BlockingDeque<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted()){
                queue.put(p = p.nextProbablePrime());
            }
        }catch (InterruptedException e){
            //允许线程推出
        }
    }

    public void cancle(){
        interrupt();
    }
}
