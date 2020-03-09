package 刷题.剑指offer;

/**
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *
 * 例如:
 * 给定的树 A:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 B：
 *
 *    4 
 *   /
 *  1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 *
 * 示例 1：
 *
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 * 示例 2：
 *
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 * 限制：
 *
 * 0 <= 节点个数 <= 10000
 *
 */
public class 树的子结构 {

    public boolean isSubStructure(TreeNode A, TreeNode B) {

        if(B == null) {
            return false;
        }

        // 从A中找B的头节点
        TreeNode a = A;
        a = preTraversal(a, B.val);
        if (a == null) {
            return false;
        }

        return preTraversal(a, B);

    }

    public boolean preTraversal(TreeNode a, TreeNode b) {
        if (b == null) {
            return true;
        }
        if (a == null) {
            return false;
        }
        if (a.val != b.val) {
            return false;
        }
        return preTraversal(a.left, b.left) && preTraversal(a.right, b.right);
    }

    public TreeNode preTraversal(TreeNode head, int val) {
        if (head == null) {
            return  null;
        }
        // 如果是头节点，直接返回
        if (head.val == val) {
            return head;
        }
        TreeNode res = null;
        // 从左子树找，如果左子树能找到，直接返回
        res = preTraversal(head.left, val);
        if (res != null) {
            return res;
        }
        // 从右子树找，如果右子树能找到，直接返回
        res = preTraversal(head.right, val);
        if (res != null) {
            return res;
        }
        return null;

    }

 public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }
}
