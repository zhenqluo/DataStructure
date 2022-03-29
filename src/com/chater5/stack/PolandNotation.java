package com.chater5.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //先定义一个逆波兰表达式，为了方便，逆波兰表达式的数字和符号使用空格隔开
/*        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        List<String> ls = getListString(suffixExpression);
        int result = calculate(ls);
        System.out.println("逆波兰表达式"+suffixExpression+"运行结果是："+result);*/
        String expression="11+((22+3)*4)-56"; //
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println(infixExpressionList);
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println(parseSuffixExpressionList);
        int result = calculate(parseSuffixExpressionList);
        System.out.println("逆波兰表达式"+parseSuffixExpressionList+"运行结果是："+result);
    }

    public static List<String> parseSuffixExpressionList(List<String> ls){
        //定义两个栈
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        for (String str:ls){
            if (str.matches("\\d+")){
                s2.add(str);
            }else if (str.equals("(")){
                s1.push(str);
            }else if (str.equals(")")){
                while (true){
                    if (s1.peek().equals("(")){
                        break;
                    }
                    s2.add(s1.pop());
                }
                s1.pop();
            }else {
                //s1符号栈为空直接入栈，当前符号优先级>s1栈顶符号优先级则入栈
                //s1栈顶元素是左括号，则入栈
                //当前符号优先级<=s1栈顶符号优先级则s1出栈加入s2
                while(true){
//                    if (s1.size() != 0 && s1.peek() != "("){ //排错排了很久，字符串的比较要用eques
                    if (s1.size() != 0 && !"(".equals(s1.peek())){
                        String str2 = s1.peek();
                        if (Operation.getValue(s1.peek()) >= Operation.getValue(str)){
                            s2.add(s1.pop());
                        }
                    }else {
                        break;
                    }
                }
                s1.push(str);
            }
        }
        while (s1.size()>0){
            s2.add(s1.pop());
        }
        return s2;
    }

    //方法：将中缀表达式转成对应的list
    public static List<String> toInfixExpressionList(String s){
        int i = 0;
        char ch;
        ArrayList<String> list = new ArrayList<>();
        String str="";//用于多位数的拼接
        while (i<=s.length()-1){
            ch = s.charAt(i);
            if (ch<48 || ch>57){
                list.add(ch+"");
            }else {
                str+=ch;
                if (i==s.length()-1){
                    list.add(str);
                    str="";
                }else {
                    if (s.charAt(i+1)<48 || s.charAt(i+1)>57){
                        list.add(str);
                        str="";
                    }
                }
            }
            i++;
        }
        return list;
    }



















    public static List<String> getListString(String suffixExpression){
        String[] items = suffixExpression.split(" ");
        ArrayList<String> arrayList = new ArrayList<>();
        for (String item : items){
            arrayList.add(item);
        }
        return arrayList;
    }
    public static int calculate(List<String> ls){
        //创建一个栈，只需要一个栈即可
        Stack<String> stack = new Stack<String>();
        int result;
        for (String item:ls){
            if (item.matches("\\d+")){
                stack.push(item);
            }else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                if (item.equals("+")){
                    result=num1+num2;
                }else if (item.equals("-") ){
                    result=num1-num2;
                }else if (item.equals("*")){
                    result=num1*num2;
                }else if (item.equals("/")){
                    result =num1/num2;
                }else {
                    throw new RuntimeException("运算符有误~~~");
                }
                stack.push(result+"");
            }
        }
        return Integer.parseInt(stack.pop());
    }

}

//编写一个类Operation可以返回一个运算符对应的优先级
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;
    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result=ADD;
                break;
            case "-":
                result=SUB;
                break;
            case "*":
                result=MUL;
                break;
            case "/":
                result=DIV;
                break;
            default:
                System.out.println("运算符非法~~");
                break;
        }
        return result;
    }
}
