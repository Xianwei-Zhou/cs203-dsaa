import java.util.Scanner;

public class Stone_Game {
    public static void main(String[] args) {
        Scanner in= new Scanner(System.in);
        int T=in.nextInt();
        int[] ways=new int[T];
        for (int i = 0; i < T; i++) {
            int piles=in.nextInt();
            int[] stones=new int[piles];
            for (int j = 0; j < piles; j++) {
                stones[j]=in.nextInt();
            }
            int sum=findSum(stones);
            if (sum==0)
                ways[i]=0;
            else {
                int way = 0;
                for (int j = 0; j < piles; j++) {
//                    int m=stones[j];
//                    stones[j]=0;
//                    if (findSum(stones)<m)
//                        way++;
//                    stones[j]=m;
                    sum=sum^stones[j];
                    if (sum<stones[j])
                        way++;
                    sum=sum^stones[j];//异或的运算性质
                }ways[i]=way;
            }
        }
        for (int i = 0; i < T; i++) {
            System.out.println(ways[i]);
        }

    }
    private static int findSum(int[] stones){
        int sum=stones[0];
        for (int i = 1; i < stones.length; i++) {
            sum=sum^stones[i];
        }return sum;
    }
}
