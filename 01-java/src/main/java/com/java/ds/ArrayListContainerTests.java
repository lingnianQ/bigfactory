package com.java.ds;

import java.util.Arrays;

class ArrayListContainer<E>{
    /**底层存储数据是数组*/
    private Object[] array;
    /**记录有效元素的个数*/
    private int size;

    public ArrayListContainer(){
        this(10);
    }

    public ArrayListContainer(int cap){
        this.array=new Object[cap];
    }

    public boolean add(E e){
        //1.检查容器是否已满，假如满了则扩容
        if(size==this.array.length){
            array=Arrays.copyOf(array, array.length*2);
        }
        //2.添加元素
        array[size]=e;
        //3.修改有效元素个数
        size++;
        //4.返回一个boolean值
        return true;
    }
    public E remove(int index){
        //判断数组下标的有效性
        if(index<0||index>=size)
            throw new IndexOutOfBoundsException();
        //获取指定位置元素
        E e=(E)array[index];
        System.arraycopy(array, index+1,
                         array, index, size-index-1);
        size--;
        array[size]=null;
        return e;
    }
    public int size(){
        return size;
    }

    @Override
    public String toString() {
        return "ArrayListContainer{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                '}';
    }
}
public class ArrayListContainerTests {
    public static void main(String[] args) {
          ArrayListContainer<Integer> list=new ArrayListContainer<>();
          for(int i=0;i<10;i++){
              list.add(i);
          }
        System.out.println(list);
          list.remove(0);
          list.remove(2);
         System.out.println(list);
    }
}
