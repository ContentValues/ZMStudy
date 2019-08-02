package xdroid.mwee.com.rxjavatest.algorithm.sf;

/**
 * Created by zhangmin on 2019/3/27.
 */

/**
 * 递归
 * <p>
 * 递归，就是在运行的过程中调用自己。
 * 　递归必须要有三个要素：
 * ①、边界条件
 * ②、递归前进段
 * ③、递归返回段
 * 当边界条件不满足时，递归前进；当边界条件满足时，递归返回
 */
public class  SF_Recursive {

    public static void main(String[] args) {

         System.out.println(getFactorial(4));
         System.out.println(getFactorial累计(4));
         System.out.println(getFactorial平方(4));
//
//        System.out.println(recurSive(5));
        //System.out.println("滴滴-->" + sum(4));

        //System.out.println("滴滴-->" + func(4));
    }

    /**
     * n! = n*(n-1)*(n-2).....*1
     */

    public static int getFactorial(int n) {

        if (n == 1) {
//            System.out.println(n);
//            System.out.println(n + "!=1");
            return 1;
        }

        //System.out.println(n);

        //int c = getFactorial(n - 1);

        //System.out.println("$$$$$"+c);

        //int temp = n* getFactorial(n - 1);

        //System.out.println(n + "!=" + temp);

        return n* getFactorial(n - 1);
    }




    /**
     * n + (n-1)+....+1
     */

    public static int getFactorial累计(int n) {

        if (n == 0) {
//            System.out.println(n);
//            System.out.println(n + "!=1");
            return 0;
        }

        //System.out.println(n);

        //int c = getFactorial累计(n - 1);

        //System.out.println("$$$$$"+c);

        int temp = n + getFactorial累计(n - 1);

        //System.out.println(n + "!=" + temp);

        return temp;
    }

    /**
     *  n2 + (n-1)2+....+1
     *  平方相加
     */

    public static int getFactorial平方(int n) {

        if (n == 0) {
            System.out.println(n);
            System.out.println(n + "!=1");
            return 0;
        }

        System.out.println(n);

        int c = getFactorial平方(n - 1);

        System.out.println("$$$$$"+c);

        int temp = n*n + c;

        System.out.println(n + "!=" + temp);

        return temp;
    }



    /**
     * 1 1 2 3 5 8 13 21 34 求第40个数
     * <p>
     * F1 = 1 F2 = 1 F3 = F1+F2  F4 = F3+F2
     */
    public static int recurSive(int n) {
        if (n == 1 || n == 2) {

            return 1;
        }
        return recurSive(n - 1) + recurSive(n - 2);
    }

    /**
     * 求1+2!+3!+…+n!的和
     * <p>
     * n!+n(n-1)!+n(n-2)!+.....+1
     */
    public static int sum(int n) {







        // sum = 1*1  1*1*2 1*1*2*3
        int func = 1;
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            func = func * i;
            sum += func;
        }
        return sum;
    }

}
