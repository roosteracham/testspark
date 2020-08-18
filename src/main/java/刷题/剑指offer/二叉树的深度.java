package 刷题.剑指offer;

/**
 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
 * <p>
 * 例如：
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
 * <p>
 *  
 * <p>
 * 提示：
 * <p>
 * 节点总数 <= 10000
 * 注意：本题与主站 104 题相同：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 二叉树的深度 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int maxDept;
    int dept = 1;

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        findDept(root);
        return maxDept;
    }

    private void findDept(TreeNode root) {
        if (root.left != null) {
            dept++;
            findDept(root.left);
            dept--;
        }
        if (root.left == null && root.right == null) {
            maxDept = maxDept > dept ? maxDept : dept;
            return;
        }
        if (root.right != null) {
            dept++;
            findDept(root.right);
            dept--;
        }
    }
}
