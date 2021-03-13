import java.util.Scanner;

//约瑟夫问题，最简单的方法是用arraylist去做，最关键的代码是index = (index+m)%arraylist.size();arraylist.remove(index);
//这节课是链表所以还是用了链表来写，于是出了各种bug
public class Eating_Ordering {
    //要建一个循环链表
    static class Node {
        private int number;
        private Node next;

        public Node(int number) {//构造方法
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private static Node head = null;
    private static Node tail = null;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            tail = null;
            head = null;
            int n = in.nextInt();
            int k = in.nextInt();
            if (n == 1) {
                System.out.println(n);
                continue;
            }
            for (int i = n; i > 0; i--) {
                addNode(new Node(i));
            }
            Node temp = tail.next;
            while (temp.getNumber()!=-1) {
                //开始用了try catch 的原因是在下面delete方法删除最后一个节点时，只能把当前节点的下一个节点设为null而操作不了本节点，在这个循环里面把这个节点继续赋值一次next成null
//                try {
                    for (int i = 0; i < k - 1; i++) {
                        temp = temp.getNext();
                    }
                    System.out.print(temp.getNumber() + " ");
                    deleteNode(temp);
//                } catch (Exception ignored) { }
            }
            System.out.println();
        }
    }


    //头部插入与尾部插入都可以写成O(1)，这里用的是头部插入（最新的元素是head），开始写了一个比较丑的O(n)的尾部插入方法在后面
    public static void addNode(Node newNode) {
        if (tail == null) {
            tail = newNode;
            newNode.setNext(newNode);
        } else {//顺序不能反
            tail.setNext(newNode);
            newNode.setNext(head);
        }
        head = newNode;
    }

    //O(1)方法删除给定节点，下面有一个O(n)的删除元素的方法但是tle
    //思路是把下一个节点复制到这个节点，然后删除下一个节点
    //事实上也可以使用建两个temp分别记录当前节点和上一个节点的方法去删除当前节点，但是比较麻烦需要输入两个值
    //在这题中由于用的是循环链表不存在没有下一个节点的情况，所以用前者的方法很好处理（删掉头尾也没关系，不需要任何操作）
    public static void deleteNode(Node delNode) {//直接输入节点而不是节点的值
        if (delNode.getNext() != delNode) {//节点数大于1
            delNode.setNumber(delNode.getNext().getNumber());
            delNode.setNext(delNode.getNext().getNext());
        } else {
            delNode.setNumber(-1);//不用null了，太容易空指针了
        }
    }


    //尾部新增节点的算法，O(n)复杂度，tle
//    public static void addNode(int number) {
//        if (head == null)
//            head = new Node(number);
//        else {
//            Node temp = head;
//            while (temp.getNext() != head) {//找到当前链表的最后一个元素
//                temp = temp.getNext();
//            }
//            temp.setNext(new Node(number));
//            temp.getNext().setNext(head);
//        }
//    }
//
//    //删除指定节点,O(n)复杂度，tle。需要换用O(1)复杂度的
//    public static void deleteNode(int number) {//无重复元素所以可以这么做
//        Node temp = head;
//        while (true) {
//            if (temp.getNext().getNumber() == number) {
//                if (temp == temp.getNext()) {
//                    /*  delete函数里面我写了temp.setNext(null);和temp=null;
//                    但实际上这两句话唯一的作用是把temp的next指针指向了一个新的null的节点。
//                    前一句不会把temp.next本来的值(temp)赋值成null，后一句我也只是把temp存储的那个地址改成了null但是地址里的内容（原链表）没变*/
//                    temp.setNext(null);
//                    temp=null;
////                    System.out.println(temp.getNumber());//print "4"
////                    try {
////                        System.out.println(temp.getNext().getNumber());//print "null"
////                    }catch (Exception e){
////                        System.out.println("null");
////                    }
//                    break;
//                }
//                if (temp.getNext() == head) {
//                    temp.setNext(temp.getNext().getNext());
//                    head = temp.getNext();//上一行已经改变了指针
//                } else temp.setNext(temp.getNext().getNext());
//                break;
//            }
//            temp = temp.getNext();
//        }
//    }
}
