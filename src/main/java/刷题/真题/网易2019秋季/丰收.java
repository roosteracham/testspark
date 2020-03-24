package 刷题.真题.网易2019秋季;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 又到了丰收的季节，恰逢小易去牛牛的果园里游玩。
 * 牛牛常说他对整个果园的每个地方都了如指掌，小易不太相信，所以他想考考牛牛。
 * 在果园里有N堆苹果，每堆苹果的数量为ai，小易希望知道从左往右数第x个苹果是属于哪一堆的。
 * 牛牛觉得这个问题太简单，所以希望你来替他回答。
 *
 * 输入描述:
 * 第一行一个数n(1 <= n <= 105)。
 * 第二行n个数ai(1 <= ai <= 1000)，表示从左往右数第i堆有多少苹果
 * 第三行一个数m(1 <= m <= 105)，表示有m次询问。
 * 第四行m个数qi，表示小易希望知道第qi个苹果属于哪一堆。
 *
 * 输出描述:
 * m行，第i行输出第qi个苹果属于哪一堆。
 *
 * 输入例子1:
 * 5
 * 2 7 3 4 9
 * 3
 * 1 25 11
 *
 * 输出例子1:
 * 1
 * 5
 * 3
 */
public class 丰收 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] a = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = a[i - 1] + scanner.nextInt();
        }
        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            int nextInt = scanner.nextInt();
            for (int j = 1; j <= n; j++) {
                if (nextInt < a[j]) {
                    System.out.println(j);
                    break;
                }
            }
        }
    }
}
