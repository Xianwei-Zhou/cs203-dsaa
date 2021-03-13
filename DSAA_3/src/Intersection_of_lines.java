import java.io.*;
import java.math.*;
import java.util.*;

//画两条竖直线看图就行了
public class Intersection_of_lines {
    public static void main(String[] args) {//代码应该放在下面的solve里，不过这么写也没啥大问题
        InputStream inputStream = System.in;// new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);

        int n=in.nextInt();
        long x1=in.nextLong();
        long x2=in.nextLong();
        long[][] leftRight=new long[n][2];//求出每一条直线与竖直线x=x1，x=x2的交点（左右两个交点）,如果无交点那么对其中一列排序后另一列也一定是按顺序的
        long k,b;
        for (int i = 0; i < n; i++) {
            k=in.nextLong();
            b=in.nextLong();
            leftRight[i][0]=x1*k+b;
            leftRight[i][1]=x2*k+b;
        }
        //对二维数组按照左列进行排序，当左列相同时比较右列
        //好像这个题目要求不能直接调这个库，只能自己写一遍    是oj测试样例数据的问题，可以调用这个库，但是后面自己写的也没问题

//        Arrays.sort(leftRight, new Comparator<long[]>() {
//            @Override
//            public int compare(long[] o1, long[] o2) {
//                if (o1[0]<o2[0]||(o1[0]==o2[0]&&o1[1]<o2[1]))
//                    return -1;
//                return 1;
//            }
//        });

        leftRight=quickSort(leftRight,0,n-1);


        //接下来检查右列是否也是按照升序排序
        for (int i = 0; i < n-1; i++) {
            assert leftRight != null;
            if (leftRight[i][1] > leftRight[i + 1][1]) {
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
        out.close();
    }

    //写了一个快排算法去排序二维数组
    private static long[][] quickSort(long[][] array,int low,int high){
        if (low < 0 || high >= array.length || low > high) return null;
        int p = partition(array, low, high);
        if (p > low)//这两段递归都会执行
            quickSort(array, low, p - 1);
        if (p < high)
            quickSort(array, p + 1, high);
        return array;
    }
    public static int partition(long[][] array, int low, int high) {
        int pivot = (int) (low + Math.random() * (high - low + 1));
        int p = low - 1;
        swap(array, pivot, high);//不采用课件上的新建一个数组的方法，直接在原数组中进行操作（把pivot移到最后，然后比他小的数全部在前p个）
        for (int i = low; i <= high; i++) {
            if (array[i][0] < array[high][0] || (array[i][0] == array[high][0] && array[i][1] < array[high][1])) {//o1[0]<o2[0]||(o1[0]==o2[0]&&o1[1]<o2[1])
                p += 1;//上面那行high是pivot的值，也就是if（当前值小于pivot的值）
                if (i > p)
                    swap(array, i, p);
            }
        }
        p+=1;
        swap(array,p,high);//最后把pivot移回p+1的位置（前面有p+1个比pivot小）
        return p;
    }

    public static void swap(long[][] array, int i, int j) {
        long[] temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
