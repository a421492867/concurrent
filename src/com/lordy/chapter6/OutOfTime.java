package com.lordy.chapter6;

import java.util.Timer;
import java.util.TimerTask;

import static java.util.concurrent.TimeUnit.SECONDS;


/**
 * 运行1s就结束并抛出Timer already cancelled.
 */
public class OutOfTime {

    public static void main(String[] args) throws Exception{
        Timer timer = new Timer();
        timer.schedule(new ThrowWork(), 1);
        SECONDS.sleep(1);
        timer.schedule(new ThrowWork(), 1);
        SECONDS.sleep(5);

    }


    static class ThrowWork extends TimerTask{
        @Override
        public void run() {
            throw new RuntimeException();
        }
    }
}
