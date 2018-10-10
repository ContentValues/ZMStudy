package com.mwee.android.tools;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.mwee.android.tools.GlobalCache;
import com.mwee.android.tools.LogUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * ViewToolsUtil
 */
public class ViewToolsUtil {
    /**
     * 根据DrawableID获取drawable,如果获取失败,则返回null
     *
     * @param drawableID int
     * @return Drawable
     */
    public static Drawable getDrawable(@DrawableRes int drawableID) {
        return getDrawable(GlobalCache.getContext(), drawableID);
    }

    /**
     * 根据DrawableID获取drawable,如果获取失败,则返回null
     *
     * @param context    Context
     * @param drawableID int
     * @return Drawable
     */
    public static Drawable getDrawable(Context context, @DrawableRes int drawableID) {
        Drawable drawable = null;
        try {
            if (context == null) {
                context = GlobalCache.getContext();
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawable = context.getDrawable(drawableID);
            } else {
                drawable = context.getResources().getDrawable(drawableID);
            }
        } catch (Exception e) {
            LogUtil.logError("", e);
        }
        return drawable;
    }

    /**
     * 根据ColorID获取Color,如果获取失败,则返回0
     *
     * @param context      Context
     * @param color_res_id int
     * @return int
     */
    public static int getColor(Context context, @ColorRes int color_res_id) {
        int color = 0;
        try {
            if (context == null) {
                context = GlobalCache.getContext();
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                color = context.getColor(color_res_id);
            } else {
                color = context.getResources().getColor(color_res_id);
            }
        } catch (Exception e) {
            LogUtil.logError("", e);
        }
        return color;
    }

    public static String getString(@StringRes int stringResID) {
        return getString(null, stringResID);
    }

    public static String getString(Context context, @StringRes int stringResID) {
        String content = "";
        try {
            if (context == null) {
                context = GlobalCache.getContext();
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                content = context.getString(stringResID);
            } else {
                content = context.getResources().getString(stringResID);
            }
        } catch (Exception e) {
            LogUtil.logError("", e);
        }
        return content;
    }
    /**
     * 根据ColorID获取Color,如果获取失败,则返回0
     *
     * @param color_res_id int
     * @return int
     */
    public static int getColor(@ColorRes int color_res_id) {
        return getColor(null, color_res_id);
    }

    public static void setBackground(View imageView, Drawable drawable) {
        if (imageView != null) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                imageView.setBackground(drawable);
            } else {
                imageView.setBackgroundDrawable(drawable);
            }
        }
    }

    /**
     * @param view
     * @param resourceId
     */
    public static void setBackgroundResourceKeepPadding(View view, int resourceId) {
        int top = view.getPaddingTop();
        int bottom = view.getPaddingBottom();
        int left = view.getPaddingLeft();
        int right = view.getPaddingRight();
        view.setBackgroundResource(resourceId);
        view.setPadding(left, top, right, bottom);
    }

    /**
     * Dip转换为实际屏幕的像素值
     *
     * @param dm  设备显示对象描述
     * @param dip dip值
     * @return 匹配当前屏幕的像素值
     */
    public static int getPixelFromDip(DisplayMetrics dm, float dip) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, dm) + 0.5f);
    }

    public static int getPixelFromDip(float f) {
        return getPixelFromDip(GlobalCache.getContext().getResources().getDisplayMetrics(), f);
    }

    public static int getPixelFromDip(Context context, float f) {
        return getPixelFromDip(context.getResources().getDisplayMetrics(), f);
    }

    public static int getPixelFromDip(Resources resources, float f) {
        return getPixelFromDip(resources.getDisplayMetrics(), f);
    }

    public static void setBackground(View imageView, @DrawableRes int drawableID) {
        if (imageView != null) {
            imageView.setBackgroundResource(drawableID);
        }
    }

    public static void setTextApperance(TextView textView, int style) {
        if (textView != null) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                textView.setTextAppearance(style);
//            } else {
            textView.setTextAppearance(textView.getContext(), style);
//            }
//            if (!textView.isInEditMode()) {
//                textView.setTypeface(EHomeApplication.getInstance().getMyFont());
//            } else {
//                textView.setTypeface(getMyFont(textView.getContext()));
//            }
        }
    }

    public static Typeface getMyFont(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/lantinghei.ttf");
    }

    public static int getLineCount(String str, int size) {
        Rect bounds = new Rect();
        Paint paint = new Paint();
        paint.setTextSize(size);
        paint.getTextBounds(str, 0, str.length(), bounds);
        int width = (int) Math.ceil(bounds.width());
        return width;
    }


    /**
     * 判断ListView的FooterView里是否包含指定的View
     *
     * @param listView XListView
     * @param footer   View
     * @return boolean | true:包含;false:不包含
     */
    @SuppressWarnings("unchecked")
    public static boolean hasFooterView(ListView listView, View footer) {
        boolean has = false;
        if (listView == null) {
            return false;
        }
        try {
            Field field = ListView.class.getDeclaredField("mFooterViewInfos");
            field.setAccessible(true);
            ArrayList<ListView.FixedViewInfo> fragments = new ArrayList<ListView.FixedViewInfo>();
            if (field.get(listView) != null) {
                fragments.addAll((List<ListView.FixedViewInfo>) field.get(listView));
                for (ListView.FixedViewInfo temp : fragments) {
                    if (temp.view == footer) {
                        has = true;
                        break;
                    }
                }
            }

        } catch (Exception e) {
            LogUtil.logError("Exception", e);
        }

        return has;
    }


    /**
     * 将Miui状态来的文字设置为黑色.
     * 调用此方法是因为:
     * 1,目前APP的statusBar的颜色是屎黄,白色的状态栏文字难以辨识
     * 2,Android6.0可以通过设置windowLightStatusBar来设置黑色的状态栏文字.
     * 3,Android4.4我们通过自定义的R.drawable.bg_status_bar来实现了一个渐变的黄色
     * 4,Android5.0和5.1,MIUI和Flyme各提供了一个方式来设置颜色,其他手机本身会提供一个黑色遮罩,所以也就无需解决.
     * 最终,只有部分原生的5.0和5.1会无法解决.
     *
     * @param activity Activity
     * @param darkmode boolean
     * @return boolean
     */
    public static boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            LogUtil.log("", e);
        }
        return false;
    }

    /**
     * 将Flyme状态来的文字设置为黑色.
     * 详细的注释请参考{@link #setMiuiStatusBarDarkMode(Activity, boolean)}这个方法的注释.
     *
     * @param activity Activity
     * @param dark     boolean
     * @return boolean
     */
    public static boolean setMeizuStatusBarDarkIcon(Activity activity, boolean dark) {
        boolean result = false;
        if (activity != null) {
            try {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                activity.getWindow().setAttributes(lp);
                result = true;
            } catch (Exception e) {
                LogUtil.log("", e);
            }
        }
        return result;
    }

    /**
     * 开启抖动动画
     *
     * @param view         执行动画的view
     * @param shakeDegrees 抖动角度
     * @param duration     动画时间
     * @param repeatCount  执行次数
     */
    public static Animator startShake(View view, float shakeDegrees, long duration, int repeatCount) {
        if (view == null) {
            return null;
        }

//        Animation rotateAnim = new RotateAnimation(-shakeDegrees, shakeDegrees, Animation.RELATIVE_TO_SELF, 0.5f,
//                Animation.RELATIVE_TO_SELF, 0.5f);
//
//        rotateAnim.setDuration(duration / 10);
//        rotateAnim.setRepeatMode(Animation.REVERSE);
//        rotateAnim.setRepeatCount(repeatCount);
//
//        view.startAnimation(rotateAnim);

        PropertyValuesHolder rotateValuesHolder = PropertyValuesHolder.ofKeyframe(View.ROTATION,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(0.1f, shakeDegrees),
                Keyframe.ofFloat(0.2f, -shakeDegrees),
                Keyframe.ofFloat(0.3f, shakeDegrees),
                Keyframe.ofFloat(0.4f, -shakeDegrees),
                Keyframe.ofFloat(0.5f, 0f),
                Keyframe.ofFloat(0.6f, 0f),
                Keyframe.ofFloat(0.7f, 0f)
        );

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, rotateValuesHolder);
        objectAnimator.setDuration(duration);
        objectAnimator.setRepeatCount(repeatCount);
        objectAnimator.start();

        return objectAnimator;
    }

    /**
     * 开启渐变动画
     *
     * @param view        执行动画的view
     * @param duration    动画时间
     * @param repeatCount 执行次数
     * @param color       颜色
     */
    public static Animator startAlpha(final View view, View view2, long duration, int repeatCount, int color) {
        if (view == null) {
            return null;
        }
        view.getLayoutParams().width = view2.getMeasuredWidth();
        view.getLayoutParams().height = view2.getMeasuredHeight();
//        view.invalidate();
        view.setBackgroundResource(color);
        view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        int RED = 0xf6FF1616;
        int RED_ALPHA = 0x00FF1616;

        ValueAnimator objectAnimator = ObjectAnimator.ofInt(view, "BackgroundColor", RED, RED_ALPHA);
        objectAnimator.setEvaluator(new ArgbEvaluator());
        objectAnimator.setRepeatCount(repeatCount);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.setDuration(duration);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setLayerType(View.LAYER_TYPE_NONE, null);
            }
        });
        objectAnimator.start();
        return objectAnimator;
    }
}
