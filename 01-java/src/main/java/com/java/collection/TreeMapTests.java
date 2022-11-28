package com.java.collection;

import java.io.Serializable;
import java.util.TreeMap;

class User implements Comparable{
    private Long id;
    private String username;

    public User(Long id,String username){
        this.id=id;
        this.username=username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        User user=(User)o;
        return this.username.compareTo(user.username);
    }
}

public class TreeMapTests {
    static void doTest01(){
        TreeMap<String,Integer> treeMap=new TreeMap<>();
        treeMap.put("D", 100);
        treeMap.put("C", 100);
        treeMap.put("E", 100);
        treeMap.put("B", 100);
        treeMap.put("A", 100);
        System.out.println(treeMap);
    }
    static void doTest02(){
        TreeMap<User,String> treeMap=new TreeMap<>();
        treeMap.put(new User(20L,"chuanqi"),"B");
        treeMap.put(new User(30L,"guobin"),"C");
        treeMap.put(new User(10L,"kejing"),"A");
        System.out.println(treeMap);
    }
    public static void main(String[] args) {
        doTest02();
    }
}
