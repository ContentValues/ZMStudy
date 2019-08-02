package xdroid.mwee.com.zmstudy.ui.xdview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangmin on 2019/4/12.
 */

public class ItemViewTypes {

    private final
    List<Class<?>> classes;
    private final
    List<VHolder<?, ?>> vHolders;
//    private final
//    List<Chain<?>> chains;


    ItemViewTypes() {
        this.classes = new ArrayList<>();
        this.vHolders = new ArrayList<>();
        //this.chains = new ArrayList<>();
    }

//    public <T> void save(Class<? extends T> clazz,VHolder<T, ?> vHolder,Chain<T> chain) {
//        classes.add(clazz);
//        vHolders.add(vHolder);
//        chains.add(chain);
//    }

    public <T> void save( Class<? extends T> clazz,VHolder<T, ?> vHolder) {
        classes.add(clazz);
        vHolders.add(vHolder);
        //chains.add(new DefaultChain<>());
    }

    public int size() {
        return classes.size();
    }


    public int getClassIndexOf(final Class<?> clazz) {
        int index = classes.indexOf(clazz);
        if (index != -1) {
            return index;
        }
        return -1;
    }

    public VHolder<?, ?> getItemView(int index) {
        return vHolders.get(index);
    }

//    public Chain<?> getChain(int index) {
//        return chains.get(index);
//    }

}
