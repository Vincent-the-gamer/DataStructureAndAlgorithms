package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//选择排序
public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {101,34,119,1,-1,90,123};

        //选择排序算法：时间测试
        int[] arr = new int[80000];
        for(int i=0;i<80000;i++){
            arr[i] = (int)(Math.random() * 8000000); //生成一个[0,8000000)数
        }

        Date date1 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = format.format(date1);
        System.out.println("排序前的时间是=" + date1Str);

        selectSort(arr);

        Date date2 = new Date();
        String date2Str = format.format(date2);
        System.out.println("排序后的时间是=" + date2Str);

    }

    //选择排序,时间复杂度O(n^2)
    public static void selectSort(int[] arr){

        //在推导的过程中，我们发现了规律，因此，可以使用for来解决
        for(int i=0;i< arr.length - 1; i++){
            int minIndex = i; //最小值的索引
            int min = arr[minIndex]; //假定最小值是索引为i的那个值, 这个变量用来保存最小值
            for(int j = i + 1;j < arr.length ; j++){
                if(min > arr[j]){ //说明假定的最小值，并不是最小
                    min = arr[j];  //重置最小值
                    minIndex = j;   //重置最小值索引
                }
            }
            //交换：将最小值放在arr[i]
            if(minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }


        //使用逐步推导的方式来演示
        //第一轮
        /*
        原始的数组： 101, 34, 119, 1
        第一轮排序： 1, 34, 119, 101
         */

        /*
        int minIndex = 0; //最小值的索引
        int min = arr[minIndex]; //假定最小值是索引为0的那个值, 这个变量用来保存最小值
        for(int j = 0 + 1;j < arr.length ; j++){
            if(min > arr[j]){ //说明假定的最小值，并不是最小
                min = arr[j];  //重置最小值
                minIndex = j;   //重置最小值索引
            }
        }
        //交换：将最小值放在arr[0]
        if(minIndex != 0){
            arr[minIndex] = arr[0];
            arr[0] = min;
        }

        System.out.println("第1轮后");
        System.out.println(Arrays.toString(arr));  //1, 34, 119, 101


        //第二轮: 没有发生交换
        minIndex = 1;
        min = arr[1];
        for(int j = 1 + 1;j < arr.length ; j++){
            if(min > arr[j]){
                min = arr[j];  //重置最小值
                minIndex = j;   //重置最小值索引
            }
        }
        //交换：将最小值放在arr[0]
        if(minIndex != 1){
            arr[minIndex] = arr[1];
            arr[1] = min;
        }

        System.out.println("第2轮后");
        System.out.println(Arrays.toString(arr));  //1, 34, 119, 101

        //第三轮: 没有发生交换
        minIndex = 2;
        min = arr[2];
        for(int j = 2 + 1;j < arr.length ; j++){
            if(min > arr[j]){
                min = arr[j];  //重置最小值
                minIndex = j;   //重置最小值索引
            }
        }
        //交换：将最小值放在arr[0]
        if(minIndex != 21){
            arr[minIndex] = arr[2];
            arr[2] = min;
        }

        System.out.println("第3轮后");
        System.out.println(Arrays.toString(arr));  //1, 34, 101, 119
        */

    }
}
