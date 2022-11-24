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

    public static Date parse1(String dateStr){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            return sdf.parse(dateStr);
        }catch (ParseException e){
            throw new RuntimeException("字符串转换日期失败了",e);
        }
    }

    //SimpleDateFormat对象是一个线程不安全的对象，不允许多个线程同时共享此对象
    //假如要多线程共享使用SimpleDateFormat需要保证其线程安全
    static SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static synchronized Date parse2(String dateStr){
        try {
            return sdf.parse(dateStr);
        }catch (ParseException e){
            throw new RuntimeException("字符串转换日期失败了",e);
        }
    }

    private static ThreadLocal<SimpleDateFormat> tdl= new ThreadLocal<SimpleDateFormat>();
    public static Date parse3(String dateStr){
           //1.从当前线程获取SimpleDateFormat对象
            SimpleDateFormat sdf=tdl.get();//threadLocalMap.get(threadLocal)
            //2.假如当前线程没有SimpleDateFormat对象，则创建，并将其存储到当前线程。
            if(sdf==null){
                System.out.println("Create SimpleDateFormat");
                sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                tdl.set(sdf);//threadLocalMap.put(threadLocal,sdf);
            }
            try {
                return sdf.parse(dateStr);
            }catch (ParseException e){
                throw new RuntimeException("类型转换异常",e);
            }
    }

    public static void remove(){
        tdl.remove();
    }
}
