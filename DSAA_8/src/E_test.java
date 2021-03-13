import java.util.HashSet;
import java.util.Random;

public class E_test {
    public static void main(String[] args) {
        Random random=new Random();
        int m=random.nextInt(10000);
        int k=random.nextInt(m);
        System.out.println(m+" "+k);
        HashSet<Integer> set=new HashSet<>();
        for (int i = 0; i < m; i++) {
            int randomNum=random.nextInt(m*m);
            if (set.contains(randomNum)) i-=1;
            else {
                set.add(randomNum);
                System.out.print(randomNum + " ");
            }
        }
        System.out.println();
        for (int i = 0; i < m-k+1; i++) {
            System.out.print(random.nextInt(k)+1+" ");
        }
    }
}
