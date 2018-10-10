package xdroid.mwee.com.posdinnerprinter.print;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbPrinter")
public class PrinterDBModel extends DBModel {
    @ColumnInf(name = "fiRetry")
    public int fiRetry = 0;
    @ColumnInf(name = "fsIP")
    public String fsIP = "";
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiInt1")
    public int fiInt1 = 0;
    @ColumnInf(name = "fiTaskCount")
    public int fiTaskCount = 0;
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fiTimeOut")
    public int fiTimeOut = 0;
    @ColumnInf(name = "fiID", primaryKey = true)
    public int fiID = 0;
    /**
     * 打印机状态;
     * 1打印机正常
     * 2打印机正在打印
     * 3一次打印结束
     * 4打印机上盖打开
     * 5切刀错误
     * 6打印机禁止打印
     * 7打印机有可恢復异常
     * 8打印机缺纸
     * 10打印机离线
     * 11打印机正常任务未完成
     */
    @ColumnInf(name = "fiPrinterStatus")
    public int fiPrinterStatus = 0;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";

    /**
     * 打印机接口类型 see {@link PrinterType}
     */
    @ColumnInf(name = "fiPrinterCls")
    public int fiPrinterCls = 0;
    @ColumnInf(name = "fiPaperSize")
    public int fiPaperSize = 0;
    @ColumnInf(name = "fiIsMakePrn")
    public int fiIsMakePrn = 0;
    @ColumnInf(name = "fsCommandType")
    public String fsCommandType = "";
    @ColumnInf(name = "fsPrinterName")
    public String fsPrinterName = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "fsStr1")
    public String fsStr1 = "";
    /**
     * 备用打印机的名称
     */
    @ColumnInf(name = "fsbakprintername")
    public String fsbakprintername = "";
    /**
     * 是否切换到备用打印机：1：已切换到备用打印机；0：未切换到备用打印机
     */
    @ColumnInf(name = "switch_backup")
    public int switch_backup = 0;
    /**
     * 切换到备用的打印机的时间:yyyy-MM-dd HH:mm:ss
     */
    @ColumnInf(name = "switchTime")
    public String switchTime;

    @ColumnInf(name = "sync")
    public int sync = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;

    /**
     * 一行字符数；80mm纸张默认是48，76mm默认是35，58mm默认32
     */
    @ColumnInf(name = "fiCPL")
    public int fiCPL = 0;

    /**
     * 打印机类型：0，热敏打印机；1，针式打印机；2，标签打印机
     */
    @ColumnInf(name = "fiPrinterType")
    public int fiPrinterType = 0;

    /**
     * 是否逆向打印：0，不逆向；1，逆向
     */
    @ColumnInf(name = "fiReverse")
    public int fiReverse = 0;

    /**
     * 打印机编码：0，默认，简体中文；1，繁体中文
     */
    @ColumnInf(name = "fiLanguage")
    public int fiLanguage = 0;

    /**
     * 打印机SN号
     */
    @ColumnInf(name = "fsPrinterSN")
    public String fsPrinterSN = "";

    /**
     * 新旧打印机类型
     */
    @ColumnInf(name = "fiNewPrinter")
    public int fiNewPrinter = 0;

    @ColumnInf(name = "fiCutSamSung")
    public int fiCutSamSung = 0;

    @ColumnInf(name = "fiSupportStatusCheck")
    public int fiSupportStatusCheck = 0;

    public PrinterDBModel() {

    }

    @Override
    public PrinterDBModel clone() {
        PrinterDBModel cloneObj = null;
        try {
            cloneObj = (PrinterDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }

    public boolean checkIsUsb() {
        return fiPrinterCls == PrinterType.USB || checkIsUsbSerial();
    }

    public boolean checkIsDirectUsb() {
        return fiPrinterCls == PrinterType.USB;
    }

    public boolean checkIsUsbSerial() {
        return fiPrinterCls == PrinterType.USBSERIAL;
    }

}