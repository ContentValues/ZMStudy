package com.mwee.android.tools;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.mwee.android.tools.exception.BizException;
import com.mwee.android.tools.exception.BladeCatch;
import com.mwee.android.tools.exception.BladeUtil;
import com.mwee.android.tools.log.LogBizConfigBuilder;
import com.mwee.android.tools.log.LogUpload;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 *
 */
public class LogUtil {

    /**
     * 是否输出log
     */
    public static boolean SHOW = true;
    //	public static final Boolean FLAG = false;
    public static String LOG_FOLDER;

    public static void logWeb(String msg) {
        writeLog(LogType.WEB, msg);
    }

    public static Context context;
    protected static String LOG_BUSINESS_PATH;
    protected static String LOG_NET_PATH;
    private static boolean writeLog = false;
    private static LinkedBlockingQueue<String[]> logQueue=new LinkedBlockingQueue<String[]>(1000);
    public static void init(Context context, boolean printLog) {
        LogUtil.context = context;
        SHOW = printLog;
        if (SdcardUtil.isSDCARDReadyReadWrite()) {
            LOG_FOLDER = android.os.Environment.getExternalStorageDirectory().getPath() + "/Android/Data/" + context.getPackageName() + "" + "/Log";
        } else {
            LOG_FOLDER = context.getFilesDir() + "/Log";
        }
        LOG_BUSINESS_PATH = LOG_FOLDER + "/BUSINESS";
        LOG_NET_PATH = LOG_FOLDER + "/NET";
        LogUpload.FROM_ZIP = LOG_BUSINESS_PATH;
        LogUpload.FROM_ZIP2 = LOG_NET_PATH;
        LogUpload.PATH_ZIP = LOG_FOLDER + "/temp";
        LogUpload.SERVER_SAVED_NAME = "Bbox";
        try {
            LogUpload.APP_VERSION = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(LogUpload.BOOT_TIME)) {
            LogUpload.BOOT_TIME = DateUtil.getCurrentTime();
        }
        final Thread logThread = new Thread("LogFileThread") {
            @Override
            public void run() {
                super.run();
                try {
                    while (true) {
                        if (logQueue == null) {
                            LogUtil.log("logQueue 为null，睡眠10秒");
                            Thread.sleep(10 * 1000);
                            continue;
                        }
                        String[] info = logQueue.take();
                        if (info != null && info.length >= 2) {
                            String content = info[0];
                            String fileName = info[1];
                            String finalPath = "";
                            if (fileName.startsWith("/")) {
                                finalPath = fileName;
                            } else {
                                finalPath = LOG_FOLDER + "/" + fileName;
                            }
                            writeToFile(LogUtil.context, content + "\n====================\n", finalPath);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        logThread.start();
    }

    public static void setWriteLog(boolean isWriteLog) {
        writeLog = isWriteLog;
    }

    public static void setCatchException(boolean b) {
        if (b) {
            BladeCatch.init(LogUtil.context);
        } else {
            BladeCatch.close(LogUtil.context);
        }
    }

    public static void updateUploadConfig(LogBizConfigBuilder builder) {
        if (builder == null) {
            return;
        }
        if (builder.FROM_ZIP != null) {
            LOG_BUSINESS_PATH = builder.FROM_ZIP;
        }
        LogUpload.updateUploadConfig(builder);
    }

    /**
     * 普通日志
     *
     * @param msg String
     */
    public static void log(String msg) {
        writeLog(LogType.MSG, msg);
    }

    public static void log(String msg, Exception e) {
        if (SHOW) {
            writeLog(LogType.MSG, msg + " \n" + StringUtil.getExceptionInfo(e));
        }
    }

    public static void log(String tag, String msg) {
        if (SHOW) {
            writeLog(LogType.MSG, tag + "==" + msg);
        }
    }

    /**
     * 需要处理的异常信息
     *
     * @param msg String
     */
    public static void logError(String msg) {
        logError("", new BizException(msg));
    }

    public static void logError(Throwable e) {
        logError("", e);
    }

    public static void logError(String msg, Throwable throwable) {
        if (msg == null) {
            msg = "";
        }
        try {
            if (!TextUtils.isEmpty(msg)) {
                CrashReport.putUserData(context, "BizData", msg);
            }
            CrashReport.postCatchedException(throwable);
        } catch (Throwable e) {
        }

        writeLog(LogType.ERROR, msg + " \n" + StringUtil.getExceptionInfo(throwable));
    }

    /**
     * 业务日志，需要上送
     *
     * @param msg String
     */
    public static void logBusiness(String msg) {
        writeLog(LogType.BUSINESS, msg);
    }

    public static void logBusiness(String tag, String msg) {
        writeLog(LogType.BUSINESS, tag + "\n" + msg);
    }

    public static void logNET(String msg) {
        writeLog(LogType.NET, msg);
    }

    public static void logNET(String tag, String msg) {
        writeLog(LogType.NET, "key=[" + tag + "]: \n" + msg);
    }

    private static void writeLog(LogType type, String msg) {
        if (msg == null) {
            msg = "";
        }
        if (type != null) {
            switch (type) {
                case ERROR: {
                    String tag = getLogTag() + "-ERROR ";
                    if (SHOW) {

                        Log.e(tag, msg);
                    }
                    writeErrorLogtoFile(tag + " \n " + msg);
                }
                break;
                case BUSINESS: {
                    String tag = getLogTag() + "-BUSINESS ";

                    if (SHOW) {
                        Log.i(tag, msg);
                    }
                    writeBusinessLogtoFile(tag + "\n  " + msg);
                }
                break;
                case NET:
                    if (SHOW) {
                        String tag = getLogTag() + "-NET ";
                        show(tag, msg);
                        writeNetLogtoFile(tag + "\n  " + msg);
                    }
                    break;
                case MSG:
                    if (SHOW) {
                        Log.v(getLogTag(), msg);
                    }
                    break;
                case WEB:
                    if (SHOW) {
                        Log.d(getLogTag() + "-WEBLog", msg);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public static void show(String tag, String str) {
        str = str.trim();
        int index = 0;
        int maxLength = 4000;
        String sub;
        while (index < str.length()) {
            // java的字符不允许指定超过总的长度end
            if (str.length() <= index + maxLength) {
                sub = str.substring(index);
            } else {
                sub = str.substring(index, index + maxLength);
            }

            index += maxLength;
            Log.i(tag, sub);
        }
    }

    private static String getLogTag() {
        return DateUtil.getCurrentDateTime("yyyy-MM-dd_HH:mm:ss") + "==";
    }

    /**
     * 写ErrorLog
     *
     * @param content String
     */
    private static void writeErrorLogtoFile(String content) {
        String filename = LOG_BUSINESS_PATH + "/ERROR/" + DateUtil.getCurrentDateTime("yyyy-MM-dd_HH") + ".txt";
        if (writeLog) {
            writeLogtoFile(content, filename);
            LogUpload.callUpload();
        }
    }

    /**
     * 写BusinessLog
     *
     * @param content String
     */
    private static void writeBusinessLogtoFile(String content) {
        String filename = LOG_BUSINESS_PATH + "/Biz/" + DateUtil.getCurrentDateTime("yyyy-MM-dd_HH") + ".txt";
        if (writeLog) {
            writeLogtoFile(content, filename);
            LogUpload.callUpload();
        }
    }

    private static void writeNetLogtoFile(String content) {
        String filename = "NET/" + DateUtil.getCurrentDateTime("yyyy-MM-dd_HH") + ".txt";
        writeLogtoFile(content, filename);
    }

    /**
     * 写Log到文件
     *
     * @param content  String
     * @param fileName String
     */
    public static void writeLogtoFile(final String content, final String fileName) {
        if(logQueue==null){
            LogUtil.log("logQueue 为null");
            return;
        }
        //如果队列的长度超过了999，则抛弃
        if(logQueue.size()>999){
            LogUtil.log("logQueue.size()是"+logQueue.size());
            return;
        }
        String[] info=new String[2];
        info[0]=(content);
        info[1]=(fileName);

        try {
            logQueue.add(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static void writeToFile(Context context, String content, String path) {
        if (!FileUtil.makeFolderPathSafe(context, path)) {
            return;
        }
        File file = new File(path);
        boolean newFile = false;
        if (!file.exists()) {
            try {
                newFile = true;
                boolean result = file.createNewFile();
                if (!result) {
                    result = file.createNewFile();
                }
            } catch (IOException e) {
                if (LogUtil.SHOW) {
                    LogUtil.log(StringUtil.getExceptionInfo(e));
                }
            }
        }

        if (file.exists()) {
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
                if (newFile) {
                    String info = BladeUtil.buildHardwareInfo(context);
                    writer.write(info);
                }
                writer.write(content + "\n");
            } catch (IOException e) {
                if (LogUtil.SHOW) {
                    LogUtil.log(StringUtil.getExceptionInfo(e));
                }
            } finally {

                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        if (LogUtil.SHOW) {
                            LogUtil.log(StringUtil.getExceptionInfo(e));
                        }
                    }
                }
            }
        }
    }

    private enum LogType {
        ERROR,
        MSG,
        BUSINESS,
        NET,
        WEB
    }

    public static void upload() {
        LogUpload.callUpload();
    }
}
