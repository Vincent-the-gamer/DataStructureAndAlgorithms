# Java数据结构与算法

#### 作者：诡锋     B站：-诡锋丿Lavafall-

##  Java两种核心机制

* Java虚拟机（Java Virtual Machine)
* 垃圾收集机制（Garbage Collection) 

垃圾回收在Java中自动进行，程序员无法控制。

当程序中没有用到的内存空间，会被JVM自动回收



## 数据结构

数据结构包括：线性结构和非线性结构



### 线性结构

特点：数据元素之间存在一对一的线性关系

线性结构有两种不同的存储结构：

* 顺序存储结构  
* 链式存储结构



顺序存储的线性表成为顺序表，顺序表中的存储元素是<font color="red">连续的</font>

链式存储的线性表称为链表，链表中的存储元素<font color="red">不一定是连续的</font>，元素节点中存放数据元素以及相邻元素的地址信息。

线性结构常见的有：

* 数组
* 队列
* 链表
* 栈



### 非线性结构

非线性结构包括： 

* 二维数组
* 多维数组
* 广义表
* 树结构
* 图结构



## 稀疏数组（稀疏矩阵）

当一个数组中大部分元素为0，或者为同一个值的数组时，可以使用稀疏数组来保存该数组。

稀疏数组的处理方法是：

1. 记录数组一共有几行几列，有多少个不同的值
2. 把具有不同值的元素的行列及值记录在一个小规模的数组（稀疏数组）中

|       | 行 (row)         | 列 (col)         | 值(value)                                                    |
| ----- | ---------------- | ---------------- | ------------------------------------------------------------ |
| [0]   | 存储数组的总行数 | 存储数组的总列数 | 存储数组中所有不等于出现次数最多的数字（这个数通常是0）的数字（在这里简称不相同值）的<font color="red">个数</font> |
| [1]   | 存储行数         | 存储列数         | 存储不相同值                                                 |
| [...] | 存储行数         | 存储列数         | 存储不相同值                                                 |
| [n]   | 存储行数         | 存储列数         | 存储不相同值                                                 |



#### 举个栗子

![](http://124.222.43.240:2334/upload/2022-7-7$97192MX8Yj.jpg)



#### 二维数组  转  稀疏数组的思路

1. 遍历  原始的二维数组，得到有效数据的个数  sum。
2. 根据sum就可以创建 稀疏数组sparseArr:  int[sum+1] [3]
3. 将二维数组的有效数据，存入到稀疏数组中



#### 稀疏数组   还原为   二维数组的思路

1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组：arr = int\[总行数][总列数]。

2. 再读取稀疏数组后几行的数据，并赋给原始的二维数组即可。

   

#### 代码实现

1. 模拟一个数组

   ~~~java
   		    //使用五子棋棋盘案例
           //创建一个原始的二维数组 11 * 11
           //0: 表示没有棋子  1 表示黑棋    2 表示白棋
           int[][] chessArr1 = new int[11][11];
           chessArr1[1][2] = 1;
           chessArr1[2][3] = 2;
           //从数组里面把每一行拿出来
           System.out.println("原始的二维数组：");
           for(int[] row: chessArr1){
               for(int data: row){
                   System.out.printf("%d\t",data);
               }
               System.out.println();
           }
   ~~~

   输出结果：

   ~~~
   原始的二维数组：
   0	0	0	0	0	0	0	0	0	0	0	
   0	0	1	0	0	0	0	0	0	0	0	
   0	0	0	2	0	0	0	0	0	0	0	
   0	0	0	0	0	0	0	0	0	0	0	
   0	0	0	0	0	0	0	0	0	0	0	
   0	0	0	0	0	0	0	0	0	0	0	
   0	0	0	0	0	0	0	0	0	0	0	
   0	0	0	0	0	0	0	0	0	0	0	
   0	0	0	0	0	0	0	0	0	0	0	
   0	0	0	0	0	0	0	0	0	0	0	
   0	0	0	0	0	0	0	0	0	0	0
   ~~~

   

2. 将二维数组转换为稀疏数组

~~~java
        //将二维数组 转为 稀疏数组
        //1. 先遍历二维数组 得到非0数据的个数

        // sum: 存放数组中非0数字的个数
        int sum = 0;
        for(int i=0; i < chessArr1.length; i++){
            for(int j=0;j < chessArr1[i].length; j++){
                if(chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }
        System.out.println("sum= "+sum);

        //2.创建对应的稀疏数组
        int[][] sparseArray = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        // 遍历二维数组，将非0的值存放到稀疏数组sparseArray中
        int count = 0;  //count 用于记录是第几个非0数据
        for(int i=0; i < chessArr1.length; i++){
            for(int j=0;j < chessArr1[i].length; j++){
                if(chessArr1[i][j] != 0){
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArr1[i][j];
                }
            }
        }

        // 输出稀疏数组
        System.out.println();
        System.out.println("得到的稀疏数组为：");
        for(int i=0; i< sparseArray.length; i++){
        System.out.printf("%d\t%d\t%d\t\n",sparseArray[i][0],sparseArray[i][1],sparseArray[i][2]);
        }

~~~

输出结果：

sum的结果： sum=2

~~~
得到的稀疏数组为：
11	11	2	
1  	2	  1	
2	  3	  2
~~~




3. 将稀疏数组恢复成原始数组

   ~~~java
           //1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
           //注意：数组的默认值是0，所以创建数组后，里面是一个全0的数组
           int[][] chessArr2 = new int[sparseArray[0][0]][sparseArray[0][1]];
   
           //2. 再读取稀疏数组后几行的数据(从第二行开始，因为第一行存储的是总行数，列数，有效数据的个数)，并赋给原始的二维数组即可。
           for(int i=1; i < sparseArray.length; i++){
               chessArr2[ sparseArray[i][0] ][ sparseArray[i][1] ] = sparseArray[i][2];
           }
   
           //输出恢复后的二维数组
           System.out.println("恢复后的二维数组:");
           for(int[] row: chessArr2){
               for(int data: row){
                   System.out.printf("%d\t",data);
               }
               System.out.println();
           }
   ~~~

   输出结果：
   
   ~~~
   恢复后的二维数组:
   0	0	0	0	0	0	0	0	0	0	0	
   0	0	1	0	0	0	0	0	0	0	0	
   0	0	0	2	0	0	0	0	0	0	0	
   0	0	0	0	0	0	0	0	0	0	0	
   0	0	0	0	0	0	0	0	0	0	0	
   0	0	0	0	0	0	0	0	0	0	0	
   0	0	0	0	0	0	0	0	0	0	0	
   0	0	0	0	0	0	0	0	0	0	0	
   0	0	0	0	0	0	0	0	0	0	0	
   0	0	0	0	0	0	0	0	0	0	0	
   0	0	0	0	0	0	0	0	0	0	0	
   ~~~
   
   



## 队列(Queue)

* 队列是一个有序列表，可以用<font color="red">数组</font>或是<font color="red">链表</font>来实现
* 遵循<font color="red">先进先出</font>的原则：先存入队列的数据，要先取出，后存入队列的，要后取出



#### 数组模拟队列

* 队列本身是有序列表，若使用数组的结构来存储队列的数据，则队列数组的声明如下图，其中maxSize是该队列的最大容量。
* 队列的输出和输入是分别从队首和队尾来处理的，所以需要两个变量front和rear分别记录队首和队尾的下标，front会随着数据输出而改变，而rear则是随着数据输入而改变

##### 队列示意图

注意：白嫖的图画错了，第三个的front是1

front指向队列<font color="red">第一个元素的前一个位置</font>，当队首是0（队列最前方）的时候，front == -1

![](http://124.222.43.240:2334/upload/2022-7-7$40476SwjKa.jpg)



#### 用数组来模拟队列的思路

##### 数据存入队列

1. front指向队列中<font color="red">第一个元素的前一个位置</font>。
2. 当rear == front 时，代表队列为空队列，front和rear初始值为-1。
3. 队列存入数据时，将尾指针往后移： rear+1,
4. 若尾指针 rear 小于队列的最大下标 maxSize - 1时，代表队列未满，可以存入数据到rear所指的数组元素中，当 rear == maxSize - 1 时，代表队列已满，无法再存入数据。 

##### 问题分析并优化

问题：

目前数组使用一次就不能用，没有达到复用的效果。

优化：

 将这个数组使用算法，改进成一个环形的队列（通过取模的方式： % 完成）



#### 数组模拟环形队列

##### 使用数组模拟环形队列的思路分析

1. 对 front 变量的含义做一个调整： front 指向队列的<font color="red">第一个元素</font>（原来是指向第一个元素的前一个位置）

   也就是说arr[front] ，就是队列的第一个元素，front的初始值 = 0。

2. 对 rear 变量的含义做一个调整：rear 指向队列的<font color="red">最后一个元素的后一个位置</font>，因为希望空出一个空间作为约定，rear的初始值 = 0。

3. 当队列满时，条件是 (rear + 1) % maxSize == front

4. 队列为空的条件： rear == front

5. 当我们这样分析，队列中有效的数据个数:  <font color="red">(rear + maxSize - front) % maxSize</font>

   

   所以我们就可以在原来的队列上修改得到一个环形的队列



##### 代码实现（最终版）

数组模拟环形队列：

~~~java
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

~~~



## 链表(Linked List)

链表是有序的列表，但是它在内存中存储如下

![](http://124.222.43.240:2334/upload/2022-7-7$81480fs5Xk.jpg)

1. 链表是以节点的方式来存储的，是链式存储。
2. 每个节点包含data域，next域：指向下一个节点。
3. 如图： 发现链表的各个节点不一定是连续存放的。
4. 链表分带头节点的链表和没有头节点的链表，根据实际的需求来确定。



### 单链表

单链表的创建（<font color="red">添加</font>）示意图，显示单向链表的分析

![](http://124.222.43.240:2334/upload/2022-7-7$624784rm8B.png)

#### 头节点head

* 不存放具体的数据
* 作用就是表示单链表头
* next域：指向下一个节点 (这个next的类型就是节点的类型)



#### 遍历

通过一个辅助变量，帮助遍历整个链表



#### 往单链表中 <font color="red">添加 </font>一个节点的思路

##### 不考虑顺序，直接在尾部添加节点

1. 先创建一个head头节点，作用就是表示单链表的头
2. 后面我们每添加一个节点，就直接加入到 链表的最后

* 过程示意图：

![](http://124.222.43.240:2334/upload/2022-7-7$188143fXaE.png)



##### 按照数据的特定顺序需求来添加节点

1. 首先找到新添加的节点的位置：使用一个辅助变量temp（辅助指针），通过遍历来搞定
2. 当temp遍历到需要添加的节点的前一个节点时，新的节点 .next = temp.next
3. 让 temp.next = 新的节点

* 过程示意图

![](http://124.222.43.240:2334/upload/2022-7-7$82494Z62ZF.png)




#### 从单链表中 <font color="red">修改 </font> 一个节点的思路

1. 根据节点中某个字段（比如编号），通过一个辅助指针变量temp，通过遍历找到要修改的节点
2. temp.除编号外的字段 = 新节点.这个相同的字段 (把新节点的字段赋值给辅助指针所指向的节点)



#### 从单链表中 <font color="red">删除 </font> 一个节点的思路

1. 我们先通过 遍历 找到需要删除的这个节点的 前一个节点 temp
2. temp.next = temp.next.next
3. 被删除的节点，将不会有其它引用指向，会被JVM垃圾回收机制回收

* 过程示意图

![](http://124.222.43.240:2334/upload/2022-7-7$27803fHd5y.png)

#### 完整的代码实现

~~~java
package linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args){
        //测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用","智多星");
        HeroNode hero4 = new HeroNode(4, "林冲","豹子头");

        //创建一个链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        //加入按照编号的顺序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        //显示
        singleLinkedList.list();

        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2,"老电线","啊这");
        singleLinkedList.update(newHeroNode);

        System.out.println("修改后的链表情况：");
        //显示
        singleLinkedList.list();

        //删除一个节点
        singleLinkedList.delete(1);
        singleLinkedList.delete(4);
        System.out.println("删除后的链表情况：");
        //显示
        singleLinkedList.list();
    }
}

//定义SingleLinkedList  管理我们的英雄
class SingleLinkedList{
    //先初始化一个头节点，头节点不要动, 不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    //添加节点到单向链表
    //思路： 当不考虑编号的顺序时
    // 1. 找到当前链表的最后节点
    // 2. 将最后这个节点的next 指向新的节点
    public void add(HeroNode node){
        //因为head节点不能动，因此我们需要一个辅助变量 temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while(true){
            //找到链表的最后
            if(temp.next == null){
                break;
            }
            //如果没有找到最后，就将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next指向新的节点
        temp.next = node;
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //（如果有这个排名，则添加失败，并给出提示）
    public void addByOrder(HeroNode heroNode){
        //因为头节点不能动，因此我们仍然通过一个辅助指针 (变量） 来帮助找到添加的位置
        //因为单链表，因此我们找的temp 是位于 添加位置的前一个节点，否则，插不进去
        HeroNode temp = head;
        boolean flag = false; //标识添加的编号是否存在，默认为false
        while(true){
            if(temp.next == null){  //说明temp已经在链表的最后
                break; //
            }
            else if(temp.next.no > heroNode.no){ //位置找到，就在这个temp的后面
                break;
            }
            else if(temp.next.no == heroNode.no){ //说明希望添加的heroNode的编号已经存在
                flag = true; //说明编号存在
                break;
            }
            temp = temp.next; //后移，遍历当前链表
        }
        //判断 flag 的值
        if(flag){ //不能添加，说明编号存在
            System.out.println("准备插入的英雄的编号"+ heroNode.no + "已经存在了，不能加入！");
        }
        else{
            //插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }
    //修改节点的信息，根据no编号来修改，即no编号不能改。
    //说明
    //1. 根据 new HeroNode 的 no 来修改即可
    public void update(HeroNode newHeroNode){
        //判断是否空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点, 根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false; //表示是否找到该节点
        while(true){
            if(temp == null){
                break; //已经遍历完链表
            }
            if(temp.no == newHeroNode.no){
                //如果找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag 判断是否找到要修改的节点
        if(flag){
            temp.name = newHeroNode.name;;
            temp.nickname = newHeroNode.nickname;;
        }
        else{ //没有找到
            System.out.printf("没有找到 编号=%d 的节点，不能修改\n", newHeroNode.no);
        }

    }

    //删除节点
    //思路
    //1.head 不能动，因此我们需要一个temp辅助节点，找到待删除节点的前一个节点
    //2. 我们在比较时，是temp.next.no 和 需要删除的节点的no比较
    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false; //标识是否找到待删除节点的前一个节点
        while(true){
            if(temp.next == null){ //已经到链表的最后
                break;
            }
            else if(temp.next.no == no){
                //找到了待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next; //temp后移，遍历
        }
        //判断是否找到
        if(flag){
            //可以删除
            temp.next = temp.next.next;
        }
        else{
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

    //显示链表（遍历）
    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        else{
            //因为头节点不能动，因此我们需要一个辅助变量来遍历
            HeroNode temp = head.next;
            while(true){
                //判断是否到链表最后
                if(temp == null){
                    break;
                }
                //输出节点的信息
                System.out.println(temp);
                //将temp后移，一定小心，否则是死循环
                temp = temp.next;
            }
        }
    }


}

//定义HeroNode, 每个HeroNode 对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点

    //构造器
    public HeroNode(int no,String name,String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方便，我们重写一下toString方法
    @Override
    public String toString(){
        return "HeroNode [no=" + no + ", name="+ name +", nickname=" + nickname+ "]";
    }
}
~~~



#### 单链表常见面试题：

##### 求单链表中有效节点的个数

* 代码演示：

~~~java
    //方法：获取到单链表的节点的个数（如果是带头节点的链表，不统计头节点）
    /**
     *
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head){
        if(head.next == null){
            return 0;
        }
        int length = 0;
        //定义一个辅助变量, 这里没有统计头节点
        HeroNode cur = head.next;
        while(cur != null){
            length++;
            cur = cur.next;
        }
        return length;
    }
~~~

##### 查找单链表中的倒数第k个节点【新浪面试题】

思路：
1. 编写一个方法，接收head节点，同时接收一个index
    index 表示是倒数第 index 个节点
2. 先把链表从头到尾遍历，得到链表的总的长度 (getLength)
3. 得到总长度后size，我们从链表的第1个开始遍历(size-index)个，就可以得到
4. 如果找到了，则返回该节点，否则返回null

* 代码演示

~~~java
public static HeroNode findLastKNode(HeroNode head,int k){
        //判断链表是否为空
        if(head.next == null){
            return null; //没有找到
        }
        //第一次遍历得到链表的长度(有效节点个数)
        int size = getLength(head);
        //第二次遍历找到 size-k 位置，就是我们倒数的第k个节点
        //先做一个index (在这里是k) 的校验
        if(k <= 0 || k > size) {
            return null;
        }
        //定义一个辅助变量, for 循环定位到倒数的k
        HeroNode cur = head.next;
        for(int i = 0; i < size - k; i++){
            cur = cur.next;
        }
        return cur;
    }
~~~

##### 单链表的反转【腾讯面试题，有一点难度】

思路：

1. 先定义一个节点 reverseHead = new HeroNode();
2. 从头到尾遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表的最前面
3. 原来的链表的head.next = reverseHead.next 

* 图解

  ![](http://124.222.43.240:2334/upload/2022-7-7$73667cTnde.png)

~~~java
 public static void reverseList(HeroNode head){
   //如果当前链表为空，或者只有一个节点，无需反转，直接返回
   if(head.next == null || head.next.next == null){
     return;
   }

   //定义一个辅助的指针(变量)， 帮助我们遍历原来的链表
   HeroNode cur = head.next;
   HeroNode next = null; //指向当前节点 cur 的下一个节点
   HeroNode reverseHead = new HeroNode(0,"","");

   //从头到尾遍历原来的链表
   //每遍历一个节点，就将其取出，并放在新的链表的最前面
   while(cur != null){
     next = cur.next; //先暂时保存当前节点的下一个节点，因为后面需要使用
     cur.next = reverseHead.next; //将 cur 的下一个节点指向新链表的最前面
     reverseHead.next = cur; // 将 cur 连接到新的链表上
     cur = next; //让cur后移
   }
   //将head.next 指向 reverseHead.next， 实现单链表的反转
   head.next = reverseHead.next;
 }
~~~



##### 从尾到头打印单链表【百度面试题】

思路：

* 方式一：像上面一样，先将单链表反转后再打印，然后再遍历即可。 <font color="red">这样做的问题是会破坏原来的单链表的结构，不建议这样做</font>
* **方式二：可以利用栈这个数据结构，将各个节点压入到<font color="red">栈</font>中，然后利用栈的<font color="red">先进后出</font>的特点，就实现了逆序打印的效果**
* 图解
  ![](http://124.222.43.240:2334/upload/2022-7-7$72184bYwna.png)


* 代码实现

~~~java
    // 将单链表逆序打印
    // 使用方式2：不破坏原单链表结构，用栈来实现
    public static void reversePrint(HeroNode head) {
        if(head.next == null) {
            return; //空链表，不能打印
        }
        //创建一个栈，将各个节点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        //临时变量
        HeroNode cur = head.next;
        //将链表的所有节点正序压入栈
        while (cur != null){
            stack.push(cur);
            cur = cur.next; //让cur后移，这样就可以压入下一个节点
        }
        //将栈中的节点进行打印, pop 出栈
        while(stack.size() > 0){
            System.out.println(stack.pop()); //栈的特点：先进后出，自动实现了逆序
        }
    }
~~~



##### 练习：合并两个有序的单链表，合并之后的链表依然有序



### 双向链表

#### 管理单向链表的缺点分析：

1. 单向链表，**查找的方向只能是一个方向**，而双向链表可以向前或者向后查找。
2. 单向链表不能自我删除，需要靠辅助节点，而双向链表，则可以<font color="red">自我删除</font>，所以前面我们单链表删除节点时，总是找到temp，temp是待删除节点的前一个节点，通过它来删除。

#### 分析双向链表的操作思路

1. 遍历：方式和单链表一样，只是可以向前查找，也可以向后查找。
2. 添加（默认添加到双向链表的最后）：

(1) 先找到双向链表的最后一个节点temp

(2) temp.next = new HeroNode();

(3) new HeroNode().prev = temp;

3. 修改思路和原来的单向链表一样。
4. 删除：

(1) 因为是双向链表，因此，我们可以实现自我删除某个节点。

(2) 直接找到要删除的这个节点，比如是temp

(3) temp.prev.next = temp.next;

(4) temp.next.prev = temp.prev;

* 图解

![](http://124.222.43.240:2334/upload/2022-7-7$83142Asdz2.jpg)

#### 双向链表的代码实现

~~~java
package linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        System.out.println("双向链表的测试:");
        //先创建节点
        DoubleHeroNode hero1 = new DoubleHeroNode(1, "宋江", "及时雨");
        DoubleHeroNode hero2 = new DoubleHeroNode(2, "卢俊义","玉麒麟");
        DoubleHeroNode hero3 = new DoubleHeroNode(3, "吴用","智多星");
        DoubleHeroNode hero4 = new DoubleHeroNode(4, "林冲","豹子头");
        //创建一个双向李娜表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        //添加
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        //修改
        DoubleHeroNode newHeroNode = new DoubleHeroNode(4,"あやぽんず*","森罗万象");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况:");
        doubleLinkedList.list();

        //删除
        doubleLinkedList.delete(3);
        System.out.println("删除后的链表情况~~");
        doubleLinkedList.list();
    }
}

//创建一个双向链表的类
class DoubleLinkedList{
    //先初始化一个头节点，头节点不要动, 不存放具体的数据
    private DoubleHeroNode head = new DoubleHeroNode(0,"","");

    //返回头节点
    public DoubleHeroNode getHead(){
        return head;
    }

    //遍历双向链表的方法
    //显示链表（遍历）
    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        else{
            //因为头节点不能动，因此我们需要一个辅助变量来遍历
            DoubleHeroNode temp = head.next;
            while(true){
                //判断是否到链表最后
                if(temp == null){
                    break;
                }
                //输出节点的信息
                System.out.println(temp);
                //将temp后移，一定小心，否则是死循环
                temp = temp.next;
            }
        }
    }

    //添加一个节点到双向链表的最后
    public void add(DoubleHeroNode node){
        //因为head节点不能动，因此我们需要一个辅助变量 temp
        DoubleHeroNode temp = head;
        //遍历链表，找到最后
        while(true){
            //找到链表的最后
            if(temp.next == null){
                break;
            }
            //如果没有找到最后，就将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //形成一个双向链表
        temp.next = node;
        node.prev = temp;
    }

    //修改一个节点的内容
    //可以看到双向链表的节点内容修改和单向链表一样，只是节点类型改成 DoubleHeroNode
    public void update(DoubleHeroNode newHeroNode){
        //判断是否空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点, 根据no编号
        //定义一个辅助变量
        DoubleHeroNode temp = head.next;
        boolean flag = false; //表示是否找到该节点
        while(true){
            if(temp == null){
                break; //已经遍历完链表
            }
            if(temp.no == newHeroNode.no){
                //如果找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag 判断是否找到要修改的节点
        if(flag){
            temp.name = newHeroNode.name;;
            temp.nickname = newHeroNode.nickname;;
        }
        else{ //没有找到
            System.out.printf("没有找到 编号=%d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    //从双向链表中删除一个节点
    /*
      说明：
      1. 对于双向链表，我们可以直接找到要删除的这个节点
      2. 找到后，自我删除即可
    */
    public void delete(int no){

        //判断当前链表是否为空
        if(head.next == null){ //空链表
            System.out.println("链表为空，无法删除");
            return;
        }

        DoubleHeroNode temp = head.next;  //辅助变量(指针)
        boolean flag = false; //标识是否找到待删除节点的前一个节点
        while(true){
            if(temp == null){ //已经到链表的最后节点的next域
                break;
            }
            else if(temp.no == no){
                //找到了待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next; //temp后移，遍历
        }
        //判断是否找到
        if(flag){
            //可以删除
//            temp.next = temp.next.next;  【单向链表的删除方式】
            temp.prev.next = temp.next;
            // 这里我们的代码有问题!
            // 如果是最后一个节点，就不需要执行下面这句话，否则会出现空指针异常
            if(temp.next != null ){
                temp.next.prev = temp.prev;
            }
        }
        else{
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }
}
~~~



**练习：按照编号顺序添加双向链表的节点**

**提示：安装单链表的顺序添加，稍作修改即可**



### 单向环形链表

#### 应用实例：约瑟夫（Josephus) 问题

Josephus问题为： 设编号为1,2，... ，n的n个人围坐一圈，约定编号为k (1<=k<=n) 的人从1开始报数，数到m的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列，依次类推，直到所有人出列为止，<font color="red">由此产生一个出队编号的序列</font>。

n = 5，即有5个人

k = 1，从第一个人开始报数

m = 2，数 2 下。

* 示意图

![](http://124.222.43.240:2334/upload/2022-7-7$64579RD3F4.png)

#### 构建一个单向环形链表的思路

1. 先创建第一个节点，让first指向该节点，并形成环形
2. 后面当我们每创建一个新的节点，就把该节点，加入到已有的环形链表中即可。

* 思路图解

  ![](http://124.222.43.240:2334/upload/2022-7-7$67913iyabC.jpg)

#### 遍历环形链表

1. 先让一个辅助指针（辅助变量）curBoy，指向first节点
2. 然后通过一个while循环遍历该环形链表即可， 当 curBoy.next == first ，遍历结束



#### 根据用户输入，生成出环队列的思路（小孩出圈案例的思路）

1. 需要创建一个辅助指针（变量）helper， 事先应该指向环形链表的最后这个节点。
2. 小孩报数前，先让 first 和 helper 移动 k-1 次
3. 当小孩报数时，让first和helper指针同时移动 m-1 次
4. 这时就可以将first指向的小孩节点出圈

​        first = first.next

​        helper.next = first

​       原来first指向的节点，就没有任何引用了，就会被JVM垃圾回收机制回收

​       出圈的顺序：2 4 1 5 3

* 思路图解

![](http://124.222.43.240:2334/upload/2022-7-7$80203hXMxd.png)

#### 约瑟夫(Josephus)问题的代码实现

~~~java
package linkedlist;

//约瑟夫问题
public class Josephus {
    public static void main(String[] args) {
        //测试：构建环形链表，和遍历是否OK
        CircleSingleLinkedList csll = new CircleSingleLinkedList();
        csll.addBoy(5);  //加入5个小孩节点
        csll.showBoy();

        //测试：小孩出圈是否正确
        csll.countBoy(1,2,5); //出圈顺序：2 4 1 5 3
    }
}
//创建一个环形的单向链表
class CircleSingleLinkedList{
    //创建一个first节点，目前先不要赋值
    private Boy first = null;
    //添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums){ //nums是要加入的小孩节点的数量
        //nums 做一个数据校验
        if(nums < 1){
            System.out.println("nums的值不正确");
            return;
        }
        //辅助指针，帮助构建环形链表
        Boy curBoy = null;
        //使用for循环来创建我们的环形链表
        for(int i = 1; i<= nums; i++){
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if(i == 1){
                first = boy;
                first.setNext(first); //构成环
                curBoy = first; //让curBoy指向第一个小孩
            }
            else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前的环形链表
    public void showBoy(){
        //判断链表是否为空
        if(first == null){
            System.out.println("没有任何小孩");
            return;
        }
        //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while(true){
            System.out.printf("小孩的编号：%d\n", curBoy.getNo());
            if(curBoy.getNext() == first){ //说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext(); //curBoy后移
        }
    }

    //根据用户的输入，计算出小孩出圈的顺序
    /**
     *
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums){
        //先对数据进行校验
        if(first == null || startNo < 1 || startNo > nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建一个辅助指针，帮助完成小孩出圈
        Boy helper = first;
        // 需要创建一个辅助指针（变量）helper， 事先应该指向环形链表的最后这个节点。
        while (true){
            if(helper.getNext() == first){ //说明helper指向最后一个小孩节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让 first 和 helper 移动 k-1 次
        for(int j = 0; j< startNo - 1; j++){
            first = first.getNext();
            helper = helper.getNext();
        }
        // 当小孩报数时，让first和helper指针同时移动 m-1 次, 然后出圈
        // 这里是一个循环操作，直到圈中只有一个节点
        while(true){
            if(helper == first){ //说明圈中只有一个节点
                break;
            }
            // 让 first 和 helper 指针同时移动 countNum-1 次
            for(int j=0; j < countNum-1; j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时，first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n",first.getNo());
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("最后留在圈中的小孩编号是："+first.getNo());
    }
}

//创建一个Boy类，表示一个节点
class Boy{
    private int no; //编号
    private Boy next; //指向下一个节点，默认null

    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
~~~



## 栈(Stack)

* 栈是一个<font color="blue">**先进后出**</font>(FILO -- First In Last Out)的有序列表

* 栈是限制线性表中元素的插入和删除**只能在线性表的同一端**进行的一种特殊线性表。允许插入和删除的一端，为变化的一端，称为<font color="red">**栈顶（Top）**</font>，另一端为固定的一端，称为**栈底（Bottom)**

* 根据栈的定义可知，最先放入栈中元素在栈底，最后放入的元素在栈顶，而删除元素刚好相反，最后放入的元素最先删除，最先放入的元素最后删除

* 图解方式说明出栈（pop）和入栈（push) 的概念

  

### 栈的应用场景

* 子程序的调用：在跳往子程序前，会先将下个指令的地址存到栈中，直到子程序执行完后再将地址取出，以回到原来的程序中。
* 处理递归调用：和子程序的调用类似，只是除了储存下一个指令的地址外，也将参数、区域变量等数据存入堆栈中。
* 表达式的转换 【常用于：中缀表达式转后缀表达式】与求值
* 二叉树的遍历
* 图的深度优先（depth-first）搜索法

![](http://124.222.43.240:2334/upload/2022-7-7$15902fDaEK.jpg)

### 实现栈的思路分析

1. 使用数组来模拟栈
2. 定义一个变量 top 来表示栈顶，初始化为 -1
3. <font color="red">入栈</font>的操作，当有数据加入到栈时， top++，stack[top] = data;
4. <font color="red">出栈</font>的操作，int value = stack[top];   top--;  return value;

#### 数组模拟栈的思路分析图

![](http://124.222.43.240:2334/upload/2022-7-7$90026RMCR3.jpg)

#### 代码实现

~~~java
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

~~~

#### 练习：用链表模拟栈



### 栈实现综合计算器（中缀表达式）

使用栈完成计算一个表达式的结果

3+2*6-2 = ?

数栈numStack：存放数

符号栈operStack：存放运算符

#### 思路

1. 通过一个 index 值（索引），来遍历我们的表达式。

2. 如果我们发现是一个数字，就直接入数栈。

3. 如果发现扫描到的是运算符号，就分如下情况：

   * 如果发现当前的符号栈为空，就直接入栈

   * 如果符号栈有操作符，就进行比较：

     * <font color="red">如果当前的操作符的优先级小于或者等于栈中的操作符</font>,  就需要从数栈中pop出两个数，然后再从符号栈中pop出一个运算符，进行运算，将得到的结果，入数栈，然后将当前的操作符入符号栈。

     * <font color="red">如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈</font>

   * **当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运算**

   * **最后在数栈只有一个数字，就是表达式的结果**

#### 代码实现（这代码有很多bug，不完善）：

~~~java
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
~~~



#### 练习：解决代码不能处理小括号的问题



## 前缀（波兰表达式）、中缀、后缀表达式（逆波兰表达式）

### 前缀表达式（波兰表达式）

#### 前缀表达式 --- 计算机求值过程

**从右至左**扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，用运算符

对它们做相应的计算（栈顶元素 和 次顶元素），并将结果入栈；重复上述过程直到表达式最左端，最后运算得出的值即为表达式的结果。

### 中缀表达式

* 中缀表达式就是**常见的运算表达式**，如(3+4)*5-6
* 中缀表达式的求值是我们人最熟悉的，但是对计算机来说却不好操作，因此，在计算结果时，往往会将中缀表达式转成其它表达式来操作（一般转成后缀表达式）

### 后缀表达式（逆波兰表达式）

1. 后缀表达式又称**逆波兰表达式**，与前缀表达式相似，只是运算符位于操作数之后
2. 举例说明：（3+4）* 5-6 对应的后缀表达式就是3 4 + 5 * 6 -

#### 后缀表达式 --- 计算机求值过程

**从左至右**扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，用运算符

对它们做相应的计算（栈顶元素 和 次顶元素），并将结果入栈；重复上述过程直到表达式最右端，最后运算得出的值即为表达式的结果。



### 逆波兰计算器的代码实现

1. 输入一个逆波兰表达式（后缀表达式），使用栈(Stack)，计算其结果
2. 支持小括号和多位数整数，这里只写对整数的计算

#### 思路：

先定义一个逆波兰表达式（后缀表达式）
(3+4) * 5-6 => 3 4 + 5 * 6 -

针对后缀表达式求值步骤如下：

1. 从左至右扫描，将 3 和 4 压入堆栈
2. 遇到+运算符，因此弹出 4 和 3（4为栈顶元素，3为次顶元素），计算出 3+4 的值，得 7 ，再将 7 入栈
3. 将 5 入栈
4. 接下来是 * 运算符，因此弹出 5 和 7，计算出 7*5=35，将35入栈
5. 将 6 入栈
6. 最后是-运算符，计算出 35-6 的值，即29，因此得出最终结果

#### 代码实现

~~~java
package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//逆波兰表达式
public class PolandNotation {
    public static void main(String[] args) {
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
    }

    //将一个你波兰表达式，依次将数据和运算符 放入到 ArrayList 中
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

~~~

 ### 中缀表达式转换为后缀表达式

**具体思路步骤分析**：

1. 初始化两个栈：运算符栈s1和储存中间结果的栈s2
2. 从左至右扫描中缀表达式
3. 遇到操作数时，将其压入s2
4. 遇到运算符是，比较其与s1栈顶运算符的优先级
   * 4-1：如果s1为空，或栈顶运算符为左括号 "("，则直接将次运算符入栈
   * 否则，若优先级比栈顶运算符的搞，也将运算符压入s1
   * 否则，将s1栈顶的运算符弹出并压入到s2中，再次转到 (4-1) 与s1中新的栈顶运算符相比较
5. 遇到括号时：
   * 如果是左括号 “ ( ”，则直接压入s1
   * 如果是右括号 " ) "，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
6. 重复步骤 2 至 5 ，直到表达式的最右边
7. 将s1中剩余的运算符一次弹出并压入s2
8. 依次弹出s2的元素并输出，**结果的逆序即为中缀表达式对应的后缀表达式**

中缀表达式： 1+ ((2+3)*4) - 5 

后缀表达式： 1 2 3 + 4 * + 5 -



#### 代码实现

~~~java
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
~~~

### 逆波兰计算器完整版（练习）

1. 支持+ - * / ( )
2. 多位数，小数支持
3. 兼容处理：过滤任何空白字符，包括空格，制表符，换页符

练习代码：

~~~java
~~~





## 递归

### 递归的概念：

递归就是方法自己调用自己，每次调用时**传入不同的变量**。**递归有助于编程者解决复杂的问题**，同时可以让代码变得简洁。



### 递归调用的规则：

1. 当程序执行到一个方法时，就会开辟一个独立的空间（栈帧---也就是栈里面的一个元素）
2. 每个空间的数据（局部变量）是独立的

#### 图解

![](http://124.222.43.240:2334/upload/2022-7-7$59162FMxTz.png)

#### 小例子：打印问题

递归可以理解成先递后归，调用过程不进行操作，等到递归调用找到出口以后，开始回溯执行之前的操作

```java
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

输出：
  n=2
  n=3
  n=4
```

#### 小例子：阶乘问题

~~~java
//阶乘问题
//遇到return，方法就会返回给上一层递归调用的那个地方
    public static int factorial(int n){
        if(n == 1){
            return 1;
        }
        else return factorial(n-1) * n;
    }

/*  
int res = factorial(4);
System.out.println("res=" + res);
输出：
  24       
*/

~~~

### 递归可以解决什么样的问题

1. 数学问题：八皇后问题，汉诺塔问题，阶乘问题，迷宫问题，球和篮子问题
2. 算法中：快速排序，归并排序，二分查找，分治算法等
3. 用栈解决的问题转换为递归，代码比较简洁



### 递归需要遵守的重要规则

1. 执行一个方法时，就创建一个新的受保护的独立空间（栈帧）
2. 方法的局部变量是独立的，不会相互影响，比如上面的n变量
3. 如果方法中使用的是引用类型（整个变量的值是地址）的变量（比如 数组，对象 等非基本类型的数据），就会共享该引用类型的数据。
4. 递归必须向退出递归的条件毕竟，否则就是无限递归，出现StackOverflowError（栈溢出错误），就死龟了。
5. 当一个方法执行完毕，或者遇到return，就会返回，遵守**谁调用，就将结果返回给谁**，同时当方法执行完毕或者返回时，该方法也就执行完毕。
6. 

### 经典案例：迷宫问题

* 示意图：

![](http://124.222.43.240:2334/upload/2022-7-7$72125cSpNm.png)

说明：

1. 小球得到的路径，和程序员设置的找路策略有关，即：

   找路的**上下左右**的顺序相关。

2. 在得到小球路径时，可以先使用（下右上左），再改成（上右下左）等，看看路径是不是有变化。

3. 测试回溯现象

4. 思考：<font color="red">**如何求出最短路径？**</font>

#### 代码实现

~~~java
package recursion;

//迷宫问题
public class Labyrinth {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //使用1 表示墙
        //上下全部置为1，模拟墙
        for(int i = 0 ;i < 7;i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右全部置为1
        for(int i=0; i<8; i++){
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板，1表示
        map[3][1] = 1;
        map[3][2] = 1;

        //输出地图
        System.out.println("地图的情况:");
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        //使用递归回溯给小球找路
//        setWay(map,1,1);
        setWay2(map,1,1);
        //输出新的地图，小球走过，并标识过的地图
        System.out.println("小球走过，并标识过的地图:");
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }


    //使用递归回溯来给小球找路
    /*
    说明：
    1. map 表示地图
    2. i,j 表示从地图的哪个位置开始出发
    3. 如果小球能到map[6][5] 位置，则说明通路找到。
    4. 约定：当map[i][j] 为 0 表示 该点没有走过， 1 表示 墙 ，2 表示 通路 可以走，3 表示该位置已经走过，但是走不通
    5. 在走迷宫时，需要确定一个策略（方法） 下 -> 右 -> 上 -> 左 ，如果该点走不通，再回溯
     */
    /**
     *
     * @param map 表示地图
     * @param i 从哪个位置开始找
     * @param j 从哪个位置开始找
     * @return 如果找到通路，返回true，否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j){
        if(map[6][5] == 2){ //通路已经找到 （假定map[6][5]是终点）
            return true;
        }
        else{
            if(map[i][j] == 0){ //如果当前这个点还没有走过
                //按照策略 下 -> 右 -> 上 -> 左 走
                map[i][j] = 2; //假定该点可以走通
                if(setWay(map, i + 1, j )){ //向下走
                    return true;
                } else if(setWay(map, i, j+1)){ //向右走，如果向下走不通
                    return true;
                } else if(setWay(map, i - 1, j)){ //向上走，如果向右也走不通
                    return true;
                } else if(setWay(map, i, j-1)){//向左走，如果向上也走不通
                    return true;
                } else{ //都走不通，就是死路，按照规定，死路置成3
                    map[i][j] = 3;
                    return false;
                }
            } else{ //如果map[i][j] != 0, 可能是1, 2, 3
                //1 3表示死路，2表示已经走过的路，不要再走了，所以返回false
                return false;
            }
        }
    }
    //修改找路的策略：改成 上 -> 右 -> 下 -> 左
    public static boolean setWay2(int[][] map, int i, int j){
        if(map[6][5] == 2){ //通路已经找到 （假定map[6][5]是终点）
            return true;
        }
        else{
            if(map[i][j] == 0){ //如果当前这个点还没有走过
                //按照策略 上 -> 右 -> 下 -> 左 走
                map[i][j] = 2; //假定该点可以走通
                if(setWay2(map, i - 1, j )){ //向上走
                    return true;
                } else if(setWay2(map, i, j+1)){ //向右走，如果向上走不通
                    return true;
                } else if(setWay2(map, i + 1, j)){ //向下走，如果向右也走不通
                    return true;
                } else if(setWay2(map, i, j - 1)){//向左走，如果向下也走不通
                    return true;
                } else{ //都走不通，就是死路，按照规定，死路置成3
                    map[i][j] = 3;
                    return false;
                }
            } else{ //如果map[i][j] != 0, 可能是1, 2, 3
                //1 3表示死路，2表示已经走过的路，不要再走了，所以返回false
                return false;
            }
        }
    }
}

~~~



### 经典案例： 八皇后问题

八皇后问题：在8x8格的国际象棋上摆放八个皇后，使其不能互相攻击，即：**任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法**（答案是92中）

#### 八皇后问题算法思路分析

1. 第一个皇后先放第一行第一列
2. 第二个皇后放在第二行第一列，然后判断是否OK（即判断是否冲突）， 如果不OK（如果冲突），继续放在第二列，第三列，依次把所有列都放完，找到一个合适的位置
3. 继续第三个皇后，还是第一列，第二列.....直到第8个皇后也能放在一个不冲突的位置，算是找到的了一个正确解。
4. 当得到一个正确解时，在栈帧回退到上一个栈帧时，就会开始回溯，即：将第一个皇后，放到第一列的所有正确解，全部得到。
5. 然后回头继续第一个皇后放第二列，后面继续循环执行1 2 3的步骤



**说明**：理论上应该创建一个二维数组来表示棋盘，但是实际上可以通过算法，用一个一维数组即可解决问题。

~~~java
 arr[8] = {0,4,7,5,2,6,1,3} //对应arr的下标表示第几行，即第几个皇后
~~~

arr[i] = val，val表示第 i+1 个皇后，放在第 i+1 行的第 val+1列。

 即：

第一个皇后（独占第一行）放在第1列（下标为0)，第二个皇后（独占第二行）放在第5列（下标为4），以此类推。

#### 代码实现

~~~java
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
~~~



## 算法

### 排序算法

排序也称排序算法（Sort Algorithm)，排序是将**一组数据**，依**指定的顺序**进行**排列的过程**。

### 排序的分类

1. 内部排序：

   指将需要处理的所有数据都加载到**内部存储器（内存）**中进行排序。

2. 外部排序法：

   **数据量过大**，无法全部加载到内存中，需要借助**外部存储（文件等）**进行排序。

**常见的排序算法分类**

![](http://124.222.43.240:2334/upload/2022-7-7$11382fp8Ww.png)

 ### 算法的时间复杂度

**度量一个程序（算法）执行时间的两种方法**

1. 事后统计的方法

   这种方法可行，但是有两个问题：

   * 一是要想对设计的算法的运行性能进行评测，需要实际运行该程序。
   * 二是所得时间的统计量依赖于计算机的硬件、软件等环境因素，**<font color="deepskyblue">这种方式，要在同一台计算机的相同状态下运行, 才能比较哪个算法速度更快。</font>**

2. 事前估算的方法：通过分析某个算法的**时间复杂度**来判断哪个算法更优。

#### 时间频度

一个算法花费的时间与算法中语句的执行次数成正比例，哪个算法中语句执行次数多，它花费时间就多。

**一个算法中的语句执行次数称为语句频度活时间频度。**记为$T(n)$ 。

**<font color="red">时间频度可以忽略常数项、低次项、系数！</font>**

##### 忽略常数

![](http://124.222.43.240:2334/upload/2022-7-7$67549TjWFE.png)

##### 忽略低次项

![](http://124.222.43.240:2334/upload/2022-7-7$24524HQcEF.jpg)

##### 忽略系数

![](http://124.222.43.240:2334/upload/2022-7-7$10293bdd3y.png)

### 时间复杂度

1. 一般情况下，算法中的基本操作语句的重复执行次数是问题规模n的某个函数，用$T(n)$表示，若有某个辅助函数$f(n)$，使得当n趋近于无穷大时，$\frac{T(n)}{f(n)}$的极限值为不等于零的常数，则称$f(n)$是$T(n)$的同数量级函数，记作$T(n) = O(f(n))$ 为算法的渐进时间复杂度，简称时间复杂度.
2. $T(n)$ 不同，但时间复杂度可能相同，如： $T(n) = n^2+7n+6$ 与$T(n) =3n^2+2n+2$ ，他们的$f(n)$不同，但时间复杂度相同，都为$O(n^2)$
3. 计算时间复杂度的方法
   * 用常数1代替运行时间中的所有加法常数$T(n) = 3n^2+2n+2$ => $T(n) = 3n^2+2n+1$ 
   * 修改后的运行次数函数中，只保留最高阶项 $T(n) = 3n^2+7n+1$  => $T(n) = 3n^2$ 
   * 去除最高阶项的系数 $T(n) = 3n^2$  => $T(n) = n^2$ => $O(n^2)$

#### 常见的时间复杂度

1. 常数阶$O(1)$
2. 对数阶$O(log_{2}n)$
3. 线性阶$O(n)$
4. 线性对数阶$O(n*log_{2}n)$
5. 平方阶$O(n^2)$
6. 立方阶$O(n^3)$
7. k次方阶$O(n^k)$
8. 指数阶$O(2^n)$

##### 说明

* 常见的算法时间复杂度由小到大依次为：$O(1)<O(log_{2}n)<O(n)<O(n*log_{2}n)<O(n^2)<O(n^3)<O(n^k)<O(2^n)<O(n!)$

  随着问题规模n的不断增大，上述时间复杂度不断增大，算法的执行效率越低

* 从图中可见，我们应该尽可能避免使用指数阶的算法

![](http://124.222.43.240:2334/upload/2022-7-7$982913JxKJ.jpg)



#####  常数阶$O(1)$

无论代码执行了多少行，只要是没有循环等复杂结构，那这个代码的时间复杂度就都是$O(1)$。

~~~java
int i = 1;
int j = 2;
++i;
j++;
int m = i + j;
~~~

上述代码在执行的时候，它消耗的时间并不随着某个遍历的增长而增长，那么无论这个代码有多长，即使有几万几十万行，都可以用$O(1)$来表示它的时间复杂度。



##### 对数阶$O(log_{2}n)$

~~~java
int i = 1;
while(i<n){
  i = i * 2;
}
~~~

**说明**：在while循环里面，每次都将乘以2，乘完之后，i距离n就越来越近了，假设循环x次之后，n就大于2了，此时这个循环就退出了，也就是说$2^x=n$，那么$x=log_{2}n$也就是说当循环$log_{2}n$次以后，这个代码就结束了。因此这个代码的时间复杂度是$O(log_{2}n)$。 $O(log_{2}n)$的这个2实际上是根据代码变化的，i=i*3，则是$O(log_{3}n)$。



##### 线性阶$O(n)$

~~~java
for(int i=1; i<=n; i++){
  j = i;
  j++;
}
~~~

**说明**：

这段代码，for循环里面的代码会执行n遍，因此它消耗的时间是随着n的变化而变化的，因此这类代码都可以用$O(n)$来表示它的时间复杂度



##### 线性对数阶$O(nlog_{2}n)$

~~~java
for(int m=1; m<n; m++){
  i = 1;
  while(i<n){
     i = i * 2;
  }
}
~~~

**说明：**线性对数阶$O(nlog_{2}n)$其实非常容易理解，将时间复杂度$O(log_{2}n)$的代码循环n遍的话，它的时间复杂度就是n * $O(log_{2}n)$，也就是$O(nlog_{2}n)$



##### 平方阶$O(n^2)$

双层循环

![](http://124.222.43.240:2334/upload/2022-7-7$50857axSF6.jpg)



##### 立方阶$O(n^3)$，K次方阶$O(n^3)$

类似平方阶，三层或者K层循环

参考上面的平方阶理解就好了。



#### 平均时间复杂度和最坏时间复杂度

![](http://124.222.43.240:2334/upload/2022-7-7$56785QatAh.png)

### 空间复杂度

![](http://124.222.43.240:2334/upload/2022-7-7$74920PZ2M8.png)

### 冒泡排序

通过对**待排序序列从前向后（从下标较小的元素开始**），依次比较**相邻元素**的值，若发现**逆序**则交换，使值较大的元素逐渐从前移向后部，就像水底下的气泡一样逐渐向上冒。

**因为排序的过程中，各元素不断接近自己的位置，<font color="red">如果一趟比较下来没有进行过交换，就说明序列有序，</font>因此要在排序过程中设置一个标志flag判断元素是否进行过交换，从而减少不必要的比较。**（这里说的优化，可以在冒泡排序写好后，再进行）

#### 冒泡排序规则

1. 一共进行 **数组的大小-1** 次大的循环
2. 每一趟排序的次数在逐渐地减少
3. 优化：如果我们发现在某趟排序中，一次交换都没有发生，可以提前结束冒泡排序。

#### 图解

![](http://124.222.43.240:2334/upload/2022-7-8$53104jk2w7.jpg)

#### 代码实现

~~~java
public static void bubbleSort(int[] arr){
        int temp; //用于交换的临时变量
        boolean flag = false;  //标识变量，表示是否进行过交换
        //最终代码
        for(int i=0; i < arr.length - 1;i++){
            for(int j=0; j < arr.length - 1 - i; j++){
                //如果前面的数比后面的数大，则交换
                if(arr[j] > arr[j + 1]){
                    flag = true; //交换过
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j + 1] = temp;
                }
            }
            if(!flag){  //在一趟排序中，一次交换都没有发生
                break;
            } else{ //交换过
                flag = false; //重置flag，进行下一趟判断
            }
        }
    }
~~~



### 选择排序

选择排序也属于内部排序法，是从欲排序的数据中，按指定的规则选出某一个元素，再依规定交换位置后达到排序的目的。

#### 基本思想

第一次从arr[0] - arr[n-1]中选取最小值，与arr[0]交换，第二次从arr[1] - arr[n-1]中选取最小值，与arr[1]交换.....以此类推，直到排序完毕。

#### 选择排序思路图解

示意图：

![](http://124.222.43.240:2334/upload/2022-7-9$65353eBiJ3.png)

![](http://124.222.43.240:2334/upload/2022-7-9$17533wtAF5.png)

说明：

1. 选择排序一共有 **数组大小-1** 轮排序
2. 每1轮排序，又是一个循环，循环的规则：
   * 先假定当前这个数是最小数
   * 然后和后面的每个数进行比较，如果发现有比当前数更小的数，就重新确定最小数，并得到下标
   * 当遍历到数组的最后时，就得到本轮最小数和下标
   * 交换 【具体见代码】



#### 代码实现

~~~java
public static void selectSort(int[] arr){
 				//选择排序
        for(int i=0;i< arr.length - 1; i++){
            int minIndex = i; //最小值的索引
            int min = arr[minIndex]; //假定最小值是索引为i的那个值, 这个变量用来保存最小值
            for(int j = i + 1;j < arr.length ; j++){
                if(min > arr[j]){ //说明假定的最小值，并不是最小
                    min = arr[j];  //重置最小值
                    minIndex = j;   //重置最小值索引
                }
            }
            //交换：将最小值放在arr[i]
            if(minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
}
~~~



### 插入排序

插入排序属于内部排序法，对于欲排序的元素以插入的方式找寻该元素的适当位置，以达到排序的目的。

#### 基本思想

**把n个待排序的元素看成一个有序表和一个无序表**，<font color="red">开始时**有序表中只包含一个元素**，无序表中包含有**n-1个元素**，</font>排序过程中每次从无序表中取出第一个元素，把它的排序码依次与有序表元素的排序码进行比较，将它插入到有序表中的适当位置，使之成为新的有序表。

#### 思路示意图

![](http://124.222.43.240:2334/upload/2022-7-9$281142Fsy6.png)

#### 代码实现

~~~java
 //插入排序
public static void insertSort(int[] arr){
        //定义待插入的数
        int insertVal = 0;
        //要插入到的索引
        int insertIndex = 0;
        //使用for循环来把代码简化
        for(int i = 1; i < arr.length; i++){
            insertVal = arr[i];
            insertIndex = i - 1; //即arr[1]的前面这个数的下标
            //给insertVal 找到插入的位置
        /*
        说明：
        1. insertIndex >= 0 保证在给insertVal找插入位置时不越界
        2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
        3. 就需要将 arr[insertIndex] 后移
         */
            while(insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex]; //数字后移，覆盖掉arr[insertIndex + 1]的值，所以在这里之前，记得保存原来未被覆盖的值
                insertIndex--;
            }
            //当推出while循环时，说明插入的位置找到：insertIndex + 1
            //这里是insertIndex + 1的原因是，程序判断出口时总是会多判断一次
            //当(insertIndex == 0，还要走完这次循环。然后 insertIndex 变成了 -1，这时候再判断才会退出循环

            //优化： 判断是否需要赋值，如果上面while没有执行，那么就不再赋值，直接把待插入的数算进有序表里，也就是说，它本来就在该在的位置上。
            if(insertIndex + 1 != i){ //
                arr[insertIndex + 1] = insertVal;
            }
        }
 }
~~~



### 希尔排序

#### 简单插入排序存在的问题

需要插入的数是较小的数时，后移的次数会明显增多，对效率有影响。

#### 希尔排序法介绍

希尔排序是简单插入排序法经过改进之后的一个**更高效的版本**，也称为**缩小增量算法**。

#### 基本思想：缩小增量，每次除以2

希尔排序是把记录按**下标的一定增量**分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含的关键词越来越多，**当增量减至1时**，整个文件恰好被分成一组，算法便终止。

**在希尔排序时，对有序序列在插入时有两种方法：**

* 交换法，速度较慢
* 移动法，速度较快

#### 希尔排序示意图

![](http://124.222.43.240:2334/upload/2022-7-10$47271EjM8c.jpg)

![](http://124.222.43.240:2334/upload/2022-7-10$55321D7XMJ.jpg)

#### 希尔排序【交换法】代码实现 （较慢）

~~~java
    //希尔排序[交换式]，对有序序列在插入时采用交换法
    public static void shellSort(int[] arr) {
        int temp = 0;
        //gap: 步长
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素，共gap组，每组有 (arr.length/gap) 个元素，步长为gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }
~~~

#### 希尔排序【移动法】代码实现

~~~java
    //对交换式的希尔排序进行优化
    //希尔排序【移动式】，或者叫移位法
    public static void shellSort2(int[] arr){
        //增量gap, 并逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对其所在的组进行直接插入排序
            for(int i = gap; i< arr.length; i++){
                int j = i;
                int temp = arr[j];
                if(arr[j] < arr[j - gap]){
                    while(j - gap >= 0 && temp < arr[j-gap]){
                        //移动
                        arr[j] = arr[j-gap];
                        j -= gap;
                    }
                    //当退出while后，就给temp找到插入的位置
                    arr[j] = temp;
                }
            }
        }
    }
~~~



### 快速排序

快速排序是对冒泡排序的一种改进。

#### 基本思想

通过一趟排序将要排序的数据分割成独立的两个部分，其中一部分的所有数据比另外一部分的所有数据要小。然后再按此方法对这两部分的数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。

#### 示意图

![](http://124.222.43.240:2334/upload/2022-7-10$27747nDnZP.png)



![](http://124.222.43.240:2334/upload/2022-7-10$588524JpsE.png)

#### 代码实现

其实某硅谷的这个快排讲的不是很好，之后我自己写一个以第一个元素为基准的快排吧233

~~~java
 public static void quickSort(int[] arr,int left,int right){
        int l = left; //左下标
        int r = right; //右下标
        //pivot 中轴
        int pivot = arr[(left + right) / 2];
        int temp = 0; //临时变量，作为交换时使用
        //while循环的目的是让：比pivot值小的放到左边，比pivot大的值放到右边
        while(l < r){
            //在pivot的左边一直找，找到大于等于pivot的值
            while(arr[l] < pivot){
                l += 1;
            }
            //在pivot的右边一直找，找到小于等于pivot的值
            while(arr[r] > pivot){
                r -= 1;
            }
            /*
              如果l >= r成立，说明pivot的左右两边的值已经变成：
              左边全部是小于pivot的值，而右边全部是大于等于pivot的值
             */

            if(l >= r){
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现这个arr[l] == pivot值，r 前移一下(r--)
            if(arr[l] == pivot){
                r -= 1;
            }
            //如果交换完后，发现这个arr[r] == pivot值，l 后移一下(l++)
            if(arr[r] == pivot){
                l += 1;
            }
        }

        //如果l == r，必须l++，r--，否则会出现栈溢出
        if(l == r){
            l += 1;
            r -= 1;
        }

        //向左递归
        if (left < r){
            quickSort(arr,left,r);
        }
        //向右递归
        if(right > l){
            quickSort(arr,l,right);
        }
    }
~~~



### 归并排序

归并排序(Merge sort) 是利用 **归并** 的思想实现的排序的方法，采用分治法。

#### 分治法（divide and conquer)

将问题 **分 (divide)** 成一些小的问题，然后递归求解。

然后 **治 (conquer)** 的阶段则将分的阶段得到的各答案“修补”在一起

#### 基本思想

* 示意图


