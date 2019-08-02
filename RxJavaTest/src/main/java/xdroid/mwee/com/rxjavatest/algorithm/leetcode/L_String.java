package xdroid.mwee.com.rxjavatest.algorithm.leetcode;

import java.util.Stack;

public class L_String {

    public static void main(String[] args) {

        String s = "abcabcabcabc";
        System.out.println(repeatedSubstringPattern(s));


        String ss = "aba";
        //System.out.println(isPalindrome(ss));

        String ttt = "aDB";

        System.out.println(toLowerCase(ttt));


    }

    /**
     * 重复子串长度最长为len/2，直接每次选择一个可以被整除的较小的数，截取开头的那几个字符，然后重复到原长度验证就好。
     *
     * @param str
     * @return
     */
    public static boolean repeatedSubstringPattern(String str) {

        int len = str.length();
        for (int i = 1; i <= len / 2; i++) {
            if (len % i == 0) {
                String temp = str.substring(0, i);
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < len / i; j++) {
                    sb.append(temp);
                }
                if (sb.toString().equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 验证回文字符串
     *
     * @param t1
     * @return
     */
    public static boolean isPalindrome(String t1) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t1.length(); i++) {
            if (Character.isLetterOrDigit(t1.charAt(i))) {
                sb.append(t1.charAt(i));
            }
        }
        String str = sb.toString().toLowerCase().trim();
        for (int j = 0; j < str.length() / 2; j++) {
            if (str.charAt(j) != str.charAt(str.length() - j - 1)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
     *
     * @param s
     * @return
     */
    public static void validPalindrome(String s) {


        StringBuilder stringBuilder = new StringBuilder(s);

        for (int i = 0; i < stringBuilder.length(); i++) {

            System.out.println(isPalindrome(stringBuilder.deleteCharAt(i).toString()));

        }

    }


    /**
     * 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入："ab-cd"
     * 输出："dc-ba"
     *
     * @param S
     * @return
     */
    public static String reverseOnlyLetters(String S) {

        char[] sss = S.toCharArray();
        for (int i = 0; i < sss.length / 2; i++) {
            Character character = sss[i];
            sss[i] = sss[S.length() - i - 1];
            sss[S.length() - i - 1] = character;
        }
        return new String(sss);

    }


    /**
     * 已知 a~z对应数字97~122
     * A~Z对应数字65~90
     * 所以讲字母转成数字判断是否大写，将大写的+32，再将其转为字符
     *
     * @param str
     * @return
     */
    public static String toLowerCase(String str) {

        char[] chars = str.toCharArray();

        String result = "";

        for (int i = 0; i < chars.length; i++) {

            char charster = chars[i];

            if (charster <= 90 && charster >= 65) {

                result = result + (char)(charster + 32);
            } else {
                result = result + charster;
            }

        }
        return result;
    }
}
