package xdroid.mwee.com.zmstudy.model.menu;

import android.text.TextUtils;
import com.mwee.android.tools.DateUtil;
import com.mwee.android.tools.LogUtil;
import com.mwee.android.tools.base.BusinessBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.mwee.android.tools.ListUtil;

/**
 * Created by liuxiuxiu on 2017/3/21.
 * 要求内容
 */

public class NoteItemModel extends BusinessBean {
    /**
     * 唯一表示符,当是菜品的时候，这个是菜品ID，当时要求的时候，这个是要求ID
     */
    public int id = 0;
    public String groupIDFather = "";
    public String uniq = "";
    /**
     * 名称
     */
    public String name = "";
    /**
     * 单价
     */
    public BigDecimal price = BigDecimal.ZERO;
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
     * 菜品要求是否显著显示 0:否,1:是
     */
    public int fiIsShow = 0;

    public NoteItemModel() {
        this.uniq = UUID.randomUUID().toString();
        this.createTime = DateUtil.getCurrentTime();
    }

    /**
     * 计算总价
     */
    public void calcTotal() {
        BigDecimal calcUnitPrice = price;
        totalPrice = calcUnitPrice.multiply(num);
    }

    /**
     * 创建自定义备注
     * @param name String | 名称
     * @param num int | 数量
     * @param totalPrice BigDecimal | 总价
     */
    public static NoteItemModel createCustomeNote(String name, int num, BigDecimal totalPrice) {
        if (!TextUtils.isEmpty(name) && name.length() <= 30) {
            if (!TextUtils.equals(name, "没有")
                    && !TextUtils.equals(name, "无")
                    && !TextUtils.equals(name, "不需要")
                    && !TextUtils.equals(name, "不用了")) {
                if (num < 0) {
                    num = 0;
                }
                NoteItemModel noteModel = new NoteItemModel();
                noteModel.id = -1;
                noteModel.groupIDFather = "";
                noteModel.name = "顾客:"+name;
                noteModel.selected = true;
                noteModel.totalPrice = totalPrice;
                noteModel.num = new BigDecimal(num);
                //由总价计算单价，如果总价或者数量异常，则单价直接赋值为0
                if (totalPrice != null && num > 0) {
                    noteModel.price = totalPrice.divide(noteModel.num, 2, BigDecimal.ROUND_HALF_UP);
                } else {
                    noteModel.price = BigDecimal.ZERO;
                }
                noteModel.fiIsShow = 0;
                return noteModel;
            }
        }
        return null;
    }

    @Override
    public NoteItemModel clone() {
        NoteItemModel cloneObj = null;
        try {
            cloneObj = (NoteItemModel) super.clone();
            if (price != null) {
                cloneObj.price = new BigDecimal(price.toString());
            }
            if (num != null) {
                cloneObj.num = new BigDecimal(num.toString());
            }
            if (totalPrice != null) {
                cloneObj.totalPrice = new BigDecimal(totalPrice.toString());
            }
        } catch (Exception e) {
            LogUtil.logError(e);
        }
        return cloneObj;
    }

    @Override
    public String toString() {
        return name + " " + price;
    }

    public int valueAt(List<NoteItemModel> itemModelList) {
        if (ListUtil.isEmpty(itemModelList)) {
            return -1;
        }
        for (int index = 0; index < itemModelList.size(); index++) {
            NoteItemModel model = itemModelList.get(index);
            if (this == model) {
                return index;
            }
            if (this.id == model.id) {
                return index;
            }
        }
        return -1;
    }
}

