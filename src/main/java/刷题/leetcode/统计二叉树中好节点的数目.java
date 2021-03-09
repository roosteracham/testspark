package 刷题.leetcode;

import 刷题.剑指offer.TreeNode;

import java.util.Stack;

public class 统计二叉树中好节点的数目 {

    public int goodNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + handle(root.val, root.left) +
                handle(root.val, root.right);
    }

    private int handle(int val, TreeNode root) {

        if (root == null) {
            return 0;
        }

        int base = 0;
        if (root.val >= val) {
            base++;
            val = root.val;
        }
        return base + handle(val, root.left) + handle(val, root.right);

    }

    public static void main(String[] args) {
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3, treeNode4, treeNode2);
        TreeNode root = new TreeNode(3, treeNode3, null);
        System.out.println(new 统计二叉树中好节点的数目().goodNodes(root));
    }

}
