package bytedance;

import java.util.HashMap;

class LRU {
}
class Node {
    public int key, val;
    public Node next, prev;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
class DoubleList {
    private Node head, tail;
    private int size;

    public DoubleList() {
        head = new Node(0, 0);
        tail = new Node(0 ,0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void addFirst (Node n) {
        n.next = head.next;
        n.prev = head;
        head.next.prev = n;
        head.next = n;
        size++;
    }

    public void remove (Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
        size--;
    }

    public Node removeLast () {
        if (tail.prev == head) return null;
        Node last = tail.prev;
        remove(last);
        return last;
    }

    public int size () {
        return size;
    }
}

class LRUCache {
    private HashMap<Integer, Node> map;
    private DoubleList cache;
    private int cap;

    public LRUCache(int cap) {
        this.cap = cap;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    public int get (int key) {
        if (!map.containsKey(key))
            return -1;
        int val = map.get(key).val;
        put(key, val);
        return val;
    }
    public void put (int key, int val ) {
        Node node = new Node(key, val);
        if (map.containsKey(key)) {
            cache.remove(map.get(key));
            cache.addFirst(node);
            map.put(key, node);
        } else {
            if (cap == cache.size()) {
                Node last = cache.removeLast();
                map.remove(last.key);
            }
            cache.addFirst(node);
            map.put(key, node);
        }
    }
}
