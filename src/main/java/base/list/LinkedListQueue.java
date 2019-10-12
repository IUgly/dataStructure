package base.list;

import base.Queue;

import java.util.stream.IntStream;

/**
 * @author kuangjunlin
 */
public class LinkedListQueue<T> implements Queue<T> {
    private Node head, tail;
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public void enQueue(T e) {
        if (tail == null) {
            tail = new Node(e);
            head = tail;
        }
        else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size ++;
    }

    @Override
    public T deQueue() {
        if (isEmpty())
            throw new IllegalArgumentException("cannot dequeue from empty queue");
        Node retNode = head;
        head = head.next;
        retNode.next = null;
        if (head == null)
            tail = null;
        size--;
        return retNode.e;
    }

    @Override
    public T getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("cannot dequeue from empty queue");
        return head.e;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("Queue: front ");
        for (Node cur = head; cur != null; cur = cur.next) {
            ret.append(cur).append("->");
        }
        ret.append("NULL tail");
        return ret.toString();
    }

    private class Node {
        T e;
        Node next;

        public Node(T e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(T e) {
            this.e = e;
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> listQueue = new LinkedListQueue<>();
        IntStream.range(0, 6).forEach(i -> listQueue.enQueue(i));
        System.out.println(listQueue);
        listQueue.deQueue();
        System.out.println(listQueue);
    }

}
