package tree;

//线索化二叉树
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试中序线索二叉树的功能
        HeroNode2 root = new HeroNode2(1,"光");
        HeroNode2 node2 = new HeroNode2(3,"对立");
        HeroNode2 node3 = new HeroNode2(6,"DORO*C");
        HeroNode2 node4 = new HeroNode2(8,"对立 - Grievous Lady");
        HeroNode2 node5 = new HeroNode2(10,"勃立♂零懵");
        HeroNode2 node6 = new HeroNode2(14,"肋米力压♂撕咔雷忑");

        //二叉树，后面我们要递归创建，现在简单处理，使用手动档(手动创建)
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试线索化
        ThreadedBinaryTree tbt = new ThreadedBinaryTree();
        tbt.setRoot(root);
        tbt.threadedNodes();

        //测试：以10号节点
//        System.out.println("10号节点的前驱节点是：" + node5.getLeft());
//        System.out.println("10号节点的后继节点是：" + node5.getRight());

        //测试：遍历中序线索化的二叉树
        System.out.println("遍历中序线索化二叉树");
        tbt.threadedList(); //8,3,10,1,14,6

    }
}

//实现了线索化功能的二叉树：ThreadedBinaryTree
class ThreadedBinaryTree {
    private HeroNode2 root; //根节点
    //为了实现线索化，需要创建一个指向当前节点的前驱节点的一个指针
    //在递归进行线索化时，prev总是保留前一个节点
    private HeroNode2 prev = null; //初始化为null

    public void setRoot(HeroNode2 root) {
        this.root = root;
    }

    // 遍历：中序线索化二叉树
    // 因为线索化后，各个节点指向有变化，因此  原来的遍历方式不能使用，
    // 这时需要使用新的方式遍历线索化二叉树，各个节点可以通过线性方式遍历，
    // 因此无需使用递归方式，这样也提高了遍历的效率, 遍历的次序应当和中序遍历保持一致。
    public void threadedList(){
        //定义一个变量，存储当前遍历的节点，从root开始
        HeroNode2 node = root;
        while(node != null){
            //循环的找到leftType == 1的节点，第一个找到的是8节点
            //后面随着遍历而变化，因为当leftType == 1时，说明该节点是按照线索化处理后的有效节点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            //找到了第一个节点，打印当前这个节点
            System.out.println(node);
            //如果当前节点的右指针指向的是 后继节点，就一直输出
            while(node.getRightType() == 1){
                //获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            //状态转移：将当前这个节点替换成后继，进入下一次循环
            node = node.getRight();

        }
    }

    //重载线索化方法，默认传根节点
    public void threadedNodes(){
        this.threadedNodes(root);
    }

    //编写对二叉树进行中序线索化的方法
    /**
     * @param node 就是当前需要线索化的节点
     */
    public void threadedNodes(HeroNode2 node){
        //如果node == null ，不能线索化
        if(node == null){
            return;
        }
        //1. 线索化左子树
        threadedNodes(node.getLeft());
        //2. 线索化当前节点[有难度]

        //处理当前节点的前驱节点
        //以8节点理解：按照中序遍历，8没有前驱节点
        //8节点的.left = null, 8节点的.leftType = 1
        if(node.getLeft() == null){
            //让当前节点的左指针指向前驱节点
            node.setLeft(prev);
            //修改当前节点的左指针的类型
            node.setLeftType(1); //代表是线索指针，指向前驱
        }
        //处理后继节点
        if(prev != null && prev.getRight() == null){
            //让前驱节点的右指针指向当前节点（也就是现在的节点是自己前驱的后继）
            prev.setRight(node);
            //修改前驱节点的右指针类型
            prev.setRightType(1);  //代表是线索指针，指向后继
        }

        //这句话很重要！！！每处理一个节点后，让当前节点是下一个节点的前驱节点
        this.prev = node;

        //3. 线索化右子树
        threadedNodes(node.getRight());
    }


}

//先创建HeroNode 节点
class HeroNode2 {
    private int no;
    private String name;
    private HeroNode2 left;  //默认null
    private HeroNode2 right;  //默认null

    //说明
    //1. 如果leftType == 0 表示指向的是左子树
    //2. 如果rightType == 0 表示指向的是右子树
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode2(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode2 getLeft() {
        return left;
    }

    public void setLeft(HeroNode2 left) {
        this.left = left;
    }

    public HeroNode2 getRight() {
        return right;
    }

    public void setRight(HeroNode2 right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "no= " + no + ", name= " + name;
    }
}

