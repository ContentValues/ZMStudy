package xdroid.mwee.com.rxjavatest.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by zhangmin on 2019/4/2.
 */

public class L_Sort {

    public static void main(String[] args) {

//        int nums1[] = {4, 9, 5};
//        int nums2[] = {9,4,9,8,4};
//        //intersection(nums1, nums2);
//        intersect(nums1, nums2);

//
//        int nums3[] = {1, 3, 4, 2};
//        sortArrayByParityII(nums3);
//
//
        ListNode listNode = new ListNode(3);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(1);
        ListNode listNode3 = new ListNode(-1);

        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        insertionSortList(listNode);
//
//        sortList(listNode);

    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 4->2->1->3
     * 输出: 1->2->3->4
     * 示例 2:
     * <p>
     * 输入: -1->5->3->4->0
     * 输出: -1->0->3->4->5
     *
     * @return
     */
    public static ListNode sortList(ListNode head) {

        ArrayList<ListNode> list = new ArrayList<>();
        ListNode first = head;
        while (first != null) {
            list.add(first);
            first = first.next;
        }

        //使用插入排序
        for (int i = 1; i < list.size(); i++) {
            int val = list.get(i).val;
            int j = i;
            while (j > 0 && val < list.get(j - 1).val) {

                list.get(j).val = list.get(j - 1).val;
                j--;
            }
            list.get(j).val = val;
        }


        ListNode second = new ListNode(-1);
        ListNode headerSecond = second;
        for (ListNode listNode : list) {
            listNode.next = null;
            headerSecond.next = listNode;
            headerSecond = headerSecond.next;
        }
        return second.next;

    }


    /**
     * 插入排序算法：

     插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
     每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
     重复直到所有输入数据插入完为止。
     示例 1：
     输入: 4->2->1->3
     输出: 1->2->3->4
     示例 2：
     输入: -1->5->3->4->0
     输出: -1->0->3->4->5
     * @param head
     * @return
     */
    public static ListNode insertionSortList(ListNode head) {

        ListNode dummyHead = new ListNode(-1);
        while (head != null) {
            ListNode next = head.next;
            ListNode p = dummyHead;
            while (p.next != null && head.val > p.next.val)
                p = p.next;

            head.next = p.next;
            p.next = head;
            head = next;
        }
        return dummyHead.next;
    }


    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     * <p>
     * <p>
     * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出: [9,4]
     * 说明:
     * <p>
     * 输出结果中的每个元素一定是唯一的。
     * 我们可以不考虑输出结果的顺序。
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        //todo 优化版本
        int idx = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (set.remove(nums2[i])) {
                nums1[idx++] = nums2[i];
            }
        }
        return Arrays.copyOf(nums1, idx);


//        HashSet<Integer> set1 = new HashSet<>();
//        for (int i : nums1) {
//            set1.add(i);
//        }
//        HashSet<Integer> set2 = new HashSet<>();
//        for (int j : nums2) {
//            set2.add(j);
//        }
//        set1.retainAll(set2);
//        int[] output = new int[set1.size()];
//        int idx = 0;
//        for (Integer s : set1) output[idx++] = s;
//        System.out.println(Arrays.toString(output));
//        return output;
    }


    /**
     * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
     * <p>
     * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
     * <p>
     * 你可以返回任何满足上述条件的数组作为答案。
     * <p>
     * 示例：
     * <p>
     * 输入：[4,2,5,7]
     * 输出：[4,5,2,7]
     * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
     *
     * @param A
     * @return
     */
    public static int[] sortArrayByParityII(int[] A) {

        //todo 优化版本
        int[] arr = new int[A.length];
        int a = 0, b = 1;
        for (int i = 0; i < A.length; i++) {

            if (A[i] % 2 == 0) {
                arr[a] = A[i];
                a += 2;
            } else {
                arr[b] = A[i];
                b += 2;
            }

        }
        System.out.println("sortArrayByParityII--->" + Arrays.toString(arr));
        return arr;


//        for (int i = 0; i < A.length - 1; i++) {
//
//            if (i % 2 == 0) {//当前位置必须放置偶数
//                if (A[i] % 2 == 0) {
//                    continue;
//                } else {
//
//                    for (int j = i + 1; j < A.length ; j++) {
//                        if (A[j] % 2 == 0) {
//                            int temp = A[i];
//                            A[i] = A[j];
//                            A[j] = temp;
//                            break;
//                        }
//                    }
//                }
//            } else {//奇数
//                if (A[i] % 2 != 0) {
//                    continue;
//                } else {
//                    for (int j = i + 1; j < A.length ; j++) {
//
//                        if (A[j] % 2 != 0) {
//                            int temp = A[i];
//                            A[i] = A[j];
//                            A[j] = temp;
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//
//        System.out.println("sortArrayByParityII--->" + Arrays.toString(A));
//
//        return A;
    }


    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums1 = [1,2,2,1], nums2 = [2]
     * 输出: [2]
     * <p>
     * <p>
     * 示例 2:
     * <p>
     * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出: [4,9]
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect(int[] nums1, int[] nums2) {

        int idx = 0;
        List<Integer> set = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {

            //使用remove(Object)方法
            if (set.remove(Integer.valueOf(nums2[i]))) {
                nums1[idx++] = nums2[i];
            }
        }

        return Arrays.copyOf(nums1, idx);


    }
}
