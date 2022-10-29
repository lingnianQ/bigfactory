package com.java.ds;

public interface Stack {
    /**
     * 压栈
     * @param item
     */
    void push(Object item);
    /**
     * 出栈
     * @return
     */
    Object pop();
    /**
     * 获取栈顶元素，但不出栈。
     * @return
     */
    Object peek();
    /**
     * 获取栈中有效元素个数
     * @return
     */
    int size();
    /**
     * 判定栈是否为空
     * @return
     */
    boolean isEmpty();
}
