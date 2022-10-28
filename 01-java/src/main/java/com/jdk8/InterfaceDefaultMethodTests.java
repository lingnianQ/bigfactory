package com.jdk8;

/**
 * JDK8中的默认方法
 * 当我们需要扩展接口功能，但又不需要在具体类中进行实现，可使用默认方法设计。
 */
interface IA{
    void doMethod();
    default void doMethod01(){
        System.out.println("==IA.doMethod01==");
    }
    default void doMethod02(){
        System.out.println("==IA.doMethod02==");
    }
}
class ClassA implements IA{
    @Override
    public void doMethod() {
        System.out.println("==doMethod==");
    }

    @Override
    public void doMethod01() {
        System.out.println("==ClassA.doMethod01==");
    }
}

public class InterfaceDefaultMethodTests {
    public static void main(String[] args) {
        IA a1=new ClassA();
        a1.doMethod01();
    }
}
