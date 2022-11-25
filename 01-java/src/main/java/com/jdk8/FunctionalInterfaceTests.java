package com.jdk8;

/**
 * @FunctionalInterface 注解描述的接口为函数接口
 */
@FunctionalInterface
interface IC{
    /**函数式接口中只允许有一个抽象方法*/
    void doMethod01();
    //void doMethod02();
    default void doMethod03(){}
    static void doMethod04(){}
}
class ClassC implements IC{
    @Override
    public void doMethod01() {
        System.out.println("ClassC.doMethod01");
    }
}
public class FunctionalInterfaceTests {
    public static void main(String[] args) {
        IC c1=new ClassC();
        c1.doMethod01();

        IC c2=new IC(){
            @Override
            public void doMethod01() {
                System.out.println("Inner1.doMethod1");
            }
        };
        c2.doMethod01();

        IC c3=()->{
            System.out.println("Lambda.doMethod02");
        };
        c3.doMethod01();
    }
}
