package base.list;

/**
 * @author kuangjunlin
 */
public class Sum {
    public static int sum (int[] arr) {
        return sum(arr, 0);
    }

    private static int sum(int[] arr, int l) {
        if (l == arr.length)
            return 0;
        return arr[l] + sum(arr, l + 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 1, 1};
        System.out.println(Sum.sum(arr));
    }

}
