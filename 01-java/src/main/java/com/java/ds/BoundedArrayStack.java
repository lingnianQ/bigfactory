package com.java.ds;

import java.util.NoSuchElementException;

/**
 * 基于数组实现数据的存储
 */
public class BoundedArrayStack implements Stack{
    /**用于数据存储*/
    private Object[] array;
    /**记录有效元素个数*/
    private int size;

    public BoundedArrayStack(int capacity) {
        this.array = new Object[capacity];
    }

    /**
     * 入栈操作，数据放在size位置
     * @param item
     */
    @Override
    public void push(Object item) {
        if(size==array.length){
            throw new IllegalArgumentException("Stack is Full");
        }
        this.array[size++]=item;
    }

    /**
     * 出栈操作
     * @return
     */
    @Override
    public Object pop() {
        if(size==0)
            throw new NoSuchElementException("Stack is Empty");
        Object element=array[size-1];
        array[--size]=null;
        return element;
    }

    /**获取栈顶元素*/
    @Override
    public Object peek() {
        if(size==0)
            throw new NoSuchElementException("Stack is Empty");
        return array[size-1];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    public static void main(String[] args) {
        BoundedArrayStack stack=new BoundedArrayStack(2);
        stack.push(10);
        stack.push(20);
        Object pop = stack.pop();//20
        System.out.println(pop);
        Object peek = stack.peek();//10
        System.out.println(peek);

    }
}
