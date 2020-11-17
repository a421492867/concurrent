package com.lordy.chapter4.delegating;


import net.jcip.annotations.Immutable;

/**
 * Point类不可变 因此是线程安全的
 * x y可以被自由地共享与发布 因此返回location时不需要复制
 */

@Immutable
public class Point {

    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
