package com.chater5.stack;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);
        arrayStack.push(10);
        arrayStack.push(20);
        arrayStack.push(30);
        arrayStack.push(40);
        arrayStack.push(50);
        arrayStack.list();
    }
}

class ArrayStack{
    private int maxSize; //栈的大小
    private int[] stack; //数组，数组模拟栈，数据就放在该数组
    private int top = -1; //top表示栈顶，初始化为-1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }
    //栈满
    public boolean isFull(){
        return top == maxSize-1;
    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈
    public void push(int value){
        if (isFull()){
            System.out.println("栈满，无法添加~~~");
            return;
        }
        top++;
        stack[top]=value;
    }
    //出栈
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空，无法出栈~~");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //显示栈的情况[遍历栈],遍历时需要从栈顶开始显示数据
    public void list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据~~");
        }
        int index = top;
        while (index != -1){
            System.out.printf("stack[%d]=%d\n",index,stack[index]);
            index--;
        }
    }
}
