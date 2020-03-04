package 刷题.剑指offer;

/**
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *  
 *
 * 限制：
 *
 * 0 <= s 的长度 <= 10000
 *
 */
public class 替换空格 {
    public String replaceSpace(String s) {
        if (s == null|| s.length() == 0) {
            return "";
        }
        int len = s.length();
        char[] res = new char[3 * len];
        int size = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (' ' == c) {
                res[size++] = '%';
                res[size++] = '2';
                res[size++] = '0';
            } else {
                res[size++] = c;
            }
        }
        return String.copyValueOf(res, 0, size);
    }
}
