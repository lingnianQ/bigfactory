package com.jdk8;

interface IB{
    static void doMethod() {
        System.out.println("IB.doMethod()");
    }
}
class ClassB implements IB{

    static String doMethod() {
        System.out.println("ClassB.doMethod()");
        return "";
    }
}
class ClassM{
    static void doMethod(){
        System.out.println("ClassM.doMethod");
    }
}
class ClassN extends  ClassM{

    static void doMethod(){
        System.out.println("ClassN.doMethod");
    }
}

public class InterfaceStaticMethodTests {
    public static void main(String[] args) {
        IB b1=new ClassB();
        ClassB b2=new ClassB();
       // b1.doMethod();//错误，调用不到
        IB.doMethod();
        b2.doMethod();

        ClassM m1=new ClassN();
        m1.doMethod();
    }
}
