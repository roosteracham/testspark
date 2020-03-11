package 刷题.剑指offer;

import java.util.Arrays;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 *
 *  
 *
 * 参考以下这颗二叉搜索树：
 *
 *      5
 *     / \
 *    2   6
 *   / \
 *  1   3
 * 示例 1：
 *
 * 输入: [1,6,3,2,5]
 * 输出: false
 * 示例 2：
 *
 * 输入: [1,3,2,6,5]
 * 输出: true
 *  
 *
 * 提示：
 *
 * 数组长度 <= 1000
 *
 */
public class 二叉搜索树的后序遍历序列 {

    public boolean verifyPostorder(int[] postorder) {

        if (postorder == null || postorder.length < 1) {
            return true;
        }
        int len = postorder.length;
        int rootVal = postorder[len - 1];
        int i = 0;
        for (; i < len - 1; i++) {
            if (postorder[i] > rootVal) {
                break;
            }
        }
        int a = postorder[i];
        for (int j = i + 1; j < len - 1; j++) {
            if (postorder[j] < rootVal) {
                return false;
            }
        }

        return verifyPostorder(Arrays.copyOfRange(postorder, 0, i)) && verifyPostorder(Arrays.copyOfRange(postorder, i, len - 1));
    }
}
