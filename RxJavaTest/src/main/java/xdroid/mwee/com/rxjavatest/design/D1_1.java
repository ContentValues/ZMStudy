package xdroid.mwee.com.rxjavatest.design;

/**
 * Created by zhangmin on 2018/12/4.
 */


/**
 * 单列模式
 */
public class D1_1 {


    /**
     * 饿汉式
     */
    static class DS_1 {

        private static DS_1 instance = new DS_1();

        private DS_1() {

        }

        public static DS_1 getInstance() {
            return instance;
        }
    }

    /**
     * 懒汉式 线程不安全
     */
    static class DS_2 {

        private static DS_2 instance = null;

        private DS_2() {
        }

        public static DS_2 getInstance() {
            if (instance == null) {
                return instance = new DS_2();
            }
            return instance;
        }
    }

    /**
     * 懒汉式 线程安全
     */
    static class DS_3 {

        private static DS_3 instance = null;

        private DS_3() {

        }

        public static synchronized DS_3 getInstance() {
            if (instance == null) {
                return instance = new DS_3();
            }
            return instance;
        }
    }

    /**
     * 双检锁
     */
    static class DS_4 {

        private volatile static DS_4 intance = null;

        private DS_4() {

        }

        public static DS_4 getInstance() {

            if(intance == null){
                synchronized (DS_4.class){
                    if(intance == null){
                        intance = new DS_4();
                    }
                }
            }
            return intance;
        }
    }

}
