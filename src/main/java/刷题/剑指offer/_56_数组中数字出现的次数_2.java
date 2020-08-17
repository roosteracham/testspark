package 刷题.剑指offer;

import java.util.Arrays;

/**
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,4,3,3]
 * 输出：4
 * 示例 2：
 *
 * 输入：nums = [9,1,7,9,7,9,7]
 * 输出：1
 *  
 *
 * 限制：
 *
 * 1 <= nums.length <= 10000
 * 1 <= nums[i] < 2^31
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _56_数组中数字出现的次数_2 {
    public int singleNumber(int[] nums) {
        int[] ints = new int[32];
        for (int num: nums) {
            for (int i = 0; i < 32; i++) {
                if (num == 0) {
                    break;
                }
                int i1 = (num & 1);
                num >>= 1;
                ints[31 - i] = (ints[31 - i] + i1) % 3;
            }
        }
        int sum = 0;
        for (int i = 0; i < 32; i++) {
            if (ints[31 - i] == 1) {
                sum += Math.pow(2, i);
            }
        }
        return sum;
    }

    public static void main(String[] args) {

    }
}
