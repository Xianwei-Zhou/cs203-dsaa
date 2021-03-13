//import java.util.Scanner;
//
//public class F_by_lyc {
//
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        AVLTree p = new AVLTree();
//        AVLTree q = new AVLTree();
//        long sum = 0;
//        for (int i = 0; i < n; i++) {
//            int a = in.nextInt();
//            long b = in.nextLong();
//            if(a==0){
//                if(q.isEmpty()){
//                    p.add(b);
//                }else{
//                    TreeNode node = q.findMinimumClosest(b);
//                    sum+=Math.abs(node.getVal()-b);
//                    q.remove(node);
//                }
//            }else if(a==1){
//                if(p.isEmpty()){
//                    q.add(b);
//                }else{
//                    TreeNode node = p.findMinimumClosest(b);
//                    sum+=Math.abs(node.getVal()-b);
//                    p.remove(node);
//                }
//            }
//        }
//        System.out.println(sum);
//        in.close();
//    }
//}
//class TreeNode{
//    private long val;
//    private int height;
//    private int subTreeSize;
//    TreeNode left;
//    TreeNode right;
//    public TreeNode(long val){
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
//    public void setVal(long val) {
//        this.val = val;
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
////构建左节点比根小，右节点比根大或相等的balance binary search tree(BBST)
//class AVLTree{
//    TreeNode root;
//    int size = 0;
//    public AVLTree(){
//        root = null;
//    }
//
//    public int size() {
//        return size;
//    }
//
//    public boolean isEmpty(){
//        return size==0;
//    }
//
//    private int getHeight(TreeNode node){
//        if(node == null){
//            return 0;
//        }
//        return node.getHeight();
//    }
//    public void setHeight(TreeNode node){
//        node.setHeight(Math.max(getHeight(node.left), getHeight(node.right))+1);
//    }
//
//    private int getBalanceFactor(TreeNode node){
//        if(node == null){
//            return 0;
//        }
//        return getHeight(node.left)-getHeight(node.right);
//    }
//
//    private int getSubTreeSize(TreeNode node){
//        if(node == null){
//            return 0;
//        }
//        return node.getSubTreeSize();
//    }
//
//    public void setSubTreeSize(TreeNode node){
//        node.setSubTreeSize(getSubTreeSize(node.left)+getSubTreeSize(node.right)+1);
//    }
//
//    public boolean isBalanced(TreeNode node){
//        if(node == null){
//            return true;
//        }
//        int BalanceFactor = Math.abs(getBalanceFactor(node));
//        if(BalanceFactor>1){
//            return false;
//        }
//        return isBalanced(node.left) && isBalanced(node.right);
//    }
//
//    public boolean isBalanced(){
//        return isBalanced(root);
//    }
//
//    //node：插入新节点后需要维护平衡的节点，即|BalanceFactor|>1的节点
//    // LL右旋：向左子树（L）的左孩子（L）中插入新节点后导致不平衡
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
//    //RR左旋：向右子树（R）的右孩子（R）中插入新节点后导致不平衡
//    private TreeNode leftRotate(TreeNode node){
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
//    //添加节点
//    public void add(TreeNode node){
//        root = add(root, node.getVal());
//    }
//
//    public void add(long val){
//        root = add(root, val);
//    }
//
//    public TreeNode add(TreeNode root, long val){
//        if(root == null){
//            size++;
//            return new TreeNode(val);
//        }
//        if(val<root.getVal()){
//            root.left = add(root.left, val);
//        }else{
//            root.right = add(root.right, val);
//        }
//        setHeight(root);
//        setSubTreeSize(root);
//        int BalanceFactor = getBalanceFactor(root);
//        //LL右旋
//        if(BalanceFactor>1 && getBalanceFactor(root.left)>0){
//            return rightRotate(root);
//        }
//        //RR左旋
//        if(BalanceFactor<-1 && getBalanceFactor(root.right)<0){
//            return leftRotate(root);
//        }
//        //LR: 先左旋成LL，再右旋
//        if(BalanceFactor>1 && getBalanceFactor(root.left)<0){
//            root.left = leftRotate(root.left);
//            return rightRotate(root);
//        }
//        //RR: 先右旋成RR，再左旋
//        if(BalanceFactor<-1 && getBalanceFactor(root.right)>0){
//            root.right = rightRotate(root.right);
//            return leftRotate(root);
//        }
//        return root;
//    }
//
//    public TreeNode getNode(TreeNode root, long val){
//        if(root == null){
//            return null;
//        }
//        if(root.getVal() == val){
//            return root;
//        }else if(root.getVal() < val){
//            return getNode(root.right, val);
//        }else{
//            return getNode(root.left, val);
//        }
//    }
//    public TreeNode max(){
//        return maxNode(root);
//    }
//
//    public TreeNode maxNode(TreeNode root){
//        if(root.right == null){
//            return root;
//        }
//        return maxNode(root.right);
//    }
//    public TreeNode min(){
//        return minNode(root);
//    }
//
//    public TreeNode minNode(TreeNode root){
//        if(root.left == null){
//            return root;
//        }
//        return minNode(root.left);
//    }
//
//
//    //删除节点
//    public long remove(TreeNode node){
//        if(node!=null){
//            root = remove(root, node.getVal());
//            return node.getVal();
//        }
//        return -1;
//    }
//    public long remove(long val){
//        TreeNode node = getNode(root, val);
//        if(node != null){
//            root = remove(root, val);
//            return node.getVal();
//        }
//        return -1;
//    }
//    public TreeNode remove(TreeNode root, long val){
//        if(root == null){
//            return null;
//        }
//        TreeNode retNode;
//        //retNode: 顶替待删除节点位置的节点
//        if(val<root.getVal()){
//            root.left = remove(root.left, val);
//            retNode = root;
//        }else if(val>root.getVal()){
//            root.right = remove(root.right, val);
//            retNode = root;
//        }else{ //val = root.getVal()
//            //待删除节点左子树为空
//            if(root.left == null){
//                TreeNode rightNode = root.right;
//                root.right = null;
//                size--;
//                retNode = rightNode;
//            }
//            //待删除节点右子树为空
//            else if(root.right == null){
//                TreeNode leftNode = root.left;
//                root.left = null;
//                size--;
//                retNode = leftNode;
//            }
//            //待删除节点左右子树均不为空
//            //找到比待删除节点大的最小节点，即删除节点右子树的最小节点
//            //用这个最小节点顶替待删除节点的位置
//            else{
//                TreeNode successor = minNode(root.right);
//                successor.right = remove(root.right, successor.getVal());
//                successor.left = root.left;
//                root.left = null;
//                root.right = null;
//                retNode = successor;
//            }
//
//        }
//        if(retNode == null){
//            return null;
//        }
//        setHeight(retNode);
//        setSubTreeSize(retNode);
//        int BalanceFactor = getBalanceFactor(retNode);
//        //LL右旋
//        if(BalanceFactor>1 && getBalanceFactor(retNode.left)>=0){
//            return rightRotate(retNode);
//        }
//        //RR左旋
//        if(BalanceFactor<-1 && getBalanceFactor(retNode.right)<=0){
//            return leftRotate(retNode);
//        }
//        //LR: 先左旋成LL，再右旋
//        if(BalanceFactor>1 && getBalanceFactor(retNode.left)<0){
//            retNode.left = leftRotate(retNode.left);
//            return rightRotate(retNode);
//        }
//        //RR: 先右旋成RR，再左旋
//        if(BalanceFactor<-1 && getBalanceFactor(retNode.right)>0){
//            retNode.right = rightRotate(retNode.right);
//            return leftRotate(retNode);
//        }
//        return retNode;
//    }
//
//    //找到第k小的节点值
//    public long findKth(int k){
//        TreeNode node = findKth(root, k);
//        if(node!=null){
//            return node.getVal();
//        }
//        return -1;
//    }
//
//    public TreeNode findKth(TreeNode root, int k){
//        if(k>getSubTreeSize(root)){
//            return null;
//        }
//        if(root.left==null&&root.right==null){
//            return root;
//        }else if(root.left!=null){
//            int now = getSubTreeSize(root.left)+1;
//            if(now == k){
//                return root;
//            }else if(now>k){
//                return findKth(root.left, k);
//            }else{
//                return findKth(root.right, k-now);
//            }
//        }else{
//            if(k==1){
//                return root;
//            }else{
//                return findKth(root.right, k-1);
//            }
//        }
//    }
//
//    //node: 目前最接近val的节点
//    public TreeNode findMinimumClosest(TreeNode root, TreeNode node, long val){
//        if(Math.abs(root.getVal()-val)<Math.abs(node.getVal()-val)){
//            node = root;
//        }else if(Math.abs(root.getVal()-val)==Math.abs(node.getVal()-val) && root.getVal()<node.getVal()){
//            node = root;
//        }
//        if(val<root.getVal() && root.left!=null){
//            return findMinimumClosest(root.left, node, val);
//        }
//        if(val>root.getVal() && root.right!=null){
//
//            return findMinimumClosest(root.right, node, val);
//        }
//        return node;
//    }
//    public TreeNode findMinimumClosest(long val){
//        return findMinimumClosest(root, root, val);
//    }
//
//}