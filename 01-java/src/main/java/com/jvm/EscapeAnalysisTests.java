package com.jvm;
//-Xms128m -Xmx128m -XX:+DoEscapeAnalysis -XX:+PrintGC
//逃逸分析参数: -XX:+DoEscapeAnalysis
//标量替换: -XX:+EliminateAllocations
public class EscapeAnalysisTests {
    public static void main(String[] args) {
           long t1=System.currentTimeMillis();
           for(int i=0;i<10000000;i++){
               alloc1();
           }
           long t2=System.currentTimeMillis();
           System.out.println(t2-t1);
    }

    /**
     * 方法内部创建的对象，仅仅是在方法内部使用，没有外部引用指向这个对象，
     * 我们称之为对象为逃逸。
     */
    static void alloc1(){
        byte[]b1=new byte[1];
    }
    static byte[] b2;
    static void alloc2(){
        b2=new byte[1];//对象已逃逸，有外部引用指向了此对象
    }
    static byte[] alloc3(){
        return new byte[1];//对象已逃逸
    }
    static void alloc4(){
        alloc5(new byte[1]);//对象已逃逸
    }
    static void alloc5(byte[] data){

    }
}
