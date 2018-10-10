package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

/**
 * 要求分组表
 * Created by chris on 16/10/12.
 */
@TableInf(name = "tbAskGp")
public class TpAskModel extends DBModel {
    //菜品要求分组代号;自增编号
    @ColumnInf(name = "fsAskGpId", primaryKey = true)
    public String fsAskGpId;
    //菜品要求分组名称
    @ColumnInf(name = "fsAskGpName")
    public String fsAskGpName;
    //状态;1正常/9禁用/13删除
    @ColumnInf(name = "fiStatus")
    public int fiStatus;
    //数据类型;1系统预设数据、2用户建立数据
    @ColumnInf(name = "fiDataKind")
    public int fiDataKind;
    //顺序
    @ColumnInf(name = "fiSortOrder")
    public int fiSortOrder;
    //修改日期时间
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime;
    //修改用户代码
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId;
    //修改用户名称
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName;
    //门店GUID
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID;
}
