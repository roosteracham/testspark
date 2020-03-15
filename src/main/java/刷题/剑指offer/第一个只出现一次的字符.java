package 刷题.剑指offer;

import java.util.HashMap;
import java.util.Map;

/**
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。
 *
 * 示例:
 *
 * s = "abaccdeff"
 * 返回 "b"
 *
 * s = ""
 * 返回 " "
 *  
 *
 * 限制：
 *
 * 0 <= s 的长度 <= 50000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 第一个只出现一次的字符 {
    public char firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return ' ';
        }
        char[] chars = s.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < s.length(); i++) {
            map[chars[i] - 'a'] += 1;
        }
        for (int i = 0; i < s.length(); i++) {
            if (map[chars[i] - 'a'] == 1) {
                return chars[i];
            }
        }
        return ' ';
    }
}
