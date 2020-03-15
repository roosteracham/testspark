package 刷题.剑指offer;

/**
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 *
 * 请写一个函数，求任意第n位对应的数字。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：3
 * 示例 2：
 *
 * 输入：n = 11
 * 输出：0
 *  
 *
 * 限制：
 *
 * 0 <= n < 2^31
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 数字序列中某一位的数字 {

    public static void main(String[] args) {
        System.out.println(new 数字序列中某一位的数字().findNthDigit(10));
    }
    public int findNthDigit(int n) {
        int digit = getDigit(n);
        if (digit == 1) {
            return n;
        }
        int a = (int) ((n - sum ) % digit);
        int b = (int) (Math.pow(10, digit - 1) + n / digit);
        return Integer.parseInt(String.valueOf(b).substring(a, a+ 1));
    }

    long sum = 0;
    private int getDigit(int n) {
        int d = 1;
        while (true) {
            double v = Math.pow(10, d - 1) * 9;
            if (n <= sum + v) {
                break;
            }
            sum += v;
            d++;
        }
        return d;
    }


}
