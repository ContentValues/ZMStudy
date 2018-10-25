package xdroid.mwee.com.zmstudy.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import xdroid.mwee.com.mwcommon.base.XFragment;
import xdroid.mwee.com.mwcommon.base.XLazyFragment;
import xdroid.mwee.com.zmstudy.R;

/**
 * Created by zhangmin on 2018/10/16.
 */

public class AnimatorViewFragment extends XLazyFragment {

    private Button mAnimatorBtn;
    private TextView mAnimatorViewTv;
    private ImageView mAnimatorViewImg;

    public static AnimatorViewFragment newInstance() {
        return new AnimatorViewFragment();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        mAnimatorBtn = findViewById(R.id.mAnimatorBtn);
        mAnimatorViewTv = findViewById(R.id.mAnimatorViewTv);
        mAnimatorViewImg = findViewById(R.id.mAnimatorViewImg);


        /*mAnimatorViewTv.getAlpha();
        mAnimatorViewTv.getRotationX();
        mAnimatorViewTv.getRotationY();
        mAnimatorViewTv.getScaleX();
        mAnimatorViewTv.getScaleY();
        mAnimatorViewTv.getTranslationX();
        mAnimatorViewTv.getTranslationY();*/

        mAnimatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ObjectAnimator objectAnimatorAlpha = ObjectAnimator.ofFloat(mAnimatorViewTv,"alpha",1f,0f,1f);
                objectAnimatorAlpha.setDuration(3000);
                objectAnimatorAlpha.start();


                ObjectAnimator rotate = ObjectAnimator.ofFloat(mAnimatorViewImg, "rotation", 0f, 360f);
                ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(mAnimatorViewImg, "alpha", 1f, 0f, 1f);
                ObjectAnimator moveIn = ObjectAnimator.ofFloat(mAnimatorViewImg, "translationX", 0,-500f, 0f);
                ObjectAnimator moveY = ObjectAnimator.ofFloat(mAnimatorViewImg, "translationY", 0,-500f, 0f);
                AnimatorSet animSet = new AnimatorSet();
                animSet.play(rotate).with(fadeInOut).after(moveIn).after(moveY);
                animSet.setDuration(3000);
                animSet.start();

            }
        });



    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_animatorview_layout;
    }
}
