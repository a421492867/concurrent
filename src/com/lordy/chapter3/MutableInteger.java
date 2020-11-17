package com.lordy.chapter3;

import net.jcip.annotations.NotThreadSafe;


/**
 * 如果某个线程调用了set 另一个线程正在访问get 那么可能get到的value是失效值
 */

@NotThreadSafe
public class MutableInteger {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
