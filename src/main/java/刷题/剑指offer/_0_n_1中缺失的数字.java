package 刷题.剑指offer;

/**
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [0,1,3]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [0,1,2,3,4,5,6,7,9]
 * 输出: 8
 *
 * 限制：
 *
 * 1 <= 数组长度 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _0_n_1中缺失的数字 {

    public static void main(String[] args) {
        System.out.println(new _0_n_1中缺失的数字().missingNumber(new int[]{0,2,3}));
    }
    public int missingNumber(int[] nums) {
        if (nums[nums.length - 1] == nums.length - 1 ) {
            return nums.length;
        }
        if (0 != nums[0]) {
            return 0;
        }
        return search(0, nums.length - 1, nums);

    }

    private int search(int firstIndex, int lastIndex, int[] nums) {
        if (lastIndex == firstIndex) {
            if (nums[lastIndex] == lastIndex) {
                return 0;
            }
            return lastIndex;
        }
        if (lastIndex - firstIndex == 1) {

            if (lastIndex != nums[lastIndex] && firstIndex != nums[firstIndex]) {
                return firstIndex - 1;
            }
            if (lastIndex == nums[lastIndex]) {
                return firstIndex;
            } else if (firstIndex == nums[firstIndex]) {
                return lastIndex;
            }
            return 0;
        }
        int mid = (firstIndex + lastIndex) / 2;
        if (nums[mid] == mid) {
            return search(mid, lastIndex, nums);
        } else {
            return search(firstIndex, mid , nums);
        }
    }
}
