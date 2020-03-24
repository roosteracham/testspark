package 刷题.真题.网易2019秋季;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

/**
 * 小易觉得高数课太无聊了，决定睡觉。不过他对课上的一些内容挺感兴趣，所以希望你在老师讲到有趣的部分的时候叫醒他一下。你知道了小易对一堂课每分钟知识点的感兴趣程度，并以分数量化，以及他在这堂课上每分钟是否会睡着，你可以叫醒他一次，这会使得他在接下来的k分钟内保持清醒。你需要选择一种方案最大化小易这堂课听到的知识点分值。
 * <p>
 * 输入描述:
 * 第一行 n, k (1 <= n, k <= 105) ，表示这堂课持续多少分钟，以及叫醒小易一次使他能够保持清醒的时间。
 * 第二行 n 个数，a1, a2, ... , an(1 <= ai <= 104) 表示小易对每分钟知识点的感兴趣评分。
 * 第三行 n 个数，t1, t2, ... , tn 表示每分钟小易是否清醒, 1表示清醒。
 * <p>
 * 输出描述:
 * 小易这堂课听到的知识点的最大兴趣值。
 * <p>
 * 输入例子1:
 * 6 3
 * 1 3 5 2 5 4
 * 1 1 0 1 0 0
 * <p>
 * 输出例子1:
 * 16
 */
public class 瞌睡 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        int index = 0;
        int[] c = new int[n];
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            if (i < k - 1) {
                c[i] = a[i];
            } else if (i == k - 1) {
                c[i] = Arrays.stream(a).limit(k).sum();
            } else {
                c[i] = c[i - 1] - a[i - k] + a[i];
            }
            map.put(i, c[i]);
        }

        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        Set<Integer> set = new HashSet<>();
        Integer key = 0;
        while (true) {

            key = Objects.requireNonNull(map.entrySet().stream().filter(x -> !set.contains(x.getKey())).max(new Comparator<Map.Entry<Integer, Integer>>() {
                @Override
                public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            }).orElse(null)).getKey();
            int sum1 = Arrays.stream(b).limit(key).skip(key - k + 1).sum();
            if (sum1 < k) {
                break;
            } else {
                set.add(key);
            }
        }
        for (int i = 0; i < key - k + 1; i++) {
            int nextInt = b[i];
            if (nextInt == 1) {
                sum += a[i];
            }
        }

        for (int i = key - k + 1; i <= key; i++) {
            int nextInt = b[i];
                sum += a[i];
        }

        for (int i = key + 1; i < n; i++) {
            int nextInt = b[i];
            if (nextInt == 1) {
                sum += a[i];
            }
        }
        System.out.println(sum);
    }
}
