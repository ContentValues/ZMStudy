package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

@TableInf(name = "tbask")
public class AskDBModel extends DBModel {
    @ColumnInf(name = "fsAskName")
    public String fsAskName = "";
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fsShopGUID")
    public String fsShopGUID = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsAskGpId")
    public String fsAskGpId = "";
    @ColumnInf(name = "fiId", primaryKey = true)
    public int fiId = 0;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fdAddPrice")
    public BigDecimal fdAddPrice = BigDecimal.ZERO;
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";

    @ColumnInf(name = "sync")
    public int sync = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;

    /**fiId
     * 禁止在原始model里添加页面使用的字段
     */
    /*@Deprecated
    public boolean selected=false;*/
    public AskDBModel() {

    }

    @Override
    public AskDBModel clone() {
        AskDBModel cloneObj = null;
        try {
            cloneObj = (AskDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}