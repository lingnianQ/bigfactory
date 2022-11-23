package com.jvm;

import java.util.ArrayList;
import java.util.List;

//-Xms128m -Xmx128m -XX:+PrintGC
public class OutOfMemoryErrorTests {
    public static void main(String[] args) throws InterruptedException {
        List<byte[]> list=new ArrayList<>();
        while(true){
            byte[] b1=new byte[1024*1024];
            list.add(b1);//内存泄漏
            Thread.sleep(300);
        }
    }
}
