package com.jvm;

class Outer{

    static class Inner extends Thread{
        @Override
        public void run() {
            while(true){
                try{Thread.sleep(500);}catch (Exception e){}
            }
        }
    }

    /**
     * 此方法会在对象回收之前执行，这里重写主要是为监控，方法所在的类的对象是否销毁了
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize()");
    }
}

public class OuterInnerTests {
    public static void main(String[] args) {
        Outer o1=new Outer();
        Outer.Inner inner=new Outer.Inner();
        inner.start();
        o1=null;
        //手动启动
        System.gc();
    }
}
