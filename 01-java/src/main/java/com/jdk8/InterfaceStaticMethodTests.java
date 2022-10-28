package com.jdk8;

interface IB{
    static void doMethod() {
        System.out.println("IB.doMethod()");
    }
}
class ClassB implements IB{
    static void doMethod() {
        System.out.println("ClassB.doMethod()");
    }
}
public class InterfaceStaticMethodTests {
    public static void main(String[] args) {
        IB b1=new ClassB();
        ClassB b2=new ClassB();
        //b1.doMethod();//错误，调用不到
        IB.doMethod();
        b2.doMethod();
    }
}
