package dyramic;

import java.util.*;

/**
 * @author kuangjunlin
 */
public class Dyramic {
    public boolean wordBreak (String s, List<String> wordDict) {
        if (s == null) {
            return true;
        }
        if (wordDict == null || wordDict.size() == 0) {
            return false;
        }

        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for(int i=0; i<nums.length; i++){
            if(nums[i] < 0){
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax*nums[i], nums[i]);
            imin = Math.min(imin*nums[i], nums[i]);

            max = Math.max(max, imax);
        }
        return max;
    }

    public int minimumTotal(List<List<Integer>> triangle) {
//        return help(triangle, 0, 0);
        int[][] map = new int[triangle.size() + 1][ triangle.size() + 1];
        return help2(triangle, 0, 0, map);
    }

    public int help (List<List<Integer>> triangle, int x, int y) {
        if (x == triangle.size()) {
            return 0;
        }
        return triangle.get(x).get(y) +
                Math.min(help(triangle, x + 1, y), help(triangle, x + 1, y + 1));
    }

    public int help2 (List<List<Integer>> triangle, int x, int y, int[][] map) {
        if (x == triangle.size()) return 0;
        int res = 0;
        int mapVal = map[x][y];
        if (mapVal != 0)
            res += ( mapVal == -1) ? 0 : mapVal;
        else {
            res += triangle.get(x).get(y) +
                    Math.min(help2(triangle, x + 1, y, map),
                            help2(triangle, x + 1, y + 1, map));
        }
        map[x][y] = ( res == 0 ) ? -1 : res;
        return res;
    }

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ?
                        o2[1] - o1[1] : o1[0] - o2[0];
            }
        });
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = envelopes[i][1];
        }
        return lengthOfLIS(height);
    }

    public String simplifyPath (String path) {
        String[] s = path.split("/");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length; i++) {
            String ss = s[i];
            if (!stack.isEmpty() && ss.equals("..")){
                stack.pop();
            }
            else if (!ss.equals("") && !ss.equals(".") && !ss.equals("..")) {
                stack.push(ss);
            }
        }
        if (stack.empty())
            return "/";

        StringBuffer res = new StringBuffer();
        for (int i = 0; i < stack.size(); i++) {
            res.append("/" + stack.get(i));
        }
        return res.toString();
    }

    private void backtracking (String s, int pos, List<String> cur, List<String> ans) {
        if (cur.size() >= 4) {
            if (pos == s.length()) ans.add(String.join(".", cur));
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if (pos+i > s.length()) break;
            String segment = s.substring(pos, pos+i);
            if (segment.startsWith("0") && segment.length() > 1 || (i == 3) && Integer.parseInt(segment) > 255) continue;
            cur.add(segment);
            backtracking(s, pos + i, cur, ans);
            cur.remove(cur.size() - 1);
        }
    }

    public List<String> restoreIpAdresses(String s) {
        List<String> ans = new ArrayList<>();
        backtracking(s, 0, new ArrayList<>(), ans);
        return ans;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(nums);
        if (nums == null || nums.length < 3) return ret;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 ) break;
            if (i > 0 && nums[i] == nums[i-1]) continue;
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    ret.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l+1]) l++;
                    while (l < r && nums[r] == nums[r-1]) r--;
                    l++;
                    r--;
                }
                else if (sum > 0) r--;
                else if (sum < 0) l++;
            }
        }
        return ret;
    }

    /**
     * 如果中间的数小于最右边的数，则右半段是有序的，
     * 若中间数大于最右边数，则左半段是有序的，
     * 只要在有序的半段里用首尾两个数组来判断目标值是否在这一区域内，
     * 就可以确定保留哪半边了
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < nums[right]) {
                if (nums[mid] < target && target <= nums[right]){
                    left = mid +1;
                }else {
                    right = mid -1;
                }
            }
            else {
                if (nums[left] <= target && target < nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            }
        }
        return -1;
    }

    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        int[] dp = new int[nums.length + 1];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int pre = nums[i-1];
            int cur = nums[i];
            if (cur > pre) {
                dp[i] = dp[i-1] + 1;
            }else {
                dp[i] = 1;
            }
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    int patition (int[] nums, int left, int right) {
        int temp = nums[left];
        while (left < right) {
            while (temp <= nums[right] && left < right) right--;
            if (left < right) nums[left] = nums[right];
            while (temp >= nums[left] && left < right) left++;
            if (left < right)
                nums[right] = nums[left];
                right--;
        }
        nums[left] = temp;
        return left;
    }

    int patition2 (int[] nums, int left, int right) {
        int temp = nums[left];
        while (left < right) {
            while (left < right && temp < nums[right]) right--;
            if (left < right) nums[left] = nums[right];
            while (left < right && temp > nums[left]) left++;
            if (left < right)
                nums[right] = nums[left];
                right--;
        }
        nums[left] = temp;
        return left;
    }

    void quickSort (int[] nums, int left, int right) {
        if (nums == null || left >= right || nums.length <= 1)
            return;
        int mid = patition(nums, left, right);
        quickSort(nums, left, mid);
        quickSort(nums, mid + 1, right);
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) numSet.add(num);
        int ret = 0;
        for (int num : nums) {
            if (!numSet.contains(num - 1)) {
                int cur = num;
                int curRet = 1;
                while (numSet.contains(cur + 1)) {
                    cur += 1;
                    curRet += 1;
                }
                ret = Math.max(ret, curRet);
            }
        }
        return ret;
    }

    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        quickSort(nums, 0, len - 1);
        return nums[len -k];
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode next = head.next;
        head.next = null;
        ListNode newHead = reverseList(next);
        next.next = head;
        return newHead;
    }

    public ListNode sortList (ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left != null ? left : right;
        return res.next;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(5);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(8);
        ListNode l5 = new ListNode(4);
        ListNode l6 = new ListNode(2);
        ListNode l7 = new ListNode(1);

        head.next = l1;
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        l7.next = null;

        ListNode p = head;
        while (p.next != null) {
            System.out.print(p.val);
            p = p.next;
        }
        System.out.print(p.val);
        System.out.println();

        new Dyramic().sortList(head);
        p = head;
        while (p != null) {
            System.out.print(p.val);
            p = p.next;
        }
    }


}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
}