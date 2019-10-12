package bytedance;

public class Solution {
    public boolean isContinuous(int [] numbers) {
        if(numbers.length == 0 || numbers == null) return false;
        int max = -1;
        int min = 15;
        int[] d = new int[15];
        d[0] = -15;
        for (int i = 0; i < numbers.length; i++) {
            d[numbers[i]] ++;
            if (numbers[i] == 0) continue;
            if (d[numbers[i]] > 1) return false;
            if (numbers[i] > max) max = numbers[i];
            if (numbers[i] < min) min = numbers[i];
        }
        return (max - min < 5) ? true : false;
    }

    public static void main(String[] args) {
        int[] arr = {1, 0, 0, 1, 0};
        System.out.println(new Solution().isContinuous(arr));
    }
}
