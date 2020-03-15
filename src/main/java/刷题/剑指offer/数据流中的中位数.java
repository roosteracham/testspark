package 刷题.剑指offer;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * <p>
 * 例如，
 * <p>
 * [2,3,4] 的中位数是 3
 * <p>
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * <p>
 * 设计一个支持以下两种操作的数据结构：
 * <p>
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例 1：
 * <p>
 * 输入：
 * ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 * [[],[1],[2],[],[3],[]]
 * 输出：[null,null,null,1.50000,null,2.00000]
 * 示例 2：
 * <p>
 * 输入：
 * ["MedianFinder","addNum","findMedian","addNum","findMedian"]
 * [[],[2],[],[3],[]]
 * 输出：[null,null,2.00000,null,2.50000]
 *  
 * <p>
 * 限制：
 * <p>
 * 最多会对 addNum、findMedia进行 50000 次调用。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 数据流中的中位数 {

    /**
     * initialize your data structure here.
     */

    public static void main(String[] args) {
        数据流中的中位数 instance = new 数据流中的中位数();
        instance.addNum(6);
        instance.addNum(10);
        instance.addNum(2);
        instance.addNum(6);
        instance.addNum(5);
//        instance.addNum(0);
        System.out.println(instance.findMedian());
    }
    public 数据流中的中位数() {

    }

    List<MyComParator> set = new LinkedList<>();

    public void addNum(int num) {
        set.add(new MyComParator(num));
    }

    public double findMedian() {
        int size = set.size();
        if (size == 0) {
            return 0.0;
        }
        int limit = 1;
        if (size % 2 == 0) {
            limit++;
        }
        Collections.sort(set);
        return set.stream().skip((size-1) / 2).limit(limit).mapToInt(MyComParator::getN).sum() / 1.0 / limit;
    }

    static class MyComParator implements Comparable<MyComParator> {

        @Override
        public boolean equals(Object o) {
            return this == o;
        }

        @Override
        public int hashCode() {
            return Objects.hash(n);
        }

        public Integer n;

        public Integer getN() {
            return n;
        }

        public MyComParator(int num) {
            this.n = num;
        }

        @Override
        public int compareTo(MyComParator o) {
            return this.n.compareTo(o.getN());
        }
    }
}
