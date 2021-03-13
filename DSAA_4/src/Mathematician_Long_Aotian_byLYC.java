import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Mathematician_Long_Aotian_byLYC {

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int T = in.nextInt();
        long con = 1;
        for(int i = 0;i<9;i++){
            con*=10;
        }
        con+=7;
        for(int i =0;i<T;i++){
            int n = in.nextInt();
            int k = in.nextInt();
            long[] a = new long[n];
            long[] b = new long[n];
            long[] prev = new long[n];
            long[] next = new long[n];
            for(int j = 0;j<n;j++){
                a[j] = in.nextInt();
                b[(int)(a[j]-1)] = j;
                prev[j] = j-1;
                next[j] = j+1;
            }


            long ans = 0;
            for (int j = 0; j < n; j++) {
                int c1 = 0;
                int c2 = 0;
                long[][] l1 = new long[k][2];
                long[][] l2 = new long[k][2];
                long max = a[(int)b[j]];
                for (int m = (int)b[j]; m >= 0; m = (int)prev[m]) {
                    l1[c1][0] = m - prev[m];
                    if(a[m]>max){
                        max = a[m];
                    }
                    l1[c1][1] = max;
                    c1++;
                    if (c1 == k) {
                        break;
                    }
                }
                max = a[(int)b[j]];
                for (int m = (int)b[j]; m < n; m = (int)next[m]) {
                    l2[c2][0] = next[m] - m;
                    if(a[m]>max){
                        max = a[m];
                    }
                    l2[c2][1] = max;
                    c2++;
                    if (c2 == k) {
                        break;
                    }
                }
                for(int m = 0;m<c1;m++){
                    if(k-m<=c2){
                        if(l1[m][1]< l2[k-m-1][1]){
                            ans=(ans+(a[(int)b[j]]*l2[k-m-1][1]*l1[m][0]*l2[k-m-1][0])%con)%con;
                        }else{
                            ans=(ans+(a[(int)b[j]]*l1[m][1]*l2[k-m-1][0]*l1[m][0])%con)%con;
                        }
                    }
                }
                if(prev[(int)b[j]]!=-1){
                    next[(int)prev[(int)b[j]]] = next[(int)b[j]];
                }
                if(next[(int)b[j]]!=n){
                    prev[(int)next[(int)b[j]]] = prev[(int)b[j]];
                }
//out.println(ans);
            }
            out.println(ans);
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

