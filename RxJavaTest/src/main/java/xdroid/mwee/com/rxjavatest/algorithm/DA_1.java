package xdroid.mwee.com.rxjavatest.algorithm;

import java.util.Arrays;

/**
 * Created by zhangmin on 2019/3/18.
 */

public class DA_1 {


    public static void main(String[] args) {


        int[] arr = {72, 6, 57, 88, 60/*, 42,83,73,48,85*/};

        //maopao(arr);

        //quick(arr, 0, arr.length - 1);
        select(arr);
        System.out.println(Arrays.toString(arr));


    }


    /**
     * 冒泡排序
     *
     * @param arr
     */
    public static void maopao(int[] arr) {

        for (int i = 0; i < arr.length; i++) {

            for (int j = 0; j < arr.length - 1 - i; j++) {

                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }


    /**
     * 快速排序
     *
     * @param s
     */
    public static void quick(int[] s, int l, int r) {

        if (l < r) {
            int i = l, j = r, x = s[l];
            while (i < j) {
                while (i < j && s[j] >= x) //从后向前找小于x的值
                    j--;
                if (i < j)
                    s[i++] = s[j];
                while (i < j && s[i] < x) //从前向后找大于x的值
                    i++;
                if (i < j)
                    s[j--] = s[i];
            }
            s[i] = x;
            quick(s, l, i - 1);
            quick(s, i + 1, r);
        }

    }

    /**
     * 选择排序
     *
     * @param arr
     */
    public static void select(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;//最小值的位置
            for (int j = i; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {//todo 最小位置比较
                    minIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }


    /**
     * 归并排序
     *
     * @param arr
     */
    public static void merge(int arr) {

    }

}
