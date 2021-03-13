import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class Simple_Sum {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int T=in.nextInt();
        long[] ans=new long[T];
        for (int i = 0; i < T; i++) {
            long n=in.nextLong();
            BigInteger mod=new BigInteger(String.valueOf(1000000007));
            BigInteger nn=new BigInteger(String.valueOf(n));
            BigInteger bigDecimal1=new BigInteger(String.valueOf(nn.multiply(nn)));
            BigInteger bigDecimal2=new BigInteger(String.valueOf(nn.multiply(BigInteger.valueOf(2)).add(BigInteger.valueOf(1))));
            bigDecimal2=bigDecimal2.add(bigDecimal1);
//            bigDecimal1=bigDecimal1.mod(mod);
//            bigDecimal2=bigDecimal2.mod(mod);
            BigInteger answer=new BigInteger(String.valueOf(0));
            answer=(bigDecimal1.multiply(bigDecimal2).divide(BigInteger.valueOf(4)));
            answer=answer.mod(mod);
            ans[i]=answer.longValue();
//            ans[i]=((n%1000000007)*(n%1000000007))*(((n+1)%1000000007)*((n+1)%1000000007))/4%(1000000007);
//            ans[i]=Math.floorMod((Math.floorMod(n,1000000007)*Math.floorMod(n,1000000007))*(Math.floorMod(n+1,1000000007))*(Math.floorMod(n+1,1000000007))/4,(1000000007));

//            Math.floorMod(n,1000000007)
        }
        for (int i = 0; i < T; i++) {
            System.out.println(ans[i]);
        }
    }
}
