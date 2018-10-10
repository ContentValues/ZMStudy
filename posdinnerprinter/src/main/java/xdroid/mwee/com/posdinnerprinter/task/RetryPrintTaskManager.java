package xdroid.mwee.com.posdinnerprinter.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import xdroid.mwee.com.posdinnerprinter.print.PrintTaskDBModel;

/**
 * Created by zhangmin on 2018/7/11.
 */

public class RetryPrintTaskManager {

    private static final RetryPrintTaskManager intance = new RetryPrintTaskManager();

    ExecutorService executorService;

    private RetryPrintTaskManager() {

        init();
    }

    public static RetryPrintTaskManager getInstance() {
        return intance;
    }

    private void init() {

        executorService = Executors.newFixedThreadPool(4);

    }


    class Worker implements Runnable {
        public PrintTaskDBModel task;

        public Worker(PrintTaskDBModel task) {
            this.task = task;
        }

        @Override
        public void run() {
            DinnerPrintProcessor.processPrint(task);
        }
    }


    /**
     * 接收一个新的打印任务
     *
     * @param task PrintTaskDBModel
     */
    public void pushNew(final PrintTaskDBModel task) {

        executorService.execute(new Worker(task));
    }


}
