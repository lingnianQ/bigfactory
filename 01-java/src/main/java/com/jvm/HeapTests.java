package com.jvm;
//-Xms6m 最小堆6m
//-Xmx6m 最大堆6m
//-XX:+PrintGC 打印GC基本信息
public class HeapTests {
    public static void main(String[] args) {
           byte[] b1=new byte[1024*1024];
           byte[] b2=new byte[1024*1024];
           //byte[] b3=new byte[1024*1024];
           //byte[] b4=new byte[1024*1024];
           //byte[] b5=new byte[1024*1024];
    }
}
