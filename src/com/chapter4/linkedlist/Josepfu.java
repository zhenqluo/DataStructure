package com.chapter4.linkedlist;

public class Josepfu {
    public static void main(String[] args) {
        CicleSingleLinkedList cicleSingleLinkedList = new CicleSingleLinkedList();
        cicleSingleLinkedList.addBoy(5);
        //cicleSingleLinkedList.showBoy();
        cicleSingleLinkedList.countBoy(1,2,5);
    }
}
class CicleSingleLinkedList{
    private Boy first = null;
    //添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums){
        if (nums<1){
            System.out.println("nums的值不争取");
            return;
        }
        Boy curBoy = null;
        for (int i=1;i<=nums;i++){
            Boy boy = new Boy(i);
            if (first == null){
                first = boy;
                first.setNext(first);
                curBoy = first;
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }
    //遍历当前的环形链表
    public void showBoy(){
        if (first == null){
            System.out.println("环形链表为空~~~");
            return;
        }
        Boy curBoy = first;
        while (true){
            System.out.println(curBoy.getNo());
            if (curBoy.getNext() == first){
                break;
            }
            curBoy=curBoy.getNext();
        }
    }
    //根据用户的输入，计算出小孩出圈的顺序

    /**
     *
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩在圈中（调用addBoy方法创建环形链表）
     */
    public void countBoy(int startNo,int countNum,int nums){
        if (first==null || startNo<1 || startNo>nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建辅助指针，帮助完成小孩出圈
        //helper辅助指针事先应该指向环形链表的最后这个节点
        Boy helper = first;
        while (true){
            if (helper.getNext() == first){
                break;
            }
            helper=helper.getNext();
        }
//        小孩报数前，先让first和helper移动k-1次
        for (int i=1;i<startNo;i++){
            first=first.getNext();
            helper=helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时移动m-1次，然后出圈
        //这里是一个循环操作，直到圈中只有一个节点
        while (first != helper){
            for (int i=1;i<countNum;i++){
                first=first.getNext();
                helper=helper.getNext();
            }
            System.out.printf("编号为%d出圈\n",first.getNo());
            first=first.getNext();
            helper.setNext(first);
        }
        System.out.printf("编号为%d出圈\n",first.getNo());
    }
}

class Boy{
    private int no;
    private  Boy next;

    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
