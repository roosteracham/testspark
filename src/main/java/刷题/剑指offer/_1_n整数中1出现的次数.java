package 刷题.剑指offer;

/**
 * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 * <p>
 * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 12
 * 输出：5
 * 示例 2：
 * <p>
 * 输入：n = 13
 * 输出：6
 *  
 * <p>
 * 限制：
 * <p>
 * 1 <= n < 2^31
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _1_n整数中1出现的次数 {


    //        int sum = n;
    //        boolean b = n > 0 && (sum += sumNums(n - 1)) > 0;
//        return sum;

    public static void main(String[] args) {
        System.out.println(new _1_n整数中1出现的次数().sumNums(12));
    }
    public int sumNums(int n) {
        int counter = 0;
        for (int i = 0; i <= n; i++) {
            counter += include1(i);
        }
        return counter;
    }

    private int include1(int i) {
        int counter = 0;
        while (i != 0) {
            if (i % 10 == 1) {
                counter++;
            }
            i = i / 10;
        }
        return counter;
    }
}
