import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

//开始理解错题意了，不是求所有人的距离和，所有人是同时出发的，求最大的那个就可以了
//无根树的求直径问题（两遍bfs），注意只有在有人的地方更新maxLength，并需要开一个数组存递归中已经遍历过的子节点（a是b的child，b也会是a的child，不加判断会死循环）
public class K_people_travel_on_a_tree {
    public static class Node {
        ArrayList<Node> children;
        boolean hasPeople;
        int depth;
        int value;

        public Node(int value) {
            this.value = value;
            children = new ArrayList<>();
        }
    }

    public static int maxLength = 0;
    public static int maxNode = 1;

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int n = in.nextInt();
            int k = in.nextInt();
            maxLength=0;
            maxNode=1;
            Node[] nodes = new Node[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = new Node(i + 1);
            }
            for (int i = 0; i < n - 1; i++) {
                Node node1 = nodes[in.nextInt() - 1];
                Node node2 = nodes[in.nextInt() - 1];
                node1.children.add(node2);
                node2.children.add(node1);
            }
            for (int i = 0; i < k; i++) {
                nodes[in.nextInt() - 1].hasPeople = true;
            }
            Node root = nodes[0];
            boolean[] hasUsed = new boolean[n];
            bfs(root, 0, hasUsed);
            root = nodes[maxNode - 1];
            maxLength = 0;
            hasUsed = new boolean[n];
            bfs(root, 0, hasUsed);
            System.out.println((maxLength + 1) / 2);
        }
        out.close();
    }

    public static void bfs(Node root, int depth, boolean[] hasUsed) {
        root.depth = depth;
        if (root.hasPeople && depth > maxLength) {
            maxLength = depth;
            maxNode = root.value;
        }
        hasUsed[root.value - 1] = true;
        for (int i = 0; i < root.children.size(); i++) {
            if (!hasUsed[root.children.get(i).value - 1])
                bfs(root.children.get(i), depth + 1, hasUsed);
        }
    }


    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}
