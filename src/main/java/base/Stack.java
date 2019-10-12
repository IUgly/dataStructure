package base;

/**
 * @author kuangjunlin
 */
public interface Stack<T> {

    void push (T val) ;

    T pop ();

    T peek();

    int getSize ();

    boolean isEmpty ();
}
