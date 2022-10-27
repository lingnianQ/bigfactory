package com.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 如何保证线程安全？不要上来就说加锁
 * 导致线程不安全的原因
 * 1)有多个线程并发
 * 2)多个线程有共享数据集
 * 3)多个线程在共享数据集上的操作有非原子操作
 *
 * 要解决解决线程安全问题可从如下几个方面进行设计
 * 1)取消并发
 * 2)取消共享
 * 3)加锁(悲观锁还是乐观锁)
 *
 * ThreadLocal是什么？
 * 线程本地变量，访问这个变量的每个线程都会有这个变量的一个本地拷贝。
 *
 * ThreadLocal对象的应用
 * 1)可以将某个对象存储到当前线程的ThreadLocalMap中
 * 2)可以从当前线程的ThreadLocalMap中获取到指定对象
 */
public class DateUtil {
    //SimpleDateFormat对象是一个线程不安全的对象，不可以多个线程共享。
    private static SimpleDateFormat sdf=
            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static synchronized Date parse1(String dateStr){
        //SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date=null;
        try {
            date=sdf.parse(dateStr);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return date;
    }

    private static AtomicLong atomicLong=new AtomicLong(1);
    private static ThreadLocal<SimpleDateFormat> td=new ThreadLocal<>();
    public static  Date parse2(String dateStr){
        //获取当前线程中的SimpleDateFormat对象
        SimpleDateFormat sdf=td.get();
        if(sdf==null){
        //当前线程没有SimpleDateFormat对象就创建对象
            System.out.println("Create SimpleDateDateFormat ->"+atomicLong.getAndIncrement());
            sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //将创建的对象存储到当前线程
            td.set(sdf);
        }
        Date date=null;
        try {
            date=sdf.parse(dateStr);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return date;
    }

    public static void remove(){
        td.remove();
    }
}
