package xdroid.mwee.com.posdinnerprinter.framework;

import android.content.Context;

import com.mwee.android.tools.LogUtil;
import com.mwee.android.tools.ProcessUtil;

import xdroid.mwee.com.mwdriverbus.DriverBus;
import xdroid.mwee.com.posdinnerprinter.device.DeviceManager;
import xdroid.mwee.com.posdinnerprinter.print.PrintConnector;
import xdroid.mwee.com.posdinnerprinter.queen.DinnerPrintWakeUpReceiver;

/**
 * 打印的Aplication
 * Created by virgil on 16/8/25.
 */
public class PrintApplication {

    public static void init(final Context context) {
        LogUtil.log("printer", ProcessUtil.getCurrentProcessName(context));

        if (ProcessUtil.isMainProcess(context)) {
            LogUtil.log("printer", "ProcessUtil.isMainProcess(context)");
            PrintConnector.getInstance().init(context);
        } else if (ProcessUtil.getCurrentProcessName(context).endsWith(":print")) {
            LogUtil.log("printer", "ProcessUtil.getCurrentProcessName(context)-->" + ProcessUtil.getCurrentProcessName(context));
            LogUtil.log("printer", "222");
            DriverBus.setErrorWithException(true);
            DeviceManager.getInstance().rebuildAllPrinter();

            DinnerPrintWakeUpReceiver.registerAlarm(context);

        }
    }

}
