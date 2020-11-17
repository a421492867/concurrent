package com.lordy.chapter5.concurrent_utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;


/**
 * 信号量
 * @param <T>
 */
public class BoundedHashSet<T> {

    private final Set<T> set;

    private final Semaphore sem;

    public BoundedHashSet(int bound) {
        set = Collections.synchronizedSet(new HashSet<>());
        sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException{
        sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        }finally {
            if(!wasAdded){
                sem.release();
            }
        }
    }

    public boolean remove(T o){
        boolean wasRemoved = set.remove(o);
        if(wasRemoved){
            sem.release();
        }
        return wasRemoved;
    }


    public static void main(String[] args) throws Exception {
        BoundedHashSet boundedHashSet = new BoundedHashSet(1);
        boundedHashSet.add(1);
        //以下两行交换顺序会阻塞
        boundedHashSet.remove(1);
        boundedHashSet.add(2);

    }
}
