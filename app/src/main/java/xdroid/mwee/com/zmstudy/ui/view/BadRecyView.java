package xdroid.mwee.com.zmstudy.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by zhangmin on 2018/12/19.
 */

public class BadRecyView extends RecyclerView {
    private int mLastX;
    private int mLastY;
    private final static String TAG = "BadRecyView";

    public BadRecyView(Context context) {
        super(context);
    }

    public BadRecyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //父viewgroup代码     (要确保down是不拦截，move和up时要拦截)
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        if(ev.getAction()==MotionEvent.ACTION_DOWN){
//            return false;
//        }else{
//            return true;
//        }
//    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();


        final int action = ev.getAction() & MotionEvent.ACTION_MASK;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
//                getParent().requestDisallowInterceptTouchEvent(true);
                Log.d(TAG, TAG + "--" + "dispatchTouchEvent MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, TAG + "--" + "dispatchTouchEvent MotionEvent.ACTION_MOVE");
                //水平移动的增量
                int deltaX = x - mLastX;
                //竖直移动的增量
                int deltaY = y - mLastY;
                //当水平增量大于竖直增量时，表示水平滑动，此时需要父View去处理事件
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
//                    getParent().requestDisallowInterceptTouchEvent(true);
                    Log.d(TAG, "当水平增量大于竖直增量时，表示水平滑动，此时需要父View去处理事件");
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, TAG + "--" + "dispatchTouchEvent MotionEvent.ACTION_UP");
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
                Log.d(TAG, TAG + "--" + "onTouchEvent MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, TAG + "--" + "onTouchEvent MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, TAG + "--" + "onTouchEvent MotionEvent.ACTION_UP");
                break;
            default:
                break;
        }
        boolean eventTouch = super.onTouchEvent(ev);
        Log.d(TAG,TAG + "--action  onTouchEvent[" + action + "]" + eventTouch);
        return eventTouch;
    }

}
