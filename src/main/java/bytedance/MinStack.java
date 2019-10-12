package bytedance;

import java.util.Stack;

/**
 * @author kuangjunlin
 */
public class MinStack {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public MinStack() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push (int val) {
        this.stackData.push(val);
        if (this.stackMin.isEmpty() || val <= this.getMin()) {
            this.stackMin.push(val);
        }
    }

    public void pop() {
        if (this.stackData.isEmpty()) {
            throw new RuntimeException("stack is empty");
        }
        int val = this.stackData.pop();
        if (val == getMin())
            this.stackMin.pop();
    }

    public int top () {
        if (this.stackData.isEmpty())
            throw new RuntimeException("stack is empty");
        return this.stackData.peek();
    }

    public int getMin() {
        if (this.stackMin.isEmpty())
            throw new RuntimeException("stack is empty");
        return this.stackMin.peek();
    }
}
