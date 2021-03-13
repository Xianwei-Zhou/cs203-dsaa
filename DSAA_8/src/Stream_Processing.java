import java.util.PriorityQueue;
import java.util.Scanner;

public class Stream_Processing {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int k = in.nextInt();
        long ans = in.nextInt();
        long lastAns=ans;
        PriorityQueue<Long> queue = new PriorityQueue<>();
        for (int i = 0; i < t / 100; i++) {
            for (int j = 1; j <= 100; j++) {
                ans = funA(100 * i + j + lastAns);
                if (queue.size() < k)
                    queue.offer(ans);
                else if (ans > queue.peek()) {
                    queue.poll();
                    queue.offer(ans);
                }
            }
            lastAns=queue.peek();
            System.out.print(lastAns + " ");
        }
    }

    public static long funA(long n) {
        int sum = 0;
        long n1=n;
        while (n != 0) {
            sum += n % 10;
            n /= 10;
        }
        return n1 + sum;
    }
}
