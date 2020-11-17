package com.lordy.chapter4.confinement;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * 通过封闭与加锁等机制使一个类成为线程安全的
 *
 * PersonSet的状态由HashSet管理
 * 虽然HashSet并非线程安全
 * 由于mySet是私有的且不会逸出 因此HashSet被封闭在PersonSet中
 *
 * 两个方法在执行时都要获得PersonSet上的锁
 * 因此 PersonSet是线程安全的
 *
 *
 * 如果Person类是可变的 那么在访问从PersonSet中获得的Person对象时 还需要额外的同步
 */
@ThreadSafe
public class PersonSet {

    @GuardedBy("this")
    private final Set<Person> mySet = new HashSet<>();

    public synchronized void addPerson(Person p){
        mySet.add(p);
    }

    public synchronized boolean containsPerson(Person p){
        return mySet.contains(p);
    }



    class Person{

    }
}
