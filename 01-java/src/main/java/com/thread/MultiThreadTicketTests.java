package com.thread;
class TicketTask implements Runnable{
    int ticket=100;
    @Override
    public void run() {
        doTicket();
    }
    public void doTicket() {
        while(true) {
            synchronized (this) {
                if (ticket <= 0) break;
                System.out.println(Thread.currentThread().getName() + "-->" + ticket--);
            }
        }
    }
}
public class MultiThreadTicketTests {
    public static void main(String[] args) {
        TicketTask task=new TicketTask();
        Thread t1=new Thread(task);
        Thread t2=new Thread(task);
        Thread t3=new Thread(task);
        Thread t4=new Thread(task);
        Thread t5=new Thread(task);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
