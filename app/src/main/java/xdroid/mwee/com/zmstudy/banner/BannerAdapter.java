package xdroid.mwee.com.zmstudy.banner;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by zhangmin on 2019/4/12.
 */

public class BannerAdapter extends PagerAdapter {

    private List<ImageView> mList;
    private int pointSize;

    BannerAdapter(List<ImageView> list, int pointSize) {
        this.mList = list;
        this.pointSize = pointSize;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        System.out.println("BannerAdapter instantiateItem +position-->" + position);
        position %= mList.size();
        if (position < 0) {
            position = mList.size() + position;
        }
        ImageView v = mList.get(position);
        //pos = position;
        v.setScaleType(ImageView.ScaleType.CENTER);
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp = v.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(v);
        }
        final int finalPosition = position;
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {

                System.out.println("BannerAdapter 触发了点击事件");

//                if (mViewPagerOnItemClickListener != null) {
//                    if (pointSize == 2) {
//                        mViewPagerOnItemClickListener.onItemClick(finalPosition>=2?finalPosition-2:finalPosition);
//                    } else {
//                        mViewPagerOnItemClickListener.onItemClick(finalPosition);
//                    }
//                }
            }
        });
        container.addView(v);
        return v;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object);
    }
}
