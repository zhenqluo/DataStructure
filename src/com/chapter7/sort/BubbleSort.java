package com.chapter7.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class BubbleSort {
    public static void main(String[] args) {
/*        int[] arr=new int[8];
        for (int i=0;i<8;i++){
            arr[i]=(int)(Math.random()*80000);//(int)Math.random()*80000 将得到0
        }*/
        int[] arr = {6,1,2,3,4,5};
        //System.out.println("排序前~~~");
        //System.out.println(Arrays.toString(arr));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sdate = new Date();
        String stime = simpleDateFormat.format(sdate);
        System.out.println(stime);


        bubbleSort(arr);

        Date edate = new Date();
        String etime = simpleDateFormat.format(sdate);
        System.out.println(etime);



        //System.out.println("排序后~~~~");
        //System.out.println(Arrays.toString(arr));
    }
    public static void bubbleSort(int[] arr){
        boolean flag = false;
        for (int i=0;i<arr.length-1;i++){
            flag = false;
            //先完成一趟排序
            for (int j=0;j<arr.length-1-i;j++){
                if (arr[j]>arr[j+1]){
                    flag = true;
                    int temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
            System.out.println(Arrays.toString(arr));
            if (!flag){
                break;
            }
        }
    }
}
