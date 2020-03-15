package 刷题.剑指offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 *
 *  
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *  
 *
 * 提示：
 *
 * 节点总数 <= 10000
 *
 */
public class 二叉树中和为某一值的路径 {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(-2);
        TreeNode node2 = new TreeNode(-3);
        node.left = null;
        node.right = node2;
        System.out.println(new 二叉树中和为某一值的路径().pathSum(node, -5));

    }
    Stack<Integer> stack = new Stack<>();
    int sum = 0;
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        preTraversal(root, sum);
        return res;
    }

    public void preTraversal(TreeNode head, int sum) {
        if (head == null) {
            return;
        }
        stack.push(head.val);
        this.sum += head.val;
        if (head.left == null && head.right == null && this.sum == sum) {
            doRes(stack);
            return;
        }
        if (head.left != null) {
            preTraversal(head.left, sum);
            if (!stack.empty()) {
                this.sum -= stack.pop();
            }
        }
        if (head.right != null) {
            preTraversal(head.right, sum);
            if (!stack.empty()) {
                this.sum -= stack.pop();
            }
        }
    }

    private void doRes(Stack<Integer> stack) {
        List<Integer> list = new ArrayList<>();
        stack.forEach(x -> list.add(x));
        res.add(list);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
