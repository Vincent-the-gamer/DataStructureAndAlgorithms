package linkedlist;

//定义HeroNode, 每个HeroNode 对象就是一个节点
public class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点

    //构造器
    public HeroNode(int no,String name,String nickname){
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
