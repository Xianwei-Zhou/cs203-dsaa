import java.util.Scanner;

//leetcode 459 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。
public class Gift {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String str = in.next();
            int len = str.length();
            int last = next(str, len)[len - 1];
            //(n+k)%((n+k)-(last+k))==0 求最小的k 即为充要条件
            if (len % (len - last) == 0 && last != 0)
                System.out.println(0);
            else
                System.out.println(len - last - len % (len - last));

        }
    }

    public static int[] next(String str, int m) {
        int[] next = new int[m];
        next[0] = 0;
        int k = 0;
        int j = 1;
        while (j < m) {
            if (str.charAt(j) == str.charAt(k)) {
                k += 1;
                next[j] = k;
                j += 1;
            } else if (k == 0) {
                next[j] = 0;
                j += 1;
            } else
                k = next[k - 1];

        }
        return next;
    }
}
