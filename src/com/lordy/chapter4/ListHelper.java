package com.lordy.chapter4;

import net.jcip.annotations.NotThreadSafe;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 扩展类功能而非本身
 *  客户端不知道具体返回了那个List对象 无法进行扩展或者修改原类
 *
 *
 * synchronized 伤的是ListHelper的锁 而不是List的锁 所以线程不安全
 * @param <E>
 */
@NotThreadSafe
public class ListHelper<E> {

    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    public synchronized boolean putIfAbsent(E x){
        boolean absent = !list.contains(x);
        if(absent){
            list.add(x);
        }
        return absent;
    }

}

@ThreadSafe
class SafeListHelper<E>{
    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    public  boolean putIfAbsent(E x){
        synchronized (list){
            boolean absent = !list.contains(x);
            if(absent){
                list.add(x);
            }
            return absent;
        }

    }
}
