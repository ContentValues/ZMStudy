package xdroid.mwee.com.zmstudy.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import xdroid.mwee.com.mwcommon.base.BaseFragment;
import xdroid.mwee.com.mwcommon.base.SimpleListAdapter;
import xdroid.mwee.com.mwcommon.base.SimpleRecAdapter;
import xdroid.mwee.com.mwcommon.xrecyclerview.divider.HorizontalDividerItemDecoration;
import xdroid.mwee.com.mwcommon.xrecyclerview.divider.VerticalDividerItemDecoration;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.ui.view.BadRecyView;

/**
 * Created by zhangmin on 2018/12/18.
 */

/**
 * View 的onTouchEvent 方法默认都会消费掉事件（返回true），
 * 除非它是不可点击的（clickable和longClickable同时为false），
 * View的longClickable默认为false，
 * clickable需要区分情况，
 * 如Button的clickable默认为true，而TextView的clickable默认为false。
 * <p>
 * 所以得出的结论 Button   的onTouchEvent会消耗点击事件
 * TextView 的onTouchEvent不会消耗点击事件
 * <p>
 * <p>
 * <p>
 * <p>
 * 对于View（注意！ViewGroup也是View）而言，如果设置了onTouchListener，
 * 那么OnTouchListener方法中的onTouch方法会被回调。
 * onTouch方法返回true，则onTouchEvent方法不会被调用（onClick事件是在onTouchEvent中调用）[那么父View的所有事件都会被消耗掉 不会向上回调]
 * 所以三者优先级是onTouch->onTouchEvent->onClick
 */
public class BadFragment extends BaseFragment {

    //private TextView tvText;

    private static String s;

    public static BadFragment newInstance(String s) {
        BadFragment badFragment = new BadFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", s);
        badFragment.setArguments(bundle);
        return badFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_bad;
    }

    @Override
    public void initView(View v) {
        //tvText = v.findViewById(R.id.tvText);
        s = getArguments().getString("key");
        //tvText.setText(s);
        /*tvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"BadFragment OnClick");
            }
        });*/

       /* tvText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG, "BadFragment MotionEvent: ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d(TAG, "BadFragment MotionEvent: ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d(TAG, "BadFragment MotionEvent: ACTION_UP");
                        break;
                }
                return true;
            }
        });
        */
        BadRecyView mRecyclerView = v.findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        //mRecyclerView.addItemDecoration(new VerticalDividerItemDecoration.Builder(getContext()).margin(20).build());
        Sim sim = new Sim(getContext());
        String[] strs = {"小美女","小美女","小美女","小美女","小美女","小美女","小美女","小美女","小美女","小美女","小美女","小美女","小美女","小美女"};
        sim.setData(strs);
        mRecyclerView.setAdapter(sim);


    }


    static class Sim extends SimpleRecAdapter<String, Sim.ViewHolder> {

        public Sim(Context context) {
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

            holder.tvAreaName.setText(data.get(position)+s);
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            private TextView tvAreaName;

            public ViewHolder(View itemView) {
                super(itemView);
                tvAreaName = itemView.findViewById(R.id.tvAreaName);
            }
        }

    }


}
