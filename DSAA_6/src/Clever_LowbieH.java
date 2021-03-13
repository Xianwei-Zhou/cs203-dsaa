import java.util.Scanner;

public class Clever_LowbieH {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = in.next();
        }
        int q = in.nextInt();
        int count = 0;
        for (int i = 0; i < q; i++) {
            if (compare(strings[in.nextInt() - 1], strings[in.nextInt() - 1]) == in.next().charAt(0)) {
                count += 1;
            }
        }
        System.out.println(count);
    }

    public static char compare(String s, String t) {
        int diff = fun(s, t) - fun(t, s);
        if (diff > 0) return '>';
        if (diff < 0) return '<';
        return '=';
    }

    //next方法改一下
    public static int fun(String s, String t) {
        String str = s + t;
        int i = 0;
        int j = 1;
        int[] next = new int[str.length()];
        while (j < str.length()) {
            if (str.charAt(i) == str.charAt(j)) {
                i += 1;
                next[j] = i;
                if (i >= Math.min(s.length(), t.length()))
                    i = next[i - 1];
                j += 1;
            } else if (i == 0)
                j += 1;
            else
                i = next[i - 1];
        }
        return next[str.length() - 1];
    }
}
