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
    /**将一个新节点添加到头部节点位置*/
    public void addFirst(Object data){
        //1.获取第一个节点，并且将其赋值给一个Node对象
        Node copyFirst=first;
        //2.创建一个新节点
        Node newNode=new Node(null, data, first);
        //3.将新的节点作为头节点
        first=newNode;
        //4.设置新节点在链表中的位置
        if(copyFirst==null){
            last=newNode;
        }else{
            copyFirst.pre=newNode;
        }
        //5.更新有效元素个数
        size++;
    }
    /**在链表的尾部添加新节点*/
    public void addLast(Object data){
        //1.获取最后个节点，并且将其赋值给一个Node对象
        Node copyLast=last;
        //2.创建一个新节点
        Node newNode=new Node(last, data, null);
        //3.将新的节点作为头节点
        last=newNode;
        //4.设置新节点在链表中的位置
        if(copyLast==null){
            first=newNode;
        }else{
            copyLast.next=newNode;
        }
        //5.更新有效元素个数
        size++;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder("[");
        Node node=first;
        while(node!=null){
            sb.append(node.data).append(",");
            node=node.next;
        }
        if(sb.length()>1)sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return sb.toString();
    }

}

public class SimpleDoubleLinkedListTests {
    public static void main(String[] args) {
        SimpleDoubleLinkedList list=new SimpleDoubleLinkedList();
        list.addFirst("A");
        list.addFirst("B");
        list.addFirst("C");
        System.out.println(list);//CBA
        list.addLast("E");
        list.addLast("F");
        System.out.println(list);//CBAEF
    }
}
