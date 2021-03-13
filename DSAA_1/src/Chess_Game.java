import java.util.Scanner;

public class Chess_Game {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int T=in.nextInt();
        int[] winner=new int[T];
        for (int i = 0; i < T; i++) {
            if (in.nextInt()!=1|in.nextInt()!=1)//这里只能用|不能用||，因为必须要让in.nextInt()执行（用|就能ac）
                winner[i]=1;
        }

//        int[] size=new int[2];
//        for (int i = 0; i < T; i++) {
//            size[0]=in.nextInt();
//            size[1]=in.nextInt();
//            if (size[0]!=size[1]||size[0]!=1)
//                winner[i]=1;
//        }

        for (int i = 0; i < T; i++) {
            if (winner[i]==0)
                System.out.println("Bob");
            else System.out.println("Alice");
        }
    }
}
