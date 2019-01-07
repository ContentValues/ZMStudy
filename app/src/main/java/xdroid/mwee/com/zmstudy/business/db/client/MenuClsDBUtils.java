package xdroid.mwee.com.zmstudy.business.db.client;

import com.mwee.android.sqlite.base.APPConfig;
import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.base.DBSimpleUtil;

import xdroid.mwee.com.model.util.db.MenuclsDBModel;

/**
 * Created by zhangmin on 2018/6/30.
 */

public class MenuClsDBUtils {

    /**
     * 根据菜品分类ID获取菜品分类,不需要判断数据状态
     *
     * @param shopGuiD    String
     * @param fsMenuClsId String
     * @return MenuclsDBModel
     */
    public static MenuclsDBModel queryMenuClsByID(String shopGuiD, String fsMenuClsId) {
        String sqlMenuType = "select * from "
                + DBModel.getTableName(MenuclsDBModel.class)
                + " where fsShopGUID='" + shopGuiD + "'"
                + " and fsMenuClsId='" + fsMenuClsId + "'"
                + " order by fiSortOrder asc,fiStatus asc";
        MenuclsDBModel model = DBSimpleUtil.query(APPConfig.DB_MAIN, sqlMenuType, MenuclsDBModel.class);
        return model;
    }
}
