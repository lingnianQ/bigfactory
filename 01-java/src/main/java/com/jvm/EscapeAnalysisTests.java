package com.jvm;
//-Xms128m -Xmx128m -XX:+DoEscapeAnalysis -XX:+PrintGC
//逃逸分析参数: -XX:+DoEscapeAnalysis
//标量替换: -XX:+EliminateAllocations
public class EscapeAnalysisTests {
    public static void main(String[] args) {
           long t1=System.currentTimeMillis();
           for(int i=0;i<10000000;i++){
               alloc();
           }
           long t2=System.currentTimeMillis();
           System.out.println(t2-t1);
    }
    //static byte[]b1;
    static void alloc(){
        byte[]b1=new byte[1];
        //b1=new byte[1];//逃逸
    }
}
