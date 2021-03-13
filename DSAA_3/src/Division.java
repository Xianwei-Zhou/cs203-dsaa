import java.io.*;
import java.math.*;
import java.util.*;

//把乘index转化为加index次这个数（后缀和加index次）
public class Division {
    public static void main(String[] args) {
        InputStream inputStream = System.in;// new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }
    static class Task {
        public void solve(InputReader in, PrintWriter out) {
            int n=in.nextInt();
            int m=in.nextInt();
            int[] array=new int[n];
            for (int i = 0; i < n; i++) {
                array[i]=in.nextInt();
            }

            long[] sums=new long[n-1];
//            for (int i = 1; i < n-1; i++) {
//                sums[i-1]=findSum(array,i);
//            }
            int k=0;
            sums[0]=findSum(array,1);
            while (k<n-2){
                sums[k+1]=sums[k]-array[k+1];
                k++;
            }
            long sum0=findSum(array,0);
//            Arrays.sort(sums);
            sums=quickSort(sums,0,sums.length-1);
            long ans = sum0;
            if (m!=1) {
                for (int i = 1; i < m; i++) {
                    ans += sums[sums.length-i];
                }
            }out.println(ans);
        }
    }


    private static long findSum(int[] array,int index){
        long sum=0;
        for (int i = index; i < array.length; i++) {
            sum+=array[i];
        }
        return sum;
    }
    private static long[] quickSort(long[] array,int low,int high){
        if (low < 0 || high >= array.length || low > high) return null;
        int p = partition(array, low, high);
        if (p > low)//这两段递归都会执行
            quickSort(array, low, p - 1);
        if (p < high)
            quickSort(array, p + 1, high);
        return array;
    }
    public static int partition(long[] array, int low, int high) {
        int pivot = (int) (low + Math.random() * (high - low + 1));
        int p = low - 1;
        swap(array, pivot, high);//不采用课件上的新建一个数组的方法，直接在原数组中进行操作（把pivot移到最后，然后比他小的数全部在前p个）
        for (int i = low; i <= high; i++) {
            if (array[i]< array[high]) {
                p += 1;//上面那行high是pivot的值，也就是if（当前值小于pivot的值）
                if (i > p)
                    swap(array, i, p);
            }
        }
        p+=1;
        swap(array,p,high);//最后把pivot移回p+1的位置（前面有p+1个比pivot小）
        return p;
    }

    public static void swap(long[] array, int i, int j) {
        long temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }





    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] nextCharArray() {
            return next().toCharArray();
        }

        //         public boolean hasNext() {
//             try {
//                 return reader.ready();
//             } catch(IOException e) {
//                 throw new RuntimeException(e);
//             }
//         }
        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch (IOException e) {
                return false;
            }
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecinal() {
            return new BigDecimal(next());
        }
    }
}
