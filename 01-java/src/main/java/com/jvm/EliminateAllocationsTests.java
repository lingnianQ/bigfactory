package com.jvm;
//-Xms128m -Xmx128m -XX:+DoEscapeAnalysis -XX:-EliminateAllocations -XX:+PrintGC
public class EliminateAllocationsTests {
    public static void main(String[] args) {
        long t1=System.currentTimeMillis();
        for(int i=0;i<10000000;i++){
            alloc();
        }
        long t2=System.currentTimeMillis();
        System.out.println(t2-t1);
    }

    public static void alloc(){
        Point p1=new Point(10,20);
        //标量替换
        //int x=10;
        //int y=20;
    }
}
class Point{
    int x;
    int y;
    public Point(int x,int y){
        this.x=x;
        this.y=y;
    }
}
