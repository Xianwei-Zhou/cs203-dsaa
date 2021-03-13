import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

//二分+哈希
public class Looking_for_a_team {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.next();
        String s2 = in.next();
        int right = Math.min(s1.length(), s2.length());
        int left = 0;
        while (left < right) {//二分找到满足check函数的最大值（找最小的最大和最大的最小mid那里不同）
            int mid = (right + left + 1) / 2;
            if (check(mid, s1, s2)) {
                left = mid;
            } else right = mid - 1;

        }
        System.out.println(left);
    }

    public static boolean check(int n, String s1, String s2) {
        long hashS1 = 0;
        long hashS2 = 0;
        if (n == 0) return true;
        for (int i = 0; i < n; i++) {
            hashS1 = (hashS1 * base + (int) s1.charAt(i)) % mod;
            hashS2 = (hashS2 * base + (int) s2.charAt(i)) % mod;
        }
        HashSet<Long> hashSet = new HashSet<>();
        hashSet.add(hashS1);
//        long h = modPow(n - 1);
        long h = quickModPow(n - 1);
        for (int i = 0; i < s1.length() - n; i++) {
            hashS1 = (base * (hashS1 - (int) s1.charAt(i) * h % mod + mod) % mod + (int) s1.charAt(i + n)) % mod;
            hashSet.add(hashS1);
        }
        if (hashSet.contains(hashS2)) {//hashSet.contains复杂度是O(1)
            if (s1.contains(s2.substring(0, n))) return true;
        }//用hash值相等得到的两个位置去比较更简单,但在哈希碰撞不多时这么写也没啥
        for (int i = 0; i < s2.length() - n; i++) {
            hashS2 = (base * (hashS2 - (int) s2.charAt(i) * h % mod + mod) % mod + (int) s2.charAt(i + n)) % mod;
            if (hashSet.contains(hashS2)) {
                if (s1.contains(s2.substring(i + 1, i + 1 + n))) return true;
            }
        }

        return false;
    }

    public static final int base = 128;//ascii码范围是0-127
    public static final long mod = 402653189;//题目数据范围n是10^5,由生日悖论mod取离10^10最近的好的质数 https://planetmath.org/goodhashtableprimes

    public static long modPow(int n) {
        long pow = 1;
        for (int i = 0; i < n; i++) {
            pow = pow * base % mod;
        }
        return pow;
    }

    public static long quickModPow(int b) {//快速幂
        long a = base;
        long ans = 1;
        while (b != 0) {
            if ((b & 1) != 0) {
                ans = (ans * a) % mod;
            }
            b >>= 1;
            a = (a * a) % mod;
        }
        return ans;
    }
}
