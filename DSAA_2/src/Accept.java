import java.util.Scanner;

public class Accept {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int T = in.nextInt();
        int[] problems = new int[N];
        int[] energies = new int[T];
        for (int i = 0; i < N; i++) {
            problems[i] = in.nextInt();
        }
        for (int i = 0; i < T; i++) {
            int energy = in.nextInt();
            int ans = binarySearch(problems, energy, 0, N - 1);
            if (ans != 0) {
                ans = (energy - problems[ans]) > 0 ? (energy - problems[ans]) : (energy - problems[ans - 1]);//不知道有没有什么其他办法
            }
            energies[i] = ans;
        }
        for (int i = 0; i < T; i++) {
            if (energies[i] == 0)
                System.out.println("Accept");
            else System.out.println(energies[i]);
        }
    }

    private static int binarySearch(int[] problems, int energy, int left, int right) {
//        int left=0;
//        int right=problems.length-1;
//        do {
//            int mid=(left+right)/2;
//            if (energy>problems[mid])
//                left=mid;
//            else if (energy<problems[mid])
//                right=mid;
//            else return 0;
//
//        }while (left>right);
        if (left > right) {
            return right;
        } else {
            int mid = left + (right - left) / 2;
            if (energy < problems[mid])
                return binarySearch(problems, energy, left, mid - 1);
            if (energy > problems[mid])
                return binarySearch(problems, energy, mid + 1, right);
            else return 0;
        }

    }
}
