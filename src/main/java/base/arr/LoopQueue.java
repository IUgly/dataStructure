package base.arr;

import base.Queue;

/**
 * 出队O(1)
 * @author kuangjunlin
 */
public class LoopQueue<T> implements Queue<T> {
    private T[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity) {
        data = (T[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity () {
        return data.length - 1;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enQueue(T e) {
        if ((tail + 1 ) % data.length == front)
            resize (getCapacity() * 2);
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    public void resize (int newCapacity) {
        T[] newData = (T[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[ (i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public T deQueue() {
        if (isEmpty())
            throw new IllegalArgumentException("cannot dequeue from an empty queue");
        T ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size --;
        if (size == getCapacity() / 4)
            resize(getCapacity() / 2);
        return ret;
    }

    @Override
    public T getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("cannot get front from an empty queue");
        return data[front];
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        ret.append(" [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            ret.append(data[i]);
            if ((i + 1) % data.length != tail)
                ret.append(", ");
        }
        ret.append("] tail");
        return ret.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>();
        for (int i = 0; i < 11; i++) {
            queue.enQueue(i);
        }
        System.out.println(queue.toString());
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        System.out.println(queue.toString());
    }
}
