package xdroid.mwee.com.zmstudy.adapter;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mwee.android.tools.ProcessUtil;

import java.util.Random;

import xdroid.mwee.com.model.util.db.MtableDBModel;
import xdroid.mwee.com.mwcommon.base.SimpleRecAdapter;
import xdroid.mwee.com.mwcommon.base.XRecAdapter;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.model.SectionEntity;
import xdroid.mwee.com.zmstudy.model.table.TableSection;

/**
 * Created by zhangmin on 2018/6/20.
 */

public class TableAdapter extends XRecAdapter<TableSection, RecyclerView.ViewHolder> {

    private int DEF_VIEW = 0;
    private int HEADER_VIEW = 1;


    public TableAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);
        return data.get(position).isHeader ? HEADER_VIEW : DEF_VIEW;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_VIEW) {
            return new HeaderSectionViewHolder(LayoutInflater.from(context).inflate(R.layout.view_menu_item_header, parent, false));
        }
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_table, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        TableSection model = data.get(position);
        if (holder instanceof HeaderSectionViewHolder) {
            Random random = new Random();
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            ((HeaderSectionViewHolder) holder).tvHeader.setBackgroundColor(Color.rgb(r, g, b));
            ((HeaderSectionViewHolder) holder).tvHeader.setText(model.header);
        } else {
            ((ViewHolder) holder).tvTableName.setText(model.t.fsmtablename);
            ((ViewHolder) holder).tvTablePersonCount.setText(model.t.fiseats);
            ((ViewHolder) holder).tvTableTime.setText(model.t.fsupdatetime);
        }

    }

   /* @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TableSection model = data.get(position);
        holder.tvTableName.setText(model.t.fsmtablename);
    }*/

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTableName;
        TextView tvTablePersonCount;
        TextView tvTableTime;
        TextView tvTableMoney;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTableName = itemView.findViewById(R.id.tvTableName);
            tvTablePersonCount = itemView.findViewById(R.id.tvTablePersonCount);
            tvTableTime = itemView.findViewById(R.id.tvTableTime);
            tvTableTime = itemView.findViewById(R.id.tvTableMoney);
        }
    }


    public static class HeaderSectionViewHolder extends RecyclerView.ViewHolder {
        TextView tvHeader;

        public HeaderSectionViewHolder(View itemView) {
            super(itemView);
            tvHeader = itemView.findViewById(R.id.tvHeader);
        }
    }


}
