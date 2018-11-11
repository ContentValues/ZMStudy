package xdroid.mwee.com.zmstudy.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import xdroid.mwee.com.mwcommon.base.SimpleRecAdapter;
import xdroid.mwee.com.mwcommon.imageloader.ILFactory;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.model.bean.GankModel;

/**
 * Created by zhangmin on 2018/4/9.
 */

public class HomeAdapter extends SimpleRecAdapter<GankModel.ItemBean, HomeAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;

    public HomeAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_home;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final GankModel.ItemBean itemBean = data.get(position);

        String type = itemBean.getType();
        switch (type) {
            case "休息视频":
                holder.rlMessage.setVisibility(View.VISIBLE);
                holder.ivPart.setVisibility(View.GONE);
                holder.ivVedio.setVisibility(View.VISIBLE);
                holder.tvItem.setText(itemBean.getDesc());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getSimpleItemClick() != null) {

                        }
                    }
                });
                break;
            case "福利":
                holder.rlMessage.setVisibility(View.GONE);
                holder.ivPart.setVisibility(View.VISIBLE);
                holder.ivVedio.setVisibility(View.GONE);

                ILFactory.getLoader().loadNet(holder.ivPart, itemBean.getUrl(), null);
                holder.tvItem.setText("瞧瞧妹纸，扩展扩展视野......");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getSimpleItemClick() != null) {

                        }
                    }
                });
                break;
            default:
                holder.rlMessage.setVisibility(View.VISIBLE);
                holder.ivPart.setVisibility(View.GONE);
                holder.ivVedio.setVisibility(View.GONE);
                holder.tvItem.setText(itemBean.getDesc());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getSimpleItemClick() != null) {

                        }
                    }
                });
                break;
        }
        Uri uri = null;
        switch (itemBean.getType()) {
            case "Android":
                holder.ivType.setImageResource(R.mipmap.android_icon);
                break;
            case "iOS":
                holder.ivType.setImageResource(R.mipmap.ios_icon);
                break;
            case "前端":
                holder.ivType.setImageResource(R.mipmap.js_icon);
                break;
            case "拓展资源":
                holder.ivType.setImageResource(R.mipmap.other_icon);
                break;
        }

        String author = itemBean.getWho();
        if (author != null) {
            holder.tvAuthor.setText(author);
            holder.tvAuthor.setTextColor(Color.parseColor("#87000000"));
        } else {
            holder.tvAuthor.setText("");
        }

        holder.tvTime.setText(itemBean.getCreatedAt());

        holder.tvType.setText(type);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSimpleItemClick() != null) {
                    getSimpleItemClick().onItemClick(position, itemBean, TAG_VIEW, holder);
                }
            }
        });


    }

    public  static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivType;
        TextView tvType;
        ImageView ivAuthor;
        TextView tvAuthor;
        TextView tvTime;
        RelativeLayout rlMessage;
        ImageView ivPart;
        ImageView ivVedio;
        TextView tvItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ivType = itemView.findViewById(R.id.iv_type);
            tvType = itemView.findViewById(R.id.tv_type);
            ivAuthor = itemView.findViewById(R.id.iv_author);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvTime = itemView.findViewById(R.id.tv_time);
            rlMessage = itemView.findViewById(R.id.rl_message);
            ivPart = itemView.findViewById(R.id.iv_part);
            ivVedio = itemView.findViewById(R.id.iv_vedio);
            tvItem = itemView.findViewById(R.id.tv_item);
        }
    }
}
