package base;

public interface Queue <T> {
    int getSize ();
    boolean isEmpty ();
    void enQueue (T e);
    T deQueue ();
    T getFront();

}
