package xdroid.mwee.com.posdinnerprinter.business;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mwee.android.print.base.PrinterConfig;
import com.mwee.android.print.processor.PrintDataItem;
import com.mwee.android.print.processor.PrintResult;
import com.mwee.android.print.processor.PrintStringUtil;
import com.mwee.android.tools.DateUtil;
import com.mwee.android.tools.JsonUtil;

import java.math.BigDecimal;

import xdroid.mwee.com.mwdriverbus.DriverMethod;
import xdroid.mwee.com.mwdriverbus.IDriver;
import xdroid.mwee.com.posdinnerprinter.PrintBizBuilder;
import xdroid.mwee.com.posdinnerprinter.print.PrintTaskDBModel;
import xdroid.mwee.com.posdinnerprinter.print.PrintUtil;
import xdroid.mwee.com.posdinnerprinter.task.DinnerPrintProcessor;

/**
 * OrderCommandProcessor
 * Created by virgil on 16/8/9.
 */
@SuppressWarnings("unused")
public class OrderCommandProcessor implements IDriver {
    private final static String DRIVER_TAG = "order";


    /**
     * 预结单
     *
     * @param ob
     * @param taskModel
     * @param config
     * @return
     */
    @DriverMethod(uri = DRIVER_TAG + "/prebill")
    public static PrintResult processPreBill(JSONObject ob, PrintTaskDBModel taskModel, PrinterConfig config) {
        PrintBizBuilder billPrint = new PrintBizBuilder(config);
        JSONObject shop = JsonUtil.getInfo(ob, "Shop", JSONObject.class);

        billPrint.addTitle(JsonUtil.getInfo(shop, "fsShopName", String.class));
        billPrint.addLine();

        String titleRemind = taskModel.titleRemind;
        if (TextUtils.isEmpty(titleRemind)) {
            titleRemind = "";
        }
        billPrint.addTitle("预结单" + titleRemind);
        billPrint.addLine();
        JSONObject sell = JsonUtil.getInfo(ob, "Sell", JSONObject.class);
        billPrint.addOrderNoTableNo(JsonUtil.getInfo(sell, "fssellno", String.class) + "", JsonUtil.getInfo(sell, "fsMTableName", String.class));
        billPrint.addLeftWithRight("开单:" + JsonUtil.getInfo(sell, "fsCreateUserName", String.class), "人数:" + JsonUtil.getInfo(sell, "fiCustSum", String.class));
        billPrint.addLeftWithRight("日期:" + JsonUtil.getInfo(sell, "fsselldate", String.class), "");
        billPrint.addHortionaDoublelLine();
        billPrint.addOrderItem(
                "菜名",
                "单价",
                "数量",
                "总计", 1);
        JSONArray list = JsonUtil.getInfo(ob, "sellorder", JSONArray.class);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                JSONObject item = list.getJSONObject(i);
                billPrint.addOrderItem(JsonUtil.getInfo(item, "fsItemName", String.class),
                        JsonUtil.getInfo(item, "fdSettlePrice", String.class),
                        JsonUtil.getInfo(item, "qty", String.class),
                        JsonUtil.getInfo(item, "fdsaleamt", String.class),
                        1);
            }
        }
        billPrint.addHortionalLine();
        JSONObject sub = JsonUtil.getInfo(ob, "Sub", JSONObject.class);
        billPrint.addSub("消费合计", JsonUtil.getInfo(sub, "qty", String.class), "￥" + JsonUtil.getInfo(sub, "total", String.class));
        billPrint.addSub("折扣", "", "￥" + JsonUtil.getInfo(ob, "fddiscountamt", String.class));
        //圆整
        String fdRoundAmt = JsonUtil.getInfo(sell, "fdroundamt", String.class);
        if (!TextUtils.isEmpty(fdRoundAmt) && !TextUtils.equals(fdRoundAmt, "0")) {
            billPrint.addSub("圆整", "", "￥" + fdRoundAmt);
        }
        //服务费
        String serviceFee = JsonUtil.getInfo(sell, "fdServiceAmt", String.class);

        if (!TextUtils.isEmpty(serviceFee) && !TextUtils.equals(serviceFee, "0")) {
            try {
                BigDecimal serviceAmt = new BigDecimal(serviceFee);
                if (serviceAmt.compareTo(BigDecimal.ZERO) > 0) {
                    billPrint.addSub("服务费", "", "￥" + serviceFee);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        billPrint.addSub("应收", "", "￥" + JsonUtil.getInfo(sell, "fdExpAmt", String.class));
        billPrint.addHortionalLine();

        String nonDisAmt = JsonUtil.getInfo(sub, "NonDisAmt", String.class);
        if (!TextUtils.isEmpty(nonDisAmt)) {
            billPrint.addText(PrintStringUtil.padRight("可折扣金额:" + JsonUtil.getInfo(sell, "fdDisExpAmt", String.class), billPrint.charSize / 2, billPrint.gbkSize) + PrintStringUtil.padLeft("不可折扣金额:" + nonDisAmt + "\n", billPrint.charSize / 2, billPrint.gbkSize), 1, 0, 0);
            billPrint.addHortionalLine();
        }

        billPrint.addText("备注:" + JsonUtil.getInfo(sell, "fsnote", String.class) + "\n");

        billPrint.addLeftWithRight("打印时间：" + DateUtil.getCurrentDate(), "印号:" + taskModel.fiPrintNo);
        String note = JsonUtil.getInfo(ob, "note", String.class);
        if (!TextUtils.isEmpty(note)) {
            billPrint.addCenterText(note + "\n", 1);
            billPrint.addBlankLine();
        }
        String tail = JsonUtil.getString(ob, "reportTail");
        if (!TextUtils.isEmpty(tail)) {
            billPrint.addCenterText(tail + "\n");
        }

        billPrint.addBlankLine(1);

        billPrint.addCut();

        return DinnerPrintProcessor.submitToPrinter(taskModel, billPrint.data, config);
    }


    /**
     * 制作单
     *
     * @param ob        JSONObject
     * @param taskModel PrintTaskDBModel
     * @param config    PrinterConfig
     * @return PrintResult
     */
    @DriverMethod(uri = DRIVER_TAG + "/make")
    public static PrintResult processMake(JSONObject ob, PrintTaskDBModel taskModel, PrinterConfig config) {
        PrintBizBuilder billPrint = new PrintBizBuilder(config);
        JSONObject dept = JsonUtil.getInfo(ob, "Dept", JSONObject.class);
        String titleRemind = taskModel.titleRemind;
        if (TextUtils.isEmpty(titleRemind)) {
            titleRemind = "";
        }
        billPrint.addTitle("(总单)${Dept.fsDeptName}制作单".replace("${Dept.fsDeptName}", JsonUtil.getString(dept, "fsDeptName")) + titleRemind);

        JSONObject sell = JsonUtil.getInfo(ob, "Sell", JSONObject.class);

        String phone = JsonUtil.getString(sell, "fsCustMobile");
        if (!TextUtils.isEmpty(phone)) {
            billPrint.addBlankLine();
            billPrint.addText("尾号" + phone + "的预订顾客已到店下单\n", 1, PrintDataItem.ALIGN_CENTRE, 0);
        }

        billPrint.addLine();

        String orderType = "";
        String fillKind = JsonUtil.getString(sell, "fiBillKind");
        switch (fillKind) {
            case "8":
                orderType = "打包";
                break;
            case "9":
                orderType = "外卖";
                break;
        }
        if (!TextUtils.isEmpty(orderType)) {
            billPrint.addText(orderType + "\n", 2);
        }

        String eatType = JsonUtil.getString(ob, "eatType");
        if (!TextUtils.isEmpty(eatType)) {
            billPrint.addText(eatType + "\n", 2);
        }
        billPrint.addOrderNoTableNo(JsonUtil.getString(sell, "fssellno") + "", JsonUtil.getString(sell, "fsMTableName") + "");
        billPrint.addBlankLine();
        billPrint.addText("人数:" + JsonUtil.getString(sell, "fiCustSum") + "\n", 1);

        billPrint.addHortionaDoublelLine();
        JSONArray list = JsonUtil.getInfo(ob, "SellOrder", JSONArray.class);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                JSONObject item = list.getJSONObject(i);
                String fiOrderItemKind = JsonUtil.getString(item, "fiOrderItemKind");
                if (TextUtils.equals("2", fiOrderItemKind)) {  //不打印套餐头
                    continue;
                }
                String fsSpecialNote = JsonUtil.getString(item, "fsSpecialNote");
                String left = fsSpecialNote + JsonUtil.getString(item, "fsItemName");
                String waitInfo = JsonUtil.getString(item, "SfiItemMakeState");
                String right = PrintUtil.optBuyNumAndUnit(JsonUtil.getString(item, "fdSaleQty"), JsonUtil.getString(item, "fsOrderUint"));
                billPrint.addItemNameWithUnit(left, right, 2);
                billPrint.addBlankLine(1);

                // 等叫
                if (!TextUtils.isEmpty(waitInfo)) {
                    billPrint.addText("  " + waitInfo + "\n", 2);
                }

                if (TextUtils.equals("3", fiOrderItemKind)) {  //套餐明细要打印出所属的套餐头
                    String parentItemName = JsonUtil.getString(item, "parentItemName");
                    if (!TextUtils.isEmpty(parentItemName)) {
                        billPrint.addOrderModifier("-(属于)" + parentItemName, 2);
                    }
                }

                String note = JsonUtil.getString(item, "fsGeneralNote");
                if (!TextUtils.isEmpty(note)) {
                    billPrint.addOrderModifier(note, 1);
                }
                String ingredientNote = JsonUtil.getString(item, "ingredientNotes");
                if (!TextUtils.isEmpty(ingredientNote)) {
                    String[] ingredientArr = ingredientNote.split("\n");
                    for (String ingredient : ingredientArr) {
                        billPrint.addOrderModifier(ingredient.replace("\n", ""), 1);
                    }
                }
                billPrint.addBlankLine(1);
            }
        }
        billPrint.addHortionalLine();
        billPrint.addBlankLine(1);
        billPrint.addLeftWithRight("下单人" + JsonUtil.getString(ob, "fsCreateUserName"), "部门" + JsonUtil.getString(dept, "fsDeptName"));
        billPrint.addBlankLine(1);
        billPrint.addLeftWithRight("打印时间：" + DateUtil.getCurrentDate(), "印号:" + taskModel.fiPrintNo);
        billPrint.addBlankLine(1);

        String barCode = JsonUtil.getInfo(ob, "barCode", String.class);
        if (!TextUtils.isEmpty(barCode)) {
            billPrint.addBarCod(barCode, PrintDataItem.ALIGN_CENTRE);
            billPrint.addBlankLine(1);
        }

        billPrint.addCut();
        String beep = JsonUtil.getString(ob, "beep");
        if (TextUtils.equals("1", beep)) {
            billPrint.addBeep();
        }

        return DinnerPrintProcessor.submitToPrinter(taskModel, billPrint.data, config);

    }

    @Override
    public String getModuleName() {
        return DRIVER_TAG;
    }
}
