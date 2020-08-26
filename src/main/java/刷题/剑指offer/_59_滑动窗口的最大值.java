package 刷题.剑指offer;

import 刷题.MaxHeap;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *  
 *
 * 提示：
 *
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 *
 * 注意：本题与主站 239 题相同：https://leetcode-cn.com/problems/sliding-window-maximum/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _59_滑动窗口的最大值 {
//    public int[] maxSlidingWindow(int[] nums, int k) {
//
//        int[] res = new int[nums.length - k + 1];
//        List<Integer> list = new LinkedList<>();
//        for (int i = 0; i < k; i++) {
//            list.add(nums[i]);
//        }
//
//        for (int i = 0; i < nums.length - k; i++) {
//            res[i] = maxHeap.pop();
//            maxHeap.addElement(nums[i + k]);
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        System.out.println(new _59_滑动窗口的最大值().maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
//    }
}
