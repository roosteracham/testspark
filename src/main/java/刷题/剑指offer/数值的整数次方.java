package 刷题.剑指offer;

/**
 * 实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 *
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 *
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 *  
 *
 * 说明:
 *
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 *
 *
 */
public class 数值的整数次方 {
    public static void main(String[] args) {
        System.out.println(new 数值的整数次方().myPow(2.00000
                , -2147483648));
    }
    public double myPow(double x, int n) {
        if(x == 1 || n == 0) {
            return 1;
        }
        if(x == -1) {
            return n % 2 != 0 ? -1 : 1;
        }
        double res = 1.0;
        while ( n != 0) {
            if (n < 0) {
                n += 1;
                res *= 1/x;
            } else {
                n--;
                res *= x;
            }
            if (res == 0) {
                return  0.0;
            }
        }

        return res;
    }
}
