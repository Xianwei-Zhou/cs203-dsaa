import java.io.*;

//最大堆
class Node{
    int val;
    int pos;

    public Node(int val) {
        this.val = val;
    }
}
class PQueue{
    Node[] nodes;
    int size;

    public PQueue(int size) {
        nodes=new Node[size];
    }
    public void insert(Node node){
        nodes[size]=node;
        node.pos=size;
        ++size;
        shiftUp();

    }
    public void shiftUp(){
        int k=size;
        while (k>1){
            if (nodes[k-1].val<nodes[k/2-1].val)
                break;
            swap(k-1,k/2-1,this);
            k=k/2;
        }
    }
    public static void swap(int i,int j,PQueue queue){
        Node temp=queue.nodes[i];
        queue.nodes[i]=queue.nodes[j];
        queue.nodes[j]=temp;
        queue.nodes[i].pos=i;
        queue.nodes[j].pos=j;
    }
}
public class Juan_King {
    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int cases=in.nextInt();
        while (cases-->0){
            int n=in.nextInt();
            PQueue pQueue=new PQueue(n);
            Node[] nodes=new Node[n];
            for (int i = 0; i < n; i++) {
                Node node=new Node(in.nextInt());
                pQueue.insert(node);
                nodes[i]=node;
            }
            int pos=nodes[in.nextInt()-1].pos+1;
            int x=(int)(Math.log(pos)/Math.log(2))+1;
            int y=pos-(int)Math.pow(2,x-1)+1;
            System.out.println(x+" "+y);
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
