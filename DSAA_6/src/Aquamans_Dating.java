import java.util.Scanner;

public class Aquamans_Dating {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        String prefix=in.next();
        String suffix=prefix;
        for (int i = 0; i < n-1; i++) {
            String str=in.next();
            for (int j = 0; j < prefix.length(); j++) {
                if (prefix.charAt(j) != str.charAt(j)) {
                    if (j == 0) {
                        System.out.println(0);
                        return;
                    }
                    prefix = prefix.substring(0, j);
                    break;
                }
            }
            for (int j = 1; j <= suffix.length(); j++) {
                if (str.charAt(str.length()-j)!=suffix.charAt(suffix.length()-j)){
                    if (j==1){
                        System.out.println(0);
                        return;
                    }
                    suffix=suffix.substring(suffix.length()-j+1);
                    break;
                }
            }
        }
        System.out.println(suffix.length()*prefix.length());
    }
}
