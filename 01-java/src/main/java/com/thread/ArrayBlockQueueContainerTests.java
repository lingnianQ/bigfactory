package com.thread;

import java.util.concurrent.TimeUnit;

/**
 * 基于数组实现一个阻塞式队列
 * 1)数据存储结构：数组
 * 2)算法：FIFO
 * 3)线程策略：容器满了，向容器放数据的线程要阻塞，容器空了，从容器取数据的线程要阻塞
 * @param <T>
 */
class ArrayBlockQueueContainer<T>{

    /**基于此数组存储数据*/
    private Object[] array;
    /**基于size记录有效元素个数*/
    private int size;
    public ArrayBlockQueueContainer(){
        this(16);
    }
    public ArrayBlockQueueContainer(int cap){
        this.array=new Object[cap];
    }

    /**
     * 向容器放数据，始终放在size位置
     * 1)放数据前要判断容器是否满了，假如满了，在放数据的线程要阻塞
     * 2)容器没满则向容器放数据、并且让有效元素的个数加1；
     * 3)唤醒在同一个监视器(同步锁)下阻塞的线程
     * @param t
     */
    public synchronized void put(T t){//同步锁为调用此方法的当前对象
        //1.判断容器是否已满,满了则等待
        while(size==array.length){
            //阻塞当前线程，并且释放锁。
            try{this.wait();}catch (InterruptedException e){}
        }
        System.out.println("put->"+t);
        //2.放数据
        array[size]=t;
        //3.有效元素个数加1
        size++;
        //4.唤醒消费者线程(唤醒的是在同一个对象锁上等待的线程)可以取数据
        this.notifyAll();
    }

    /**
     * 从容器取数据，永远从0位置开始取
     * 1)取数据前要判断容器是否为空，假如空了，取数据的线程要阻塞
     * 2)容器有数据则从容器取数据、移动元素中的数据，并执行size减一操作；
     * 3)唤醒在同一个监视器(同步锁)下阻塞的线程(例如放数据的线程)
     */
    public synchronized T take(){//锁的是this
        //1.判断容器是否为空，假如为空，取数据的线程要等待
        while(size==0)
           try{this.wait();}catch (InterruptedException e){}
        //2.取数据
        T t=(T)array[0];
        //3.移动数据
        System.arraycopy(array, 1, array, 0, size-1);
        //4.有效元素个数减1
        size--;
        array[size]=null;
        //5.唤醒生产着线程(唤醒的是在同一个对象锁上等待的线程)继续放数据
        this.notifyAll();
        return t;
    }
    /**获取有效元素个数*/
    public int size(){
        return size;
    }
}

public class ArrayBlockQueueContainerTests {
    public static void main(String[] args) {
        ArrayBlockQueueContainer<Integer> container=
                new ArrayBlockQueueContainer<>();
        Thread producer=new Thread(()->{
           int i=1;
           while(true){
               container.put(i);
               i++;
               try {
                   TimeUnit.MILLISECONDS.sleep(500);
               }catch (InterruptedException e){}
           }
        });
        Thread consumer=new Thread(()->{
            while(true){
                Integer take = container.take();
                System.out.println(take);
            }
        });

        producer.start();
        consumer.start();
    }
}
