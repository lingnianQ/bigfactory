package com.java.ds;

import java.util.NoSuchElementException;

/**
 * 单项链表
 */
class SimpleSingleLinkedList{
    /**头节点*/
    private Node head;
    /**有效元素个数*/
    private int size;

    class Node{
        Object data;
        Node next;
        public Node(Object data){
            this.data=data;
        }
    }

    /**在头部位置添加元素*/
    public void addFirst(Object data){
        //1.构建节点对象
        Node newNode=new Node(data);
        //2.将节点对象作为头节点
        newNode.next=head;
        //3.设置新的头节点
        head=newNode;
        //4.更新有效元素个数
        size++;
    }
    /**添加到尾部*/
    public void addLast(Object data){
        //1.创建一个新节点
        Node newNode=new Node(data);
        //2.假如头节点为null，那新节点就是头节点
        if(head==null){head=newNode;return;}
        //3.假如头节点不为null，基于头节点找到为节点
        Node last=head;
        while(last.next!=null){
            last=last.next;
        }
        //4.将新节点添加到原尾节点之后
        last.next=newNode;
        //5.更新有效元素个数
        size++;
    }

    /**
     * 将data添加到链表的index位置
     * @param index
     * @param data
     */
    public void add(int index,Object data){
        //1.判断index是否越界
        if(index<0||index>size)
            throw new IndexOutOfBoundsException();
        //2.假如index的值0，应该就数据作为头节点
        if(index==0){addFirst(data);return;}
        //3.假如index的值为size，则将数据添加到尾节点
        if(index==size){addLast(data);return;}
        //4.假如index的值在0~size之间,在将数据添加到index位置
        //4.1查找index位置的上一个节点
        Node preNode=node(index-1);
        //4.2构建新的节点
        Node newNode= new Node(data);
        //4.3将新节点插入到index位置
        newNode.next=preNode.next;
        preNode.next=newNode;
        //5.更新有效元素个数
        size++;
    }

    /**查找指定位置的节点*/
    Node node(int pos){
        Node node=head;
        for(int i=0;i<pos;i++){
            node=node.next;
        }
        return node;
    }

    public void removeFirst(){
        if(size==0||head==null)
            throw new NoSuchElementException();
        head=head.next;
        size--;
    }
    public void removeLast(){
        if(size==0||head==null)
            throw new NoSuchElementException();
        if(size==1){
            head=null;
        }else{
            Node preNode=node(size-2);//最后一个节点位置为size-1
            preNode.next=null;
        }
        size--;
    }
    public void removeNode(int index){
        if(index<0||index>=size)
            throw new IndexOutOfBoundsException();
        if(index==0){
            removeFirst();
            return;
        }
        if(index==size-1){
            removeLast();
            return;
        }
        Node preNode=node(index-1);
        preNode.next=preNode.next.next;
        size--;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder("[");
        Node node=head;
        while(node!=null){
            sb.append(node.data).append(",");
            node=node.next;
        }
        if(sb.length()>1)sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return sb.toString();
    }
}

public class SimpleSingleLinkedListTests {
    public static void main(String[] args) {
        SimpleSingleLinkedList list=new SimpleSingleLinkedList();
        list.addFirst("A");
        list.addFirst("B");
        list.addFirst("C");
        list.addFirst("D");
        System.out.println(list);//dcba
        list.addLast("E");
        System.out.println(list);//dcbae
        list.add(2,"F");
        System.out.println(list);//dcfbae
        list.removeFirst();
        list.removeLast();
        System.out.println(list);//cfba
        list.removeNode(1);
        System.out.println(list);
        list=null;

    }
}
