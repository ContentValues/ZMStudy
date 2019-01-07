package xdroid.mwee.com.zmstudy.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import xdroid.mwee.com.mwcommon.base.SimpleRecAdapter;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.model.menu.MenuTypeBean;

/**
 * Created by zhangmin on 2018/6/20.
 */

public class MenuClsAdapter extends SimpleRecAdapter<MenuTypeBean, MenuClsAdapter.ViewHolder> {

    private int selectPosition = 0;

    public MenuClsAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_menucls;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MenuTypeBean model = data.get(position);
        holder.tvCategoryName.setText(model.fsMenuClsName);
        if (selectPosition == position) {
            holder.tvCategoryName.setBackgroundResource(R.drawable.bg_category_son_white_item_checked);
            holder.tvCategoryName.setTextColor(getColor(R.color.system_red));
        } else {
            holder.tvCategoryName.setBackgroundResource(0);
            holder.tvCategoryName.setTextColor(getColor(R.color.color_2d2d2d));
        }
        holder.tvCategoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPosition = position;
                notifyDataSetChanged();
                getItemClick().onItemClick(position, model, -1);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategoryName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
        }
    }

}
