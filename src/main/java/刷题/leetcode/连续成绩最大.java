package 刷题.leetcode;

/**
 * 给你一个整数数组nums，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 输入: [2,3,-2,4]
 * 输出: 6
 * <p>
 * 输入: [2,3,-2,4,-3]
 * 输出: 144
 * <p>
 * 输入: [1,6,1,1]
 * 输出: 6
 * <p>
 * 输入: [-4,-3,-2]
 * 输出: 12
 */
public class 连续成绩最大 {

    public static void main(String[] args) {
        System.out.println(handle(new int[]{2, 3, -2, 4}));
        System.out.println(handle(new int[]{2, 3, -2, 4, -3}));
        System.out.println(handle(new int[]{1, 6, 1, 1}));
        System.out.println(handle(new int[]{2, 0, -2}));
    }

    static int handle(int[] nums) {

        int[][] dp = new int[nums.length + 1][2];
        dp[0][0] = 1;
        dp[0][1] = 1;

        int sum = 1;
        for (int i = 0; i < nums.length; i++) {
            sum *= nums[i];
        }

        if (sum > 0) {
            return sum;
        }

        int sum2 = 1;
        int i = nums.length;
        for (; i >= 0; i--) {
            if (nums[i - 1] < 0) {
                break;
            }
            sum2 *= nums[i - 1];
        }

        return Math.max(sum2, sum / sum2 / nums[i - 1]);
    }

}
