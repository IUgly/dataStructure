package base.arr;

import base.Queue;

/**
 * @author kuangjunlin
 */
public class LoopArrayQueue<T> implements Queue<T> {
    T[] data;
    int front, tail;
    int size;

    public LoopArrayQueue(int capacity) {
        this.data = (T[]) new Object[capacity];
        front =0 ;
        tail = 0;
        size = 0;
    }

    public LoopArrayQueue() {
        this(10);
    }

    @Override
    public int getSize() {
        return data.length;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enQueue(T e) {
        if ((tail + 1) % data.length == front)
            resize(data.length * 2);
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    @Override
    public T deQueue() {
        if (isEmpty())
            throw new IllegalArgumentException("cannot dequeue from empty queue");
        T e = data[front];
        data[front] = null;
        size--;
        if (size < data.length / 4)
            resize(getCapacity() / 2);
        return e;
    }

    public int getCapacity (){
        return data.length - 1;
    }

    private void resize (int newCapacity) {
        T[] newData = (T[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public T getFront() {
        return data[front];
    }
}
