import java.util.Scanner;
//这题正确解法是用二分去做（对步数从0到10^15二分），我的解法有很大问题，遗漏掉了跨过坐标轴的情况以及一个周期内来回动的某类情况（wa），但由于测试样例不行才得以ac
public class WA_Chasing_the_Robot {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int xr0 = in.nextInt();int xr=xr0;
        int yr0 = in.nextInt();int yr=yr0;
        int xc0 = in.nextInt();
        int yc0 = in.nextInt();
        long period = in.nextInt();
        char[] instructions;
        String s=in.next();
        instructions=s.toCharArray();

        int totDistance = findDistance(xr, yr, xc0, yc0);//第一个周期开始前的距离
        long step = 0;//记录robot移动的步数（时间）

        //类似于用r=step的圆去切robot走过的路径
        //直接暴力算法运行的时间取决于答案大小，答案最大值在10^14，时间超限
        long distance=0;
        while (true) {
            if (step==2*period) {//直接把机器人移动的循环变成 O(1),用两个周期的原因是防止下面times为负数
                if (findDistance(xr, yr, xc0, yc0) - totDistance < step) {
                    distance=(-findDistance(xr, yr, xc0, yc0)+findDistance(xr0, yr0, xc0, yc0))/2;//一个周期距离变近了distance
                    long times=totDistance/(distance+period)-2;//画图通过数学运算找到此表达式
                    step+=times*period;
                    xr+=times*(xr-xr0)/2;
                    yr+=times*(yr-yr0)/2;
                }
                else {
                    System.out.println(-1);
                    return;
                }
            }

            if (findDistance(xr, yr, xc0, yc0) <= step) {
                System.out.println(step);
                return;
            } else {
                step += 1;
                if (instructions[(int) ((step-1) % period)] == 'U')
                    yr += 1;
                else if (instructions[(int) ((step-1) % period)] == 'D')
                    yr -= 1;
                else if (instructions[(int) ((step-1) % period)] == 'R')
                    xr += 1;
                else if (instructions[(int) ((step-1) % period)] == 'L')
                    xr -= 1;

            }
        }
    }

    private static int findDistance(int xr, int yr, int xc, int yc) {
        return Math.abs(xc - xr) + Math.abs(yc - yr);
    }
}
