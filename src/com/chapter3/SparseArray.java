package com.chapter3;

import java.io.*;
import java.util.ArrayList;

public class SparseArray {
    //二维数组int array[][] = new int[3][3]; 行长度:array.length 列长度:array[i].length

    public static void main(String[] args) {
        //创建原始数组
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        //输出原始数组
        for(int[] row:chessArr1){
            for(int data:row){
                System.out.printf("%d\t",data); //格式化输出使用printf
            }
            System.out.println();
        }
        //转为稀疏数组
        //1.统计原数组中的有效数据个数
        int sum=0;
        for(int[] row:chessArr1){
            for(int data:row){
                if(data>0){
                    sum++;
                }
            }
        }
        System.out.println("原始数组中有效数据个数："+sum);
        //创建对应的稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //装入有效数据
        int index = 0;
        for(int i=0;i<chessArr1.length;i++){
            for (int j=0;j<chessArr1[i].length;j++){
                if(chessArr1[i][j]>0){
                    index++;
                    sparseArr[index][0] = i;
                    sparseArr[index][1] = j;
                    sparseArr[index][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组
        System.out.println("生成对应的稀疏数组~~~~~");
        for(int[] row:sparseArr){
            for(int data:row){
                System.out.printf("%d\t",data); //格式化输出使用printf
            }
            System.out.println();
        }
        //把稀疏数组输出至文件
        writeSparseArray(sparseArr);
        //从文件中读出稀疏数组
        int[][] sparseArr2 = readSparseArray();

        //稀疏数组恢复成数组
        int[][] chessArr2 = new int[sparseArr2[0][0]][sparseArr2[0][1]];
        for (int i=1;i<sparseArr2.length;i++){
            chessArr2[sparseArr2[i][0]][sparseArr2[i][1]]=sparseArr2[i][2];
        }

        System.out.println("由稀疏数组重新生成普通数组~~~~~");
        for(int[] row:chessArr2){
            for(int data:row){
                System.out.printf("%d\t",data); //格式化输出使用printf
            }
            System.out.println();
        }
    }
    public static void writeSparseArray(int[][] sparseArr){
        try(FileOutputStream fos = new FileOutputStream("temp.txt");
            PrintStream ps = new PrintStream(fos)
        ){
            //二维数组按行输出
            for(int[] row:sparseArr){
                String tempStr=""; //之前这里写String tempStr = null;那下面拼接的字符串会出现null
                for(int data:row){
                    tempStr=tempStr+String.valueOf(data)+" "; //拼接成“x1 x2 x3”字符串形式进行输出
                }
                ps.println(tempStr);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static int[][] readSparseArray(){
        int sparseArr[][]=null;
        try(FileReader fr = new FileReader("temp.txt");
            BufferedReader br = new BufferedReader(fr)){
            //思路：按行读取文件内容，读取的行内容是字符串，字符串lit得到字符串数组，字符串数组->int数组，int数组赋值给二维数组做行
            //1、按行读取文件内容，读取的内容放入ArrayList
            String tmpStr = null;
            ArrayList<String> arrayList = new ArrayList<>();
            while ((tmpStr=br.readLine())!=null){
                arrayList.add(tmpStr);
            }
            //创建一个二维数组，该数组的行数和ArrayList.size()相同
            sparseArr = new int[arrayList.size()][];  //注意创建的数组没有固定列数
            //将ArrayList的每一项转换为int[]数组，把该数组赋给上面二维数组的行
            for(int i=0;i<arrayList.size();i++){
                String tmp = arrayList.get(i);
                String[] strings = tmp.split(" ");
                int[] row = new int[strings.length];
                for(int j=0;j<strings.length;j++){
                    row[j]=Integer.parseInt(strings[j]);
                }
                sparseArr[i]=row;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("读取文件转换为数组~~~~");
        for(int[] row:sparseArr){
            for(int data:row){
                System.out.printf("%d\t",data); //格式化输出使用printf
            }
            System.out.println();
        }
        System.out.println("-----------结束-------------");
        return sparseArr;
    }
}




