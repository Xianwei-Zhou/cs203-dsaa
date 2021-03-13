import java.io.*;
import java.math.*;
import java.util.*;

//块状链表（查找插入，但没写删除）
//可以把list直接连起来，，，不要再加一个node。。。
class Node {
    Node next;
    list list;

    public Node(list list) {
        this.list = list;
    }
}

class Node2 {
    char val;
    Node2 next;

    public Node2(char val) {
        this.val = val;
    }
}

class list {
    Node2 head;
    int size;

    public list() {
        this.size = 0;
    }

    public list(Node2 head, int size) {
        this.head = head;
        this.size = size;
    }

    public void addNode2(Node2 node2, Node2 newNode) {
        if (node2 == null) {
            newNode.next = head;
            head = newNode;
        } else {
            newNode.next=node2.next;
            node2.next = newNode;
        }
        size += 1;
    }
}

public class String_operations {
    public static void main(String[] args) {
        InputStream inputStream = System.in;// new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    public static Node head;

    public static void addNode(Node node, Node newNode) {
        if (node == null)
            head = newNode;
        else {
            newNode.next=node.next;
            node.next = newNode;
        }
    }

    static class Task {
        public void solve(InputReader in, PrintWriter out) {
            int T = in.nextInt();
            for (int t = 0; t < T; t++) {
                head=null;
                char[] base = in.next().toCharArray();
                int length = base.length;
                int sqrt = (int) Math.sqrt(length);
                int min = sqrt / 2;
                int max = sqrt * 2;
                Node temp = null;
                Node current;
                for (int i = 0; i < length / sqrt; i++) {
                    list list = new list();
                    Node2 temp2 = null;
                    for (int j = 0; j < sqrt; j++) {
                        Node2 current2;
                        current2 = new Node2(base[i * sqrt + j]);
                        list.addNode2(temp2, current2);
                        temp2 = current2;
                    }
                    current = new Node(list);
                    addNode(temp, current);
                    temp = current;
                }
                list list = new list();
                current = new Node(list);
                addNode(temp, current);
                Node2 temp2 = null;
                for (int i = (length/sqrt-1)*sqrt+sqrt; i < length; i++) {
                    Node2 current2 = new Node2(base[i]);
                    list.addNode2(temp2, current2);
                    temp2 = current2;
                }

                int n = in.nextInt();
                for (int i = 0; i < n; i++) {
                    if (in.nextInt() == 1) {
                        char ch = in.next().charAt(0);
                        int index = in.nextInt();
                        insert(ch, index - 1,max);
                    } else {
                        out.println(find(in.nextInt()).val);
                    }
                }
            }
        }

        public void insert(char ch, int index,int max) {
            Node node=head;
            if (index == 0) {
                node.list.addNode2(null, new Node2(ch));
            } else {
                while (index - node.list.size > 0) {
                    index -= node.list.size;
                    node = node.next;
                }
                Node2 node2 = node.list.head;
                for (int i = 1; i < index; i++) {
                    node2 = node2.next;
                }
                node.list.addNode2(node2,new Node2(ch));
            }
            //单个node的list长度超过max就对半分裂
            if (node.list.size>max){
                Node2 temp=node.list.head;
                for (int i = 0; i < max/2; i++) {
                    temp=temp.next;
                }
                Node2 rightHead=temp.next;
                temp.next=null;
                list right=new list(rightHead,node.list.size-max/2-1);
                node.list.size=max/2+1;
                addNode(node,new Node(right));
            }
        }

        public Node2 find(int index) {
            Node node = head;
            while (index - node.list.size > 0) {
                index -= node.list.size;
                node = node.next;
            }
            Node2 node2 = node.list.head;
            for (int i = 1; i < index; i++) {
                node2 = node2.next;
            }
            return node2;
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
