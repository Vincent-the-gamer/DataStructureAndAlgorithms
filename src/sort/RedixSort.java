package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//基数排序
public class RedixSort {
    public static void main(String[] args) {
//        int[] arr = {53, 3, 542, 748, 14, 214};
//        redixSort(arr);

        //基数排序算法 ：时间测试
        int[] arr = new int[80000];
        for(int i=0;i<80000;i++){
            arr[i] = (int)(Math.random() * 8000000); //生成一个[0,8000000)数
        }

        Date date1 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = format.format(date1);
        System.out.println("排序前的时间是=" + date1Str);

        redixSort(arr);

        Date date2 = new Date();
        String date2Str = format.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
    }

    //基数排序
    public static void redixSort(int[] arr) {
        //根据推导过程，得到最终基数排序的代码
        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        /*
        说明：
        1. 二维数组包含10个一维数组，表示每一位的范围0~9，所以需要10个
        2. 为了防止在放入数的时候，数据溢出，则每一个一维数组（桶），大小定为arr.length
        3. 很明确，基数排序是使用空间换时间的经典算法
         */
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中实际存放了多少个数据，我们定义一个一维数组来记录各个桶每次放入的数据个数
        //可以这样理解：
        // bucketElementCounts[0] 记录的就是 bucket[0] 桶中 已经放入的数据的个数
        int[] bucketElementCounts = new int[10];

        //1. 得到数组中最大的数的位数
        int max = arr[0]; //假设第一个数就是最大的数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength = (max + "").length();
        //数据要从桶中放入数组时数组的下标
        int index = 0;

        //这里我们使用循环将代码处理一下
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //针对每个元素对应的位进行排序，第一次是个位，第二次是十位，第三次是百位.....
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的对应的位的数
                int digitOfElement = (arr[j] / n) % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序（一维数组的下标）依次取出数据，放入到原来的数组
            index = 0;
            //遍历每一个桶，并将桶中的数据放入到原数组中
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，我们才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    //循环第k个桶（第k个一维数组），放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr中
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                //第i+1轮处理后，需要将每个bucketElementCounts[k] = 0 !!!
                bucketElementCounts[k] = 0;
            }
//            System.out.println("第"+ (i+1) +"轮 arr =" + Arrays.toString(arr));
        }


//        //第1轮排序（针对每个元素的个位进行排序处理）
//        for(int j = 0;j < arr.length; j++){
//            //取出每个元素的个位的数
//            int digitOfElement = arr[j] % 10;
//            //放入到对应的桶中
//            bucket[digitOfElement][ bucketElementCounts[digitOfElement] ] = arr[j];
//            bucketElementCounts[digitOfElement]++;
//        }
//        //按照这个桶的顺序（一维数组的下标）依次取出数据，放入到原来的数组
//        int index = 0;
//        //遍历每一个桶，并将桶中的数据放入到原数组中
//        for(int k = 0; k < bucketElementCounts.length; k++){
//            //如果桶中有数据，我们才放入到原数组
//            if(bucketElementCounts[k] != 0){
//                //循环第k个桶（第k个一维数组），放入
//                for(int l = 0; l < bucketElementCounts[k]; l++){
//                    //取出元素放入到arr中
//                    arr[index] = bucket[k][l];
//                    index++;
//                }
//            }
//            //第一轮处理后，需要将每个bucketElementCounts[k] = 0 !!!
//            bucketElementCounts[k] = 0;
//        }
//        System.out.println("第一轮，对个位的排序处理 arr =" + Arrays.toString(arr));
//
//        //第2轮排序（针对每个元素的十位进行排序处理）
//        for(int j = 0;j < arr.length; j++){
//            //取出每个元素的十位的数
//            int digitOfElement = (arr[j] / 10) % 10; //748 / 10 => 74 % 10 = 4
//            //放入到对应的桶中
//            bucket[digitOfElement][ bucketElementCounts[digitOfElement] ] = arr[j];
//            bucketElementCounts[digitOfElement]++;
//        }
//        //按照这个桶的顺序（一维数组的下标）依次取出数据，放入到原来的数组
//        index = 0;
//        //遍历每一个桶，并将桶中的数据放入到原数组中
//        for(int k = 0; k < bucketElementCounts.length; k++){
//            //如果桶中有数据，我们才放入到原数组
//            if(bucketElementCounts[k] != 0){
//                //循环第k个桶（第k个一维数组），放入
//                for(int l = 0; l < bucketElementCounts[k]; l++){
//                    //取出元素放入到arr中
//                    arr[index] = bucket[k][l];
//                    index++;
//                }
//            }
//            //第二轮处理后，需要将每个bucketElementCounts[k] = 0 !!!
//            bucketElementCounts[k] = 0;
//        }
//        System.out.println("第二轮，对个位的排序处理 arr =" + Arrays.toString(arr));
//
//        //第3轮排序（针对每个元素的百位进行排序处理）
//        for(int j = 0;j < arr.length; j++){
//            //取出每个元素的百位的数
//            int digitOfElement = (arr[j] / 100) % 10; //748 / 100 =>  7 % 10 = 7
//            //放入到对应的桶中
//            bucket[digitOfElement][ bucketElementCounts[digitOfElement] ] = arr[j];
//            bucketElementCounts[digitOfElement]++;
//        }
//        //按照这个桶的顺序（一维数组的下标）依次取出数据，放入到原来的数组
//        index = 0;
//        //遍历每一个桶，并将桶中的数据放入到原数组中
//        for(int k = 0; k < bucketElementCounts.length; k++){
//            //如果桶中有数据，我们才放入到原数组
//            if(bucketElementCounts[k] != 0){
//                //循环第k个桶（第k个一维数组），放入
//                for(int l = 0; l < bucketElementCounts[k]; l++){
//                    //取出元素放入到arr中
//                    arr[index] = bucket[k][l];
//                    index++;
//                }
//            }
//            //第三轮处理后，需要将每个bucketElementCounts[k] = 0 !!!
//            bucketElementCounts[k] = 0;
//        }
//        System.out.println("第三轮，对个位的排序处理 arr =" + Arrays.toString(arr));

    }
}
