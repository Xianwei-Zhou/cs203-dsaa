import java.util.Scanner;

public class Chasing_the_Robot_byWYF
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        long xr = input.nextLong();
        long yr = input.nextLong();
        long xc = input.nextLong();
        long yc = input.nextLong();
        int n = input.nextInt();
        String s = input.next();
        char[] step = s.toCharArray();
        long zhouQiStep = 0;
        for (char a : step)
        {
            switch (a)
            {
                case 'D':
                    zhouQiStep--;
                    break;
                case 'U':
                    zhouQiStep++;
                    break;
                case  'L':
                    zhouQiStep--;
                    break;
                case  'R':
                    zhouQiStep++;
                    break;
            }
        }
        long min = 0;
        long max = (long) Math.pow(10, 14);
        long mid = 0;
        while (min < max)
        {
            mid = (min + max) / 2;
            if(isBig(mid, step, xr, yr, xc, yc))
            {
                max = mid;
            }
            else
            {
                min = mid + 1;
            }
            System.out.println(mid);
        }
        System.out.println(min);
    }

    public static boolean isBig(long mid, char[] step, long xr, long yr, long xc, long yc)
    {
        long xr1 = xr;
        long yr1 = yr;
        long time = 0;
        int yesOrNo = 0;
        while (xr1 <= 1000000000 && yr1 <= 1000000000)
        {
            for (char a : step)
            {
                if (xr1 <= 1000000000 && yr1 <= 1000000000 && xr1 >= 0 && yr1 >= 0)
                {
                    time++;
                    switch (a)
                    {
                        case 'D':
                            yr1--;
                            break;
                        case 'U':
                            yr1++;
                            break;
                        case  'L':
                            xr1--;
                            break;
                        case  'R':
                            xr1++;
                            break;
                    }
                }
                else
                {
                    yesOrNo = 1;
                    break;
                }
                if(Math.abs(yc - yr1) + Math.abs(xc - xr1) == time && time<=mid)
                {
                    return true;
                }
                else if(Math.abs(yc - yr1) + Math.abs(xc - xr1) == time && time>mid)
                {
                    return false;
                }
            }
            if(yesOrNo == 1)
                break;
        }
        return false;
    }
}
