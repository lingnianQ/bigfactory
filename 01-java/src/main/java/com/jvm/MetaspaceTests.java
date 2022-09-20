package com.jvm;
//‐XX:MetaspaceSize=1k
//‐XX:MaxMetaspaceSize=1k
//-Xms128m -Xmx128m -XX:MetaspaceSize=30m -XX:MaxMetaspaceSize=30m -XX:+PrintGC
public class MetaspaceTests {//类加载多了会出现 OutOfMemoryError: Metaspace
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("Hello Metaspace");
    }
}
