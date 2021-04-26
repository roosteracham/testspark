package 刷题.剑指offer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 * <p>
 * 示例: 
 * <p>
 * 你可以将以下二叉树：
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * <p>
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 序列化二叉树 { // Encodes a tree to a single string.

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
//        n1.left = n2;
//        n1.right = n3;
        n3.left = n4;
        n3.right = n5;
        序列化二叉树 instance = new 序列化二叉树();
        String serialize = instance.serialize(n1);
        System.out.println(serialize);
        TreeNode treeNode = instance.deserialize(serialize);
    }

    List<TreeNode> serList = new ArrayList<>();
    List<String> seres = new ArrayList<>();

    // 一次层序遍历
    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder("[");
        serList.add(root);
        layoutTraversal(builder, root);
        int i = 0;
        for (; i < Math.pow(2, layout - 1) - 2; i++) {
            builder.append(seres.get(i) + ",");
        }
        builder.append(seres.get(i));
        builder.append("]");
//        System.out.println(builder.toString());
        return builder.toString();
    }

    int layout = 0;

    private void layoutTraversal(StringBuilder builder, TreeNode root) {
        while (!serList.isEmpty()) {
            int size = serList.size();
            layout++;
            for (int i = 0; i < size; i++) {
                TreeNode remove = serList.remove(0);
                if (remove != null) {
                    seres.add(String.valueOf(remove.val));
                } else {
                    seres.add("null");
                    continue;
                }
                serList.add(remove.left);
                serList.add(remove.right);

            }
        }
    }

    Map<Integer, TreeNode> map = new HashMap<>();
    ;

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        TreeNode head = null;
        String[] dataSplit = data.substring(1, data.length() - 1).split(",");
        int len = dataSplit.length;
        for (int i = 0; i <= len / 2; i++) {
            String s = dataSplit[i];
            if ("null".equals(s)) {
                continue;
            }
            TreeNode curNode = getTN(i, dataSplit);
            if (i == 0) {
                head = curNode;
            }
            int index = 2 * (i + 1);
            curNode.right = getTN(index, dataSplit);
            curNode.left = getTN(index - 1, dataSplit);
        }
        return head;
    }
    static Node handle(Integer[] nums) {
        Queue<Node> queue = new LinkedList<>();
        Node root = new Node(nums[0]);
        queue.add(root);

        int j = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node remove = queue.remove();
                Integer num = nums[j];
                if (num != null) {
                    Node node = new Node(num);
                    remove.left = node;
                    queue.add(node);
                }
                j++;
                num = nums[j];
                if (num != null) {
                    Node node = new Node(num);
                    remove.right = node;
                    queue.add(node);
                }
                j++;
            }
        }
        return root;
    }

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private TreeNode getTN(int index, String[] dataSplit) {
        if (index >= dataSplit.length) {
            return null;
        }
        TreeNode treeNode = null;
        if ("null".equals(dataSplit[index])) {
            return null;
        }
        if (map.containsKey(index)) {
            return map.get(index);
        } else {
            int val = Integer.parseInt(dataSplit[index]);
            treeNode = new TreeNode(val);
            map.put(index, treeNode);
        }
        return treeNode;
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

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

