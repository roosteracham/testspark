package 刷题.剑指offer;

import org.mortbay.util.ajax.JSON;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 *
 *  
 *
 * 为了让您更好地理解问题，以下面的二叉搜索树为例：
 *
 *  
 *
 *
 *
 *  
 *
 * 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 *
 * 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
 *
 *  
 *
 *
 *
 *  
 *
 * 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 二叉搜索树与双向链表 {
    public static void main(String[] args) {
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);

//        n4.left = n2;
//        n4.right = n5;
        n2.left = n1;
        n2.right = n3;

        new 二叉搜索树与双向链表().treeToDoublyList(n4);
    }

    /**
     * Node head=null,pre=null,tail=null;
     *     public Node treeToDoublyList(Node root) {
     *         if(root==null) return root;
     *         //中序遍历访问节点并连接
     *         inorder(root);
     *         //连接头尾节点
     *         head.left=tail;
     *         tail.right=head;
     *         return head;
     *     }
     *     private void inorder(Node root){
     *         //递归出口
     *         if(root==null) return ;
     *         //访问左子树
     *         inorder(root.left);
     *         //将当前节点和上一个节点连接
     *         if(pre==null) head=root;
     *         else pre.right=root;
     *         root.left=pre;
     *         pre=root;
     *         tail=root;
     *         //访问右子树
     *         inorder(root.right);
     *         return ;
     *     }
     *
     * 作者：chenjunboBUPT
     * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/solution/zhong-xu-bian
     * -li-fang-wen-jie-dian-quan-ju-bian-li/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */


    // 我的方法
    List<Node> res = new ArrayList<>();
    List<Node> stack = new ArrayList<>();
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            root.left = null;
            root.right = root;
            return root;
        }
//        stack.push(root);
        doHandle(root);
        System.out.println(JSON.toString(stack));
        Iterator<Node> iterator = stack.iterator();
        Node backNode = iterator.next();
        while (iterator.hasNext()) {
            Node next = iterator.next();
            backNode.right = next;
            next.left = backNode;
            backNode = next;
        }
        Node head = res.get(0);
        backNode.right = head;
        head.left = backNode;

        return stack.get(0);
    }

    private void doHandle(Node root) {

        // 叶子节点
        if (root == null) {
//            stack.add(root);
            return;
        }
        doHandle(root.left);
        // 访问父节点
//        stack.add(root);

        if (res.isEmpty()) {
            res.add(root);
        }
        doHandle(root.right);
    }

    static class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    }
}
