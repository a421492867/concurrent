package com.lordy.chapter5.concurrent_utils.cache;

public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
