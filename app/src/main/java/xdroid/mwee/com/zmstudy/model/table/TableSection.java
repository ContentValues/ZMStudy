package xdroid.mwee.com.zmstudy.model.table;

import xdroid.mwee.com.model.util.db.MtableDBModel;
import xdroid.mwee.com.zmstudy.model.SectionEntity;

/**
 * Created by zhangmin on 2018/6/23.
 */

public class TableSection extends SectionEntity<MtableDBModel, String> {

    public TableSection headerSection;

    public TableSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public TableSection(TableSection headerSection, MtableDBModel mtableDBModel) {
        super(mtableDBModel);
        this.headerSection = headerSection;
    }

}
