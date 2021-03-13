import java.util.Arrays;
import java.util.Scanner;
//第k大数的集合里第m大的元素等价于在数组a中恰好有m个子数组的第k大的数大于等于 answer（为了便于理解实际上可以把所有大于 answer的数看成 answer）
public class Kth_Largest_Elements {//和第四题第五题非常相似，都是二分+一个单调的check函数（而不是按顺序去递归），二分重点在于找到答案的上下界
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int T=in.nextInt();
        int[] ans=new int[T];
        for (int i = 0; i < T; i++) {
            int n=in.nextInt();
            int k=in.nextInt();
            int m=in.nextInt();
            int[] a=new int[n];
//            int[] b=new int[n];
            for (int j = 0; j < n; j++) {
                a[j]=in.nextInt();
//                b[j]=a[j];
            }
//            Arrays.sort(b);//写错了，不想改了，还是直接二分数更简单（复杂度略高）
            int left=0;
            int right= Arrays.stream(a).max().getAsInt();
            int mid;
            while (left<right){//二分查找a[]中的哪个元素能作为 answer
                mid=left+(right-left)/2;
                if (midIsBig(a,mid,m,k)){
                    right=mid;
                }else left=mid+1;
            }
            ans[i]=left-1;
        }
        for (int i = 0; i < T; i++) {
            System.out.println(ans[i]);
        }
    }
    private  static boolean midIsBig(int[] a,int mid, int m,int k){//判定在数组a中至少有 m个子数组的第 k大的数大于等于 m
        long array_nums=0;//子数组的个数，与 m比较
        long num=0;//子数组中比mid大的数的个数,与 k比较   //下次再也不写这么接近的变量名了，，，因为这两个sum浪费了20分钟debug
        int j = 0;
        for (int i = 0; i < a.length; i++) {//尺取（同向的双指针）法   逻辑很简单 代码很绕 debug无数次
            if (a[i] >= mid)
                num++;
            if (num == k) {
                array_nums += a.length - i;
                while (a[j] < mid) {
                    array_nums += a.length - i;
                    j += 1;
                }
                num-=1;//注意这里ij是两个指针而不是循环里套循环，跑一遍就知道为什么要加这句话了
                j+=1;//同上,这题把我写吐了
            }
        }
        return array_nums<m;
    }
}
