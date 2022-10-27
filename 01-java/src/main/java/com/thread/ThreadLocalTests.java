package com.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTests {
    static void doMethod01(){
        ExecutorService executorService =
                Executors.newFixedThreadPool(100);

        for(int i=0;i<100;i++){
            executorService.execute(()->{
                System.out.println(DateUtil.parse1("2022/10/25 15:38:38"));
            });
        }
    }
    static void doMethod02(){
        for(int i=0;i<100;i++){
            new Thread(()->{
                System.out.println(DateUtil.parse2("2022/10/25 15:38:38"));
                System.out.println(DateUtil.parse2("2022/10/25 15:38:38"));
                System.out.println(DateUtil.parse2("2022/10/25 15:38:38"));
            }).start();
        }
    }
    public static void main(String[] args) {
        //doMethod01();
        doMethod02();
    }
}
