package 刷题.剑指offer;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 *
 *  
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回：
 *
 * [3,9,20,15,7]
 *
 */
public class 从上到下打印二叉树 {
    public int[] levelOrder(TreeNode root) {
        int[] res = new int[1004];
        int j = 0;
        j = doHandle(root, res, j);
        return Arrays.copyOfRange(res, 0, j);
    }

    LinkedList<TreeNode> list = new LinkedList();
    private int doHandle(TreeNode root, int[] res, int j) {
        if (root == null) {
            return j;
        }
        list.add(root);
        while (!list.isEmpty()) {
            TreeNode tmp = list.remove();
            res[j++] = tmp.val;
            if (tmp.left != null) {
                list.add(tmp.left);
            }
            if (tmp.right != null) {
                list.add(tmp.right);
            }
        }
        return j;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
