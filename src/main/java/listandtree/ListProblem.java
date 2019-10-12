package listandtree;


import sun.jvm.hotspot.utilities.Interval;

import java.util.*;

/**
 * @author kuangjunlin
 */
public class ListProblem {
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode intersect = getInterset(head);
        if (intersect == null) return null;
        ListNode p1 = head;
        ListNode p2 = intersect;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    private ListNode getInterset (ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return slow;
        }
        return null;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    public ListNode mergeKLists (ListNode[] lists) {
        if (lists.length == 0) return null;
        return solve(lists, 0, lists.length - 1);
    }

    public ListNode solve (ListNode[] arr, int left, int right) {
        if (left == right) return arr[left];
        int mid = (left + right) >> 1;
        ListNode node1 = solve(arr, left, mid);
        ListNode node2 = solve(arr, mid + 1, right);
        return merge(node1, node2);
    }

    private ListNode merge (ListNode node1, ListNode node2) {
        if (node1 == null) return node2;
        if (node2 == null ) return node1;
        if (node1.val < node2.val) {
            node1.next = merge(node1.next, node2);
            return node1;
        }else {
            node2.next = merge(node1, node2.next);
            return node2;
        }
    }

    TreeNode res = null;
    public TreeNode lowestCommonAncestor (TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return res;
    }

    public int dfs (TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return 0;
        int left = dfs(root.left, p, q);
        int right = dfs(root.right, p, q);
        int mid = (root == p || root == q ) ? 1 : 0;
        if (left + right + mid > 1) res = root;
        return left + right + mid > 0 ? 1 : 0;
    }

    public List<List<Integer>> zigzagLevelOrder (TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, root, 0);
        return res;
    }
    public void helper (List<List<Integer>> res, TreeNode root, int depth) {
        if (root == null ) return;
        if (res.size() == depth) res.add(new LinkedList<>());
        if (depth % 2 == 0) res.get(depth).add(root.val);
        else res.get(depth).add(0, root.val);
        helper(res, root.left, depth+1);
        helper(res, root.right, depth+1);
    }

    private void dfs (int[][] M, int[] visited, int i ) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }

    public int findCircleNum (int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                dfs(M, visited, i);
                count ++;
            }
        }
        return count;
    }

    public int[][] merge (int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        if (intervals == null || intervals.length == 0)
            return res.toArray(new int[0][]);
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int i = 0;
        while (i < intervals.length) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            while (i < intervals.length - 1 && right >= intervals[i+1][0]) {
                i++;
                right = Math.max(right, intervals[i][1]);
            }
            res.add(new int[]{left, right});
            i++;
        }
        return res.toArray(new int[0][]);
    }

    public int trap (int[] height) {
        int sum = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];

        for (int i = 1; i < height.length - 1; i++) {
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(max_left[i], max_right[i]);
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }


}
class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}

