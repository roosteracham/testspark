package 刷题.剑指offer;

import java.math.BigInteger;

/**
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m] 。请问 k[0]*k[1]*...*k[m]
 * 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 *  
 *
 * 提示：
 *
 * 2 <= n <= 1000
 *
 */
public class 剪绳子2 {
    public static void main(String[] args) {
        int n = 10;
        System.out.println(new 剪绳子2().cuttingRope(n));
    }

    public int cuttingRope(int n) {
        if (n < 4) {
            return n - 1;
        }
        int mod = (int) (1e9 + 7);
        long s= 1;
        while (n > 4) {
            s = s * 3 % mod;
            n -= 3;
        }
        return (int) (s * n % mod);
    }

//    public int cuttingRope(int n) {
//        if(n == 2) {
//            return 1;
//        }
//        if(n == 3){
//            return 2;
//        }
//        int mod = (int)1e9 + 7;
//        long res = 1;
//        while(n > 4) {
//            res *= 3;
//            res %= mod;
//            n -= 3;
//        }
//        return (int)(res * n % mod);
//    }

}
