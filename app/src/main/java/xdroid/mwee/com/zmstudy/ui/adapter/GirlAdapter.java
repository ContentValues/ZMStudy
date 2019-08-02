package xdroid.mwee.com.zmstudy.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.mwee.android.tools.LogUtil;

import xdroid.mwee.com.mwcommon.base.SimpleRecAdapter;
import xdroid.mwee.com.mwcommon.imageloader.ILFactory;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.model.bean.GankModel;

/**
 * Created by zhangmin on 2018/6/19.
 */

public class GirlAdapter extends SimpleRecAdapter<GankModel.ItemBean, GirlAdapter.ViewHolder> {

    private final static String TAG = GirlAdapter.class.getSimpleName();

    public GirlAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {

        ViewHolder viewHolder =   new ViewHolder(itemView);
        Log.d(TAG, "onCreateViewHolder GirlAdapter     getItemCount" + getItemCount() +"       viewHolder"+viewHolder.hashCode());
        return viewHolder;
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_girl;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Log.d(TAG, "****onBindViewHolder GirlAdapter       getItemCount" + getItemCount()+"       viewHolder"+holder.hashCode());


        GankModel.ItemBean itemBean = data.get(position);
        ILFactory.getLoader().loadNet(holder.iv_girl, itemBean.getUrl(), null);
        /*ILFactory.getLoader().loadNet(context, item.getUrl(), null, new LoadCallback() {
            @Override
            public void onLoadReady(Bitmap bitmap) {
                holder.iv_girl.setImageBitmap(bitmap);
            }
        });*/
        holder.iv_girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItemClick().onItemClick(position, itemBean, -1);
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
