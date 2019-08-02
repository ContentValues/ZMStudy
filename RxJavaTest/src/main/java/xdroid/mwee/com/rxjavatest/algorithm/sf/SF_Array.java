package xdroid.mwee.com.rxjavatest.algorithm.sf;

import java.util.Arrays;

/**
 * Created by zhangmin on 2019/3/26.
 */

public class SF_Array {

    public static void main(String[] args) {


        int arr[] = {4, 9, 8, 2, 5};

        Sort_Array sort_array = new Sort_Array();
        //sort_array.冒泡(arr);
//        System.out.println(Arrays.toString(arr));

        sort_array.选择(arr);
//        System.out.println(Arrays.toString(arr));

//        sort_array.插入(arr);
//        System.out.println(Arrays.toString(arr));

    }

    static class Sort_Array {

        public void 冒泡(int arr[]) {


            //降序冒泡排序
//            for(int i=0;i<arr.length-1;i++){
//                for(int j=i;j<arr.length-1;j++){
//                    if(arr[j+1] > arr[j] ){
//                        int temp = arr[j+1];
//                        arr[j+1] = arr[j];
//                        arr[j] = temp;
//                    }
//                }
//                System.out.println("冒泡排序 第" + i + "轮排序后的结果为:" + Arrays.toString(arr));
//            }

            //升序冒泡排序
//            for (int i = 0; i < arr.length - 1; i++) {
//                for(int j=0;j<arr.length-1-i;j++){
//                    if(arr[j] > arr[j+1] ){
//                        int temp = arr[j+1];
//                        arr[j+1] = arr[j];
//                        arr[j] = temp;
//                    }
//                }
//                System.out.println("冒泡排序 第" + i + "轮排序后的结果为:" + Arrays.toString(arr));
//            }

            for (int i = 0; i < arr.length - 1; i++) {

                for (int j = 0; j < arr.length - i - 1; j++) {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
                System.out.println("冒泡排序 第" + i + "轮排序后的结果为:" + Arrays.toString(arr));

            }

        }

        public void 选择(int arr[]) {

            ///升序选择
            for(int i=0;i<arr.length-1;i++){
                int min = i;
                for(int j=i;j<arr.length;j++){
                    if(arr[min] > arr[j]){
                        min = j;
                    }
                }
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
                System.out.println("选择升序 排序 第" + i + "轮排序后的结果为:" + Arrays.toString(arr));
            }




            ///降序选择
//            for(int i=0;i<arr.length-1;i++){
//                int max = i;
//                for(int j=i;j<arr.length;j++){
//                    if(arr[j] > arr[max]){
//                        max = j;
//                    }
//                }
//                int temp = arr[i];
//                arr[i] = arr[max];
//                arr[max] = temp;
//                System.out.println("选择降序 排序 第" + i + "轮排序后的结果为:" + Arrays.toString(arr));
//            }


        }
        public void 插入(int arr[]) {


            //从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
            for (int i = 1; i < arr.length; i++) {
                int tmp = arr[i];//记录要插入的数据
                int j = i;
                while (j > 0 && tmp < arr[j - 1]) {//从已经排序的序列最右边的开始比较，找到比其小的数
                    arr[j] = arr[j - 1];//向后挪动
                    j--;
                    //System.out.println("￥￥￥￥￥￥￥￥插入排序 第" + i + "轮排序后的结果为:" + Arrays.toString(arr));
                }
                arr[j] = tmp;//存在比其小的数，插入
                //System.out.println("插入排序 第" + i + "轮排序后的结果为:" + Arrays.toString(arr));
            }
        }


        public void 快速(int s[], int l, int r) {


            if (l < r) {

                int i = l, j = r, x = s[i];

                while (i<j){

                    if(i<j && s[j] >= x ){//从后向前找小于x的值
                        j--;
                    }
                    if(i<j){
                        s[i++] = s[j];
                    }

                    if(i<j && s[i] < x){//从前向后找大于x的值
                        i++;
                    }
                    if(i<j){
                        s[j--] = s[i];
                    }
                }
                s[i] = x;
                快速(s, l, i - 1);
                快速(s, i + 1, r);
            }



        }





        public void 归并() {

        }
    }
}
