package 刷题.真题.携程;

import java.util.Scanner;

/**
 * 输入一个long类型的数值, 求该数值的二进制表示中的1的个数 .
 *
 *
 * 输入描述:
 * long 类型的数值
 *
 * 输出描述:
 * 该数值二进制表示中1的个数
 *
 * 输入例子1:
 * 3
 *
 * 输出例子1:
 * 2
 *
 * 例子说明1:
 * 3的二进制表示: 11, 所以1个数为2
 *
 * 输入例子2:
 * 65
 *
 * 输出例子2:
 * 2
 *
 * 例子说明2:
 * 65的二进制为:1000001，所以１的个数为：２
 */
public class bit_count {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(doHandle(scanner.nextLong()));;
    }

    private static String doHandle(long nextInt) {

        int count = 0;
        while (nextInt != 0) {
            nextInt &= (nextInt - 1);
            count++;
        }
        return String.valueOf(count);
    }
}
