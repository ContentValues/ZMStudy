package xdroid.mwee.com.zmstudy.ui.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by zhangmin on 2018/12/18.
 */

public class TButton extends android.support.v7.widget.AppCompatButton {

    private static final String TAG = "TButton";

    private int mLastX;
    private int mLastY;
    public TButton(Context context) {
        super(context);
    }

    public TButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        final int action = ev.getAction() & MotionEvent.ACTION_MASK;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //孩子自己消耗事件
                getParent().requestDisallowInterceptTouchEvent(true);
                Log.d(TAG, TAG+"--"+"dispatchTouchEvent MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                //水平移动的增量
                int deltaX = x - mLastX;
                //竖直移动的增量
                int deltaY = y - mLastY;
                //当水平增量大于竖直增量时，表示水平滑动，此时需要父View去处理事件
                if (Math.abs(deltaX) > Math.abs(deltaY)){
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                Log.d(TAG, TAG+"--"+"dispatchTouchEvent MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, TAG+"--"+"dispatchTouchEvent MotionEvent.ACTION_UP");
                break;
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        boolean eventTouch = super.dispatchTouchEvent(ev);
        Log.d(TAG,TAG + "--action dispatchTouchEvent [" + action + "]" + eventTouch);
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
        Log.d(TAG,TAG + "--action  onTouchEvent[" + action + "]" + eventTouch);
        return eventTouch;
    }

}
