package base.arr2;

// 累加和<=k的最长子串
public class LongestLessSum {
    public static int max1(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        // i向后最小累加和
        int[] minSums = new int[n];
        // i向后最小累加和位置
        int[] minSumEnds = new int[n];
        minSums[n - 1] = arr[n - 1];
        minSumEnds[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (minSums[i + 1] < 0) {
                minSums[i] = arr[i] + minSums[i + 1];
                minSumEnds[i] = minSumEnds[i + 1];
            } else {
                minSums[i] = arr[i];
                minSumEnds[i] = i;
            }
        }
        int end = 0, sum = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            while (end < n && sum + minSums[end] <= k) {
                sum += minSums[end];
                end = minSumEnds[end] + 1;
            }
            ans = Math.max(ans, end - i);
            // end不用回退
            if (end > i) {
                sum -= arr[i];
            } else {
                end = i + 1;
            }
        }
        return ans;
    }

    //

    private static int getLessIdx(int[] arr, int num) {
        int l = 0, r = arr.length - 1;
        int res = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] >= num) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return res;
    }

    public static int max2(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            pre = getLessIdx(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    //

    private static int[] randomArr(int len, int maxVal) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxVal + 1) * Math.random()) - (maxVal / 3);
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        for (int i = 0; i < 1000000; i++) {
            int[] arr = randomArr(10, 20);
            int k = (int) (21 * Math.random()) - 5;
            if (max1(arr, k) != max2(arr, k)) {
                System.out.println("Wrong");
            }
        }
        System.out.println("test end");
    }
}
