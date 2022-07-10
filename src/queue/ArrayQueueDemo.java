package queue;

import java.util.Scanner;

//用数组来模拟队列（未优化）
public class ArrayQueueDemo {
    public static void main(String[] args){
        //测试
        //创建一个长度为3的队列
        ArrayQueue aq = new ArrayQueue(3);
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
                    aq.showQueue();
                    break;

                case 'a': //添加数据到队列
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    aq.addQueue(value);
                    break;

                case 'g': //取出数据
                    try{
                        int res = aq.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                        break;
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                        break;
                    }

                case 'h': //查看队列头的数据
                    try{
                        int res = aq.headQueue();
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

    //使用数组模拟队列 --- 编写一个ArrayQueue类
    //内部类
    static class ArrayQueue{
        private int maxSize; //表示数组的最大容量
        private int front; //队列头, 初始值为-1
        private int rear; //队列尾, 初始值为-1
        private int[] arr; //用于存放数据，模拟队列

        //创建队列的构造函数
        public ArrayQueue(int arrMaxSize){
            this.maxSize = arrMaxSize;
            this.arr = new int[maxSize];
            this.front = -1;  //指向队列头部，front是指向队列头的前一个位置
            this.rear = -1;   //指向队列尾，指向队列最后一个数据
        }

        //判断队列是否满: 尾指针指向队列长度最大值
        public boolean isFull(){
            return rear == maxSize - 1;
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
                rear++; //让rear后移
                arr[rear] = n;
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
                front++; //取出数据后，front后移
                return arr[front];
            }
        }

        //显示队列的所有数据
        public void showQueue(){
            //遍历
            if(isEmpty()){
                System.out.println("队列空的，你玩我呢！");
                return;
            }
            else{
                for(int i=0; i < arr.length; i++){
                    System.out.printf("arr[%d]=%d\n" , i , arr[i]);
                }
            }
        }

        //显示队列的头数据   注意不是取出数据
        public int headQueue(){
            //判断
            if(isEmpty()){
                System.out.println("队列空的，你玩我呢！");
                throw new RuntimeException("队列空的，你玩我呢！");
            }
            else{
                return arr[ front+1 ];
            }
        }
    }
}
