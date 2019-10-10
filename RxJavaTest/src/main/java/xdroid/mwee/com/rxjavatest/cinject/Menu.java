package xdroid.mwee.com.rxjavatest.cinject;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author：created by SugarT
 * Time：2019/9/30 10
 */
public class Menu {
    public Map<String,Boolean> menus;

    @Inject
    public Menu( Map<String,Boolean> menus){
        this.menus = menus;
    }

    Map<String,Boolean> getMenus(){
        return menus;
    }
}
