package xdroid.mwee.com.posdinnerprinter.conn;

import android.os.RemoteException;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.mwee.android.tools.LogUtil;

import xdroid.mwee.com.posdinnerprinter.IDinnerPrintInterface;
import xdroid.mwee.com.posdinnerprinter.device.DeviceManager;
import xdroid.mwee.com.posdinnerprinter.queen.TaskDBUtil;

/**
 * Created by zhangmin on 2018/7/9.
 */

public class PrintStub extends IDinnerPrintInterface.Stub {


    @Override
    public void init() throws RemoteException {
        LogUtil.log("printer", "PrintStub init()");
        DeviceManager.getInstance().rebuildAllPrinter();
    }


    @Override
    public void cleanOrder() throws RemoteException {
        LogUtil.log("printer", "PrintStub cleanOrder()");
    }

    @Override
    public void processTask(String task) throws RemoteException {
        //LogUtil.log("printer", "PrintStub processTask()"+ JSON.toJSONString(task));
        TaskDBUtil.receiveNewTask(task);
    }

    @Override
    public void printSyncTask(String task) throws RemoteException {
        LogUtil.log("printer", "PrintStub printSyncTask()"+ JSON.toJSONString(task));

    }

    @Override
    public void optTask(String nolist, String hostID) throws RemoteException {
        LogUtil.log("printer", "PrintStub optTask()");
    }


    @Override
    public void refreshSetting() throws RemoteException {
        LogUtil.log("printer", "PrintStub refreshSetting()");
    }

    @Override
    public void killingProcess() throws RemoteException {
        LogUtil.log("printer", "PrintStub killingProcess()");
    }


    @Override
    public void updateTask(String fsHostId, int printNo, String hashMap) throws RemoteException {
        LogUtil.log("printer", "PrintStub updateTask()");
    }
}
