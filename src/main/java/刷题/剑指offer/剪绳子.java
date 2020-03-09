package 刷题.剑指offer;

import java.math.BigInteger;
import java.util.HashMap;

/**
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m] 。请问 k[0]*k[1]*...*k[m]
 * 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
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
 * 2 <= n <= 58
 */
public class 剪绳子 {

    public static void main(String[] args) {
        System.out.println(new 剪绳子().cuttingRope(10));
    }


//    public int cuttingRope(int n) {
//
//        int[] dp = new int[n + 1];
//        dp[1] = 1;
//        for (int i = 2; i <= n; i++) {
//            // int max = 1;
//            for (int j = i / 2; j < i; j++) {
//                dp[i] = Math.max(Math.max(dp[j], j) * (i - j), dp[i]);
//            }
//            // map.put(i, max);
//        }
//        for (int i : dp) {
//            System.out.println(i);
//        }
//        return dp[n];
//    }

    public int cuttingRope(int n) {
        HashMap<Integer, BigInteger> map = new HashMap<>();
        map.put(1, BigInteger.valueOf(1));
        map.put(2, BigInteger.valueOf(1));
        for (int i = 2; i <= n; i++) {
            BigInteger max = BigInteger.ONE;
            for (int j = i / 2; j < i; j++) {
//                max = BigInteger.valueOf(Math.max(Math.max(map.get(j).longValue(), j) * (i - j), max.longValue()));

                BigInteger tmp = map.get(j).compareTo(BigInteger.valueOf(j)) > 0 ? map.get(j) :
                        BigInteger.valueOf(j);
                tmp = tmp.multiply(BigInteger.valueOf(i - j));
                max = tmp.compareTo(max) > 0 ? tmp : max;
            }
            map.put(i, max);
        }
         System.out.println(map);
        return map.get(n).mod(BigInteger.valueOf(1000000007)).intValue();
    }

}



