package leetc.top;

public class P53_MaximumSubarray {
    public static int maxSubArray(int[] nums) {
        int cur = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            cur += nums[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }
}
