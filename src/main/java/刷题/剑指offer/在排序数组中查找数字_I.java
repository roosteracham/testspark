package 刷题.剑指offer;

public class 在排序数组中查找数字_I {
    public static void main(String[] args) {
        
    }

    public int search(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                sum++;
            }
        }
        return sum;
    }
}
