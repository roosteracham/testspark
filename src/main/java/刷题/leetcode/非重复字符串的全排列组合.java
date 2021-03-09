package 刷题.leetcode;

import jodd.util.StringUtil;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 非重复字符串的全排列组合 {
    public static void main(String[] args) {
        System.out.println(new 非重复字符串的全排列组合().solve("123", "4123"));
        System.out.println(set);
    }

    static Set<String> set = new HashSet<>();


    public String solve (String s, String t) {
        // write code here
        int borrow = 0;
        if(s.length() > t.length()) {
            String tmp = s;
            s = t;
            t = tmp;
        }

        String result = "";

        for(int i = s.length() - 1; i >= 0; i--) {
            int n1 = s.charAt(i) - '0';
            int n2 = t.charAt(i) - '0';

            int sum = n1 + n2 + borrow;
            result += sum % 10;
            borrow = sum / 10;
        }

        t = t.substring(0, t.length() - s.length());
        if(borrow != 0) {
            for(int j = t.length() - 1; j >= 0; j--) {

                int n2 = t.charAt(j) - '0';
                int sum = n2 + borrow;

                result += sum % 10;
                borrow = sum / 10;
            }
        } else{
            return t + StringUtil.reverse(result);
        }
        return StringUtil.reverse(result);
    }

//    public String handle(String pre, String s) {
//        char[] cs = s.toCharArray();
//        if(cs.length == 1) {
//            return s;
//        }
//
//        return new BigDecimal(s).add(new BigDecimal(s)).toString();
//        Set<String> set = new HashSet<>();
//        for(int i = 0; i < cs.length; i++) {
//            set.add(cs[i] +  handle(String.valueOf(Arrays.copyOfRange(cs, 0, i)) + String.valueOf(Arrays.copyOfRange(cs, i + 1, cs.length))));
//        }
//
//        return String.join(",", set);
//    }
}
