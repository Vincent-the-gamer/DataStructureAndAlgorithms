package linkedlist;

//定义DoubleHeroNode, 每个DoubleHeroNode 对象就是一个节点
public class DoubleHeroNode {
    public int no;
    public String name;
    public String nickname;
    public DoubleHeroNode next; //指向下一个节点，默认为null
    public DoubleHeroNode prev; //指向前一个节点，默认为null

    //构造器
    public DoubleHeroNode(int no,String name,String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方便，我们重写一下toString方法
    @Override
    public String toString(){
        return "HeroNode [no=" + no + ", name="+ name +", nickname=" + nickname+ "]";
    }
}
