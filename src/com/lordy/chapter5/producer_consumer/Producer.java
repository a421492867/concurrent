package com.lordy.chapter5.producer_consumer;

import java.util.UUID;
import java.util.concurrent.BlockingDeque;

public class Producer implements Runnable {

    private final BlockingDeque<Product> productBlockingDeque;

    public Producer(BlockingDeque<Product> productBlockingDeque) {
        this.productBlockingDeque = productBlockingDeque;
    }

    @Override
    public void run(){
        try {
            for(int i = 0; i < 10; i++){
                Thread.sleep(100);
                String productName = UUID.randomUUID().toString();
                Product product = new Product(productName);
                System.out.println(Thread.currentThread().getName() + " Produce " + productName);
                productBlockingDeque.put(product);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
