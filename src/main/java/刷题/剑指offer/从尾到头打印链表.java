package 刷题.剑指offer;

import java.util.Arrays;

/**
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *  
 *
 * 限制：
 *
 * 0 <= 链表长度 <= 10000
 *
 */
public class 从尾到头打印链表 {
//    public int[] reversePrint(ListNode head) {
//        int[] res = new int[10000];
//        int size = 0;
//        while (head != null) {
//            res[10000 - size - 1] = head.val;
//            size ++;
//            head = head.next;
//        }
//        return Arrays.copyOfRange(res, 10000 - size, 10000);
//    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(3);
        head.next.next =  new ListNode(2);
        new 从尾到头打印链表().reversePrint(head);
    }
    int[] res = new int[10000];
    int size = 0;
    // 递归做法
    public int[] reversePrint(ListNode head) {
        reversePrint1(head);
        return Arrays.copyOfRange(res, 0, size);
    }
    public void reversePrint1(ListNode head) {
        if (head != null) {
            reversePrint1(head.next);
            res[size++] = head.val;
        }
    }

 public static class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }
}
