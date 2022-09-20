package com.jvm;

public class IntegerTests {
    byte[] b2;
    public byte[] doMethod(){
        byte[] b1=new byte[1];//未逃逸对象(对象在方法在内部定义,同时在内部使用)
        b2=new byte[2];//逃逸对象
        byte[] b3=new byte[3];
        return b3;//逃逸对象
    }
    public static void main(String[] args) {
        Integer a=100; //Integer.valueOf(100) -128~+127
        Integer b=100;
        System.out.println(a==b);
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
