package com.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 编写线程安全的计数器对象
 */
class Counter01{
    int count;
    synchronized int count(){//悲观锁(排它锁)
        return count++;
    }
}
class Counter02{
    int count;
    int count(){
        ReentrantLock loc=new ReentrantLock();//可重入锁(排它锁)
        loc.lock();
        try {
            count++;
        }finally {
            loc.unlock();
        }
        return count;
    }
}
class Counter03 {
    AtomicLong atomicLong=new AtomicLong(1);//乐观锁：底层CAS(比较和交换)
    long count(){
        long andIncrement = atomicLong.getAndIncrement();
        return andIncrement;
    }
}//16:10


public class CounterTests {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Counter01 c1=new Counter01();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.out.println(c1.count);
                    try{Thread.sleep(1000);}catch(Exception e){}
                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.out.println(c1.count);
                    try{Thread.sleep(1000);}catch(Exception e){}
                }
            }
        });
    }
}
