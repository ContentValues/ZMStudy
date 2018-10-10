package xdroid.mwee.com.zmstudy.db.client;

import android.support.v4.util.ArrayMap;
import android.util.SparseArray;

import com.mwee.android.sqlite.base.APPConfig;
import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.base.DBSimpleUtil;

import java.util.ArrayList;
import java.util.List;

import xdroid.mwee.com.model.util.db.AskDBModel;
import xdroid.mwee.com.model.util.db.AskgpDBModel;
import xdroid.mwee.com.model.util.db.MenuitemaskgpDBModel;
import xdroid.mwee.com.zmstudy.model.menu.MenuExtra;
import xdroid.mwee.com.zmstudy.model.menu.MenuExtraItem;
import xdroid.mwee.com.zmstudy.model.menu.MenuExtraType;


/**
 * ClientAskDBUtil
 * Created by virgil on 16/6/15.
 */
public class ClientAskDBUtil {
    /**
     * 获取要求分组的id和名称
     *
     * @param shopGuiD String
     * @return ArrayMap<String,String> | key:fsAskGpId;value:fsAskGpName
     */
    public static ArrayMap<String, String> getAskGoup(String shopGuiD) {
        String sql = "select fsAskGpId,fsAskGpName from "
                + DBModel.getTableName(AskgpDBModel.class)
                + " where fsShopGUID='" + shopGuiD + "'"
                + " and fiStatus='1' and fsAskGpId != '-1'"
                + " order by fiSortOrder asc";
        List<AskgpDBModel> askGroupList = DBSimpleUtil.queryList(APPConfig.DB_MAIN, sql, AskgpDBModel.class);
        ArrayMap<String, String> result = new ArrayMap<>();
        if (askGroupList != null && askGroupList.size() > 0) {
            for (AskgpDBModel temp : askGroupList) {
                result.put(temp.fsAskGpId, temp.fsAskGpName);
            }
        }
        return result;
    }

    /**
     * 根据fsAskGpId,归类db里所有合法的要求
     *
     * @param shopGuiD String
     * @return ArrayMap<String, List<AskDBModel>> | key:fsAskGpId;value:List<AskDBModel>
     */
    public static ArrayMap<String, MenuExtra> getAskList(String shopGuiD, ArrayMap<String, String> askTypeMap) {
        String sql = "select fsAskGpId,fiId,fsAskName,fdAddPrice from "
                + DBModel.getTableName(AskDBModel.class)
                + " where fsShopGUID='" + shopGuiD + "'"
                + " and fiStatus='1' and fsAskGpId in (select fsAskgpId from tbaskgp where fistatus = '1' and fsAskGpId != '-1' ) ";
        List<AskDBModel> askList = DBSimpleUtil.queryList(APPConfig.DB_MAIN, sql, AskDBModel.class);
        ArrayMap<String, MenuExtra> result = new ArrayMap<>();
        if (askList != null && askList.size() > 0) {
            for (AskDBModel temp : askList) {
                MenuExtra item = result.get(temp.fsAskGpId);
                if (item == null) {
                    item = new MenuExtra();
                    item.groupID = temp.fsAskGpId;
                    item.type = MenuExtraType.ASK;
                    item.isRequired = false;
                    item.name = askTypeMap.get(temp.fsAskGpId);
                    result.put(temp.fsAskGpId, item);
                }
                MenuExtraItem extraItem = new MenuExtraItem();
                extraItem.id = temp.fiId;
                extraItem.groupIDFather = temp.fsAskGpId;
                extraItem.name = temp.fsAskName;
                extraItem.price = temp.fdAddPrice;
                extraItem.isDefault = false;
                extraItem.selected = false;
                item.itemList.add(extraItem);
            }
        }
        return result;
    }

    /**
     * 获取菜品的要求列表
     *
     * @param shopGuiD String
     * @return SparseArray<List<String>> | key:{@link };value:List<{@link MenuitemaskgpDBModel#fsAskGpId}
     */
    public static SparseArray<List<String>> getMenuAskList(String shopGuiD) {
        String sql = "select fiItemCd,fsAskGpId from " + DBModel.getTableName(MenuitemaskgpDBModel.class)
                + " where fsShopGUID='" + shopGuiD + "'"
                + " and fiStatus='1' " +
                " and fsaskgpId in ( select fsaskgpid from tbaskgp where fistatus = '1' and fsAskGpId != '-1'" +
                " ) ";
        List<MenuitemaskgpDBModel> list = DBSimpleUtil.queryList(APPConfig.DB_MAIN, sql, MenuitemaskgpDBModel.class);
        SparseArray<List<String>> result = new SparseArray<>();
        for (MenuitemaskgpDBModel temp : list) {
            List<String> askList = result.get(temp.fiItemCd);
            if (askList == null) {
                askList = new ArrayList<>();
                result.append(temp.fiItemCd, askList);
            }
            askList.add(temp.fsAskGpId);
        }
        return result;
    }

}
