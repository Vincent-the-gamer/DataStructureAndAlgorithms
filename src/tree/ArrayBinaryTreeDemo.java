package tree;

//用数组存储二叉树（顺序存储二叉树）
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        //创建一个顺序存储二叉树
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        System.out.println("前序遍历");
        arrayBinaryTree.frontOrder(); //1,2,4,5,3,6,7
        System.out.println();
        System.out.println("中序遍历");
        arrayBinaryTree.midOrder();
        System.out.println();
        System.out.println("后序遍历");
        arrayBinaryTree.backOrder();
    }
}


//编写一个ArrayBinaryTree, 实现顺序存储二叉树遍历
class ArrayBinaryTree {
    private int[] arr; //存储数据节点的数组

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载
    public void frontOrder(){
        this.frontOrder(0);
    }
    public void midOrder(){
        this.midOrder(0);
    }
    public void backOrder(){
        this.backOrder(0);
    }

    //编写一个方法，完成顺序存储二叉树的前序遍历
    /**
     * @param index 数组的下标
     */
    public void frontOrder(int index){
        //如果数组为空，或者 arr.length == 0
        if(arr == null || arr.length == 0){
            System.out.println("数组为空，不能进行顺序二叉树的前序遍历");
            return;
        }
        //输出当前数组的元素
        System.out.print(arr[index] + " ");
        //向左递归遍历
        if( (2 * index + 1)  < arr.length){
            frontOrder(2 * index + 1);
        }
        //向右递归遍历
        if(( 2 * index + 2) < arr.length){
            frontOrder(2 * index + 2);
        }
    }

    //中序遍历
    public void midOrder(int index){
        //如果数组为空，或者 arr.length == 0
        if(arr == null || arr.length == 0){
            System.out.println("数组为空，不能进行顺序二叉树的中序遍历");
            return;
        }
        //向左递归遍历
        if(( 2 * index + 1) < arr.length){
            midOrder(2 * index + 1);
        }
        //输出当前数组的元素
        System.out.print(arr[index] + " ");

        //向右递归遍历
        if( (2 * index + 2) < arr.length){
            midOrder(2 * index + 2);
        }
    }

    //后序遍历
    public void backOrder(int index){
        //如果数组为空，或者 arr.length == 0
        if(arr == null || arr.length == 0){
            System.out.println("数组为空，不能进行顺序二叉树的中序遍历");
            return;
        }
        //向左递归遍历
        if( (2 * index + 1) < arr.length){
            backOrder(2 * index + 1);
        }
        //向右递归遍历
        if( (2 * index + 2) < arr.length){
            backOrder(2 * index + 2);
        }
        //输出当前数组的元素
        System.out.print(arr[index] + " ");
    }
}