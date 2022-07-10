package recursion;

public class Queen8 {
    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array，保存皇后放置位置的结果，比如arr = {0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    static int count = 0; //一共有多少种解法
    static int judgeCount = 0; //一共判断了多少次冲突

    public static void main(String[] args) {
        //测试一把， 8皇后是否正确
        System.out.println("八皇后问题的所有解法：");
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.println("一共有" + count + "种解法！");
        System.out.println("一共判断了" + judgeCount + "次冲突！");
        //一共有92种解法！
        //一共判断了15720次冲突！
    }

    //编写一个方法，放置第n个皇后
    // 特别注意： check 是 每一次递归时，进入到check中都有  for(int i = 0; i < max; i++)
    private void check(int n){
        if(n == max){ //n = 8 ，其实8个皇后已经放好了
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        //遍历列
        for(int i = 0; i < max; i++){
            //先把当前这个皇后n，放到该行的第1列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if(judge(n)){ //不冲突
                //接着放第n+1个皇后，即开始递归
                check(n+1);
            }
            //如果冲突，就继续执行 array[n] = i; 即将第n个皇后放置在本行的 后移的一个位置
        }
    }

    //当我们放置第n个皇后时，就去检测该皇后是否和前面已经摆放的皇后冲突
    /**
     *
     * @param n 表示第n个皇后
     * @return 。
     */
    private boolean judge(int n){
        judgeCount++;
        for(int i=0; i<n; i++){
            // 说明：
            // 1. array[i] == array[n] 表示判断 第n个皇后是否和前面的n-1个皇后在同一列
            // 2. Math.abs(n-i) == Math.abs(array[n] - array[i])  判断第n个皇后是否和第i个皇后在同一斜线上
            // 可以把八皇后的棋盘画成平面直角坐标系来理解,这就相当于Math.abs(array[n] - array[i])除以Math.abs(n-i) == 1
            // 没错，就是斜率！斜率等于正负1时，说明两点是在一条斜线上
            if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }


    //写一个方法，可以将皇后摆放的位置输出
    private void print(){
        count++;
        for(int i=0; i< array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }


}
