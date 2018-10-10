package xdroid.mwee.com.zmstudy.model.menu;

import android.text.TextUtils;

import com.mwee.android.tools.DateUtil;
import com.mwee.android.tools.UUIDUtil;
import com.mwee.android.tools.base.BusinessBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import xdroid.mwee.com.model.util.db.AskDBModel;
import xdroid.mwee.com.model.util.db.DiscountDBModel;
import com.mwee.android.tools.ListUtil;

/**
 * MenuBiz
 * Created by virgil on 16/6/16.
 */
public class MenuBiz extends BusinessBean {
    /**
     * 订单菜的唯一表示，必须赋值
     */
    public String uniq = "";
    /**
     * 订单菜的第三方唯一表示commitId
     */
    public String thirduniq = "";
    /**
     * 点菜批次，必须赋值
     */
    public int orderSeqID = 0;
    /**
     * 点的份数
     */
    public BigDecimal buyNum = BigDecimal.ZERO;


    /**
     * 当前菜品的已点总价, 算入口参 {@link MenuItem#calcTotal(boolean)}
     * 包括额外加的料的 用{@link MenuBiz#selectedPackageItem}
     */
    public BigDecimal totalPrice = BigDecimal.ZERO;

    /**
     * 额外的加料总价
     */
    public BigDecimal priceExtraTotal = BigDecimal.ZERO;
    public BigDecimal priceTotalOriginAfterGift = BigDecimal.ZERO;
    /**
     * 菜品的服务费
     */
    public BigDecimal priceService = BigDecimal.ZERO;

    /**
     * 折扣的金额
     */
    public BigDecimal discountAmount = BigDecimal.ZERO;
    /**
     * 是否打包
     */
    public boolean isTakeAway = false;
    /**
     * 菜品要求、备注
     */
    public String note = "";

    /**
     * 显著要求
     */
    public String fsSpecialNote = "";
    /**
     * 做法 + 普通要求
     */
    public String fsGeneralNote = "";

    /**
     * 选择的套餐，已弃用，当市面上没有用户使用2.1以下版本时放可删除
     */
    @Deprecated
    public List<MenuExtra> selectedPackageItem = null;

    /**
     * 快餐的做法，已弃用，2.3版本删除
     */
    @Deprecated
    public List<MenuExtraItem> selectProcedure = null;


    /**
     * 多做法  pro2.5新加
     */
    public List<AskDBModel> selectMulProcedure = new ArrayList<>();

    /**
     * 单品备注信息
     */
    public List<NoteItemModel> selectNote = new ArrayList<>();
    /**
     * 整单备注
     */
    public List<NoteItemModel> selectOrderNote = new ArrayList<>();

    /**
     * 套餐子项，或者做法
     */
    public String selectedExtraStr = "";

    /**
     * 选择的折扣方案
     */
    public DiscountDBModel selectDiscount = null;

    /**
     * 折扣理由
     */
    public String discountReason = "";


    /**
     * 折扣的UserID
     */
    public String discountUserID = "";
    /**
     * 折扣的UserName
     */
    public String discountUserName = "";

    /**
     * 菜品销售类型,see{@link MenuSellType}
     */
    public int menuSellType = MenuSellType.ADD;
    /**
     * 退菜和增菜的时间
     */
    public String menuSellTime = "";
    /**
     * 赠菜的时间
     */
    public String giftTime = "";
    /**
     * 退菜的时间
     */
    public String voidTime = "";

    /**
     * 赠菜的UserID
     */
    public String giftUserID = "";
    /**
     * 赠菜的UserName
     */
    public String giftUserName = "";
    /**
     * 赠菜的原因
     */
    public String giftReason = "";

    /**
     * 送的份数
     */
    public BigDecimal giftNum = BigDecimal.ZERO;
    /**
     * 退的份数
     */
    public BigDecimal voidNum = BigDecimal.ZERO;

    /**
     * 退菜的UserID
     */
    public String voidUserID = "";
    /**
     * 退菜的UserName
     */
    public String voidUserName = "";
    /**
     * 退菜理由
     */
    public String voidReson = "";

    public String createTime = "";
    /**
     * 上菜状态;默 1; 1一般即起 / 2等叫(叫起) / 3起菜 / 4划菜 / 8打包 /9取消(退菜数量=销售数量，消除此菜)
     * 注：划菜不用这个字段，会跟其它冲突。使用：用{@link MenuBiz#fiMenuProgress}
     */
    public int fiItemMakeState = 0;


    /**
     * 催菜次数
     */
    public int fiHurryTimes = 0;

    /**
     * 划菜：0=未上菜，1=已上菜。
     */
    public int fiMenuProgress = 0;

    /**
     * 时价菜更改次数
     */
    public int currentPriceTimes = 0;

    /**
     * 已选的配料
     */
    public List<MenuItem> selectedModifier = new ArrayList<>();

    /**
     * 已选的套餐
     */
    public List<MenuItem> selectedPackageItems = new ArrayList<>();


    /**
     * 默认选中(套餐用)
     */
    @Deprecated
    public boolean isDefault = false;
    /**
     * 套餐分账折扣金额
     */
    public BigDecimal packageSplitDiscount = BigDecimal.ZERO;
    /**
     * 套餐分账折前金额
     */
    public BigDecimal packageSplitOrigin = BigDecimal.ZERO;
    /**
     * 套餐分账的最终价格
     */
    public BigDecimal packageSplitPrice = BigDecimal.ZERO;

    /**
     * 商品所在的口袋，0为1号口袋，1为2号口袋，以此类推
     */
    public int pokeNo = 0;

    /**
     * 业务属性：
     * 1，当前菜是特价
     * 2，当前菜是买送
     * 4，且启用了套餐分账
     * 8, 外卖菜品尚未映射成功
     * 16，当前是会员价
     * 32. 非会员使用会员价
     */
    public int bizConfig = 0;

    public MenuBiz() {

    }

    public void generateUniq() {
        uniq = UUIDUtil.optUUID();
        if (TextUtils.isEmpty(createTime)) {
            createTime = DateUtil.getCurrentTime();
        }
    }

    /**
     * 是否非会员使用了会员价
     *
     * @return boolean
     */
    public boolean isMemPriceWithouMem() {
        return (bizConfig & 32) == 32;
    }

    /**
     * 是否是特价菜
     *
     * @return boolean
     */
    public boolean checkSpecialPrice() {
        return (bizConfig & 1) == 1;
    }

    /**
     * 是否使用了会员价
     *
     * @return boolean
     */
    public boolean checkMemberPrice() {
        return (bizConfig & 16) == 16;
    }

    /**
     * 添加业务设置
     *
     * @param value int
     */
    public void addConfig(int value) {
        bizConfig = bizConfig | value;
    }

    /**
     * 减去业务设置
     *
     * @param value int
     */
    public void decreasConfig(int value) {
        bizConfig &= ~value;
    }



    @Override
    public MenuBiz clone() {
        MenuBiz cloneObj = null;
        try {
            cloneObj = (MenuBiz) super.clone();
            if (selectedPackageItem != null) {
                cloneObj.selectedPackageItem = ListUtil.cloneList(selectedPackageItem);
            }
            if (selectNote != null) {
                cloneObj.selectNote = ListUtil.cloneList(selectNote);
            }
            if (selectOrderNote != null) {
                cloneObj.selectOrderNote = ListUtil.cloneList(selectOrderNote);
            }
            if (selectedModifier != null) {
                cloneObj.selectedModifier = ListUtil.cloneList(selectedModifier);
            }
            if (selectedPackageItems != null) {
                cloneObj.selectedPackageItems = ListUtil.cloneList(selectedPackageItems);
            }
            if (selectProcedure != null) {
                cloneObj.selectProcedure = ListUtil.cloneList(selectProcedure);
            }
            if (selectMulProcedure != null) {
                cloneObj.selectMulProcedure = ListUtil.cloneList(selectMulProcedure);
            }
            if (selectDiscount != null) {
                cloneObj.selectDiscount = selectDiscount.clone();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}
