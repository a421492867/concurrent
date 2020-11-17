package com.lordy.chapter5.producer_consumer;

import java.util.concurrent.BlockingDeque;

public class Consumer implements Runnable {

    private final BlockingDeque<Product> productBlockingDeque;

    public Consumer(BlockingDeque<Product> productBlockingDeque) {
        this.productBlockingDeque = productBlockingDeque;
    }

    @Override
    public void run() {
        try {
            while (true){
                Product product = productBlockingDeque.take();
                System.out.println(Thread.currentThread().getName() + " consume " + product.getName());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
