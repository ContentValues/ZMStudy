package xdroid.mwee.com.posdinnerprinter.conn;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.mwee.android.tools.LogUtil;
import xdroid.mwee.com.mwdriverbus.DriverBus;
import xdroid.mwee.com.mwdriverbus.exception.DriverException;
import xdroid.mwee.com.posdinnerprinter.R;

/**
 * Created by zhangmin on 2018/7/9.
 */

public class PosPrintService extends Service {

    private PrintStub stub;



    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onCreate()");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DriverBus.init(getResources().getStringArray(R.array.print_drive_path));
                } catch (DriverException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        stub = new PrintStub();
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onBind(Intent intent)--->"+intent);
        if (stub == null) {
            return null;
        }
        try {
            stub.init();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return stub.asBinder();
    }


    @Override
    public boolean onUnbind(Intent intent) {
        LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onDestroy()");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

}
