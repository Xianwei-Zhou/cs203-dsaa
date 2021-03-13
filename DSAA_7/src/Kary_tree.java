import java.util.Scanner;

public class Kary_tree {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            System.out.println(leafNum(in.nextInt(), in.nextInt()));
        }
    }

    public static long leafNum(long n, int k) {
        int h = (int) (Math.floor(Math.log(n * k - n) / Math.log(k)));
        long last = n - (1 - (long) Math.pow(k, h)) / (1 - k);
        long nu = (long) Math.pow(k, h) - last;
        return nu / k + last;
    }
}
