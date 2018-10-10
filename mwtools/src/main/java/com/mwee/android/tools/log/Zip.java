package com.mwee.android.tools.log;

import com.mwee.android.tools.FileUtil;

public class Zip {

    /**
     * 压缩文件,文件夹
     *
     * @param srcFileString 要压缩的文件/文件夹名字
     * @param zipFileString 指定压缩的目的和名字
     * @throws Exception
     */
    public static void ZipFolder(String srcFileString, String zipFileString) throws Exception {
        FileUtil.makeFileSafe(null, zipFileString);
        //创建Zip包
        java.util.zip.ZipOutputStream outZip = new java.util.zip.ZipOutputStream(new java.io.FileOutputStream(zipFileString));

        //打开要输出的文件
        java.io.File file = new java.io.File(srcFileString);

        //压缩
        try {
            ZipFiles(file.getParent() + java.io.File.separator, file.getName(), outZip);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //完成,关闭
            outZip.finish();
            outZip.close();
        }



    }//end of func

    /**
     * 压缩文件
     *
     * @param folderString String
     * @param fileString String
     * @param zipOutputSteam ZipOutputStream
     * @throws Exception
     */
    private static void ZipFiles(String folderString, String fileString, java.util.zip.ZipOutputStream zipOutputSteam) throws Exception {

        if (zipOutputSteam == null) {
            return;
        }

        if (fileString.contains(".zip")) {
            return;
        }
        java.io.File file = new java.io.File(folderString + fileString);

        //判断是不是文件
        if (file.isFile()) {
            java.util.zip.ZipEntry zipEntry = new java.util.zip.ZipEntry(fileString);
            java.io.FileInputStream inputStream = new java.io.FileInputStream(file);
            zipOutputSteam.putNextEntry(zipEntry);

            int len;
            byte[] buffer = new byte[4096];

            while ((len = inputStream.read(buffer)) != -1) {
                zipOutputSteam.write(buffer, 0, len);
            }

            zipOutputSteam.closeEntry();
            inputStream.close();
        } else {

            //文件夹的方式,获取文件夹下的子文件
            String[] fileList = file.list();

            //如果没有子文件, 则添加进去即可
            if (fileList == null || fileList.length <= 0) {
                java.util.zip.ZipEntry zipEntry = new java.util.zip.ZipEntry(fileString + java.io.File.separator);
                zipOutputSteam.putNextEntry(zipEntry);
                zipOutputSteam.closeEntry();
            }
            if (fileList != null) {
                //如果有子文件, 遍历子文件
                for (int i = 0; i < fileList.length; i++) {
                    ZipFiles(folderString, fileString + java.io.File.separator + fileList[i], zipOutputSteam);
                }//end of for
            }
        }//end of if

    }//end of func
}
