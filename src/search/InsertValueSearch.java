package search;


//插值查找算法
public class InsertValueSearch {
    public static void main(String[] args) {
//        int[] arr = new int[100];
//        for(int i=0; i<100; i++){
//            arr[i] = i + 1;
//        }
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1234};
        int index = insertValueSearch(arr,1000);
        System.out.println("index = " + index);
    }

    //编写插值查找算法
    //这段代码也和视频中不同，是用while循环做的，因为插值查找就是二分查找把mid的算法改了一下，其他不动
    /**
     *
     * @param arr 待查找的数组
     * @param findVal 要查找的值
     * @return 如果找到，就返回对应的下标，如果没有找到，就返回-1
     */
    public static int insertValueSearch(int[] arr,int findVal){
        int left = 0; //左边索引
        int right = arr.length - 1; //右边索引
        int mid; //要查找的中间索引
        int midVal; //中间索引的值

        int result = -1; //结果，如果没找到默认等于-1
        while(left <= right){
            if(findVal > arr[arr.length - 1] || findVal < arr[0]){
                //如果要查找的值比数组最小的还小，或者比最大的还大，肯定就找不到了
                return result;
            }
            //自适应mid计算法
            mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
            midVal = arr[mid];
            if(findVal > midVal){
                left = mid + 1;
            }
            else if(findVal < midVal){
                right = mid - 1;
            }
            else{
                result = mid;
                break;
            }
        }
        return result;
    }
}
