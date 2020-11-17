package com.lordy.chapter5.concurrent_utils.cache;

import com.lordy.chapter5.concurrent_utils.Preloader;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;


/**
 * 由于if代码块中仍然是非原子的 可能两个线程在同一时刻调用compute来计算
 * @param <A>
 * @param <V>
 */
public class Memoizer3<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if(f == null){
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<>(eval);
            f = ft;
            cache.put(arg, ft);
            ft.run();//这里调用c.compute
        }
        try {
            return f.get();
        }catch (Exception e){
            throw Preloader.laundefThrowable(e.getCause());
        }
    }


}
