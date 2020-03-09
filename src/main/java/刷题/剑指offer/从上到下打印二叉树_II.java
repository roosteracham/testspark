package 刷题.剑指offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 * <p>
 *  
 * <p>
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 *  
 * <p>
 * 提示：
 * <p>
 * 节点总数 <= 1000
 */
public class 从上到下打印二叉树_II {

    LinkedList<TreeNode> list = new LinkedList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {

        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode remove = list.remove();
                tmp.add(remove.val);
                if (remove.left != null) {
                    list.add(remove.left);
                }
                if (remove.right != null) {
                    list.add(remove.right);
                }
            }
            res.add(tmp);
        }
        return res;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
