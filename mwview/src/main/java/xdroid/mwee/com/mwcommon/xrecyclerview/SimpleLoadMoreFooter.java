package xdroid.mwee.com.mwcommon.xrecyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import xdroid.mwee.com.mwcommon.R;


public class SimpleLoadMoreFooter extends RelativeLayout implements LoadMoreUIHandler {

    TextView tvMsg;
    ProgressBar progressBar;

    public SimpleLoadMoreFooter(Context context) {
        this(context, null);
    }

    public SimpleLoadMoreFooter(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleLoadMoreFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView();
    }

    private void setupView() {
        inflate(getContext(), R.layout.x_view_footer_load_more_simple, this);
        tvMsg = (TextView) findViewById(R.id.tv_msg);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void onLoading() {
        setVisibility(VISIBLE);
        tvMsg.setText("加载中");
        progressBar.setVisibility(VISIBLE);
    }

    @Override
    public void onLoadFinish(boolean hasMore) {
        if (hasMore) {
            setVisibility(GONE);
        } else {
            setVisibility(VISIBLE);
            tvMsg.setText("没有更多数据");
            progressBar.setVisibility(GONE);
        }
    }
}

