package com.java.ds;

public class JavaMethodStackTests {

    static void c(){
        StackTraceElement[] stackTrace =
                Thread.currentThread().getStackTrace();
        for(StackTraceElement e:stackTrace){
            System.out.println(e.getMethodName());
        }
    }
    static void b(){c();}
    static void a(){b();}
    public static void main(String[] args) {
        a();
    }
}
