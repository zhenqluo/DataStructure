package com.chapter4.linkedlist;

public class SigleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");

        SigleLinkedList sigleLinkedList = new SigleLinkedList();

        sigleLinkedList.addByOrder(hero1);
        sigleLinkedList.addByOrder(hero2);
        sigleLinkedList.addByOrder(hero3);
        sigleLinkedList.addByOrder(hero4);



        //反转
        System.out.println("反转前~~~");
        sigleLinkedList.list();
        SigleLinkedList.reversetList(sigleLinkedList.getHead());
        System.out.println("反转后~~~");
        sigleLinkedList.list();

        int count=SigleLinkedList.getLength(sigleLinkedList.getHead());
        System.out.println("有效节点个数："+count);

        int index =1;
        HeroNode lastIndexNode = SigleLinkedList.findLastIndexNode(sigleLinkedList.getHead(), index);
        System.out.printf("倒数第%d个节点是：%s",index,lastIndexNode);
/*
        sigleLinkedList.list();
        System.out.println("删除节点~~~");
        sigleLinkedList.del(4);
        sigleLinkedList.list();

        HeroNode newHeroNode = new HeroNode(3, "吴用~", "智多星~~");
        sigleLinkedList.update(newHeroNode);
        sigleLinkedList.list();
*/

    }
}
class SigleLinkedList{
    //先初始化一个头节点，头节点不能动，不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路：当不考虑编号顺序时，1.找到当前链表的最后节点；2.将最后这个节点的next指向新节点
    public void add(HeroNode heroNode){
        HeroNode temp = head;
        while (true){
            if(temp.next==null){
                break;
            }
            temp=temp.next;
        }
        temp.next=heroNode;
    }
    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //（如果有这个排名，则添加失败，并给出提示）
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;
        while (temp.next != null){
            if(temp.next.no > heroNode.no){
                break;
            }
            if(temp.next.no == heroNode.no){
                flag = true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            System.out.println("队列已有该编号节点，不可再次添加~~~");
            return;
        }
        heroNode.next=temp.next;
        temp.next=heroNode;
    }
    //修改节点的信息，根据no编号来修改，即no编号不能改
    //说明：1.根据newHeroNode的no来修改即可
    public void update(HeroNode newHeroNode){
        if(head.next == null){
            System.out.println("链表为空~~");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;
        while (true){
            if (temp == null){
                break;
            }
            if (temp.no == newHeroNode.no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.name=newHeroNode.name;
            temp.nickname=newHeroNode.nickname;
        }else {
            System.out.printf("没有找到编号%d的节点，不能修改\n",newHeroNode.no);
        }

    }
    //删除节点
    //思路
    //1.head不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
    //2.说明我们在比较时，是temp.next.no和需要删除的节点的no比较
    public void del(int no){
        if (head.next == null){
            System.out.println("队列为空~~");
            return;
        }
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.next=temp.next.next;
        }else {
            System.out.printf("没有找到编号%d的节点，不能修改\n",no);
        }
    }

    //显示链表[遍历]
    public void list(){
        if(head.next == null){
            System.out.println("链表为空~~~~");
            return;
        }
        HeroNode temp= head.next;
        while (temp != null){
            System.out.println(temp);
            temp=temp.next;
        }
    }
    //1)求单链表中有效节点的个数
    /**
     * @param head 链表的头节点
     * @return 返回的是有效节点的个数
     */
    public static int getLength(HeroNode head){
        if (head.next==null){
            System.out.println("链表为空~~~~");
            return 0;
        }
        int count=0;
        HeroNode temp=head.next;
        while (true){
            if(temp == null){
                break;
            }else{
                count++;
            }
            temp=temp.next;
        }
        return count;
    }
    //2)查找单链表中的倒数第k个节点
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        if (head.next == null){
            return null;
        }
        int count = getLength(head);
        if (index<0 || index>count){
            return null;
        }
        HeroNode temp = head.next;
        for(int i=0;i<count-index;i++){
            temp=temp.next;
        }
        return temp;
    }
    //单链表的反转
    //思路：新建一个头节点，遍历原链表，每遍历一个节点取出插入到新头节点后一个位置
    public static void reversetList(HeroNode head){
        if (head.next==null || head.next.next==null){
            return;
        }
        HeroNode newHead = new HeroNode(0,"","");
        HeroNode temp = head.next;//遍历指针
        HeroNode cur;
        while (true){
            if (temp == null){
                break;
            }
            //在改变当前节点指针指向前必须先把遍历指针指向下一个节点
            cur=temp;
            temp=temp.next;
            cur.next=newHead.next;
            newHead.next=cur;
        }

        head.next=newHead.next;
        head=newHead;//为啥这种写法不行？
    }
}
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    //构造器
    public HeroNode(int no,String name,String nickname){
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
