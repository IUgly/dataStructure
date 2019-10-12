package stringproblem;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author kuangjunlin
 */
public class StringProblem {
    public int lengthOfLongestSubstring (String s) {
        if (s == null || s.equals("")) return 0;
        char[] chas = s.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        int len = 0;
        int pre = -1;
        int cur = 0;
        for (int i = 0; i != chas.length ; i++) {
            pre = Math.max(pre, map[chas[i]]);
            cur = i - pre;
            len = Math.max(len, cur);
        }
        return len;
    }

    public String longestCommonPrefix (String[] strs) {
        if (strs.length == 0 || strs == null) return "";
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if ( i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    public String multiply (String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = (res[i + j + 1] + n1 * n2);
                res[i + j +1] = sum % 10;
                res[i + j] += sum /10;
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i< res.length; i++) {
            if (i == 0 && res[i] == 0) continue;
            result.append(res[i]);
        }
        return result.toString();
    }


    public String reverseWords (String s) {
        if (s == null) return null;
        char[] chas = s.toCharArray();
        int len = chas.length;
        reverse(chas, 0, len - 1);
        wordReverse(chas, len);
        return cleanSpace(chas, len);
    }

    private void reverse (char[] chas, int i, int j) {
        while (i < j) {
            char c = chas[i];
            chas[i++] = chas[j];
            chas[j--] = c;
        }
    }

    private void wordReverse (char[] chas, int n) {
        int i = 0, j = 0;
        while (j < n) {
            while (i < n && chas[i] == ' ') i++;
            j = i;
            while (j < n && chas[j] != ' ') j++;
            reverse(chas, i, j - 1);
            i = j;
        }
    }

    private String cleanSpace (char[] chas, int n) {
        int i = 0, j = 0;
        while (j < n) {
            while (j < n && chas[j] == ' ') j++;
            while (j < n && chas[j] != ' ') chas[i++] = chas[j++];
            while (j < n && chas[j] == ' ') j++;
            if (j < n) chas[i++] = ' ';
        }
        return new String(chas).substring(0, i);
    }

    public String simplifyPath (String path) {
        String[] ss = path.split("/");
        Stack<String> stack = new Stack<>();
        for (String s : ss) {
            if (!stack.isEmpty() && s.equals(".."))
                stack.pop();
            else if (!s.equals("") && !s.equals(".") && !s.equals(".."))
                stack.push(s);
        }
        if (stack.empty())
            return "/";
        StringBuilder res = new StringBuilder();
        for (String s : stack) {
            res.append("/").append(s);
        }
        return res.toString();
    }

    public String simplify (String path) {
        String[] ss = path.split("/");
        Stack<String> stack = new Stack<>();
        for (String s : ss) {
            if (!stack.isEmpty() && s.equals(".."))
                stack.pop();
            else if (!s.equals("") && !s.equals(".") && !s.equals(".."))
                stack.push(s);
        }
        if (stack.empty()) {
            return "/";
        }
        StringBuilder res = new StringBuilder();
        for (String s : stack) {
            res.append("/").append(s);
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
            if (segment.startsWith("0") && segment.length() > 1
                    || (i == 3) && Integer.parseInt(segment) > 255) continue;
            cur.add(segment);
            backtracking(s, pos + i, cur, ans);
            cur.remove(cur.size() - 1);
        }
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        backtracking(s, 0, new ArrayList<>(), ans);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new StringProblem().reverseWords("the sky is blue"));
    }
}
