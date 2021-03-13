import java.io.*;
import java.util.*;

//通过入度表进行拓扑排序，每次找到入度为0的入队（都在队列里的元素顺序任意），队列非空时将队首元素出队并且其子节点入度均-1，然后将入度为0的子节点入队
public class Troublesome_Long_Queue {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);

        int n=in.nextInt();
        int m=in.nextInt();

        Node[] nodes=new Node[n];
        int[] degrees=new int[n];

        for (int i = 0; i < n; i++) {
            nodes[i]=new Node(i+1);
        }
        for (int i = 0; i < m; i++) {
            int a=in.nextInt();
            int b=in.nextInt();
            nodes[a-1].children.add(nodes[b-1]);
            degrees[b-1]++;
        }
        PriorityQueue<Node> heap=new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                //这是字典序的比较，题目有歧义 1 12 2 3比1 2 3 12字典序大（把每个node当成一个字符，node.val当成node的编码）
//                return Integer.toString(o1.val).compareTo(Integer.toString(o2.val));
                return o1.val-o2.val;
            }
        });
        for (int i = 0; i < n; i++) {
            if (degrees[i]==0)
                heap.add(nodes[i]);
        }
        int[] sortedNumbers=new int[n];
        int j=0;
        while (!heap.isEmpty()){
            Node node=heap.poll();
            sortedNumbers[j++]=node.val;
            for (int i = 0; i < node.children.size(); i++) {
                if (--degrees[node.children.get(i).val-1]==0){
                    heap.add(node.children.get(i));
                }
            }
        }
        for (int i = 0; i < n; i++) {
            out.print(sortedNumbers[i]+" ");
        }

        out.close();
    }
    static class Node{
        int val;
        ArrayList<Node> children;

        public Node(int val) {
            this.val = val;
            children=new ArrayList<>();
        }
    }
    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
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

        public double nextDouble() throws IOException
        {
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

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }

}
