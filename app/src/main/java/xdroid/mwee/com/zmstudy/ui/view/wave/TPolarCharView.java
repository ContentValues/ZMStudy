package xdroid.mwee.com.zmstudy.ui.view.wave;

import android.view.View;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.FloatRange;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

import xdroid.mwee.com.zmstudy.R;

/**
 * Author：created by SugarT
 * Time：2019/7/25 13
 */
public class TPolarCharView extends View {

    //默认继续绘制
    private int mMode = DrawMode.CONTINUE;
    private Paint continuePaint;


    public void setDrawMode(@DrawMode int mode) {
        mMode = mode;
        initPaintsAndAnimator(w);
        startAnimation();
        invalidate();
    }


    /**
     * 当position中的最大值不为1.0f时 即无法画完整个圆的情况 判断绘制情况{@link DrawMode ==1} ,
     * 则继续绘制最大值后的圆,颜色为continueColor
     * 否则 最大值后的圆保持空白  不进行绘制
     */
    @IntDef({DrawMode.STOP, DrawMode.CONTINUE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DrawMode {
        int CONTINUE = 0;
        int STOP     = 1;
    }


    private int len;//颜色的数量

    /**
     * 绘制轮廓的外围矩形
     */
    private RectF mRectF;
    /**
     * 轮廓宽度
     */
    private int strokeWidth = 50;
    private TextPaint textPaint;

    private Context       context;
    /**
     * 角度变化的动画对象
     */
    private ValueAnimator mAnimator;

    private int mCurrentAngle;
    /**
     * 颜色数组  默认六种颜色
     */
    private int colors[] = new int[]{Color.parseColor("#FEE407")
            , Color.parseColor("#13CFFD"),
            Color.parseColor("#FC732E"),
            Color.parseColor("#33FC2E"),
            Color.parseColor("#BF2EFC"),
            Color.parseColor("#FC2E2E")
    };
    /**
     * 颜色对应位置
     */
    private float postions[]=new float[]{0.33f,0.33f,0.33f};
    /**
     * 动画时间
     */
    private int duration = 2000;

    /**
     * 相应颜色的渐变色画笔集合
     */
    private ArrayList<Paint> paints;

    /**
     * @param error  误差
     */
    float error = 0.0f;
    /**
     * 渐变的最终透明度
     */
    private int alphaInt = 80;

    /**
     * 控件宽度,内圆半径
     */
    private int w;
    /**
     * 内圆正方形原点X,标题X,标题Y
     */
    private float innerRectX, titleX, titleY;
    /**
     * 内圆正方形，文字绘制区域判断
     */
    private RectF textRect;
    /**
     * 标题文字大小，副标题文字大小
     */
    private int titleSizeDp = 28, infoSizeDp = 16;

    private String title = "title", info = "info";
    /**
     * 给定的最大绘制位置
     */
    private int maxPos = 0;

    public TPolarCharView(Context context) {
        this(context, null);
    }

    public TPolarCharView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TPolarCharView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        //关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PieChartView);
//        title = typedArray.getString(R.styleable.PieChartView_title);
//        info = typedArray.getString(R.styleable.PieChartView_subTitle);
        titleSizeDp = (int) typedArray.getDimension(R.styleable.PieChartView_titleSize, 28);
        infoSizeDp = (int) typedArray.getDimension(R.styleable.PieChartView_subTitleSize, 16);
        strokeWidth = (int) typedArray.getDimension(R.styleable.PieChartView_strokeWidth, 50);
        alphaInt = (int) (typedArray.getFloat(R.styleable.PieChartView_colorAlpha, 0.3f) * 255);
        mMode = typedArray.getBoolean(R.styleable.PieChartView_isContinueMode, true) ? DrawMode.CONTINUE : DrawMode.STOP;
        duration = typedArray.getInt(R.styleable.PieChartView_anim_duration, 2000);
        typedArray.recycle();

        paints = new ArrayList<>();
        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);
        textPaint.setColor(Color.BLACK);


    }
    public int  getResource(String resName,String resType){
        return context.getResources().getIdentifier(resName,resType,context.getPackageName());
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


        //控件宽度
        this.w = w;

        /**
         * 弧度的半径是从圆心到stoke的中线的距离，若直接设置w为半径那么有一半的stroke不在预料之中
         */
        mRectF = new RectF(strokeWidth >> 1, strokeWidth >> 1, w - (strokeWidth >> 1), w - (strokeWidth >> 1));


        //内圆的半径
        int innerRadius = (w - (strokeWidth << 1)) >> 1;

        double cos = Math.sqrt(2);//根号2
        //内部正方形原点X
        innerRectX = (float) ((w >> 1) - innerRadius / cos);


        //文字原点Y
        titleY = (w >> 1);

        //文字绘制区域
        textRect = new RectF(innerRectX, innerRectX, innerRectX + ((float) (innerRadius / cos * 2)), innerRectX + ((float) (innerRadius / cos * 2)));

        //测量标题原点X
        caculateTitleX();


        if (postions != null) {

            initPaintsAndAnimator(w);
            startAnimation();
        }

    }

    private void caculateTitleX() {
        textPaint.setTextSize(getTitleSizeDp());
        //测量文字边界
        Rect lineRect = new Rect();
        textPaint.getTextBounds(title, 0, title.length(), lineRect);
        //文字原点X
        titleX = innerRectX + (textRect.width() - lineRect.width()) / 2;
    }

    private void initAnimator() {
        mAnimator = ValueAnimator.ofInt(0, 360).setDuration(duration);
        mAnimator.setInterpolator(new LinearInterpolator());
        if (postions != null) {
            maxPos = (int) (postions[len - 1] * 360);
        }
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentAngle = (int) animation.getAnimatedValue();
                /**
                 * 当最后一种颜色无法占满整个圆的时候 并且绘制模式为STOP 则不进行绘制
                 */
                if (mCurrentAngle >= maxPos && mMode == DrawMode.STOP) {
                    mCurrentAngle = maxPos;
                    mAnimator.cancel();
                }
                invalidate();

            }
        });
    }

    private void startAnimation() {
        mAnimator.start();
    }

    /**
     * 计算和初始化绘制各段使用的paint, 动画
     *
     * @param w 控件宽度
     */
    private void initPaintsAndAnimator(int w) {

        //数组长度
        len = postions.length;

        //初始化动画
        initAnimator();

        /**
         * 初始化continuePaint
         * 当最后一种颜色无法占满整个圆的时候 对绘制模式进行判断并初始化画笔
         */
        if (mMode == DrawMode.CONTINUE && postions[len - 1] < 1f) {
            continuePaint = new Paint();
            continuePaint.setAntiAlias(true);
            continuePaint.setDither(true);
            continuePaint.setStyle(Paint.Style.STROKE);
            continuePaint.setStrokeWidth(strokeWidth);
            int           continueColor = Color.LTGRAY;
            SweepGradient sweepGradient = new SweepGradient(w >> 1, w >> 1, new int[]{continueColor, continueColor}, new float[]{postions[0], 1});
            continuePaint.setShader(sweepGradient);
        }

        float[] pos;
        for (int i = 0; i < len; i++) {
            Paint mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(strokeWidth);
            paints.add(mPaint);


            //获取色值的argb
            int originColor = colors[i];

            int red   = (originColor & 0x00ff0000) >> 16;
            int green = (originColor & 0x0000ff00) >> 8;
            int blue  = (originColor & 0x000000ff);


            int lightColor = Color.argb(alphaInt, red, green, blue);
            if (i == 0) {
                pos = new float[]{0, postions[0]};
            } else {
                pos = new float[]{postions[i - 1], postions[i]};
            }

            SweepGradient sweepGradient = new SweepGradient(w >> 1, w >> 1, new int[]{originColor, lightColor}, pos);
            paints.get(i).setShader(sweepGradient);
        }


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.rotate(-90, w >> 1, w >> 1);
        if (postions != null) {
            drawMain(canvas);
        }
        canvas.restore();

//        if(BuildConfig.DEBUG){
//            canvas.drawLine(0,0,0,w,textPaint);
//            canvas.drawLine(0,w>>1,w,w>>1,textPaint);
//            canvas.drawRect(textRect,textPaint);
//        }


        textPaint.setTextSize(getTitleSizeDp());
        textPaint.setShadowLayer(10, 4, 4, Color.parseColor("#aaaaaa"));
        canvas.drawText(title, titleX, titleY, textPaint);
        textPaint.clearShadowLayer();
        textPaint.setTextSize(getInfoSizeDp());
        float infoWidth = textPaint.measureText(info);
        canvas.drawText(info, innerRectX + (textRect.width() - infoWidth) / 2, textRect.bottom - 20, textPaint);
    }


    /**
     * 根据绘制各段渐变色
     *
     * @param canvas
     */
    private void drawMain(Canvas canvas) {

        for (int i = 0; i < len; i++) {
            if (mCurrentAngle <= 360 * postions[i]) {//如果当前值小于区段阈值
                if (i == 0) {
                    //第一段动画绘制
                    canvas.drawArc(mRectF, 0, mCurrentAngle, false, paints.get(i));
                } else {
                    //第一段直接绘制
                    canvas.drawArc(mRectF, 0, 360 * postions[0] + error, false, paints.get(0));

                    //动画绘制接下来的段

                    canvas.drawArc(mRectF, 360 * postions[i - 1], mCurrentAngle - 360 * postions[i - 1] + error, false, paints.get(i));

                    for (int i1 = i - 1; i1 > 0; i1--) {
                        //从当前段的前一段开始直接循环绘制,直到第二段
                        canvas.drawArc(mRectF, 360 * postions[i1 - 1], 360 * (postions[i1] - postions[i1 - 1]) + error, false, paints.get(i1));
                    }

                }
                break;
            } else if (i == len - 1) {
                checkModeDraw(canvas, i);

            }
        }
    }

    /**
     * 判断是否继续绘制   并执行绘制
     *
     * @param canvas
     * @param i
     */
    private void checkModeDraw(Canvas canvas, int i) {
        //最后一个颜色画完并且动画未结束  判断进行绘制模式
        if (mMode == DrawMode.CONTINUE) {
            if (continuePaint != null) {
                //第一段动画绘制
                canvas.drawArc(mRectF, 0, mCurrentAngle, false, paints.get(0));

                //动画绘制填充段
                canvas.drawArc(mRectF, 360 * postions[i], mCurrentAngle - 360 * postions[i] + error, false, continuePaint);

                for (int i1 = i; i1 > 0; i1--) {
                    //从当前段开始直接循环绘制，直到第二段
                    canvas.drawArc(mRectF, 360 * postions[i1 - 1], 360 * (postions[i1] - postions[i1 - 1]) + error, false, paints.get(i1));
                }
            }
        } else if (mMode == DrawMode.STOP) {

        } else {
            throw new IllegalStateException("Please use one DrawMode contained in PieChartView ！");
        }
    }

    /**
     * 可预览
     *
     * @return
     */
    @Override
    public boolean isInEditMode() {
        return true;
    }

    /**
     * 设置比例后开始动画
     *
     * @param postions 颜色对应的弧度比例   {0,1};
     */
    public void setPostionsThenStart(@NonNull float[] postions) {
        this.postions = postions;
        initPaintsAndAnimator(w);
        startAnimation();
        invalidate();
    }

    /**
     * 设置比例，颜色后开始动画
     *
     * @param postions 颜色对应的弧度比例   {0,1};
     * @param colors   颜色数组
     */
    public void setDataThenStart(@NonNull float[] postions, @Nullable int[] colors) {
        this.postions = postions;
        if (colors != null) {
            this.colors = colors;
        }
        initPaintsAndAnimator(w);
        startAnimation();
        invalidate();
    }

    /**
     * @param postions 颜色对应的弧度比例   {0,1};
     */
    public void setPostions(float[] postions) {
        this.postions = postions;
    }

    /**
     * @param colors 颜色数组
     */
    public void setColors(int[] colors) {
        this.colors = colors;
    }


    /**
     * 设置标题 ,副标题并开始动画
     *
     * @param title
     */
    public void setTextThenStart(String title, String info) {
        this.title = title;
        this.info = info;
        caculateTitleX();
        initPaintsAndAnimator(w);
        startAnimation();
        invalidate();
    }


    /**
     * 设置标题  大字
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 设置副标题 小字
     *
     * @param info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * 设置颜色渐变程度   1.0为没有渐变色
     *
     * @param alpha
     */
    public void setAlpha(@FloatRange(from = 0, to = 1.0f) float alpha) {
        this.alphaInt = alphaInt * 255;
    }


    /**
     * @return Dp
     */
    public int getTitleSizeDp() {
        return dip2px(context, titleSizeDp);
    }

    /**
     * @return Dp
     */
    public int getInfoSizeDp() {
        return dip2px(context, infoSizeDp);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
