import java.util.Random;

public class F_test {
    public static void main(String[] args) {
        Random random=new Random();
        int n= 200;
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            System.out.println(random.nextInt(2)+" "+(1+random.nextInt(20000)));
        }
    }
}
