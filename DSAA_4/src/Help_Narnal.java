import java.util.Scanner;

public class Help_Narnal {

    static class Node {
        private int value;
        private Node next;
        private Node prev;

        public Node(int value) {
            this.value = value;
        }
    }

    private static Node head;


    public static void addNode(int value, Node node) {//需要插入位置的后一个节点
        Node newNode = new Node(value);
        if (node == head) {
            newNode.next = node;
            node.prev = newNode;
            head = newNode;
        } else {
            node.prev.next = newNode;
            newNode.next = node;
            newNode.prev = node.prev;
            node.prev = newNode;
        }
    }

    public static Node deleteNote(Node node) {//类似键盘上的del而不是backspace
        if (node.next != null) {
            if (node == head) {
                head = node.next;
                head.prev = null;
                return head;
            }
            node.prev.next = node.next;
            node.next.prev = node.prev;
            return node.next;
        }
        return node;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int n = in.nextInt();
            String str = in.next();
            char[] input = str.toCharArray();
            head = new Node(-1);
            Node temp = head;
            Node tail;
//            try {
            for (int i = 0; i < n; i++) {
                switch (input[i]) {
                    case 'r':
                        if (i == n - 1) continue;
                        if (temp.value == -1) {
                            tail = new Node(-1);
                            temp.next = tail;
                            tail.prev = temp;
                        }
                        temp.value = input[++i] - 48;
                        break;
                    case 'I':
                        temp = head;
                        break;
                    case 'H':
                        if (temp.prev != null)
                            temp = temp.prev;
                        break;
                    case 'L':
                        if (temp.next != null)
                            temp = temp.next;
                        break;
                    case 'x':
                        temp = deleteNote(temp);
                        break;
                    default:
                        addNode(input[i] - 48, temp);
                }
            }
//            } catch (Exception ignored) {
//            }
            Node ans = head;
            if (ans == null) {
                System.out.println();
                continue;
            }
            while (ans.next != null) {
                if (ans.value != -1)
                    System.out.print(ans.value);
                ans = ans.next;
            }
            System.out.println();
        }
    }
}

