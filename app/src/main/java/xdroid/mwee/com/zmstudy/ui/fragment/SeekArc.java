package xdroid.mwee.com.zmstudy.ui.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.mwee.android.tools.DM;

import xdroid.mwee.com.zmstudy.R;

/**
 * Author：created by SugarT
 * Time：2019/8/1 09
 */
public class SeekArc extends View {


    private final int mAngleOffset = -90;
    /**
     * The Drawable for the seek arc thumbnail
     */
    private Drawable     mThumb;
    /**
     * The Width of the background arc for the SeekArc
     */
    private int mArcWidth = (int) DM.dpToPx(2);
    /**
     * The Angle through which to draw the arc (Max is 360)
     */
    private int mSweepAngle = 360;

    private RectF mArcRect = new RectF();
    private int mArcRadius = 0;

    private float mProgressSweep = 0;

    //private Paint mArcPaint;
    private Paint mProgressPaint;

    private int mTranslateX;
    private int mTranslateY;


    private int mThumbXPos;
    private int mThumbYPos;

    private double mTouchAngle;


    public SeekArc(Context context) {
        this(context,null);
    }

    public SeekArc(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SeekArc(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {

        final Resources res = getResources();

        int arcColor = res.getColor(R.color.progress_gray);
        int progressColor = res.getColor(android.R.color.holo_blue_light);

//        mArcPaint = new Paint();
//        mArcPaint.setColor(arcColor);
//        mArcPaint.setAntiAlias(true);
//        mArcPaint.setStyle(Paint.Style.STROKE);
//        mArcPaint.setStrokeWidth(mArcWidth);
        // mArcPaint.setAlpha(45);

        mProgressPaint = new Paint();
        mProgressPaint.setColor(progressColor);
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressPaint.setStrokeWidth(mArcWidth);


        mThumb = TextDrawable.builder()
                .beginConfig()
                .boardColor(arcColor)
                .textColor(arcColor)
                .withBorder(3).endConfig()
                .round()
                .build("0", getResources()
                        .getColor(android.R.color.white));
        mThumb.setBounds(-36, -36, 36, 36);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // Draw the arcs
        final int arcStart = 0 + mAngleOffset + 0;
        //final int arcSweep = mSweepAngle;
        //canvas.drawArc(mArcRect, arcStart, arcSweep, false, mArcPaint);
        canvas.drawArc(mArcRect, arcStart, mProgressSweep, false, mProgressPaint);

        // Draw the thumb nail
        canvas.translate(mTranslateX - mThumbXPos, mTranslateY - mThumbYPos);
        mThumb.draw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        /**
         * 确定圆弧范围
         */
        final int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int min = Math.min(width, height);
        float top = 0;
        float left = 0;
        int arcDiameter = 0;

        mTranslateX = (int) (width * 0.5f);
        mTranslateY = (int) (height * 0.5f);

        arcDiameter = min - getPaddingLeft();
        mArcRadius = arcDiameter / 2;
        top = height / 2 - (arcDiameter / 2);
        left = width / 2 - (arcDiameter / 2);
        mArcRect.set(left, top, left + arcDiameter, top + arcDiameter);

        int arcStart = (int) mProgressSweep + 0 + 0 + 90;
        mThumbXPos = (int) (mArcRadius * Math.cos(Math.toRadians(arcStart)));
        mThumbYPos = (int) (mArcRadius * Math.sin(Math.toRadians(arcStart)));

        //setTouchInSide();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //onStartTrackingTouch();
                updateOnTouch(event);
                break;
            case MotionEvent.ACTION_MOVE:
                updateOnTouch(event);
                break;
            case MotionEvent.ACTION_UP:
                //onStopTrackingTouch();
                setPressed(false);
                break;
            case MotionEvent.ACTION_CANCEL:
                //onStopTrackingTouch();
                setPressed(false);
                break;
        }

        return true;
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (mThumb != null && mThumb.isStateful()) {
            int[] state = getDrawableState();
            mThumb.setState(state);
        }
        invalidate();
    }

    private void updateOnTouch(MotionEvent event) {
        setPressed(true);
        mTouchAngle = getTouchDegrees(event.getX(), event.getY());
        mProgressSweep = (float) mTouchAngle;

        updateThumbPosition();

        invalidate();
    }

    private double getTouchDegrees(float xPos, float yPos) {
        float x = xPos - mTranslateX;
        float y = yPos - mTranslateY;
        double angle = Math.toDegrees(Math.atan2(y, x) + (Math.PI / 2) - Math.toRadians(0));
        return angle;
    }


    private void updateThumbPosition() {
        int thumbAngle = (int) (0 + mProgressSweep + 0 + 90);
        mThumbXPos = (int) (mArcRadius * Math.cos(Math.toRadians(thumbAngle)));
        mThumbYPos = (int) (mArcRadius * Math.sin(Math.toRadians(thumbAngle)));
    }
}
