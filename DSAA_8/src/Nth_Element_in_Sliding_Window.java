import java.util.*;

public class Nth_Element_in_Sliding_Window {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int k = in.nextInt();
//        avlTree avlTree = new avlTree();
////        库函数（红黑树实现）不支持查找第k小的数，只能手写
////        TreeSet<Integer> avlTree = new TreeSet<>();
//        Queue<Integer> queue = new LinkedList<>();
//        int[] vals = new int[m];
//        for (int i = 0; i < m; i++) {
//            vals[i] = in.nextInt();
//        }
//        for (int i = 0; i < k; i++) {
//            avlTree.add(vals[i]);
//            queue.offer(vals[i]);
//        }
//        System.out.println(avlTree.findKth(in.nextInt()));
//
////        boolean bool=true;
//
//        for (int i = k; i < m; i++) {
//            avlTree.remove(queue.poll());
////            if (!avlTree.isBalanced(avlTree.root))
////                System.out.println("remove");
//            avlTree.add(vals[i]);
////            if (!avlTree.isBalanced(avlTree.root))
////                System.out.println("add");
//            queue.offer(vals[i]);
////            bool&=avlTree.isBalanced(avlTree.root);
//
////            System.out.println(avlTree.findKth(in.nextInt()));
//        }
////        System.out.println(bool);
//
//        //删掉这句话就爆栈了。。。
//        in.close();

        //ac了
        int[] vals=new int[m];
        int[] ns=new int[m-k+1];
        avlTree tree=new avlTree();
        for (int i = 0; i < m; i++) {
            vals[i]=in.nextInt();
        }
        for (int i = 0; i < m-k+1; i++) {
            ns[i]=in.nextInt();
        }
        in.close();
        for (int i = 0; i < k; i++) {
            tree.add(vals[i]);
        }
        System.out.println(tree.findKth(ns[0]));
        for (int i = 0; i < m-k; i++) {
            tree.remove(vals[i]);
            tree.add(vals[k+i]);
            System.out.println(tree.findKth(ns[i+1]));
        }
    }

    static class Node {
        int val;
        Node left;
        Node right;
        int height;
        int subTreeSize;

        public Node(int val) {
            this.val = val;
            height = 1;
            subTreeSize = 1;
        }
    }

    static class avlTree {
        Node root;
        int size = 0;

        public void add(int val) {
            root = addNode(root, new Node(val));
        }

        public void remove(int val) {
            root = remove(root, val);
        }

        public int getHeight(Node node) {
            if (node == null) return 0;
            return node.height;
        }

        public int findKth(int k) {
            return findKth(root, k).val;
        }

        private void updateHeight(Node node) {
            node.height = Math.max(getHeight(node.right), getHeight(node.left)) + 1;
        }

        private int getBalanceFactor(Node node) {
            if (node == null) return 0;
            return getHeight(node.left) - getHeight(node.right);
        }

        private int getSubTreeSize(Node node) {
            if (node == null) return 0;
            return node.subTreeSize;
        }

        private void updateSubTreeSize(Node node) {
            node.subTreeSize = getSubTreeSize(node.left) + getSubTreeSize(node.right) + 1;
        }

        public boolean isBalanced(Node node) {
            if (node == null) return true;
            if (getBalanceFactor(node) > 1 || getBalanceFactor(node) < -1) return false;
            return isBalanced(node.left) && isBalanced(node.right);
        }

        //LL右旋
        private Node rightRotate(Node root) {
            Node b = root.left;
            Node B = b.right;
            b.right = root;
            root.left = B;
            //顺序不能反
            updateHeight(root);
            updateHeight(b);
            updateSubTreeSize(root);
            updateSubTreeSize(b);
            return b;
        }

        //RR左旋
        private Node leftRotate(Node root) {
            Node b = root.right;
            Node B = b.left;
            b.left = root;
            root.right = B;
            updateHeight(root);
            updateHeight(b);
            updateSubTreeSize(root);
            updateSubTreeSize(b);
            return b;
        }

        private Node addNode(Node root, Node node) {
            if (root == null) {
                size++;
                return node;
            }
            if (root.val < node.val)
                root.right = addNode(root.right, node);
            else root.left = addNode(root.left, node);
            updateHeight(root);
            updateSubTreeSize(root);
            int balanceFactor = getBalanceFactor(root);
            //LL
            if (balanceFactor > 1 && getBalanceFactor(root.left) >= 0)
                return rightRotate(root);
            //RR
            else if (balanceFactor < -1 && getBalanceFactor(root.right) <= 0)
                return leftRotate(root);
            //LR
            else if (balanceFactor > 1 && getBalanceFactor(root.left) < 0) {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
            //RL
            else if (balanceFactor < -1 && getBalanceFactor(root.right) > 0) {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
            return root;
        }

        private Node getNode(Node root, int val) {
            if (root == null) return null;
            if (root.val == val) return root;
            if (root.val < val) return getNode(root.right, val);
            else return getNode(root.left, val);
        }

        private Node minNode(Node root) {
            if (root.left == null) return root;
            return minNode(root.left);
        }

        private Node remove(Node root, int val) {
            if (root == null) return null;
            Node tempNode;
            if (root.val < val) {
                root.right = remove(root.right, val);
                tempNode = root;
            } else if (root.val > val) {
                root.left = remove(root.left, val);
                tempNode = root;
            } else {
                if (root.left == null) {
                    tempNode = root.right;
//                    root.right=null;
                    root=null;
                    size--;
                } else if (root.right == null) {
                    tempNode = root.left;
//                    root.left=null;
                    root=null;
                    size--;
                } else {
                    Node successor = minNode(root.right);
                    successor.right = remove(root.right, successor.val);
                    successor.left = root.left;
                    root=null;
//                    root.left=null;
//                    root.right=null;
                    tempNode = successor;
                }
            }
            if (tempNode == null) return null;
            updateHeight(tempNode);
            updateSubTreeSize(tempNode);
            int balanceFactor = getBalanceFactor(tempNode);
            //LL
            if (balanceFactor > 1 && getBalanceFactor(tempNode.left) >= 0)
                return rightRotate(tempNode);
            //RR
            else if (balanceFactor < -1 && getBalanceFactor(tempNode.right) <= 0)
                return leftRotate(tempNode);
            //LR
            else if (balanceFactor > 1 && getBalanceFactor(tempNode.left) < 0) {
                tempNode.left = leftRotate(tempNode.left);
                return rightRotate(tempNode);
            }
            //RL
            else if (balanceFactor < -1 && getBalanceFactor(tempNode.right) > 0) {
                tempNode.right = rightRotate(tempNode.right);
                return leftRotate(tempNode);
            }

            return tempNode;
        }

        private Node findKth(Node root, int k) {
            if (k > root.subTreeSize) return null;
            if (root.left == null && root.right == null) return root;
            if (root.left != null) {
                int left = getSubTreeSize(root.left) + 1;
                if (k == left) return root;
                else if (k < left) return findKth(root.left, k);
                else return findKth(root.right, k - left);
            } else {
                if (k == 1) return root;
                return findKth(root.right, k - 1);
            }
        }
    }

}
