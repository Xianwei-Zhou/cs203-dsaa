import java.io.*;
import java.math.*;
import java.util.*;

class Queue {
    Node head;
    Node tail;
    int size;

    public Queue() {
        size = 0;
    }

    //加到尾部
    public void enQueue(int val) {
        Node node = new Node(val);
        if (isEmpty()) {
            head = node;
            tail = head;
        } else if (size==1) {
            tail = node;
            head.next = tail;
            tail.prev = head;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size += 1;
    }

    //从头部出
    public void deQueue() {
        if (isEmpty()) return;
        if (size==1){
            head = null;
        }
        else {
            head=head.next;
            head.prev=null;
        }
        size -= 1;
    }

    public int peek() {
        return head.val;
    }

    public boolean isEmpty() {
        return head==null;
    }
}

class Node {
    int val;
    Node next;
    Node prev;

    public Node(int val) {
        this.val = val;
    }
}

public class Skylar_Learning_Queue {
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
            int n = in.nextInt();
            Queue queue = new Queue();
            for (int i = 0; i < n; i++) {
                switch (in.next().charAt(0)) {
                    case 'E':
                        queue.enQueue(in.nextInt());
                        break;
                    case 'A':
                        if (queue.isEmpty())
                            out.println();
                        else out.println(queue.peek());
                        break;
                    case 'D':
                        queue.deQueue();
                        break;
                }
            }
            Node temp = queue.head;
            for (int i = 0; i < queue.size; i++) {
                out.print(temp.val + " ");
                temp = temp.next;
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
