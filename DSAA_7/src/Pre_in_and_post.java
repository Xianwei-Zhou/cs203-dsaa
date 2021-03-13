import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Pre_in_and_post {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int n = in.nextInt();
            int[] pre = new int[n];
            int[] mid = new int[n];
            for (int i = 0; i < n; i++) {
                pre[i] = in.nextInt();
            }
            for (int i = 0; i < n; i++) {
                mid[i] = in.nextInt();
            }
            printPost(pre, mid);
            System.out.println();
        }


        out.close();
    }

    public static String printPost(int[] pre, int[] mid) {
        if (pre.length == 0) return "";
        if (pre.length == 1) {
            System.out.print(pre[0] + " ");
            return "";
        }
        int m = findIndex(pre[0], mid);
        if (m == -1) {
            System.out.println("error");
            return "error";
        }
        int[] a = new int[m];
        System.arraycopy(pre, 1, a, 0, m);
        int[] b = new int[m];
        System.arraycopy(mid, 0, b, 0, m);
        int[] c = new int[pre.length - m - 1];
        System.arraycopy(pre, m + 1, c, 0, pre.length - m - 1);
        int[] d = new int[pre.length - m - 1];
        System.arraycopy(mid, m + 1, d, 0, pre.length - m - 1);
        System.out.print(printPost(a, b) + printPost(c, d) + pre[0] + " ");
        return "";
    }

    public static int findIndex(int a, int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] == a) return i;
        }
        return -1;
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
