package com.chapter3.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(4);
        char key ;
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数：");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try{
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~~~");
    }
}
class CircleArrayQueue{
    private int maxSize;//表示数组的最大容量
    //front变量的含义做一个调整：front就指向队列的第一个元素，也就是说arr[front]就是队列的第一个元素
    //front的初始值为0
    private int front;
    //rear指向队列的最后一个元素的后一个位置。约定空出一个空间。
    private int rear;//队列尾
    int[] arr;//该数据用于存放数据，模拟队列

    public CircleArrayQueue(int maxSize){
        this.maxSize=maxSize;
        arr = new int[maxSize];
    }
    //判断队列是否已满
    public boolean isFull(){
        return (rear+1)%maxSize == front;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }
    //添加数据到队列
    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列已满~~~~");
            return;
        }
        arr[rear]=n;
        rear=(rear+1)%maxSize;
    }
    //获取队列的数据，出队列
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空，不能取数据~~~");
        }
        int tmp=arr[front];
        front=(front+1)%maxSize;
        return tmp;
    }
    //显示队列的所有数据
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列空的，没有数据~~~~");
            return;
        }
        for(int i=0;i<size();i++){
            System.out.printf("arr[%d]=%d\n",(front+i)%maxSize,arr[(front+i)%maxSize]);
        }
    }
    //求出当前队列有效数据的个数
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }
    //显示队列头数据，注意不是取数据
    public int headQueue(){
        //判断
        if (isEmpty()){
            throw new RuntimeException("队列空的，没有数据~~~");
        }
        return arr[front];
    }

}
