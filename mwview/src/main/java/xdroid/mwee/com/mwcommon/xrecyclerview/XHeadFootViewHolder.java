package xdroid.mwee.com.mwcommon.xrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;


public class XHeadFootViewHolder extends RecyclerView.ViewHolder {

    public XHeadFootViewHolder(View itemView) {
        super(itemView);
        //itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        itemView.setTag(this);
    }
}

