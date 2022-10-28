package com.jdk8;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用
 */
public class MethodReferenceTests {
    //实例方法引用
    static void doTest01(){
        List<String> asList = Arrays.asList("A", "B", "C");
        //asList.forEach((e)->{
        //    System.out.println(e);
        //});
        //方法引用的实现(实例方法)
        asList.forEach(System.out::println);
    }
    //构造方法引用
    public static void doTest02(){
        //1.传统方式构建对象
        Supplier<Date> sp1=new Supplier<Date>() {
            @Override
            public Date get() {
                return new Date();
            }
        };
        Date date = sp1.get();
        System.out.println(date);
        //2.基于Lambda方式构建对象
        Supplier<Date> sp2=()->{
            return new Date();
        };
        System.out.println(sp2.get());
        //3.基于构造方法引用构建对象
        Supplier<Date> sp3=Date::new;
        System.out.println(sp3.get());
    }
    //静态方法引用
    static void doTest03(){

        //1.传统方式
        Function<String,Integer> f1=new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        };
        System.out.println(f1.apply("100"));
        //2.Lambda方式

        Function<String,Integer> f2=(s)->Integer.parseInt(s);
        System.out.println(f2.apply("200"));

        //3.静态方法引用
        Function<String,Integer> f3=Integer::parseInt;
        System.out.println(f3.apply("300"));
    }


    public static void main(String[] args) {
       //doTest01();
      //doTest02();
       doTest03();

    }
}
