package 刷题.剑指offer;

/**
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *
 *  
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *  
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 *
 */
public class 反转链表 {
//    public ListNode reverseList(ListNode head) {
//
//        if (head == null) {
//            return null;
//        }
//        ListNode res = new ListNode(head.val);
//        head = head.next;
//        while (head != null) {
//            ListNode tmp = new ListNode(head.val);
//            tmp.next = res;
//            res = tmp;
//            head = head.next;
//        }
//        return res;
//    }

    //递归做法
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        } else {
            ListNode ret = reverseList(head.next);
            head.next.next = head; // 这里head 是前一个指针，head.next是当前指针，而且总不是空
            head.next = null;
            return ret;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
