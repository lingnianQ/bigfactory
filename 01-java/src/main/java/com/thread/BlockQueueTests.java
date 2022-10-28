package com.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockQueueTests {
    public static void main(String[] args) throws InterruptedException {
        //阻塞式队列
        BlockingQueue<Integer> bq=new ArrayBlockingQueue<>(3);
        bq.put(1);
        bq.put(2);
        bq.put(3);
        System.out.println(bq);
        //bq.put(4);//阻塞式方法，容器满了会阻塞
        bq.take();//取数据
        bq.take();
        bq.take();
        System.out.println(bq);
        //bq.take(); 阻塞式方法，空了会阻塞
        //System.out.println(bq);
    }
}
