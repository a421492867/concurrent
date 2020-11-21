package com.lordy.chapter10;


/**
 * 容易产生死锁
 */
public class LeftRightDeadlock {

    private final Object left = new Object();
    private final Object rigth = new Object();

    public void leftRight(){
        synchronized (left){
            synchronized (rigth){
                // do something
            }
        }
    }

    public void rightLeft(){
        synchronized (rigth){
            synchronized (left){
                //do something
            }
        }
    }
}
