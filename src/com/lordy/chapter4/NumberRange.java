package com.lordy.chapter4;


import net.jcip.annotations.NotThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * upper 和 lower相互有关  委托失效
 */

@NotThreadSafe
public class NumberRange {

    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger uppper = new AtomicInteger(0);

    public void setLower(int i){
        if(i > uppper.get()){
            throw new IllegalArgumentException();
        }
        lower.set(i);
    }

    public void setUppper(int i){
        if(i < lower.get()){
            throw new IllegalArgumentException();
        }
        uppper.set(i);
    }

    public boolean isInRange(int i){
        return (i >= lower.get() && i <= uppper.get());
    }
}
