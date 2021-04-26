import org.eclipse.jetty.util.ArrayQueue;

import java.util.LinkedList;
import java.util.Queue;

public class Test {

    public static void main(String[] args) {
        Node handle = handle(new Integer[]{1, 2, 3, null, 5, null, 6, null, null, 7, null, null, null});
        System.out.println(handle);
    }

    //[1, 2, 3, null, 5, null, 6, null, null, 7, null, null, null]
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
}
