import java.io.*;
import java.util.*;

public class Valentines_Day {
    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);

        int n=in.nextInt();
        int m=in.nextInt();
        City[] cities=new City[n];
        //建图
        for (int i = 0; i < n; i++) {
            cities[i]=new City(i+1);
        }
        for (int i = 0; i < m; i++) {
            int u=in.nextInt();
            int v=in.nextInt();
            if (in.nextInt()==1){
                cities[u-1].children.add(cities[v-1]);
            }else {
                City tmp=new City(-1);
                cities[u-1].children.add(tmp);
                tmp.children.add(cities[v-1]);
            }
        }

        //bfs查询最短路径(sssp)
        Queue<City> queue=new LinkedList<>();
        queue.offer(cities[0]);
        cities[0].color=1;
        while (!queue.isEmpty()){
            City city=queue.poll();
            city.color=2;
            for(City child:city.children){
                if (child.color==0){
                    queue.offer(child);
                    child.color=1;
                    child.degree= city.degree+1;
                }
            }
        }
        if (cities[n-1].degree!=0)
        out.println(cities[n-1].degree);
        else out.println(-1);
        out.close();
    }

    static class City{
        int val;
        ArrayList<City> children;
        int color;//0:white  1:yellow   2:red
        int degree;

        public City(int val) {
            this.val = val;
            children=new ArrayList<>();
            color=0;
            degree=0;
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
