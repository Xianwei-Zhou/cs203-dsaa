import java.io.*;
import java.math.*;
import java.util.*;

//单调栈问题 栈内只留递减（递增）的数，其他pop掉
public class Skylars_Investment_In_Stocks {
    static class Node{
        int val;
        int index;

        public Node(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
    static class stack {
        int maxSize;
        Node[] s;
        int top;

        public stack(int maxSize) {
            this.maxSize = maxSize;
            s = new Node[maxSize];
            top = -1;
        }

        public void push(int val,int index) throws Exception {
            if (top == maxSize - 1) throw new Exception("stack is full");
            Node node=new Node(val,index);
            top += 1;
            s[top]=node;
        }

        public void pop() throws Exception {
            if (top == -1) throw new Exception("stack is empty");
            top -= 1;
        }

        public Node peek() throws Exception {
            if (top == -1) throw new Exception("no peek");
            return s[top];
        }

        public boolean isEmpty() {
            return top == -1;
        }
    }

    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    //这种边运行边删除的题不要用cnt去计数（间隔），直接建一个node存index，你不知道在要求的区间里是否之前有删除过元素（一般都有）
    static class Task {
        public void solve(InputReader in, PrintWriter out) throws Exception {
            int T = in.nextInt();
            for (int t = 0; t < T; t++) {
                int n = in.nextInt();
                stack stack = new stack(n);
                int[] ans = new int[n];
                int cur;
                for (int i = 0; i < n; i++) {
                    cur = in.nextInt();
                    while ((!stack.isEmpty()) && cur > stack.peek().val) {
                        ans[stack.peek().index] = i-stack.peek().index;
                        stack.pop();
                    }
                    stack.push(cur,i);
                }
                int q = in.nextInt();
                int k;
                for (int i = 0; i < q; i++) {
                    k = in.nextInt();
                    if (ans[k - 1] == 0)
                        out.println(-1);
                    else out.println(ans[k - 1]);
                }
            }
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
