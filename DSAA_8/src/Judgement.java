import java.io.*;
import java.util.*;
public class Judgement {
    static class Node{
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }
    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int T=in.nextInt();
        label:
        for (int t = 0; t < T; t++){
            int n=in.nextInt();
            Node[] nodes=new Node[n];
            int[] ys=new int[n];
            for (int i = 0; i < n; i++) {
                nodes[i]=new Node(in.nextInt());
            }
            for (int i = 0; i < n-1; i++) {
                Node x=nodes[in.nextInt()-1];
                int y0=in.nextInt()-1;
                Node y=nodes[y0];
                ys[y0]++;
                if (x.left==null)
                    x.left=y;
                else if (x.right==null)
                    x.right=y;
                else {
                    out.printf("Case #%d: NO\n",t+1);
                    continue label;
                }
            }
            int rootIndex = 0;
            for (int i = 0; i < n; i++) {
                if (ys[i]==0){
                    if (rootIndex==0)
                        rootIndex=i;
                    else {
                        out.printf("Case #%d: NO\n",t+1);
                        continue label;
                    }
                }
            }
            Node root=nodes[rootIndex];
            Queue<Node> queue=new LinkedList<>();
            queue.offer(root);
            boolean larger=false;
            if ((root.left!=null&&root.val>=root.left.val)&&(root.right!=null&&root.val>=root.right.val))
                larger=true;
            while (!queue.isEmpty()&&queue.peek()!=null){
                Node temp=queue.poll();
                if (larger){
                    if ((temp.left!=null&&temp.val<temp.left.val)||(temp.right!=null&&temp.val<temp.right.val)){
                        out.printf("Case #%d: NO\n",t+1);
                        continue label;
                    }
                }else {
                    if ((temp.left!=null&&temp.val>temp.left.val)||(temp.right!=null&&temp.val>temp.right.val)){
                        out.printf("Case #%d: NO\n",t+1);
                        continue label;
                    }
                }

                queue.offer(temp.left);
                queue.offer(temp.right);
            }
            if (!queue.isEmpty()){
                while (!queue.isEmpty()&queue.peek()==null){
                    queue.poll();
                }
                if (queue.isEmpty()){
                    out.printf("Case #%d: YES\n",t+1);
                    continue label;
                }
                else {
                    out.printf("Case #%d: NO\n",t+1);
                    continue label;
                }
            }else {
                out.printf("Case #%d: YES\n",t+1);
                continue label;
            }


        }


        out.close();
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
