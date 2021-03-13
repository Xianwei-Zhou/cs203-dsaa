import java.io.*;
import java.math.*;
import java.util.*;


//寻找每一个长度大于k的子序列中第k大的数与最大的数
//这题思维量很大，很多地方不好理解，非常感谢lyc的代码
//写的有点烂，，，多加了个position属性，事实上可以不要的（目的是找到比当前数大的上（下）一个数的索引，我的方法事实上是正确思路下的暴力做法
public class Mathematician_Long_Aotian {

    public static void main(String[] args) {
        InputStream inputStream = System.in;// new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Node {
        int val;
        int pos;//输入的val的位置
        Node next;
        Node prev;

        public Node(int val, int pos) {
            this.val = val;
            this.pos = pos;
        }

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node head;
    public static Node tail;

    public static void addNode(Node node) {
        node.next = tail;
        node.prev = tail.prev;
        node.prev.next = node;
        tail.prev = node;
    }

    public static void deleteNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    static class Task {
        public void solve(InputReader in, PrintWriter out) {
            int T = in.nextInt();
            for (int t = 0; t < T; t++) {
                long sum = 0;
                int n = in.nextInt();
                int k = in.nextInt();
                head = new Node(0, -1);
                tail = new Node(Integer.MAX_VALUE, n);
                head.next = tail;
                tail.prev = head;
                Node[] nodes = new Node[n];
                for (int i = 0; i < n; i++) {
                    Node node = new Node(in.nextInt());
                    nodes[node.val - 1] = node;
                    addNode(node);
                    node.pos = i;
                }
                long[] left = new long[k];
                long[] right = new long[k];
                // n个数分别扫描左右k-1个
                for (int i = 0; i < n; i++) {
                    Node currentNode = nodes[i];
                    Node temp = currentNode;
                    int max = -1;
                    int l = 0;
                    int r = 0;
                    if (k != 1) {
                        while (temp.prev != head) {
                            temp = temp.prev;
                            if (max < temp.val)
                                max = temp.val;
                            left[l + 1] = max;
                            l += 1;
                            if (l == k - 1)
                                break;
                        }
                        temp = currentNode;
                        max = -1;
                        while (temp.next != tail) {
                            temp = temp.next;
                            if (max < temp.val)
                                max = temp.val;
                            right[r + 1] = max;
                            r += 1;
                            if (r == k - 1)
                                break;
                        }
                    }
                    left[0] = currentNode.val;
                    right[0] = currentNode.val;
                    temp = currentNode;
                    //对两边都要取值共取k-1个(最大值的逻辑和思路挺复杂的），起点确定了终点也就确定了
                    Node le = temp;
                    Node ri = temp;
                    //这里可以删了，用上面的那个temp就行了，懒得改了
                    for (int j = 0; j < r; j++) {
                        ri = ri.next;
                    }
                    for (int j = 0; j < l + 1; j++) {
                        if (r + j + 1 >= k) {//想明白这句话是干什么的（右边数不够，只移左边不移右边）  尺取
                            long ma = Math.max(left[j], right[k - j - 1]);
                            sum = (sum + (temp.val * ma * (le.pos - le.prev.pos) * (ri.next.pos - ri.pos)) % 1000000007) % 1000000007;

                            ri = ri.prev;
                            if (le == head || ri == currentNode.prev)
                                break;
                        }
                        le = le.prev;
                    }

                    deleteNode(temp);
                }
                out.println(sum);
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
