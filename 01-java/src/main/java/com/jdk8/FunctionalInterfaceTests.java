package com.jdk8;

@FunctionalInterface
interface IC{
    void doMethod01();
    //void doMethod02();
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
