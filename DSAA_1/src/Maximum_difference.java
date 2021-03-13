import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Maximum_difference {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int T=in.nextInt();
        int[] differences=new int[T];
        for (int i = 0; i < T; i++) {
            int n=in.nextInt();
            int[] input=new int[n];
            for (int j = 0; j < n; j++) {
                input[j]=in.nextInt();
            }
            int[] first_differences=new int[n-1];

            //first_differences（长度为n-1） 为 a-b,b-c,c-d... 问题转化为求 first_differences 子数组的和的最大值
            for (int j = 0; j < n-1; j++) {
                first_differences[j]=input[j]-input[j+1];
            }
            //求子数组的和的最大值
            int sum=first_differences[0];//子数组的和
            int max=first_differences[0];//最大值
            for (int j = 1; j < n-1; j++) {
                if (sum<0){
                    sum=first_differences[j];}
                else sum+=first_differences[j];
                max=Math.max(sum,max);
//                if(first_differences[j]<0){
//                    max=first_differences[j];
//                }else sum+=first_differences[j];
//                if (max>sum)
//                    sum=max;
            }
            differences[i]=max;



//            int[] second_differences=first_differences.clone();
//            Arrays.sort(second_differences);
//            int max=second_differences[0];

//            Arrays.sort(first_differences);
//            if (first_differences[0]>=0)
//                differences[i]=first_differences[first_differences.length-1];
//            else {
//                differences[i]=first_differences[first_differences.length-1]-first_differences[0];
//            }
        }
        for (int i = 0; i < T; i++) {
            System.out.println(differences[i]);
        }
    }
}
