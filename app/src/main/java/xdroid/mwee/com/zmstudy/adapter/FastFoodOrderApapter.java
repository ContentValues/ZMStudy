package xdroid.mwee.com.zmstudy.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import xdroid.mwee.com.mwcommon.base.SimpleRecAdapter;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.model.menu.MenuItem;
import xdroid.mwee.com.zmstudy.model.order.OrderCache;

/**
 * Created by zhangmin on 2018/6/29.
 */

public class FastFoodOrderApapter extends SimpleRecAdapter<MenuItem, FastFoodOrderApapter.ViewHolder> {


    private OrderCache orderCache;

    public FastFoodOrderApapter(Context context) {
        super(context);
    }

    public void setOrderCache(OrderCache orderCache) {
        this.orderCache = orderCache;
        ArrayList<MenuItem> menuItemArrayList = new ArrayList<>();
        menuItemArrayList.addAll(orderCache.originMenuList);
        menuItemArrayList.addAll(orderCache.tempMenuList);
        this.setData(menuItemArrayList);
    }


    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adpter_order_menu_item;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MenuItem model = data.get(position);
        holder.tvMenuName.setText(model.name);
        holder.tvMenuQty.setText(model.menuBiz.buyNum + "");
        holder.tvMenuPrice.setText(model.menuBiz.totalPrice + "");
        if (orderCache.isOrderedSeqNo(model.menuBiz.orderSeqID)) {
            holder.itemView.setBackgroundColor(Color.YELLOW);
            holder.tvMenuDelete.setText("退");
        } else {
            holder.itemView.setBackgroundColor(getColor(R.color.white));
            holder.tvMenuDelete.setText("删");
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvMenuName;
        TextView tvMenuPrice;
        TextView tvMenuQty;
        TextView tvMenuDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            tvMenuName = itemView.findViewById(R.id.tvMenuName);
            tvMenuQty = itemView.findViewById(R.id.tvMenuQty);
            tvMenuPrice = itemView.findViewById(R.id.tvMenuPrice);
            tvMenuDelete = itemView.findViewById(R.id.tvMenuDelete);

        }

    }
}
