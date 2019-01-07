package xdroid.mwee.com.zmstudy.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import xdroid.mwee.com.mwcommon.base.SimpleRecAdapter;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.model.menu.MenuItem;

/**
 * Created by zhangmin on 2018/6/20.
 */

public class MenuAdapter extends SimpleRecAdapter<MenuItem, MenuAdapter.ViewHolder> {


    public MenuAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_menu;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MenuItem model = data.get(position);
        holder.mMenuItemNameLabel.setText(model.name);
        //holder.mMenuItemRepertoryLabel.setText(model.name);
        //holder.mMenuTagSellOutLabel.setText(model.name);
        holder.mMenuItemSellPriceLabel.setText(model.price + "");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItemClick().onItemClick(position, model, -1);
            }
        });

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mMenuItemNameLabel;
        TextView mMenuItemRepertoryLabel;
        TextView mMenuTagSellOutLabel;
        TextView mMenuItemSellPriceLabel;

        public ViewHolder(View itemView) {
            super(itemView);
            mMenuItemNameLabel = itemView.findViewById(R.id.mMenuItemNameLabel);
            mMenuItemRepertoryLabel = itemView.findViewById(R.id.mMenuItemRepertoryLabel);
            mMenuTagSellOutLabel = itemView.findViewById(R.id.mMenuTagSellOutLabel);
            mMenuItemSellPriceLabel = itemView.findViewById(R.id.mMenuItemSellPriceLabel);
        }
    }
}
