import java.io.*;
import java.util.*;
//数组模拟链表
public class LowbieHs_best_friend_byLYC {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            if(n%2 == 0){
                int[][] a = new int[n-1][2];
                for(int j = 0;j<n-1;j++){
                    a[j][0] = in.nextInt();
                    a[j][1] = j;
                }
                a = mergeSort(a,n-1);
                int[] x = new int[n-1];
                int[] pre = new int[n-1]; pre[a[0][1]] = -1;
                int[] nex = new int[n-1]; nex[a[n-2][1]] = n-1;
                int[] ans = new int[(n+1)/2];
                for(int j = 0;j<n-1;j++){
                    x[a[j][1]] = j;
                }
                for(int j = 1;j<n-1;j++){
                    pre[a[j][1]] = j-1;
                }
                for(int j = 0;j<n-2;j++){
                    nex[a[j][1]] = j+1;
                }
                int mid = (n-1)/2;
                ans[(n-1)/2] = a[mid][0];
                for(int j = n-2;j>=2;j-=2){
                    if(x[j]>=mid && x[j-1]>=mid){
                        mid = pre[a[mid][1]];
                    }else if(x[j]<=mid && x[j-1]<=mid){
                        mid = nex[a[mid][1]];
                    }
                    ans[(j-2)/2] = a[mid][0];
                    if(pre[j]!= -1){
                        nex[a[pre[j]][1]] = nex[j];
                    }
                    if(nex[j]!= n-1){
                        pre[a[nex[j]][1]] = pre[j];
                    }
                    if(pre[j-1]!= -1){
                        nex[a[pre[j-1]][1]] = nex[j-1];
                    }
                    if(nex[j-1]!=n-1){
                        pre[a[nex[j-1]][1]] = pre[j-1];
                    }
                }
                for(int j = 0;j<(n+1)/2;j++){
                    out.print(ans[j]+" ");
                }
            }else{
                int[][] a = new int[n][2];
                for(int j = 0;j<n;j++){
                    a[j][0] = in.nextInt();//输入
                    a[j][1] = j;//输入的index
                }
                a = mergeSort(a,n);//按照第一列升序排列
                int[] x = new int[n];
                int[] pre = new int[n]; pre[a[0][1]] = -1;
                int[] nex = new int[n]; nex[a[n-1][1]] = n;
                int[] ans = new int[(n+1)/2];//答案
                for(int j = 0;j<n;j++){
                    x[a[j][1]] = j;//x[第j大的输入的index]=j
                }
                for(int j = 1;j<n;j++){
                    pre[a[j][1]] = j-1;
                }
                for(int j = 0;j<n-1;j++){
                    nex[a[j][1]] = j+1;
                }
                int mid = (n-1)/2;
                ans[(n-1)/2] = a[mid][0];
                for(int j = n-1;j>=2;j-=2){
                    if(x[j]>=mid && x[j-1]>=mid){
                        mid = pre[a[mid][1]];
                    }else if(x[j]<=mid && x[j-1]<=mid){
                        mid = nex[a[mid][1]];
                    }
                    ans[(j-2)/2] = a[mid][0];
                    if(pre[j]!= -1){
                        nex[a[pre[j]][1]] = nex[j];//a是已经按数字大小排好序的二维数组
                    }//第 第j大的数的前一个的index 个的下一个=第 j 个的下一个
                    if(nex[j]!= n){
                        pre[a[nex[j]][1]] = pre[j];
                    }
                    if(pre[j-1]!= -1){
                        nex[a[pre[j-1]][1]] = nex[j-1];
                    }
                    if(nex[j-1]!=n){
                        pre[a[nex[j-1]][1]] = pre[j-1];
                    }
                }
                for(int j = 0;j<(n+1)/2;j++){
                    out.print(ans[j]+" ");
                }
            }
            out.println();
        }
        out.close();
    }
    public static int[][] mergeSort(int[][] a,int n){
        if(n>1){
            int p = n/2;
            int[][] b = new int[p][2];
            int[][] c = new int[n-p][2];
            for(int i = 0;i<p;i++){
                b[i][0] = a[i][0];
                b[i][1] = a[i][1];
            }
            for(int i = 0;i<(n-p);i++){
                c[i][0] = a[p+i][0];
                c[i][1] = a[p+i][1];
            }
            int[][] bb = mergeSort(b,b.length);
            int[][] cc = mergeSort(c,c.length);
            int[][] d = merge(bb,cc);
            return d;
        }else{
            return a;
        }
    }
    public static int[][] merge(int[][] b,int[][] c){
        int[][] a = new int[b.length+c.length][2];
        int i = 0;
        int j = 0;
        for(int k = 0;k<b.length+c.length;k++){
            if(i<b.length && (j>=c.length || b[i][0]<=c[j][0])){
                a[k][0] = b[i][0];
                a[k][1] = b[i][1];
                i++;
            }else{
                a[k][0] = c[j][0];
                a[k][1] = c[j][1];
                j++;
            }
        }
        return a;
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
            } while ((c = read()) >= '0' && c <= '9');
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