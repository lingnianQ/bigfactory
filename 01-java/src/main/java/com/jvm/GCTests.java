package com.jvm;
//-Xmx128m -Xms128m
//如何查看使用的GC -XX:+PrintCommandLineFlags

//-XX:+UseSerialGC
//-XX:+UseParallelGC
//-XX:+UseConcMarkSweepGC
//-XX:+UseG1GC

public class GCTests {
    public static void main(String[] args) {
        long t1=System.currentTimeMillis();
        for (int i=0;i<10000000;i++){
            alloc();
        }
        System.out.println(System.currentTimeMillis()-t1);
    }
    static void alloc(){
        byte[] b1=new byte[1024];
    }
}
