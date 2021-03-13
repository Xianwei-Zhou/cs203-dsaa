import java.io.*;
import java.math.*;
import java.util.*;
//注释中的列是一二，代码中写的是01，太混乱了导致出了一些莫名其妙的bug，以后写注释也用01列表示

//可以证明所有的翻倍必须全部翻到同一个人，然后比较翻倍+相等以及相等带来的攻击力增量
//快排过不了，只能归并或者库函数（也是归并），不让使用Arrays.sort只好手写了一个归并排序
/*快排过不了的原因：oj数据中有200000组一样的数据，然后快排遍历每个数时返回的p值（当前值应该的位置）都在最小（最大），快排退化为冒泡排序，
 需要采用优化的快排把这种情况解决（双指针法遍历swap然后把相同的元素移到两边最外面，在当前pivot遍历完后再移到中间）
 试了下应该用我这种单指针方法无法实现这一点，只能按照课件上的双指针方法做
 事实上快排是否快取决于每次的划分函数返回的pivot的位置p是否在中间，如果一直在端点就变成冒泡了*/
public class Divine_Spirit_and_Inner_Fire {//与F题思路一模一样
    public static void main(String[] args) {
        InputStream inputStream = System.in;// new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);

        int n = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();
        long d = 1;
        for (int i = 0; i < p; i++) {
            d *= 2;
        }
        long[][] diff = new long[n][2];
        long sum = 0;//攻击力之和

        for (int i = 0; i < n; i++) {
            int hp = in.nextInt();//hp
            int atk = in.nextInt();//atk
            diff[i][0] = hp - atk;//单纯一次相等带来的增量（消耗一次q）  （第一列，需要取q-1个）
            diff[i][1] = d * hp - atk;//翻倍后相等带来的增量（消耗所有p以及一次q）   （第二列，需要取1个）
            sum += atk;
        }
        mergeSort(diff,0, diff.length-1);
//        Arrays.sort(diff, new Comparator<long[]>() {//把数组diff按照第一列的降序排列（注意不是升序）
//            @Override
//            public int compare(long[] o1, long[] o2) {
//                if (o1[0] > o2[0])
//                    return -1;
//                if (o1[0] < o2[0])
//                    return 1;
//                return 0;
//            }
//        });
        int index = 0;//第二列要取的数的编号
        q = Math.min(q, n);//避免q>n的极端情况
        if (q == 0) {
            System.out.println(sum);
            return;
        }
        long tmp = Integer.MIN_VALUE;//记录一二列差值（二减一）的最大值
        for (int i = 0; i < n; i++) {
            int min = Math.min(i, q - 1);//在第一列先取前q个再去掉一个
            if (diff[i][1] - diff[min][0] > tmp) {
                tmp = diff[i][1] - diff[min][0];
                index = i;
            }
        }
        long increase = 0;//增量
        if (tmp < 0||diff[index][1]<0) {//考虑加上第二列还不如不加的情况（这时第一列要取q个数）
            for (int i = 0; i < q; i++) {
                if (diff[i][0] > 0)
                    increase += diff[i][0];
                else break;
            }
            System.out.println(sum + increase);
            return;
        }

        int min = Math.min(index, q - 1);//要删掉的第一列的数（第一列总共要取q-1个数）
        for (int i = 0; i < q; i++) {//遍历q个数删掉一个得到需要的q-1个
            if (i != min && diff[i][0] > 0)
                increase += diff[i][0];
        }
        increase += diff[index][1];
        sum += increase;

        out.println(sum);
        out.close();
    }

    //归并算法排序（按照降序）     网上找的优化了的算法，不用按照课件里那样new两个子数组
    public static void mergeSort(long[][] array, int low, int high) {
        //这个方法不用像课件上那样实质上的分两个子数组出来，只是标记一个mid的（由此划分左右两个数组）
        int mid = low + (high-low) / 2;
        if (low < high) {
            // 左边
            mergeSort(array, low, mid);
            // 右边
            mergeSort(array, mid + 1, high);
            // 归并
            merge(array, low, mid, high);
        }
    }
    public static void merge(long[][] a, int low, int mid, int high) {
        long[][] temp = new long[high - low + 1][2];
        int i = low;
        int j = mid + 1;
        int k = 0;
        // 把较大的数先移到新数组中   课件中的写法更简洁但这么思路更清晰也不容易有bug
        while (i <= mid && j <= high) {
            if (a[i][0] > a[j][0]||(a[i][0]==a[j][0]&&a[i][1]>a[j][1])) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        // 右半边已经移完了全部移左边的情况
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        // 左半边移完了移右边的情况
        while (j <= high) {
            temp[k++] = a[j++];
        }
        // 覆盖输入的数组a
        if (temp.length >= 0) System.arraycopy(temp, 0, a, low, temp.length);
    }

//这个是按课件上的，一样的
//    //归并算法
//    private static long[][] mergeSort(long[][] array) {
//        if (array.length < 2) return array;
//        int p = array.length / 2;
//        long[][] b = Arrays.copyOfRange(array, 0, p);
//        long[][] c = Arrays.copyOfRange(array,  p, array.length);
//        return merge(mergeSort(b),mergeSort(c));
//    }
//
//    private static long[][] merge(long[][]b,long[][] c){
//        int i = 0,j=0;
//        int length=b.length+c.length;
//        long[][] a=new long[length][2];
//        for (int k = 0; k < length; k++) {
//            if (j<c.length&&(i>=b.length||b[i][0]<c[j][0])){
//                a[k]=c[j];
//                j+=1;
//            }else{
//                a[k]=b[i];
//                i+=1;
//            }
//        }
//        return a;
//    }




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
