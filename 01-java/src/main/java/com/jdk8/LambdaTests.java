package com.jdk8;

import java.util.Arrays;
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
    public static void main(String[] args) {
       doTest01();
    }
}
