package 刷题.剑指offer;

import com.google.inject.internal.cglib.core.$Signature;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 *
 *  
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 * 输出: 2
 *  
 *
 * 限制：
 *
 * 1 <= 数组长度 <= 50000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 数组中出现次数超过一半的数字 {

    public static void main(String[] args) {
//        System.out.println(new 数组中出现次数超过一半的数字().majorityElement(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}));

        int[] ints = new int[2];
        ints[0] = 1;
        ints[1] = 2;
        System.out.println(ints.hashCode());
    }


    public int majorityElement(int[] nums) {
        int count = 0;
        int val = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                val = nums[i];
            }
            if (val == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return val;
    }


//    Map<Integer, Integer> map = new HashMap<>();
//    public int majorityElement(int[] nums) {
//        int length = nums.length;
//        for (int i = 0; i < length/2 +1; i++) {
//            if (map.containsKey(nums[i])) {
//                int times = map.get(nums[i]);
//                if (++times > length / 2) {
//                    return nums[i];
//                } else {
//                    map.put(nums[i], times);
//                }
//            } else {
//                map.put(nums[i], 1);
//            }
//        }
//        return 0;
//    }
}
