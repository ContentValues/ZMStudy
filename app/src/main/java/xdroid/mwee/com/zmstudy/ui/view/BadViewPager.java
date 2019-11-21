package xdroid.mwee.com.zmstudy.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.mwee.android.tools.LogUtil;

/**
 * Created by zhangmin on 2018/12/18.
 */

public class BadViewPager extends ViewPager {


    private int mLastXIntercept;
    private int mLastYIntercept;

    private static final String TAG = "BadViewPager";

    public BadViewPager(@NonNull Context context) {
        super(context);
    }

    public BadViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        final int action = ev.getAction() & MotionEvent.ACTION_MASK;

        boolean inter = false;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, TAG+"--"+"dispatchTouchEvent MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, TAG+"--"+"dispatchTouchEvent MotionEvent.ACTION_MOVE");
                inter = true;
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, TAG+"--"+"dispatchTouchEvent MotionEvent.ACTION_UP");
                break;
            default:
                break;
        }
        boolean eventTouch = super.dispatchTouchEvent(ev);
        Log.d(TAG,TAG + "--action dispatchTouchEvent [" + action + "]" + eventTouch);
        return eventTouch;
//        return inter;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        final int action = ev.getAction() & MotionEvent.ACTION_MASK;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                intercepted = false;
                Log.d(TAG, TAG+"--"+"onInterceptTouchEvent MotionEvent.ACTION_DOWN");
                //调用ViewPager的onInterceptTouchEvent方法初始化mActivePointerId
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, TAG+"--"+"onInterceptTouchEvent MotionEvent.ACTION_MOVE");
                //横坐标位移增量
                int deltaX = x - mLastXIntercept;
                //纵坐标位移增量
                int deltaY = y - mLastYIntercept;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    //自己消耗事件 不交给孩子处理
                    intercepted = true;
                } else {
                    intercepted = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, TAG+"--"+"onInterceptTouchEvent MotionEvent.ACTION_UP");
                intercepted = false;
                break;
            default:
                break;
        }

        mLastXIntercept = x;
        mLastYIntercept = y;


        boolean eventTouch = super.onInterceptTouchEvent(ev);
        Log.d(TAG,TAG + "--action onInterceptTouchEvent [" + action + "]" + eventTouch);
        return eventTouch;
    }



    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        final int action = ev.getAction() & MotionEvent.ACTION_MASK;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,TAG+"--"+ "onTouchEvent MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG,TAG+"--"+ "onTouchEvent MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG,TAG+"--"+"onTouchEvent MotionEvent.ACTION_UP");
                break;
            default:
                break;
        }

        boolean eventTouch = super.onTouchEvent(ev);
        Log.d(TAG,TAG + "--action onTouchEvent [" + action + "]" + eventTouch);
        return eventTouch;

//        return super.onTouchEvent(ev);
    }


}
