package xdroid.mwee.com.zmstudy.ui.view.rose;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.LinkedList;
import java.util.List;

import xdroid.mwee.com.zmstudy.R;

/**
 * @ClassName RoseChart01View
 * @Description 南丁格尔玫瑰图 的例子
 */
public class RoseChart02View extends View {

    public RoseChart02View(Context context) {
        this(context, null);
    }

    public RoseChart02View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoseChart02View(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    //数据源
    List<PieData> roseData = new LinkedList<>();


    private float mWidth = 0.0f;
    private float mHeight = 0.0f;


    /**
     * 外部圆弧画笔
     */
    private Paint mPaintArc = null;

    //开放标签画笔让用户设置
    private Paint mPaintLabel = null;

    private Paint mPaintSubLabel = null;

    /**
     * 中心点的文字画笔
     */
    private Paint mPaintCenterLabel = null;

    //描边线的画笔
    Paint mPaintStokenLabel;

    Paint mPaintInner;


    private void initView() {

        mPaintArc = new Paint();
        mPaintArc.setAntiAlias(true);

        mPaintLabel = new Paint();
        mPaintLabel.setColor(Color.parseColor("#61A4E0"));
        mPaintLabel.setAntiAlias(true);
        mPaintLabel.setTextSize(dip2px(getContext(), 14));


        mPaintSubLabel = new Paint();
        mPaintSubLabel.setColor(Color.WHITE);
        mPaintSubLabel.setAntiAlias(true);
        mPaintSubLabel.setTextSize(dip2px(getContext(), 16));


        mPaintCenterLabel = new Paint();
        mPaintCenterLabel.setColor(Color.WHITE);
        mPaintCenterLabel.setAntiAlias(true);
        mPaintCenterLabel.setTextSize(dip2px(getContext(), 24));

        /**
         * 内环
         */
        mPaintInner = new Paint();
        mPaintInner.setColor(Color.parseColor("#094C9E"));
        mPaintInner.setStyle(Paint.Style.FILL);
        mPaintInner.setAntiAlias(true);


        /**
         * 设置文字的引导线
         */
        mPaintStokenLabel = new Paint();
        mPaintStokenLabel.setColor(Color.WHITE);
        mPaintStokenLabel.setStrokeWidth(dip2px(getContext(), 1));
        mPaintStokenLabel.setStyle(Paint.Style.STROKE);

        roseData.clear();
        //数据源
        //PieData(标签，百分比，在饼图中对应的颜色)
        roseData.add(new PieData("4级系统", 60, Color.rgb(77, 83, 97), 7));
        roseData.add(new PieData("3级系统", 80, Color.rgb(148, 159, 181), 16));
        roseData.add(new PieData("2级系统", 100, Color.rgb(253, 180, 90), 19));
        roseData.add(new PieData("1级系统", 100, Color.rgb(39, 51, 72), 35));
        roseData.add(new PieData("5级系统", 100, Color.rgb(255, 135, 195), 12));
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);
        mWidth = width;
        mHeight = height;
    }


    //计算中心点横坐标
    float cirX;

    //计算中心点纵坐标
    float cirY;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

         cirX = mWidth / 2f;

         cirY = mHeight / 2f;

        //外圈圆半径大小 默认缩放0.7
        float radius = (float) (cirX * 0.5);

        //计算纵向方向的空白区域
        float freeSection = mHeight - 2 * radius;

        //纵向向上平移三分之二 以空大下方空闲区域
        cirY = cirY - (freeSection / 5) * 1;

        //内圈默认是外圈的三分之一
        float radiusInner = radius / 3;

        int totalAngle = 360;

        ///扫描的角度  默认都是相同的
        float arcAngle = totalAngle / roseData.size();

        //初始偏移角度
        float mOffsetAngle = 0.0f;//180;

        ///内圈显示的文字 89
        int centerText = 0;

        for (PieData cData : roseData) {

            centerText += cData.mSubValue;

            mPaintArc.setColor(cData.getSliceColor());
            //将百分比转换为新扇区的半径
            double p = cData.getPercentage() / 100;
            float newRaidus = radius * (float) p;

            drawMarkerLineAndText(canvas, newRaidus, mOffsetAngle + arcAngle / 2, cData.getLabel(), cData.mSubValue + "");

            //在饼图中显示所占比例
            RectF nRF = new RectF(cirX - newRaidus, cirY - newRaidus, cirX + newRaidus, cirY + newRaidus);
            canvas.drawArc(nRF, mOffsetAngle + 0, arcAngle, true, mPaintArc);

            //下次的起始角度
            mOffsetAngle = mOffsetAngle + arcAngle;
        }

        //内环
        canvas.drawCircle(cirX, cirY, radiusInner, mPaintInner);

        //画中心点文字
        float mTetxWidth = mPaintCenterLabel.measureText(centerText + "", 0, String.valueOf(centerText).length());
        float dx = mWidth / 2 - mTetxWidth / 2;
        Paint.FontMetricsInt fontMetricsInt = mPaintCenterLabel.getFontMetricsInt();
        float dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        float baseLine = cirY + dy;
        canvas.drawText(centerText + "", dx, baseLine, mPaintCenterLabel);


        //背景画笔
        Paint mPaintTitleSubBackGround = new Paint();
        mPaintTitleSubBackGround.setAntiAlias(true);
        mPaintTitleSubBackGround.setColor(Color.rgb(5, 56, 123));

        //背景矩阵
        RectF rectF = new RectF();
        rectF.left = cirX - dip2px(getContext(), 100);
        rectF.top = getHeight() - dip2px(getContext(), 16) - dip2px(getContext(), 30);
        rectF.right = cirX + dip2px(getContext(), 100);
        rectF.bottom = getHeight() - dip2px(getContext(), 16) + 0;
        //画背景
        canvas.drawRect(rectF, mPaintTitleSubBackGround);


        //底部标识
        Paint mPaintTitleSub = new Paint();
        mPaintTitleSub.setAntiAlias(true);
        mPaintTitleSub.setTextSize(dip2px(getContext(), 14));
        mPaintTitleSub.setColor(Color.WHITE);

        float mSubTetxWidth = mPaintTitleSub.measureText(titleSub, 0, titleSub.length());
        float dxSub = rectF.centerX() - mSubTetxWidth / 2;
        Paint.FontMetricsInt fontMetricsIntSub = mPaintTitleSub.getFontMetricsInt();
        float dySub = (fontMetricsIntSub.bottom - fontMetricsIntSub.top) / 2 - fontMetricsIntSub.bottom;
        float baseLineSub = rectF.centerY() + dySub;
        canvas.drawText(titleSub, dxSub+ dip2px(getContext(), 4), baseLineSub, mPaintTitleSub);

        //画底部标识 4级系统:卡系统
        Paint mPaintTitleSubImage = new Paint();
        mPaintTitleSubImage.setAntiAlias(true);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_third_unmapped);
        canvas.drawBitmap(bitmap, dxSub - bitmap.getWidth() - dip2px(getContext(), 2), rectF.centerY() - bitmap.getHeight() / 2, mPaintTitleSubImage);
        bitmap.recycle();

    }

    private String titleSub = "四级系统:卡系统";


    /**
     * 标记线长度
     */
    private float markerLineLength = dip2px(getContext(), 15);

    /**
     * 折线的长度
     */
    private float markerLineBend = dip2px(getContext(), 10);

    /**
     * 绘制标注线和标记文字
     *
     * @param canvas               画布
     * @param pieChartCircleRadius 半径
     * @param rotateAngel          标记线和水平相差旋转的角度
     */
    protected void drawMarkerLineAndText(Canvas canvas, float pieChartCircleRadius, float rotateAngel, String text, String mSubValue) {

        Path path = new Path();
        path.close();
        path.moveTo(cirX, cirY);
        final float x = (float) (cirX + (markerLineLength + pieChartCircleRadius) * Math.cos(Math.toRadians(rotateAngel)));
        final float y = (float) (cirY + (markerLineLength + pieChartCircleRadius) * Math.sin(Math.toRadians(rotateAngel)));
        path.lineTo(x, y);
        float landLineX;
        if (270f > rotateAngel && rotateAngel > 90f) {
            landLineX = x - markerLineBend;
        } else {
            landLineX = x + markerLineBend;
        }
        path.lineTo(landLineX, y);
        canvas.drawPath(path, mPaintStokenLabel);

        //文字的宽度
        float textWidth = mPaintLabel.measureText(text);
        Paint.FontMetricsInt fontMetricsInt = mPaintLabel.getFontMetricsInt();
        //文字的高度
        float dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        //默认的偏移量
        float offestY = dip2px(getContext(), 7);

        if (270f > rotateAngel && rotateAngel > 90f) {
            canvas.drawText(text, landLineX - textWidth, y - offestY, mPaintLabel);
            canvas.drawText(mSubValue, landLineX - textWidth, y + dy + offestY, mPaintSubLabel);
//            float offerstX = landLineX - textWidth - getLeft();
//            if (offerstX > 0) {
//                canvas.drawText(text, landLineX - textWidth, y - offestY, mPaintLabel);
//                canvas.drawText(mSubValue, landLineX - textWidth, y + dy + offestY, mPaintSubLabel);
//            } else {
//                canvas.drawText(text, landLineX + offerstX, y - offestY, mPaintLabel);
//                canvas.drawText(mSubValue, landLineX + offerstX, y + dy + offestY, mPaintSubLabel);
//            }
        } else {
            //偏移量
            canvas.drawText(text, landLineX, y - offestY, mPaintLabel);
            canvas.drawText(mSubValue, landLineX, y + dy + offestY, mPaintSubLabel);
//            float offerstX = landLineX + textWidth - getRight();
//            if (offerstX > 0) {
//                canvas.drawText(text, landLineX - offerstX, y - offestY, mPaintLabel);
//                canvas.drawText(mSubValue, landLineX - offerstX, y + dy + offestY, mPaintSubLabel);
//            } else {
//                canvas.drawText(text, landLineX, y - offestY, mPaintLabel);
//                canvas.drawText(mSubValue, landLineX, y + dy + offestY, mPaintSubLabel);
//            }
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
