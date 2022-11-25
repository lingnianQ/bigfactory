package com.java.collection;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTests {
    /**LinkedHashMap存储数据时可以通过链表记录key的添加顺序*/
    static void doTest01(){
        LinkedHashMap<String,Integer> map=new LinkedHashMap<>();
        map.put("A", 100);
        map.put("D",200);
        map.put("C", 300);
        System.out.println(map);//A,D,C
    }
    /**LinkedHashMap存储数据时可以通过链表记录key的访问顺序*/
    static void doTest02(){
        LinkedHashMap<String,Integer> map=
                new LinkedHashMap<>(3,//初始容量
                        0.75f,//加载因子
                        true);//访问顺序
        map.put("A", 100);
        map.put("D",200);
        map.put("C", 300);
        map.get("A");
        map.get("D");
        System.out.println(map);//CAD
    }

    /**将LinkedHashMap作为一个Cache对象，当cache满的时按照FIFO(先进先出)算法，
     * 移除容器中的对象*/
    static void doTest03(){
        int initialCapacity=3;
        LinkedHashMap<String,Integer> map=
                new LinkedHashMap<String,Integer>(initialCapacity){
                   //每次执行put方法时，底层都会调用
                    @Override
                    protected boolean removeEldestEntry(Map.Entry eldest) {
                        return size()>initialCapacity;
                    }
         };
        map.put("A", 100);
        map.put("D",200);
        map.put("C", 300);
        map.put("E",400);
        System.out.println(map);//DCE
    }
    /**将LinkedHashMap作为一个Cache对象，当cache满的时按照LRU(最近最少使用)算法，
     * 移除容器中的对象*/
    static void doTest04(){
        int initialCapacity=3;
        LinkedHashMap<String,Integer> map=
                new LinkedHashMap<String,Integer>(initialCapacity,//初始容量
                        0.75f,//加载因子
                        true){//访问顺序
                    @Override
                    protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                        return size()>initialCapacity;
                    }
                };
        map.put("A", 100);
        map.put("D",200);
        map.put("C", 300);
        map.get("A");
        map.put("E", 400);
        System.out.println(map);//CAE

    }



    public static void main(String[] args) {
         doTest04();
    }
}
