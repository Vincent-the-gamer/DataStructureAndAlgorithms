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