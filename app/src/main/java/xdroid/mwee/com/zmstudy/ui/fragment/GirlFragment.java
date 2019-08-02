package xdroid.mwee.com.zmstudy.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import xdroid.mwee.com.mwcommon.base.SimpleRecAdapter;
import xdroid.mwee.com.mwcommon.callback.ItemCallback;
import xdroid.mwee.com.zmstudy.ui.activity.WebActivity;
import xdroid.mwee.com.zmstudy.ui.adapter.GirlAdapter;
import xdroid.mwee.com.zmstudy.model.bean.GankModel;

/**
 * Created by zhangmin on 2018/4/9.
 */

public class GirlFragment extends BasePagerFragment {

    public static GirlFragment newInstance() {
        return new GirlFragment();
    }

    GirlAdapter girlAdapter = null;

    @Override
    public SimpleRecAdapter getAdapter() {
        if (girlAdapter == null) {
            girlAdapter = new GirlAdapter(getContext());
            girlAdapter.setItemClick(new ItemCallback<GankModel.ItemBean>() {
                @Override
                public void onItemClick(int position, GankModel.ItemBean model, int tag) {
                    super.onItemClick(position, model, tag);
                    WebActivity.launch(getActivity(), model.getUrl(), model.getDesc());
                }
            });
        }
        return girlAdapter;
    }

    @Override
    public String getType() {
        return "福利";
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getContext(), 1);
    }

}
