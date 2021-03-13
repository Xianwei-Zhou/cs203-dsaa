import java.util.Scanner;

public class Find_Next {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        String str=in.next();
        int[] next=new int[str.length()];
        next[0]=0;
        int k=0;
        int j=1;
        while (j<str.length()){
            if (str.charAt(j)==str.charAt(k)){
                k+=1;
                next[j]=k;
                j+=1;
            }else if (k==0){
                next[j]=0;
                j+=1;
            }else
                k=next[k-1];
        }
        for (int value : next) {
            System.out.println(value);
        }
    }
}
