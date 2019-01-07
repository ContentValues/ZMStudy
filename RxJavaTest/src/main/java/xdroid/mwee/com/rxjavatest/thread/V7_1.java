package xdroid.mwee.com.rxjavatest.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by zhangmin on 2018/11/29.
 */

/**
 * FutureTask+Future
 */
public class V7_1 {

    public static void main(String[] args) {

        ExecutorService poolExecutor = Executors.newCachedThreadPool();

        Task task = new Task();

        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);

        poolExecutor.submit(futureTask);

        poolExecutor.shutdown();

        System.out.println("主线程在执行任务");

        try {
            System.out.println("task运行结果" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("主线程在执行结束");


    }

    static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {

            System.out.println("子线程在进行计算开始");
            int count = 0;
            for (int i = 0; i < 100; i++) {
                count += i;
            }

            Thread.sleep(3000);
            System.out.println("子线程在进行计算结束");
            return count;
        }
    }

}
