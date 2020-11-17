package com.lordy.chapter3;

import net.jcip.annotations.NotThreadSafe;

/**
 * 发布一个内部的类实例
 * ThisEscape发布registerListener时也隐含地发布了ThisEscape本身
 * 因为在这个内部类的实例中包含了对ThisEscape实例的隐含引用
 *
 *
 * this引用在构造过程中逸出
 *
 * 内部类的实例包含对封装类的隐含引用
 *
 * ThisEscape还没有构造完成  就调用了doSomething
 *
 *
 * https://blog.csdn.net/zyh5540/article/details/51173808
 */

@NotThreadSafe
public class ThisEscape {

    public ThisEscape(EventSource eventSource) {
       eventSource.registerListener(new EventListener() {
           @Override
           public void onEvent(Event e) {
               doSomething(e);
           }
       });
    }

    void doSomething(Event e){

    }


    interface EventSource{
         void registerListener(EventListener eventListener);
    }

    interface EventListener{
        void onEvent(Event e);
    }

    interface Event{

    }
}


