package stack;

import java.util.Scanner;

//数组模拟栈
public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试一下ArrayStack 是否正确
        //先创建一个ArrayStack对象  表示栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true; //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while(loop){
            System.out.println("show：表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();

            switch (key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try{
                        int res = stack.pop();
                        System.out.println("出栈的数据是：" + res);
                        break;
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                        break;
                    }
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}

//定义一个 ArrayStack 来表示栈
class ArrayStack{
    private int maxSize; //栈的大小
    private int[] stack; //数组  模拟栈，数据放在该数组中
    private int top = -1; //top表示栈顶，初始化为-1

    //构造器
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        this.stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈 - push
    public void push(int value){
        //先判断站是否满
        if(isFull()){
            System.out.println("栈满了！");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈 - pop, 将栈顶的数据返回
    public int pop(){
        //先判断栈是否空
        if(isEmpty()){
            //抛出异常
            throw new RuntimeException("栈空了！没有数据！");
        }
        //拿到栈顶值
        int value = stack[top];
        //栈指针-1
        top--;
        return value;
    }

    //显示栈的情况（遍历栈），遍历时需要从栈顶开始显示
    public void list(){
        if(isEmpty()){
            System.out.println("栈空了，无法遍历！");
            return;
        }
        //需要从栈顶开始显示数据
        for(int i = top; i>=0 ;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}


