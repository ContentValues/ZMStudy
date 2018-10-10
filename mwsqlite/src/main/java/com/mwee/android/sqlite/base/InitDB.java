package com.mwee.android.sqlite.base;

import android.content.Context;
import android.text.TextUtils;

import com.mwee.android.tools.FileUtil;
import com.mwee.android.tools.LogUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

class InitDB {
    public static boolean init(Context context, int resID, String dbName) {
        boolean result = true;
        if (resID == 0) {
            DBManager.getInstance(dbName).initHelper();
        } else {
            if (context != null) {
                String dbPath = context.getDatabasePath(dbName).getAbsolutePath();
                if (!verifyLocalDB(dbPath, dbName)) {
                    LogUtil.logBusiness("InitDB current db status is invalid,need recopy");

                    result = copyWithEnhanceTimes(context, resID, dbName, dbPath, 4);
                } else {
                    LogUtil.logBusiness("InitDB current db is ok");
                    result = true;
                }
            } else {
                result = false;
                LogUtil.logError("InitDB context is null");
            }
        }
        return result;
    }

    private static boolean copyDB(Context context, int resID, String destPath) {
        boolean result = false;
        if (context != null) {
            FileOutputStream outputStream = null;
            try {
                File targetDB = new File(destPath);
                if (targetDB.exists()) {
                    targetDB.delete();
                }
                FileUtil.makeFileSafe(context, destPath);
                outputStream = new FileOutputStream(targetDB);

                InputStream inputStream = context.getResources().openRawResource(resID);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                outputStream.flush();
                inputStream.close();
                outputStream.close();
                result = true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    private static boolean copyWithEnhanceTimes(Context context, int resID, String dbName, String dbPath, int times) {
        boolean result = copyDB(context, resID, dbPath);

        if (!result) {
            LogUtil.log("InitDB copyDB fail,recopy fail,times=" + times);

            if (times > 0) {
                delOldDB(dbPath, dbName);
                times--;
                result = copyWithEnhanceTimes(context, resID, dbName, dbPath, times);
            }
        } else {
            LogUtil.log("InitDB copyDB success");
        }
        return result;
    }

    private static boolean verifyLocalDB(String dbPath, String dbName) {
return verifyLocalDB(dbPath,dbName,0);
    }
    /**
     * 校验DB，
     *
     * @return boolean｜true：通过；false：失败
     */
    private static boolean verifyLocalDB(String dbPath, String dbName,int retry) {
        //重试两次
        if (retry > 2) {
            return false;
        }
        File db = new File(dbPath);
        if (db.exists()) {
            DBManager.getInstance(dbName).initHelper();
            String info = DBMetaUtil.getSettingsValueByKey(dbName,META.KEY_PACKAGE);
            LogUtil.log("InitDB verifyLocalDB read info =" + info+"，times="+retry);
            if (!TextUtils.isEmpty(info)) {
                return true;
            }else {
                //出现异常时，等待100ms
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return verifyLocalDB(dbPath,dbName,retry++);
            }
        }
        return false;
    }

    /**
     * 删除旧DB
     */
    public static boolean delOldDB(String dbFilePath, String name) {
        boolean result = true;
        //如果DB存在于一个独有的文件夹中,删除整个文件夹
        File targetDB = new File(dbFilePath);
        result = FileUtil.deleteAllFile(targetDB, false);

        //如果DB存在于一个共享的文件夹中,删除DB和相关同名的DBjournal文件
        String dbFolderPath = FileUtil.getFolderPath(dbFilePath);
        File targetFolder = new File(dbFolderPath);
        if (targetFolder.exists() && targetFolder.isDirectory()) {
            File[] sonList = targetFolder.listFiles();
            for (File temp : sonList) {
                if (temp.getName().startsWith(name)) {
                    result = temp.delete();
                }
            }
        }
        return result;
    }
}
