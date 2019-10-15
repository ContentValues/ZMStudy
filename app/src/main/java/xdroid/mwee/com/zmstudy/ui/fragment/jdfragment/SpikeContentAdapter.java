package xdroid.mwee.com.zmstudy.ui.fragment.jdfragment;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import xdroid.mwee.com.mwcommon.base.XRecAdapter;
import xdroid.mwee.com.mwcommon.imageloader.ILFactory;
import xdroid.mwee.com.zmstudy.R;

/**
 * @author：admin on 2017/3/30 18:00.
 */

public class SpikeContentAdapter extends XRecAdapter<HomeIndex.ItemInfoListBean.ItemContentListBean, SpikeContentAdapter.SpikeContentHolder> {

    public SpikeContentAdapter(Context context) {
        super(context);
    }

    @Override
    public SpikeContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SpikeContentHolder(LayoutInflater.from(context).inflate(R.layout.homerecycle_spike_content, parent, false));
    }

    @Override
    public void onBindViewHolder(SpikeContentHolder holder, int position) {
        holder.bindData(data.get(position));
    }

    public class SpikeContentHolder extends RecyclerView.ViewHolder {

        private final ImageView spike_ware_img;
        private final TextView spike_ware_subscript;
        private final TextView spike_ware_now_price;
        private final TextView spike_ware_old_price;

        public SpikeContentHolder(View itemView) {
            super(itemView);
            spike_ware_img = itemView.findViewById(R.id.spike_ware_img);
            spike_ware_subscript = itemView.findViewById(R.id.spike_ware_subscript);
            spike_ware_now_price = itemView.findViewById(R.id.spike_ware_now_price);
            spike_ware_old_price = itemView.findViewById(R.id.spike_ware_old_price);
        }

        private void bindData(HomeIndex.ItemInfoListBean.ItemContentListBean item) {
            ILFactory.getLoader().loadNet(spike_ware_img, item.imageUrl, null);
            spike_ware_subscript.setText(item.itemSubscript);
            spike_ware_now_price.setText(item.itemTitle);
            spike_ware_old_price.setText(item.itemSubTitle);
            spike_ware_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }

    }

}
