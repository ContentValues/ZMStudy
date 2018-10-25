package xdroid.mwee.com.zmstudy.business;
/**
 * 结账相关的打印数据构造类
 * Created by virgil on 2017/1/22.
 *
 * @author virgil
 */

public class PrintBillUtil {
    /**
     * 构建预结单的PrintTaskDbModel
     *
     * @return
     */
   /* public static void printPreBill(OrderCache orderCache) {

        JSONObject datas = new JSONObject();
        SellDBModel sell = DBSimpleUtil.query(APPConfig.DB_MAIN, "select *, (fdSaleAmt+fdRoundAmt) as fdSaleAmt FROM tbSell where fsSellNo = '" + orderCache.orderID + "'", SellDBModel.class);
        datas.put("Shop", DBSimpleUtil.query(APPConfig.DB_MAIN, "select * from tbShop", ShopDBModel.class));
        datas.put("Sell", sell);
        JSONObject map = new JSONObject();
        map.put("total", sell.fdSaleAmt.subtract(sell.fdRoundAmt));
        map.put("qty", 3);

        datas.put("Sub", map);
        datas.put("PrintTime", DateUtil.getCurrentTime());

        datas.put("printUserName", "管理员");

        List<SellOrderItemDBModel> sellOrderItemDBModels = DBSimpleUtil.queryList(APPConfig.DB_MAIN, "select * from tbSellOrderItem where fsSellNo = '" + orderCache.orderID + "'", SellOrderItemDBModel.class);
        datas.put("sellorder", sellOrderItemDBModels);
        datas.put("reportTail", "本服务由美味不用等提供");

        PrintTaskDBModel task = PrintJSONBuilder.buildPrintTask(
                orderCache.orderID,
                orderCache.fsmtablename,
                orderCache.businessDate,
                100,
                "管理员",
                "0",
                "S04",
                "Cashier",
                true);
        task.uri = "order/prebill";
        datas.put("fiPrintNo", task.fiPrintNo);
        task.fsPrnData = datas.toJSONString();
        task.fsPrinterName = DBSimpleUtil.queryString(APPConfig.DB_MAIN, "select fsPrinterName from tbHost where fsHostId = 'Cashier'");
        PrintConnector.getInstance().print(task, false);
    }*/
}
