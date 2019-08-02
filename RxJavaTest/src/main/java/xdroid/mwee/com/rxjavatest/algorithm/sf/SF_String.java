package xdroid.mwee.com.rxjavatest.algorithm.sf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Stack;

public class SF_String {


    public static void main(String[] args) {

//        String s = "abcbas";
//        System.out.println("回文--> " + longestPalindrome(s));


        //String[] ss = {"h","e","l","l","o"};

//        String s = "hello";
//
//
//        new StringBuilder(s).reverse().toString();
//        char[] s1 = new char[5];
//        s1[0] = 'h';
//        s1[1] = 'e';
//        s1[2] = 'l';
//        s1[3] = 'l';
//        s1[4] = 'o';
//
//        reverseString(s1);


        String s = "loveleetcode";
        System.out.println(firstUniqChar(s));

    }


    /**
     * 最长的子字符串
     *
     * @return
     */
    public static int maxSubString() {

        String s = "abcdefacd";

        HashMap<Integer, StringBuffer> hashMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {

                StringBuffer sb = hashMap.get(i);
                if (sb == null) {
                    sb = new StringBuffer();
                    hashMap.put(i, sb);
                }
                if (sb.toString().contains(s.charAt(j) + "")) {
                    break;
                }
                sb.append(s.charAt(j) + "");
            }
        }
        ArrayList<StringBuffer> list = new ArrayList<>(hashMap.values());
        Collections.sort(list, new Comparator<StringBuffer>() {
            @Override
            public int compare(StringBuffer t0, StringBuffer t1) {

                if (t0.length() > t1.length()) {
                    return -1;
                }

                if (t0.length() < t1.length()) {
                    return 1;
                }
                return 0;
            }
        });
        System.out.println(Arrays.toString(list.toArray()));
        for (StringBuffer value : hashMap.values()) {
            System.out.println(value);
        }
        return list.size() == 0 ? 0 : list.get(0).length();
    }


    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {

        int maxPalinLength = 0;
        String longestPalindrome = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                int len = j - i;
                String sub = s.substring(i, j + 1);
                if (isRalindrome(sub)) {
                    if (len >= maxPalinLength) {
                        longestPalindrome = sub;
                        maxPalinLength = len;
                    }
                }
            }

        }
        return longestPalindrome;
    }

    public static boolean isRalindrome(String s) {

        int count = 0;

        if (s.length() % 2 == 0) {
            count = s.length() / 2;
        } else {
            count = s.length() / 2 + 1;
        }

        for (int i = 0; i < count; i++) {

            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;

    }

    /**
     * 反转字符串
     *
     * @param s
     */
    public static void reverseString(char[] s) {


//        int count = 0;
//        if (s.length % 2 == 0) {
//            count = s.length / 2;
//        } else {
//            count = s.length / 2 + 1;
//        }

        for (int i = 0; i < s.length / 2; i++) {
            char character = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = character;

        }

        //System.out.println("前" + Arrays.toString(s));


//        Stack<Character> stack = new Stack<>();
//        for (char c : s) {
//            stack.push(c);
//        }
//        char[] s1 = new char[s.length];
//        int index = 0;
//        while (!stack.isEmpty()) {
//            Character character = stack.pop();
//            s1[index++] = character;
//        }
//        s = s1;
//
//        System.out.println("前" + Arrays.toString(s));
//
//        System.out.println("后" + Arrays.toString(s1));

    }


    /**
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     * <p>
     * 案例:
     * <p>
     * s = "leetcode"
     * 返回 0.
     * <p>
     * s = "loveleetcode",
     * 返回 2.
     *
     * @param s
     * @return
     */
    public static int firstUniqChar(String s) {
        char a = ' ';
        for(int i=0; i<s.length(); ++i) {
            a = s.charAt(i);
            if(s.indexOf(a) == s.lastIndexOf(a)) {
                return i;
            }
        }
        return -1;
    }
}
