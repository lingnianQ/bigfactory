package com.jdk8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class LambdaTests {
     static void doTest01(){
        List<String> asList = Arrays.asList("A", "B", "C");
        //asList.remove("A");//不允许修改构建的集合对象

        for(String e:asList){
            System.out.println(e);
        }


        asList.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        asList.forEach(s-> System.out.println(s));
    }

    static void doTest02(){
        String[] strArray= {"abcd","ab","abc"};
    /*    Arrays.sort(strArray, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        });*/
        Arrays.sort(strArray, (o1, o2) ->o1.length() - o2.length());

        System.out.println(Arrays.toString(strArray));
    }
    static class A{
         void doSay(){
             System.out.println("hello");
         }
    }
    static void doTest03(){
         Runnable t=()->{
             System.out.println("run");
         };
         A a1=new A(){
             @Override
             void doSay() {
                 System.out.println("a1.hello");
             }
         };
         a1.doSay();
    }

    public static void main(String[] args) {
      // doTest01();
       // doTest02();
        doTest03();
    }
}
