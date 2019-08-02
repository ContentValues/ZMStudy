package xdroid.mwee.com.zmstudy.ui.xdview.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.ui.xdview.VHolder;
import xdroid.mwee.com.zmstudy.ui.xdview.bean.Item2Vo;


/**
 * @author：tqzhang on 18/8/22 13:57
 */
public class ItemType2 extends VHolder<Item2Vo, ItemType2.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.type_2,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Item2Vo item) {

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }

}
