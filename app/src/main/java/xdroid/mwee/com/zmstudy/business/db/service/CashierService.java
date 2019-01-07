package xdroid.mwee.com.zmstudy.business.db.service;
import android.content.ContentValues;
import java.util.List;
import xdroid.mwee.com.model.util.db.MareaDBModel;
import xdroid.mwee.com.zmstudy.business.db.dao.impl.MareaDaoImpl;
import xdroid.mwee.com.zmstudy.business.db.dao.impl.MtableDaoImpl;
/**
 * Created by zhangmin on 2018/11/1.
 */

public class CashierService {

    private final MareaDaoImpl mAreaDaoImpl;
    private final MtableDaoImpl mTableDao;

    public CashierService(){
        mAreaDaoImpl = new MareaDaoImpl();
        mTableDao = new MtableDaoImpl();
    }

    public  List<MareaDBModel> loadMareaList() {

        ContentValues contentValues = new ContentValues();
        contentValues.put("fiStatus","1");
        List<MareaDBModel> mareaDBModels = mAreaDaoImpl.queryList(contentValues);

        MareaDBModel allMAreaDBModel = new MareaDBModel();
        allMAreaDBModel.fsMAreaId = "-1AllMArea";
        allMAreaDBModel.fsMAreaName = "全部";
        mareaDBModels.add(0, allMAreaDBModel);
        return mareaDBModels;
    }


}
