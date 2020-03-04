package 刷题.剑指offer;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：n = 7
 * 输出：21
 * 提示：
 *
 * 0 <= n <= 100
 *
 */
public class 青蛙跳台阶问题 {
    /**
     * 本质上还是斐波那契函数，青蛙最后一次跳跃，只有两种跳法，一个台阶或者两个台阶，那就变成了f(n) = f(n - 1) + f(n - 2)
     * @param n
     * @return
     */
    public int numWays(int n) {
        if (n <= 0) {
            return 1;
        }
        if (n <= 2) {
            return n;
        }

        int a = 1;
        int b = 2;
        long res = 0;
        for (int i = 2; i < n; i++) {
            res = (a + b) % 1000000007;
            a = b;
            b = (int)res;
        }
        return (int)res;
    }
}
