package xdroid.mwee.com.mwdriverbus;

import xdroid.mwee.com.mwdriverbus.exception.DriverBusException;

/**
 * Created by zhangmin on 2018/7/16.
 */

public class DriverBus {
    public DriverBus() {
    }

    public static void registerDriver(IDriver driver) {
        DrivenBusManager.getInstance().registerDriver(driver);
    }

    public static void unRegisterDriver(IDriver driver) {
        DrivenBusManager.getInstance().unRegisterDriver(driver);
    }

   /* public static void prepareMultiProcess(final Context context) {
        (new Thread(new Runnable() {
            public void run() {
                try {
                    String className = DriverServiceUtil.getService(context);
                    Intent intent = new Intent();
                    intent.setClassName(context, className);
                    intent.setPackage(context.getPackageName());
                    context.startService(intent);
                } catch (Exception var3) {
                    var3.printStackTrace();
                }

            }
        })).start();
    }*/

    public static void init(String[] pathList) throws DriverBusException {
        DrivenBusManager.getInstance().init(pathList);
    }

    public static <T> T call(String uri, Object... params) throws DriverBusException {
        return DrivenBusManager.getInstance().call(uri, params);
    }

    public static <T> T call(String uri) throws DriverBusException {
        return DrivenBusManager.getInstance().call(uri, new Object[0]);
    }

    public static void broadcast(String method, Object... params) throws DriverBusException {
        DrivenBusManager.getInstance().broadcast(method, params);
    }

    public static <T> T callInProcess(String processName, String uri, Object... params) throws DriverBusException {
        return DrivenBusManager.getInstance().callInProcess(processName, uri, params);
    }

    public static <T> T callInProcess(String processName, String uri) throws DriverBusException {
        return DrivenBusManager.getInstance().callInProcess(processName, uri);
    }

    public static void broadcastInProcess(String processName, String method, Object... params) throws DriverBusException {
        DrivenBusManager.getInstance().broadcastInProcess(processName, method, params);
    }

    public static void setErrorWithException(boolean errorWithException) {
        DriverCalled.errorWithException = errorWithException;
    }
}
