package com.java.ds;
class SimpleDoubleLinkedList{
    private Node first;
    private Node last;
    private int size;
    /**双向链表的节点类型*/
    class Node{
        Object data;
        Node next;
        Node pre;
        public Node(Node pre,Object data,Node next){
            this.pre=pre;
            this.data=data;
            this.next=next;
        }
    }
    //.....

}

public class SimpleDoubleLinkedListTests {
}
