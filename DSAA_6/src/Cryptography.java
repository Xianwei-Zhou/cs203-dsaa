import java.util.Scanner;

public class Cryptography {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] d = new char[26];
        for (int i = 0; i < 26; i++) {
            char ch = in.next().charAt(0);
            d[ch - 'a'] = (char) ('a' + i);
        }
        StringBuilder str = new StringBuilder(in.next());
        int n = str.length();
        for (int i = 0; i < (n + 1) / 2; i++) {
            str.setCharAt(i, d[str.charAt(i) - 'a']);
        }
        int[] next = new int[n];
        next[0] = 0;
        int k = 0;
        int j = 1;
        while (j < n) {
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
        System.out.println(n - next[n - 1]);
    }
}
