package tree;

//二叉树
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先需要创建一棵二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1, "蕾姆");
        HeroNode node2 = new HeroNode(2, "蒂法");
        HeroNode node3 = new HeroNode(3, "爱丽丝");
        HeroNode node4 = new HeroNode(4, "玛丽萝丝");
        HeroNode node5 = new HeroNode(5, "雏鹤爱");


        //说明：我们先手动创建该二叉树，后面学习以递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

//        //测试前序遍历
//        System.out.println("前序遍历"); //1 2 3 5 4
//        binaryTree.frontOrder();

//        //测试中序遍历
//        System.out.println("中序遍历"); //2 1 5 3 4
//        binaryTree.midOrder();

//        //测试后序遍历
//        System.out.println("后序遍历"); //2 5 4 3 1
//        binaryTree.backOrder();

        // 前序遍历查找
//        System.out.println("前序遍历查找：");
//        int no = 5;
//        System.out.println("正在查找编号为："+ no + " 的节点");
//        HeroNode node = binaryTree.frontOrderSearch(no);
//        if (node != null) {
//            System.out.println("找到了节点：no=" + node.getNo() + "  name=" + node.getName());
//        } else {
//            System.out.println("没有找到no= " + no + " 的节点");
//        }

//         //中序遍历查找
//        System.out.println("中序遍历查找：");
//        int no = 5;
//        System.out.println("正在查找编号为："+ no + " 的节点");
//        HeroNode node = binaryTree.midOrderSearch(no);
//        if (node != null) {
//            System.out.println("找到了节点：no=" + node.getNo() + "  name=" + node.getName());
//        } else {
//            System.out.println("没有找到no= " + no + " 的节点");
//        }

//        //后序遍历查找
//        System.out.println("中序遍历查找：");
//        int no = 3;
//        System.out.println("正在查找编号为："+ no + " 的节点");
//        HeroNode node = binaryTree.backOrderSearch(no);
//        if (node != null) {
//            System.out.println("找到了节点：no=" + node.getNo() + "  name=" + node.getName());
//        } else {
//            System.out.println("没有找到no= " + no + " 的节点");
//        }

        //测试：删除节点功能
        System.out.println("删除前：前序遍历");
        binaryTree.frontOrder();
        binaryTree.delNode(3);
        System.out.println("删除后：前序遍历");
        binaryTree.frontOrder();


    }

}


//先创建HeroNode 节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;  //默认null
    private HeroNode right;  //默认null

    public HeroNode(int no, String name) {
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "no= " + no + ", name= " + name;
    }

    //编写前根序遍历的方法: 根 左 右
    public void frontOrder() {
        System.out.println(this); //先输出根节点（父节点）
        //递归向左子树前根序遍历
        if (this.left != null) {
            this.left.frontOrder(); //递归
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.frontOrder();//递归
        }
    }

    //编写中根序遍历的方法: 左 根 右
    public void midOrder() {
        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.midOrder();
        }
        //输出根节点
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    //编写后根序遍历的方法: 左 右 根
    public void backOrder() {
        //递归向左子树后序遍历
        if (this.left != null) {
            this.left.backOrder();
        }
        //递归向右子树后序遍历
        if (this.right != null) {
            this.right.backOrder();
        }
        //输出根节点
        System.out.println(this);
    }

    //前序遍历查找

    /**
     * @param no 要查找的编号
     * @return 如果找到就返回该Node, 如果没有找到返回null
     */
    public HeroNode frontOrderSearch(int no) {
        //比较当前结点是不是
        if (this.no == no) {
            return this;
        }
        //如果不等，则判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
        //如果左递归前序查找找到了节点，就返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.frontOrderSearch(no);
        }
        if (resNode != null) { //说明左子树找到了
            return resNode;
        }
        //如果左递归前序查找没有找到，
        // 继续判断当前节点的右子节点是否为空，如果不为空则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.right.frontOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode midOrderSearch(int no) {
        //先判断当前节点的左子节点是否为空，如果不为空，则递归中序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.midOrderSearch(no);
        }
        if (resNode != null) { //如果找到了，返回
            return resNode;
        }
        //如果没有找到，就和当前节点比较，如果是则返回当前节点，
        if (this.no == no) {
            return this;
        }
        //否则继续进行右递归的中序查找
        if (this.right != null) {
            resNode = this.right.midOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode backOrderSearch(int no) {
        //先判断当前节点的左子节点是否为空, 如果不为空，则向左递归后序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.backOrderSearch(no);
        }

        //如果左边找到了
        if (resNode != null) {
            return resNode;
        }
        //如果左子树没有找到，则向右子树递归进行后序遍历查找
        if (this.right != null) {
            resNode = this.right.backOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        //如果左右子树都没有找到，就比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        return null;
    }

    //递归删除节点：
    /*
    规定：
    1. 如果删除的节点是叶子节点（没有子节点的节点），则删除该节点
    2. 如果删除的节点是非叶子节点（有子节点的节点），则删除该子树
     */
    public void delNode(int no){
        /*
        思路：
        1. 如果整个树只有一个根节点（根节点root的left和right都是空)，则等价于将二叉树置空
        2. 因为二叉树是单向的，所以我们是判断当前节点的子节点是否需要删除，而不能去判断当前这个节点是不是需要删除的节点。(类似于单向链表的删除)
        3. 如果当前节点的左子节点不为空，并且左子节点就是需要删除的节点，就将this.left = null ，并且结束删除任务（结束递归）
        4. 如果当前节点的右子节点不为空，并且右子节点就是需要删除的节点，就将this.right = null，并且结束删除任务（结束递归）
        5. 如果第2，3步都没有删除掉这个节点，那么我们就需要向左子树进行递归删除
        6. 如果第4步也没有删除节点，则应当向右子树进行递归删除。
         */

        //如果当前节点的左子节点不为空，并且左子节点就是需要删除的节点，就将this.left = null ，并且结束删除任务（结束递归）
        if(this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        //如果当前节点的右子节点不为空，并且右子节点就是需要删除的节点，就将this.right = null，并且结束删除任务（结束递归）
        if(this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        //如果第2，3步都没有删除掉这个节点，那么我们就需要向左子树进行递归删除
        if(this.left != null){
            this.left.delNode(no);
        }
        //如果第4步也没有删除节点，则应当向右子树进行递归删除
        if(this.right != null){
            this.right.delNode(no);
        }
    }


}

//定义一个二叉树：BinaryTree
class BinaryTree {
    private HeroNode root; //根节点

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void frontOrder() {
        if (this.root != null) {
            this.root.frontOrder();
        } else {
            System.out.println("二叉树为空，无法前序遍历！");
        }
    }

    //中序遍历
    public void midOrder() {
        if (this.root != null) {
            this.root.midOrder();
        } else {
            System.out.println("二叉树为空，无法中序遍历！");
        }
    }

    //后序遍历
    public void backOrder() {
        if (this.root != null) {
            this.root.backOrder();
        } else {
            System.out.println("二叉树为空，无法后序遍历！");
        }
    }

    //前序遍历查找
    public HeroNode frontOrderSearch(int no) {
        if (root != null) {
            return root.frontOrderSearch(no);
        } else return null;

    }

    //中序遍历查找
    public HeroNode midOrderSearch(int no) {
        if (root != null) {
            return root.midOrderSearch(no);
        } else return null;
    }

    //后序遍历查找
    public HeroNode backOrderSearch(int no) {
        if (root != null) {
            return root.backOrderSearch(no);
        } else return null;
    }

    //删除节点
    public void delNode(int no){
        if(root != null){
            //如果只有一个root节点，这里立即判断root是不是就是要删除的节点
            if(root.getNo() == no){
                root = null;
            }
            else{
                //递归删除
                root.delNode(no);
            }
        }
        else{
            System.out.println("空树，不能删除！");
        }
    }

}