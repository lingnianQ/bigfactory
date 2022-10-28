package com.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class BlockQueueArrayContainer<T>{
    private Object[] array;
    /**有效元素个数*/
    private int size;
    public BlockQueueArrayContainer(){
        this(16);
    }
    public BlockQueueArrayContainer(int cap){
        this.array=new Object[cap];
    }

    private ReentrantLock lock=new ReentrantLock();
    private Condition producerCondition=lock.newCondition();
    private Condition consumerCondition=lock.newCondition();

    /**向容器放数据*/
    public void put(T t)throws InterruptedException{
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            //1.判断容器是否已满了，满了则等待
            while (size == array.length) producerCondition.await();
            //2.放数据
            array[size] = t;
            //3.更新有效元素个数
            size++;
            //4.通知消费者线程取数据
            consumerCondition.signalAll();
        }finally {
            lock.unlock();
        }
    }
    /**从容器取数据*/
    public  T take()throws InterruptedException{
        final ReentrantLock lock = this.lock;
        //lock.lock();
        lock.lockInterruptibly();//优先响应中断
        try {
            //1.判定容器是否为空，空则等待
            while (size == 0)
                consumerCondition.await();
            //2.取数据
            T t = (T) array[0];
            //3.移动元素
            System.arraycopy(array, 1, array, 0, size - 1);
            //4.更新有效元素个数
            size--;
            //5.将size位置内容设置为null
            array[size] = null;
            //6.通知(唤醒)生产者线程继续放数据
            producerCondition.signalAll();
            return t;
        }finally {
            lock.unlock();
        }
    }
}
public class BlockQueueConditionContainerTests {
    public static void main(String[] args) {
        //1.创建容器对象
        BlockQueueArrayContainer<Integer> blockQueueContainer=
                new BlockQueueArrayContainer<>(3);
        //2.创建生产者线程
        Thread producer=new Thread(()->{
            int i=1;
            while(true){
                System.out.println("put->"+i);
                try{
                blockQueueContainer.put(i++);
                 Thread.sleep(1000);
                }catch (InterruptedException e){}
            }
        });
        //3.创建消费者线程
        Thread consumer=new Thread(()->{
            while(true){
                try {
                    Integer obj = blockQueueContainer.take();
                    System.out.println("take->"+obj);
                }catch (InterruptedException e){}

            }
        });
        //4.启动线程
        producer.start();
        consumer.start();
    }
}
