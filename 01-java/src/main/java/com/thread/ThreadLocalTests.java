package com.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTests {
    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(100);

        for(int i=0;i<100;i++){
            executorService.execute(()->{
                System.out.println(DateUtil.parse("2022/10/25 15:38:38"));
            });
        }
    }
}
