package com.lordy.chapter3;

public class SafeListener {

    private final EventListener listener;

    void doSomething(){

    }

    private SafeListener() {

        /**
         * 虽然内部类中持有了外部类的引用
         * 但是listener并没有在构造函数中发布出去
         * 而是在newInstance()函数中才发布出去
         * 此时外部类已经构造完成。
         */
        listener = new EventListener() {
            @Override
            public void onEvent(Event e) {
                doSomething();
            }
        };
    }

    public static SafeListener getInstance(EventSource eventSource){
        SafeListener safeListener = new SafeListener();

        /**
         * 当构造好了SafeListener对象之后
         * 我们才启动了监听线程
         * 也就确保了SafeListener对象是构造完成之后在使用的SafeListener对象
         */
        eventSource.registerListener(safeListener.listener);
        return safeListener;
    }

    interface EventSource {
        void registerListener(EventListener e);
    }

    interface EventListener {
        void onEvent(Event e);
    }

    interface Event {
    }
}
