package com.jvm;
//-Xss2m 设置栈内存的大小为2m
public class StackTests {//StackOverflowError
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        //1663662757305
        //1663662854684
        main(args);
    }
}
