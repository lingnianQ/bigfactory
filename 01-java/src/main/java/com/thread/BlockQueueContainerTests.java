package com.thread;
class BlockQueueContainer<T>{
    private Object[] array;
    /**有效元素个数*/
    private int size;
    public BlockQueueContainer(){
        this(16);
    }
    public BlockQueueContainer(int cap){
        this.array=new Object[cap];
    }

    /**向容器放数据*/
    public synchronized void put(T t){
        //1.判断容器是否已满了，满了则等待
        while(size==array.length)try{this.wait();}catch (InterruptedException e){}
        //2.放数据
        array[size]=t;
        //3.更新有效元素个数
        size++;
        //4.通知消费者线程取数据
        this.notifyAll();
    }
    /**从容器取数据*/
    public synchronized T take(){
        //1.判定容器是否为空，空则等待
        while(size==0)try{this.wait();}catch (InterruptedException e){}
        //2.取数据
        T t=(T)array[0];
        //3.移动元素
        System.arraycopy(array, 1, array, 0,size-1);
        //4.更新有效元素个数
        size--;
        //5.将size位置内容设置为null
        array[size]=null;
        //6.通知(唤醒)生产者线程继续放数据
        this.notifyAll();
        return t;
    }

}

public class BlockQueueContainerTests {

    public static void main(String[] args) {

        //1.创建容器对象
        BlockQueueContainer<Integer> blockQueueContainer=
                new BlockQueueContainer<>(3);
        //2.创建生产者线程
        Thread producer=new Thread(()->{
            int i=1;
            while(true){
                System.out.println("put->"+i);
                blockQueueContainer.put(i++);
                try{Thread.sleep(1000);}catch (Exception e){}
            }
        });
        //3.创建消费者线程
        Thread consumer=new Thread(()->{
            while(true){
                Integer obj = blockQueueContainer.take();
                System.out.println("take->"+obj);
            }
        });
        //4.启动线程
        producer.start();
        consumer.start();
    }

}
