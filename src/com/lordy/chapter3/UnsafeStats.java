package com.lordy.chapter3;

import net.jcip.annotations.NotThreadSafe;

/**
 * 从非私有方法中返回一个引用，会发布返回的对象
 *
 * states逸出了它所在作用域 所有调用者均可修改数组内容
 */

@NotThreadSafe
public class UnsafeStats {

    private String[] states = new String[]{"AK", "AL"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafeStats stats = new UnsafeStats();
        String[] strings = stats.getStates();
        strings[0] = "AT";
        for(String s : stats.getStates()){
            System.out.println(s);
        }
    }
}
