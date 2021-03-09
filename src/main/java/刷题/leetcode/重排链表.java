package 刷题.leetcode;

import 刷题.ListNode;

import java.util.LinkedList;

/**
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1:
 *
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 *
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorder-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 重排链表 {

    public static void main(String[] args) {
        ListNode node4 = new ListNode(4);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        new 重排链表().reorderList(node1);
        while (node1 != null) {
            System.out.println(node1.val);
            node1 = node1.next;
        }
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        LinkedList<ListNode> queue = new LinkedList<>();
        LinkedList<ListNode> stack = new LinkedList<>();
        ListNode cur = head;
        int i = 0;
        while (cur != null) {
            if ((i++ & 1) == 0) {
                queue.add(cur);
            } else {
                stack.add(cur);
            }
            cur = cur.next;
        }
        ListNode node = null;
        while (!queue.isEmpty()) {
            if (node == null) {
                node = queue.pop();
            } else {
                node.next = queue.pop();
                node = node.next;
            }
            node.next = stack.pollLast();
            node = node.next;
        }
        node.next = null;
    }

}
