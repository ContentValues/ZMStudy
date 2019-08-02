package xdroid.mwee.com.zmstudy.ui.xdview.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.ui.xdview.VHolder;
import xdroid.mwee.com.zmstudy.ui.xdview.bean.Item1Vo;


/**
 * @author：tqzhang on 18/8/22 13:57
 */
public class ItemType1 extends VHolder<Item1Vo, ItemType1.ViewHolder> {


    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.type_1, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Item1Vo item) {
        holder.tvType.setText(item.type);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvType;

        ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvType = itemView.findViewById(R.id.tv_type);
        }

    }

}
