package 刷题.leetcode;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 两两交换链表中的节点24 {
    public ListNode swapPairs(ListNode head) {
        ListNode node = new ListNode(0), res = node;
        node.next = head;
        while (head != null && head.next != null) {
            ListNode tmp = head.next.next;
            node.next = head.next;
            node.next.next = head;
            head.next = tmp;
            node = head;
            head = head.next;
        }

        return res.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
