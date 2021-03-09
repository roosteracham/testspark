package 刷题.leetcode;

/**
 * 在计算机界中，我们总是追求用有限的资源获取最大的收益。
 *
 * 现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
 *
 * 你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出: 4
 * 解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。
 * 示例 2:
 *
 * 输入: strs = ["10", "0", "1"], m = 1, n = 1
 * 输出: 2
 * 解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1" 。
 *  
 *
 * 提示：
 *
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] 仅由 '0' 和 '1' 组成
 * 1 <= m, n <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ones-and-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 一和零 {
    public static void main(String[] args) {

//        System.out.println(new 一和零().rob(new int[]{200,3,140,20,10}));
        System.out.println(new 一和零().rob(new int[]{1,3,1,3,100}));
    }
//    public int findMaxForm(String[] strs, int m, int n) {
//
//        int[][] dp = new int[m][n];
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//
//            }
//        }
//    }


    public int rob(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = nums[1];
        int max = dp[0] > dp[1] ? dp[0] : dp[1];
        for (int i = 2; i < length; i++) {
            if (i == length - 1 && (length & 1) == 1) {
                continue;
            }

            int tmp = nums[i] + dp[i - 2];
            dp[i] = tmp > dp[i - 1] ? tmp : dp[i - 1];
            max = max < dp[i] ? dp[i] : max;
        }
        return max;
    }
}
