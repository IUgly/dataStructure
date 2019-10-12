package base.setandmap;

import base.tree.BST;

/**
 * 二分搜索树实现Set
 * O(h)
 * 如果二叉树为满二叉树：h = O(logn)
 * @author kuangjunlin
 */
public class BSTSet <E extends Comparable<E>> implements Set<E>{
    private BST<E> bst;

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int getSize() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    public BSTSet() {
        this.bst = new BST<>();
    }
}
