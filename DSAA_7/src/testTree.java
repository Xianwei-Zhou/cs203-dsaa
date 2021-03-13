import java.util.Scanner;

public class testTree {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        String s1=in.nextLine();
        String s2=in.nextLine();
        String s3=in.nextLine();
        String s4=in.nextLine();
        String s5=in.nextLine();
        String s6=in.nextLine();
        boolean a,b,c;
        System.out.println(a=s1.equals(s2));
        System.out.println(b=s4.equals(s3));
        System.out.println(c=s5.equals(s6));
        if (a&&b&&c){
            System.out.println("perfect! "+ '\u2665'+"  "+"\uD83D\uDC02"+"\uD83D\uDC02"+"\uD83D\uDC02");
        }

    }
}
