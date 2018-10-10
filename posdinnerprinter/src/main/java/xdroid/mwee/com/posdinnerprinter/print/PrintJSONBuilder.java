package xdroid.mwee.com.posdinnerprinter.print;

import android.support.annotation.NonNull;
import com.mwee.android.tools.DateUtil;

/**
 * Created by virgil on 2017/1/22.
 */

public class PrintJSONBuilder {

    /**
     * @param orderID      订单号
     * @param mealNO
     * @param businessDate 营业日期
     * @param printNO      印号
     * @param userName     服务员名称
     * @param deptID       部门ID
     * @param reportID
     * @param hostId       站点ID
     * @param printAtOnce  是否立即打印 | Boolean true: 立即打印； false:不打印
     * @return
     */
    public static PrintTaskDBModel buildPrintTask(String orderID, String mealNO, String businessDate, int printNO, String userName, String deptID, String reportID,
                                                  @NonNull String hostId, boolean printAtOnce) {
        PrintTaskDBModel taskDBModel = PrintTaskDBModel.Creater.newInstance()
                .setCreateUserName(userName)
                .setHostId(hostId)
                .setDeptId(deptID)
                .setDeptName("")
                .setCreateTime(DateUtil.getCurrentTime())
                .setSellNo(orderID)
                .setOtherNo(mealNO)
                .setDate(businessDate)
                .setPrintNo(printNO)
                .setReportId(reportID)
                .setPrintAtOnce(printAtOnce)
                .setReportName("预结单")
                .build();
        taskDBModel.fiTaskType = 1;
        taskDBModel.fiPrnDataType = 2;
        return taskDBModel;
    }

}
