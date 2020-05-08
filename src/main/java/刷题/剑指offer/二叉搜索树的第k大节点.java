package 刷题.剑指offer;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 4
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 *  
 *
 * 限制：
 *
 * 1 ≤ k ≤ 二叉搜索树元素个数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 二叉搜索树的第k大节点 {

    public int kthLargest(TreeNode root, int k) {
        handle(root, k);
        return list.remove(list.size() - 1).val;
    }

    List<TreeNode> list = new LinkedList<>();

    Stack<TreeNode> stack = new Stack<>();
    private void handle(TreeNode root, int k) {
        if (root == null || list.size() == k) {
            return;
        }
        if (root.right != null) {
            stack.push(root.right);
            handle(root.right, k);
            list.add(stack.pop());
        }
        
        list.add(root);

    }
}
