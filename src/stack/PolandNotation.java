package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//逆波兰表达式
public class PolandNotation {
    public static void main(String[] args) {
        //完成将一个中缀表达式转换为后缀表达式的功能
        //说明
        //1. 目标： 1+ ((2+3)*4) - 5  转成=>  1 2 3 + 4 * + 5 -
        //2. 因为直接对 字符串 进行操作不方便，因此先将1+ ((2+3)*4) - 5 转为中缀表达式对应的List
        //   即1+ ((2+3)*4) - 5 => ArrayList 【1,+,(,(,2,+,3,),*,4,),-,5]
        //3. 将得到的中缀表达式对应的List 转成 后缀表达式对应的List
        //   即ArrayList 【1,+,(,(,2,+,3,),*,4,),-,5] => ArrayList[1,2,3,+,4,*,+,5,-]
        String expression = "1+((2+3)*4)-5"; //注意表达式的符号要写对
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的list = " + infixExpressionList);
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的list = " + suffixExpressionList);
        //计算后缀表达式
        System.out.printf("expression=%d",calculate(suffixExpressionList));

 /*
        //先定义一个逆波兰表达式（后缀表达式）
        // (3+4)x5-6 => 3 4 + 5 x 6 -
        // 4 * 5 - 8 + 60 + 8 / 2 =>  4 5 * 8 - 60 + 8 2 / +
        //说明：为了方便，逆波兰表达式中的 数字 和 符号 使用空格隔开
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        //思路
        //1. 先将"3 4 + 5 x 6 -"  放到ArrayList中
        //2. 将 ArrayList 传递给一个方法，遍历 ArrayList 配合 栈 完成计算
        List<String> rpnlist = getListString(suffixExpression);
        System.out.println("逆波兰表达式是：" + rpnlist);

        int res = calculate(rpnlist);
        System.out.println("计算的结果是：" + res);
  */

    }
    //  ArrayList 【1,+,(,(,2,+,3,),*,4,),-,5] => ArrayList[1,2,3,+,4,*,+,5,-]
    //  方法：将得到的中缀表达式对应的List => 后缀表达式对应的List
    public static List<String> parseSuffixExpressionList(List<String> ls){
        //定义两个栈
        Stack<String> s1 = new Stack<String>(); //符号栈
        //说明：因为s2这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里我们不用Stack<String> 直接用List<String>
//        Stack<String> s2 = new Stack<String>(); //储存中间结果
        List<String> slist = new ArrayList<String>(); //储存中间结果的list

        //遍历ls
        for(String item: ls){
            //如果是一个数，加入到slist
            if(item.matches("\\d+")){
                slist.add(item);
            }
            else if(item.equals("(") || s1.isEmpty()){
                s1.push(item);
            }
            else if(item.equals(")")){
                //如果是右括号 " ) "，则依次弹出s1栈顶的运算符，并压入slist，直到遇到左括号为止，
                // 此时将这一对括号丢弃
                while(!s1.peek().equals("(")){
                    slist.add(s1.pop());
                }
                // 将 ( 弹出 s1 栈，消除小括号
                s1.pop();
            }
            else{
                //当item的优先级小于等于s1栈顶运算符的优先级，将s1栈顶的运算符弹出并加入到slist中，
                // 再次转到 (4-1) 与s1中新的栈顶运算符相比较
                // 问题： 我们缺少一个比较优先级高低的方法
                while(s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    slist.add(s1.pop());
                }
                //还需要将item压入栈中
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并加入slist
        while(s1.size() != 0){
            slist.add(s1.pop());
        }

        return slist; //注意：因为是存放到List，因此不需要逆序输出了，按顺序输出就是对应的后缀（逆波兰）表达式对应的list
    }

    //方法：将中缀表达式转成对应的List
    public static List<String> toInfixExpressionList(String s){
        //定义一个List，存放中缀表达式 对应的内容
        List<String> ls = new ArrayList<>();
        int i = 0; //这是一个指针，用于遍历 中缀表达式字符串
        String str; //对多位数的一个拼接工作
        char c; //每遍历到一个字符，就放入c
        do{
            //如果c是一个非数字，需要加入到ls  (比较ascii码)
            if( (c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57){
                ls.add("" + c);
                i++; //i需要后移
            }
            else { //如果是一个数，需要考虑多位数
                str = ""; //先将str 置成空串
                while(i < s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57){
                    str += c; //拼接
                    i++;
                }
                ls.add(str);
            }
        }while(i < s.length());
        return ls;
    }

    //将一个逆波兰表达式，依次将数据和运算符 放入到 ArrayList 中
    public static List<String> getListString(String suffixExpression){
        //将 suffixExpression 分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for(String s: split){
            list.add(s);
        }
        return list;
    }
    //完成对逆波兰表达式的运算
    /*
    从左至右扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，用运算符
    对它们做相应的计算（栈顶元素 和 次顶元素），并将结果入栈；重复上述过程直到表达式最右端，最后运算得出的值即为表达式的结果。
     */

    public static int calculate(List<String> ls){
        //创建一个栈, 只需要一个栈即可
        Stack<String> stack = new Stack<String>();
        //遍历 ls
        for(String item: ls){
            //这里使用正则表达式来取出数
            if(item.matches("\\d+")){ //匹配的是多位数
                //入栈
                stack.push(item);
            }
            else{
                //pop两个数，并运算，再入栈，注意出栈两个数的顺序，
                // 在减法或者除法的时候: 后弹出的 num1 作为 被减/除数，先弹出的num2作为减/除数
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                switch (item){
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                    default:
                        break;
                }
                //把 res 入栈
                stack.push(res + "");
            }
        }
        //最后留在stack中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }
}


//编写一个类 Operation 可以返回一个运算符对应的优先级
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符！");
                break;
        }
        return result;
    }
}