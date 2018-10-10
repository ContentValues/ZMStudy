package xdroid.mwee.com.mwdriverbus;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import xdroid.mwee.com.mwdriverbus.exception.DriverBusException;
import xdroid.mwee.com.mwdriverbus.exception.DriverException;
import xdroid.mwee.com.mwdriverbus.exception.IncorrectDriverException;
import xdroid.mwee.com.mwdriverbus.exception.NoDriverException;

/**
 * Created by zhangmin on 2018/7/16.
 */

public class DrivenBusManager implements IDriveProcessor {
    private static final DrivenBusManager instance = new DrivenBusManager();
    private HashMap<String, IDriver> driverMap = new HashMap();
    private HashMap<String, HashMap<String, Method>> driverMethod = new HashMap();
    private String mPackageName;
    private IDriveProcessor multiProcessor;
    private String currentProcessName = "";
    private String currentReceiverName = "";
    private Context context = null;
    //private ArrayMap<String, DriverWorkerService> map = null;

    private DrivenBusManager() {
    }

    public static DrivenBusManager getInstance() {
        return instance;
    }

    public <T> T callInProcess(String processName, String uri, Object... params) throws DriverBusException {
        if(this.multiProcessor == null) {
            Log.e("Driver", "You shall call DriverBus.prepareMultiProcess(Context) first");
            return null;
        } else {
            return this.multiProcessor.call(uri, params);
        }
    }

    public <T> T callInProcess(String processName, String uri) throws DriverBusException {
        if(this.multiProcessor == null) {
            Log.e("Driver", "You shall call DriverBus.prepareMultiProcess(Context) first");
            return null;
        } else {
            return this.multiProcessor.call(uri, new Object[0]);
        }
    }

    public void broadcastInProcess(String processName, String method, Object... params) throws DriverBusException {
        if(this.multiProcessor == null) {
            Log.e("Driver", "You shall call DriverBus.prepareMultiProcess(Context) first");
        }

        this.multiProcessor.broadcast(method, params);
    }

    public void broadcast(String method, Object... params) {
        if(this.driverMap != null && !this.driverMap.isEmpty()) {
            Set<String> set1 = this.driverMap.keySet();
            Set<String> set2 = new HashSet();
            set2.addAll(set1);
            Iterator var5 = set2.iterator();

            while(var5.hasNext()) {
                String moduleName = (String)var5.next();
                IDriver driver = (IDriver)this.driverMap.get(moduleName);
                if(driver != null) {
                    this.beCalled(moduleName + "/" + method, false, params);
                }
            }
        }

    }

    public <T> T call(String uri, Object... params) throws DriverBusException {
        this.checkBeforeCall(uri);
        return this.beCalled(uri, params);
    }

    public void registerDriver(IDriver driver) {
        String result = this.verifyDriver(driver);
        if(TextUtils.isEmpty(result)) {
            this.driverMap.put(driver.getModuleName(), driver);
            this.driverMethod.put(driver.getModuleName(), DriverUtil.indexDriver(driver));
        } else {
            Log.e("DrivenBusManager", "registerDriver fail:" + result);
        }

    }

    public void unRegisterDriver(IDriver driver) {
        String result = this.verifyDriver(driver);
        if(TextUtils.isEmpty(result)) {
            IDriver cacheDriver = (IDriver)this.driverMap.get(driver.getModuleName());
            if(cacheDriver == driver) {
                this.driverMap.remove(driver.getModuleName());
                this.driverMethod.remove(driver.getModuleName());
            }
        } else {
            Log.e("DrivenBusManager", "unregisterDriver fail:" + result);
        }

    }

    public void init(String[] pathList) throws DriverBusException {
        if(pathList != null && pathList.length > 0) {
            List<String> nameList = new ArrayList();
            Collections.addAll(nameList, pathList);
            this.loadDrivers(nameList);
        } else {
            throw new DriverException("Empty pathList");
        }
    }

    private <T> T beCalled(String uri, Object... paramList) {
        return this.beCalled(uri, true, paramList);
    }

    private <T> T beCalled(final String uri, boolean throwException, final Object... paramList) {
        String moduleName = this.getModuleNameByUri(uri);
        final IDriver driver = (IDriver)this.driverMap.get(moduleName);
        if(driver == null) {
            if(throwException) {
                if(DriverCalled.errorWithException) {
                    throw new NoDriverException(moduleName + " does not exist");
                }

                (new NoDriverException(moduleName + " does not exist")).printStackTrace();
            }
        } else {
            HashMap<String, Method> methodMap = (HashMap)this.driverMethod.get(moduleName);
            if(methodMap != null) {
                final Method method = (Method)methodMap.get(uri);
                if(method != null) {
                    if(this.needUiThread(method)) {
                        (new Handler(Looper.getMainLooper())).post(new Runnable() {
                            public void run() {
                                DriverCalled.beCalled(driver, method, uri, paramList);
                            }
                        });
                        return null;
                    }

                    return DriverCalled.beCalled(driver, method, uri, paramList);
                }

                if(throwException) {
                    if(DriverCalled.errorWithException) {
                        throw new NoDriverException(uri + " does not exist");
                    }

                    (new NoDriverException(uri + " does not exist")).printStackTrace();
                }
            }
        }

        return null;
    }

    private boolean needUiThread(Method method) {
        if(method == null) {
            return false;
        } else {
            if(Looper.getMainLooper() != Looper.myLooper()) {
                DriverMethod drivenMethod = (DriverMethod)method.getAnnotation(DriverMethod.class);
                if(drivenMethod.UIThread()) {
                    return true;
                }
            }

            return false;
        }
    }

    private void loadDrivers(List<String> pathList) {
        Iterator var2 = pathList.iterator();

        while(var2.hasNext()) {
            String temp = (String)var2.next();
            if(!TextUtils.isEmpty(temp)) {
                try {
                    Object clzObject = Class.forName(temp).newInstance();
                    if(clzObject instanceof IDriver) {
                        this.registerDriver((IDriver)clzObject);
                    }
                } catch (InstantiationException var5) {
                    var5.printStackTrace();
                } catch (IllegalAccessException var6) {
                    var6.printStackTrace();
                } catch (ClassNotFoundException var7) {
                    var7.printStackTrace();
                }
            }
        }

    }

    private void checkBeforeCall(String uri) throws DriverBusException {
        String moduleName = this.getModuleNameByUri(uri);
        if(TextUtils.isEmpty(moduleName)) {
            throw new IncorrectDriverException("Empty moduleName");
        }
    }

    private String getModuleNameByUri(String uri) {
        if(TextUtils.isEmpty(uri)) {
            return "";
        } else {
            String moduleName = "";
            String[] temp = uri.split("/");
            if(temp.length > 1) {
                moduleName = temp[0];
            }

            return moduleName;
        }
    }

    private String verifyDriver(IDriver driver) {
        if(driver == null) {
            return "";
        } else {
            String errorInfo = "";
            if(TextUtils.isEmpty(driver.getModuleName())) {
                errorInfo = "driver.getModuleName() returns empty";
            }

            return errorInfo;
        }
    }

    public void registerProcess(Context context, String currentProcessName, String currentReceiverName) {
        this.context = context;
        this.currentProcessName = currentProcessName;
        this.currentReceiverName = currentReceiverName;
    }

  /*  private void checkProcess(String process) {
        String className = DriverServiceUtil.getServiceByProcessName(this.context, process);
        this.connectProcess(this.context, className);
    }*/

    private void callProcess(String uri, Object... param) {
        Message mes = Message.obtain();
        mes.obj = null;
        mes.setData((Bundle)null);
        Bundle bundle = new Bundle();
        bundle.putString("uri", uri);

        for(int i = 0; i < param.length; ++i) {
            Object temp = param[i];
            if(temp instanceof String) {
                bundle.putString(String.valueOf(i), (String)temp);
            }
        }

    }

    private void connectProcess(Context context, String className) {
        try {
            Intent intent = new Intent();
            intent.setClassName(context, className);
            intent.setPackage(context.getPackageName());
            context.bindService(intent, new ServiceConnection() {
                public void onServiceConnected(ComponentName name, IBinder service) {
                    new Messenger(service);
                }

                public void onServiceDisconnected(ComponentName name) {
                }
            }, Context.BIND_AUTO_CREATE);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }
}
