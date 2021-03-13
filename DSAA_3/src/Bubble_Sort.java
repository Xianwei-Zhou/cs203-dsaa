import java.io.*;
import java.math.*;
import java.util.*;

public class Bubble_Sort {//这题等价于归并算法求给定数组的逆序对数 或者说是归并排序加上一个计数器
    public static void main(String[] args) {
        InputStream inputStream = System.in;// new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);

        int T=in.nextInt();
        for (int t = 0; t < T; t++) {
            count=0;//每一组数据都重置一下计数器
            int n=in.nextInt();
            int[] array=new int[n];
            for (int i = 0; i < n; i++) {
                array[i]=in.nextInt();
            }
            mergeSort(array);
            out.println(count);

        }
        out.close();
    }
    private static long count;//一个计数器

    //归并算法
    private static int[] mergeSort(int[] array) {
        if (array.length < 2) return array;
        int p = array.length / 2;
        int[] b = Arrays.copyOfRange(array, 0, p);
        int[] c = Arrays.copyOfRange(array, p, array.length);
//        mergeSort(b);
//        mergeSort(c);
        return merge(mergeSort(b),mergeSort(c));
    }

    private static int[] merge(int[]b,int[] c){
        int i = 0,j=0;
        int length=b.length+c.length;
        int[] a=new int[length];
        for (int k = 0; k < length; k++) {
            if (j<c.length&&(i>=b.length||b[i]>c[j])){
                if (i<b.length)
                count+=b.length-i;
                a[k]=c[j];
                j+=1;
            }else{
                a[k]=b[i];
                i+=1;
            }
        }
        return a;
    }

    static class Task {

        public void solve(InputReader in, PrintWriter out) {




        }
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
