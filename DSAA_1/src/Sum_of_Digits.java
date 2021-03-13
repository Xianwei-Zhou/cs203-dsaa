import java.util.ArrayList;
import java.util.Scanner;

public class Sum_of_Digits {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int T=in.nextInt();
        long[] answer=new long[T];
        for (int t = 0; t < T; t++) {
            long n=in.nextLong();
            long s=in.nextLong();
            answer[t]=findAnswer(n,s);
        }
        for (int i = 0; i < T; i++) {
            System.out.println(answer[i]);
        }
    }
    private static long findAnswer(long n, long s){
        ArrayList<Integer> digits=findDigits(n);//获得由n每位数组成的数组（为防止越界最高位前面加了个0）
        int digitsSum=findDigitsSum(digits);//获得n各位数字之和

        if (digitsSum<=s)//不需要操作
            return 0;

        long ans=n;
        do {
            ans=findAnswer(s,findDigits(ans));
        }while (findDigitsSum(findDigits(ans))>s);

        return ans-n;
    }
    private static ArrayList<Integer> findDigits(long n){
        int i=0;
        ArrayList<Integer> digits=new ArrayList<>();
        while (true){
            if ((n/pow(i))!=0){
                digits.add((int) (n/ (pow(i))%10));//Arraylist记录n的每一位（从个位开始）
                i++;
            }else break;
        }digits.add(0);
        return digits;
    }
    private static int findDigitsSum(ArrayList<Integer> digits){
        int digitsSum=0;
        for (Integer digit : digits) {
            digitsSum += digit;
        }
        return digitsSum;
    }
    private static long findAnswer(long s,ArrayList<Integer> digits){
        int length=digits.size()-1;
        int sum=0;
        int k=length-1;
        while (sum<=s) {
            sum += digits.get(k);
            k--;
        }
        long ans=0;
        for (int j = k+3; j < length; j++) {
            ans+=digits.get(j)*pow(j);
        }ans+=pow(k+2)*(digits.get(k+2)+1);
        return ans;
    }
    private static long pow(int j){
        long pow=1;
        for (int i = 0; i < j; i++) {
            pow*=10;
        }return pow;
    }
}
