import java.io.*;
import java.util.*;

//这题思路很巧妙
public class Skylars_Candy_Stack {
    static class Stack {//head进head出
        Node head;
        int size;

        public Stack() {
            size = 0;
            head = null;
        }

        public void push(int val) {
            Node node = new Node(val);
            node.next = head;
            head = node;
            size += 1;
        }

        public int pop() throws Exception {
            if (isEmpty()) throw new Exception("stack is empty");
            int x = head.val;
            head = head.next;
            size -= 1;
            return x;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
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

    static class Task {
        public void solve(InputReader in, PrintWriter out) throws Exception {
            int[] cnt = new int[100001];//编号为i的糖的个数是cnt[i](范围是1-100000，所以这里开100001的数组，，，i的最大值是100000，以后还是用i-1比较好）
            ArrayList<Stack> stacks = new ArrayList<>();//stacks.size表示当前栈内最多的糖果数量
            while (true) {
                String op = in.next();
                if (op.equals("nsdd")) return;
                if (op.equals("eat")) {
                    if (stacks.isEmpty())
                        out.println("pa");
                    else {
                        int x = stacks.get(stacks.size() - 1).pop();
                        out.println(x);
                        cnt[x] -= 1;
                        if (stacks.get(stacks.size() - 1).isEmpty())
                            stacks.remove(stacks.size() - 1);
                    }
                } else {
                    int c = in.nextInt();
                    cnt[c] += 1;
                    if (cnt[c] <= stacks.size()) {

                        stacks.get(cnt[c] - 1).push(c);
                    } else {
                        Stack stack = new Stack();

                        stack.push(c);
                        stacks.add(stack);
                    }
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

    }
}
