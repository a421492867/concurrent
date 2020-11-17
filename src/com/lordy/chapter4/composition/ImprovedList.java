package com.lordy.chapter4.composition;


import net.jcip.annotations.ThreadSafe;

import java.util.List;


/**
 * 不关心List是否线程安全  通过自身内置锁增加一层额外的锁
 * 需要确保重写是 需要的方法需要synchronized  例如 clear  add等
 */
//@ThreadSafe
//public class ImprovedList<T> implements List<T> {
//    private final List<T> list;
//
//    public ImprovedList(List<T> list) {
//        this.list = list;
//    }
//
//    public synchronized boolean putIfAbsent(T x){
//        boolean contains = list.contains(x);
//        if(!contains){
//            list.add(x);
//        }
//        return contains;
//    }
//}
