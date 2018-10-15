package xdroid.mwee.com.zmstudy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.Random;

import xdroid.mwee.com.zmstudy.R;

/**
 * Created by zhangmin on 2018/10/12.
 */

public class ValidationCodeView extends View {

    /**
     * 验证码的位数
     */
    private int mCodeCount;
    /**
     * 验证码字符的大小
     */
    private float mTextSize;

    /**
     * 验证码文本画笔
     */
    private Paint mTextPaint;


    /**
     * 验证码字符串的显示宽度
     */
    private float mTextWidth;

    /**
     * 验证码字符串
     */
    private String mCodeString;


    /**
     * 控件的宽度
     */
    private int mWidth;
    /**
     * 控件的高度
     */
    private int mHeight;

    public ValidationCodeView(Context context) {
        this(context, null);
    }

    public ValidationCodeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ValidationCodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IndentifyingCode);
        mCodeCount = typedArray.getInteger(R.styleable.IndentifyingCode_codeCount, 5); // 获取布局中验证码位数属性值，默认为5个
        // 获取布局中验证码文字的大小，默认为20sp
        mTextSize = typedArray.getDimension(R.styleable.IndentifyingCode_textSize, typedArray.getDimensionPixelSize(R.styleable.IndentifyingCode_textSize, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, getResources().getDisplayMetrics())));
        //mTextSize = typedArray.getDimension(R.styleable.IndentifyingCode_textSize, 20);
        // 一个好的习惯是用完资源要记得回收，就想打开数据库和IO流用完后要记得关闭一样
        typedArray.recycle();
        init();
    }

    private void init() {

        // 生成随机数字和字母组合
        mCodeString = getCharAndNumr(mCodeCount);

        mTextPaint = new Paint();
        mTextPaint.setStrokeWidth(6);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setAntiAlias(true);

        mTextPaint.setUnderlineText(true);
        // 取得验证码字符串显示的宽度值
        mTextWidth = mTextPaint.measureText(mCodeString);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 分别测量控件的宽度和高度，基本为模板方法
        int measureWidth = measureWidth(widthMeasureSpec);
        int measureHeight = measureHeight(heightMeasureSpec);

        // 其实这个方法最终会调用setMeasuredDimension(int measureWidth,int measureHeight);
        // 将测量出来的宽高设置进去完成测量
        setMeasuredDimension(measureWidth, measureHeight);
    }


    /**
     * 测量宽度
     *
     * @param widthMeasureSpec
     */
    private int measureWidth(int widthMeasureSpec) {
        int result = (int) (mTextWidth * 1.8f);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            // 精确测量模式，即布局文件中layout_width或layout_height一般为精确的值或match_parent
            result = widthSize; // 既然是精确模式，那么直接返回测量的宽度即可
        } else {
            if (widthMode == MeasureSpec.AT_MOST) {
                // 最大值模式，即布局文件中layout_width或layout_height一般为wrap_content
                result = Math.min(result, widthSize);
            }
        }
        return result;
    }


    /**
     * 测量高度
     *
     * @param heightMeasureSpec
     */
    private int measureHeight(int heightMeasureSpec) {
        int result = (int) (mTextWidth / 1.6f);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            // 精确测量模式，即布局文件中layout_width或layout_height一般为精确的值或match_parent
            result = heightSize; // 既然是精确模式，那么直接返回测量的宽度即可
        } else {
            if (heightMode == MeasureSpec.AT_MOST) {
                // 最大值模式，即布局文件中layout_width或layout_height一般为wrap_content
                result = Math.min(result, heightSize);
            }
        }
        return result;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 获取控件的宽和高，此时已经测量完成
        mHeight = getHeight();
        mWidth = getWidth();
        int length = mCodeString.length();
        float charLength = mTextWidth / length;
        Random mRandom = new Random();
        for (int i = 1; i <= length; i++) {
            int offsetDegree = mRandom.nextInt(15);
            // 这里只会产生0和1，如果是1那么正旋转正角度，否则旋转负角度
            offsetDegree = mRandom.nextInt(2) == 1 ? offsetDegree : -offsetDegree;
            canvas.save();
            canvas.rotate(offsetDegree, mWidth / 2, mHeight / 2);
            // 给画笔设置随机颜色
            mTextPaint.setARGB(255, mRandom.nextInt(200) + 20, mRandom.nextInt(200) + 20, mRandom.nextInt(200) + 20);
            canvas.drawText(String.valueOf(mCodeString.charAt(i - 1)), (i - 1) * charLength * 1.6f + 30, mHeight * 2 / 3f, mTextPaint);
            canvas.restore();
        }
    }


    /**
     * java生成随机数字和字母组合
     *
     * @param
     * @return
     */
    public static String getCharAndNumr(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

}
