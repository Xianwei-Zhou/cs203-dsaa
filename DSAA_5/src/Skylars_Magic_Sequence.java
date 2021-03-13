import java.util.LinkedList;
import java.util.Scanner;

//单调队列  队列中元素之间的关系具有单调性，而且队首和队尾都可以进行出队操作，只有队尾可以进行入队操作。（普通队列是头部出尾部进）
//使用双端队列来实现单调队列
//思路主要来源于 leetcode 1438
class MoQueue {
    LinkedList<Integer> deque;

    public MoQueue() {
        deque = new LinkedList<>();
    }

    public void enQueue(int val) {
        deque.addLast(val);
    }

    public void deQueueFront() {
        deque.removeFirst();
    }

    public void deQueueRear() {
        deque.removeLast();
    }

    public int getRear() {
        return deque.getLast();
    }

    public int getFront() {
        return deque.getFirst();
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }
}

public class Skylars_Magic_Sequence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //为了避免不必要的麻烦，max和min存的都是index而不是value
        MoQueue max = new MoQueue();//递减
        MoQueue min = new MoQueue();//递增
        int k = in.nextInt();
        int n = in.nextInt();
        int[] input = new int[n];
        int left = 0;//窗口左沿
        int ans = 0;
        //left是窗口左沿，i是窗口右沿
        for (int i = 0; i < n; i++) {
            int val = in.nextInt();
            input[i] = val;
            while (!max.isEmpty() && val > input[max.getRear()]) {
                max.deQueueRear();
            }
            max.enQueue(i);
            while (!min.isEmpty() && val < input[min.getRear()]) {
                min.deQueueRear();
            }
            min.enQueue(i);
            while (!max.isEmpty() && !min.isEmpty() && input[max.getFront()] - input[min.getFront()] > k) {
                if (max.getFront() == left) max.deQueueFront();
                if (min.getFront() == left) min.deQueueFront();
                left += 1;
            }
            ans = Math.max(ans, i - left + 1);
        }
        System.out.println(ans);
    }
}
