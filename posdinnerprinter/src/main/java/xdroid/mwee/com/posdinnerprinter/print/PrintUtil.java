package xdroid.mwee.com.posdinnerprinter.print;

import android.text.TextUtils;

import com.mwee.android.print.base.PrinterConfig;
import com.mwee.android.print.processor.PrintBuilder;
import com.mwee.android.tools.StringUtil;

import java.math.BigDecimal;

/**
 * 打印相关的工具类
 * Created by virgil on 2017/6/21.
 */
public class PrintUtil {
    /**
     * 获取小票尺寸
     *
     * @param commandType String
     * @param configSize  int
     * @return int see{@link PrintBuilder}
     */
    public static int getSize(String commandType, int configSize) {
        if (!TextUtils.isEmpty(commandType)) {
            commandType = commandType.toLowerCase().trim().replaceAll(" ", "");
            if (commandType.contains("size_")) {
                int size = StringUtil.toInt(commandType.substring(5, commandType.length()), PrintBuilder.SIZE_BIG);
                if (size > PrintBuilder.SIZE_BIG || size < PrintBuilder.SIZE_MIN) {
                    size = PrintBuilder.SIZE_BIG;
                }
                return size;
            }
        }
        if (configSize == 1) {
            return PrintBuilder.SIZE_MIN;
        } else if (configSize == 2) {
            return PrintBuilder.SIZE_BIG;
        } else if (configSize == 3) {
            if ("epson".equalsIgnoreCase(commandType)) {
                return PrintBuilder.SIZE_SMALL;
            } else {
                return PrintBuilder.SIZE_NORMAL;
            }
        } else {
            return PrintBuilder.SIZE_BIG;
        }
    }

    public static void buildPrinterInfo(PrinterConfig config, PrinterDBModel model){
        config.type = model.fiPrinterCls;
        config.timeOut = 3;
        config.retryTimes = model.fiRetry;
        config.paperType = model.fiPrinterType;
        config.prepareLang(model.fiLanguage);
        config.commandType = model.fsCommandType;
        config.reverse = model.fiReverse;
        //config.setIsOldPrinter(model.isOldPrinter());
        config.setIsSupportStatusCheck(model.fiSupportStatusCheck);
        //config.setSamSungCommand(model.fiCutSamSung==1);
        if (model.fiCPL > 0) {
            config.paperSize = model.fiCPL;
        }else {
            config.paperSize = PrintUtil.getSize(config.commandType, model.fiPaperSize);
        }
    }
    /**
     * 购买数量与规格
     *
     * @param fdBuyQty
     * @param unitName
     * @return
     */
    public static String optBuyNumAndUnit(BigDecimal fdBuyQty, String unitName) {
        return optBuyNumAndUnit(fdBuyQty, "/", unitName);
    }

    /**
     * 购买数量与规格
     *
     * @param buyQty
     * @param unitName
     * @return
     */
    public static String optBuyNumAndUnit(String buyQty, String unitName) {
        return optBuyNumAndUnit(buyQty, "/", unitName);
    }

    /**
     * 购买数量与规格
     *
     * @param fdBuyQty
     * @param unitName
     * @return
     */
    public static String optBuyNumAndUnit(BigDecimal fdBuyQty, String split, String unitName) {
        return optBuyNumAndUnit(fdBuyQty.stripTrailingZeros().toPlainString(), split, unitName);
    }

    /**
     * 购买数量与规格
     *
     * @param buyQty
     * @param unitName
     * @return
     */
    public static String optBuyNumAndUnit(String buyQty, String split, String unitName) {
        if (TextUtils.isEmpty(unitName) || TextUtils.isEmpty(unitName.trim())) {
            return buyQty;
        } else {
            return buyQty + split + unitName;
        }
    }
}
