package xdroid.mwee.com.posdinnerprinter.queen;
import com.alibaba.fastjson.JSON;
import com.mwee.android.tools.LogUtil;


import xdroid.mwee.com.posdinnerprinter.print.PrintTaskDBModel;
import xdroid.mwee.com.posdinnerprinter.task.DinnerPrintProcessor;
import xdroid.mwee.com.posdinnerprinter.task.RetryPrintTaskManager;


/**
 * TaskDBUtil
 * Created by virgil on 16/7/8.
 */
public class TaskDBUtil {

    public static void receiveNewTask(String taskInfo) {

        PrintTaskDBModel taskModel = JSON.parseObject(taskInfo, PrintTaskDBModel.class);
        taskModel.replaceNoTrans();

        preparePrint(taskModel);
    }

    public static void preparePrint(PrintTaskDBModel taskModel) {
        LogUtil.logBusiness("TaskDBUtil", "PrintStub receive uri=" + taskModel.uri + "; PrinterName=" + taskModel.fsPrinterName + "; fsReportName=" + taskModel.fsReportName + "； fiPrintNo=" + taskModel.fiPrintNo);
        RetryPrintTaskManager.getInstance().pushNew(taskModel);
    }


}
