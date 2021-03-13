import java.util.Scanner;

public class Magic_Recurrence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        int[] fs = new int[T];
        int[] table=new int[(int)Math.pow(10,6)+1];
        table[0]=1;
        table[1]=1;
        table[2]=1;
        table[3]=1;
        for (int i = 4; i < Math.pow(10,6)+1; i++) {//直接打表做了，放弃找规律
            table[i]=table[i/2+1]+table[i/2]+table[i/2-1];
        }
        for (int i = 0; i < T; i++) {
            int x = in.nextInt();
            fs[i]=table[x];
        }
        for (int i = 0; i < T; i++) {
            System.out.println(fs[i]);
        }
    }


        //递归算法超时了
//        for (int i = 0; i < T; i++) {
//            int x=in.nextInt();
//            if (x<=3)
//                fs[i]=1;
//            else
//            fs[i]=function(x);
//        }
//        for (int i = 0; i < T; i++) {
//            System.out.println(fs[i]);
//        }
//    }
//    private static int function(int x){
//        if (x<=3)
//            return 1;
//        else if (x%2!=0)
//            x=x-1;
//        return function(x/2)+function(x/2+1)+function(x/2-1);
//    }

}