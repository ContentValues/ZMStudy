package xdroid.mwee.com.mwcommon.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import xdroid.mwee.com.mwcommon.R;


/**
 */
public class SingleDialogFragment extends BaseDialogFragment {
    public static final String FRAGMENT_TAG = "SingleDialogFragment";
    private static final String KEY_CONTENT = "key_content";
    protected boolean mHasTitle;
    protected String mContentText;//提示框内容文字
    protected String mTitleText;
    private DialogResponseListener mSingleOnClickListener;
    private String mSingleBtnText;//单按钮文字
    private TextView mContentTv;

    public static SingleDialogFragment newInstance(DialogParamBundle bundle) {
        SingleDialogFragment singleDialogFragment = new SingleDialogFragment();
        singleDialogFragment.mParamBundle = (bundle);
        return singleDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSingleBtnText = mParamBundle.singleBtnText;
        mContentText = mParamBundle.contentText;
        mSingleOnClickListener = mParamBundle.singleBtnClick;
        mHasTitle = mParamBundle.hasTitle;
        mTitleText = mParamBundle.titleText;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_single_layout, container, false);
        TextView mTitleTv = (TextView) view.findViewById(R.id.tv_dialog_title);
        mContentTv = (TextView) view.findViewById(R.id.tv_dialog_msg);
        Button mSingleBtn = (Button) view.findViewById(R.id.bt_dialog_single);
        mSingleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mSingleOnClickListener != null) {
                    mSingleOnClickListener.response();
                }
            }
        });
        if (bIsBackable) {
            view.setOnClickListener(mSpaceClickListener);
        }
        mContentTv.setText(mContentText);
        if (!TextUtils.isEmpty(mSingleBtnText)) {
            mSingleBtn.setText(mSingleBtnText);
        }
        if (mHasTitle && !TextUtils.isEmpty(mTitleText)) {
            mTitleTv.setVisibility(View.VISIBLE);
            mTitleTv.setText(mTitleText);
        } else {
            mTitleTv.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            mContentTv.setText(savedInstanceState.getString(KEY_CONTENT));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mContentTv != null) {
            outState.putString(KEY_CONTENT, mContentTv.getText().toString());
        }
    }

    public void setMessage(String msg) {
        if (mContentTv != null) {
            mContentTv.setText(msg);
        }
    }

    public void setMessage(int msg) {
        if (mContentTv != null) {
            mContentTv.setText(msg);
        }
    }

}
