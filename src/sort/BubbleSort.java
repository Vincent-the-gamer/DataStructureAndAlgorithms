package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


public class BubbleSort {
    //将冒泡排序算法封装成一个函数
    //冒泡排序，时间复杂度O(n^2)
    public static void bubbleSort(int[] arr){
        int temp; //用于交换的临时变量
        boolean flag = false;  //标识变量，表示是否进行过交换
        //最终代码
        for(int i=0; i < arr.length - 1;i++){

            for(int j=0; j < arr.length - 1 - i; j++){
                //如果前面的数比后面的数大，则交换
                if(arr[j] > arr[j + 1]){
                    flag = true; //交换过
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j + 1] = temp;
                }
            }
            if(!flag){  //在一趟排序中，一次交换都没有发生
                break;
            } else{ //交换过
                flag = false; //重置flag，进行下一趟判断
            }
        }
    }
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, 20};
        //测试冒泡排序
        System.out.println("排序前的数组：");
        System.out.println(Arrays.toString(arr));

        bubbleSort(arr);

        System.out.println("排序后的数组：");
        System.out.println(Arrays.toString(arr));


        //测试冒泡排序的速度，给80000个数据，测试
//        int[] arr = new int[80000];
//        for(int i=0;i<80000;i++){
//            arr[i] = (int)(Math.random() * 8000000); //生成一个[0,8000000)数
//        }
//
//        Date date1 = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date1Str = format.format(date1);
//        System.out.println("排序前的时间是=" + date1Str);
//
//        bubbleSort(arr);
//
//        Date date2 = new Date();
//        String date2Str = format.format(date2);
//        System.out.println("排序后的时间是=" + date2Str);

        //为了容易理解，我们把冒泡排序的演变过程展示出来

//        //第一趟排序, 就是将最大的数排在最后
//        int temp; //用于交换的临时变量
//        for(int j=0; j < arr.length - 1; j++){
//            //如果前面的数比后面的数大，则交换
//            if(arr[j] > arr[j + 1]){
//                temp = arr[j];
//                arr[j] = arr[j+1];
//                arr[j + 1] = temp;
//            }
//        }
//
//        System.out.println("第一趟排序后的数组");
//        System.out.println(Arrays.toString(arr));
//
//        //第二趟排序，就是将第二大的数排在倒数第二位
//        for(int j=0; j < arr.length - 1 - 1; j++){
//            //如果前面的数比后面的数大，则交换
//            if(arr[j] > arr[j + 1]){
//                temp = arr[j];
//                arr[j] = arr[j+1];
//                arr[j + 1] = temp;
//            }
//        }
//
//        System.out.println("第二趟排序后的数组");
//        System.out.println(Arrays.toString(arr));
//
//        //第三趟排序，就是将第三大的数排在倒数第三位
//        for(int j=0; j < arr.length - 1 - 2; j++){
//            //如果前面的数比后面的数大，则交换
//            if(arr[j] > arr[j + 1]){
//                temp = arr[j];
//                arr[j] = arr[j+1];
//                arr[j + 1] = temp;
//            }
//        }
//
//        System.out.println("第三趟排序后的数组");
//        System.out.println(Arrays.toString(arr));
//
//        //第四趟排序，就是将第三大的数排在倒数第三位
//        for(int j=0; j < arr.length - 1 - 3; j++){
//            //如果前面的数比后面的数大，则交换
//            if(arr[j] > arr[j + 1]){
//                temp = arr[j];
//                arr[j] = arr[j+1];
//                arr[j + 1] = temp;
//            }
//        }
//
//        System.out.println("第四趟排序后的数组");
//        System.out.println(Arrays.toString(arr));
    }
}
