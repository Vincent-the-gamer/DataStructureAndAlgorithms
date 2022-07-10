package linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args){
        //测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用","智多星");
        HeroNode hero4 = new HeroNode(4, "林冲","豹子头");
        HeroNode hero5 = new HeroNode(5, "老电线", "打击垫人");
        HeroNode hero6 = new HeroNode(6, "南愁","音乐人");
        HeroNode hero7 = new HeroNode(7, "Alan Walker","热度人");
        HeroNode hero8 = new HeroNode(8, "佛屌瑞","sb");

        //创建2个单链表
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();

        //测试有序单链表的合并
        //按顺序加入, 模拟有序链表
        singleLinkedList1.add(hero4);
        singleLinkedList1.add(hero2);
        singleLinkedList1.add(hero3);
        singleLinkedList1.add(hero1);

        singleLinkedList2.add(hero8);
        singleLinkedList2.add(hero7);
        singleLinkedList2.add(hero5);
        singleLinkedList2.add(hero6);


        //将单链表2 逆向
//        reverseList(singleLinkedList2.getHead());

        SingleLinkedList result = mergeLists(singleLinkedList1.getHead(),singleLinkedList2.getHead());
        System.out.println("合并后的结果为：");
        result.list();





        //加入节点，它们会自动按照编号排序
//        singleLinkedList.addByOrder(hero1);
//        singleLinkedList.addByOrder(hero4);
//        singleLinkedList.addByOrder(hero2);
//        singleLinkedList.addByOrder(hero3);
//        System.out.println("原来的链表情况：");
//        //显示
//        singleLinkedList.list();

        //测试一下单链表反转
//        System.out.println("反转单链表：");
//        reverseList(singleLinkedList.getHead());
//        singleLinkedList.list();

        //测试逆序打印单链表，没有改变链表的结构
//        System.out.println("测试逆序打印单链表, 没有改变链表的结构~~~");
//        //逆序打印
//        reversePrint(singleLinkedList.getHead());


        //测试修改节点的代码
//        HeroNode newHeroNode = new HeroNode(2,"老电线","啊这");
//        singleLinkedList.update(newHeroNode);
//
//        System.out.println("修改后的链表情况：");
//        //显示
//        singleLinkedList.list();
//
//        //删除一个节点
//        singleLinkedList.delete(1);
//        singleLinkedList.delete(4);
//        System.out.println("删除后的链表情况：");
//        //显示
//        singleLinkedList.list();
//
//        //测试一下 求单链表中有效节点的个数
//        System.out.println("有效的节点个数为： " + getLength( singleLinkedList.getHead() ) ); //2
//
//        //测试一下看看是否得到了倒数第k个节点
//        int k = 2;
//        HeroNode res = findLastKNode(singleLinkedList.getHead(), k);
//        System.out.println("倒数第"+ k +"个节点是： " + res);



    }

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

    //查找单链表中的倒数第k个节点【新浪面试题】
    /*
      思路：
      1. 编写一个方法，接收head节点，同时接收一个index
      index 表示是倒数第 index 个节点
      2. 先把链表从头到尾遍历，得到链表的总的长度 (getLength)
      3. 得到总长度后size，我们从链表的第1个开始遍历(size-index)个，就可以得到
      4. 如果找到了，则返回该节点，否则返回null
     */
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

    //将单链表反转
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

    //练习： 合并两个有序的单链表，合并之后仍然有序   (其实我这么写，无序的也能合并了）
    //参数： 要被并入的链表list， 要并入链表的头节点head
    public static SingleLinkedList mergeLists(HeroNode head1, HeroNode head2) {
        HeroNode current1 = head1.next;
        HeroNode current2 = head2.next;
        //创建一个单链表
        SingleLinkedList list = new SingleLinkedList();
        //遍历待合并链表一, 把节点推入到新链表
        while (current1 != null){
            HeroNode next1 = current1.next; //操作前保存一下节点的next域所指向的节点，否则这个节点会丢失，变成null
            list.addByOrder(current1);   //按编号正序推入list
            current1 = next1; //current后移
        }
        //遍历待合并链表一, 把节点推入到新链表
        while(current2 != null){
            HeroNode next = current2.next; //操作前保存一下节点的next域所指向的节点，否则这个节点会丢失，变成null
            list.addByOrder(current2); //按编号正序推入list
            current2 = next; //current后移
        }

       return list;
    }
}

//定义SingleLinkedList  管理我们的英雄
class SingleLinkedList{

    //先初始化一个头节点，头节点不要动, 不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    //getter， 返回头节点
    public HeroNode getHead() {
        return head;
    }

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

