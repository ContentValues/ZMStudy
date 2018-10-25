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
public class ExecuteDialogFragment extends BaseDialogFragment {
    public static final String FRAGMENT_TAG = "ExecuteDialogFragment";
    protected boolean mHasTitle;
    protected String mTitleText;
    private DialogResponseListener mNegitiveClickListener;
    private DialogResponseListener mPositiveOnClickListener;
    private String mLeftBtnText;//左按钮文字
    private String mRightBtnText;//右按钮文字
    private String mContentText;//提示框内容文字

    public static ExecuteDialogFragment newInstance(DialogParamBundle bundle) {
        ExecuteDialogFragment executeDialogFragment = new ExecuteDialogFragment();
        executeDialogFragment.mParamBundle = bundle;
        return executeDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mParamBundle != null) {
            mLeftBtnText = mParamBundle.negativeBtnText;
            mRightBtnText = mParamBundle.positiveBtnText;
            mContentText = mParamBundle.contentText;
            mHasTitle = mParamBundle.hasTitle;
            mTitleText = mParamBundle.titleText;
            mNegitiveClickListener = mParamBundle.negitiveveClick;
            mPositiveOnClickListener = mParamBundle.positiveClick;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_excute_layout, container, false);
        TextView mTitleTv = (TextView) view.findViewById(R.id.tv_dialog_title);
        TextView mContentTv = (TextView) view.findViewById(R.id.tv_dialog_msg);
        Button mLeftBtn = (Button) view.findViewById(R.id.bt_dialog_left);
        Button mRightBtn = (Button) view.findViewById(R.id.bt_dialog_right);
        if (mHasTitle && !TextUtils.isEmpty(mTitleText)) {
            mTitleTv.setVisibility(View.VISIBLE);
            mTitleTv.setText(mTitleText);
        } else {
            mTitleTv.setVisibility(View.GONE);
        }
        mContentTv.setText(mContentText);
        if (!TextUtils.isEmpty(mLeftBtnText)) {
            mLeftBtn.setText(mLeftBtnText);
        }
        if (!TextUtils.isEmpty(mRightBtnText)) {
            mRightBtn.setText(mRightBtnText);
        }
        mLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mNegitiveClickListener != null) {
                    mNegitiveClickListener.response();
                }

            }
        });
        mRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mPositiveOnClickListener != null) {
                    mPositiveOnClickListener.response();
                }
            }
        });
        if (bIsBackable) {
            view.setOnClickListener(mSpaceClickListener);
        }
        return view;
    }
}
