package tree;

//先创建HeroNode 节点
public class HeroNode {
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