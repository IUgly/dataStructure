package base.setandmap;

import base.list.LinkedList;

import java.util.Random;

/**
 * 基于链表实现Set集合
 * O(n)
 * @author kuangjunlin
 */
public class LinkedListSet<E> implements Set<E> {
    private LinkedList<E> list;

    /**
     * 增加不重复元素
     * @param e
     */
    @Override
    public void add(E e) {
        if(!list.contains(e)) {
            list.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public LinkedListSet() {
        this.list = new LinkedList<>();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[ ");
        for (int i = 0; i < getSize(); i++) {
            res.append(list.get(i)).append(", ");
        }
        res.append(" ]");
        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListSet<Integer> linkedList = new LinkedListSet<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            linkedList.add(random.nextInt(2220000));
        }
        System.out.println(linkedList);
    }
}
