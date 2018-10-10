package xdroid.mwee.com.posdinnerprinter.print;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

/**
 * 打印任务的model，这个model已经实现了Parcelable接口，修改参数的时候，需要更新parcelable方法
 * Created by virgil on 16/7/5.
 */
@TableInf(name = "tbPrintTask")
public class PrintTaskDBModel extends DBModel {
    @ColumnInf(name = "fiID")
    public int fiID = 0;//自增主键

    @ColumnInf(name = "fiID_Server")

    public int fiID_Server = 0;//--关联ID(用于打服客户端同步数据使用)

    @ColumnInf(name = "fiPrintNo", primaryKey = true)
    public int fiPrintNo = 0;//	--打印流水号;1、2、3、…

    @ColumnInf(name = "fsCreateTime")
    public String fsCreateTime = "";//	--提交日期时间;系统日期

    @ColumnInf(name = "fsCreateUserName")
    public String fsCreateUserName = "";//			--提交用户名称

    @ColumnInf(name = "fsHostId", primaryKey = true)
    public String fsHostId = "";//	--提交站点代码

    @ColumnInf(name = "fsDeptId")
    public String fsDeptId = "";//	--部门代码

    @ColumnInf(name = "fsDeptName")
    public String fsDeptName = "";//		--部门名称;make report title

    @ColumnInf(name = "fsPrinterName")
    public String fsPrinterName = "";//	--打印机名称;别名

    @ColumnInf(name = "fsReportId")
    public String fsReportId = "";//		--小票代码

    @ColumnInf(name = "fsReportName")
    public String fsReportName = "";//		--小票名称

    @ColumnInf(name = "fsPrnData")
    public String fsPrnData = "";//				--打印数据内容;

    @ColumnInf(name = "fsPrnData2")
    public String fsPrnData2 = "";//				--打印数据内容2;

    @ColumnInf(name = "fiTaskType")
    public int fiTaskType = 0;//	--任务类型;1打印/2开钱箱/3蜂鳴

    @ColumnInf(name = "fiPrnDataType")
    public int fiPrnDataType = 0;//		--打印数据类型;1图打/2命令打

    @ColumnInf(name = "fsRemark")
    public String fsRemark = "";//	--备注

    /*-来源单据信息-*/
    @ColumnInf(name = "fsSellNo")
    public String fsSellNo = "";//		--销售单号

    @ColumnInf(name = "fsOtherNo")
    public String fsOtherNo = "";//			--桌号;牌号

    @ColumnInf(name = "fsDate")
    public String fsDate = "";//			--营业日期

    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;//	--任务状态;1未打印(正常)、3失败、4成功、8逾期、7打印中

    @ColumnInf(name = "fiErrCount")
    public int fiErrCount = 0;//			--错误次数

    @ColumnInf(name = "fiPrintCount")
    public int fiPrintCount = 0;//			--打印的次数

    @ColumnInf(name = "fsFinishTime")
    public String fsFinishTime = "";//			--完成日期时间

    @ColumnInf(name = "fsPrnOkAction")
    public String fsPrnOkAction = "";//			--成功后执行动作;sql语句

    @ColumnInf(name = "uri")
    public String uri = "";//			--成功后执行动作;sql语句

    @ColumnInf(name = "fiRetry")
    public int fiRetry = 0;//最大重打次数,目前已不使用这个字段
    @ColumnInf(name = "fiPaperSize")
    public int fiPaperSize = 0;

    @ColumnInf(name = "fsTaskDetail")
    public String fsTaskDetail = "";//任务明细
    /**
     * 使用的是否是备用的打印机
     */
    @ColumnInf(name = "is_backup_printer")
    public int is_backup_printer = 0;
    /**
     * 备用打印机的名称
     */
    @ColumnInf(name = "fsbakprintername")
    public String fsbakprintername = "";


    /**
     * 是否立即打印
     * true: 立即打印
     * false: 不打印
     */
    public boolean printAtOnce = true;
    public String titleRemind = "";  //标题备注信息
    /**
     * 手动重打
     */
    public int manaualReprint = 0;

    public PrintTaskDBModel() {
    }

    @Override
    public PrintTaskDBModel clone() {
        PrintTaskDBModel cloneObj = null;
        try {
            cloneObj = (PrintTaskDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }

    public static class Creater {

        private PrintTaskDBModel task = null;

        private Creater() {
            task = new PrintTaskDBModel();
        }

        public static Creater newInstance() {
            return new Creater();
        }

        public PrintTaskDBModel build() {
            return task;
        }

        public Creater setPrintNo(int fiPrintNo) {
            task.fiPrintNo = fiPrintNo;
            return this;
        }

        public Creater setCreateTime(String fsCreateTime) {
            task.fsCreateTime = fsCreateTime;
            return this;
        }

        public Creater setCreateUserName(String fsCreateUserName) {
            task.fsCreateUserName = fsCreateUserName;
            return this;
        }

        public Creater setHostId(String fsHostId) {
            task.fsHostId = fsHostId;
            return this;
        }

        public Creater setDeptId(String fsDeptId) {
            task.fsDeptId = fsDeptId;
            return this;
        }

        public Creater setDeptName(String fsDeptName) {
            task.fsDeptName = fsDeptName;
            return this;
        }

        public Creater setPrinterName(String fsPrinterName) {
            task.fsPrinterName = fsPrinterName;
            return this;
        }

        public Creater setReportId(String fsReportId) {
            task.fsReportId = fsReportId;
            return this;
        }

        public Creater setReportName(String fsReportName) {
            task.fsReportName = fsReportName;
            return this;
        }

        public Creater setSellNo(String fsSellNo) {
            task.fsSellNo = fsSellNo;
            return this;
        }

        public Creater setOtherNo(String fsOtherNo) {
            task.fsOtherNo = fsOtherNo;
            return this;
        }

        public Creater setPrintAtOnce(boolean printAtOnce) {
            task.printAtOnce = printAtOnce;
            return this;
        }

        public Creater setDate(String fsDate) {
            task.fsDate = fsDate;
            return this;
        }

    }

}
