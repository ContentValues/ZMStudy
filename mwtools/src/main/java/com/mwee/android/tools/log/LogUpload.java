package com.mwee.android.tools.log;

import android.os.Build;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;

import com.mwee.android.tools.FileUtil;
import com.mwee.android.tools.LogUtil;
import com.mwee.android.tools.ProcessUtil;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * LogUpload
 * Created by virgil on 16/7/14.
 */
public class LogUpload {


    public static String PATH_ZIP = "";
    public static String FROM_ZIP = "";
    public static String FROM_ZIP2 = "";
    public static String UPLOAD_URL = LogBizConfigBuilder.URL_LOG_SERVER_PRODUCT;
    public static String SERVER_SAVED_NAME = "";
    public static String SESSION = "";
    public static String DeviceID = "";
    public static String APP_VERSION = "";
    public static String BOOT_TIME = "";
    private static SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss", Locale.SIMPLIFIED_CHINESE);
    private static int idx;
    private static boolean autoUpload = false;
    private static String shopID = "";
    private static String appID = "";
    private static long lastUploadTime = 0;
    private static long INERVAL = 5 * 10 * 1000;

    public static void updateUploadConfig(LogBizConfigBuilder builder) {
        if (builder == null) {
            return;
        }
        if (builder.shopID != null) {
            shopID = builder.shopID;
        }
        if (builder.appID != null) {
            appID = builder.appID;
        }
        if (builder.APP_VERSION != null) {
            APP_VERSION = builder.APP_VERSION;
        }
        if (builder.APP_VERSION != null) {
            APP_VERSION = builder.APP_VERSION;
        }
        if (builder.PATH_ZIP != null) {
            PATH_ZIP = builder.PATH_ZIP;
        }
        if (builder.FROM_ZIP != null) {
            FROM_ZIP = builder.FROM_ZIP;
        }
        if (builder.SERVER_SAVED_NAME != null) {
            SERVER_SAVED_NAME = builder.SERVER_SAVED_NAME;
        }
        UPLOAD_URL = builder.isTest ? LogBizConfigBuilder.URL_LOG_SERVER_TEST : LogBizConfigBuilder.URL_LOG_SERVER_PRODUCT;

        if (!TextUtils.isEmpty(builder.UPLOAD_URL)) {
            UPLOAD_URL = builder.UPLOAD_URL;
        }
        if (!TextUtils.isEmpty(builder.SESSION)) {
            SESSION = builder.SESSION;
            updateSessoin(SESSION);
        }
        if (builder.DeviceID != null) {
            DeviceID = builder.DeviceID;
        }
        if (builder.uploadIntervalMinute > 0) {
            INERVAL = builder.uploadIntervalMinute * 60 * 1000;
        }
        autoUpload = builder.upload;
    }

    public static void setTest(boolean test) {
        UPLOAD_URL = test ? LogBizConfigBuilder.URL_LOG_SERVER_TEST : LogBizConfigBuilder.URL_LOG_SERVER_PRODUCT;
    }

    public static void updateSessoin(String session) {
        if (!TextUtils.isEmpty(session)) {
            SESSION = session;
            SharedPreferencesUtil.saveString(LogUtil.context, "sid", session);
        }
    }

    public static String getTrace(StackTraceElement[] stack, int depth) {
        if (depth < 1) {
            depth = 1;
        }
        depth += 3;
        if (depth > stack.length) {
            depth = stack.length;
        }
        String trace = "";

        for (int i = 3; i < depth; i++) {
            trace += "\n" + stack[i].getClassName() + ", "
                    + stack[i].getMethodName() + "(), "
                    + stack[i].getLineNumber() + ", "
                    + stack[i].getFileName();
        }

        return trace;
    }

    public static void callUpload() {
        if (!autoUpload) {
            return;
        }
        boolean isOk = false;
        synchronized (LogUpload.class) {
            if (lastUploadTime == 0 || ((SystemClock.elapsedRealtime() - lastUploadTime) > INERVAL)) {
                lastUploadTime = SystemClock.elapsedRealtime();
                isOk = true;
            }
        }
        if (isOk) {
            manualUpload();
        }

    }

    public static void manualUpload() {
        if (!autoUpload) {
            return;
        }
        zipLogFile();
        lastUploadTime = SystemClock.elapsedRealtime();
    }

    private static String logSeparate() {
        return String.format(Locale.SIMPLIFIED_CHINESE, "\n\n[%d]_%s ", idx++, sdfTime.format(new Date()));
    }

    public static void zipLogFile() {
        if (!ProcessUtil.isMainProcess(LogUtil.context)) {
            return;
        }
        if (TextUtils.isEmpty(SESSION)) {
            SESSION = SharedPreferencesUtil.readStrng(LogUtil.context, "sid");
        }
        if (TextUtils.isEmpty(shopID)) {
            shopID = SharedPreferencesUtil.readStrng(LogUtil.context, "bsid");
            if (TextUtils.isEmpty(shopID)) {
                shopID = FileUtil.readFile(LogUtil.context.getCacheDir() + "/log/sid");
                if (!TextUtils.isEmpty(shopID)) {
                    if (shopID.contains(" ")) {
                        shopID = shopID.split(" ")[0];
                    }
                }
            }
        }
        if (TextUtils.isEmpty(shopID)) {
            return;
        }
        if (TextUtils.isEmpty(SESSION)) {
            LogUtil.log("cannot upload log:No Session");
            return;
        }
        if (Looper.getMainLooper() == Looper.myLooper()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    zipLogFile();
                }
            }).start();
            return;
        }
        LogUtil.log("UploadLog", System.currentTimeMillis() + "");
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.getDefault());
        String time = formatter.format(new Date());
        String fileName = time + ".zip";
        try {
            if (!hasLogs(FROM_ZIP) && !hasLogs(FROM_ZIP2)) {
                return;
            }
            Zip.ZipFolder(FROM_ZIP, PATH_ZIP + "/" + fileName);
            deleteAllLo(new File(FROM_ZIP));
            uploadZipLog(PATH_ZIP);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static boolean hasLogs(String path) {
        boolean has = false;
        if (!TextUtils.isEmpty(path)) {
            File file = new File(path);
            if (file.exists()) {
                if (file.isFile()) {
                    has = true;
                } else {
                    for (File temp : file.listFiles()) {
                        if (hasLogs(temp.getAbsolutePath())) {
                            has = true;
                            break;
                        }
                    }
                }
            }
        }
        return has;
    }

    private static boolean deleteAllLo(File dir) {

        if (dir.isDirectory()) {
            for (File temp : dir.listFiles()) {
                deleteAllLo(temp);
            }
        }
        // android 删除文件 再马上创建文件的busy问题
        // 参考资料：http://stackoverflow.com/questions/11539657/open-failed-ebusy-device-or-resource-busy
        final File to = new File(dir.getAbsolutePath() + System.currentTimeMillis());
        dir.renameTo(to);
        return to.delete();
    }

    private static void uploadZipLog(final String path) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File dirFile = new File(path);
                File[] list = dirFile.listFiles();
                if (list.length > 50) {
                    deleteAllLo(dirFile);
                    return;
                }
                HashMap<String, String> params = new HashMap<String, String>();

                params.put("SessionID", SESSION);
                params.put("appID", appID);
                params.put("shopId", shopID);
                params.put("Model", android.os.Build.MODEL);
                params.put("SDKVer", Build.VERSION.SDK_INT + "");
                params.put("APPVer", APP_VERSION);
                params.put("DeviceID", DeviceID);

                for (final File file : list) {
                    if (file.isDirectory()) {
                        continue;
                    }
                    if (!file.getName().endsWith(".zip")) {
                        continue;
                    }

                    try {
                        long size = file.length() / 1024;
                        if (size > 2048) {
                            LogUtil.log("日志zip文件大小为" + size + "KB,直接删除");
                            file.delete();
                            continue;
                        }
                        String result = uploadFile(UPLOAD_URL, params, file, SERVER_SAVED_NAME);
                        LogUtil.log(UPLOAD_URL, result);
                        JSONObject jsonObject = new JSONObject(result);
                        if (jsonObject.optInt("errno") == 0) {
                            file.delete();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } catch (Error e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    /**
     * 上传文件
     *
     * @param actionUrl actionUrl
     * @param params    Map<String, String>
     * @param file      File
     * @param name      String
     * @throws IOException
     */
    public static String uploadFile(String actionUrl, Map<String, String> params, File file, String name) throws IOException {

        LogUtil.log("start upload");
        String BOUNDARY = java.util.UUID.randomUUID().toString();
        String PREFIX = "--";
        String LINEND = "\r\n";
        String MULTIPART_FROM_DATA = "multipart/form-data";
        String CHARSET = "UTF-8";

        URL url = new URL(actionUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(30 * 1000);

        conn.setDoInput(true);// 允许输入
        conn.setDoOutput(true);// 允许输出
        conn.setUseCaches(false);
        conn.setRequestMethod("POST"); // Post方式
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);

        // 首先组拼文本类型的参数
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(PREFIX);
            sb.append(BOUNDARY);
            sb.append(LINEND);
            sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
            sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
            sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
            sb.append(LINEND);
            sb.append(entry.getValue());
            sb.append(LINEND);
        }

        DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
        outStream.write(sb.toString().getBytes());

        // 发送文件数据
        if (null != file) {
            StringBuilder sb1 = new StringBuilder();
            sb1.append(PREFIX);
            sb1.append(BOUNDARY);
            sb1.append(LINEND);
            sb1.append("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + file.getName() + "\"" + LINEND);
            sb1.append("Content-Type: application/octet-stream; charset=" + CHARSET + LINEND);
            sb1.append(LINEND);
            outStream.write(sb1.toString().getBytes());
            InputStream is = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }

            is.close();
            outStream.write(LINEND.getBytes());
        }

        // 请求结束标志
        byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
        outStream.write(end_data);
        outStream.flush();
        outStream.close();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "gbk"));
        String line;
        StringBuffer stringBuffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            stringBuffer.append(line);
        }
        reader.close();
        conn.disconnect();
        return stringBuffer.toString();
    }

    public static void setShopID(String shopID) {
        LogUpload.shopID = shopID;
        SharedPreferencesUtil.saveString(LogUtil.context, "bsid", shopID);
        FileUtil.writeToFile(LogUtil.context, shopID, LogUtil.context.getCacheDir() + "/log/sid", false);
    }
}
