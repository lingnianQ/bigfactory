package com.java.ds;

import java.util.Arrays;

/**
 * 手写ArrayList集合(仿Java中的ArrayList)
 * 1)数据的存储结构(基于数组存储数据)
 * 2)数据的逻辑操作(添加、移除)
 */
class SimpleArrayList<E>{
    /**基于数组存储元素*/
    private Object[] elementData;//null
    /**记录有效元素的个数*/
    private int size;
    /**定义一个空的数组*/

    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    private static final int DEFAULT_CAPACITY = 10;

    public SimpleArrayList() {
        this.elementData=DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }
    public SimpleArrayList(int initialCapacity){
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }
    /**将元素添加到size位置*/
    public boolean add(E e) {
        //检查容器是否需要扩容，假如需要则扩容，并将原有内容拷贝到新数组
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        //将元素添加size位置
        elementData[size++] = e;
        //添加成功返回值为true
        return true;
    }
    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }
    private void ensureExplicitCapacity(int minCapacity) {
        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }
    /**计算数组容量
     * 1)假如构建SimpleArrayList集合对象时传入的初始容量小于默认容量，则返回默认容量大小
     * 2)假如构建SimpleArrayList集合对象时传入的初始容量大于默认容量，则返回传入的容量值*/
    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    @Override
    public String toString() {
        return "SimpleArrayList{" +
                "elementData=" + Arrays.toString(elementData) +
                ", size=" + size +
                '}';
    }
}
public class SimpleArrayListCollectionTests {
    public static void main(String[] args) {
        SimpleArrayList<Integer> simpleArrayList= new SimpleArrayList<>(-1);
        for(int i=0;i<10;i++) {
            simpleArrayList.add(i);
        }
        System.out.println(simpleArrayList);
    }
}
