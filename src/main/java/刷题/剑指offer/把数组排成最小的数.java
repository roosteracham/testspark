package 刷题.剑指offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [10,2]
 * 输出: "102"
 * 示例 2:
 * <p>
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 *  
 * <p>
 * 提示:
 * <p>
 * 0 < nums.length <= 100
 * 说明:
 * <p>
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 把数组排成最小的数 {

    public static void main(String[] args) {
        System.out.println(new 把数组排成最小的数().minNumber(new int[]{4704, 6306, 9385, 7536, 3462, 4798, 5422, 5529, 8070,
                6241, 9094, 7846, 663, 6221, 216, 6758, 8353, 3650, 3836, 8183, 3516, 5909, 6744, 1548, 5712, 2281,
                3664, 7100, 6698, 7321, 4980, 8937, 3163, 5784, 3298, 9890, 1090, 7605, 1380, 1147, 1495, 3699, 9448,
                5208, 9456, 3846, 3567, 6856, 2000, 3575, 7205, 2697, 5972, 7471, 1763, 1143, 1417, 6038, 2313, 6554,
                9026, 8107, 9827, 7982, 9685, 3905, 8939, 1048, 282, 7423, 6327, 2970, 4453, 5460, 3399, 9533, 914,
                3932, 192, 3084, 6806, 273, 4283, 2060, 5682, 2, 2362, 4812, 7032, 810, 2465, 6511, 213, 2362, 3021, 2745, 3636, 6265, 1518, 8398}));
    }

    public String minNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(String.valueOf(nums[i]));
        }

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int l1 = o1.length();
                int l2 = o2.length();
                int res = 0;
                int i = 0;
                for (; i < (l1 < l2 ? l1 : l2); i++) {
                    if (o1.charAt(i) < o2.charAt(i)) {
                        return -1;
                    }  else if (o1.charAt(i) > o2.charAt(i)) {
                        return 1;
                    }
                }
                if (l1 == l2) {
                    return 0;
                }
                boolean a = l1 > l2;
                if (a) {
                    if (o1.charAt(i) == o2.charAt(0)) {
                        return (o1 + o2).compareTo(o2 + o1);
                    }
                    return o1.charAt(i) - o2.charAt(0);
                } else {
                    if (o1.charAt(0) == o2.charAt(i)) {
                        return (o1 + o2).compareTo(o2 + o1);
                    }
                    return o1.charAt(0) - o2.charAt(i);
                }
            }
        });

        StringBuilder stringBuilder = new StringBuilder();
        list.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }
}
