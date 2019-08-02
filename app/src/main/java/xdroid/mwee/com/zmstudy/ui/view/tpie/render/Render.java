package xdroid.mwee.com.zmstudy.ui.view.tpie.render;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Author：created by SugarT
 * Time：2019/7/23 14
 */
public abstract class Render {
    public Render() {
    }

    protected void textCenter(String[] strings, Paint paint, Canvas canvas, PointF point, Paint.Align align) {
        paint.setTextAlign(align);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;
        int length = strings.length;
        float total = (float)(length - 1) * (-top + bottom) + -fontMetrics.ascent + fontMetrics.descent;
        float offset = total / 2.0F - bottom;

        for(int i = 0; i < length; ++i) {
            float yAxis = (float)(-(length - i - 1)) * (-top + bottom) + offset;
            canvas.drawText(strings[i], point.x, point.y + yAxis, paint);
        }

    }
}
