import java.util.Scanner;

public class Tower_of_Hanoi {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int T=in.nextInt();
        long[] array=new long[T];
        for (int i = 0; i < T; i++) {
            int n=in.nextInt();
            long m=1;
            for (int j = 0; j < n; j++) {
                m=3*m%1000000007;
            }array[i]=m-1;
//            array[i]=Math.floorMod((long) Math.pow(3,(long)n)-1,1000000007);
        }in.close();
        for (int i = 0; i < T; i++) {
            System.out.println(array[i]);
        }
    }
}
