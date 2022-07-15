package huffmantree;

import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
//        System.out.println("压缩前长度：" + contentBytes.length); //40

        List<HuffmanNode> nodes = getNodes(contentBytes);
//        System.out.println("nodes = " + nodes);

        //测试：创建的二叉树
        System.out.println("哈夫曼树：");
        HuffmanNode huffmanTreeRoot = createHuffmanTree(nodes);
        //调用前序遍历
        huffmanTreeRoot.frontOrder();

        //测试，是否生成了对应的哈夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        System.out.println("~生成的哈夫曼编码表是：" + huffmanCodes);

    }

    //生成哈夫曼树对应的哈夫曼编码
    /*
    思路：
    1. 将哈夫曼编码表存放在 Map<Byte,String> 形式：
       32 => 01 97 => 100  10 => 11000 等
    */
    static Map<Byte,String> huffmanCodes = new HashMap<>();
    /*
    2. 在生成哈夫曼编码表时，需要去拼接路径，定义一个 StringBuilder 存储某个叶子节点的路径
     */
    static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便，我们重载getCodes
    private static Map<Byte,String> getCodes(HuffmanNode root){
        if(root == null){
            return null;
        }
        //处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        //处理root的右子树
        getCodes(root.right,"1",stringBuilder);
        return huffmanCodes;
    }

    /**
     * 功能：将传入的node节点的所有叶子节点的哈夫曼编码得到，并放入到huffmanCode集合中
     * @param node 传入的节点
     * @param code 路径的值（左子节点是0，右子节点是1）
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(HuffmanNode node, String code, StringBuilder stringBuilder){
       StringBuilder stringBuilder2 =  new StringBuilder(stringBuilder);
       //将code 加入到 stringBuilder2
       stringBuilder2.append(code);
       if(node != null){ //如果node == null不处理
           //判断当前 node 是叶子节点还是非叶子节点
           if(node.data == null){ //非叶子节点
               //递归处理
               //向左
               getCodes(node.left, "0", stringBuilder2);
               //向右递归
               getCodes(node.right, "1",stringBuilder2);
           }
           else{ //说明是一个叶子节点
               //就表示找到某个叶子节点
               huffmanCodes.put(node.data,stringBuilder2.toString());
           }

       }
    }

    //前序遍历
    private static void frontOrder(HuffmanNode root){
        if(root != null){
            root.frontOrder();
        }
        else {
            System.out.println("哈夫曼树为空");
        }
    }

    /**
     * @param bytes 接收字节数组
     * @return 返回的就是List形式
     */
    private static List<HuffmanNode> getNodes(byte[] bytes){
        //1.创建一个Arraylist
        List<HuffmanNode> nodes = new ArrayList<>();

        //遍历bytes，统计 每一个byte出现的次数 用map存放
        Map<Byte,Integer> counts = new HashMap<>();
        for(byte b:bytes){
            Integer count = counts.get(b);
            if(count == null){ //Map还没有这个字符数据
                 counts.put(b,1);
            }
            else{
                 counts.put(b, count+1);
            }
        }

        //把每一个键值对转成一个 HuffmanNode对象，并加入到nodes集合
        //遍历map(遍历键值对，键值对是一个整体，每一个元素就是一对键值对）
        for(Map.Entry<Byte,Integer> entry: counts.entrySet()){
            nodes.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //通过 List 创建对应的哈夫曼树
    private static HuffmanNode createHuffmanTree(List<HuffmanNode> nodes){
        while(nodes.size() > 1){
            //排序,从小到大
            Collections.sort(nodes);
            //取出第一棵最小的二叉树
            HuffmanNode leftNode = nodes.get(0);
            //取出第一棵最小的二叉树
            HuffmanNode rightNode = nodes.get(1);
            //创建一棵新的二叉树，它的根节点 没有 data ， 只有权值
            HuffmanNode parent = new HuffmanNode(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //将已经处理的两棵二叉树从nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //将新的二叉树，加入到nodes
            nodes.add(parent);
        }

        //nodes 最后的节点，就是哈夫曼树的根节点
        return nodes.get(0);
    }


}

//创建Node，带数据和权值
class HuffmanNode implements Comparable<HuffmanNode> {
    Byte data; //存放数据本身，用ASCII码，a是97，空格" "是32
    int weight; //权值，表示字符出现的次数
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        //从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "(data = " + data + ",weight = " + weight + ")";
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
}
