package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//插入排序
public class InsertSort {
    public static void main(String[] args) {
        //插入排序算法：时间测试
        int[] arr = new int[80000];
        for(int i=0;i<80000;i++){
            arr[i] = (int)(Math.random() * 8000000); //生成一个[0,8000000)数
        }

        Date date1 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = format.format(date1);
        System.out.println("排序前的时间是=" + date1Str);

        insertSort(arr);  //调用插入排序算法

        Date date2 = new Date();
        String date2Str = format.format(date2);
        System.out.println("排序后的时间是=" + date2Str);

    }

    //插入排序
    public static void insertSort(int[] arr){
        //定义待插入的数
        int insertVal = 0;
        //要插入到的索引
        int insertIndex = 0;
        //使用for循环来把代码简化
        for(int i = 1; i < arr.length; i++){
            insertVal = arr[i];
            insertIndex = i - 1; //即arr[1]的前面这个数的下标
            //给insertVal 找到插入的位置
        /*
        说明：
        1. insertIndex >= 0 保证在给insertVal找插入位置时不越界
        2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
        3. 就需要将 arr[insertIndex] 后移
         */
            while(insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex]; //数字后移，覆盖掉arr[insertIndex + 1]的值，所以在这里之前，记得保存原来未被覆盖的值
                insertIndex--;
            }
            //当推出while循环时，说明插入的位置找到：insertIndex + 1
            //这里是insertIndex + 1的原因是，程序判断出口时总是会多判断一次
            //当(insertIndex == 0，还要走完这次循环。然后 insertIndex 变成了 -1，这时候再判断才会退出循环

            //优化： 判断是否需要赋值，如果上面while没有执行，那么就不再赋值，直接把待插入的数算进有序表里，也就是说，它本来就在该在的位置上。
            if(insertIndex + 1 != i){ //
                arr[insertIndex + 1] = insertVal;
            }

        }



//        //使用逐步推导的方式来演示，便于理解
//        //第1轮
//        // 认为101是有序列表，后三个数是无序列表： {101, 34, 119, 1}  => {34,101,119,1}
//
//        //定义待插入的数
//        int insertVal = arr[1];
//        //要插入到的索引
//        int insertIndex = 1 - 1; //即arr[1]的前面这个数的下标
//
//        //给insertVal 找到插入的位置
//        /*
//        说明：
//        1. insertIndex >= 0 保证在给insertVal找插入位置时不越界
//        2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
//        3. 就需要将 arr[insertIndex] 后移
//         */
//        while(insertIndex >= 0 && insertVal < arr[insertIndex]){
//            arr[insertIndex + 1] = arr[insertIndex]; //数字后移，覆盖掉arr[insertIndex + 1]的值，所以在这里之前，记得保存原来未被覆盖的值
//            insertIndex--;
//        }
//        //当推出while循环时，说明插入的位置找到：insertIndex + 1
//        //这里是insertIndex + 1的原因是，程序判断出口时总是会多判断一次
//        //当(insertIndex == 0，还要走完这次循环。然后 insertIndex 变成了 -1，这时候再判断才会退出循环
//        arr[insertIndex + 1] = insertVal;
//
//        System.out.println("第1轮插入");
//        System.out.println(Arrays.toString(arr));
//
//        //第2轮
//        insertVal = arr[2];
//        //要插入到的索引
//        insertIndex = 2 - 1; //即arr[1]的前面这个数的下标
//
//        //给insertVal 找到插入的位置
//        while(insertIndex >= 0 && insertVal < arr[insertIndex]){
//            arr[insertIndex + 1] = arr[insertIndex]; //数字后移，覆盖掉arr[insertIndex + 1]的值，所以在这里之前，记得保存原来未被覆盖的值
//            insertIndex--;
//        }
//        arr[insertIndex + 1] = insertVal;
//
//        System.out.println("第2轮插入");
//        System.out.println(Arrays.toString(arr));
//
//        //第3轮
//        insertVal = arr[3];
//        //要插入到的索引
//        insertIndex = 3 - 1; //即arr[1]的前面这个数的下标
//
//        //给insertVal 找到插入的位置
//        while(insertIndex >= 0 && insertVal < arr[insertIndex]){
//            arr[insertIndex + 1] = arr[insertIndex]; //数字后移，覆盖掉arr[insertIndex + 1]的值，所以在这里之前，记得保存原来未被覆盖的值
//            insertIndex--;
//        }
//        arr[insertIndex + 1] = insertVal;
//
//        System.out.println("第3轮插入");
//        System.out.println(Arrays.toString(arr));
//

    }
}
