package xdroid.mwee.com.zmstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mwee.android.tools.LogUtil;

/**
 * Created by zhangmin on 2018/10/12.
 */

public class CircleView extends View {

    private Paint mCirClePaint;

    public CircleView(Context context) {
        this(context,null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mCirClePaint = new Paint();
        mCirClePaint.setAntiAlias(true);
        mCirClePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mCirClePaint.setStrokeWidth(10);
        mCirClePaint.setColor(Color.BLUE);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        LogUtil.log("CircleView","onMeasure()----widthMeasureSpec---->" + widthMeasureSpec + "        heightMeasureSpec-->" + heightMeasureSpec);
        LogUtil.log("CircleView","widthSize---->" + widthSize);
        LogUtil.log("CircleView","heightSize---->" + heightSize);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtil.log("CircleView","onDraw()");

        LogUtil.log("CircleView","onDraw()2222");

        canvas.save();

        //int r = getMeasuredWidth() / 2;//也可以是getMeasuredHeight()/2,本例中我们已经将宽高设置相等了

        //LogUtil.log("CircleView","getMeasuredWidth() -->"+getMeasuredWidth() );


        int r = getMeasuredHeight() / 2;

        LogUtil.log("CircleView","getMeasuredHeight() -->"+getMeasuredHeight() );

        //圆心的横坐标为当前的View的左边起始位置+半径
        int centerX = getLeft() + r;
        LogUtil.log("CircleView","getLeft()-->"+getLeft());
        LogUtil.log("CircleView","centerY()-->"+centerX);

        //圆心的纵坐标为当前的View的顶部起始位置+半径
        int centerY = getTop() + r;
        LogUtil.log("CircleView","getTop()-->"+getTop());
        LogUtil.log("CircleView","centerY()-->"+centerY);
        canvas.drawCircle(centerX, centerY, r, mCirClePaint);
        canvas.restore();
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogUtil.log("CircleView","onLayout()");
    }
}
