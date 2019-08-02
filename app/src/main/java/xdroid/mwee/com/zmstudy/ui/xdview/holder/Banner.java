package xdroid.mwee.com.zmstudy.ui.xdview.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.ui.xdview.VHolder;
import xdroid.mwee.com.zmstudy.ui.xdview.bean.VoBanner;


/**
 * @author：tqzhang on 18/8/22 13:57
 */
public class Banner extends VHolder<VoBanner, Banner.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.banner, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull VoBanner item) {
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout mBannerView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mBannerView = itemView.findViewById(R.id.rl_root_view);
        }

    }

}
