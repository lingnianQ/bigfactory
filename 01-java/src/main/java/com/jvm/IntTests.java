package com.jvm;

//JVM参数
//-XX:+TraceClassLoading (基于此参数可以跟踪类的加载过程)
public class IntTests{
    public static void main(String[] args) throws InterruptedException {
        int a=10;
        int b=20;
        int c=a+b;
    }
}
