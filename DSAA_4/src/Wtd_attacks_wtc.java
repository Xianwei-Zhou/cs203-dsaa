import java.io.*;
import java.math.*;
import java.util.*;

//建第一个链表就够了，后面的插入这个链表中
//人为多加头尾
public class Wtd_attacks_wtc {
    static class Node {
        int coe;
        int exp;
        Node next;
        public Node(int coe, int exp) {
            this.coe = coe;
            this.exp = exp;
        }
    }

    public static void addNode(Node node, Node newNode) {
        newNode.next = node.next;
        node.next = newNode;
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;// new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
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
            Node head = new Node(0, -1);
            Node tail = new Node(0, Integer.MAX_VALUE);
            for (int t = 0; t < T; t++) {
                int n = in.nextInt();
                Node temp = head;
                head.next = tail;
                for (int i = 0; i < n; i++) {
                    addNode(temp, new Node(in.nextInt(), in.nextInt()));
                    temp = temp.next;
                }
                int m = in.nextInt();
                temp = head;
                for (int i = 0; i < m; i++) {
                    Node node = new Node(in.nextInt(), in.nextInt());
                    while (temp.next.exp <= node.exp) {
                        temp = temp.next;
                    }
                    if (node.exp == temp.exp)
                        temp.coe = temp.coe + node.coe;
                    else
                        addNode(temp, node);
                }

                temp = head.next;


                while (temp.coe == 0&&temp!=tail) {
                    temp = temp.next;
                }
                if (temp==tail){
                    System.out.println(0);
                    continue;
                }
                print0(temp);
                temp=temp.next;
                while (temp != tail) {
                    if (temp.coe != 0)
                        print(temp);
                    temp = temp.next;
                }
                System.out.println();
            }
        }
    }

    public static void print0(Node node) {
        if (node.coe == 1) {
            if (node.exp == 0)
                System.out.print(1);
            else if (node.exp == 1)
                System.out.print("x");
            else System.out.print("x^" + node.exp);
        } else if (node.coe == -1) {
            if (node.exp == 0)
                System.out.print(-1);
            else if (node.exp == 1)
                System.out.print("-x");
            else System.out.print("-x^" + node.exp);
        } else {
            if (node.exp == 0)
                System.out.print(node.coe);
            else if (node.exp == 1)
                System.out.print(node.coe+"x");
            else
                System.out.print(node.coe + "x^" + node.exp);
        }
    }

    public static void print(Node node) {
        if (node.coe > 0)
        System.out.print("+");
        print0(node);
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
