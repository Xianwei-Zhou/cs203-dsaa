import java.io.*;
import java.util.*;

//对a,b节点分别dfs找到ab两边的节点数相乘即可（画个图就清楚了）
public class SUSTech_Tower_Defence {

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);

        int n=in.nextInt();
        int m=in.nextInt();
        int a=in.nextInt();
        int b=in.nextInt();
        Node[] nodes=new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i]=new Node(i);
        }
        for (int i = 0; i < m; i++) {
            int u=in.nextInt();
            int v=in.nextInt();
            nodes[u-1].children.add(nodes[v-1]);
            nodes[v-1].children.add(nodes[u-1]);
        }
        //从a点dfs
        Stack<Node> stack=new Stack<>();
        stack.push(nodes[a-1]);
        nodes[a-1].isVisited=true;
        Node tmp;
        while (!stack.isEmpty()){
            tmp=stack.pop();
            for (Node child:tmp.children){
                if ((!child.isVisited)&&child!=nodes[b-1]){
                    stack.push(child);
                    child.isVisited=true;
                    child.mark+=1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            nodes[i].isVisited=false;
        }

        //从b点dfs
        stack=new Stack<>();
        stack.push(nodes[b-1]);
        nodes[b-1].isVisited=true;
        while (!stack.isEmpty()){
            tmp=stack.pop();
            for (Node child:tmp.children){
                if ((!child.isVisited)&&child!=nodes[a-1]){
                    stack.push(child);
                    child.isVisited=true;
                    child.mark-=1;
                }
            }
        }

        int cntA=0,cntB=0;
        for (int i = 0; i < n; i++) {
            if (nodes[i].mark==1)
                cntA++;
            else if (nodes[i].mark==-1)
                cntB++;
        }
        out.println(cntA*cntB);
        out.close();
    }

    static class Node{
        int val;
        ArrayList<Node> children;
        int mark=0;//1表示a点dfs到的，-1表示b点dfs到的
        boolean isVisited=false;

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