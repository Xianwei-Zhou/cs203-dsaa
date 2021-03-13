import java.util.Scanner;
import java.util.Stack;

public class B {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        int[] sum = new int[10000000];
        int[] max = new int[10000000];
        int cursor = 0;

        for (int i = 0; i < q; i++) {
            max[0] = Integer.MIN_VALUE;
            sum[0] = 0;
            switch (in.next().charAt(0)) {
                case 'I': {
                    int x = in.nextInt();
                    stack1.push(x);
                    cursor++;
                    sum[cursor] = sum[cursor - 1] + x;
                    max[cursor] = Math.max(max[cursor - 1], sum[cursor]);
                }
                break;
                case 'D': {
                    if (!stack1.isEmpty()) {
                        stack1.pop();
                        cursor--;
                    }
                }
                break;
                case 'L': {
                    if (!stack1.empty()) {
                        cursor--;
                        stack2.push(stack1.pop());
                    }
                }
                break;
                case 'R': {
                    if (!stack2.empty()) {
                        int x = stack2.pop();
                        stack1.push(x);
                        cursor++;
                        sum[cursor] = sum[cursor - 1] + x;
                        max[cursor] = Math.max(max[cursor - 1], sum[cursor]);
                    }
                }
                break;
                case 'Q': {
                    int k = in.nextInt();
                    System.out.println(max[k]);
                }
                break;
            }
        }
    }
}
