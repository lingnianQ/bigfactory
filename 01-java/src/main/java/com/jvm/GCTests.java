package com.jvm;
//-Xmx128m -Xms128m -XX:+PrintGC
//-XX:+PrintCommandLineFlags
public class GCTests {
    public static void main(String[] args) {
        long t1=System.currentTimeMillis();
        for (int i=0;i<10000000;i++){
            alloc();
        }
        System.out.println(System.currentTimeMillis()-t1);
    }
    static void alloc(){
        byte[] b1=new byte[1024];//1k
    }
}
