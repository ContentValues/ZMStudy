package com.mwee.android.print.processor;

import android.graphics.Bitmap;

import com.mwee.android.print.base.PrinterConfig;
import com.mwee.android.print.encoding.Lang;
import com.mwee.android.print.image.ImageProcess;
import com.mwee.android.print.printer.PrintLog;
import com.mwee.android.print.printer.tsc.TscPrinter;
import com.mwee.android.print.processor.command.CommandEsc;
import com.mwee.android.print.processor.command.CommandProxy;
import com.mwee.android.print.processor.command.ICommand;
import com.mwee.android.tools.LogUtil;
import com.mwee.android.tools.StringUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * 打印指令输出管理类
 *
 * @author virgil
 */
public class PrinterManager {
    private final PrinterConfig config;
    /**
     * 是否是TSC指令集
     */
    private boolean isTsc;
    /**
     * 打印数据集合
     */
    private List<PrintDataItem> data;

    /**
     * 构造方法
     *
     * @param config     PrinterConfig | 打印机的配置项
     * @param isGprinter boolean | 是否是指令
     */
    public PrinterManager(PrinterConfig config, boolean isGprinter) {
        this.config = config;
        this.isTsc = isGprinter;
        this.data = new ArrayList<>();

    }

    public PrintResult start() {
        return start("");
    }

    /**
     * 开始执行打印
     *
     * @param uniq 小票唯一标识
     */
    public PrintResult start(String uniq) {
        PrintResult result = new PrintResult();
        //开始做一些异常判断
        if (this.data == null || this.data.isEmpty()) {
            result.result = PrintResultCode.NO_DATA;
            result.buildMsg();
            return result;
        }
        if (config == null) {
            result.result = PrintResultCode.PRINTER_CONNECT_FAIL;
            result.buildMsg();
            return result;
        }
        try {
            PrintLog.i("PrinterManager", "准备锁");

            synchronized (config) {
                PrintLog.i("PrinterManager", " 进入锁");
                //链接打印机
                try {
                    PrintLog.i("PrinterManager", " 开始链接");
                    this.config.connect();
                } catch (Exception e) {
                    //重试一次，如果失败，则返回
                    PrintLog.i("PrinterManager ", "链接失败，开始重连");

                    e.printStackTrace();
                    try {
                        this.config.connect();
                        PrintLog.i("PrinterManager", " 重连成功");

                    } catch (Exception e1) {
                        PrintLog.e("PrinterManager", " 链接失败，重连失败");

                        result.result = PrintResultCode.PRINTER_CONNECT_FAIL;
                        result.buildMsg();
                        this.config.closeConnect();
                        e.printStackTrace();
                        result.addTrace(e1);
                        return result;
                    }
                }
                //发送打印
                PrintLog.i("PrinterManager", " 开始打印");

                result.result = doPrint(uniq);
                //关闭链接
                PrintLog.i("PrinterManager", " 打印结束，关闭链接");

                this.config.closeConnect();
                long sleepTime;
                if (config.isOldPrinter()) {
                    sleepTime = 2000;
                } else {
                    sleepTime = 1000;
                }
                //等待1秒
                try {
                    PrintLog.i("PrinterManager", " 结束等待");

                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        } catch (Throwable e) {
            result.result = PrintResultCode.PRINT_EXCEPTION;
            result.addTrace(e);
            PrintLog.e("PrinterManager", " 打印异常", e);
        } finally {
            try {
                this.config.closeConnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        result.buildMsg();

        return result;
    }

    private int doPrint(String uniq) throws Exception {
        ICommand tempCommand = CommandProxy.createCommand(config);

        if (isTsc) {
            //TSC指令的打印机，单独处理
            TscPrinter printer = new TscPrinter(config);
            printer.setOffSet(PrinterConfig.tscOffset);
            printer.setReservse(config.reverse == 1);
            if (this.data.size() == 1) {
                switch (data.get(0).dataFormat) {
                    case PrintDataItem.FORMAT_DRAWER:
                        printer.kickDrawer();
                        break;
                    default:
                        break;
                }
            } else {
                printer.printText(this.data);
            }
            return PrintResultCode.SUCCESS;
        } else {
            LogUtil.logBusiness("PrinterStatus " + uniq + " start");

            //检测打印机的状态，目前不生效
            if (config.supportStatusCheck()) {
                int result = checkStatus(uniq);
                //暂时不做处理
//                if (result != PrintResultCode.SUCCESS) {
//                    return result;
//                }
            }

            //开始组建指令
            tempCommand.reset();
            //商米T1不支持这个指令
//            tempCommand.setEncodingType((byte) 2);
            tempCommand.setLanguage();
            tempCommand.setChineseEncodingMode();
            if (config.isNeedle()) {
                tempCommand.setFont((byte) 0);
            }
            tempCommand.reSetLine();
            for (int i = 0; i < this.data.size(); i++) {

                PrintDataItem toPrint = this.data.get(i);
                byte isUnderline = CommandEsc.UNDERLINE_NONE;
                if (toPrint.underline) {
                    isUnderline = CommandEsc.UNDERLINE_SINGLE;
                }
                if (toPrint.textBold != -1) {
                    tempCommand.setBold((byte) 1);
                } else {
                    tempCommand.setBold((byte) 0);
                }
                if (config.isNeedle()) {
                    if (toPrint.fontsize == -1) {
                        tempCommand.setFontSizeFor76(1);
                    } else {
                        tempCommand.setFontSizeFor76(toPrint.fontsize);
                    }
                } else {
                    if (toPrint.fontsize == -1) {
                        tempCommand.setFontSize(1);
                    } else {
                        tempCommand.setFontSize(toPrint.fontsize);
                    }
                }
                switch (toPrint.textAlign) {
                    case PrintDataItem.ALIGN_RIGHT:
                        tempCommand.setAlign((byte) toPrint.textAlign);
                        break;
                    case PrintDataItem.ALIGN_CENTRE:
                        tempCommand.setAlign((byte) toPrint.textAlign);
                        break;
                    default:
                        tempCommand.setAlign((byte) 0);
                        break;
                }

                switch (toPrint.dataFormat) {
                    case PrintDataItem.FORMAT_FEED:
                        if (toPrint.marginTop > 0) {
                            if (config.isNeedle()) {
                                tempCommand.feed((byte) (toPrint.marginTop));
                            } else {
                                tempCommand.feed((byte) (toPrint.marginTop * 20));
                            }
                        }
                        break;
                    case PrintDataItem.FORMAT_TXT:
                        if (toPrint.marginTop > 0) {
                            if (config.isNeedle()) {
                                tempCommand.feed((byte) (toPrint.marginTop));
                            } else {
                                tempCommand.feed((byte) (toPrint.marginTop * 20));
                            }
                        }
                        if (config.isNeedle()) {
                            tempCommand.setFontColor((byte) toPrint.fontColor);
                        }
                        if (config.lang != Lang.CHINESE_SIMPLIE) {
                            toPrint.text = config.translate(toPrint.text);
                        }
                        tempCommand.printText(toPrint.text, config.lang);
                        break;
                    case PrintDataItem.FORMAT_IMG: {
                        int maxWidth = 140;
                        if (config.isNeedle()) {
                            continue;
                        }
                        if (!config.isBig()) {
                            maxWidth = 110;
                        } else if (config.isUsbSerial()) {
                            maxWidth = 130;
                        }
                        try {
                            Bitmap bitmap = toPrint.bitmap;
                            if (bitmap == null) {
                                continue;
                            }
                            bitmap = ImageProcess.toFixedSizeAndGrayscale(bitmap, maxWidth);
                            tempCommand.printBitmap(bitmap);
                        } catch (Exception e) {
                            LogUtil.logError(e);
                        }
                        break;
                    }
                    case PrintDataItem.FORMAT_BARCODE:
                        if (config.isNeedle()) {
                            continue;
                        }
                        tempCommand.printBarcode(toPrint.text, config.isBig());
                        break;
                    case PrintDataItem.FORMAT_CUT:
                        tempCommand.cut();
                        break;
                    case PrintDataItem.FORMAT_BEEP:
                        if (config.isNeedle()) {
                            continue;
                        }
                        tempCommand.beep();
                        break;
                    case PrintDataItem.FORMAT_QR: {
                        int imageSize = 210;
                        if (config.isNeedle()) {
                            continue;
                        }
                        if (config.isUsbSerial()) {
                            imageSize = 180;
                        }
                        Bitmap bitmap = BarcodProcessor.createQRImage(toPrint.text, imageSize, imageSize);
                        if (bitmap != null) {
                            bitmap = BitmapUtil.toGrayscale(bitmap);
                            tempCommand.printQRBitmap(bitmap);
                        }
                        break;
                    }
                    case PrintDataItem.FORMAT_DRAWER:
                        tempCommand.kickDrawer();
                        break;
                    default:
                        break;

                }
            }
            tempCommand.reset();


        }
        PrintLog.e("PrinterManager", " 开始发送发送指令");

        int result = tempCommand.startWrite(config, uniq);
        PrintLog.e("PrinterManager", " send finish,receive result [" + result + "]");

        //检测打印之后，的状态，目前不生效
        if (config.supportStatusCheck()) {
            checkResultStatus(uniq);
        }
        return result;
    }

    private int checkStatus(String uniq) throws Exception {
        //重置缓冲区
        byte[] tcmd = new byte[2];
        tcmd[0] = ICommand.ESC;
        tcmd[1] = 0x40;
        this.config.write(tcmd);

        //发送打印机状态的实时回调
        tcmd = new byte[3];
        tcmd[0] = 0x10;
        tcmd[1] = 0x04;
        tcmd[2] = 0x04;
        this.config.write(tcmd);
        byte[] result = null;
        try {
            result = this.config.read(7);
            PrintLog.e("PrinterStatus", uniq + " result=" + PrintLog.i("PrinterStatus", result));
        } catch (IOException e) {
            PrintLog.e("PrinterStatus", uniq, (e));
            return PrintResultCode.PRINTER_CHECK_TIME_OUT;

        }
        if (result != null && result.length > 0) {
            int value = result[0];
            if ((value & 12) == 12) {
                //纸将用完
//                return PrintResultCode.PRINT_PAPER_NEARLY_OUT;

            }
            if ((value & 96) == 96) {
                //纸将用完
//                return PrintResultCode.PRINT_PAPER_OUT;
            }
        } else {
            return PrintResultCode.PRINTER_CHECK_TIME_OUT;
        }
        return PrintResultCode.SUCCESS;
    }

    private int checkResultStatus(String uniq) throws Exception {
        //重置缓冲区
        byte[] tcmd = new byte[2];
        tcmd[0] = ICommand.ESC;
        tcmd[1] = 0x40;
        this.config.write(tcmd);

        //发送打印机状态的实时回调
        tcmd = new byte[3];
        tcmd[0] = 0x10;
        tcmd[1] = 0x04;
        tcmd[2] = 0x03;
        this.config.write(tcmd);
        byte[] result = null;
        try {
            result = this.config.read(7);
            LogUtil.logBusiness("PrinterStatus " + uniq + " result=" + PrintLog.i("PrinterStatus", result));
        } catch (IOException e) {
            LogUtil.logBusiness("PrinterStatus " + uniq + " err=" + StringUtil.getExceptionInfo(e));
            PrintLog.e("PrinterStatus", "", e);
            return PrintResultCode.PRINTER_CHECK_TIME_OUT;

        }
        if (result != null && result.length > 0) {
            int value = result[0];
            if ((value & 12) == 12) {
                //纸将用完
//                return PrintResultCode.PRINT_PAPER_NEARLY_OUT;

            }
            if ((value & 96) == 96) {
                //纸将用完
//                return PrintResultCode.PRINT_PAPER_OUT;
            }
        } else {
            return PrintResultCode.PRINTER_CHECK_TIME_OUT;

        }
        return PrintResultCode.SUCCESS;
    }

    public List<PrintDataItem> getData() {
        return data;
    }

    public void setData(List<PrintDataItem> data) {
        this.data = data;
    }
}
