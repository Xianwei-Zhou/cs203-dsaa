import java.io.*;
import java.math.*;
import java.util.*;

class stack {
    int size;
    int top;
    char[] s;

    public stack(int size) {
        this.size = size;
        top = -1;
        s = new char[size];
    }

    public void push(char a) {
        if (top != size - 1) {
            top += 1;
            s[top] = a;
        }
    }

    public void pop() {
        if (top != -1)
            top -= 1;
    }

    public char peek() {
        if (top != -1)
            return s[top];
        return 'N';
    }

    public boolean isNull() {
        return top == -1;
    }
}

public class Skylar_Matching_Brackets {
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
            int T = in.nextInt();
            for (int t = 0; t < T; t++) {
                label:
                {
                    int n = in.nextInt();
                    stack stack = new stack(n);
                    char[] chars = in.next().toCharArray();
                    for (char ch : chars) {
                        if (ch == '(' || ch == '[' || ch == '{') {
                            stack.push(ch);
                        } else {
                            if (stack.isNull()) {
                                out.println("NO");
                                break label;
                            }
                            char top = stack.peek();
                            switch (ch) {
                                case ')':
                                    if (top != '(') {
                                        out.println("NO");
                                        break label;
                                    } else break;
                                case ']':
                                    if (top != '[') {
                                        out.println("NO");
                                        break label;
                                    } else break;
                                case '}':
                                    if (top != '{') {
                                        out.println("NO");
                                        break label;
                                    } else break;
                            }
                            stack.pop();
                        }
                    }
                    if (stack.isNull()) {
                        out.println("YES");
                    } else out.println("NO");
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