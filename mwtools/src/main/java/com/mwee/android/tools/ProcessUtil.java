package com.mwee.android.tools;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.mwee.android.tools.process.ProcessManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 进程相关的Util
 */
public class ProcessUtil {

    /**
     * 当前进程是否是应用的主进程.
     *
     * @param context Context
     * @return boolean | true:是主进程;false:不是主进程(例如:推送进程)
     */
    public static boolean isMainProcess(Context context) {
        if (context == null) {
            return false;
        }
        final String packageName = context.getPackageName();
        return TextUtils.equals(packageName, getCurrentProcessName(context));
    }

    /**
     * 获取当前进程的包名
     *
     * @param context Context
     * @return String | example:com.google.youtube
     */
    public static String getCurrentProcessName(Context context) {
        if(context==null){
            return "";
        }
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> proList = am.getRunningAppProcesses();
        if (proList == null) {
            return "";
        }
        int currentPID = android.os.Process.myPid();
        String currentProcess = "";
        for (ActivityManager.RunningAppProcessInfo temp : proList) {
            if (temp.pid == currentPID) {
                currentProcess = temp.processName;
                break;
            }
        }
        return currentProcess;
    }

    /**
     * 遍历当前类以及父类去查找方法，例子，写的比较简单
     *
     * @param object     Object
     * @param methodName String
     * @param params     Object[]
     * @param paramTypes Class[]
     * @return Object
     */
    @SuppressWarnings("unchecked")
    public static Object invokeMethod(Object object, String methodName, Object[] params, Class[] paramTypes) {
        Object returnObj = null;
        if (object == null) {
            return null;
        }
        Class cls = object.getClass();
        Method method = null;
        for (; cls != Object.class; cls = cls.getSuperclass()) { //因为取的是父类的默认修饰符的方法，所以需要循环找到该方法
            try {
                method = cls.getDeclaredMethod(methodName, paramTypes);
                break;
            } catch (NoSuchMethodException e) {
//					e.printStackTrace();
            } catch (SecurityException e) {
//					e.printStackTrace();
            }
        }
        if (method != null) {
            method.setAccessible(true);
            try {
                returnObj = method.invoke(object, params);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return returnObj;
    }

    /**
     * 判断app当前是否在前台
     *
     * @return boolean | true:在前台;false:在后台
     */
    public static boolean isAppOnForeground(Context context) {
        if (DeviceUtil.isScreenLocked(context)) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return ProcessManager.isMyProcessInTheForeground();
        } else {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
            if (tasksInfo.size() > 0) {
                //应用程序位于堆栈的顶层
                if (TextUtils.equals(context.getPackageName(), tasksInfo.get(0).topActivity.getPackageName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
