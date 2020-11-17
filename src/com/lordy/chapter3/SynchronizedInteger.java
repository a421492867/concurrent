package com.lordy.chapter3;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;


@ThreadSafe
public class SynchronizedInteger {

    @GuardedBy("this")
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int value) {
        this.value = value;
    }
}
