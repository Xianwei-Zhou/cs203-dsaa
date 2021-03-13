import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

//所有的 1+2*3种遍历方法
public class traverse_a_tree_by_lyc {
    public static void main(String[] args) {
        TreeNode root = randomTree(777);
        preOrder_Iterative(root);
        System.out.println();
        preOrder_Recursive(root);
        System.out.println();
        postOrder_Iterative(root);
        System.out.println();
        postOrder_Recursive(root);
        System.out.println();
        inOrder_Iterative(root);
        System.out.println();
        inOrder_Recursive(root);

    }

    //随机生成一棵树
    public static TreeNode randomTree(int n) {
        Random random = new Random();
        if (n == 0)
            return null;

        TreeNode root = new TreeNode(random.nextInt(10000));

        int leftN = random.nextInt(n);

        root.setLeft(randomTree(leftN));
        root.setRight(randomTree(n - leftN - 1));

        return root;

    }

    //广度优先遍历
    public static void levelTraversal(TreeNode root){
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            System.out.print(node.getVal()+" ");
            if(node.left!=null){
                q.offer(node.left);
            }
            if(node.right!=null){
                q.offer(node.right);
            }
        }
    }

    // preorder traversal: root left right
    public static void preOrder_Recursive(TreeNode root) {
        System.out.print(root.getVal() + " ");
        if (root.left != null) {
            preOrder_Recursive(root.left);
        }
        if (root.right != null) {
            preOrder_Recursive(root.right);
        }
    }

    public static void preOrder_Iterative(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode node = s.pop();
            System.out.print(node.getVal() + " ");
            if (node.right != null) {
                s.push(node.right);
            }
            if (node.left != null) {
                s.push(node.left);
            }
        }
    }

    // postorder traversal: left right root
    public static void postOrder_Recursive(TreeNode root) {
        if (root.left != null) {
            postOrder_Recursive(root.left);
        }
        if (root.right != null) {
            postOrder_Recursive(root.right);
        }
        System.out.print(root.getVal() + " ");
    }

    public static void postOrder_Iterative(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        TreeNode node = s.peek();
        while (node.left != null) {
            node = node.left;
            s.push(node);
        }
        TreeNode last = null; //记录访问过的右节点防止死循环
        while (!s.isEmpty()) {
            node = s.peek();
            if (node.right == null || last == node.right) {
                System.out.print(node.getVal() + " ");
                last = node;
                s.pop();
            } else {
                node = node.right;
                s.push(node);
                while (node.left != null) {
                    node = node.left;
                    s.push(node);
                }
            }
        }
    }

    // inorder traversal: left root right
    public static void inOrder_Recursive(TreeNode root) {
        if (root.left != null) {
            inOrder_Recursive(root.left);
        }
        System.out.print(root.getVal() + " ");
        if (root.right != null) {
            inOrder_Recursive(root.right);
        }
    }

    public static void inOrder_Iterative(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        TreeNode node = s.peek();
        while (node.left != null) {
            node = node.left;
            s.push(node);
        }
        while (!s.isEmpty()) {
            node = s.pop();
            System.out.print(node.getVal() + " ");
            if (node.right != null) {
                node = node.right;
                s.push(node);
                while (node.left != null) {
                    node = node.left;
                    s.push(node);
                }
            }
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getVal() {
        return val;
    }


}
