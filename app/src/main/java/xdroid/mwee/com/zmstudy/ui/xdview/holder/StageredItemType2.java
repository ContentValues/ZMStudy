package xdroid.mwee.com.zmstudy.ui.xdview.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.Random;

import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.ui.xdview.VHolder;
import xdroid.mwee.com.zmstudy.ui.xdview.bean.ItemVo;

/**
 * @author：tqzhang on 18/8/22 13:57
 */
public class StageredItemType2 extends VHolder<ItemVo, StageredItemType2.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.type_1, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ItemVo item) {
        int h = new Random().nextInt(180) + 260;
        holder.rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, h));
        holder.tv_type.setText("StageredItemType2" + item.type);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rootView;
        TextView tv_type;

        ViewHolder(@NonNull final View itemView) {
            super(itemView);
            rootView = itemView.findViewById(R.id.rl_root_view);
            tv_type = itemView.findViewById(R.id.tv_type);

        }

    }

}
