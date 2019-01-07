package xdroid.mwee.com.rxjavatest.thread;

/**
 * Created by zhangmin on 2018/11/28.
 */

/**
 * 继承实现创建线程
 */
public class V1_2Thread extends Thread {

    @Override
    public void run() {
        super.run();
        System.out.println(this.getClass().getSimpleName() + " extends Thread");
    }

    public static void main(String[] args) {
        V1_2Thread v1_2Thread = new V1_2Thread();
        v1_2Thread.start();
    }
}
