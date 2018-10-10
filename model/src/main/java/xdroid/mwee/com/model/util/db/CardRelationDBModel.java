package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

/**
 * 第三方会员卡与美味会员卡关联关系映射
 */
@TableInf(name = "tbCardRelation")
public class CardRelationDBModel extends DBModel {

    @ColumnInf(name = "fsGuid", primaryKey = true)
    public String fsGuid = "";

    /**
     * 第三方会员卡号
     */
    @ColumnInf(name = "fsRelKey", primaryKey = false)
    public String fsRelKey = "";

    /**
     * 美味会员卡号
     */
    @ColumnInf(name = "fsRelValue", primaryKey = false)
    public String fsRelValue = "";
    /**
     * 预留字段
     */
    @ColumnInf(name = "fsBody", primaryKey = false)
    public String fsBody = "";
    /**
     * 备注
     */
    @ColumnInf(name = "fsRemark", primaryKey = false)
    public String fsRemark = "";


    @ColumnInf(name = "fsShopGUID", primaryKey = false)
    public String fsShopGUID = "";

    @ColumnInf(name = "fsCreateTime", primaryKey = false)
    public String fsCreateTime = "";

    @ColumnInf(name = "fsUpdateTime", primaryKey = false)
    public String fsUpdateTime = "";

    @ColumnInf(name = "fsUpdateUserId", primaryKey = false)
    public String fsUpdateUserId = "";

    @ColumnInf(name = "fsUpdateUserName", primaryKey = false)
    public String fsUpdateUserName = "";

    /**
     * 状态
     */
    @ColumnInf(name = "fiStatus", primaryKey = false)
    public int fiStatus = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource", primaryKey = false)
    public int fiDataSource = 0;

    @ColumnInf(name = "lver")
    public int lver = 0;

    @ColumnInf(name = "pver")
    public int pver = 0;

    @ColumnInf(name = "sync")
    public int sync = 0;


    public CardRelationDBModel() {

    }

    @Override
    public CardRelationDBModel clone() {
        CardRelationDBModel cloneObj = null;
        try {
            cloneObj = (CardRelationDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}