package base.arr;

/**
 * @author kuangjunlin
 */
public class Array <T> {
    private T[] data;
    private int size;
    private int capacity;

    public Array() {
        this.capacity = 10;
        data = (T[])new Object[this.capacity];
    }

    public Array(int capacity) {
        if (capacity > 0) this.capacity = capacity;
        else this.capacity = 10;
        data = (T[])new Object[this.capacity];
    }

    public boolean isEmpty () {
        return size == 0;
    }

    public void addFirst (T val) {
        add(0, val);
    }

    public void addLast (T val) {
        add(this.size, val);
    }

    private void resize (int newCapacity) {
        T[] newData = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
        capacity = newCapacity;
    }

    public void add (int index, T val) {
        if (size == data.length)
            resize(2 * capacity);
        if (index > size || index < 0)
            resize(2 * capacity);
        for (int i = size - 1; i >= index ; i--) {
            data[i + 1] = data[i];
        }
        data[index] = val;
        this.size ++ ;
    }

    public T get (int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Index is illegal ");
        return data[index];
    }

    public void set (int index, T val) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Index is illegal ");
        data[index] = val;
    }

    public boolean contains (T val) {
        for (int i = 0; i < size; i++) {
            if (data[i] == val) return true;
        }
        return false;
    }

    public int find (T val) {
        for (int i = 0; i < size; i++) {
            if (data[i] == val) return i;
        }
        return -1;
    }

    public T getLast () {
        return get(size - 1);
    }

    public T getFirst () {
        return get(0);
    }

    public T remove (int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Index is illegal");
        T ret = data[index];
        for (int i = index + 1; i < size ; i++) {
            data[i - 1] = data[i];
        }
        size --;
        data[size] = null;
        if (size == data.length / 4 && data.length / 2 != 0)
            resize(data.length / 2);
        return ret;
    }

    public T removeFirst () {
        T ret = data[0];
        remove(0);
        return ret;
    }

    public T removeLast() {
        T ret = get(size - 1);
        remove(size - 1);
        return ret;
    }

    public void removeElement (T val) {
        int index = find(val);
        if (index != -1) {
            remove(index);
        }
    }

    public int getSize () {
        return this.size;
    }

    public int getCapacity () {
        return this.capacity;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d \n", this.size, this.capacity));
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) res.append(". ");
        }
        res.append("]");
        return res.toString();
    }

    public static void main(String[] args) {
        Array<Integer> arr = new Array<Integer>(20);
        for (int i = 0; i < 20; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);
        arr.add(1, 100);
        System.out.println(arr);
        arr.removeFirst();
        System.out.println(arr);
        arr.removeFirst();
        System.out.println(arr);

    }
}
