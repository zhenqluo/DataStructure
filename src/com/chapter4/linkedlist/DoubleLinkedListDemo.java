package com.chapter4.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4 = new HeroNode2(4,"林冲","豹子头");

        HeroNode2 newHero = new HeroNode2(6,"宋江~~","及时雨~~");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.list();

//        doubleLinkedList.del(2);
//        doubleLinkedList.del(4);
//        doubleLinkedList.list();
//        doubleLinkedList.update(newHero);
//        doubleLinkedList.list();
    }


}
class DoubleLinkedList{
    private HeroNode2 head = new HeroNode2(0,"","");

    //返回头节点
    public HeroNode2 getHead(){
        return head;
    }
    //遍历摄像链表的方法
    //显示链表（遍历）
    public void list(){
        if (head.next==null){
            System.out.println("链表为空~~");
            return;
        }
        HeroNode2 temp = head.next;
        while (temp != null){
            System.out.println(temp);
            temp=temp.next;
        }
    }
    //添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode){
        HeroNode2 temp = head;
        while (temp != null){
            if(temp.next == null){
                break;
            }
            temp=temp.next;
        }
        temp.next=heroNode;
        heroNode.pre=temp;
    }
    //修改一个节点的内容，可以看到双向链表的节点内容修改和单向链表一样
    public void update(HeroNode2 newHeroNode){
        if(head.next==null){
            System.out.println("链表为空~~~");
        }
        HeroNode2 temp=head.next;
        boolean flag = false;
        while (true){
            if(temp==null){
                break;
            }
            if (temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.name=newHeroNode.name;
            temp.nickname=newHeroNode.nickname;
        }else {
            System.out.printf("没找到编号%d的英雄\n",newHeroNode.no);
        }
    }
    //从双向链表中删除一个节点
    public void del(int no){
        if (head.next == null){
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true){
            if (temp == null){
                break;
            }
            if (temp.no == no){
                flag = true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.pre.next = temp.next;
            if (temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.printf("没找到编号为%d的节点，无法删除~~",no);
        }
    }
    //按编号顺序添加
    public void addByOrder(HeroNode2 heroNode){
        HeroNode2 temp = head;
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no > heroNode.no){
                break;
            }
            if (temp.next.no ==  heroNode.no){
                throw new RuntimeException("已有该编号%的节点，无法再次添加~~");
            }
            temp=temp.next;
        }
        if (temp.next != null){
            heroNode.next=temp.next;
            temp.next.pre = heroNode;
            temp.next=heroNode;
            heroNode.pre=temp;
        }else {
            temp.next=heroNode;
            heroNode.pre=temp;
        }
    }
}


class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    //构造器
    public HeroNode2(int no,String name,String nickname){
        this.no=no;
        this.name=name;
        this.nickname=nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
