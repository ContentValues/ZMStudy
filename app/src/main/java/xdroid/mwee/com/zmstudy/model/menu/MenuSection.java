package xdroid.mwee.com.zmstudy.model.menu;

import xdroid.mwee.com.zmstudy.model.SectionEntity;

/**
 * Created by zhangmin on 2018/6/27.
 */

public class MenuSection extends SectionEntity<MenuItem, String> {

    MenuSection headerSection;

    public MenuSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MenuSection(MenuSection headerSection, MenuItem menuItem) {
        super(menuItem);
        this.headerSection = headerSection;
    }

}
