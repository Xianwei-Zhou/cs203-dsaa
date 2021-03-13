import java.util.*;

class deque {
    dequeNode head;//head和tail只是指向两个node（可以是同一个），在是同一个时也只要删掉那一个node就行
    dequeNode tail;
    int size;

    public deque() {
        size = 0;
    }

    public void addToHead(int val) {
        dequeNode node = new dequeNode(val);
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size += 1;
    }

    public void addToTail(int val) {
        dequeNode node = new dequeNode(val);
        if (isEmpty()) {
            tail = node;
            head = node;
        } else {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
        size += 1;
    }

    public void removeHead() {
        if (isEmpty()) return;
        if (size == 1) {
            clear();
        } else {
            head = head.next;
            head.prev = null;
            size -= 1;
        }
    }

    public void removeTail() {
        if (isEmpty()) return;
        if (size == 1)
            clear();
        else {
            tail = tail.prev;
            tail.next = null;
            size -= 1;
        }
    }

    public int findHead() {
        if (isEmpty()) return -1;
        return head.val;
    }

    public int findTail() {
        if (isEmpty()) return -1;
        return tail.val;
    }

    public void connect(deque v) {
        if (v.isEmpty())
            return;
        if (isEmpty()) {
            head = v.head;
            tail = v.tail;
            size=v.size;
            return;
        }
        tail.next = v.head;
        v.head.prev = tail;
        tail = v.tail;
        size = size + v.size;
    }

    public void reverse() {//双向链表反转
        if (isEmpty())
            return;
        dequeNode pre = null;
        dequeNode next;
        tail = head;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.prev = next;
            pre = head;
            head = next;
        }
        head = pre;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

class dequeNode {
    int val;
    dequeNode next;
    dequeNode prev;

    public dequeNode(int val) {
        this.val = val;
    }
}

public class Skylar_Learning_Deque {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int q = in.nextInt();
            deque[] deques = new deque[n];
            for (int i = 0; i < n; i++) {
                deques[i] = new deque();
            }
            for (int i = 0; i < q; i++) {
                int t = in.nextInt();
                int u = in.nextInt();
                switch (t) {
                    case 1:
                        switch (in.nextInt()) {
                            case 0:
                                deques[u - 1].addToHead(in.nextInt());
                                break;
                            case 1:
                                deques[u - 1].addToTail(in.nextInt());
                                break;
                        }
                        break;
                    case 2:
                        switch (in.nextInt()) {
                            case 0:
                                System.out.println(deques[u - 1].findHead());
                                deques[u - 1].removeHead();
                                break;
                            case 1:
                                System.out.println(deques[u - 1].findTail());
                                deques[u - 1].removeTail();
                                break;
                        }
                        break;
                    case 3:
                        int v = in.nextInt();
                        switch (in.nextInt()) {
                            case 0:
                                deques[u - 1].connect(deques[v - 1]);
                                deques[v - 1].clear();
                                break;
                            case 1:
                                deques[v - 1].reverse();
                                deques[u - 1].connect(deques[v - 1]);
                                deques[v - 1].clear();
                                break;
                        }
                }
            }
        }
    }
}

