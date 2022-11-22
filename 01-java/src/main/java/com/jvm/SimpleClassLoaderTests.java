package com.jvm;

import sun.java2d.pipe.SpanShapeRenderer;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 自定义的类加载器
 */
class SimpleClassLoader extends ClassLoader{
    /**通过baseDir指定要加载的类的根路径*/
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

    /**
     * findClass负责查找类，并将类的信息读取到内存，并创建Class字节码对象
     * @param name 类全名(包名.类名)
     * @return 字节码对象
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //1.定义类查找路径
        String path=baseDir+name.replace(".","\\")+".class";
        System.out.println(path);
        //2.通过IO读取指定位置的class
        InputStream in=null;
        try {
            //构建入流对象
            in=new FileInputStream(path);
            byte[] buf=new byte[in.available()];//in.available()用户获取流中有效字节的个数
            //读取字节码内容
            in.read(buf);
        //3.基于数组中的内容构建字节码对象
            return defineClass(name,buf,0,buf.length);
        }catch (Exception e){
            //e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            if(in!=null)try{in.close();}catch (Exception e){}
        }
    }
}

// -XX:+TraceClassLoading
public class SimpleClassLoaderTests {
    public static void main(String[] args) throws ClassNotFoundException {
        SimpleClassLoader classLoader1=
                new SimpleClassLoader("E:\\TCGBIV\\DEVCODES\\bigfactory\\notes\\");
        Class<?> aClass1 = classLoader1.loadClass("pkg.Hello");//findClass
       //Class<?> aClass2 = classLoader1.loadClass("pkg.Hello");//findClass
       //System.out.println(aClass1==aClass2);//true
        SimpleClassLoader classLoader2=
                new SimpleClassLoader("E:\\TCGBIV\\DEVCODES\\bigfactory\\notes\\");
        Class<?> aClass3= classLoader2.loadClass("pkg.Hello");//findClass
        System.out.println(aClass1==aClass3);//false
    }
}
