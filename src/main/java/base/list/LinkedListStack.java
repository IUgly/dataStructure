package base.list;

import base.Stack;

import java.util.stream.IntStream;

/**
 * 链表栈通常比数组栈快
 * 因为数组栈可能频繁控制数组内存空间/开辟或者缩小
 * 但是如果频繁new，可能更慢
 * @author kuangjunlin
 */
public class LinkedListStack<T> implements Stack<T> {
    private LinkedList<T> list;

    public LinkedListStack() {
        this.list = new LinkedList<>();
    }

    @Override
    public void push(T val) {
        list.addFirst(val);
    }

    @Override
    public T pop() {
        return list.removeFirst();
    }

    @Override
    public T peek() {
        return list.getFirst();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("Stack: top");
        ret.append(list);
        return ret.toString();
    }

    public static void main(String[] args) {
        LinkedListStack<Integer> listStack = new LinkedListStack<>();
        IntStream.range(0, 5).forEach(i -> listStack.push(i));
        System.out.println(listStack);
        listStack.pop();
        System.out.println(listStack);
    }
}
