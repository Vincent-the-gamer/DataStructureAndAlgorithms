package recursion;

//递归学习
public class RecursionTest {
    public static void main(String[] args) {
        //通过打印问题，回顾递归调用机制
//        test(4);
        int res = factorial(4);
        System.out.println("res=" + res);
    }

    //打印问题
    //递归过程：test(4)调用test(3),test(3)调用test(2)
    //test(2)不满足n > 2，则直接输出n=2，不再调用，然后开始回溯,输出n=3,n=4;

    //递归可以理解成先递后归，调用过程不进行操作，等到递归调用找到出口以后，开始回溯执行之前的操作
    public static void test(int n) {
        if (n > 2){
            test(n - 1);
        }
        System.out.println("n=" + n);
        //n = 2时，走else的话，递归就到出口了，不会回溯到test(3)了
//        else{
//            System.out.println("n=" + n);
//        }
    }

    //阶乘问题
    //遇到return，方法就会返回给上一层递归调用的那个地方
    public static int factorial(int n){
        if(n == 1){
            return 1;
        }
        else return factorial(n-1) * n;
    }
}
