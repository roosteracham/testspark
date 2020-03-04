package 刷题.leetcode;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 寻找两个有序数组的中位数 {

    public static void main(String[] args) {
        System.out.println(new 寻找两个有序数组的中位数().findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    }
    public double findMedianSortedArrays(int[] A, int[] B) {
        if (A.length > B.length) {
            int [] tmp = A;
            A = B;
            B= tmp;
        }
        int m = A.length;
        int n = B.length;

        int iMin = 0, iMax = m, half = (m + n + 1) / 2;
        while(iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = half - i;
            if (i > iMin && A[i-1] > B[j] ) {
                iMax = i-1;
            } else if (i < iMax && A[i] < B[j-1]) {
                iMin = i + 1;
            } else {
                int maxLeft = 0;
                if (i ==0) {
                    maxLeft = B[j -1];
                } else if(j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n)/2 ==1) {
                    return maxLeft;
                }

                int maxRight = 0;
                if (i == m) {
                    maxRight = B[j];
                } else if (j == n) {
                    maxRight = A[i];
                } else {
                    maxRight = Math.min(A[i], B[j]);
                }
                return (maxLeft + maxRight) / 2.0;
            }
        }
        return 0.0;
    }
}
