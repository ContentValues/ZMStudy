package xdroid.mwee.com.zmstudy.model.menu;

import com.mwee.android.tools.LogUtil;
import com.mwee.android.tools.base.BusinessBean;

import java.util.ArrayList;
import java.util.List;

import com.mwee.android.tools.ListUtil;

/**
 * MenuExtraItem
 * Created by virgil on 16/6/15.
 */
public class MenuExtra extends BusinessBean {
    /**
     * 唯一表示符
     */
    public String groupID = "";
    /**
     * 名称
     */
    public String name = "";
    /**
     * 类型,see{@link MenuExtraType}
     */
    public int type = MenuExtraType.ASK;

    /**
     *     是否固定配菜
     */
    public boolean isSolid=false;
    /**
     * 是否必选
     */
    public boolean isRequired = false;
    /**
     * 子列表
     */
    public List<MenuExtraItem> itemList = new ArrayList<>();

    /**
     * 套餐:第二名称
     */
    public String pacName2 = "";

    /**
     * 套餐最大可选菜品数量
     */
    public int pacFoodNum = 0;

    public MenuExtra() {
    }


    @Override
    public MenuExtra clone() {
        MenuExtra cloneObj = null;
        try {
            cloneObj = (MenuExtra) super.clone();
            if (itemList != null) {
                cloneObj.itemList = ListUtil.cloneList(itemList);
            }
        } catch (Exception e) {
            LogUtil.logError(e);
        }
        return cloneObj;
    }
}
