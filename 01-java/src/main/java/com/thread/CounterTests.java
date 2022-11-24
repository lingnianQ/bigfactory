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
    private final Object lock=new Object();
    int count(){//悲观锁(排它锁)，从JVM原语维度进行加锁
        synchronized (lock) {
            return count++;
        }
    }

    //synchronized 描述实例方法时，默认对象锁是this
    //synchronized 描述静态方法时，默认对象锁是方法所在类的字节码对象
    //synchronized 描述静态代码块时，小括号中使用的对象就是对象锁,指向这个对象的变量尽量使用final修饰
}
class Counter02{
    int count;
    int count(){//从API维度进行加锁
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
        //Counter01 c1=new Counter01();
        //Counter02 c1=new Counter02();
        Counter03 c1=new Counter03();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.out.println(c1.count());
                    try{Thread.sleep(1000);}catch(Exception e){}
                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.out.println(c1.count());
                    try{Thread.sleep(1000);}catch(Exception e){}
                }
            }
        });
    }
}
