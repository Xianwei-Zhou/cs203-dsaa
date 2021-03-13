import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

//贪心算法 优先队列 （用数组实现，本质上还是树）
//优先队列要求父节点比两个子节点大就行了，层与层直接大小不完全确定
public class Cut_the_stick {

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int n = in.nextInt();
            ArrayList<Integer> queue = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                insert(in.nextInt(), queue);
            }
//            System.out.println(queue.toString());
            long sum = 0;
            if (queue.size() == 1)
                sum = queue.get(0);
            while (queue.size() > 1) {
                int a = delete(queue) + delete(queue);
                insert(a, queue);
                sum += a;
            }
            System.out.println(sum);

        }


        out.close();
    }

    public static void insert(int val, ArrayList<Integer> list) {
        list.add(val);
        int i = list.size();
        if (i == 1) return;
        while (i > 1 && list.get(i - 1) < list.get(i / 2 - 1)) {
            swap(list, i - 1, i / 2 - 1);
            i /= 2;
        }
    }

    public static int delete(ArrayList<Integer> list) {
        swap(list, 0, list.size() - 1);
        int d = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        shiftDown(list);
        return d;
    }

    public static void shiftDown(ArrayList<Integer> list) {
        int i = 1;
        while (list.size() != 1 && 2 * i <= list.size()) {
            //找到左右两个节点的最小值
            int minChild = 2 * i;
            if (2 * i < list.size() && list.get(2 * i - 1) > list.get(2 * i))
                minChild = 2 * i + 1;
            if (list.get(i - 1) > list.get(minChild - 1))
                swap(list, i - 1, minChild - 1);
            else break;
            i = minChild;
        }
    }

    public static void swap(ArrayList<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
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
