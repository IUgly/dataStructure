package base.setandmap;

/**
 * 链表实现的Map
 * 无序映射
 * O(n)
 * @author kuangjunlin
 */
public class LinkedListMap <K, V> implements Map<K, V> {
    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    private Node getNode (K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }


    @Override
    public void add(K k, V v) {
        Node node = getNode(k);
        if (node == null) {
            dummyHead.next = new Node(k, v, dummyHead.next);
            size++;
        }
        else {
            node.value = v;
        }
    }

    @Override
    public V remove(K k) {

        Node pre = dummyHead;
        while (pre.next != null) {
            if (pre.next.key.equals(k)) {
                break;
            }
            pre = pre.next;
        }
        if (pre.next != null) {
            Node delNode = pre.next;
            pre.next = delNode.next;
            delNode.next = null;
            size --;
            return delNode.value;
        }
        return null;
    }

    @Override
    public boolean contains(K k) {
        return getNode(k) != null;
    }

    @Override
    public V get(K k) {
        Node node = getNode(k);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K k, V newV) {
        Node node = getNode(k);
        if (node == null)
            throw new IllegalArgumentException(k + " does not exist");
        node.value = newV;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node() {
            this(null, null, null);
        }

        public Node(K key) {
            this(key, null, null);
        }

        @Override
        public String toString() {
            if (key != null && value != null) {
                return key.toString() + " : " + value.toString();
            }
            return "null key and value";
        }

    }

    @Override
    public String toString() {
        return "LinkedListMap{" +
                "dummyHead=" + dummyHead +
                ", size=" + size +
                '}';
    }

    public static void main(String[] args) {
        LinkedListMap<Integer, Integer> listMap = new LinkedListMap<>();
        for (int i = 0; i < 20; i++) {
            listMap.add(i, i);
        }
        for (int i = 0; i < 20 ; i++) {
            System.out.println(listMap.get(i));
        }
    }
}
