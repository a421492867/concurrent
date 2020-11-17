package com.lordy.chapter3;

import net.jcip.annotations.NotThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * 发布对象最简单的方式  将对象的引用保存到一个共有静态变量中
 *
 * 发布某个对象时可能会间接发布其他对象  如 将一个Secret对象放到knownSecrets中 会发布这个Secret对象
 */

@NotThreadSafe
public class Publish {

    public static Set<Secret> knownSecrets;

    public void initialize(){
        knownSecrets = new HashSet<>();
    }
}

class Secret{

}
