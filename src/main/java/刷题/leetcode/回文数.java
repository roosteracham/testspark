package 刷题.leetcode;

public class 回文数 {

    public static void main(String[] args) {
        System.out.println(new 回文数().isPalindrome(121));
    }
    public boolean isPalindrome(int x) {
//        String s = x + "";

        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }
        int r = 0;
        while (x > r) {
            r = r * 10 + x % 10;
            x = x/10;
        }
//        for (int i = 0; i < length || i <= length -1-i; i++) {
//            if (!(s.charAt(i) == s.charAt(length - 1- i))) {
//                return false;
//            }
//        }

//        int r = 0;
//        for (int i = 0; i < length / 2; i++) {
//            r = r * 10 + x % 10;
//            x = x/10;
//        }
        return x == r || x / 10 == r;
    }
}
