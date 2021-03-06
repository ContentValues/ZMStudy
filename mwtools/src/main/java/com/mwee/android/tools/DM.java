package com.mwee.android.tools;

import android.content.Context;
import android.content.res.Resources;

public class DM {
    /**
     * Converts dp size into pixels.
     *
     * @param dp   dp size to get converted
     * @return Pixel size
     */
    public static float dpToPx(float dp) {
        return dp * Resources.getSystem().getDisplayMetrics().density;
    }

    /**
     * Converts pixels size into dp
     * @param px  pixels size to get converted
     * @return  dp size
     */
    public static float pxToDp(float px){
        return px / Resources.getSystem().getDisplayMetrics().density;
    }

    /**
     * Obtain the Width of resolution
     * @return
     */
    public static int getWidthPixels(){
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
    /**
     * Obtain the height of resolution
     * @return
     */
    public static int getHeightPixels(){
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static float getDensity(){
        return Resources.getSystem().getDisplayMetrics().density;
    }



    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
