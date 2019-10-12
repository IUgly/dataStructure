package base.setandmap;

public interface Map <K, V> {
    void add (K k, V v);
    V remove (K k);
    boolean contains(K k);
    V get (K k);
    void set(K k, V newV);
    int getSize ();
    boolean isEmpty();
}
