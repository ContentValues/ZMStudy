package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbmenuitem")
public class MenuitemDBModel extends DBModel {
    @ColumnInf(name = "fiIsEditQty")
    public int fiIsEditQty = 0;
    @ColumnInf(name = "fsCreateUserName")
    public String fsCreateUserName = "";
    @ColumnInf(name = "fiImgWidth")
    public int fiImgWidth = 0;
    @ColumnInf(name = "fsCreateUserId")
    public String fsCreateUserId = "";
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fsShopGUID")
    public String fsShopGUID = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fiIsSet")
    public int fiIsSet = 0;//只属套餐(0=否/1=是)
    /**
     * '套餐打印部门；1套餐头部门/ 2套餐明细部门 ',
     */
    @ColumnInf(name = "fiIsSetDtlPrn")
    public int fiIsSetDtlPrn = 0;
    //--套餐计价方式；1固定价格 2合计价格 ;套餐头用
    @ColumnInf(name = "fiItemSetCalc")
    public int fiItemSetCalc = 0;
    @ColumnInf(name = "fiSortOrder")
    public int fiSortOrder = 0;
    @ColumnInf(name = "fsItemId")
    public String fsItemId = "";
    @ColumnInf(name = "fsItemDesc")
    public String fsItemDesc = "";
    @ColumnInf(name = "fsItemName")
    public String fsItemName = "";
    /**
     * --菜品种类；1单品 2套餐头,4 配料
     */
    @ColumnInf(name = "fiItemKind")
    public int fiItemKind = 0;
    @ColumnInf(name = "fiItemCd", primaryKey = true)
    public int fiItemCd = 0;
    @ColumnInf(name = "fiIsSpecialty")
    public int fiIsSpecialty = 0;
    @ColumnInf(name = "fsImageURL")
    public String fsImageURL = "";
    @ColumnInf(name = "fsColor")
    public String fsColor = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "fsItemName2")
    public String fsItemName2 = "";
    @ColumnInf(name = "fiImgHeight")
    public int fiImgHeight = 0;
    @ColumnInf(name = "fsCreateTime")
    public String fsCreateTime = "";
    @ColumnInf(name = "fiIsServiceFee")
    public int fiIsServiceFee = 0;
    @ColumnInf(name = "fsDeptId")
    public String fsDeptId = "";   //制作部门ID
    /**
     * --是否后厨打印；0=false/1=true，指打印传菜部、制作部; 默认1
     */
    @ColumnInf(name = "fiIsPrn")
    public int fiIsPrn = 0;
    @ColumnInf(name = "fiIsDiscount")
    public int fiIsDiscount = 0;
    @ColumnInf(name = "fiIsGift")
    public int fiIsGift = 0;
    @ColumnInf(name = "fiIsEditPrice")
    public int fiIsEditPrice = 0;
    @ColumnInf(name = "fsImagePath")
    public String fsImagePath = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fiIsOut")
    public int fiIsOut = 0;
    @ColumnInf(name = "fiIsNew")
    public int fiIsNew = 0;
    /**
     * 当菜品是单品或者套餐头的时候（fiItemKind = 1，2），菜单分类代码来自tbmenucls表
     * 当菜品是配料的时候（fiItemKind = 4）
     */
    @ColumnInf(name = "fsMenuClsId")
    public String fsMenuClsId = "";   //菜单分类代码
    @ColumnInf(name = "fsHelpCode")
    public String fsHelpCode = "";
    @ColumnInf(name = "fsNote")
    public String fsNote = "";
    @ColumnInf(name = "fiIsTakeAway")
    public int fiIsTakeAway = 0;//是否外卖
    @ColumnInf(name = "fiIsBonus")
    public int fiIsBonus = 0;//是否提成
    @ColumnInf(name = "fiIsHot")
    public int fiIsHot = 0;//热门菜(0=否/1=是)

    @ColumnInf(name = "fiIsMulDept")
    public int fiIsMulDept = 0;//是否为多制作部门(0=否/1=是)
    @ColumnInf(name = "fiIsCuisine")
    public int fiIsCuisine = 0;//是否必选做法

    @ColumnInf(name = "fiIsEffectiveDate")
    public int fiIsEffectiveDate = 0;//时效限制 0不限制 1限制
    @ColumnInf(name = "fsStarDate")
    public String fsStarDate = "";//开始时间
    @ColumnInf(name = "fsEndDate")
    public String fsEndDate = "";//结束时间
    @ColumnInf(name = "fiMax")
    public int fiMax = 0;//配料菜最大可点份数


    @ColumnInf(name = "fimultipractice")
    public int fimultipractice = 0; //做法多选 0:未选中,1:多选

    @ColumnInf(name = "fipracticemin")
    public int fipracticemin = 1;   //'做法最小'

    @ColumnInf(name = "fipracticemax")
    public int fipracticemax = 1;   //'做法最大'


    @ColumnInf(name = "sync")
    public int sync = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;
    /**
     * 是否可手机点菜：0，不可以；1，可以
     */
    @ColumnInf(name = "fiIsWechatOrder")
    public int fiIsWechatOrder = 1;
    /**
     * 套餐时，是否需要套餐分账：1，开启分账
     */
    @ColumnInf(name = "fiSplitStatus")
    public int fiSplitStatus = 0;

    /**
     * '分账模式:0:按金额比例,1:按分账金额,2:按分账比例'
     */
    @ColumnInf(name = "fiLedgerMode")
    public int fiLedgerMode = 0;

    /**
     * 销售分类
     */
    @ColumnInf(name = "fsExpClsId")
    public String fsExpClsId = "";
    /**
     * 收入分类
     */
    @ColumnInf(name = "fsRevenueTypeId")
    public String fsRevenueTypeId = "";

    /**
     * 是否纯收银的菜品 0：否；1：是
     */
    @ColumnInf(name = "fiIsPurePay")
    public int fiIsPurePay = 0;

    /**
     * 是否是临时菜：0=false/1=true
     */
    @ColumnInf(name = "fiIsTemporaryMenu")
    public int fiIsTemporaryMenu = 0;

    public MenuitemDBModel() {

    }

    @Override
    public MenuitemDBModel clone() {
        MenuitemDBModel cloneObj = null;
        try {
            cloneObj = (MenuitemDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }

    @Override
    public String toString() {
        return "MenuitemDBModel{" +
                "fiIsEditQty=" + fiIsEditQty +
                ", fsCreateUserName='" + fsCreateUserName + '\'' +
                ", fiImgWidth=" + fiImgWidth +
                ", fsCreateUserId='" + fsCreateUserId + '\'' +
                ", fsUpdateUserName='" + fsUpdateUserName + '\'' +
                ", fsShopGUID='" + fsShopGUID + '\'' +
                ", fiStatus=" + fiStatus +
                ", fiIsSetDtlPrn=" + fiIsSetDtlPrn +
                ", fiSortOrder=" + fiSortOrder +
                ", fsItemId='" + fsItemId + '\'' +
                ", fsItemDesc='" + fsItemDesc + '\'' +
                ", fsItemName='" + fsItemName + '\'' +
                ", fiItemKind=" + fiItemKind +
                ", fiItemCd=" + fiItemCd +
                ", fiIsSpecialty=" + fiIsSpecialty +
                ", fsImageURL='" + fsImageURL + '\'' +
                ", fsColor='" + fsColor + '\'' +
                ", fsUpdateUserId='" + fsUpdateUserId + '\'' +
                ", fsItemName2='" + fsItemName2 + '\'' +
                ", fiImgHeight=" + fiImgHeight +
                ", fsCreateTime='" + fsCreateTime + '\'' +
                ", fiIsServiceFee=" + fiIsServiceFee +
                ", fsDeptId='" + fsDeptId + '\'' +
                ", fiIsPrn=" + fiIsPrn +
                ", fiIsDiscount=" + fiIsDiscount +
                ", fiIsGift=" + fiIsGift +
                ", fiIsEditPrice=" + fiIsEditPrice +
                ", fsImagePath='" + fsImagePath + '\'' +
                ", fsUpdateTime='" + fsUpdateTime + '\'' +
                ", fiIsOut=" + fiIsOut +
                ", fiIsNew=" + fiIsNew +
                ", fsMenuClsId='" + fsMenuClsId + '\'' +
                ", fsHelpCode='" + fsHelpCode + '\'' +
                ", fsNote='" + fsNote + '\'' +
                '}';
    }
}