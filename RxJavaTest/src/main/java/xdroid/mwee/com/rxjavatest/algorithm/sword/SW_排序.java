package xdroid.mwee.com.rxjavatest.algorithm.sword;


import java.util.Arrays;

/**
 * Author：created by SugarT
 * Time：2019/9/24 11
 */
public class SW_排序 {

    public static void main(String[] args) {
        int arr[] = {4, 9, 8, 2, 5};
//        System.out.println(Arrays.toString(冒泡(arr)));
//        System.out.println(Arrays.toString(选择(arr)));
        System.out.println(Arrays.toString(插入(arr)));
    }

    public static int[] 冒泡(int arr[]) {

        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0; j < arr.length - 1 - i; j++) {

                System.out.println("冒泡排序 第" + j + "轮排序后的结果为:" + arr[j] + "  " + arr[j + 1]);

                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
            System.out.println("哈哈 冒泡排序 第" + i + "轮排序后的结果为:" + Arrays.toString(arr));
        }
        return arr;
    }

    public static int[] 选择(int arr[]) {

        for (int i = 0; i < arr.length; i++) {

            int minIndex = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        return arr;
    }

    public static int[] 插入(int arr[]) {

        for (int i = 1; i < arr.length; i++) {
            int j = i;
            int temp = arr[i];
            while ( j>0 && temp < arr[j-1] ){
                arr[j] = arr[j-1];
                j--;
            }
            arr[j] = temp;
        }
        return arr;
    }

    public static int[] 快速(int arr[]) {


        return arr;
    }

}
