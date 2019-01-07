package xdroid.mwee.com.rxjavatest.thread;

/**
 * Created by zhangmin on 2018/11/28.
 */

/**
 * 接口实现创建线程
 * Java的线程是不允许启动两次的，第二次调用必然会抛出IllegalThreadStateException，
 * 这是一种运行时异常，多次调用start被认为是编程错误。
 */
public class V1_1Thread implements Runnable {
    @Override
    public void run() {

        System.out.println(this.getClass().getSimpleName() + " implements Runnable");
    }

    public static void main(String[] args) {

        Thread thread = new Thread(new V1_1Thread());
        thread.start();
        //thread.start();

    }
}
