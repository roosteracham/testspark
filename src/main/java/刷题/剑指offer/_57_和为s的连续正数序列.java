package 刷题.剑指offer;

import java.util.Arrays;

/**
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 *
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * 示例 2：
 *
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 *  
 *
 * 限制：
 *
 * 1 <= target <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _57_和为s的连续正数序列 {
//    public int[][] findContinuousSequence(int target) {
//        int mid = (target + 1) / 2;
//        if (mid <= 1) {
//            return new int[0][0];
//        }
//        int[] nums = new int[mid];
//        for (int i = 0; i < mid; i++) {
//            nums[i] = i+1;
//        }
//
//
//        int[][] res = new int[mid][];
//        int m = 0;
//        for (int i = 0, j = mid; ; i++) {
//            if (i == j) {
//                break;
//            }
//            int sum = (nums[i] + nums[j]) * (j - i + 1) / 2;
//            if (sum == target) {
//                res[m++] = getRes(i, j, nums);
//                i++;
//                continue;
//            }
//            if (sum > target) {
//                j--;
//            }
//            if (sum < target) {
//                i++;
//            }
//
//        }
//    }

    private int[] getRes(int i, int j, int[] nums) {
        int[] ints = new int[j - i + 1];
        for (int k = i; k <= j; k++) {
            ints[k - i] = nums[k];
        }
        return ints;
    }
}
