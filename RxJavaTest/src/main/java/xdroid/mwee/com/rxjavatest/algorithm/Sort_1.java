package xdroid.mwee.com.rxjavatest.algorithm;

import java.util.Arrays;


/**
 * Created by zhangmin on 2018/12/29.
 */

/**
 * 稳定：如果a原本在b前面，而a=b，排序之后a仍然在b的前面；
 * 不稳定：如果a原本在b的前面，而a=b，排序之后a可能会出现在b的后面；
 * 内排序：所有排序操作都在内存中完成；
 * 外排序：由于数据太大，因此把数据放在磁盘中，而排序通过磁盘和内存的数据传输才能进行；
 * 时间复杂度： 一个算法执行所耗费的时间。
 * 空间复杂度：运行完一个程序所需内存的大小
 */
public class Sort_1 {

    public static void main(String[] args) {

        //int[] array = {1, 2, 3, 3, 4, 5};

//        int[] array = {1, 8, 3, 2, 5, 4,19,5,0};
//        //int[] array = {1, 2, 3, 3, 4, 5, 7, 8, 12, 17};
//        //System.out.println(Arrays.toString(bubbleSort(array)));
//        System.out.println(Arrays.toString(selectSort(array)));
        //System.out.println(Arrays.toString(MergeSort(array)));
        //System.out.println(findLastEqual(array, 3));
        //System.out.println(binarySerach(array, 3));
//        System.out.println(findFirstEqual(array, 3));
//        System.out.println(findLastEqual(array, 3));


        int[] array = {72, 6, 57, 88, 60/*, 42,83,73,48,85*/};

        quick_sort(array, 0, array.length - 1);

        System.out.println(Arrays.toString(array));

    }

    static void quck(int[] s, int l, int r) {

        int i = l;
        int j = r;
        int x = s[l];

        while (i < j) {

            while (i < j && s[j] >= x) {

                j--;
            }

            if (i < j) {
                s[i++] = s[j];

            }


            while (i < j && s[i] < x) {
                i++;
            }

            if (i < j) {

                s[j--] =s[i];
            }


        }

        s[i] = x;
        quick_sort(s, l, i - 1);
        quick_sort(s, i + 1, r);


    }


    static void quick_sort(int s[], int l, int r) {
        if (l < r) {
            //Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1
            int i = l, j = r, x = s[l];
            while (i < j) {
                while (i < j && s[j] >= x) // 从右向左找第一个小于x的数
                    j--;
                if (i < j)
                    s[i++] = s[j];

                while (i < j && s[i] < x) // 从左向右找第一个大于等于x的数
                    i++;
                if (i < j)
                    s[j--] = s[i];
            }
            s[i] = x;
            quick_sort(s, l, i - 1); // 递归调用
            quick_sort(s, i + 1, r);
        }
    }


    // 查找第一个相等的元素
    static int findFirstEqual(int[] array, int key) {


        int left = 0;
        int right = array.length;

        //终止条件 left = right+1
        while (left <= right) {

            int mid = (left + right) / 2;
            if (array[mid] >= key) {
                right = mid - 1;
            } else {
                left = left + 1;
            }


        }

        if (array[left] == key) {
            return left;
        }


        return -1;


//        int left = 0;
//        int right = array.length - 1;
//        while (left <= right) {
//            int mid = (left + right) / 2;
//            if (array[mid] >= key) {
//                right = mid - 1;
//            } else {
//                left = mid + 1;
//            }
//        }
//        if (left < array.length && array[left] == key) {
//            return left;
//        }
//
//        return -1;
    }


    //查找第一个 就是向左  array[mid] >= key   key大 就是left = mid+1
    //查找第一个 就是向右  array[mid] <= key


    //查找第一个 就是向左
    // 查找最后一个相等的元素
    static int findLastEqual(int[] array, int key) {


        int left = 0;
        int right = array.length;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] <= key) {
                left = left + 1;
            } else {
                right = mid - 1;
            }
        }
        if (array[right] == key) {
            return right;
        }


//        int left = 0;
//        int right = array.length - 1;
//        //跳出循环条件 left>right 并且left = right+1
//        while (left <= right) {
//            int mid = (left + right) / 2;
//            if (array[mid] <= key) {
//                left = mid + 1;
//            } else {
//                right = mid - 1;
//            }
//        }
//        if (right >= 0 && array[right] == key) {
//            return right;
//        }

        return -1;
    }

    /**
     * 二分查找，找到该值在数组中的下标，否则为-1
     */
    static int binarySerach(int[] array, int key) {


        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = array.length / 2;
            if (array[mid] == key) {
                return mid;
            }
            if (array[mid] >= key) {

                right = mid - 1;
            } else {
                left = left + 1;
            }

        }
        return -1;


//        int left = 0;
//        int right = array.length - 1;
//
//        // 这里必须是 <=
//        while (left <= right) {
//            int mid = (left + right) / 2;
//            if (array[mid] == key) {
//                return mid;
//            }
//            else if (array[mid] < key) {
//                left = mid + 1;
//            }
//            else {
//                right = mid - 1;
//            }
//        }
//
//        return -1;
    }


    /**
     * 冒泡排序
     * <p>
     * T(n) = O(n)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
     */
    public static int[] bubbleSort(int[] array) {

        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < array.length - 1 - i; j++) {

                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }

            }
        }

        return array;


//        if (array == null || array.length == 0) {
//            return array;
//        }
//        for (int i = 0; i < array.length; i++) {
//            for (int j = 0; j < array.length - 1 - i; j++) {
//                //todo 递增
//                if (array[j] > array[j + 1]) {
//                    int temp = array[j + 1];
//                    array[j + 1] = array[j];
//                    array[j] = temp;
//                }
//                //todo 递减
////                if (array[j] < array[j + 1]) {
////                    int temp = array[j];
////                    array[j] = array[j + 1];
////                    array[j + 1] = temp;
////                }
//            }
//        }
//        return array;
    }

    /**
     * 最稳定的排序算法之一，因为无论什么数据进去都是O(n2)的时间复杂度，所以用到它的时候，数据规模越小越好。
     * 唯一的好处可能就是不占用额外的内存空间了吧
     * <p>
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，
     * 然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
     *
     * @param array
     * @return
     */
    public static int[] selectSort(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }

        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[i] > array[j]) {
                    minIndex = j;
                }
            }

            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }


//        for (int i = 0; i < array.length; i++) {
//            int min = i;
//            for (int j = i; j < array.length; j++) {
//                if (array[min] > array[j]) {
//                    min = j;
//                }
//            }
//            int temp = array[min];
//            array[min] = array[i];
//            array[i] = temp;
//
//        }
        return array;
//
//            int minLocation = i;
//            for (int j = i; j < array.length; j++) {
//                if (array[minLocation] > array[j]) {
//                    minLocation = j;
//                }
//            }
//
//            int temp = array[minLocation];
//            array[minLocation] = array[i];
//            array[i] = temp;
//
//        }
//
//        return array;
    }


    /**
     * 归并算法
     * 他妈的搞不懂
     */
    public static int[] MergeSort(int[] array) {

        if (array.length < 2) {
            return array;
        }

        int[] left = Arrays.copyOfRange(array, 0, array.length / 2);
        int[] right = Arrays.copyOfRange(array, array.length / 2, array.length);


        return merge(MergeSort(left), MergeSort(right));


//        if (array.length < 2)
//            return array;
//        int mid = array.length / 2;
//        int[] left = Arrays.copyOfRange(array, 0, mid);
//        int[] right = Arrays.copyOfRange(array, mid, array.length);
//        return merge(MergeSort(left), MergeSort(right));

    }


    public static int[] merge(int[] left, int[] right) {

        int[] result = new int[left.length + right.length];
        int i = 0;
        int j = 0;
        for (int index = 0; index < result.length; index++) {

            if (i >= left.length) {
                result[index] = right[j++];
            } else if (j >= right.length) {
                result[index] = left[i++];
            } else if (left[i] > right[j]) {
                result[index] = right[j++];
            } else {
                result[index] = left[i++];
            }
        }
        return result;

//        int[] result = new int[left.length + right.length];
//        int i = 0;
//        int j = 0;
//        for (int index = 0; index < result.length; index++) {
//            if (i >= left.length) {
//                result[index] = right[j++];
//            } else if (j >= right.length) {
//                result[index] = left[i++];
//            } else if (left[i] > right[j]) {
//                result[index] = right[j++];
//            } else {
//                result[index] = left[i++];
//            }
//        }
//        return result;
    }


}
