package huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//哈夫曼树
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node huffmanRoot = createHuffmanTree(arr);
        //测试
        frontOrder(huffmanRoot);

    }

    //前序遍历哈夫曼树
    public static void frontOrder(Node huffmanRoot){
        if(huffmanRoot != null){
            huffmanRoot.frontOrder();
        }
        else{
            System.out.println("是空树，无法遍历！");
        }
    }


    //创建哈夫曼树的方法

    /**
     *
     * @param arr 需要创建成哈夫曼树的数组
     * @return  创建好后的哈夫曼树的root节点
     */
    public static Node createHuffmanTree(int[] arr){
        //第一步为了操作方便
        //1. 遍历arr数组
        //2. 将arr的每个元素构建成一个Node
        //3. 将Node 放入到ArrayList中
        List<Node> nodes = new ArrayList<>();
        for(int value:arr){
            nodes.add(new Node(value));
        }

        //我们这里要循环处理
        while(nodes.size() > 1) {
            //排序 从小到大
            Collections.sort(nodes);
//            System.out.println("nodes = " + nodes);

            //取出根节点权值最小的两棵二叉树
            // 1. 取出权值最小的节点（二叉树)
            Node leftNode = nodes.get(0);
            //2. 取出权值第二小的节点（二叉树)
            Node rightNode = nodes.get(1);

            //3.构建一棵新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //4. 从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //5. 将parents加入到nodes
            nodes.add(parent);
        }

        //返回哈夫曼树的根节点
        return nodes.get(0);
    }
}

//创建节点类
//为了让 Node 的实例对象支持使用集合Collections的sort排序方法，要实现Comparable接口，自定义排序规则
class Node implements Comparable<Node>{
    int value;  //节点权值
    Node left;  //指向左子节点
    Node right;  //指向右子节点

    public Node(int value) {
        this.value = value;
    }
    //前序遍历
    public void frontOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.frontOrder();
        }
        if(this.right != null){
            this.right.frontOrder();
        }
    }

    @Override
    public String toString() {
        return "节点权值 value = " + value;
    }

    @Override
    public int compareTo(Node o) {
        // 表示从小到大排序
        return this.value - o.value;
    }
}
