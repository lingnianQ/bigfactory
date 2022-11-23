package com.jvm;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
//https://mp.weixin.qq.com/s?__biz=Mzg4MzIxMDE2Mg==&mid=2247484171&idx=1&sn=b8f1e06437e0c8538aa921096823353f&chksm=cf4ba1d6f83c28c0c3399628273d487a128616854fe0c9d310591d33662866b56b68ef411e71&token=1742423490&lang=zh_CN#rd
//-Xms128m -Xmx128m -XX:+PrintGC
public class ReferenceTests {
    //强引用引用的对象即使是内存溢出都不会销毁对象.
    static void doStrongRef() throws InterruptedException {
        List<byte[]> cache=new ArrayList<>();
        while(true){
            //b1这里为强引用
            byte[] b1=new byte[1024*1024];
            //list集合对b1指向的对象的引用也是强引用
            cache.add(b1);
            Thread.sleep(300);
        }
    }
    //软引用:SoftReference引用对象时为软引用
    //软引用引用的对象会在内存不足时,将引用的对象销毁.
    static void doSoftRef() throws InterruptedException {
        List<SoftReference> cache=new ArrayList<>();
            while (true) {
                //b1这里为强引用
                byte[] b1 = new byte[1024 * 1024];
                //list集合对b1指向的对象的引用是软引用
                cache.add(new SoftReference<byte[]>(b1));
                Thread.sleep(300);
            }
    }
    //弱引用:WeakReference引用对象时为弱引用
    //弱引用引用的对象可能会在GC时,就会将引用的对象销毁.
    static void doWeakRef() throws InterruptedException {
        List<WeakReference> cache=new ArrayList<>();
        while (true) {
            //b1这里为强引用
            byte[] b1 = new byte[1024 * 1024];
            //list集合对b1指向的对象的引用是弱引用
            cache.add(new WeakReference<byte[]>(b1));
            Thread.sleep(300);
        }

    }
    //虚引用:PhantomReference引用对象时为虚引用,相当于没有引用,生命力最弱,只是为了记录对象的销毁状态
    static void doPhantomRef(){
        List<PhantomReference> list=new ArrayList<>();
        //引用队列
        ReferenceQueue queue=new ReferenceQueue ();//记录已经被销毁的对象的引用
        while (true) {
            //b1这里为强引用
            byte[] b1 = new byte[1024 * 1024];
            //list集合对b1指向的对象的引用也是强引用
            list.add(new PhantomReference<byte[]>(b1,queue));
        }
    }
    public static void main(String[] args) throws Exception{
            //强引用
            //doStrongRef();
            //软引用
            //doSoftRef();
            //弱引用
            doWeakRef();
            //虚引用(一般不用)
            //doPhantomRef();
    }
}
