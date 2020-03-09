package 刷题.剑指offer;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *  
 *
 * 限制：
 *
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 *
 */
public class 顺时针打印矩阵 {

    public static void main(String[] args) {
        System.out.println(new 顺时针打印矩阵().spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8},{ 9, 10, 11, 12}}));
    }
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }
        int l1 = matrix.length;
        int l2 = matrix[0].length;
        int[] res = new int[l1 * l2];
        int i = 0, j = 0;
        int k = 0;
        doHandle(matrix, i, j, l1, l2, res, k);
        return res;
    }

    public  void doHandle(int[][] matrix, int i, int j, int l1, int l2, int[] res, int k) {
        if (i < l1 && j < l2) {
            // 第一行
            for (int l = j; l < l2; l++) {
                res[k++] = matrix[i][l];
            }
            // 最后一列
            for (int l = i + 1; l < l1; l++) {
                res[k++] = matrix[l][l2 - 1];
            }
            // 最后一行倒着
            for (int l = l2 - 2; l >= j && (i + 1 != l1); l--) {
                res[k++] = matrix[l1 - 1][l];
            }
            for (int l = l1 - 2; l >= i + 1 && j + 1 != l2; l--) {
                res[k++] = matrix[l][j];
            }
            doHandle(matrix, ++i, ++j, --l1, --l2, res, k);
        }
    }
}
