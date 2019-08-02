package xdroid.mwee.com.rxjavatest.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Created by zhangmin on 2019/4/7.
 * 堆堆堆
 * <p>
 * "a#c"
 * "b"
 */

public class L_Stack {
    public static void main(String[] args) {
//        String S = "y#fo##f", T = "y#f#o##f";
//
//        System.out.println(backspaceCompare(S, T));


        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        topKFrequent(nums, k);
    }


    /**
     * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     * 示例 2:
     * <p>
     * 输入: nums = [1], k = 1
     * 输出: [1]
     *
     * @param nums
     * @param k
     * @return
     */
    public static List<Integer> topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            Integer value = hashMap.get(nums[i]);
            if (value == null) {
                hashMap.put(nums[i], 1);
            } else {
                hashMap.put(nums[i], ++value);
            }
        }

        ArrayList<Map.Entry<Integer, Integer>> arrayList = new ArrayList(hashMap.entrySet());
        Collections.sort(arrayList, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> t0, Map.Entry<Integer, Integer> t1) {
                return t1.getValue().compareTo(t0.getValue());
            }
        });


        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : arrayList) {
            list.add(entry.getKey());
        }

        return list.subList(0, k);

    }


    /**
     * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符
     * <p>
     * 示例 1：
     * <p>
     * 输入：S = "ab#c", T = "ad#c"
     * 输出：true
     * 解释：S 和 T 都会变成 “ac”。
     * <p>
     * "a##c"
     * "#a#c"
     * <p>
     * "y#fo##f"
     * "y#f#o##f"
     *
     * @param S
     * @param T
     * @return
     */
    public static boolean backspaceCompare(String S, String T) {

        Stack<String> sStack = new Stack<>();

        Stack<String> tStack = new Stack<>();

        for (int i = 0; i < S.length(); i++) {
            char character = S.charAt(i);
            if (character == '#') {
                if (!sStack.isEmpty()) {
                    sStack.pop();
                }
            } else {
                sStack.push(character + "");
            }
        }

        for (int i = 0; i < T.length(); i++) {
            Character character = T.charAt(i);
            if (character.equals('#')) {
                if (!tStack.isEmpty()) {
                    tStack.pop();
                }
            } else {
                tStack.push(character + "");
            }
        }

        if (sStack.size() != tStack.size()) {
            return false;
        }

        while (!sStack.isEmpty()) {
            if (!sStack.pop().equals(tStack.pop())) {
                return false;
            }
        }

        return true;

    }

}
