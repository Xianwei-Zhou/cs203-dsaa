import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

public class Pet_Adoption {
    static class Node{
        long val;
        int index;

        public Node(long val, int index) {
            this.val = val;
            this.index = index;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Comparator<Node> comparator=new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.val==o2.val) return o1.index-o2.index;
                return (o1.val-o2.val)>0?1:-1;
            }
        };
        TreeSet<Node> pets = new TreeSet<>(comparator);
        TreeSet<Node> adopters = new TreeSet<>(comparator);
        long ans=0;
        for (int i = 0; i < n; i++) {
            if (in.nextInt() == 0) {
                ans = getAns(in, pets, adopters, ans,i);
//                System.out.println(ans);
            } else {
                ans = getAns(in, adopters, pets, ans,i);
//                System.out.println(ans);
            }
        }
        System.out.println(ans);
    }

    private static long getAns(Scanner in, TreeSet<Node> pets, TreeSet<Node> adopters, long ans,int i) {
        if (adopters.isEmpty())
            pets.add(new Node(in.nextInt(),i));
        else {
            int val = in.nextInt();
            Node a = null;
            Node b = null;
            try {
                a = adopters.subSet(new Node(0,-1), new Node(val,-1)).last();
            } catch (Exception ignored) {
            }
            try {
                b = adopters.subSet(new Node(val,-1), new Node(Integer.MAX_VALUE + 1L,-1)).first();
            } catch (Exception ignored) {
            }

            if (b==null||(a!=null&&val-a.val< b.val -val)){
                ans+=val-a.val;
                adopters.remove(a);
            }else if (a==null||val-a.val> b.val -val){
                ans+=b.val-val;
                adopters.remove(b);
            }else {
                ans+=val-a.val;
                adopters.remove(a);
            }
        }
        return ans;
    }
}
