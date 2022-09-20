package com.jvm;

public class ClassLoaderTypeTests {
    public static void main(String[] args) {
        ClassLoader loader1=
        ClassLoader.getSystemClassLoader();
        System.out.println(loader1);//AppClassLoader (负责加载我们自己写的类)

        ClassLoader loader2=
        loader1.getParent();//ExtClassLoader(获取的是java自带的扩展类库中的类)
        System.out.println(loader2);

        ClassLoader loader3=
        loader2.getParent();
        System.out.println(loader3); //null,BootStrapClassLoader(负责加载基础类库中的类,例如Object,String)


        ClassLoader loader4=
        Object.class.getClassLoader();
        System.out.println(loader4);//null
    }
}
