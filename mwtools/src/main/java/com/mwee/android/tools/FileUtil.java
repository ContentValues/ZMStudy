package com.mwee.android.tools;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * FileUtil
 */
@SuppressWarnings("unused")
public class FileUtil {
    public static boolean deleteAllFile(String path) {
        return deleteAllFile(path, true);
    }

    public static boolean deleteAllFile(File file) {
        return deleteAllFile(file, true);
    }

    /**
     * 删除所有文件和目录，如果有子文件和目录，则逐层遍历删除
     *
     * @param path String
     */
    public static boolean deleteAllFile(String path, boolean delFolder) {
        if (!TextUtils.isEmpty(path)) {
            return deleteAllFile(new File(path), delFolder);
        } else {
            return true;
        }
    }

    /**
     * 删除所有文件和目录，如果有子文件和目录，则逐层遍历删除
     *
     * @param file File
     */
    public static boolean deleteAllFile(File file, boolean delFolder) {
        boolean result = true;
        if (file != null) {
            if (file.exists()) {
                if (file.isFile()) {
                    File to = new File(file.getAbsolutePath() + System.currentTimeMillis());
                    file.renameTo(to);
                    result = to.delete();
                } else if (file.isDirectory()) {
                    File[] sonList = file.listFiles();
                    if (sonList != null && sonList.length > 0) {
                        for (File sonPath : sonList) {
                            deleteAllFile(sonPath, delFolder);
                        }
                    }
                    if (delFolder) {
                        File to = new File(file.getAbsolutePath() + System.currentTimeMillis());
                        file.renameTo(to);
                        result = to.delete();
                    }
                }
            }
        }
        return result;
    }

    /**
     * 写文件，默认不追加
     *
     * @param content String｜要写入的内容
     * @param path    String｜完整文件路径
     */
    public synchronized static void writeToFile(Context context, String content, String path) {
        writeToFile(context, content, path, false);
    }

    /**
     * 写文件
     *
     * @param content String｜要写入的内容
     * @param path    String｜完整文件路径
     * @param append  boolean|是否追加
     */
    public synchronized static void writeToFile(Context context, String content, String path, boolean append) {
        if (!FileUtil.makeFolderPathSafe(context, path)) {
            return;
        }
        File file = new File(path);
        if (!file.exists()) {
            try {
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
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append)));
                writer.write(content + "\n");
                writer.flush();
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

    /**
     * 将传入的Map里的数据写入到指定的Properties里
     *
     * @param filePath String｜Property文件路径
     * @param dataMap  Map<String, String>｜需要些的数据
     * @return boolean | true:写入成功；false：写入失败
     */
    public static boolean writeProperties(Context context, String filePath, Map<String, String> dataMap) {
        Properties p = new Properties();
        try {
            File file = makeFileSafe(context, filePath);
            if (file == null) {
                return false;
            }
            InputStream in = new BufferedInputStream(new FileInputStream(file));
            p.load(in);
            OutputStream out = new FileOutputStream(filePath);
            Iterator<Map.Entry<String, String>> ite = dataMap.entrySet().iterator();

            while (ite.hasNext()) {
                Map.Entry<String, String> map = ite.next();
                p.setProperty(map.getKey(), map.getValue());
            }
            p.store(out, "");
            in.close();
            out.close();
            return true;
        } catch (IOException e) {
            LogUtil.logError("", e);
        }
        return false;
    }

    /**
     * 读取Properties，并将读取到的内容写入到Map里
     *
     * @param filePath String｜Property文件路径
     * @return Map<String,String>
     */
    public static Map<String, String> readProperties(String filePath) {
        String value = "";
        Properties p = new Properties();
        Map<String, String> dataMap = new HashMap<>();
        InputStream in = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return new HashMap<>();
            }
            in = new BufferedInputStream(new FileInputStream(filePath));
            p.load(in);
            Iterator<Map.Entry<Object, Object>> ite = p.entrySet().iterator();
            while (ite.hasNext()) {
                Map.Entry<Object, Object> data = ite.next();
                dataMap.put((String) data.getKey(), (String) data.getValue());
            }
        } catch (IOException e) {
            LogUtil.logError("", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dataMap;
    }

    public static void makeFolderPathSafe(Context context, Uri uri) {
        if (uri != null) {
            String localPath = FileUtil.getRealDiskPath(context, uri);
            if (!TextUtils.isEmpty(localPath)) {
                FileUtil.makeFolderPathSafe(context, localPath);
            }
        }
    }

    /**
     * 如果指定文件夹路径不存在，则创建
     *
     * @param filePath String｜路径
     */
    public static boolean makeFolderPathSafe(Context context, String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }
        //判断指定的目录是否有权限
        if (context != null && context.getExternalCacheDir() != null) {
            String external = context.getExternalCacheDir().toString();
            external = external.substring(0, external.lastIndexOf("/"));
            if (!filePath.startsWith(external)) {
                String sdcard = Environment.getExternalStorageDirectory().toString();
                if (TextUtils.isEmpty(sdcard)) {
                    sdcard = "/sdcard/111";
                }

                if (filePath.startsWith(sdcard) && !SdcardUtil.hasExternalStoragePermission(context)) {
                    return false;
                }
            }
        }

        boolean result = false;
        if (filePath.startsWith("file://")) {
            filePath = filePath.replace("file://", "");
        } else if (filePath.startsWith("content:")) {
            filePath = getRealDiskPath(context, Uri.parse(filePath));
        }
        String folderPath = getFolderPath(filePath);
        File file = new File(folderPath);

        if (!file.exists()) {
            try {
                result = file.mkdirs();
                if (!result) {
                    result = file.delete();
                    result = file.mkdirs();
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (!SdcardUtil.isSDCardUsable(context)) {
                    result = false;
                }
            }
            if (!result) {
                Log.e("mkdirs fail", "folderPath=" + folderPath + ";filePath=" + filePath);
            }
            return result;
        } else {
            result = true;
        }

        return result;
    }

    public static String getFolderPath(String filePath) {
        String folderPath = filePath;
        if (!filePath.endsWith("/")) {
            int index = filePath.lastIndexOf("/");
            folderPath = filePath.substring(0, index) + "/";
        }
        return folderPath;
    }

    /**
     * 从指定路径中读取文件内容
     *
     * @param path String | 文件路径
     * @return String | 文件读取结果
     */
    public static String readFile(String path) {
        File file = new File(path);
        String result = null;
        if (file.exists() && file.isFile()) {
            String temp;

            StringBuilder sb;
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(file));
                sb = new StringBuilder();
                temp = br.readLine();
                while (temp != null) {
                    sb.append(temp).append(" ");
                    temp = br.readLine();
                }
                result = sb.toString();
            } catch (IOException e) {
                LogUtil.logError("", e);
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 通过Uri获取本地资源的真实文件路径.
     * 如果Uri指向并非本地地址,则返回null
     *
     * @param fileUrl Uri
     * @return String
     */
    public static String getRealDiskPath(Context context, Uri fileUrl) {
        String fileName = "";
        if (fileUrl != null) {
            if (fileUrl.toString().startsWith("content:") && context != null) {
                //content://开头的uri
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor cursor = context.getContentResolver().query(fileUrl, proj, null, null, null);
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA);
                            fileName = cursor.getString(column_index);
                        }
                    } catch (Exception e) {
                        LogUtil.logError("", e);
                    } finally {
                        cursor.close();
                    }
                }
            } else if (fileUrl.toString().startsWith("file:")) {//file:///开头的uri
                fileName = fileUrl.toString().replace("file://", "");
            } else {
                fileName = fileUrl.toString();
            }
        }
        if (TextUtils.isEmpty(fileName)) {
            if (fileUrl != null) {
                fileName = fileUrl.toString();
            } else {
                fileName = "";
            }
        }
        return fileName;
    }

    public static File makeFileSafe(Context context, String path) {
        if (!FileUtil.makeFolderPathSafe(context, path)) {
            return null;
        }
        File file = new File(path);
        if (!file.exists()) {
            try {
                boolean result = file.createNewFile();
                if (!result) {
                    file = null;
                    LogUtil.logError("createNewFile fail,filePath=" + path);
                }
            } catch (IOException e) {
                file = null;
                LogUtil.logError("createNewFile fail,filePath=" + path, e);
            }
        }
        return file;
    }

    /**
     * 复制文件
     *
     * @param filename 文件名
     * @param bytes    数据
     */
    public static void copy(String filename, byte[] bytes) {
        try {
            //如果手机已插入sd卡,且app具有读写sd卡的权限
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                FileOutputStream output = new FileOutputStream(filename);
                output.write(bytes);
                output.flush();
                output.close();
            } else {
                //Log.i(TAG, "copy:fail, " + filename);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 复制文件
     *
     * @param file  文件名
     * @param bytes 数据
     */
    public static void copy(File file, byte[] bytes) {
        try {
            //如果手机已插入sd卡,且app具有读写sd卡的权限
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                FileOutputStream output = new FileOutputStream(file);
                output.write(bytes);
                output.flush();
                output.close();
            } else {
                //Log.i(TAG, "copy:fail, " + filename);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
       /* File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile();//注意小米手机必须这样获得public绝对路径
        String fileName = ”新建文件夹”;
        File appDir = new File(file ,fileName);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }*/
        String fileName = "hello";
        File appDir = new File(context.getCacheDir() ,fileName);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        File currentFile = new File(appDir, System.currentTimeMillis() + ".png");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(currentFile);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(new File(currentFile.getPath()))));
    }
}
