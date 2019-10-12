package base;

import java.util.TreeSet;
import java.util.stream.IntStream;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    public ListNode(int[] arr) {
        if (arr == null || arr.length == 0)
            throw  new IllegalArgumentException("arr cannot be empty");
        this.val = arr[0];
        ListNode cur = this;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {
            ret.append(cur.val).append("->");
            cur = cur.next;
        }
        ret.append("NULL");
        return ret.toString();
    }
}

class Solution {
    private ListNode removeElements(ListNode head, int val) {

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next =head;

        ListNode pre = dummyHead;
        while (pre.next != null ) {
            if (pre.next.val == val) {
                pre.next = pre.next.next;
            }else {
                pre = pre.next;
            }
        }
        return dummyHead.next;
    }

    private ListNode removeElements3 (ListNode head, int val) {
        if (head == null) return head;
        head.next =removeElements3(head.next, val);
        return head.val == val ? head.next : head;
    }

    private ListNode removeElements2 (ListNode head, int val, int depth) {
        String depthString = generateDepthString(depth);
        System.out.print(depthString);
        System.out.println("Call: remove " + val + " in " + head);

        if (head == null ) {
            System.out.print(depthString);
            System.out.println("Return: " + head);
            return head;
        }

        ListNode res = removeElements2(head.next, val, depth + 1);
        System.out.print(depthString);
        System.out.println("After remove " + val + ": " + res);

        ListNode ret;
        if (head.val == val){
            ret = res;
        } else {
            head.next = res;
            ret = head;
        }
        System.out.print(depthString);
        System.out.println("Return" + ret);
        return ret;
    }

    private String generateDepthString (int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }


    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        TreeSet<String> treeSet = new TreeSet<>();
        for (String word : words) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                res.append(codes[word.charAt(i) - 'a']);
            }
            treeSet.add(res.toString());
        }
        return treeSet.size();
    }


    public static void main(String[] args) {
        int[] arr = {1, 3, 4,5, 2, 6, 8, 2};
        ListNode head = new ListNode(arr);
        System.out.println(head);
        new Solution().removeElements2(head, 2, 0);
        System.out.println(head);
    }
}