package 刷题.剑指offer;

/**
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 *  
 *
 * 示例 1：
 *
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *  
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 1000
 *
 */
public class 对称的二叉树 {

//    class Solution {
//        public boolean isSymmetric(TreeNode root) {
//            if(root == null){
//                return true;
//            }
//            return isMirror(root.left,root.right);
//        }
//        public boolean isMirror(TreeNode left, TreeNode right){
//            if(left == null && right == null){
//                return true;
//            }else if((left == null && right != null) || (right == null && left != null)){
//                //左右有一边为空另一边不为空，说明不对称
//                return false;
//            }
//            if(left.val != right.val){
//                return false;
//            }
//
//            //递归左的左孩子和右的右孩子相等，并且左的右孩子和右的左孩子相等
//            return isMirror(left.left,right.right) && isMirror(left.right,right.left);
//
//        }
//    }


    public boolean isSymmetric(TreeNode root) {

        if (root == null) {
            return false;
        }
        if (root.left != null && root.right != null) {
            if (root.left.val == root.right.val) {
                return doHandle(root.left, root.right);
            }
        }

        return  false;
    }

    boolean doHandle(TreeNode left, TreeNode right) {
        // 同时为null，返回true
        if (left == null && right == null)  {
            return true;
        }
        // 不同时为null，返回false
        if (left == null || right == null)  {
            return false;
        }
        if (((left.left == null && right.right == null) || (left.left != null && right.right != null && left.left.val == right.right.val)) &&
                ((left.right == null && right.left == null) || left.right != null && right.left != null && left.right.val == right.left.val)) {
            return doHandle(left.left, right.right) && doHandle(left.right, right.left);
        }
        return false;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
