package xdroid.mwee.com.zmstudy.business.db.client;

import android.text.TextUtils;
import android.util.SparseArray;

import com.mwee.android.sqlite.base.APPConfig;
import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.base.DBSimpleUtil;
import com.mwee.android.tools.StringUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import xdroid.mwee.com.model.util.db.MenuItemUnitDBModel;
import xdroid.mwee.com.model.util.db.MenuclsDBModel;
import xdroid.mwee.com.model.util.db.MenuitemDBModel;
import com.mwee.android.tools.ListUtil;
import xdroid.mwee.com.zmstudy.model.menu.MenuItem;
import xdroid.mwee.com.zmstudy.model.menu.MenuTypeBean;
import xdroid.mwee.com.zmstudy.model.menu.UnitModel;

/**
 * ClientMenuDBUtil
 * Created by virgil on 16/6/15.
 */
public class ClientMenuDBUtil {

    public final static int SYSTEM_CLSID_SPECAIL = 90;
    public final static int SYSTEM_CLSID_NEW = 91;


    public static List<MenuitemDBModel> getDBMenuitemListByClsId(String shopGuiD, String fsMenuClsId) {
        String sqlMenu = "select * from "
                + DBModel.getTableName(MenuitemDBModel.class)
                + " where fsShopGUID='" + shopGuiD + "'"
                + " and fiStatus='1' "
                + " and fiIsOut='0'" +
                " and fsMenuClsId = '" + fsMenuClsId
                + "' order by fiSortOrder ";
        //数据库中所有有效菜品
        List<MenuitemDBModel> menuDBList = DBSimpleUtil.queryList(APPConfig.DB_MAIN, sqlMenu, MenuitemDBModel.class);
        if (menuDBList == null) {
            menuDBList = new ArrayList<>();
        }
        return menuDBList;
    }

    public static List<MenuitemDBModel> getDBMenuitemList(String shopGuiD, int level, String fsMenuClsId_P, List<MenuitemDBModel> menuDBList) {
        List<MenuclsDBModel> menuclsDBModelList = getAllClsByLevel(shopGuiD, level, fsMenuClsId_P);
        if (menuclsDBModelList.size() > 0) {
            addToList(menuclsDBModelList, shopGuiD, level, menuDBList);
        }
        return menuDBList;
    }

    private static void addToList(List<MenuclsDBModel> menuclsDBModelList, String shopGuiD, int level, List<MenuitemDBModel> menuDBList) {
        for (MenuclsDBModel menuclsDBModel : menuclsDBModelList) {
            menuDBList.addAll(getDBMenuitemListByClsId(shopGuiD, menuclsDBModel.fsMenuClsId));
            int childLevel = level + 1;
            getDBMenuitemList(shopGuiD, childLevel, menuclsDBModel.fsMenuClsId, menuDBList);
        }
    }

    /**
     * 得到所有的一级/二级分类的菜单分类表
     *
     * @param shopGuiD      门店ID
     * @param level         层;1=第一级/2=第二级
     * @param fsMenuClsId_P 父层菜单分类代码
     * @return
     */
    public static List<MenuclsDBModel> getAllClsByLevel(String shopGuiD, int level, String fsMenuClsId_P) {
        String fsParentMenuClsIdParam = " and fsMenuClsId_P = '" + fsMenuClsId_P + "'";
        if (level == 1) {
            fsParentMenuClsIdParam = "";
        }
        String clsSql = "select * from " + DBModel.getTableName(MenuclsDBModel.class) + " where fiStatus = '1'" +
                " and fiLevel = '" + level + "' " + fsParentMenuClsIdParam
                + " and fiDataKind <> '1' "
                + " and fsShopGUID = '" + shopGuiD + "' order by fiSortOrder";
        List<MenuclsDBModel> menuclsDBModelList = DBSimpleUtil.queryList(APPConfig.DB_MAIN, clsSql, MenuclsDBModel.class);
        if (menuclsDBModelList == null) {
            menuclsDBModelList = new ArrayList<>();
        }
        return menuclsDBModelList;
    }


    /**
     * 获取单个菜品的所有规格
     *
     * @param menuID int|菜品ID
     * @return List<MenuItemUnitDBModel>
     */
    public static List<MenuItemUnitDBModel> getMenuUnitList(int menuID, boolean containsDeleteMenu) {
        String sql = null;
        if (containsDeleteMenu) {
            sql = "select * from tbmenuitemuint"
                    + " where fiItemCd='" + menuID
                    + "'"
                    + " order by fiDefault desc";
        } else {
            sql = "select * from tbmenuitemuint"
                    + " where fiItemCd='" + menuID
                    + "' and fiStatus <> '13' "
                    + " order by fiDefault desc";
        }
        return DBSimpleUtil.queryList(APPConfig.DB_MAIN, sql, MenuItemUnitDBModel.class);
    }


    /**
     * 获取单个菜品的规格数量
     *
     * @param menuID int|菜品ID
     * @return List<MenuItemUnitDBModel>
     */
    public static int getMenuUnitSize(int menuID) {
        String sql = "select count(*) as count from tbmenuitemuint"
                + " where fiItemCd='" + menuID
                + "' and fiStatus <> '13' ";
        String value = DBSimpleUtil.queryString(APPConfig.DB_MAIN, sql);
        int count = 0;
        if (!TextUtils.isEmpty(value)) {
            count = StringUtil.toInt(value, 0);
        }
        return count;
    }

    /**
     * 获取菜品配料分组的数量
     *
     * @param menuID 菜品ID
     * @return
     */
    public static int optIngredientGPCount(int menuID) {
        String sql = "select count(*) as count from tbmenuingredgprel"
                + " where fiStatus='1'" +
                "  and fiItemCd='" + menuID + "'";
        String value = DBSimpleUtil.queryString(APPConfig.DB_MAIN, sql);
        int count = 0;
        if (!TextUtils.isEmpty(value)) {
            count = StringUtil.toInt(value, 0);
        }
        return count;
    }

    /**
     * 获取菜品的做法数量
     *
     * @param menuID int | fiItemCd
     * @return int
     */
    public static int getMenuProcedureSize(int menuID) {
        String sql = "select count(*) as count from tbask"
                + " where fiStatus='1'" +
                "  and fiItemCd='" + menuID
                + "' and fsAskGpId='-1' ";
        String value = DBSimpleUtil.queryString(APPConfig.DB_MAIN, sql);
        int count = 0;
        if (!TextUtils.isEmpty(value)) {
            count = StringUtil.toInt(value, 0);
        }
        return count;
    }

    public static MenuItem buildMenuByDBModel(MenuitemDBModel tempMenu, SparseArray<List<String>> menuAsk) {
        return buildMenuByDBModel(tempMenu, menuAsk, false);
    }

    /**
     * 构建菜品Model
     *
     * @param tempMenu MenuitemDBModel | 菜品的DBmodel
     * @param menuAsk  SparseArray<List<String>>  | 所有菜品的要求
     * @return MenuItem
     */
    public static MenuItem buildMenuByDBModel(MenuitemDBModel tempMenu, SparseArray<List<String>> menuAsk, boolean containsDeleteMenu) {
        List<MenuItemUnitDBModel> unit = getMenuUnitList(tempMenu.fiItemCd, containsDeleteMenu);
        MenuItem item = new MenuItem();
        item.itemID = tempMenu.fiItemCd;
        item.name = tempMenu.fsItemName;
        item.isCategory = false;
        item.fsItemId = tempMenu.fsItemId;
        item.categoryCode = tempMenu.fsMenuClsId;
        if (!ListUtil.listIsEmpty(unit)) {
            item.currentUnit = UnitModel.copyTo(unit.get(0));
            item.restoreCurrentUnit();
        }
        DishesUtil.buildMenuConfig(item, tempMenu, getMenuProcedureSize(tempMenu.fiItemCd), getMenuUnitSize(tempMenu.fiItemCd), optIngredientGPCount(tempMenu.fiItemCd));
        return item;
    }


    private static MenuTypeBean buildIndexMenuType(String parentID) {
        MenuTypeBean typeNewMenu = new MenuTypeBean();
        typeNewMenu.fsMenuClsName = "全部";
        typeNewMenu.fsMenuClsId = "-1P_" + parentID;
        typeNewMenu.fsMenuClsId_P = parentID;
        typeNewMenu.typeIndex = true;
        typeNewMenu.menuList = new ArrayList<>();
        return typeNewMenu;
    }


    public static List<MenuTypeBean> buildMenuType(final String shopID, LinkedHashMap<String, MenuTypeBean>
            fullDataMap, List<MenuTypeBean> firstNodeList) {
        String sqlMenuType = "select * from tbmenucls"
                + " where fsShopGUID='" + shopID + "'"
                + " and fiStatus='1' and fiDataKind<>'1' and fiMenuClsKind <> '4' "
//                + " order by fiLevel asc,fiSortOrder asc";
                + " order by fsMenuClsId_p asc, fiSortOrder asc,fiDtlLvl asc";
        List<MenuTypeBean> list = DBSimpleUtil.queryList(APPConfig.DB_MAIN, sqlMenuType, MenuTypeBean.class);

        // 子分类的 fiSortOrder 小于 父分类的 fiSortOrder，可能出现的首次遍历无法找到关联父级分类的情况
        LinkedList<MenuTypeBean> makeuplist = new LinkedList<>();

        LinkedHashMap<String, MenuTypeBean> firstNodeMapTemp = new LinkedHashMap<>();
        for (MenuTypeBean temp : list) {
            if (TextUtils.equals(temp.fsMenuClsId, temp.fsMenuClsId_P)) {
                continue;
            }
            fullDataMap.put(temp.fsMenuClsId, temp);
            if (StringUtil.emptyInt(temp.fsMenuClsId_P)) {
                firstNodeMapTemp.put(temp.fsMenuClsId, temp);
            } else {
                MenuTypeBean father = fullDataMap.get(temp.fsMenuClsId_P);
                if (father != null) {
                    if (father.sonTypeList == null) {
                        father.sonTypeList = new ArrayList<>();
                    }
                    father.sonTypeList.add(temp);
                } else {
                    // 暂未找到父级分类的菜品分类加入列表，稍后二次查找
                    makeuplist.addFirst(temp);
                }
            }
        }

        if (!ListUtil.isEmpty(makeuplist)) {
            while (makeuplist.size() != 0) {
                MenuTypeBean menuTypeBean = makeuplist.removeFirst();
                MenuTypeBean father = fullDataMap.get(menuTypeBean.fsMenuClsId_P);
                if (father == null) {
                    continue;
                }
                if (father.sonTypeList == null) {
                    father.sonTypeList = new ArrayList<>();
                }
                father.sonTypeList.add(0, menuTypeBean);
            }
        }

        //新菜列表
        List<MenuItem> itemNew = new ArrayList<>();
        //招牌菜列表
        List<MenuItem> itemSpecial = new ArrayList<>();
        //全部菜品列表
        List<MenuItem> itemALl = new ArrayList<>();

        String sqlMenu = "select * from "
                + DBModel.getTableName(MenuitemDBModel.class)
                + " where fiIsOut=0 and fsShopGUID='" + shopID + "'"
                + " and fiStatus='1' and fiItemKind in (1,2) "
                + " and fiIsSet <> 1 " //过滤掉只属套餐的菜
                + " and fiIsOut='0' and fsItemName != '预点单餐盒费'  order by fsMenuClsId asc,fiSortOrder asc";
        //数据库中所有有效菜品-单品、套餐头
        List<MenuitemDBModel> menuDBList = DBSimpleUtil.queryList(APPConfig.DB_MAIN, sqlMenu, MenuitemDBModel.class);

        for (MenuitemDBModel tempMenu : menuDBList) {
            MenuItem menuItem = buildMenuByDBModel(tempMenu, null);
            if (menuItem == null) {
                continue;
            }
            itemALl.add(menuItem);
            MenuTypeBean men = fullDataMap.get(tempMenu.fsMenuClsId);
            if (men == null) {
                continue;
            }
            if (TextUtils.equals(men.fsMenuClsId, men.fsMenuClsId_P)) {
                continue;
            }
            if (men.menuList == null) {
                men.menuList = new ArrayList<>();
            }
            men.menuList.add(menuItem);
            if ((menuItem.config & 128) == 128) {
                itemNew.add(menuItem);
            }
            if ((menuItem.config & 64) == 64) {
                itemSpecial.add(menuItem);
            }
        }
        //处理有二级分类三级分类的菜品类别，给二级分类、三级分类加上全部菜品列表
        appendAll(firstNodeMapTemp);

        if (!itemALl.isEmpty()) {
            MenuTypeBean typeAll = new MenuTypeBean();
            typeAll.menuList = itemALl;
            typeAll.fsMenuClsName = "全部";
            typeAll.fsMenuClsId = "-1localAll";
            typeAll.typeIndex = true;
            firstNodeList.add(typeAll);
        }
        if (!itemSpecial.isEmpty()) {
            MenuTypeBean typeSpecial = new MenuTypeBean();
            typeSpecial.menuList = itemSpecial;
            typeSpecial.fsMenuClsName = "招牌菜";
            typeSpecial.fsMenuClsId = SYSTEM_CLSID_SPECAIL + "";
            typeSpecial.typeIndex = true;
            firstNodeList.add(typeSpecial);

        }
        if (!itemNew.isEmpty()) {
            MenuTypeBean typeNewMenu = new MenuTypeBean();
            typeNewMenu.menuList = itemNew;
            typeNewMenu.fsMenuClsName = "新菜";
            typeNewMenu.fsMenuClsId = SYSTEM_CLSID_NEW + "";
            typeNewMenu.typeIndex = true;
            firstNodeList.add(typeNewMenu);
        }
        if (!firstNodeMapTemp.isEmpty()) {
            Iterator<Map.Entry<String, MenuTypeBean>> ite = firstNodeMapTemp.entrySet().iterator();
            while (ite.hasNext()) {
                firstNodeList.add(ite.next().getValue());
            }
        }
        return list;
    }

    private static void appendAll(LinkedHashMap<String, MenuTypeBean> fullDataMap) {
        Iterator<Map.Entry<String, MenuTypeBean>> ite = fullDataMap.entrySet().iterator();
        while (ite.hasNext()) {
            Map.Entry<String, MenuTypeBean> entry = ite.next();
            MenuTypeBean menuType = entry.getValue();
            if (menuType.sonTypeList != null && !menuType.sonTypeList.isEmpty()) {
                List<MenuItem> allSonMenu = appendSonList(menuType);
                menuType.menuList.addAll(allSonMenu);
            }
        }
    }

    private static List<MenuItem> appendSonList(MenuTypeBean type) {
        List<MenuItem> menuList = new ArrayList<>();
        if (type != null && !ListUtil.isEmpty(type.sonTypeList)) {
            for (MenuTypeBean temp : type.sonTypeList) {
                if (temp.menuList != null && !temp.menuList.isEmpty()) {
                    menuList.addAll(temp.menuList);
                }
                if (temp.sonTypeList != null && !temp.sonTypeList.isEmpty()) {
                    List<MenuItem> allSonMenu = appendSonList(temp);
                    temp.menuList.addAll(allSonMenu);
                    menuList.addAll(allSonMenu);
                }
            }
            MenuTypeBean indexModel = buildIndexMenuType(type.fsMenuClsId);
            indexModel.menuList.addAll(type.menuList);
            indexModel.menuList.addAll(menuList);
            type.sonTypeList.add(0, indexModel);
        }
        return menuList;
    }


    /**
     * 根据菜品ID获取菜品Model
     *
     * @param fiItemCd int
     * @return MenuitemDBModel
     */
    public static MenuitemDBModel queryMenuDBModel(int fiItemCd) {
        String sql = "select * from tbmenuitem where fiItemCd='" + fiItemCd + "' and fiStatus <> '13' ";
        return DBSimpleUtil.query(APPConfig.DB_MAIN, sql, MenuitemDBModel.class);
    }


}
