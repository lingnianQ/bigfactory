package com.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadPoolExecutorTests {
    /**核心线程数*/
    static int corePoolSize=2;
    /**最大线程数*/
    static int maximumPoolSize=3;
    /**空闲线程释放时间*/
    static int keepAliveTime=60;
    /**阻塞式任务队列*/
    static BlockingQueue<Runnable> workQueue=new ArrayBlockingQueue<>(1);
    /**创建线程的工厂*/
    private static AtomicLong atomicLong=new AtomicLong(1);
    static ThreadFactory threadFactory=new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "tedu-2207-"+atomicLong.getAndIncrement());
        }
    };
    /**拒绝策略：
     * 1)AbortPolicy 直接拒绝，抛出异常
     * 2)DiscardPolicy 直接丢弃
     * 3)callerRunsPolicy 由调用着线程执行。
     * 4)DiscardOldestPolicy 丢弃队列中最早放入的任务，将当前任务交给线程池。
     * */
    static RejectedExecutionHandler handler=
            new ThreadPoolExecutor.CallerRunsPolicy();
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor=
         new ThreadPoolExecutor(corePoolSize,
               maximumPoolSize,
               keepAliveTime,
               TimeUnit.SECONDS,
               workQueue,
               threadFactory,
               handler);

        poolExecutor.execute(()->{
            String tName=Thread.currentThread().getName();
            System.out.println(tName+"->Task1");
            try {
                Thread.sleep(3000);
            }catch (Exception e){}
        });
        poolExecutor.execute(()->{
            String tName=Thread.currentThread().getName();
            System.out.println(tName+"->Task2");
            try {
                Thread.sleep(3000);
            }catch (Exception e){}
        });
        poolExecutor.execute(()->{
            String tName=Thread.currentThread().getName();
            System.out.println(tName+"->Task3");
            try {
                Thread.sleep(3000);
            }catch (Exception e){}
        });

        poolExecutor.execute(()->{
            String tName=Thread.currentThread().getName();
            System.out.println(tName+"->Task4");
            try {
                Thread.sleep(3000);
            }catch (Exception e){}
        });
        poolExecutor.execute(()->{
            String tName=Thread.currentThread().getName();
            System.out.println(tName+"->Task5");
            try {
                Thread.sleep(3000);
            }catch (Exception e){}
        });

        //poolExecutor.shutdownNow();
        poolExecutor.shutdown();
    }

}
