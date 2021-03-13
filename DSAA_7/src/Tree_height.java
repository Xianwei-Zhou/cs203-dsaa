import java.util.Scanner;

public class Tree_height {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int T=in.nextInt();
        for (int t = 0; t < T; t++) {
            long n=in.nextLong();
            System.out.println((int)Math.floor(Math.log(n)/Math.log(2))+1);
        }
    }
}
