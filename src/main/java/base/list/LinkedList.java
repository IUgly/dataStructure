package base.list;

import java.util.stream.IntStream;

/**
 * 添加O(n)
 * 删除O(n)
 * 修改O(n)
 * 查找O(n)
 * @author kuangjunlin
 */
public class LinkedList <T> {
    private Node dummyHead;
    int size;

    public LinkedList() {
        dummyHead = new Node(null, null);
        size = 0;
    }

    public int getSize () {
        return size;
    }

    public boolean isEmpty () {
        return size == 0;
    }

    public void addFirst (T e) {
        add(0, e);;
    }

    public void add (int index, T e) {
        if (index <0 || index > size)
            throw new IllegalArgumentException("illegal index");

        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
//            Node node = new Node(e);
//            node.next = pre.next;
//            pre.next = node;
        pre.next = new Node(e, pre.next);
        size++;
    }

    public void addLast (T e) {
        addFirst(e);
    }

    public T get (int index) {
        if (index <0 || index > size)
            throw new IllegalArgumentException("illegal index");
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    public T getFirst () {
        return get(0);
    }

    public T getLast () {
        return get(size - 1);
    }

    public void set (int index, T e) {
        if (index <0 || index > size)
            throw new IllegalArgumentException("illegal index");
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }
    public boolean contains (T e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public void remove (int index) {
        if (index <0 || index > size)
            throw new IllegalArgumentException("illegal index");
        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node retNode = pre.next;
        pre.next = retNode.next;
        retNode.next = null;
        size--;
    }

    public void removeElement (T val) {
        Node pre =dummyHead;
        while (pre.next != null) {
            if (pre.next.e == val) break;
            pre = pre.next;
        }
        if (pre.next != null) {
            Node delNode = pre.next;
            pre.next = delNode.next;
            delNode.next = null;
        }
    }

    public T removeFirst () {
        T e = get(0);
        remove(0);
        return e;
    }

    public T removeLast(){
        T e = get(size - 1);
        remove(size -1);
        return e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            res.append(cur).append("->");
        }
        res.append("NULL");
        return res.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();

        IntStream.range(0, 5)
                .forEach(i -> linkedList.addFirst(i));
        System.out.println(linkedList);
        linkedList.add(2, 66);
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);
        linkedList.removeFirst();
        System.out.println(linkedList);

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


}
