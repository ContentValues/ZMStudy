package xdroid.mwee.com.zmstudy.model.menu;

import com.mwee.android.tools.DateUtil;
import com.mwee.android.tools.LogUtil;
import com.mwee.android.tools.base.BusinessBean;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * MenuExtraItem
 * Created by virgil on 16/6/15.
 */
public class MenuExtraItem extends BusinessBean {
    /**
     * 唯一表示符,当是菜品的时候，这个是菜品ID，当时要求的时候，这个是要求ID
     */
    public int id = 0;
    public String groupIDFather = "";
    public String uniq = "";
    /**
     * 当是菜品的时候，这个是对应的规格ID
     */
    public int unitID = 0;
    /**
     * 名称
     */
    public String name = "";
    /**
     * 单价
     */
    public BigDecimal price = BigDecimal.ZERO;
    /**
     * 可选套餐的加价
     */
    public BigDecimal increasePrice = BigDecimal.ZERO;
    /**
     * 可选套餐的会员加价
     */
    public BigDecimal fdVIPPrice = BigDecimal.ZERO;
    /**
     * 默认选中
     */
    public boolean isDefault = false;
    /**
     * 是否已被选中
     */
    public boolean selected = false;

    /**
     * 选中的数量/默认基数
     */
    public BigDecimal num = BigDecimal.ZERO;

    /**
     * 计算的总价(目前套餐不支持单个菜品定价)
     */
    public BigDecimal totalPrice = BigDecimal.ZERO;
    public String createTime = "";

    /**
     * 储存默认基数,用于套餐选择时
     */
    public BigDecimal tempNum = BigDecimal.ZERO;

    public MenuExtraItem() {

    }

    /**
     * 计算总价
     */
    public void calcTotal(boolean isMember) {

        BigDecimal calcUnitPrice = increasePrice.add(price);
        if (isMember && fdVIPPrice != null && fdVIPPrice.compareTo(BigDecimal.ZERO) > 0){
            calcUnitPrice = fdVIPPrice.add(price);
        }
        totalPrice = calcUnitPrice.multiply(num);
    }

    @Override
    public MenuExtraItem clone() {
        MenuExtraItem cloneObj = null;
        try {
            cloneObj = (MenuExtraItem) super.clone();
            if (price != null) {
                cloneObj.price = new BigDecimal(price.toString());
            }
            if (num != null) {
                cloneObj.num = new BigDecimal(num.toString());
            }
            if (tempNum != null) {
                cloneObj.tempNum = new BigDecimal(tempNum.toString());
            }
            if (totalPrice != null) {
                cloneObj.totalPrice = new BigDecimal(totalPrice.toString());
            }
        } catch (Exception e) {
            LogUtil.logError(e);
        }
        return cloneObj;
    }
}
