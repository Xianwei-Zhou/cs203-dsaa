//import java.util.LinkedList;
//import java.util.Queue;
//
//public class avlTree_StackOverFlow {
//
//    public static void main(String[] args) {
//        AVLTree tree = new AVLTree();
//        for (int i = 0; i < 10; i++) {
//            tree.add(1);
//        }
//        for (int i = 0; i < 10; i++) {
//            tree.add(3);
//        }
//        levelTraversal(tree.root);
//        tree.remove(1);
////        levelTraversal(tree.root);
//        tree.add(2);
//
//    }
//    public static void levelTraversal(TreeNode root){
//        Queue<TreeNode> q = new LinkedList<>();
//        q.offer(root);
//        while(!q.isEmpty()){
//            TreeNode node = q.poll();
//            System.out.print(node.getVal()+" ");
//            if(node.left!=null){
//                q.offer(node.left);
//            }
//            if(node.right!=null){
//                q.offer(node.right);
//            }
//        }
//    }
//}
//
//
//class TreeNode {
//    private final long val;
//    private int height;
//    private int subTreeSize;
//    TreeNode left;
//    TreeNode right;
//
//    public TreeNode(long val) {
//        this.val = val;
//        height = 1;
//        subTreeSize = 1;
//        left = null;
//        right = null;
//    }
//
//    public int getSubTreeSize() {
//        return subTreeSize;
//    }
//
//    public void setSubTreeSize(int subTreeSize) {
//        this.subTreeSize = subTreeSize;
//    }
//
//    public long getVal() {
//        return val;
//    }
//
//    public int getHeight() {
//        return height;
//    }
//
//    public void setHeight(int height) {
//        this.height = height;
//    }
//}
//
////构建左节点⽐根⼩，右节点⽐根⼤的balance binary search tree(BBST)
//class AVLTree {
//    TreeNode root;
//    int size = 0;
//
//    public AVLTree() {
//        root = null;
//    }
//
//    private int getHeight(TreeNode node) {
//        if (node == null) {
//            return 0;
//        }
//        return node.getHeight();
//    }
//
//    public void setHeight(TreeNode node) {
//        node.setHeight(Math.max(getHeight(node.left), getHeight(node.right)) + 1);
//    }
//
//    private int getBalanceFactor(TreeNode node) {
//        if (node == null) {
//            return 0;
//        }
//        return getHeight(node.left) - getHeight(node.right);
//    }
//
//    private int getSubTreeSize(TreeNode node) {
//        if (node == null) {
//            return 0;
//        }
//        return node.getSubTreeSize();
//    }
//
//    public void setSubTreeSize(TreeNode node) {
//        node.setSubTreeSize(getSubTreeSize(node.left) + getSubTreeSize(node.right) + 1);
//    }
//
//    //node：插⼊新节点后需要维护平衡的节点，即|BalanceFactor|>1的节点
//    // LL右旋：向左⼦树（L）的左孩⼦（L）中插⼊新节点后导致不平衡
//    private TreeNode rightRotate(TreeNode node) {
//        TreeNode root = node.left;
//        TreeNode node1 = root.right;
//        root.right = node;
//        node.left = node1;
//        setHeight(node);
//        setHeight(root);
//        setSubTreeSize(node);
//        setSubTreeSize(root);
//        return root;
//    }
//
//    //RR左旋：向右⼦树（R）的右孩⼦（R）中插⼊新节点后导致不平衡
//    private TreeNode leftRotate(TreeNode node) {
//        TreeNode root = node.right;
//        TreeNode node1 = root.left;
//        root.left = node;
//        node.right = node1;
//        setHeight(node);
//        setHeight(root);
//        setSubTreeSize(node);
//        setSubTreeSize(root);
//        return root;
//    }
//
//    public void add(long val) {
//        root = add(root, val);
//    }
//
//    public TreeNode add(TreeNode root, long val) {
//        if (root == null) {
//            size++;
//            return new TreeNode(val);
//        }
//        if (val < root.getVal()) {
//            root.left = add(root.left, val);
//        } else {
//            root.right = add(root.right, val);
//        }
//        setHeight(root);
//        setSubTreeSize(root);
//        int BalanceFactor = getBalanceFactor(root);
//        //LL右旋
//        if (BalanceFactor > 1 && getBalanceFactor(root.left) > 0) {
//            return rightRotate(root);
//        }
//        //RR左旋
//        if (BalanceFactor < -1 && getBalanceFactor(root.right) < 0) {
//            return leftRotate(root);
//        }
//        //LR: 先左旋成LL，再右旋
//        if (BalanceFactor > 1 && getBalanceFactor(root.left) < 0) {
//            root.left = leftRotate(root.left);
//            return rightRotate(root);
//        }
//        //RL: 先右旋成RR，再左旋
//        if (BalanceFactor < -1 && getBalanceFactor(root.right) > 0) {
//            root.right = rightRotate(root.right);
//            return leftRotate(root);
//        }
//        return root;
//    }
//
//    public TreeNode getNode(TreeNode root, long val) {
//        if (root == null) {
//            return null;
//        }
//        if (root.getVal() == val) {
//            return root;
//        } else if (root.getVal() < val) {
//            return getNode(root.right, val);
//        } else {
//            return getNode(root.left, val);
//        }
//    }
//
//    public TreeNode minNode(TreeNode root) {
//        if (root.left == null) {
//            return root;
//        }
//        return minNode(root.left);
//    }
//
//    public void remove(long val) {
//        TreeNode node = getNode(root, val);
//        if (node != null) {
//            root = remove(root, val);
//        }
//    }
//
//    public TreeNode remove(TreeNode root, long val) {
//        if (root == null) {
//            return null;
//        }
//        TreeNode retNode;
//        //retNode: 顶替待删除节点位置的节点
//        if (val < root.getVal()) {
//            root.left = remove(root.left, val);
//            retNode = root;
//        } else if (val > root.getVal()) {
//            root.right = remove(root.right, val);
//            retNode = root;
//        } else { //val = root.getVal()
//            //待删除节点左⼦树为空
//            if (root.left == null) {
//                TreeNode rightNode = root.right;
//                root.right = null;
//                size--;
//                retNode = rightNode;
//            }
//            //待删除节点右⼦树为空
//            else if (root.right == null) {
//                TreeNode leftNode = root.left;
//                root.left = null;
//                size--;
//                retNode = leftNode;
//            }
//            //待删除节点左右⼦树均不为空
//            //找到⽐待删除节点⼤的最⼩节点，即删除节点右⼦树的最⼩节点
//            //⽤这个最⼩节点顶替待删除节点的位置
//            else {
//                TreeNode successor = minNode(root.right);
//                successor.right = remove(root.right, successor.getVal());
//                successor.left = root.left;
//                root.left = null;
//                root.right = null;
//                retNode = successor;
//            }
//        }
//
//        if (retNode == null) {
//            return null;
//        }
//
//        setHeight(retNode);
//        setSubTreeSize(retNode);
//        int BalanceFactor = getBalanceFactor(retNode);
//
//        //LL右旋
//        if (BalanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
//            return rightRotate(retNode);
//        }
//        //RR左旋
//        if (BalanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
//            return leftRotate(retNode);
//        }
//        //LR: 先左旋成LL，再右旋
//        if (BalanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
//            retNode.left = leftRotate(retNode.left);
//            return rightRotate(retNode);
//        }
//        //RL: 先右旋成RR，再左旋
//        if (BalanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
//            retNode.right = rightRotate(retNode.right);
//            return leftRotate(retNode);
//        }
//        return retNode;
//    }
//}