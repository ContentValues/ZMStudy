package xdroid.mwee.com.zmstudy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import xdroid.mwee.com.model.util.db.MareaDBModel;
import xdroid.mwee.com.mwcommon.base.SimpleRecAdapter;
import xdroid.mwee.com.zmstudy.R;

/**
 * Created by zhangmin on 2018/6/20.
 */

public class MareaAdapter extends SimpleRecAdapter<MareaDBModel, MareaAdapter.ViewHolder> {


    private int selectPosition = 0;


    public MareaAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_area;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MareaDBModel model = data.get(position);
        holder.tvAreaName.setText(model.fsMAreaName);
        holder.mButtomLine.setVisibility(position == selectPosition ? View.VISIBLE : View.INVISIBLE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPosition = position;
                notifyDataSetChanged();
                getItemClick().onItemClick(position, model, -1);
            }
        });

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAreaName;
        View mButtomLine;

        public ViewHolder(View itemView) {
            super(itemView);
            tvAreaName = itemView.findViewById(R.id.tvAreaName);
            mButtomLine = itemView.findViewById(R.id.mButtomLine);
        }
    }
}
