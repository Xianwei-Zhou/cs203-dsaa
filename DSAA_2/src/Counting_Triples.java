import java.util.Scanner;

public class Counting_Triples {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int S=in.nextInt();
        int[] array=new int[n];
        for (int i = 0; i < n; i++) {
            array[i]=in.nextInt();
        }
        long count=0;//计数，就输出这个  //int的范围只到21亿

        if (n<3){
            System.out.println(0);
            return;
        }
        if (n==3){
            int sum=0;
            for (int i = 0; i < 3; i++) {
                sum+=array[i];
            }if (sum==S)
                System.out.println(1);
            else System.out.println(0);
            return;
        }
        for (int i = 0; i <= n-3; i++) {//仅考虑n>3的情况(前面讨论了n<=3的情况) //先遍历选定一个数
            int sum=S-array[i];
            int start=i+1;
            int end=n-1;
            while (start<end){//在剩下的数组里求两个数之和是否等于某数，从两边向中间遍历（一边是加一边是减）复杂度仅为 n（整个算法为O(n^2)）
                //这里既要考虑全部重复也要考虑部分重复数字的情况，如8 8\n 1 2 2 3 3 4 4 4 4（也可合并写）
                if (array[start]+array[end]==sum){
                    if (array[start]==array[end]){//考虑剩余所有数全相等的极端情况,直接求出此种 i下的所有count
                        int repeat=end-start;
                        count+=((long) repeat *(repeat+1))/2;
                        break;
                    }
                    //部分重复数字
                    int start_repeat=1;
                    int end_repeat=1;
                    while (start + start_repeat <= end-1 && array[start + start_repeat] == array[start])
                        start_repeat+=1;
                    while (start + start_repeat +end_repeat<= end && array[end - end_repeat] == array[end])
                        end_repeat+=1;
                    count+= (long) start_repeat *end_repeat;
                    start += start_repeat;
                    end -= end_repeat;
                }else if (array[start]+array[end]<sum){
                    start+=1;
                }else end-=1;
            }
        }
        System.out.println(count);
    }
}
