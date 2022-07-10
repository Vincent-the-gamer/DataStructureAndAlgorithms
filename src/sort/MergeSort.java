package sort;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//归并排序
public class MergeSort {
    public static void main(String[] args) {
//        int[] arr = {8,2,5,7,1,3,6,4};  //merge的次数是arr.length - 1次
//        int[] temp = new int[arr.length];
//        mergeSort(arr,0,arr.length - 1,temp);
//        System.out.println("归并排序后: " + Arrays.toString(arr));

        //归并排序算法：时间测试
        int[] arr = new int[80000];
        int[] temp = new int[arr.length];
        for(int i=0;i<80000;i++){
            arr[i] = (int)(Math.random() * 8000000); //生成一个[0,8000000)数
        }

        Date date1 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = format.format(date1);
        System.out.println("排序前的时间是=" + date1Str);

        mergeSort(arr,0,arr.length - 1, temp);

        Date date2 = new Date();
        String date2Str = format.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
    }

    //归并排序 （分解 + 合并 + 排序）
    public static void mergeSort(int[] arr,int left, int right, int[] temp){
        if(left < right){
            int mid = (left + right) / 2; //中间索引
            //向左递归进行分解
            mergeSort(arr,left,mid,temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr,left,mid,right,temp);
        }
    }


    //合并 + 排序的方法
    /**
     *
     * @param arr 待排序的原始数组
     * @param left 左边有序序列的初始边界索引 0
     * @param mid  中间索引 (left + right) / 2
     * @param right  右边有序序列的初始边界索引 arr.length - 1
     * @param temp  做中转的临时数组
     */
    public static void merge(int[] arr,int left,int mid,int right, int[] temp){
        int i = left; // 初始化i, 左边有序序列的初始边界索引
        int j = mid + 1; //初始化j，右边有序序列的初始边界索引
        int t = 0; // 指向temp数组的当前索引

        /*
        (1)
          先把左右两边(有序)的数据按按照规则填充到temp数组，直到左右两边的有序序列
          有一边处理完毕为止
          */
        while(i <= mid && j <= right){
            //如果左边有序序列的当前元素，小于等于右边有序序列的当前元素
            //即：将左边的当前元素，拷贝到temp数组
            //然后 t 后移， i 后移
            if(arr[i] <= arr[j]){
                temp[t] = arr[i];
                t++;
                i++;
            } else{    //如果右边有序序列的当前元素，小于左边有序序列的当前元素
                //将右边有序序列的当前元素，填充到temp数组
                //然后 t 后移， j 后移
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        /*
        (2)
          把有剩余数据的一边的数据依次全部填充到temp去
          */
        while(i <= mid){ //左边的有序序列还有剩余的元素，就 按照顺序 全部填充到temp
            temp[t] = arr[i];
            t++;
            i++;
        }
        while(j <= right){ //右边的有序序列还有剩余的元素，就 按照顺序 全部填充到temp
            temp[t] = arr[j];
            t++;
            j++;
        }
        /*
        (3)
          将temp数组的元素拷贝到arr
          注意，并不是每次都拷贝所有的元素
         */
        t = 0;
        int tempLeft = left;
        //第一次合并：templeft = 0, right = 1
        //最后一次合并: templeft = 0, right = length - 1
        while(tempLeft <= right){
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
