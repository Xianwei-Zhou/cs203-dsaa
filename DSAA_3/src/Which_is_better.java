import java.io.*;
import java.math.*;
import java.util.*;

public class Which_is_better {
    private static int[] quickSort(int[] array,int low,int high){
        if (low < 0 || high >= array.length || low > high) return null;
        int p = partition(array, low, high);
        if (p > low)//这两段递归都会执行
            quickSort(array, low, p - 1);
        if (p < high)
            quickSort(array, p + 1, high);
        return array;
    }
    public static int partition(int[] array, int low, int high) {
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

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    public static void main(String[] args) {
        InputStream inputStream = System.in;// new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);

        int T=in.nextInt();
        for (int i = 0; i < T; i++) {
            int n=in.nextInt();
            int[] array=new int[n];
            for (int j = 0; j < n; j++) {
                array[j]=in.nextInt();
            }
//            //算时间有误差，只能直接比较次数
//            long startTime1=System.nanoTime();
//            int[] insert=insertionSort(array);
//            long endTime1=System.nanoTime();
//
//            long startTime2=System.nanoTime();
//            int[] select=selectionSort(array);
//            long endTime2=System.nanoTime();

//            if (endTime1-startTime1<endTime2-startTime2)
//                System.out.print("Insertion Sort wins!");
//            else System.out.print("Selection Sort wins!");
////            System.out.println(Arrays.toString(insert) +"\n"+ Arrays.toString(select));

            int insert=insertionSort(array);
            int select=selectionSort(array);
//            Arrays.sort(array);
            array=quickSort(array,0,n-1);
            for (int j = 0; j < n; j++) {
                assert array != null;
                out.print(array[j]+" ");
            }
            out.println();
            if (insert<select)
                out.println("Insertion Sort wins!");
            else out.println("Selection Sort wins!");
        }
        out.close();
    }

    //插入排序
    public static int insertionSort(int[] array){
        int times=0;
        int mid;
        for (int i = 1; i < array.length; i++) {//这里的判断不能算（不符合题意中的comparison,所以不用times+=1
            for (int j = i; j >=1; j--) {//同上说明
                times+=1;//下面这个if判断
                if (array[j]<array[j-1]){
                    times+=1;//交换
                    mid=array[j];
                    array[j]=array[j-1];
                    array[j-1]=mid;
                }else break;
            }
        }
//        System.out.println(Arrays.toString(array));
//        System.out.println(times);
        return times;
    }

    //选择排序
    public static int selectionSort(int[] array){
        int times=0;
        int mid;
        for (int i = 0; i < array.length-1; i++) {//见插入排序的注释
            int k=i;
            for (int j = i+1; j < array.length; j++) {//同上
                times+=1;//下面这个比较
                if (array[j]<array[k]){
                    k=j;
                }
            }times+=1;//交换
            mid=array[k];
            array[k]=array[i];
            array[i]=mid;
        }
//        System.out.println(Arrays.toString(array));
//        System.out.println(times);
        return times;
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
