package xdroid.mwee.com.zmstudy.business.db.client;

import xdroid.mwee.com.model.util.db.MenuitemDBModel;
import xdroid.mwee.com.zmstudy.model.menu.MenuItem;


public class DishesUtil {

    /**
     * 配置项， 位操作
     * 1，是否必选做法
     * 2，是否多规格
     * 4，是否多做法
     * 8，是否可赠送
     * 16,是否支持打折
     * 32，是否支持自定义称重
     * 64，是否招牌菜
     * 128，是否新菜
     * 256，是否是套餐
     * 512，是否是时价菜
     * 1024, 是否有配料
     * 2048，是套餐时，需要分账
     */
    public static boolean buildMenuConfig(MenuItem item, MenuitemDBModel tempMenu, int procedureCount, int unitSize,
                                          int ingredientGPCount) {
        //配料菜不可以是称重菜，不可以是时价菜
        if (tempMenu.fiItemKind != 4) {

            if (tempMenu.fiIsEditQty == 1) {
                item.config = item.config | 32;
            }

            if (tempMenu.fiIsEditPrice == 1) {
                item.config = item.config | 512;
            }
            if (tempMenu.fiIsSpecialty == 1) {
                item.config = item.config | 64;
            }

            if (tempMenu.fiIsNew == 1) {
                item.config = item.config | 128;
            }

            if (procedureCount > 0) {
                item.config = item.config | 4;
            }
            if (tempMenu.fiIsCuisine == 1) {
                item.config = item.config | 1;
            }

            if (unitSize > 1) {
                item.config = item.config | 2;
            }

        }

        if (tempMenu.fiIsDiscount == 1) {
            item.config = item.config | 16;
        }
        if (tempMenu.fiIsGift == 1) {
            item.config = item.config | 8;
        }

        if (tempMenu.fiItemKind == 2) {
            item.config = item.config | 256;
        }

        if (ingredientGPCount > 0) {
            item.config = item.config | 1024;
        }
        if (tempMenu.fiSplitStatus == 1) {
            //套餐分账
            item.config = item.config | 2048;
        }

        if (tempMenu.fiIsServiceFee == 1) {
            //收取服务费
            item.config = item.config | 8192;
        }

        if (tempMenu.fiIsTakeAway == 1) {
            //是否外卖
            item.config = item.config | 16384;
        }
        if (tempMenu.fiIsPrn == 1) {
            //是否打印
            item.config = item.config | 32768;
        }
        if (tempMenu.fimultipractice == 1) {
            //是否做法多选
            item.config = item.config | 65536;
        }
        if (tempMenu.fiIsHot == 1) {
            //是否热门菜
            item.config = item.config | 131072;
        }

        return true;
    }
}
