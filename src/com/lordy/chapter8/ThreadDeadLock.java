package com.lordy.chapter8;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadDeadLock {

    ExecutorService exec = Executors.newSingleThreadExecutor();

    class RenderPageTask implements Callable<String>{

        @Override
        public String call() throws Exception {
            Future<String> header, footer;
            header = exec.submit(new LoadFileTask("header.html"));
            footer = exec.submit(new LoadFileTask("footer.html"));
            String page = renderPage();
            //会发生死锁   由于任务在等待任务的结果
            return header.get() + page + footer.get();
        }
    }

    String renderPage(){
        return null;
    }

    class LoadFileTask implements Callable<String>{
        String str;

        public LoadFileTask(String str) {
            this.str = str;
        }

        @Override
        public String call() throws Exception {
            return null;
        }
    }
}
