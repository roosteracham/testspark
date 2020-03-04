package 刷题.leetcode;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abacabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 无重复字符的最长子串 {

    public static void main(String[] args) {
        System.out.println(new 无重复字符的最长子串().lengthOfLongestSubstring1("abaa"));
    }
    public int lengthOfLongestSubstring1(@NotNull String s) {
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int left = 0;
        for(int i = 0; i < s.length(); i ++){
            if(map.containsKey(s.charAt(i))){
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-left+1);
        }
        return max;

    }


//    static int len = 1;
//    static int rnt = 1;
//    public static int lengthOfLongestSubstring(String s) {
//
//        if (s == null || s.length() == 0) {
//            return 0;
//        }
//        if (s.length() == 1) {
//            return 1;
//        }
//        char[] res = new char[s.length()];
//        res[0] = s.charAt(0);
//        for (int i = 1; i < s.length(); i++) {
//            char curChar = s.charAt(i);
//            int pos = findChar(curChar, res);
//            if (-1 == pos) {
//                res[len] = curChar;
//                len++;
//                if (len > rnt) {
//                    rnt = len;
//                }
//            } else {
//                adjust(curChar, res, pos);
//            }
//        }
//        return rnt;
//    }
//
//    private static void adjust(char curChar, char[] res, int pos) {
//        for (int i = pos; i < len; i++) {
//            res[i - pos] = res[i + 1];
//        }
//        len = len - pos;
//        if (len > rnt) {
//            rnt = len;
//        }
//        res[len - 1] = curChar;
//        for (int i = len; i < res.length; i++) {
//            res[i] = 0;
//        }
//    }
//
//    static int findChar(char i, char[] res) {
//        for (int j = 0; j < res.length; j++) {
//            if (i == res[j]) {
//                return j;
//            }
//        }
//        return -1;
//    }
}
