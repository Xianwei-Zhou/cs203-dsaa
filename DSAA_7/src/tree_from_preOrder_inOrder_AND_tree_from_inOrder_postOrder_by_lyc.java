public class tree_from_preOrder_inOrder_AND_tree_from_inOrder_postOrder_by_lyc {
    public static void main(String[] args) {
        //test
        TreeNode root = tree_from_preOrder_inOrder(new int[]{1, 2, 4, 3, 5}, new int[]{2, 4, 1, 5, 3}, 0, 4, 0, 4);
        traverse_a_tree_by_lyc.postOrder_Iterative(root);
        System.out.println();
        TreeNode root2 = preOrder_from_inOrder_postOrder(new int[]{2, 4, 1, 5, 3}, new int[]{4, 2, 5, 3, 1}, 0, 4, 0, 4);
        traverse_a_tree_by_lyc.preOrder_Iterative(root2);
    }

    // l1: pre中子树的起点 r1: pre中子树的终点 l2: in中子树的起点 r2: in中子树的终点
    public static TreeNode tree_from_preOrder_inOrder(int[] pre, int[] in, int l1, int r1, int l2, int r2) {
        if (l2 > r2) {
            return null;
        }
        TreeNode root = new TreeNode(pre[l1]);
        int count = 0;
        int i;
        for (i = l2; in[i] != pre[l1]; i++) {
            count++;
        }
        root.left = tree_from_preOrder_inOrder(pre, in, l1 + 1, l1 + count, l2, i - 1);
        root.right = tree_from_preOrder_inOrder(pre, in, l1 + count + 1, r1, i + 1, r2);
        return root;
    }

    // l1: in中子树的起点 r1: in中子树的终点 l2: post中子树的起点 r2: post中子树的终点
    public static TreeNode preOrder_from_inOrder_postOrder(int[] in, int[] post, int l1, int r1, int l2, int r2) {
        if (l1 > r1) {
            return null;
        }
        TreeNode root = new TreeNode(post[r2]);
        int count = 0;
        int i;
        for (i = l1; in[i] != post[r2]; i++) {
            count++;
        }
        root.left = preOrder_from_inOrder_postOrder(in, post, l1, i - 1, l2, l2 + count - 1);
        root.right = preOrder_from_inOrder_postOrder(in, post, i + 1, r1, l2 + count, r2 - 1);
        return root;
    }


}
