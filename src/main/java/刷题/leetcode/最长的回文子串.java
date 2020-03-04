package 刷题.leetcode;

public class 最长的回文子串 {

    public static void main(String[] args) {
        System.out.println(new 最长的回文子串().longestPalindrome("aaba"));
    }

    public String longestPalindrome(String s) {
       String max = "";
       int len = s.length();
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                String substring = s.substring(i, j + 1);
                if (ispalindrom(substring) && j + 1-i > max.length()) {
                    max = substring;
                }
            }
        }
       return max;
    }

    private boolean ispalindrom(String substring) {
        return substring.equals(new StringBuilder(substring).reverse().toString());
    }
}
