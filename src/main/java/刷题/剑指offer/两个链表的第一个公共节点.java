package 刷题.剑指offer;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 两个链表的第一个公共节点 {

    // 两个指针相遇的时候，即是第一个共有节点

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode node1 = headA, node2 = headB;
        while (node1 != node2) {
            node1 = node1 == null ? headB : node1.next;
            node2 = node2 == null ? headA : node2.next;
        }
        return node1;
    }
//    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        Set<ListNode> set = new HashSet<>();
//        ListNode a = headA, b = headB;
//        while (headA != null && headB != null) {
//            if (setEle(headA, set)) {
//                return headA;
//            } else {
//                set.add(headA);
//            }
//            headA = headA.next;
//            if (setEle(headB, set)) {
//                return headB;
//            } else {
//                set.add(headB);
//            }
//            headB = headB.next;
//
//            if (headA != null && headB == null) {
//                headB = b;
//                set.clear();
//            }
//            if (headA == null && headB != null) {
//                headA = a;
//                set.clear();
//            }
//        }
//
//        return null;
//    }
//
//    private boolean setEle(ListNode headA, Set<ListNode> set) {
//        return set.contains(headA);
//    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
