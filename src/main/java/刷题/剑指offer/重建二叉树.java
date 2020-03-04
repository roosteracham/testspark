package 刷题.剑指offer;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * <p>
 *  
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 *  
 * <p>
 * 限制：
 * <p>
 * 0 <= 节点个数 <= 5000
 */
public class 重建二叉树 {
    public static void main(String[] args) {
        TreeNode treeNode = new 重建二叉树().buildTree(new int[]{3,9,8,5,4,10,20,15,7}, new int[]{4,5,8,10,9,3,15,20,7});

    }

    public TreeNode buildTree(int[] preorder, int[] inorder, HashMap<Integer, Integer> map, int preLeftIndex,
                              int preRightIndex, int midLeftIndex, int midRightIndex) {

        TreeNode head = null;
        int headVal = preorder[preLeftIndex];
        head =  new TreeNode(headVal);
        if (preLeftIndex > preRightIndex) {
            return null;
        } else if (preLeftIndex == preRightIndex) {
            return head;
        }

        /**
         *             int rootIndex = indexMap.get(rootVal);
         *             int leftNodes = rootIndex - inorderStart, rightNodes = inorderEnd - rootIndex;
         *             TreeNode leftSubtree = buildTree(preorder, preorderStart + 1, preorderStart + leftNodes,
         *             inorder, inorderStart, rootIndex - 1, indexMap);
         *             TreeNode rightSubtree = buildTree(preorder, preorderEnd - rightNodes + 1, preorderEnd,
         *             inorder, rootIndex + 1, inorderEnd, indexMap);
         *
         */
        int i = map.get(headVal);
        head.left = buildTree(preorder, inorder, map, preLeftIndex + 1, preLeftIndex + (i - midLeftIndex), midLeftIndex, i - 1);
        head.right = buildTree(preorder, inorder, map, preRightIndex - (midRightIndex - i) + 1, preRightIndex, i + 1, midRightIndex);

        return head;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if ((preorder == null || preorder.length == 0) && (inorder == null || inorder.length == 0)) {
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, inorder, map, 0, preorder.length, 0, inorder.length);
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
