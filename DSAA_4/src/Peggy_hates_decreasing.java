import java.io.*;
import java.math.*;
import java.util.*;

//递归删除每个递增子序列的头尾（除第一个和最后一个）
//接近5层if else我已经晕了，，，解决办法是先在草稿上手写逻辑层次，然后拿样例去手动跑
public class Peggy_hates_decreasing {
    static class Node {
        int val;
        Node next;
        Node prev;

        public Node(int val) {
            this.val = val;
        }
    }

    static class Node2 {
        Node node;
        Node2 next;
        Node2 prev;

        public Node2(Node node) {
            this.node = node;
        }
    }

    public static void addNode(Node node, Node newNode) {
        newNode.next = node.next;
        node.next = newNode;
        newNode.prev = node;
        newNode.next.prev = newNode;
    }

    public static void addNode2(Node2 node, Node2 newNode) {
        if (node == null) {
            head2.prev = newNode;
            newNode.next = head2;
            head2 = newNode;
            return;
        }
        if (node.next == null) {
            node.next = newNode;
            newNode.prev = node;
            return;
        }
        newNode.next = node.next;
        node.next = newNode;
        newNode.prev = node;
        newNode.next.prev = newNode;
    }

    public static void deleteNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public static Node2 deleteNode2(Node2 node2) {
        if (node2.next != null) {
            if (node2 == head2) {
                head2 = node2.next;
                head2.prev = null;
                return head2;
            }
            node2.prev.next = node2.next;
            node2.next.prev = node2.prev;
            return node2.next;
        }
        if (node2.prev == null) {
            head2 = null;
            return null;
        }
        node2.prev.next = null;
        return null;
    }

    private static Node2 head2;

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Task {
        public void solve(InputReader in, PrintWriter out) {
            int T = in.nextInt();
            for (int t = 0; t < T; t++) {
                int n = in.nextInt();
                Node head = new Node(0);
                Node tail = new Node(Integer.MAX_VALUE);
                head.next = tail;
                tail.prev = head;
                Node temp = head;
                for (int i = 0; i < n; i++) {
                    addNode(temp, new Node(in.nextInt()));
                    temp = temp.next;
                }
                //一个辅助的链表(node2)存储每个递增子序列的尾部
                temp = head;
                Node2 temp2 = null;
                while (temp.next != tail) {
                    if (temp.val > temp.next.val) {
                        if (head2 == null) {
                            head2 = new Node2(temp);
                            temp2 = head2;
                        } else {
                            assert temp2 != null;
                            addNode2(temp2, new Node2(temp));
                            temp2 = temp2.next;
                        }
                    }
                    temp = temp.next;
                }

                while (head2 != null) {//跳出条件是head2开头的链表为空（不断删除）
                    temp2 = head2;
                    while (temp2 != null) {//跳出条件是遍历完了一遍head2开头的链表（从左到右）
                        if (temp2.node.val > temp2.node.next.val) {
                            if (temp2.next != null && temp2.node.next == temp2.next.node) {
                                addNode2(temp2.prev, new Node2(temp2.node.prev));
                                while (temp2 != null && temp2.next != null && temp2.next.node == temp2.node.next && temp2.node.val > temp2.node.next.val) {
                                    deleteNode(temp2.node);
                                    temp2 = deleteNode2(temp2);
                                }
                                if (temp2.prev.prev != null && temp2.prev.prev.node == temp2.prev.node) {//防止上面的add加了重复的节点
                                    deleteNode2(temp2.prev);
                                }
                                if (temp2.next == null || temp2.node.next == null || temp2.next.node != temp2.node.next) {//如果node的下一个不是辅助链表中的节点
                                    if (temp2.node.val > temp2.node.next.val) {
                                        deleteNode(temp2.node.next);
                                    }
                                }
                                deleteNode(temp2.node);
                                temp2 = deleteNode2(temp2);
                            } else {
                                if (temp2.prev == null || temp2.node.prev != temp2.prev.node) {
                                    temp2.node = temp2.node.prev;
                                    if (temp2.node.next.next != tail)
                                        deleteNode(temp2.node.next.next);
                                    deleteNode(temp2.node.next);
                                    temp2 = temp2.next;
                                } else {
                                    deleteNode(temp2.node.next);
                                    deleteNode(temp2.node);
                                    temp2 = deleteNode2(temp2);
                                }
                            }
                        } else
                            temp2 = deleteNode2(temp2);
                    }
                }
                temp = head.next;
                while (temp != tail) {
                    out.print(temp.val);
                    out.print(" ");
                    temp = temp.next;
                }
                out.println();

            }
        }
    }


    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] nextCharArray() {
            return next().toCharArray();
        }

        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch (IOException e) {
                return false;
            }
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecinal() {
            return new BigDecimal(next());
        }
    }
}
