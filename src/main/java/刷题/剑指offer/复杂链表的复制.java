package 刷题.剑指offer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * 示例 4：
 * <p>
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 *  
 * <p>
 * 提示：
 * <p>
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 * 节点数目不超过 1000 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 复杂链表的复制 {

    public static void main(String[] args) {
        Node n1 = new Node(3);
        Node n2 = new Node(3);
        Node n3 = new Node(3);
        n1.random = null;
        n1.next = n2;
        n2.next = n3;
        n2.random = n1;
        new 复杂链表的复制().copyRandomList(n1);
    }
    Map<String, Node> map = new HashMap<>();


//    public Node copyRandomList(Node head) {
//        if(head == null)  return null;
//        Node newHead = new Node(head.val);//建立我们新链表的头
//        Node tmp = newHead;
//        HashMap<Node, Node> map = new HashMap<>();//建立哈希表
//        map.put(head, tmp);
//
//        while(head.next != null){//如果原来链表的下一个不为null
//            head = head.next;
//            tmp.next = new Node(head.val);//复制下一个
//            tmp = tmp.next;
//            map.put(head, tmp);//放入哈希表
//        }
//        tmp.next = null;
//        //至此建立了包括val和next的链表。
//        for(Node key : map.keySet()){
//            map.get(key).random = map.get(key.random);//新链表的每一个结点指向匹配的旧结点指向的旧结点的匹配新结点
//        }
//        return newHead;
//    }


    // 我的垃圾方法
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        Node res = new Node(0);
        Node backNode = new Node(head.val);
        map.put(head.toString(), backNode);
        if (head.next == null) {
            backNode.next = head.next;
        } else if (map.containsKey(head.next.toString())) {
            backNode.next = map.get(head.next.toString());
        } else {
            Node node = new Node(head.next.val);
            map.put(head.next.toString(), node);
            backNode.next = node;
        }
        map.put(head.toString(), backNode);
        if (head.random == null) {
            backNode.random = null;
        } else if (map.containsKey(head.random.toString())) {
            Node node = map.get(head.random.toString());
            backNode.random = node;
        } else {
            Node node = new Node(head.random.val);
            map.put(head.random.toString(), node);
            backNode.random = node;
        }
        res.next = backNode;

        doHandle(backNode, head.next);
        return res.next;
    }

    private void doHandle(Node backNode, Node head) {
        if (head != null) {
            Node tmp = null;
            if (map.containsKey(head.toString())) {
                tmp = map.get(head.toString());
            } else {
                tmp = new Node(head.val);
                map.put(head.toString(), tmp);
            }
//            tmp.next = head.next;
            if (head.next == null) {
                tmp.next = head.next;
            } else if (map.containsKey(head.next.toString())) {
                tmp.next = map.get(head.next.toString());
            } else {
                Node node = new Node(head.next.val);
                map.put(head.next.toString(), node);
                tmp.next = node;
            }
            if (head.random == null) {
                tmp.random = null;
            } else if (map.containsKey(head.random.toString())) {
                Node node = map.get(head.random.toString());
                tmp.random = node;
            } else {
                Node node = new Node(head.random.val);
                map.put(head.random.toString(), node);
                tmp.random = node;
            }
            backNode.next = tmp;
            doHandle(backNode.next, head.next);
        }
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
