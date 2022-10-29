package com.java.ds;

import java.util.Arrays;

class SimpleArrayContainer {

    /**底层存储数据是数组*/
    private Object[] array;
    /**记录有效元素的个数*/
    private int size;
    /**未指定大小时容量是10*/
    public SimpleArrayContainer() {
        this(10);
    }
    /**定义集合初始化大小*/
    public SimpleArrayContainer(int capacity) {
        this.array=new Object[capacity];
    }

    /**实现数组元素的拷贝*/
    private Object[] copyOf(Object[] source,int length) {
        //1)创建新数组，并定义其长度为原数组的2倍大小
        Object[] newArray=new Object[2*length];
        //2)拷贝原有数组内容到新数组
        for(int i=0;i<source.length;i++) {
            newArray[i]=source[i];
        }
        //3)返回新创建的数组
        return newArray;
    }
    /**定义向数组size位置添加新元素的方法*/
    public void add(Object element) {
        //1.数组是否已满了，满了则扩容
        if(size==array.length) {
            //array=copyOf(array, 2*array.length);
            array= Arrays.copyOf(array, 2*array.length);
        }
        //2.添加新的元素
        array[size++]=element;
    }
    /**定义在指定位置添加新的元素的方法*/
    public void add(int index,Object element) {
        if(index<0||index>size)
            throw new IndexOutOfBoundsException();
        if(size==array.length) {
            array=Arrays.copyOf(array, 2*array.length);
        }
        System.arraycopy(array, index, array, index+1, size-index);
        array[index]=element;
        size++;
    }
    //定义按指定位置删除数组元素的方法
    public Object remove(int index) {
        //1.参数校验
        if(index<0||index>=size)
            throw new IndexOutOfBoundsException();
        //2.获取index位置元素
        Object element=array[index];
        //3.移动元素
//    int numMoved=size-index-1;
//    if(numMoved>0)
//    System.arraycopy(array, index+1, array, index, numMoved);
//    //4.修改最后一个元素的值，并且有效元素个数减1
//    this.array[--size]=null;
        fastRemove(index);
        return element;
    }
    private void fastRemove(int index) {
        int numMoved=size-index-1;
        if(numMoved>0)
            System.arraycopy(array, index+1, array, index, numMoved);
        array[--size]=null;
    }
    //按元素值移除元素
    public boolean remove(Object element) {
        if(element==null) {
            for(int index=0;index<size;index++) {
                if(array[index]==null) {
                    //移除元素
                    fastRemove(index);
                    return true;
                }
            }
        }else {
            for(int index=0;index<size;index++) {
                if(array[index].equals(element)) {
                    //移除元素
                    fastRemove(index);
                    return true;
                }
            }

        }
        return false;
    }

    public Object get(int index) {
        if(index<0||index>=size)
            throw new IndexOutOfBoundsException();
        return this.array[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
    public int size() {
        return size;
    }
}

public class SimpleArrayListTests {
    public static void main(String[] args) {
        SimpleArrayContainer container=new SimpleArrayContainer(2);
        container.add(100);//size
        container.add(200);
        container.add(300);
        System.out.println(container);//[100,200,300]
        container.add(1, 400);
        System.out.println(container);//[100,400,200,300]
        container.remove(1);
        System.out.println(container);//[100,200,300]
        container.remove((Object)200);
        System.out.println(container);//[100,300]
        Object element=container.get(0);
        System.out.println(element);
    }
}
