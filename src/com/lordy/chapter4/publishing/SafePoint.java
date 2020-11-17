package com.lordy.chapter4.publishing;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * 同时对x和y进行赋值  否则可能会出现从未有过的(x,y)组合
 */

@ThreadSafe
public class SafePoint {

    @GuardedBy("this")
    private int x, y;

    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public SafePoint(SafePoint p){
        this(p.get());
    }

    private SafePoint(int[] a){
        this(a[0], a[1]);
    }

    public synchronized int[] get(){
        return new int[]{x, y};
    }

    public synchronized void set(int x, int y){
        this.x = x;
        this.y = y;
    }
}
