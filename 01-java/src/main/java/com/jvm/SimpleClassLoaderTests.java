package com.jvm;

import sun.java2d.pipe.SpanShapeRenderer;

import java.io.FileInputStream;
import java.io.InputStream;

class SimpleClassLoader extends ClassLoader{
    private String baseDir;
    public SimpleClassLoader(String baseDir){
        this.baseDir=baseDir;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        try {
            //先尝试自己加载
            return findClass(name);
        }catch (Exception e){
            //双亲委派(加载基础类库中的一些类)
            return super.loadClass(name,resolve);
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path=baseDir+name.replace(".","\\")+".class";
        System.out.println(path);
        InputStream in=null;
        try {
            //构建入流对象
            in=new FileInputStream(path);
            byte[] buf=new byte[in.available()];
            //读取字节码内容
            in.read(buf);
            //基于数组中的内容构建字节码对象
            return defineClass(name,buf,0,buf.length);
        }catch (Exception e){
            //e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            if(in!=null)try{in.close();}catch (Exception e){}
        }
    }
}
public class SimpleClassLoaderTests {
    public static void main(String[] args) throws ClassNotFoundException {
        SimpleClassLoader classLoader1=
                new SimpleClassLoader("d:\\bigfactory\\notes\\");
        Class<?> aClass1 = classLoader1.loadClass("pkg.Hello");//findClass

        SimpleClassLoader classLoader2=
                new SimpleClassLoader("d:\\bigfactory\\notes\\");
        Class<?> aClass2 = classLoader2.loadClass("pkg.Hello");//findClass
        System.out.println(aClass1==aClass2);
    }
}
