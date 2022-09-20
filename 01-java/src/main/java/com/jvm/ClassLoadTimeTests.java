package com.jvm;
class ClassA{
    static int a=10;
    /**类加载并执行类的初始化时会执行静态代码块*/
    static{
        System.out.println("ClassA.static{}");
    }
}
class ClassB extends ClassA{
    static{
        System.out.println("ClassB.static{}");
    }
}
public class ClassLoadTimeTests {
    public static void main(String[] args) throws ClassNotFoundException {
        //1.显式加载
        //ClassLoader.getSystemClassLoader().loadClass("com.jvm.ClassA");
        //2.隐式加载(访问类中成员,构建类的对象)
        //int a=ClassA.a;

        //3.被动加载
        int a=ClassB.a; //ClassB为被动加载(被动加载静态代码块不会执行)
    }
}
