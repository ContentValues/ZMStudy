package xdroid.mwee.com.zmstudy.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.mwee.android.sqlite.base.APPConfig;
import com.mwee.android.sqlite.base.DBSimpleUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import xdroid.mwee.com.model.util.db.MareaDBModel;
import xdroid.mwee.com.model.util.db.MtableDBModel;
import xdroid.mwee.com.mwcommon.base.XLazyFragment;
import xdroid.mwee.com.mwcommon.callback.ItemCallback;
import xdroid.mwee.com.mwcommon.xrecyclerview.XRecyclerContentLayout;
import xdroid.mwee.com.mwcommon.xrecyclerview.XRecyclerView;
import xdroid.mwee.com.mwcommon.xrecyclerview.divider.VerticalDividerItemDecoration;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.adapter.MareaAdapter;
import xdroid.mwee.com.zmstudy.adapter.TableAdapter;
import xdroid.mwee.com.zmstudy.cache.AppCache;
import xdroid.mwee.com.zmstudy.model.table.HeaderModel;
import xdroid.mwee.com.zmstudy.model.table.TableSection;

/**
 * Created by zhangmin on 2018/6/20.
 */

public class TableFragment extends XLazyFragment {


    private MareaAdapter mareaAdapter;
    private TableAdapter tableAdapter;

    public static TableFragment newInstance() {
        return new TableFragment();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        XRecyclerView mAreaRecyclerView = findViewById(R.id.mAreaRecyclerView);
        mAreaRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        mAreaRecyclerView.addItemDecoration(new VerticalDividerItemDecoration.Builder(getContext()).margin(10)/*.colorResId(R.color.system_red)*/.build());
        mareaAdapter = new MareaAdapter(getContext());
        mAreaRecyclerView.setAdapter(mareaAdapter);
        mareaAdapter.setData(AppCache.getInstance().mareaDBModelList);

        XRecyclerContentLayout mTableContentLayout = findViewById(R.id.mTableContentLayout);
        XRecyclerView mTableRecyclerView = mTableContentLayout.getRecyclerView();
        mTableRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        //mTableRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tableAdapter = new TableAdapter(getContext());
        mTableRecyclerView.setAdapter(tableAdapter);

        for (int i = 0; i < 2; i++) {
            Random random = new Random();
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            View HeaderView = LayoutInflater.from(getContext()).inflate(R.layout.view_menu_item_header, mTableRecyclerView, false);
            HeaderView.findViewById(R.id.tvHeader).setBackgroundColor(Color.rgb(r, g, b));
            mTableRecyclerView.addHeaderView(HeaderView);
            View footerView = LayoutInflater.from(getContext()).inflate(R.layout.view_menu_item_footer, mTableRecyclerView, false);
            footerView.findViewById(R.id.tvFooter).setBackgroundColor(Color.rgb(r, g, b));
            mTableRecyclerView.addFooterView(footerView);
        }
        //loadTableByClsPosition(AppCache.getInstance().mareaDBModelList.get(0).fsMAreaId);

        mareaAdapter.setItemClick(new ItemCallback<MareaDBModel>() {
            @Override
            public void onItemClick(int position, MareaDBModel model, int tag) {
                super.onItemClick(position, model, tag);
                loadTableByClsPosition(model);
            }
        });
        loadTableByClsPosition(AppCache.getInstance().mareaDBModelList.get(0));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_table;
    }

    private void loadTableByClsPosition(MareaDBModel model) {

        if (TextUtils.equals("-1AllMArea", model.fsMAreaId)) {
            List<TableSection> dataList = new ArrayList();
            Set<String> stringSet = AppCache.getInstance().areaTableList.keySet();
            for (String header : stringSet) {
                String fsMAreaNameHeader = DBSimpleUtil.queryString(APPConfig.DB_MAIN, "select fsMAreaName from tbmarea where fiStatus = '1' and fsMAreaId = '" + header + "'");

                HeaderModel headerModel = new HeaderModel();
                headerModel.title = fsMAreaNameHeader;
                headerModel.subTitle = "测试头部11";
                TableSection tableHeaderSection = new TableSection(true, headerModel);
                dataList.add(tableHeaderSection);
                for (MtableDBModel mtableDBModel : AppCache.getInstance().areaTableList.get(header)) {
                    TableSection tableItemSection = new TableSection(tableHeaderSection, mtableDBModel);
                    dataList.add(tableItemSection);
                }
            }
            tableAdapter.setData(dataList);
        } else {
            List<TableSection> dataList = new ArrayList();

            HeaderModel headerModel2 = new HeaderModel();
            headerModel2.title = model.fsMAreaName;
            headerModel2.subTitle = "测试头部11";

            TableSection tableHeaderSection = new TableSection(true, headerModel2);
            dataList.add(tableHeaderSection);
            for (MtableDBModel mtableDBModel : AppCache.getInstance().areaTableList.get(model.fsMAreaId)) {
                TableSection tableItemSection = new TableSection(tableHeaderSection, mtableDBModel);
                dataList.add(tableItemSection);
            }
            tableAdapter.setData(dataList);
        }


    }
}
