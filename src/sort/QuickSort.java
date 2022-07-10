package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-9,78,0,23,-567,70,-1,900,4561};
//        quickSort(arr,0,arr.length-1);
//        System.out.println("arr="+ Arrays.toString(arr));
        //快速排序算法 ：时间测试
        int[] arr = new int[80000];
        for(int i=0;i<80000;i++){
            arr[i] = (int)(Math.random() * 8000000); //生成一个[0,8000000)数
        }

        Date date1 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = format.format(date1);
        System.out.println("排序前的时间是=" + date1Str);

        quickSort(arr,0,arr.length - 1);

        Date date2 = new Date();
        String date2Str = format.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
    }

    public static void quickSort(int[] arr,int left,int right){
        int l = left; //左下标
        int r = right; //右下标
        //pivot 中轴
        int pivot = arr[(left + right) / 2];
        int temp = 0; //临时变量，作为交换时使用
        //while循环的目的是让：比pivot值小的放到左边，比pivot大的值放到右边
        while(l < r){
            //在pivot的左边一直找，找到大于等于pivot的值
            while(arr[l] < pivot){
                l += 1;
            }
            //在pivot的右边一直找，找到小于等于pivot的值
            while(arr[r] > pivot){
                r -= 1;
            }
            /*
              如果l >= r成立，说明pivot的左右两边的值已经变成：
              左边全部是小于pivot的值，而右边全部是大于等于pivot的值
             */

            if(l >= r){
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现这个arr[l] == pivot值，r 前移一下(r--)
            if(arr[l] == pivot){
                r -= 1;
            }
            //如果交换完后，发现这个arr[r] == pivot值，l 后移一下(l++)
            if(arr[r] == pivot){
                l += 1;
            }
        }

        //如果l == r，必须l++，r--，否则会出现栈溢出
        if(l == r){
            l += 1;
            r -= 1;
        }

        //向左递归
        if (left < r){
            quickSort(arr,left,r);
        }
        //向右递归
        if(right > l){
            quickSort(arr,l,right);
        }
    }
}
