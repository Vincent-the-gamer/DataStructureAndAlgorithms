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