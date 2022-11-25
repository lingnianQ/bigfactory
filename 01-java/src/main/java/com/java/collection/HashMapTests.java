package com.java.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class HashMapTests {
    /**HashMap存储数据时不能记录key的添加顺序*/
    static void doTest01(){
        Map<String,Integer> map=new HashMap<>();
        map.put("A", 100);
        map.put("D",200);
        map.put("C", 300);
        System.out.println(map);//A,C,D
    }
    /**HashMap中的key和value都允许为null*/
    static void doTest02(){
        Map<String,Integer> map=new HashMap<>();
        map.put(null, null);
        map.put(null, 100);
        System.out.println(map);
    }
    static Map<String,Object> map=new HashMap<>();

    static void doTest03(){
        for(int i=0;i<100;i++) {
            map.put(UUID.randomUUID().toString(), UUID.randomUUID());
        }
    }
    static void doTest04(){
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for(Map.Entry<String, Object> entry:entries){
            map.remove(entry.getKey());
        }
    }

    public static void main(String[] args) {
         // doTest02();
       Thread t1=new Thread(()->{
            for(int i=0;i<1000000;i++) {
                doTest03();
                doTest04();
            }
        });
       Thread t2=new Thread(()->{
            for(int i=0;i<100000;i++) {
                doTest03();
                doTest04();
            }
        });
       Thread t3=new Thread(()->{
            for(int i=0;i<100000;i++) {
                doTest03();
                doTest04();
            }
        });
       t1.start();
       t2.start();
       t3.start();
    }
}
