package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbshop")
public class ShopDBModel extends DBModel {
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fiShopKind")
    public int fiShopKind = 0;
    @ColumnInf(name = "fsEncryption")
    public String fsEncryption = "";
    @ColumnInf(name = "fsNotMaintain")
    public String fsNotMaintain = "";
    @ColumnInf(name = "fsFax")
    public String fsFax = "";
    @ColumnInf(name = "fsShopDesc")
    public String fsShopDesc = "";
    @ColumnInf(name = "fsShopId")
    public String fsShopId = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "fsEmail")
    public String fsEmail = "";
    @ColumnInf(name = "fsTel")
    public String fsTel = "";
    @ColumnInf(name = "fsFoodTradeId")
    public String fsFoodTradeId = "";
    @ColumnInf(name = "fsPostal")
    public String fsPostal = "";
    @ColumnInf(name = "fsCreateShopGUID")
    public String fsCreateShopGUID = "";
    @ColumnInf(name = "fsCellphoneCt")
    public String fsCellphoneCt = "";
    @ColumnInf(name = "fsCompanyGUID")
    public String fsCompanyGUID = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsContact")
    public String fsContact = "";
    @ColumnInf(name = "fsLicenceCode")
    public String fsLicenceCode = "";
    /**
     * 门店ID
     */
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fsMallShopGUID")
    public String fsMallShopGUID = "";
    @ColumnInf(name = "fsShopName")
    public String fsShopName = "";
    @ColumnInf(name = "fsShopAlias")
    public String fsShopAlias = "";
    @ColumnInf(name = "fsAddr")
    public String fsAddr = "";
    @ColumnInf(name = "fsNote")
    public String fsNote = "";
    @ColumnInf(name = "fslogourl")
    public String fslogourl = "";
    /**
     * 省代号
     */
    @ColumnInf(name = "fsProvinceId")
    public String fsProvinceId = "";
    /**
     * 城市代号
     */
    @ColumnInf(name = "fsCityId")
    public String fsCityId = "";
    /**
     * 区域代号
     */
    @ColumnInf(name = "fsDistrictId")
    public String fsDistrictId = "";


    /**
     * 店铺标识符: 0:普通门店 1:小散门店'
     */
    @ColumnInf(name = "fiShopType")
    public int fiShopType = 0;

    @ColumnInf(name = "sync")
    public int sync = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;

    public ShopDBModel() {

    }

    @Override
    public ShopDBModel clone() {
        ShopDBModel cloneObj = null;
        try {
            cloneObj = (ShopDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}