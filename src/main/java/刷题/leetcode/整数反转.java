package 刷题.leetcode;

public class 整数反转 {

    public static void main(String[] args) {
        System.out.println(new 整数反转().solve(-123));
    }

    public int solve(int x) {
        boolean minus = x < 0;
        x = Math.abs(x);
        try {
            StringBuilder res = new StringBuilder();
            while (x != 0) {
                res.append( x % 10);
                x = x / 10;
            }
            return Integer.parseInt(res.toString()) * (minus? -1 : 1);
        } catch (Exception e) {
        }
        return 0;
    }
}
