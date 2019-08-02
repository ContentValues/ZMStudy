package xdroid.mwee.com.zmstudy.ui.view.tpie.animation;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.animation.DecelerateInterpolator;

/**
 * Author：created by SugarT
 * Time：2019/7/23 15
 */
public class ChartAnimator {
    private ValueAnimator animator;
    private ValueAnimator.AnimatorUpdateListener listener;
    private TimeInterpolator timeInterpolator;

    public ChartAnimator(ValueAnimator.AnimatorUpdateListener listener, TimeInterpolator timeInterpolator) {
        this.animator = new ValueAnimator();
        this.listener = listener;
        this.timeInterpolator = timeInterpolator;
    }

    public ChartAnimator(ValueAnimator.AnimatorUpdateListener listener) {
        this(listener, new DecelerateInterpolator());
    }

    /**
     * 使用时，需要 invalidate()
     * @param duration 动画时间
     * @param y 动画运动目标值
     */
    public void animatedY(long duration, float y) {
        if (this.animator != null && this.animator.isRunning()) {
            this.animator.cancel();
            this.animator.start();
        } else {
            this.animator = ValueAnimator.ofFloat(new float[]{0.0F, y}).setDuration(duration);
            this.animator.setInterpolator(this.timeInterpolator);
            this.animator.addUpdateListener(this.listener);
            this.animator.start();
        }

    }
}