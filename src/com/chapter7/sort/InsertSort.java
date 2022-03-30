package com.chapter7.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101,34,119,-1,90,123};
        //insertSortByMove(arr);
        insertSortByExchange(arr);
    }
    //插入排序：移动法
    public static void insertSortByMove(int[] arr){

        for (int i=1;i<arr.length;i++){
            //完成第一个内层循环
            int insertVal = arr[i];
            int insertIndex = i-1;
            while (insertIndex>=0&&insertVal<arr[insertIndex]){
                arr[insertIndex+1]=arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex+1]=insertVal;

            System.out.println(Arrays.toString(arr));
        }
    }
    //插入排序：交换法
    //受希尔排序中的交换法启示
    public static void insertSortByExchange(int[] arr){

        for (int i=1;i<arr.length;i++){
//            int insertVal = arr[i];
//            int insertIndex = i-1;
            for (int j = i-1;j>=0;j--){
                if (arr[j+1]<arr[j]){
                    int temp=arr[j+1];
                    arr[j+1]=arr[j];
                    arr[j]=temp;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
        //完成第一个内层循环
        //第一个内循环是把arr[0]当做有序数组，arr[1]作为插入数
        //往一个有序数组中插入数值，既可以使用上面的移动法，可以使用下面的交换法
        //j = 1-1是获得有序数组的边界
        for(int j = 1-1;j>=0;j--){
            if(arr[j+1]<arr[j]){
                int temp=arr[j+1];
                arr[j+1]=arr[j];
                arr[j]=temp;
            }
        }
    }
}
