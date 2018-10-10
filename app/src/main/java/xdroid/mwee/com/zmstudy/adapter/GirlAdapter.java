package xdroid.mwee.com.zmstudy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import xdroid.mwee.com.mwcommon.base.SimpleRecAdapter;
import xdroid.mwee.com.mwcommon.imageloader.ILFactory;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.model.Item;

/**
 * Created by zhangmin on 2018/6/19.
 */

public class GirlAdapter extends SimpleRecAdapter<Item, GirlAdapter.ViewHolder> {

    public GirlAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_girl;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = data.get(position);
        ILFactory.getLoader().loadNet(holder.iv_girl, item.getUrl(), null);
        holder.iv_girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItemClick().onItemClick(position,item,-1);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_girl;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_girl = itemView.findViewById(R.id.iv_girl);
        }
    }

}
