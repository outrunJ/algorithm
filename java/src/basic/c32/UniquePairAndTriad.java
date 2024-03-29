package basic.c32;

// 有序数组，找所有和为k的非重二元组，三元组
public class UniquePairAndTriad {
    public static void uniquePair(int[] arr, int k) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            if (arr[left] + arr[right] < k) {
                left++;
            } else if (arr[left] + arr[right] > k) {
                right--;
            } else {
                if (left == 0 || arr[left - 1] != arr[left]) {
                    System.out.println(arr[left] + "," + arr[right]);
                }
                left++;
                right--;
            }
        }
    }

    //
    private static void rest(int[] arr, int first, int l, int r, int k) {
        while (l < r) {
            if (arr[l] + arr[r] < k) {
                l++;
            } else if (arr[l] + arr[r] > k) {
                r--;
            } else {
                if (l == first + 1 || arr[l - 1] != arr[l]) {
                    System.out.println(arr[first] + "," + arr[l] + "," + arr[r]);
                }
                l++;
                r--;
            }
        }
    }

    public static void uniqueTriad(int[] arr, int k) {
        if (arr == null || arr.length < 3) {
            return;
        }
        for (int i = 0; i < arr.length - 2; i++) {
            if (i == 0 || arr[i] != arr[i - 1]) {
                rest(arr, i, i + 1, arr.length - 1, k - arr[i]);
            }
        }
    }

    //

    private static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int sum = 10;
        int[] arr = {-8, -4, -3, 0, 1, 2, 4, 5, 8, 9};
        print(arr);
        System.out.println("=====");
        uniquePair(arr, sum);
        System.out.println("=====");
        uniqueTriad(arr, sum);
    }
}
