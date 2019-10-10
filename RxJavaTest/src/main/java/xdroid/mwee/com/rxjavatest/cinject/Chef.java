package xdroid.mwee.com.rxjavatest.cinject;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author：created by SugarT
 * Time：2019/9/30 10
 */
public class Chef implements Cooking{

    Menu menu;

    @Inject
    public Chef(Menu menu){
        this.menu = menu;
    }

    @Override
    public String cook(){
        //key菜名， value是否烹饪
        Map<String,Boolean> menuList = menu.getMenus();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String,Boolean> entry : menuList.entrySet()){
            if (entry.getValue()){
                sb.append(entry.getKey()).append(",");
            }
        }

        return sb.toString();
    }
}
