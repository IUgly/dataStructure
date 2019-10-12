package base.tree;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;

/**
 * 二分搜索树
 * 不包含相同元素
 * 也可以将等于放入定义
 * @author kuangjunlin
 */
public class BST <E extends Comparable<E>> {
    private Node root;
    private int size;

    public BST() {
        this.root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty () {
        return size == 0;
    }

    public void add (E e) {
        root = add(root, e);
    }

    public boolean contains (E value) {
        return contains(root, value);
    }
    private boolean contains (Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) == 0) {
            return true;
        }else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    /**
     * 遍历得到的元素是顺序排列
     */
    public void inOrder () {
        inOrder(root);
    }

    public void inOrder (Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 后续遍历应用：
     * 为二分搜索树释放内存
     */
    public void postOrder () {
        postOrder(root);
    }

    public void postOrder (Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    public void preOrder () {
        preOrder(root);
    }

    private void preOrder (Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    private Node add(Node node, E e) {
        if (node == null) {
            size ++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0){
            node.right = add(node.right, e);
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }


    private void generateBSTString (Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth)).append("null\n");
            return;
        }
        res.append(generateDepthString(depth)).append(node.e).append("\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("-");
        }
        return res.toString();
    }


    /**
     * 二叉树层序遍历/广度优先遍历
     * 需要队列
     * 找到问题的解，最短路径
     */
    public void levelOrder () {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println(cur.e);

            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    public E minimum () {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");
        return minimum(root).e;
    }

    private Node minimum (Node node) {
        if (node.left == null)return node;
        return minimum(node.left);
    }
    public E maximun () {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");
        return maximun(root).e;
    }

    public E removeMin () {
        E ret = minimum();
        removeMin(root);
        return ret;
    }

    private Node removeMin (Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public E removeMax() {
        E ret = maximun();
        removeMax(root);
        return ret;
    }

    private Node removeMax (Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }


    private Node maximun (Node node) {
        if (node.right == null) return node;
        return maximun(node.right);
    }

    /**
     * 删除有左右子树的节点
     * 找到该节点的后继 / 该节点的右子树的最小节点 作为新子树的根节点
     * 或者找该节点的前驱，～左子树的最大节点 ～
     */
    public void remove (E e) {
        root = remove(root, e);
    }

    public Node remove (Node node , E e) {
        if (node == null) return null;
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        }else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        }
        else {
            //待删除节点左子树为空时
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            //待删除节点右子树为空时
            if (node.right ==null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            //左右字数均不为空
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }


    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
//        int[] nums = {5,3,2,6,4,8};
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            bst.add(random.nextInt(10000));
        }
        ArrayList<Integer> nums = new ArrayList<>();
        while (!bst.isEmpty()) {
            nums.add(bst.removeMin());
        }
        System.out.println(nums);
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i-1) > nums.get(i)) {
                throw new IllegalArgumentException("Error ");
            }
        }
        System.out.println("Ok");
    }


    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }
}
