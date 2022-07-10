package queue;

import java.util.Scanner;

//用数组来模拟环形队列（已优化)
public class CircleArrayQueueDemo {
    public static void main(String[] args){
        //测试
        System.out.println("测试数组模拟环形队列的案例");
        //创建一个长度为3的队列
        CircleArrayQueue caq = new CircleArrayQueue(4); //这里设置的4，器队列的有效数据最大是3
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while(loop){
            System.out.println("s(show)： 显示队列");
            System.out.println("e(exit)： 退出程序");
            System.out.println("a(add)： 添加数据到队列");
            System.out.println("g(get)： 从队列去除数据");
            System.out.println("h(head)： 查看队列头的数据");
            key = scanner.next().charAt(0); //接收一个字符
            switch (key){
                case 's':  //显示队列
                    caq.showQueue();
                    break;

                case 'a': //添加数据到队列
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    caq.addQueue(value);
                    break;

                case 'g': //取出数据
                    try{
                        int res = caq.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                        break;
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                        break;
                    }

                case 'h': //查看队列头的数据
                    try{
                        int res = caq.headQueue();
                        System.out.printf("队列头的数据是：%d\n", res);
                        break;

                    }catch (Exception e){
                        System.out.println(e.getMessage());
                        break;
                    }
                case 'e':  //退出程序
                    scanner.close();
                    loop = false;
                    break;

                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }

    static class CircleArrayQueue{
        private int maxSize; //表示数组的最大容量

        private int front; //队列头
        // 对 front 变量的含义做一个调整： front 指向队列的第一个元素  （原来是指向第一个元素的前一个位置）
        // 也就是说arr[front] ，就是队列的第一个元素
        // front的初始值 = 0

        private int rear; //队列尾
        // 对 rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置，因为希望空出一个空间作为约定
        // rear的初始值 = 0

        private int[] arr; //用于存放数据，模拟队列

        public CircleArrayQueue(int arrMaxSize){
            this.maxSize = arrMaxSize;
            this.arr = new int[maxSize];
            //可以不给 front 和 rear 初始化赋值，因为它们初始值是0
        }

        //判断队列是否满:  尾指针+1 的结果对数组长度取模等于头指针的值，则队列满
        public boolean isFull(){
            return (rear + 1) % maxSize == front;
        }

        //判断队列是否空： 头指针和尾指针相等
        public boolean isEmpty(){
            return rear == front;
        }

        //添加数据到队列
        public void addQueue(int n){
            //判断队列是否满
            if(isFull()){
                System.out.println("队列已经满了，别把我当骡子！");
                return;
            }
            else{
               //直接将数据加入
                arr[rear] = n;
                //将rear后移，这里必须考虑取模
                rear = (rear + 1) % maxSize;
            }
        }

        //获取队列的数据，并且出队列
        public int getQueue(){
            //判断队列是否空
            if(isEmpty()){
                //通过抛出异常来处理
                throw new RuntimeException("队列空的，你玩我呢！");
            }
            else{
                // 这里需要注意： front是指向队列的第一个元素
                //1. 先把 front 对应的值保存到一个临时的变量中
                int value = arr[front];
                //2. 将 front 后移，考虑取模
                front = (front + 1) % maxSize;
                //3.将临时保存的变量返回
                return value;
            }
        }

        //求出当前队列有效数据的个数
        public int size(){
            return (rear + maxSize - front) % maxSize;
        }


        //显示队列的所有数据
        public void showQueue(){
            //遍历
            if(isEmpty()){
                System.out.println("队列空的，你玩我呢！");
                return;
            }
            else{
                //思路：从front开始遍历，遍历多少个元素
                for(int i = front; i < front + size(); i++){
                    System.out.printf("arr[%d]=%d\n" , i % maxSize , arr[i % maxSize]);
                }
            }
        }

        //显示队列的头数据   注意不是取出数据
        public int headQueue(){
            //判断
            if(isEmpty()){
                throw new RuntimeException("队列空的，你玩我呢！");
            }
            else{
                return arr[front];
            }
        }
    }
}
