package search;

import java.util.ArrayList;
import java.util.List;

//二分查找
public class BinarySearch {
    public static void main(String[] args) {
        //注意，使用二分查找的前提是要操作的数组必须有序
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1234};

        List<Integer> resIndexList = binarySearch2(arr, 1000);
        System.out.println("resIndexList=" + resIndexList);

    }

    //二分查找算法（递归版本）
    //注意，使用二分查找的前提是要操作的数组必须有序

    //这里的代码和视频里原版不一样！！！他视频里最后没有return，在我的IDE里会报错说没有返回类型！！
    //所以我被迫用void类型来改装了一个

    /**
     *
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal 要查找的值
     * 如果找到就输出下标，如果没有找到，就输出-1
     */
    public static void binarySearch(int[] arr,int left,int right,int findVal){
        // 当left > right 时，说明递归整个数组，但是没有找到
        if(left > right){
            System.out.println(-1);
            throw new RuntimeException("没有找到该值！");
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if(findVal > midVal){ //向右递归
            binarySearch(arr,mid + 1,right,findVal);
        } else if(findVal < midVal){//向左递归
            binarySearch(arr,left,mid - 1,findVal);
        } else{// findVal == arr[mid]  找到了
            System.out.println(mid);
        }
    }


    /**
     *
     * @param arr 数组
     * @param findVal 要查找的值
     * @return 如果找到就输出整个ArrayList，如果没有找到，就输出空的ArrayList
     */
    //二分查找算法（循环版本)
    //注意，使用二分查找的前提是要操作的数组必须有序
    //注意，这段代码也和视频中不一样，因为我是用while循环来做的，而不是递归

    /*
     {1, 8, 10, 89, 1000,1000,1000,1234}
    优化：当一个有序数组中有多个相同数值时，如何将所有的数值都查找到，比如这里的1000
    优化思路分析：
    1. 在找到 mid 值后，不要马上返回
    2. 向 mid 索引值的左边扫描，将所有值等于 1000 的元素的下标，加入到集合ArrayList
    3. 向 mid 索引值的右边扫描，将所有值等于 1000 的元素的下标，加入到集合ArrayList
     */
    public static List<Integer> binarySearch2(int[] arr, int findVal){
        List<Integer> resultIndexList = new ArrayList<>();
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        while(left <= right){
            mid = (left + right) / 2;
            if(findVal == arr[mid]){
                //findVal == arr[mid]  找到了
                //向 mid 索引值的左边扫描，将所有值等于 要查找的那个元素 的下标，加入到集合ArrayList
                int temp = mid - 1;
                while(true){
                    if(temp < 0){//退出
                        break;
                    }
                    //否则，如果temp的值等于findVal, 就将 temp 放入到resIndexList
                    if(arr[temp] == findVal){
                        resultIndexList.add(temp);
                    }
                    temp--; //temp左移
                }
                resultIndexList.add(mid);

                //向 mid 索引值的右边扫描，将所有值等于 要查找的那个元素 的下标，加入到集合ArrayList
                temp = mid + 1;
                while(true){
                    if(temp > arr.length - 1){//退出
                        break;
                    }
                    //否则，如果temp的值等于findVal, 就将 temp 放入到resIndexList
                    if(arr[temp] == findVal){
                        resultIndexList.add(temp);
                    }
                    temp++; //temp右移
                }
                break;  //这个break很重要！！！一定不要忘记！！不然就是死循环！！ 找到了一定要退出！！不然就一直搁那添加同一个值到ArrayList!!!
            }
            else if(findVal < arr[mid]){
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }
        return resultIndexList;
    }
}
