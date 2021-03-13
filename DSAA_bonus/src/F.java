import java.util.*;

public class F {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        Queue<Integer> queue=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for (int i = 0; i < n; i++) {
            if (in.nextInt()==1){
                queue.offer(in.nextInt());
            }else {
                int k=in.nextInt();
                for (int j = 1; j < k; j++) {
                    queue.poll();
                }
                System.out.println(queue.peek());
            }
        }
    }
}

