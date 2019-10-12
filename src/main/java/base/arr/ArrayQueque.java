package base.arr;

import base.Queue;

/**
 * 出队O(n)
 * @author kuangjunlin
 */
public class ArrayQueque <T> implements Queue<T> {
    private Array<T> array;

    public ArrayQueque() {
        this.array = new Array<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void enQueue(T e) {
        this.array.addLast(e);
    }

    public int getCapacity () {
        return array.getCapacity();
    }

    @Override
    public T deQueue() {
        return array.removeFirst();
    }

    @Override
    public T getFront() {
        return array.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("Queue: ").append("front [");
        for (int i = 0; i < getSize(); i++) {
            ret.append(array.get(i));
            if (i != array.getSize() - 1)
                ret.append(", ");
        }
        ret.append("] tail");
        return ret.toString();
    }

    public static void main(String[] args) {
        ArrayQueque<Integer> arrayQueque = new ArrayQueque<>();
        for (int i = 0; i < 5; i++) {
            arrayQueque.enQueue(i);
        }
        System.out.println(arrayQueque.toString());
        arrayQueque.deQueue();
        System.out.println(arrayQueque.toString());
    }
}
