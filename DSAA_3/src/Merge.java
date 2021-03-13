import java.util.Scanner;
import java.io.*;
import java.math.*;
import java.util.*;

public class Merge {
    public static void main(String[] args) {
//        Scanner in=new Scanner(System.in);
//        int T=in.nextInt();
//        int[] ans=new int[T];
        InputStream inputStream = System.in;// new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        int T=in.nextInt();
        for (int t = 0; t < T; t++) {
            int n=in.nextInt();
            int m=in.nextInt();
            int[] ns=new int[n];
            int[] ms=new int[m];
            for (int i = 0; i <n ; i++) {
                ns[i]=in.nextInt();
            }
            for (int i = 0; i < m; i++) {
                ms[i]=in.nextInt();
            }
            int[] mn=new int[m+n];
            int i=0;
            int j=0;
            for (int k = 0; k < n+m; k++) {
                if (i<n&&(j>=m||ns[i]<ms[j])) {//j>m||(i<n&&ns[i]<ms[j])
                    mn[k] =ns[i];
                    i+=1;
                }else {
                    mn[k]=ms[j];
                    j+=1;
                }
            }
            for (int k = 0; k < n+m; k++) {
                System.out.print(mn[k]+" ");
            }
            System.out.println();
        }out.close();
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