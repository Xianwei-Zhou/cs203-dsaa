import java.util.Scanner;;

public class Relay {//类似于跳石头问题，该题等价于将一个n+1已排序数组分成m个子数组，求首尾差值最大的子数组的（差值）最小值
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();//树的颗数
        int m=in.nextInt();//人数
        int L=in.nextInt();//总距离
        int[] trees=new int[n+1];
        for (int i = 0; i < n; i++) {
            trees[i]=in.nextInt();
        }
        trees[n]=L;//所有坐标位置（包括最终终点线）
        int[] distances=new int[n];//第一次做这种最大最小问题，减法太绕了，利用每段距离的差值的数组将问题转化为求把该数组分成m个子数组后求子数组的和的最大值的最小值问题（二分法解决）
        for (int i = 0; i < n; i++) {
            distances[i]=trees[i+1]-trees[i];
        }
        int max_num=0;
        int tot_sum=0;
        for (int i = 0; i < n; i++) {//求出了该数组的最大元素以及数组的和（二分法的上下界）
            tot_sum+=distances[i];
            max_num=Math.max(max_num,distances[i]);
        }
        int left=max_num;
        int right=tot_sum;
        int mid;

        while (left<right){//left,right,mid在题干中都是指cc跑的距离（子数组和的最大值）   跳出循环的边界条件是left=right
            mid=left+(right-left)/2;
            if (midIsBig(distances,mid,m))
                right=mid;
            else left=mid+1;
        }
        System.out.println(left);//left，right，mid都一样大了，随便返回一个就行
    }
    private static boolean midIsBig(int[] distances,int mid,int m){
        int count=1;//子数组的个数
        int sum=0;//子数组的和
        for (int distance : distances) {
            if (sum + distance <= mid) {
                sum += distance;
            } else {
                sum = distance;
                count++;
            }
        }
        return count<=m;//如果在当前mid下子数组的个数大于m，则return true
    }
}
