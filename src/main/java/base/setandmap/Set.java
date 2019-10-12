package base.setandmap;


/**
 * 无序集合 -> 基于哈希表 ps：有序集合基于搜索树实现
 * @author kuangjunlin
 */
public interface Set <E> {
    /**
     * 不添加充分元素
     * @param e
     */
    void add (E e);
    void remove (E e);
    boolean contains (E e);
    int getSize ();
    boolean isEmpty();
}
