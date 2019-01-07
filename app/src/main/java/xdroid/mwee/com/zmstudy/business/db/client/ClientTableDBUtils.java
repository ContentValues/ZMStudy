package xdroid.mwee.com.zmstudy.business.db.client;

import com.mwee.android.sqlite.base.APPConfig;
import com.mwee.android.sqlite.base.DBSimpleUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import xdroid.mwee.com.model.util.db.MareaDBModel;
import xdroid.mwee.com.model.util.db.MtableDBModel;

/**
 * Created by zhangmin on 2018/6/20.
 */

public class ClientTableDBUtils {

    public static List<MareaDBModel> loadMareaList() {
        String sql = "select * from tbmarea where fiStatus = '1'";
        List<MareaDBModel> mareaDBModels = DBSimpleUtil.queryList(APPConfig.DB_MAIN, sql, MareaDBModel.class);
        if (mareaDBModels == null) {
            mareaDBModels = new ArrayList<>();
        }
        MareaDBModel allMAreaDBModel = new MareaDBModel();
        allMAreaDBModel.fsMAreaId = "-1AllMArea";
        allMAreaDBModel.fsMAreaName = "全部";
        mareaDBModels.add(0, allMAreaDBModel);
        return mareaDBModels;
    }

    public static LinkedHashMap<String, List<MtableDBModel>> loadAreaTableList() {

        String sql = "select * from tbMtable where fiStatus = '1'";
        List<MtableDBModel> mtableDBModels = DBSimpleUtil.queryList(APPConfig.DB_MAIN, sql, MtableDBModel.class);
        LinkedHashMap<String, List<MtableDBModel>> linkedHashMap = new LinkedHashMap<>();
        for (MtableDBModel mtableDBModel : mtableDBModels) {
            List<MtableDBModel> models = linkedHashMap.get(mtableDBModel.fsmareaid);
            if (models == null) {
                models = new ArrayList<>();
                linkedHashMap.put(mtableDBModel.fsmareaid, models);
            }
            models.add(mtableDBModel);
        }
        return linkedHashMap;
    }

}
