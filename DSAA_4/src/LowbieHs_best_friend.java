import java.io.*;
import java.math.*;
import java.util.*;

//总结一下，我这个代码写的巨烂，，，同一个内容开了三片内存去存（int数组，node数组，链表），应该用类似反函数的思想数组嵌套数组去写或者直接用数组模拟，
// 可以在常数级别上优化两到三倍以上的时间复杂度（像lyc的代码一样），
//下面我想到的思路都不够简洁，，，最优的解法是就用一个node数组按照读入的顺序存好，然后对这个数组中的node排序（不改变node数组），按照排好的顺序连成一个链表
//主要的点在于数组的顺序跟链表的顺序没任何关系，但是通过数组又可以很方便的调用到node。比我的单独建数组存两种索引的方法优雅很多倍，其实我的写法和这个已经很接近了（强行自我安慰x）
//换言之，通过Node数组与链表的两种得到两种index然后他们通过Node这个类连接，无需新建其他的属性/数组去存储与使用

//g题解法（数组+链表）与这个较为类似，就不重写了，，，（其实是懒）

// 开始思路有点问题一直用值来代所以无法处理重复，应该要用两个index去相互对应、查询。
// 我的思路的优化方向：排序可以在链表中完成没必要用数组去做再来对应链表（也是nlogn复杂度归并排序或者快排），这样就不需要使用那个int数组了
/*原题是Running Median，POJ 3784.
 从后往前做，先把所有数输入存到链表里，然后nlogn排序，先获取最后一个中位数，然后每次删除最后输入的两个数（输入次序也作为一个属性存储在节点中），
 然后对删除的数在排好序的链表中的位置进行讨论就可以得到前一个要求的中位数，最后反着输出即可
 链表只是在插入删除比较快，其他地方均可用数组辅助来做（弄清楚为什么要使用链表这种数据结构），但是数据相同时使用简单的一维数组有巨大的坑*/
//以后写这种可能有重复数据的需要用到index的题不能简单的用排序+二分查找的思路，二维数组是个好东西，或者用一个class存要用的这几个数据
//为了方便mid的移动，使用双向链表
public class LowbieHs_best_friend {
    static class Node {
        private int value;//这个点的值
        private Node next;//下一个节点
        private Node last;//前一个节点
        private int index;//排序好了后的序号

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }


    private static Node head = null;
    private static Node tail = null;

    //新插入的节点作为尾部节点（双向链表）
    private static Node temp = null;

    public static void addNode(Node newNode) {
        if (tail == null) {
            tail = newNode;
            head = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
            newNode.last = temp;
        }
        temp = newNode;
    }

    public static void deleteNode(Node node) {//O(1)删除node节点，最后会剩三个节点所以不要考虑太多
        if (node.next == null) {//尾节点
            tail = node.last;
            node.last.next = null;
        } else if (node.last == null) {
            node.next.last = null;
            head = node.next;
        } else {
            node.next.last = node.last;
            node.last.next = node.next;
        }
    }


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
                head = null;
                tail = null;
                temp = null;
                int n = in.nextInt();
                int[][] input = new int[n][3];//第0列是value，第1列是输入的index，第2列是排序后的index
                for (int i = 0; i < n; i++) {
                    input[i][0] = in.nextInt();
                    input[i][1] = i;
                }
                //如果n为偶数也只需要考虑前n-1个数，并让n=n-1
                if (n % 2 == 0) {
                    input = Arrays.copyOfRange(input, 0, n - 1);
                    n -= 1;
                }
                Arrays.sort(input, new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return Integer.compare(o1[0], o2[0]);
                    }
                });
                for (int i = 0; i < input.length; i++) {
                    input[i][2]=i;
                }

                Node[] nodes = new Node[n];
                for (int i = 0; i < n; i++) {
                    Node node = new Node(input[i][0], i);
                    addNode(node);//链表是已经排好序了的输入值
                    nodes[i] = node;//方便调用node，这个数组索引与链表及排序的一样
                }

                //这么写很不优雅，变过来再变回去，但是便于理解些
                Arrays.sort(input, new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return Integer.compare(o1[1], o2[1]);
                    }
                });


                int num = (n + 1) / 2;//输出的数的个数
                int[] ans = new int[num];

                //n为奇数,(n-1)/2恰好为中点
                Node mid = head;
                for (int i = 0; i < n / 2; i++) {
                    mid = mid.next;
                }
                ans[num - 1] = mid.value;
                //找到最后输入的两个值，进行讨论
                /*这里有个巨大的坑，，，ab是重复元素时两者的index相同，而且都是二分得到的第一个,以及mid的值重复情况
                在数组中无法保证每次ab的index不重复（不在mid同一侧）mid也在变，因此不能使用简单的一维数组排序+查找的方法。
                可以使用与排序那节类似的思路（就是每次mid移动一位需要处理一下，使用链表方便理解些）*/
                //需要进行操作的是最新输入的数及它在排序后的位置（又不能用二分查找等查找方法），围绕这两个数去操作
                for (int i = n - 1; i > 1; i -= 2) {
                    int a = input[i][0];
                    int b = input[i - 1][0];
                    int indexA = input[i][2];
                    int indexB = input[i-1][2];
                    int indexMid = mid.index;
//                  int indexMid = Arrays.binarySearch(sortedInput, mid.value);//这个不一定真的是mid的index
//                    if (a != b) {
//                        indexA = Arrays.binarySearch(sortedInput, a);//最后输入的一个数在排好序后的index
//                        indexB = Arrays.binarySearch(sortedInput, b);
//                    } else {
//                        indexA = Arrays.binarySearch(sortedInput, a);
//                        if (indexA != 0 && sortedInput[indexA - 1] == a) indexB = indexA - 1;
//                        else indexB = indexA + 1;
//                    }

                    //考虑到重复元素这里用index而不用数值比较。。。这里需要最大的最小和最小的最大，不能看错
                    if (Math.max(indexA, indexB) <= indexMid)
                        mid = mid.next;
                    if (Math.min(indexA, indexB) >= indexMid)
                        mid = mid.last;

                    deleteNode(nodes[indexA]);
                    deleteNode(nodes[indexB]);
                    ans[--num - 1] = mid.value;
                }

                for (int a : ans) {
                    out.print(a + " ");
                }
                out.println();
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
