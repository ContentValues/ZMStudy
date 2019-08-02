package xdroid.mwee.com.rxjavatest.design;

/**
 * Created by zhangmin on 2019/3/25.
 */

public class D_String {


    public static void main(String[] args) {

        System.out.println("" + isPalindrome("abc张敏Ba"));
        System.out.println("" + isPalindrome("abcBa"));
        System.out.println("" + isPalindrome("上海自来水来自海上"));

    }


    /**
     * 给定一个字符串，判断其是否为一个回文串。只考虑字母和数字，忽略大小写
     */

    public static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int front = 0;
        int end = s.length() - 1;
        while (front < end) {
            if (Character.toLowerCase(s.charAt(front)) != Character.toLowerCase(s.charAt(end))) {
                break;
            } else {
                front++;
                end--;
            }
        }
        return end <= front;
    }

}
