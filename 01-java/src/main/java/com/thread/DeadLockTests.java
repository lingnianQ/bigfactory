package com.thread;

/**
 * 死锁的案例演示
 */
class SyncThread implements  Runnable{
    private Object lock1;
    private Object lock2;
    public SyncThread(Object lock1,Object lock2){
        this.lock1=lock1;
        this.lock2=lock2;
    }
    @Override
    public void run() {
        synchronized (lock1){
            doWork();
            synchronized (lock2){
                doWork();
            }
        }
    }
    void doWork(){
        System.out.println("execute task");
        try {
            Thread.sleep(3000);
        }catch (Exception e){}
    }
}

public class DeadLockTests {
    public static void main(String[] args) {
        Object obj1=new Object();
        Object obj2=new Object();
        Thread t1 = new Thread(new SyncThread(obj1, obj2));
        Thread t2 = new Thread(new SyncThread(obj2, obj1));
        t1.start();
        t2.start();
    }
}
