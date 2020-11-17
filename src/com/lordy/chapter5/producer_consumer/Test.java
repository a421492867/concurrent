package com.lordy.chapter5.producer_consumer;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Test {

    public static void main(String[] args) {
        BlockingDeque<Product> productBlockingDeque = new LinkedBlockingDeque<>(10);

        for(int i = 0; i < 2; i++){
            new Thread(new Producer(productBlockingDeque)).start();
        }
        for(int i = 0; i < 5; i++){
            new Thread(new Consumer(productBlockingDeque)).start();
        }
    }
}
