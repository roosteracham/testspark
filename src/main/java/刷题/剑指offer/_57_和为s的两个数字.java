package 刷题.剑指offer;

import java.util.HashMap;
import java.util.Map;

/**
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 * 示例 2：
 *
 * 输入：nums = [10,26,30,31,47,60], target = 40
 * 输出：[10,30] 或者 [30,10]
 *  
 *
 * 限制：
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/he-wei-sde-liang-ge-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _57_和为s的两个数字 {

    public int[] twoSum(int[] nums, int target) {
        return myShit(nums, target);
    }

    private int[] recomond(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        for (; i < j; ) {
            int sum = nums[i] + nums[j];
            if (sum > target) {
                j--;
            } else if (sum < target) {
                i++;
            } else {
                break;
            }
        }
        return new int[]{nums[i], nums[j]};
    }
    private int[] myShit(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        int num = 0;
        Integer value = 0;
        for (int i = 0; i < nums.length; i++) {
            num = nums[i];
            value = map.get(num);
            if (value == null) {
                map.put(target - num, num);
            } else {
                break;
            }
        }
        return new int[]{num, value.intValue()};
    }
}
