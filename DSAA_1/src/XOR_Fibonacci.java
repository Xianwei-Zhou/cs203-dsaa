import java.util.Scanner;

public class XOR_Fibonacci {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int T=scanner.nextInt();
        int[] array=new int[T];
        for (int i = 0; i < T; i++) {
            int a=scanner.nextInt();
            int b=scanner.nextInt();
            int n=scanner.nextInt();
            //scanner.next();
            array[i]=function(a,b,n);
        }
        for (int i = 0; i < T; i++) {
            System.out.println(array[i]);
        }
    }
//    public static int function(int a, int b,int n){
//        if (n==0){
//            return a;
//        }if (n==1){
//            return b;
//        }
//
//        int[] array=new int[n+1];
//        array[0]=a;
//        array[1]=b;
//
//        for (int i = 2; i <= n; i++) {
//            array[i]=array[i-2]^array[i-1];
//        }
//        return array[n];
//    }
    public static int function(int a,int b,int n){
        int m=a^b;
        if (n%3==0){
            return a;
        }if (n%3==1){
            return b;
        }else return m;
    }
}
