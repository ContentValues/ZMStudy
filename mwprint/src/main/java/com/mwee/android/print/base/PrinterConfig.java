package com.mwee.android.print.base;


import android.text.TextUtils;

import com.mwee.android.print.encoding.Lang;
import com.mwee.android.print.encoding.ZHConverter;
import com.mwee.android.print.printer.PrintLog;
import com.mwee.android.print.printer.usbserial.UsbSerialPrinterConfig;
import com.mwee.android.print.processor.PrintBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * 缓存打印机配置
 * Created by sunkai on 15/11/11.
 *
 * @author virgil
 */
public abstract class PrinterConfig implements Serializable {

    private final static String COMMAND_STAR = "star";

    public static boolean checkPrinterStatus = false;
    /**
     * 标签打印的偏移量
     */
    public static int tscOffset = 13;
    /**
     * 标签打印机的反向
     * 使用{@link #reverse}
     */
    @Deprecated
    public static boolean tscReservse = false;
    public static ZHConverter converter = null;
    /**
     * 打印机链接类型
     */
    public int type = 0;
    /**
     * 纸张类型：0，热敏；1，针式；2，标签
     * see{@link PaperType}
     */
    @PaperType
    public int paperType = PaperType.Thermal;
    /**
     * 语言：0，简体中文；1，繁体
     */
    public int lang = Lang.CHINESE_SIMPLIE;
    /**
     * 超时时间
     */
    public int timeOut = 2;
    /**
     * 重打次数
     */
    public int retryTimes = 0;
    /**
     * 打印机尺寸
     */
    public int paperSize = 48;
    /**
     * 标签打印机的逆向:1,逆向；其他，默认
     */
    public int reverse = 0;
    /**
     * 命令类型
     */
    public String commandType = "";
    public boolean controlByteAlone = false;
    protected OutputStream outputStream;
    protected InputStream inputStream;
    /**
     * 1：是，0：不是
     */
    private int mIsOldPrinter = 0;
    /**
     * 支持状态检测
     */
    private int isSupportStatusCheck = 0;

    public abstract String getUniq();

    public abstract void connect() throws Exception;

    public abstract void closeConnect();

    /**
     * 输出流
     *
     * @param data byte[] | 内容
     * @return int | 成功输出的内容长度
     * @throws Exception | 抛出的异常
     */
    public abstract int write(byte[] data) throws Exception;

    public int write(List<byte[]> data) throws Exception {
        return 0;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public byte[] read(int targetLength) throws IOException {
        if (this.inputStream != null) {
            byte[] retData = new byte[targetLength];
            byte[] result = new byte[targetLength];
            int readLen = 0;
            int totalReadLen = 0;
            while (totalReadLen < targetLength) {
                readLen = this.inputStream.read(retData, 0, targetLength);
                if (readLen > 0) {
                    PrintLog.i("PrinterStatus-Base", "len=" + readLen);
                    PrintLog.i("PrinterStatus-Base", retData);
                    System.arraycopy(retData, 0, result, totalReadLen, readLen);
                } else {
                    return result;
                }
                totalReadLen += readLen;
            }
            return result;
        }
        return null;
    }

    public boolean isBig() {
        return this.paperSize == PrintBuilder.SIZE_BIG;
    }

    /**
     * 是否针式打印机
     *
     * @return boolean
     */
    public boolean isNeedle() {
        return this.paperType == PaperType.Impact || this.paperSize == PrintBuilder.SIZE_SMALL || this.paperSize == PrintBuilder.SIZE_NORMAL;
    }

    /**
     * 是否是非常小的尺寸
     *
     * @return boolean
     */
    public boolean isTooSmall() {
        return this.paperSize == PrintBuilder.SIZE_SMALL || this.paperSize == PrintBuilder.SIZE_MIN;
    }

    /**
     * 是否是串口转接的USb
     *
     * @return boolean
     */
    public boolean isUsbSerial() {
        return this instanceof UsbSerialPrinterConfig;
    }



    /**
     * 是否是Star的Printer
     *
     * @return boolean
     */
    public boolean starPrinter() {
        return COMMAND_STAR.equalsIgnoreCase(commandType);
    }

    public void prepareLang(int lang) {
        this.lang = lang;
        if (lang == Lang.CHINESE_TRANDITIONAL) {
            if (converter == null) {
                synchronized (PrinterConfig.class) {
                    if (converter == null) {
                        converter = ZHConverter.getInstance(lang);
                    }
                }
            }
        }
    }

    /**
     * 语言翻译
     *
     * @param content String
     * @return String
     */
    public String translate(String content) {
        if (TextUtils.isEmpty(content)) {
            return content;
        }
        if (converter == null) {
            return content;
        }
        return converter.convert(content);
    }

    public void setIsOldPrinter(boolean isOldPrinter) {
        if (isOldPrinter) {
            mIsOldPrinter = 1;
        } else {
            mIsOldPrinter = 0;
        }
    }

    public boolean isOldPrinter() {
        return mIsOldPrinter == 1;
    }

    public boolean supportStatusCheck() {
        return isSupportStatusCheck == 1;
    }

    public void setIsSupportStatusCheck(int isSupportStatusCheck) {
        this.isSupportStatusCheck = isSupportStatusCheck;
    }

}
