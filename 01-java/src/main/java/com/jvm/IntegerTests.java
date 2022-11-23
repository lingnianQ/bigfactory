package com.jvm;

/**
 * Integer类中的整数池中默认存储的数据为-128~+127
 * 假如希望调整整数池的最大值，可以通过如下JVM参数进行设计，
 * 例如：-XX:AutoBoxCacheMax=500
 */
public class IntegerTests {

    public static void main(String[] args) {
        Integer a=100; //Integer.valueOf(100) -128~+127
        Integer b=100;
        System.out.println(a==b);//true
        Integer c=200;
        int d=200;
        System.out.println(c==d);//对象类型与基本类型比较
        Integer e=200;
        System.out.println(c==e);//false

        Double d1=1.5;
        Double d2=1.5;
        System.out.println(d1==d2);//false

    }
}
