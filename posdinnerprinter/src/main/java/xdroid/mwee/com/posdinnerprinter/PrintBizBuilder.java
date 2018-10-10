package xdroid.mwee.com.posdinnerprinter;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.mwee.android.print.base.PrinterConfig;
import com.mwee.android.print.processor.PrintBillBuilder;
import com.mwee.android.print.processor.PrintDataItem;
import com.mwee.android.print.processor.PrintStringUtil;
import com.mwee.android.tools.JsonUtil;
import com.mwee.android.tools.StringUtil;

import java.io.UnsupportedEncodingException;

/**
 * Created by virgil on 16/9/13.
 */
public class PrintBizBuilder extends PrintBillBuilder {
    public PrintBizBuilder(PrinterConfig config) {
        super(config);
    }


    public void addOrderNoAndBillNo(JSONObject sell) {
        String orderID = JsonUtil.getInfo(sell, "fssellno", String.class);
        orderID = orderID.substring(orderID.length() - 4, orderID.length());
        String orderStr = "单号:" + orderID;
        String mealNO = JsonUtil.getInfo(sell, "fsMealNumber", String.class);
        int mealNoInt = TextUtils.equals(mealNO, "无") ? -1 : StringUtil.toInt(mealNO, -1);
        String mealNoStr = "牌号:" + mealNO;
        try {
            if (mealNoInt > -1) {
                this.addText(PrintStringUtil.padRight(orderStr, this.charSize - mealNoStr.getBytes("GBK").length * 2, this.gbkSize));
                this.addText(mealNoStr + "\n", 2, PrintDataItem.ALIGN_RIGHT, 0);
            } else {
                this.addText(orderStr + "\n", 2, PrintDataItem.ALIGN_LEFT, 1);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void addOrderNoTableNo(String orderID, String tableNO) {
        tableNO = tableNO.replace("\n", "");
        String orderStr = "单号:" + orderID;
        String tableNOStr = "台位:";
        try {
            this.addText(tableNOStr, 1, PrintDataItem.ALIGN_LEFT, 0,1);
            this.addText(tableNO, 2, PrintDataItem.ALIGN_LEFT, 0,1);

            this.addText(PrintStringUtil.padLeft(orderStr, this.charSize - tableNOStr.getBytes("GBK").length - tableNO.getBytes("GBK").length * 2, this.gbkSize)+"\n",1, PrintDataItem.ALIGN_RIGHT,0);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void addLeftWithRightBig(String leftString, String rightSmall, String rightBig) {
        rightBig = rightBig.replace("\n", "");
        try {
            this.addText(PrintStringUtil.padRight(leftString, this.charSize - rightSmall.getBytes("GBK").length - rightBig.getBytes("GBK").length * 2, this.gbkSize));
            this.addText(rightSmall, 1, PrintDataItem.ALIGN_RIGHT, 0);
            this.addText(rightBig + "\n", 2, PrintDataItem.ALIGN_RIGHT, 0);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void addOrderNoTableNo(String orderID, String mealLabel, String mealNO) {
        String orderStr = "单号:" + orderID;
        String mealNoStr = mealLabel;
        try {
            this.addText(PrintStringUtil.padRight(orderStr, this.charSize - mealNoStr.getBytes("GBK").length - mealNO.getBytes("GBK").length * 2, this.gbkSize));
            this.addText(mealNoStr, 1, PrintDataItem.ALIGN_RIGHT, 0);
            this.addText(mealNO + "\n", 2, PrintDataItem.ALIGN_RIGHT, 0);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void addMealNoWaiterName(String leftLabel, String tableName, String rightLabel, String waiterName) {
        String orderStr = leftLabel + tableName;
        try {
            this.addText(PrintStringUtil.padRight(orderStr, this.charSize - rightLabel.getBytes("GBK").length - waiterName.getBytes("GBK").length * 2, this.gbkSize));
            this.addText(rightLabel, 1, PrintDataItem.ALIGN_RIGHT, 0);
            this.addText(waiterName + "\n", 2, PrintDataItem.ALIGN_RIGHT, 0);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void addTypeCount(String type, String count) {
        String typeStr = "餐段:" + type;
        String countStr = "人数:";
        try {
            this.addText(PrintStringUtil.padRight(typeStr, this.charSize - countStr.getBytes("GBK").length - count.getBytes("GBK").length * 2, this.gbkSize));
            this.addText(countStr, 1, PrintDataItem.ALIGN_RIGHT, 0);
            this.addText(count + "\n", 2, PrintDataItem.ALIGN_RIGHT, 0);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void add1212(String startTableNo, String targetTableNo, String gapLabel) {
        String label1Str = "台位:";
        try {
            int gap = this.charSize - label1Str.getBytes("GBK").length -
                    -startTableNo.getBytes("GBK").length * 2 - targetTableNo.getBytes("GBK").length * 2;
            this.addText(label1Str, 1, PrintDataItem.ALIGN_RIGHT, 0);
            this.addText(startTableNo, 2, PrintDataItem.ALIGN_RIGHT, 0);
            if (gap > gapLabel.getBytes("GBK").length) {
                this.addText(PrintStringUtil.padCenter(gapLabel, gap, 1, true, " ", this.gbkSize));
            } else {
                this.addText(gapLabel, 1, PrintDataItem.ALIGN_RIGHT, 0);
            }
            this.addText(targetTableNo + "\n", 2, PrintDataItem.ALIGN_RIGHT, 0);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void add1111(String startLabel, String str1, String str2, String gapLabel) {
        try {
            int gap = this.charSize - startLabel.getBytes("GBK").length -
                    -str1.getBytes("GBK").length - str2.getBytes("GBK").length;
            this.addText(startLabel, 1, PrintDataItem.ALIGN_RIGHT, 0);
            this.addText(str1, 1, PrintDataItem.ALIGN_RIGHT, 0);
            if (gap > gapLabel.getBytes("GBK").length) {
                this.addText(PrintStringUtil.padCenter(gapLabel, gap, 1, true, " ", this.gbkSize));
            } else {
                this.addText(gapLabel, 1, PrintDataItem.ALIGN_RIGHT, 0);
            }
            this.addText(str2 + "\n", 1, PrintDataItem.ALIGN_RIGHT, 0);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    public void addOrderItemNoMargin(String itemName, String price, String qty, String total, int fontSize) {
        PrintDataItem order = new PrintDataItem();
        if (forceSize1) {
            fontSize = 1;
        }
        order.dataFormat = 10;
        order.fontsize = fontSize;
        order.language = PrintDataItem.LANG_CN;
        order.text = getFourColContent(itemName, price, qty, total, fontSize);
        order.textAlign = 0;
        this.data.add(order);
    }

}
