package xdroid.mwee.com.posdinnerprinter.task;

import android.content.ContentValues;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.mwee.android.print.base.PrinterConfig;
import com.mwee.android.print.processor.PrintDataItem;
import com.mwee.android.print.processor.PrintResult;
import com.mwee.android.print.processor.PrintResultCode;
import com.mwee.android.print.processor.PrinterManager;
import com.mwee.android.sqlite.base.APPConfig;
import com.mwee.android.sqlite.base.DBSimpleUtil;
import com.mwee.android.tools.DateUtil;
import com.mwee.android.tools.LogUtil;
import com.mwee.android.tools.ToastUtil;

import java.util.List;

import xdroid.mwee.com.mwdriverbus.DriverBus;
import xdroid.mwee.com.posdinnerprinter.business.OrderCommandProcessor;
import xdroid.mwee.com.posdinnerprinter.device.DeviceManager;
import xdroid.mwee.com.posdinnerprinter.print.PrintTaskDBModel;
import xdroid.mwee.com.posdinnerprinter.print.PrinterDBModel;

/**
 * PrintdProcessor
 * Created by virgil on 16/7/12.
 */
public class DinnerPrintProcessor {


    /**
     * 将打印数据提交到打印机
     *
     * @param data   ArrayList<PrintDataItem>
     * @param config PrinterConfig
     * @return PrintResult
     */
    public static synchronized PrintResult submitToPrinter(PrintTaskDBModel taskModel, List<PrintDataItem> data, PrinterConfig config) {

        PrintResult result = null;

        config = optPrinterConfig(taskModel);
        PrinterManager printer1 = new PrinterManager(config, false);
        printer1.setData(data);
        LogUtil.log("PrintLoop 发送到打印机[" + taskModel.fiPrintNo + "]");
        try {
            // 一般情况下使用印号作为唯一标识
            StringBuilder seq = new StringBuilder();
            seq.append(taskModel.fiPrintNo);
            // 如果是手动重打，避免服务端去重,需要额外拼接时间
            if (taskModel.manaualReprint == 1) {
                seq.append("_");
                seq.append(DateUtil.getCurrentDateTime("ssSSS"));
            }
            result = printer1.start(seq.toString());
        } catch (Exception e) {
            result = new PrintResult();
            result.result = PrintResultCode.PRINT_EXCEPTION;
            result.addTrace(e);
        } catch (Error e) {
            result = new PrintResult();
            result.result = PrintResultCode.PRINT_EXCEPTION;
            result.addTrace(e);
        }
        if (result != null && result.trace != null) {
            LogUtil.logError("打印异常", result.trace);
        }
        processPrintResult(result, taskModel);

        return result;
    }

    /**
     * 处理打印任务的结果
     *
     * @param result    PrintResult
     * @param taskModel PrintTaskDBModel
     */
    private static void processPrintResult(PrintResult result, PrintTaskDBModel taskModel) {
        ContentValues values = new ContentValues();

        taskModel.fiPrintCount++;
        values.put("fiPrintCount", taskModel.fiPrintCount);
        if (result != null && result.result == PrintResultCode.SUCCESS) {
            taskModel.fiStatus = 4;
            taskModel.fsFinishTime = DateUtil.getCurrentTime();
            values.put("fiStatus", 4);
            values.put("fsFinishTime", DateUtil.getCurrentTime());
        } else if (result != null && result.result == PrintResultCode.PRINTED) {
            // 云打印机，打印中和打印失败同样处理
            taskModel.fiErrCount++;
            taskModel.fiStatus = 7;
            values.put("fiStatus", 7);
            values.put("fiErrCount", taskModel.fiErrCount);
            if (taskModel.fiErrCount == taskModel.fiRetry) {
                values.put("fsFinishTime", DateUtil.getCurrentTime());
            }
        } else {
            taskModel.fiErrCount++;
            taskModel.fiStatus = 3;
            values.put("fiStatus", 3);
            values.put("fiErrCount", taskModel.fiErrCount);
            if (taskModel.fiErrCount == taskModel.fiRetry) {
                values.put("fsFinishTime", DateUtil.getCurrentTime());
            }
        }
        values.put("fsTaskDetail", taskModel.fsTaskDetail);
    }

    /**
     * 根据打印机名称获取打印机
     *
     * @return PrinterDBModel
     */
    public static PrinterDBModel getPrinterByPrinterName(String printerName) {
        String sql = "where fsPrinterName='" + printerName + "' and fiStatus = '1'";
        return DBSimpleUtil.query(APPConfig.DB_MAIN, sql, PrinterDBModel.class);
    }

    public static PrinterConfig optPrinterConfig(PrintTaskDBModel taskModel) {
        PrinterConfig config = null;
        synchronized (DeviceManager.getInstance()) {
            String printerName = taskModel.fsPrinterName;
            if (taskModel.is_backup_printer == 1 && !TextUtils.isEmpty(taskModel.fsbakprintername)) {
                printerName = taskModel.fsbakprintername;
            }

            config = DeviceManager.getInstance().getPrinterConfig(printerName);
            if (config == null) {
                PrinterDBModel printer = getPrinterByPrinterName(printerName);
                if (printer == null) {
                    LogUtil.log("printer", "根据打印机名称获取打印机失败-->" + printerName);
                    return null;
                }
                DeviceManager.getInstance().registPrinter(printer);
            }
            config = DeviceManager.getInstance().getPrinterConfig(printerName);
        }
        return config;
    }


    /**
     * 处理打印任务
     *
     * @param taskModel PrintTaskDBModel
     */
    @WorkerThread
    public static void processPrint(PrintTaskDBModel taskModel) {
        PrinterConfig config = optPrinterConfig(taskModel);
        JSONObject ob = null;
        if (!TextUtils.isEmpty(taskModel.fsPrnData)) {
            try {
                ob = JSONObject.parseObject(taskModel.fsPrnData);
            } catch (JSONException e) {
                e.printStackTrace();
                ToastUtil.showToast("数据异常");
                return;
            }
        }
        try {
            if (config == null) {
                throw new Exception(taskModel.fsPrinterName);
            }
            LogUtil.log("PrintLoop 开始打印[" + taskModel.fiPrintNo + "]");
            DriverBus.call(taskModel.uri, ob, taskModel, config);
        } catch (Exception e) {
            LogUtil.logError(e);
            PrintResult result = new PrintResult();
            result.result = PrintResultCode.PRINT_EXCEPTION;
            processPrintResult(result, taskModel);
            //TaskDBUtil.checkRetry(taskModel);
        }
    }

}
