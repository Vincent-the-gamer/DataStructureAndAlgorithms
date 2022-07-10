package stack;

public class Calculator {
    public static void main(String[] args) {
        //完成表达式的运算
        String expression = "70-2*6+4"; //如何处理多位数的问题
        //创建两个栈，数栈，符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = ' ';
        int res = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch
        String keepNum = ""; //用于拼接 多位数
        //开始while循环的扫描expression
        while(true){
            //依次得到expression 的每一个字符
            ch = expression.charAt(index);
            //判断ch是什么，然后做相应的处理
            if(operStack.isOper(ch)){ //如果是运算符
                //判断当前的符号栈是否为空
                if(!operStack.isEmpty()){
                    // 如果当前的操作符的优先级小于或者等于栈中的操作符, 就需要从数栈中pop出两个数，
                    // 然后再从符号栈中pop出一个运算符，进行运算，将得到的结果，入数栈，然后将当前的操作符入符号栈。
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        //把运算的结果入数栈
                        numStack.push(res);
                        //然后将当前的操作符入符号栈
                        operStack.push(ch);
                    }
                    else{
                        // 如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                }
                else{
                    //如果为空，直接入符号栈
                    operStack.push(ch);
                }
            }
            else {
                //如果是数，则直接入数栈
                /*
                1. 当处理多位数时，不能发现是一个数就立即入栈，因为它可能是多位数
                2. 在处理数时，需要向expression的表达式的index后再看，如果是数就继续扫描，如果是符号再入栈
                3. 因此我们需要定义一个变量 字符串， 用于拼接。
                */
//                numStack.push(ch - 48); // '1' 和 数字 1 的 ascii码 相差48

                //处理多位数
                keepNum += ch;

                //如果ch已经是expression的最后一位，就直接入栈
                if(index == expression.length() - 1){
                    numStack.push( Integer.parseInt(keepNum) );
                }
                else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入数栈
                    //注意是看后一位，不是index++
                    if (operStack.isOper(expression.charAt(index + 1))) {
                        //如果后一位是运算符，则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //重要的！！！ 清空keepNum
                        keepNum = "";
                    }
                }
            }
            //让index + 1，并判断是否扫描到expression最后
            index++;
            if(index >= expression.length()){
                break;
            }
        }
        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运算
        while(true){
            //如果符号栈为空，则计算到最后的结果，数栈中只剩下一个数字时，也就是计算结果
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res); //数栈 入栈结果
        }
        //将数栈的最后数拿出来，就是结果
        System.out.printf("表达式%s = %d",expression,numStack.pop());
    }
}


//创建一个栈
//定义一个 ArrayStack2 来表示栈，需要扩展功能
class ArrayStack2 {
    private int maxSize; //栈的大小
    private int[] stack; //数组  模拟栈，数据放在该数组中
    private int top = -1; //top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈 - push
    public void push(int value) {
        //先判断站是否满
        if (isFull()) {
            System.out.println("栈满了！");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈 - pop, 将栈顶的数据返回
    public int pop() {
        //先判断栈是否空
        if (isEmpty()) {
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
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空了，无法遍历！");
            return;
        }
        //需要从栈顶开始显示数据
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
    //返回运算符的优先级，优先级是程序员来确定的，优先级使用数字表示
    //数字越大，则优先级就越高。
    public int priority(int oper){
       if(oper == '*' || oper == '/'){
           return 1;
       }
       else if (oper == '+' || oper == '-'){
           return 0;
       }
       else{
           return -1; //假定目前的表达式只有 + , - , * , /
       }
    }

    //判断是不是一个运算符
    //在Java中，int和char可以混用，int可以读取字符的ascii编码
    public boolean isOper(int val){
        return val == '+' || val == '-' || val == '*' | val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper){
        int res = 0; //res用于存放计算的结果
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1; //num2是后弹出来的，注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
    //增加一个方法，可以返回当前栈顶的值，但是不是真正的pop
    public int peek(){
        return stack[top];
    }
}