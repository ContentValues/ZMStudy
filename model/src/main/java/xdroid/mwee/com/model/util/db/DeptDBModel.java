package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbdept")
public class DeptDBModel extends DBModel {
    /**
     * --制作部门用-是否打出催菜单;0/1
     */
    @ColumnInf(name = "fiIsHurryBill")
    public int fiIsHurryBill = 0;
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    /**
     * --制作部门用-是否打出换桌单;0/1
     */
    @ColumnInf(name = "fiIsChangeBill")
    public int fiIsChangeBill = 0;
    /**
     * --制作部门用-制作单 1=一菜一切/2=总单/3=总单&一菜一切/4=一份一切
     */
    @ColumnInf(name = "fiIsOneItemCut")
    public int fiIsOneItemCut = 0;
    @ColumnInf(name = "fiPrintCopies")
    public int fiPrintCopies = 0;//打印份数
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsPrinterName2")
    public String fsPrinterName2 = "";
    /**
     * --部门类型; 0一般 /1传菜部门/2制作部门
     */
    @ColumnInf(name = "fiDeptCls")
    public int fiDeptCls = 0;
    /**
     * --制作部门用-是否打出退菜单;0/1
     */
    @ColumnInf(name = "fiIsBackBill")
    public int fiIsBackBill = 0;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    /**
     * --制作部门用-是否打出起菜单;0/1
     */
    @ColumnInf(name = "fiIsTaskBill")
    public int fiIsTaskBill = 0;
    /**
     * --制作部门用-是否打出转菜单;0/1
     */
    @ColumnInf(name = "fiIsTurnBill")
    public int fiIsTurnBill = 0;
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fsPrinterName")
    public String fsPrinterName = "";
    @ColumnInf(name = "fsDeptId", primaryKey = true)
    public String fsDeptId = "";
    @ColumnInf(name = "fsDeptName")
    public String fsDeptName = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";

    @ColumnInf(name = "sync")
    public int sync = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;

    /**
     * 可打印全部菜 '可打印全部菜 0-false,1-true';
     */
    @ColumnInf(name = "fiPrintDishes")
    public int fiPrintDishes = 0;

    public DeptDBModel() {

    }

    @Override
    public DeptDBModel clone() {
        DeptDBModel cloneObj = null;
        try {
            cloneObj = (DeptDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}