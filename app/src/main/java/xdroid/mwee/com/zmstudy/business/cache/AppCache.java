package xdroid.mwee.com.zmstudy.business.cache;

import com.mwee.android.sqlite.META;
import com.mwee.android.sqlite.base.ClientMetaUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import xdroid.mwee.com.model.util.db.MareaDBModel;
import xdroid.mwee.com.model.util.db.MtableDBModel;
import xdroid.mwee.com.zmstudy.business.db.client.ClientMenuDBUtil;
import xdroid.mwee.com.zmstudy.business.db.client.ClientTableDBUtils;
import xdroid.mwee.com.zmstudy.model.menu.MenuTypeBean;

/**
 * Created by zhangmin on 2018/6/20.
 */

public class AppCache extends Cache {

    private final static AppCache instance = new AppCache();

    /**
     * 所有菜品
     */
    public LinkedHashMap<String, MenuTypeBean> fullDataMap = new LinkedHashMap<>();

    /**
     * 根节点菜品
     */
    public List<MenuTypeBean> firstNodeMap = new ArrayList<>();

    /**
     * 所有餐区
     */
    public List<MareaDBModel> mareaDBModelList = new ArrayList<>();

    /**
     * 餐区对应的桌台  key fsmareaid --value List<MtableDBModel>
     */
    public LinkedHashMap<String, List<MtableDBModel>> areaTableList = new LinkedHashMap<>();


    private AppCache() {

    }

    public static AppCache getInstance() {
        return instance;
    }


    @Override
    public void refresh() {
        fullDataMap.clear();
        firstNodeMap.clear();
        ClientMenuDBUtil.buildMenuType(ClientMetaUtil.getSettingsValueByKey(META.SHOPID), fullDataMap, firstNodeMap);

        mareaDBModelList = ClientTableDBUtils.loadMareaList();
        areaTableList = ClientTableDBUtils.loadAreaTableList();

    }

    @Override
    public void clean() {

    }
}
