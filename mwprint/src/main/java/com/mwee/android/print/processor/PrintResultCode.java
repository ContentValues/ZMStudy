package com.mwee.android.print.processor;

/**
 * PrintResultCode
 * Created by virgil on 16/8/9.
 */
public class PrintResultCode {
    /**
     * 打印成功
     */
    public final static int SUCCESS = 0;
    /**
     * 没有这事打印数据
     */
    public final static int NO_DATA = 1;
    /**
     * 打印机配置不合法
     */
    public final static int CONFIG_ERROR = 2;
    /**
     * 打印机连接失败
     */
    public final static int PRINTER_CONNECT_FAIL = 3;
    /**
     * 打印出现异常
     */
    public final static int PRINT_EXCEPTION = 4;

    /**
     * 纸将尽
     */
    public final static int PRINT_PAPER_NEARLY_OUT = 5;
    /**
     * 纸已经用完了
     */
    public final static int PRINT_PAPER_OUT = 6;

    /**
     * 打印中(指令发送打印机，发送打印机成功)
     * {@link com.mwee.android.print.printer.cloud.CloudPrintResult#SENDED}
     */
    public final static int PRINTED = 7;
    /**
     * 打印机状态检测时，超时
     */
    public final static int PRINTER_CHECK_TIME_OUT =8;

}
