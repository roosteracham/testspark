package 刷题.leetcode;

import 刷题.剑指offer.TreeNode;

import java.util.Stack;

public class 二叉搜索树的最近公共祖先 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null || p == null || q == null) {
            return root;
        }

        handle(root, p, q);

        TreeNode treeNode = root;
        while (!stackp.isEmpty() && !stackq.isEmpty()) {
            TreeNode pop =  stackp.remove(0);
            TreeNode pop1 = stackq.remove(0);
            if (pop == pop1) {
                treeNode = pop;
            } else {
                return treeNode;
            }
        }

        return treeNode;
    }

    boolean findP = false;
    boolean findq = false;

    Stack<TreeNode> stack = new Stack<>();
    Stack<TreeNode> stackp = new Stack<>();
    Stack<TreeNode> stackq = new Stack<>();
    private void handle(TreeNode node, TreeNode p, TreeNode q) {

        if (findP && findq) {
            return;
        }

        stack.push(node);
        if (node == p) {
            findP = true;
            stackp.addAll(stack);
        }

        if (node == q) {
            findq = true;
            stackq.addAll(stack);
        }
        if (node.left != null) {
            handle(node.left, p, q);
            if (findP && findq) {
                return;
            }
            stack.pop();
        }
        if (node.right != null) {
            handle(node.right, p, q);
            if (findP && findq) {
                return;
            }
            stack.pop();
        }
    }

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2, node3, node4);
        TreeNode node8 = new TreeNode(8);
        TreeNode node6 = new TreeNode(6, node2, node8);
        System.out.println(new 二叉搜索树的最近公共祖先().lowestCommonAncestor(node6, node2, node4).val);
    }
}
