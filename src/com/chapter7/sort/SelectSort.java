package com.chapter7.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101,34,119,-1,90,123};
        selectSort(arr);
    }
    public static void selectSort(int[] arr){

        for (int i=0;i<arr.length-1;i++){
            //先完成一个内层循环
            int minValue = arr[i];
            int minIndex = i;
            for (int j=i+1;j<arr.length-1;j++){
                if (arr[j]<minValue){
                    minIndex=j;
                    minValue=arr[j];
                }
            }
            arr[minIndex]=arr[i];
            arr[i]=minValue;

            System.out.println(Arrays.toString(arr));
        }
    }
}
