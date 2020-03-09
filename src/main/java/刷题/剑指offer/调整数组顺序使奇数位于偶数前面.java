package 刷题.剑指offer;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 *
 *  
 *
 * 示例：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10000
 *
 */
public class 调整数组顺序使奇数位于偶数前面 {
    public static void main(String[] args) {
        System.out.println(new 调整数组顺序使奇数位于偶数前面().exchange(new int[]{1, 2, 3, 4}));
    }
    public int[] exchange(int[] nums) {
        int length = nums.length;
        int j = length - 1;
        for (int i = 0; i < length; i++) {
            if (nums[i] % 2 == 0) {// 偶数
                for (int k = j; k > i; k--) {
                    if (nums[k] % 2 != 0) { // 奇数
                        nums[i] = nums[i] - nums[k];//奇偶交换位置
                        nums[k] = nums[k] + nums[i];
                        nums[i] = nums[k] - nums[i];
                        j = k;
                        break;
                    }
                }
            }
        }
        return nums;
    }
}
