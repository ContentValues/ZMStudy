package xdroid.mwee.com.posdinnerprinter.print;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.mwee.android.tools.GlobalCache;
import com.mwee.android.tools.LogUtil;
import com.mwee.android.tools.base.BaseConfig;

import java.util.List;
import java.util.Stack;

import xdroid.mwee.com.posdinnerprinter.IDinnerPrintInterface;

/**
 * PrintConnector
 * Created by virgil on 16/7/5.
 */
public class PrintConnector {

    private final static PrintConnector instance = new PrintConnector();
    private IDinnerPrintInterface printBinder = null;
    private Stack<PrintTaskDBModel> waitingPrint = new Stack<>();

    private PrintConnector() {
    }

    public static PrintConnector getInstance() {
        return instance;
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            printBinder = IDinnerPrintInterface.Stub.asInterface(service);
            if (!waitingPrint.isEmpty()) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (!waitingPrint.empty()) {
                            print(waitingPrint.pop(),false);
                        }
                    }
                }).start();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            printBinder = null;
            LogUtil.log("printer", "PrintConnector onServiceDisconnected");
        }
    };


    public void init(final Context context) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (printBinder == null) {
                    checkConnect(context, null);
                } else {
                    try {
                        printBinder.init();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    @WorkerThread
    public boolean checkConnect(Context context, PrintTaskDBModel task) {
        if (printBinder == null) {
            if (task != null) {
                waitingPrint.push(task);
            }
            LogUtil.log("printer", "PrintConnector start connect print service");
            try {
                Intent intent = new Intent();
                intent.setAction("xdroid.mwee.com.posdinnerprinter.conn.PosPrintService");
                intent.setPackage(context.getPackageName());
                context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
            } catch (Exception e) {
                LogUtil.logError(e);
                LogUtil.log("printer", "Bind失败" + e.getMessage());
            }
            return false;
        }
        return true;
    }

    @WorkerThread
    public void print(final PrintTaskDBModel task, final boolean taskSyncFromCenter) {
        LogUtil.log("PrintConnector  fiPrintNo=[" + task.fiPrintNo + "]");
        if (!checkConnect(GlobalCache.getContext(), task)) {
            return;
        }
        if (TextUtils.isEmpty(task.fsPrinterName)) {
            Toast.makeText(GlobalCache.getContext(), "没有配置关联的打印机", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!TextUtils.isEmpty(task.uri)) {
            try {
                if (taskSyncFromCenter) {
                    printBinder.printSyncTask(JSON.toJSONString(task));
                } else {
                    printBinder.processTask(JSON.toJSONString(task));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (!BaseConfig.isProduct()) {
                throw new RuntimeException("TaskDBModel.uri是空");
            }
        }
    }

    public void killProcess() {
        if (printBinder == null) {
            return;
        }
        try {
            printBinder.killingProcess();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @WorkerThread
    public void prepareToGetPrintTask(final List<Integer> noList, final String host) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (printBinder == null) {
                    return;
                }
                try {
                    printBinder.optTask(JSON.toJSONString(noList), host);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @WorkerThread
    public void refreshSetting() {
        if (printBinder == null) {
            return;
        }
        try {
            printBinder.refreshSetting();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @WorkerThread
    public void updateTask(final String fsHostId, final int printNo, final String hashMap) {
        if (printBinder == null) {
            return;
        }
        try {
            printBinder.updateTask(fsHostId, printNo, hashMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
