package com.thread;

/**
 * 单例设计：保证这个类型的对象在内存中只有一份
 * 1)构造方法私有化
 * 2)在类的内部构建对象,并赋值给一个static修饰符描述的属性
 * 3)对外界提供一个静态方法，可以访问到内部创建的对象。
 *
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**饿汉式单利：类加载时就创建对象
 * 应用场景：小对象、频繁用*/
class Singleton01{
    private Singleton01(){}
    private static  Singleton01 instance=new Singleton01();
    public static Singleton01 getInstance() {
        return instance;
    }
}
/**懒汉式单例：何时需要对象，何时创建*/
class Singleton02{
    private Singleton02(){}

    /**
     * volatile 作用
     * 1)禁止指令重排序(1条语句可能会有多条指令构成，JVM为了优化指令的指令可能会对其进行重排序)
     * 2)保证线程可见性(底层通过内存屏障实现-JMM)
     * 3)不保证原子性
     */
    private static volatile Singleton02 instance;
    /**
     * synchronized 是排它锁，这样一个线程在访问此方法时，其它线程都要阻塞
     * @return
     */
    public static Singleton02 getInstance() {
        Singleton02 inst=instance;
        if(inst==null) {//通过外层校验可以减少锁的竞争
            synchronized (Singleton02.class) {
                inst=instance;
                if (inst == null) {
                    instance = new Singleton02();
                    //1.分配内存
                    //2.初始化属性
                    //3.执行构造方法
                    //4.将对象内存地址赋值给instance
                }
            }
        }
        return instance;
    }
}
/**饿汉单利*/
enum Singleton03{
    INSTANCE;
    private Singleton03(){}
}

public class SingletonTests {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<10;i++) {
            executorService.execute(() -> {
                System.out.println(Singleton02.getInstance());
            });
        }
    }
}
