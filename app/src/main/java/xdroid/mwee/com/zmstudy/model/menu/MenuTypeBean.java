package xdroid.mwee.com.zmstudy.model.menu;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜品分类表
 * Created by virgil on 2016/10/17.
 */
@TableInf(name = "tbMenuCls")
public class MenuTypeBean extends DBModel {
    @ColumnInf(name = "fiMenuClsKind")
    public int fiMenuClsKind = 0;//--类型;1单品类、2套餐类
    @ColumnInf(name = "fsMenuClsId")
    public String fsMenuClsId = "";//--菜单分类代码
    @ColumnInf(name = "fsMenuClsName")
    public String fsMenuClsName = "";//--菜单分类名称
    @ColumnInf(name = "fsShopGUID")
    public String fsShopGUID = "";
    @ColumnInf(name = "fsMenuClsId_P")
    public String fsMenuClsId_P = "";//--父层菜单分类代码
    @ColumnInf(name = "fsRevenueTypeId")
    public String fsRevenueTypeId = "";//--收入分类代码
    @ColumnInf(name = "fsExpClsId")
    public String fsExpClsId = "";//--销售分类代码
    public boolean typeIndex=false;
    /**
     * 子分类列表
     */
    public List< MenuTypeBean> sonTypeList = null;
    /**
     * 菜品列表
     */
    public List<MenuItem> menuList = new ArrayList<>();

    public MenuTypeBean() {

    }
}
