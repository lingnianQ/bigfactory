package com.thread;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTests {

    static void doMethod01(){
        String dateStr="2022/11/23 15:12:12";
        for(int i=0;i<10;i++) {
            //System.out.println(DateUtil.parse1(dateStr));
            System.out.println(DateUtil.parse2(dateStr));
        }
    }
    static void doMethod02(){
        String dateStr="2022/11/23 15:12:12";
        for(int i=0;i<10;i++) {
            new Thread(()->{
                //System.out.println(DateUtil.parse1(dateStr));
                //System.out.println(DateUtil.parse2(dateStr));
                System.out.println(DateUtil.parse3(dateStr));
                System.out.println(DateUtil.parse3(dateStr));
                DateUtil.remove();//防止内存泄漏
            }).start();
        }
    }

    static void doMethod03(){
        //创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        String dateStr="2022/11/23 15:12:12";
        for(int i=0;i<10;i++) {
             executorService.execute(()->{//Runnable
                 String tName=Thread.currentThread().getName();
                 System.out.println("thread.name="+tName);
                 DateUtil.parse3(dateStr);
                 DateUtil.parse3(dateStr);
                 DateUtil.parse3(dateStr);
                 DateUtil.remove();
             });
        }
        executorService.shutdown();
    }




    public static void main(String[] args) {
       // doMethod01();
       // doMethod02();
        doMethod03();
    }
}
