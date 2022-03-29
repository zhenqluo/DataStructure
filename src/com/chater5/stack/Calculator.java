package com.chater5.stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "7*2*2-5+1-5+3-4";
        //创建两个栈：数栈、符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch
        String keepNum = "";//用于拼接多位数
        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOper(ch)) {//如果是运算符
                if (operStack.isEmpty()){
                    operStack.push(ch);
                }else {
                    if (operStack.priority(ch)  <= operStack.priority(operStack.peek())){
                        num1=numStack.pop();
                        num2=numStack.pop();
                        oper=operStack.pop();
                        res=numStack.cal(num1,num2,oper);
                        //将运算结果入数栈
                        numStack.push(res);
                        //然后将当前的操作符入符号栈(编程时漏了这个步骤)
                        operStack.push(ch);
                    }else {
                        operStack.push(ch);
                    }
                }

            } else { //不是运算符，就是数字
                keepNum += ch;
                //判断是不是字符串的最后一个字符，是的话则压栈
                if (index >= expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                    keepNum = "";
                } else {
                    //判断下一个字符是数字还是运算符，是运算符则把数字压栈
                    if (numStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index++;
            if (index >= expression.length()){
                break;
            }
        }
        while (true){
            if (operStack.isEmpty()){
                break;
            }
            num1=numStack.pop();
            num2=numStack.pop();
            oper=operStack.pop();
            res=numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        System.out.printf("运算式%s的值是%d",expression,numStack.pop());
    }
}

class ArrayStack2 {
    private int maxSize; //栈的大小
    private int[] stack; //数组，数组模拟栈，数据就放在该数组
    private int top = -1; //top表示栈顶，初始化为-1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //返回栈顶元素，但不出栈
    public int peek() {
        return stack[top];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满，无法添加~~~");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，无法出栈~~");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况[遍历栈],遍历时需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据~~");
        }
        int index = top;
        while (index != -1) {
            System.out.printf("stack[%d]=%d\n", index, stack[index]);
            index--;
        }
    }

    //返回运算符的优先级，优先级程序员来确定，数字越大优先级越高
    public int priority(int oper) {
        if (oper == '+' || oper == '-') {
            return 0;
        } else if (oper == '/' || oper == '*') {
            return 1;
        } else {
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num2 + num1;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

}
