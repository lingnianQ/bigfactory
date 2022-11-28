package com.thread;

import java.util.Locale;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadLockTests {
    //共享变量
    static String str;

    static void doMethod01()throws InterruptedException{
        //1.创建生产者线程(为共享变量赋值)
        new Thread(()->{
            synchronized (ThreadLockTests.class) {
                str = "Hello JSD2206";
                ThreadLockTests.class.notify();
            }
        }).start();
        //2.消费者线程(主线程)去共享变量的值，并进行操作
        synchronized (ThreadLockTests.class) {
            while (str == null)ThreadLockTests.class.wait();
            System.out.println(str.toUpperCase());
        }
    }
    public static void doMethod02() throws InterruptedException {
        ReentrantLock lock=new ReentrantLock();
        //Condition putCondition= lock.newCondition();
        Condition takeCondition= lock.newCondition();
        new Thread(()->{
            lock.lock();
            try {
                str = "Hello JSD2206";
                takeCondition.signalAll();
            }finally {
                lock.unlock();
            }
        }).start();

        lock.lock();
        try {
            while(str==null)takeCondition.await();
            System.out.println(str.toUpperCase());
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
          doMethod02();
    }
}
