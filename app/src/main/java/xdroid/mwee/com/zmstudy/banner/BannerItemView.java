package xdroid.mwee.com.zmstudy.banner;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.ui.xdview.VHolder;

/**
 * @author：tqzhang on 18/6/21 18:00
 */
public class BannerItemView extends VHolder<BannerListVo, BannerItemView.ViewHolder> {

    private Context mContext;

    public BannerItemView(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.common_banner_view, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull BannerItemView.ViewHolder holder, @NonNull final BannerListVo bannerAdListVo) {
        holder.mBannerView.delayTime(5).setBannerView(new BannerView.OnBindView() {
            @Override
            public List<ImageView> bindView() {
                List<ImageView> imageViewList = new ArrayList<>();
                for (int i = 0; i < bannerAdListVo.data.size(); i++) {
                    ImageView mImageView = new ImageView(mContext);
                    mImageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    Glide.with(mContext).load(bannerAdListVo.data.get(i).topimage1 == null ? bannerAdListVo.data.get(i).topimage : bannerAdListVo.data.get(i).topimage1).centerCrop().into(mImageView);
                    imageViewList.add(mImageView);
                }
                return imageViewList;
            }
        }).build(bannerAdListVo.data);

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private BannerView mBannerView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mBannerView = itemView.findViewById(R.id.banner);
        }

    }

}
