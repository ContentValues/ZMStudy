package xdroid.mwee.com.mwcommon.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import xdroid.mwee.com.mwcommon.R;


/**
 * Progress,Floating Progress with various of function
 */
public class Progress extends BaseDialogFragment {
    private TextView tv_loadingText;
    private RotateAnimation mAnim;


    public static Progress newInstance(DialogParamBundle bundle) {
        Progress progress = new Progress();
        progress.mParamBundle = bundle;
        return progress;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.progress_layout, container, false);
        layoutView.setOnClickListener(mSpaceClickListener);
        tv_loadingText = (TextView) layoutView.findViewById(R.id.tip);
        ImageView iv_route = (ImageView) layoutView.findViewById(R.id.iv_rrroute);
        initAnim(iv_route);
        if (!TextUtils.isEmpty(mContentTxt)) {
            tv_loadingText.setText(mContentTxt);
        }
        return layoutView;
    }

    public void updateText(final String str) {
        if (str == null) {
            return;
        }
        if (Looper.getMainLooper() != Looper.myLooper()) {
            tv_loadingText.post(new Runnable() {
                @Override
                public void run() {
                    updateText(str);
                }
            });
        } else {
            mContentTxt = str;
            tv_loadingText.setText(str);
        }
    }

    public void updateText(@StringRes int strID) {
        try {
            updateText(getString(strID));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

    private void initAnim(ImageView imageView) {
        if (mAnim == null) {
            mAnim = new RotateAnimation(360, 0, Animation.RESTART, 0.5f,
                    Animation.RESTART, 0.5f);
            mAnim.setDuration(2000);
            mAnim.setRepeatCount(Animation.INFINITE);
            mAnim.setRepeatMode(Animation.RESTART);
            mAnim.setStartTime(Animation.START_ON_FIRST_FRAME);
        }
        imageView.startAnimation(mAnim);
    }

    @Override
    public void dismiss() {
        if (mAnim != null) {
            mAnim.cancel();
        }
        if (Looper.getMainLooper() != Looper.myLooper()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    dismiss();
                }
            });
        } else {
            super.dismiss();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
