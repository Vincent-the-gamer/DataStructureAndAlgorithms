package huffmantree;

import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
//        System.out.println("压缩前长度：" + contentBytes.length); //40

        byte[] huffmanCodesBytes = huffmanZip(contentBytes);
//        System.out.println("压缩后的结果是：" + Arrays.toString(huffmanCodesBytes) + ", 长度=" + huffmanCodesBytes.length); //长度为17
       byte[] sourceBytes = decode(huffmanCodes,huffmanCodesBytes);
        System.out.println("原来的字符串: " + new String(sourceBytes));
        //分步过程
//        List<HuffmanNode> nodes = getNodes(contentBytes);
//        System.out.println("nodes = " + nodes);
//
//        //测试：创建的二叉树
//        System.out.println("哈夫曼树：");
//        HuffmanNode huffmanTreeRoot = createHuffmanTree(nodes);
//        //调用前序遍历
//        huffmanTreeRoot.frontOrder();
//
//        //测试，是否生成了对应的哈夫曼编码
//        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
//        System.out.println("生成的哈夫曼编码表是：" + huffmanCodes);
//        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
//        System.out.println("哈夫曼编码后的字节数组: " + Arrays.toString(huffmanCodeBytes) );
    }

    //编写一个方法，完成对压缩数据的解码

    /**
     *
     * @param huffmanCodes 哈夫曼编码 map
     * @param huffmanBytes 哈夫曼编码得到的字节数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes){
        //1. 先得到 huffmanBytes 对应的 二进制的字符串，形式为：1010100010...
        StringBuilder sb = new StringBuilder();
        //将byte数组转成二进制的字符串
        for(int i = 0;i < huffmanBytes.length; i++){
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            sb.append(byteToBitString(!flag,b));
        }
       //把字符串按照指定的哈夫曼编码进行解码
        //把哈夫曼编码表进行调换，因为要反向查询： a => 100 变成 100 => a
        Map<String, Byte> map = new HashMap<String,Byte>();
        for(Map.Entry<Byte,String> entry: huffmanCodes.entrySet()){
            //反向放key value
            map.put(entry.getValue(), entry.getKey());
        }
        //创建一个集合，存放byte
        List<Byte> list = new ArrayList<>();
        //i就是一个索引，扫描stringBuilder（二进制字符串）
        for(int i=0;i < sb.length(); ){
            int count = 1; //小的计数器
            boolean flag = true;
            Byte b = null;

            while(flag){
                //暴力匹配不同个字符，直到找到一个匹配的
                String key = sb.substring(i, i+count); //i不动，让count移动，直到匹配一个字符
                b = map.get(key);
                if(b == null){ //说明没有匹配到
                    count++;
                }
                else{
                    //匹配到, 退出循环
                    flag = false;
                }
            }
            list.add(b);
            i += count; //让i 直接移动到 count
        }
        // 当for循环结束后，我们list中就存放了所有的字符: i like like like java do you like a java
        //把list 中的数据放入到byte[] 并返回
        byte[] b = new byte[list.size()];
        for(int i = 0; i < b.length; i++){
            b[i] = list.get(i);
        }
        return b;
    }

    //如何将数据进行解压（解码）
        /*
        思路：
        1. 将哈夫曼编码后的数组 重新转成 哈夫曼编码对应的二进制字符串
        2. 将 哈夫曼编码对应的二进制字符串 对照 哈夫曼编码 重新转成"i like like like java do you like a java"
         */
    /**
     * 将一个 byte 转成一个二进制字符串
     * @param flag 标识是否需要补高位，如果 true 表示需要补高位，false不需要补高位
     * @param b 传入的byte
     * @return 是该 b 对应的二进制字符串，（注意是按补码返回）
     */
    private static String byteToBitString(boolean flag, byte b){
        //使用变量保存b
        int temp = b; //把b转成int
        //如果是正数，他的补码和原码一样，需要补高位, 如果是最后一个字节，无需补高位
        if(flag){
            temp |= 256; //按位或： 256 | 1  => 1 0000 0000 | 0000 0001 == 1 0000 0001
        }
        String str = Integer.toBinaryString(temp);  //返回的是temp对应的二进制的补码
        if(flag){
            return str.substring(str.length() - 8); //取字符串最后8位
        }
        else {
            return str;
        }
    }

    //使用一个方法，将前面的方法封装起来，便于我们的调用
    /**
     * 哈夫曼压缩（编码过程）
     * @param bytes 原始的字符串对应的字节数组
     * @return 经过哈夫曼编码处理后的字节数组（压缩后的数组）
     */
    private static byte[] huffmanZip(byte[] bytes){
        List<HuffmanNode> nodes = getNodes(bytes);
        //根据 nodes 创建哈夫曼树
        HuffmanNode huffmanTreeRoot = createHuffmanTree(nodes);
        //生成对应的哈夫曼编码（根据哈夫曼树）
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据生成的哈夫曼编码，压缩得到压缩后的哈夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);

        return huffmanCodeBytes;
    }

    //编写一个方法，将字符串对应的 byte[] 数组，通过生成的哈夫曼编码表，返回一个哈夫曼编码压缩后的字节 byte 数组

    /**
     * @param bytes        这是原始的字符串对应的byte[]
     * @param huffmanCodes 生成的哈夫曼编码map
     * @return 返回哈夫曼编码处理后的byte数组
     * 举例： String content = "i like like like java do you like a java";
     * byte[] contentBytes = content.getBytes();
     * 返回的是： 二进制哈夫曼编码后的字符串 “1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100”
     * 字符串对应的byte数组 byte[] huffmanCodeBytes，即8位对应一个byte，放入到 byte 数组中
     * huffmanCodeBytes[0] = 10101000（负数的补码) => 推出反码（反码 = 补码 - 1) 10101000 - 1 = 10100111 =>
     * 原码 = 符号位不变，其它取反： 11011000
     * 所以结果应该是：huffmanCodeBytes[0] == -88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1.利用 huffmanCodes 将 bytes 转成哈夫曼编码后的二进制字符串
        StringBuilder sb = new StringBuilder();
        //遍历 bytes 数组
        for (byte b : bytes) {
            sb.append(huffmanCodes.get(b));
        }
        //将对应字符串转成byte数组 byte[] huffmanCodeBytes
        // 统计 byte[] huffmanCodeBytes 的长度
        // 一句话搞定的话： int len = (sb.length() + 7) / 8;
        int len;
        if (sb.length() % 8 == 0) {
            len = sb.length() / 8;
        } else {
            len = sb.length() / 8 + 1;
        }
        //创建 存储压缩后的 哈夫曼码byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0; //记录是第几个byte

        for (int i = 0; i < sb.length(); i += 8){//因为每8位对应一个byte，所以步长 + 8
            String strByte;
            if(i + 8 > sb.length()){ //如果某一段不够8位
                strByte = sb.substring(i);
            }
            else{
                strByte = sb.substring(i, i + 8);
            }
            //将strByte 转成一个byte，放入到 huffmanCodeBytes
            huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte,2);
            index++;
        }
        return huffmanCodeBytes;
    }

    //生成哈夫曼树对应的哈夫曼编码
    /*
    思路：
    1. 将哈夫曼编码表存放在 Map<Byte,String> 形式：
       32 => 01 97 => 100  10 => 11000 等
    */
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    /*
    2. 在生成哈夫曼编码表时，需要去拼接路径，定义一个 StringBuilder 存储某个叶子节点的路径
     */
    static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便，我们重载getCodes
    private static Map<Byte, String> getCodes(HuffmanNode root) {
        if (root == null) {
            return null;
        }
        //处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        //处理root的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 功能：将传入的node节点的所有叶子节点的哈夫曼编码得到，并放入到huffmanCode集合中
     *
     * @param node          传入的节点
     * @param code          路径的值（左子节点是0，右子节点是1）
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(HuffmanNode node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code 加入到 stringBuilder2
        stringBuilder2.append(code);
        if (node != null) { //如果node == null不处理
            //判断当前 node 是叶子节点还是非叶子节点
            if (node.data == null) { //非叶子节点
                //递归处理
                //向左
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else { //说明是一个叶子节点
                //就表示找到某个叶子节点
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }

        }
    }

    //前序遍历
    private static void frontOrder(HuffmanNode root) {
        if (root != null) {
            root.frontOrder();
        } else {
            System.out.println("哈夫曼树为空");
        }
    }

    /**
     * @param bytes 接收字节数组
     * @return 返回的就是List形式
     */
    private static List<HuffmanNode> getNodes(byte[] bytes) {
        //1.创建一个Arraylist
        List<HuffmanNode> nodes = new ArrayList<>();

        //遍历bytes，统计 每一个byte出现的次数 用map存放
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) { //Map还没有这个字符数据
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        //把每一个键值对转成一个 HuffmanNode对象，并加入到nodes集合
        //遍历map(遍历键值对，键值对是一个整体，每一个元素就是一对键值对）
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //通过 List 创建对应的哈夫曼树
    private static HuffmanNode createHuffmanTree(List<HuffmanNode> nodes) {
        while (nodes.size() > 1) {
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
    public void frontOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.frontOrder();
        }
        if (this.right != null) {
            this.right.frontOrder();
        }
    }
}
