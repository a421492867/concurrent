package com.lordy.chapter5.concurrent_utils.cache;

import java.util.HashMap;
import java.util.Map;


/**
 * 使用HashMap构造缓存
 * 由于HashMap非线程安全，因此要确保两个线程不会同时访问HashMap
 * 对compute方法进行同步 会带来明显的可伸缩性问题 一次只有一个线程可以执行compute
 * @param <A>
 * @param <V>
 */
public class Memoizer1<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new HashMap<>();
    private final Computable<A, V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    public synchronized V compute(A arg) throws InterruptedException{
        V result = cache.get(arg);
        if(result == null){
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
