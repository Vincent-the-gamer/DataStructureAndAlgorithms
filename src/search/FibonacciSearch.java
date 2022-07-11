package search;

import java.util.Arrays;

//斐波那契查找
public class FibonacciSearch {

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println("index = " + fibSearch(arr,89));
    }

    //因为mid = low + F(k-1) -1 需要用到斐波那契数列，所以需要先获取到一个斐波那契数列
    //用非递归的方式得到一个长度为len的斐波那契数列
    public static int[] fib(int len){
        int[] f = new int[len];
        f[0] = 1;
        f[1] = 1;
        for(int i = 2; i < len; i++){
            f[i] = f[i-1] + f[i-2];
        }
        return f;
    }

    //斐波那契查找算法（非递归）
    /**
     *
     * @param a  数组
     * @param key 我们需要查找的关键码(值)
     * @return 返回对应的下标，如果没有，返回-1
     */
    public static int fibSearch(int[] a,int key){
        int low = 0;
        int high = a.length - 1;
        int k = 0; //表示斐波那契分割数值的下标 也就是 F(k-1) 中的 k
        int mid = 0; //存放mid值
        int[] f = fib(a.length); //获取到斐波那契数列

        //获取到斐波那契分割数值的下标
        while(high > f[k] - 1){
            k++;
        }
        // 因为 f[k] 值 可能大于 a 的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向a
        // 不足的部分会使用0填充
        int[] temp = Arrays.copyOf(a, f[k]);
        // 实际上需要使用 a 数组的最后的数填充 temp
        for(int i = high + 1; i < temp.length; i++){
            temp[i] = a[high];
        }

        //使用while来循环处理，找到我们的数key
        while(low <= key){ //只要满足就一直找
            //如果下标越界, 表示没找到，直接break
            if(k - 1 < 0 || k - 1 > a.length){
                break;
            }
            mid = low + f[k-1] - 1;
            if(key < temp[mid]){ //我们应该继续向数组的前面（左边）查找
                high = mid - 1;
                //为什么是k--?
                //说明：
                //1. 全部元素 = 前面的元素 + 后面的元素
                //2. f[k] = f[k-1] + f[k-2]
                //因为 前面有 f[k-1]个元素，所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                //即在 f[k-1]的前面继续查找， 下次循环mid = low + f[k-1-1]-1
                k--;
            }
            else if( key > temp[mid] ){ //我们应该继续向数组的后面（右边）查找
                low = mid + 1;
                /*
                为什么是k -= 2?
                说明：
                1. 全部元素 = 前面的元素 + 后面的元素
                2. f[k] = f[k-1] + f[k-2]
                3. 因为后面有f[k-2]个元素，所以可以继续拆分 f[k-2] = f[k-3] + f[k-4]
                4. 即在f[k-2] 的后面继续查找, 即下次循环mid = low + f[k - 1 - 2] - 1;
                 */
                k -= 2;
            }
            else{ //找到
                //需要确定返回的是哪个下标
                if(mid <= high){
                   return mid;
                }
                else if(mid >= low){
                   return high;
                }
            }
        }
        return -1;
    }
}
