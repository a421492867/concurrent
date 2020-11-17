package com.lordy.chapter4;

import net.jcip.annotations.ThreadSafe;

import java.util.Vector;


/**
 * 不存在就添加
 *
 * 扩展方法更脆弱（相较于直接修改原始类） 若底层类同步策略改变那么子类便会被破坏
 *
 * 扩展了类本身
 * @param <E>
 */
@ThreadSafe
public class BetterVector<E> extends Vector<E> {

    public synchronized boolean putIfAbsent(E x){
        boolean absent = !contains(x);
        if(absent){
            add(x);
        }
        return absent;
    }
}
