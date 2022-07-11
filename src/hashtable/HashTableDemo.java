package hashtable;

import java.util.Scanner;

//哈希表
public class HashTableDemo {
    //注意： 视频中没有讲删除，这个是自己练习的代码
    public static void main(String[] args) {
        //创建一个哈希表
        //初始化一个大小为 7 的  empLinkedListArray链表数组
        HashTable hashTable = new HashTable(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("delete: 删除雇员");
            System.out.println("exit: 退出程序");

            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id,name);
                    hashTable.add(emp);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTable.findEmpById(id);
                    break;
                case "delete":
                    System.out.println("请输入要删除的id");
                    id = scanner.nextInt();
                    hashTable.deleteEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }

    }
}

//创建哈希表 HashTable 管理多条链表（用数组 + 链表，数组里每一个元素都是一个链表来简单实现）
class HashTable {
    private EmpLinkedList[] empLinkedListArray;
    private int size;  //数组的长度，表示有多少条链表

    //构造器
    //初始化一个大小为size 的  empLinkedListArray链表数组
    public HashTable(int size) {
        this.size = size;
        //初始化empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        //这里有个坑！！！不要忘记分别实例化每个链表！！
        for(int i=0;i<size;i++){
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp){
        //根据员工的ID，得到该员工应当添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        //将emp 添加到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }
    //遍历所有的链表 (遍历哈希表）
    public void list(){
        for(int i = 0;i < size;i++){
            empLinkedListArray[i].list(i);
        }
    }

    //编写一个散列函数，使用一个简单的取模法来处理
    public int hashFun(int id) {
        return id % size;
    }

    //根据输入的id，查找雇员
    public void findEmpById(int id){
        //使用散列函数确定到哪条链表查找
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if(emp != null){ //找到了
            System.out.printf("在第 %d 条链表中找到 雇员 id = %d\n",(empLinkedListNo + 1),id);
        }
        else{
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }

    //根据输入的id，删除雇员
    public void deleteEmpById(int id){
        //使用散列函数确定到哪条链表查找
        int empLinkedListNo = hashFun(id);
        empLinkedListArray[empLinkedListNo].deleteEmpById(id);
    }
}



//雇员类
class Emp{
    public int id;
    public String name;
    public Emp next; //next默认为null

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}


//创建EmpLinkedList，表示链表
class EmpLinkedList {
    //头指针，指向第一个Emp，因此，这个链表的head是有效的，直接指向第一个Emp
    private Emp head; //默认null

    //添加雇员到链表
    //说明：
    //1. 假设当添加雇员时，id是自增长的，即id的分配总是从小到大的
    //   因此我们将该雇员直接加入到本链表的最后即可
    public void add(Emp emp){
        //如果是添加第一个雇员
        if(head == null){
            head = emp;
        }
        else{
            //如果不是第一个雇员，则使用一个辅助指针，帮助定位到最后
            Emp curEmp = head;
            while(true){
                if(curEmp.next == null){ //说明到链表最后
                    break;
                }
                curEmp = curEmp.next; //后移
            }
            //退出时直接将emp 加入链表
            curEmp.next = emp;
        }
    }

    //遍历链表的雇员信息
    public void list(int no){
        if(head == null){ //说明链表为空
            System.out.println("第"+ (no+1) +"条链表为空");
            return;
        }
        System.out.print("第"+ (no+1) +"条链表的信息为 ");
        Emp curEmp = head; //辅助指针
        while(true){
            System.out.printf("=> id = %d name = %s\t\n",curEmp.id,curEmp.name);
            if(curEmp.next == null){ //说明curEmp已经是最后节点
                break;
            }
            curEmp = curEmp.next; //指针后移
        }
    }

    //根据id查找雇员
    //如果查找到，返回Emp, 如果没有找到，就返回null
    public Emp findEmpById(int id){
        //判断链表是否为空
        if(head == null){
            System.out.println("链表空的！！");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while(true){
            if(curEmp.id == id){ //找到
                break; //这时curEmp就指向要查找的雇员
            }
            //退出
            if(curEmp.next == null){//说明遍历当前链表没有找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next; //后移
        }
        return curEmp;
    }


    //按照id删除链表
    public void deleteEmpById(int id){
        //判断链表是否为空
        if(head == null){
            System.out.println("链表已经空了，不要删了！！");
        }
        else{
            //遍历链表
            Emp curEmp = head; //辅助指针
            while(true){
                //链表遍历到头了，并且还没有找到要删除的雇员
                //注意，这里的head.id != id是为了排除某个链表中只有一个节点的情况，因为此时如果头节点正好找到
                //它的next也是空的，就不能正常删除了
                if(curEmp.next == null && head.id != id){
                    System.out.println("没有找到该雇员，无法删除！");
                    break;
                }
                if(head.id == id){ //如果一上来头节点就是
                    head = head.next;
                    System.out.println("删除成功！正好就是id="+ id + "所在链表的头节点");
                    break;
                }

                if(curEmp.next.id == id){ //如果找到的不是头节点
                    curEmp.next = curEmp.next.next;
                    System.out.println("删除成功！");
                    break;
                }
                curEmp = curEmp.next; //指针后移

            }
        }
    }
}