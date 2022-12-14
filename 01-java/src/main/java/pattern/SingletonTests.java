package pattern;

/**
 * 单例模式设计?
 * 定义:一个类在内存中实例只能有一份
 * 目的:减少对象的创建次数,实现对象的可重用性.
 * 方案:
 * 1)构造方法私有化
 * 2)在这个类的内部构建对象
 * 2.1)类加载时构建(饥饿模式)
 * 2.2)应用时候构建(懒加载方式)
 */
class Singleton{
    /**类加载构建对象
     * 优点:
     * 1)代码简单
     * 2)线程安全(可以保证对象只构建1次)
     * 缺点:
     * 1)假如Singleton对象比较大,创建对象后,又暂时不用,就会产生资源浪费.
     *
     * 适合场景: 小对象,频繁应用.
     * */
    private static Singleton instance=new Singleton();
    private Singleton(){}
    public static Singleton getInstance(){
        return instance;
    }
}

/**
 * 懒加载模式的单例设计
 */
class LazySingleton{
    /**
     * volatile 关键字通常用于修饰共享属性
     * 1)禁止指令重排序(JVM为了优化指令的执行,可以对指令进行重排序.)
     * 2)保证线程之间的可见性.
     * 3)不具备原子性.
     */
    private static volatile LazySingleton instance;
    private LazySingleton(){}
    public static  LazySingleton getInstance() {
        if(instance==null) {//A,B,C
            synchronized (LazySingleton.class) {
                if (instance == null) {//A,B
                    instance = new LazySingleton();
                    //构建对象的基本步骤
                    //1.分配内存
                    //2.初始化属性
                    //3.调用构造构造方法
                    //4.为instance赋值
                }
            }
        }
        return instance;
    }
}

public class SingletonTests {
    public static void main(String[] args) {
        for(int i=0;i<10;i++) {
            Thread t1 = new Thread(() -> {
                LazySingleton instance1 = LazySingleton.getInstance();
                System.out.println(instance1);
            });
            t1.start();
        }
    }
}
