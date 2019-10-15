package xdroid.mwee.com.zmstudy.ui.fragment.jdfragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.mwee.android.tools.DM;

import xdroid.mwee.com.mwcommon.base.XRecAdapter;
import xdroid.mwee.com.mwcommon.imageloader.ILFactory;
import xdroid.mwee.com.mwcommon.imageloader.LoadCallback;
import xdroid.mwee.com.mwcommon.widget.autoscrollviewpager.BGABanner;
import xdroid.mwee.com.mwcommon.xrecyclerview.divider.HorizontalDividerItemDecoration;
import xdroid.mwee.com.mwcommon.xrecyclerview.divider.VerticalDividerItemDecoration;
import xdroid.mwee.com.zmstudy.R;

/**
 * Author：created by SugarT
 * Time：2019/10/10 13
 */
public class JDAdapter extends XRecAdapter<HomeIndex.ItemInfoListBean, RecyclerView.ViewHolder> {


    public static final int TYPE_TOP_BANNER = 0xff01;
    public static final int TYPE_ICON_LIST = 0xff02;
    public static final int TYPE_NEW_USER = 0xff03;
    public static final int TYPE_JD_BULLETIN = 0xff04;
    public static final int TYPE_JD_SPIKE_HEADER = 0xff05;
    public static final int TYPE_JD_SPIKE_CONTENT = 0xff06;
    public static final int TYPE_SHOW_EVENT_3 = 0xff07;
    public static final int TYPE_FIND_GOOD_STUFF = 0xff08;
    public static final int TYPE_WIDTH_PROPORTION_211 = 0xff09;
    public static final int TYPE_TITLE = 0xff10;
    public static final int TYPE_WIDTH_PROPORTION_22 = 0xff11;
    public static final int TYPE_WIDTH_PROPORTION_1111 = 0xff12;
    public static final int TYPE_MIDDLE_BANNER = 0xff13;
    public static final int TYPE_SHOW_EVENT_FILL_UP = 0xff14;
    public static final int TYPE_FIND_GOOD_SHOP = 0xff15;
    public static final int TYPE_PREFERRED_LIST = 0xff16;
    public static final int TYPE_LIVE = 0xff17;
    public static final int TYPE_RECOMMENDED_WARE = 0xff18;

    private final static String TAG = JDAdapter.class.getSimpleName();
    private SparseArray<CountDownTimer> downTimerSparseArray = new SparseArray<>();


    public JDAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TOP_BANNER) {
            return new TopBannerHolder(LayoutInflater.from(context).inflate(R.layout.homerecycle_item_top_banner, parent, false));
        } else if (viewType == TYPE_ICON_LIST) {
            return new IconListHolder(LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false));
        } else if (viewType == TYPE_SHOW_EVENT_3) {
            return new ShowEventHolder(LayoutInflater.from(context).inflate(R.layout.homerecycle_item_show_event_3, parent, false));
        } else if (viewType == TYPE_NEW_USER) {
            return new NewUserHolder(LayoutInflater.from(context).inflate(R.layout.homerecycle_item_new_user, parent, false));
        } else if (viewType == TYPE_JD_BULLETIN) {
            return new BulletinHolder(LayoutInflater.from(context).inflate(R.layout.homerecycle_item_jd_bulletin, parent, false));
        } else if (viewType == TYPE_JD_SPIKE_CONTENT) {
            return new SpikeHolder(LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false));
        } else if (viewType == TYPE_JD_SPIKE_HEADER) {
            return new SpikeHeaderHolder(LayoutInflater.from(context).inflate(R.layout.homerecycle_item_spike_header, parent, false));
        } else if (viewType == TYPE_TITLE) {
            return new TypeTitleHolder(LayoutInflater.from(context).inflate(R.layout.homerecycle_item_type_title, parent, false));
        } else if (viewType == TYPE_WIDTH_PROPORTION_22) {
            return new Type22Holder(LayoutInflater.from(context).inflate(R.layout.homerecycle_item_type_22, parent, false));
        } else if (viewType == TYPE_WIDTH_PROPORTION_1111) {
            return new Type1111Holder(LayoutInflater.from(context).inflate(R.layout.homerecycle_item_type_1111, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HomeIndex.ItemInfoListBean item = data.get(position);
        if (holder instanceof TopBannerHolder) {
            ((TopBannerHolder) holder).bindData(item);
        }
        if (holder instanceof IconListHolder) {
            ((IconListHolder) holder).bindData(item);
        }
        if (holder instanceof ShowEventHolder) {
            ((ShowEventHolder) holder).bindData(item);
        }
        if (holder instanceof NewUserHolder) {
            ((NewUserHolder) holder).bindData(item);
        }
        if (holder instanceof BulletinHolder) {
            ((BulletinHolder) holder).bindData(item);
        }
        if (holder instanceof SpikeHolder) {
            ((SpikeHolder) holder).bindData(item);
        }
        if (holder instanceof SpikeHeaderHolder) {
            Log.d(TAG, "bindData  onBindViewHolder() ((SpikeHeaderHolder) holder)" + holder.hashCode());
            ((SpikeHeaderHolder) holder).bindData(item);
        }

        if (holder instanceof TypeTitleHolder) {
            ((TypeTitleHolder) holder).bindData(item);
        }

        if (holder instanceof Type22Holder) {
            ((Type22Holder) holder).bindData(item);
        }

        if (holder instanceof Type1111Holder) {
            ((Type1111Holder) holder).bindData(item);
        }


    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {

        HomeIndex.ItemInfoListBean bean = data.get(position);
        if ("topBanner".equals(bean.itemType)) {
            return TYPE_TOP_BANNER;
        } else if ("iconList".equals(bean.itemType)) {
            return TYPE_ICON_LIST;
        } else if ("showEvent".equals(bean.itemType)) {
            return TYPE_SHOW_EVENT_3;
        } else if ("newUser".equals(bean.itemType)) {
            return TYPE_NEW_USER;
        } else if ("jdBulletin".equals(bean.itemType)) {
            return TYPE_JD_BULLETIN;
        } else if ("jdSpikeContent".equals(bean.itemType)) {
            return TYPE_JD_SPIKE_CONTENT;
        } else if ("jdSpikeHeader".equals(bean.itemType)) {
            return TYPE_JD_SPIKE_HEADER;
        } else if ("type_Title".equals(bean.itemType)) {
            return TYPE_TITLE;
        } else if ("type_22".equals(bean.itemType)) {
            return TYPE_WIDTH_PROPORTION_22;
        } else if ("type_1111".equals(bean.itemType)) {
            return TYPE_WIDTH_PROPORTION_1111;
        }
        return super.getItemViewType(position);
    }

//
//    @Override
//    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
//        if (manager instanceof GridLayoutManager) {
//            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
//            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                @Override
//                public int getSpanSize(int position) {
//                    int type = getItemViewType(position);
//
//                }
//            });
//        }
//    }

    /**
     * 顶部轮播图
     */
    private class TopBannerHolder extends RecyclerView.ViewHolder {

        private BGABanner banner;

        public TopBannerHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }

        private void bindData(HomeIndex.ItemInfoListBean item) {

            banner.setDelegate(new BGABanner.Delegate<View, HomeIndex.ItemInfoListBean.ItemContentListBean>() {
                @Override
                public void onBannerItemClick(BGABanner banner, View itemView, HomeIndex.ItemInfoListBean.ItemContentListBean model, int position) {
                    Toast.makeText(itemView.getContext(), "" + item.itemContentList.get(position).clickUrl, Toast.LENGTH_SHORT).show();
                }
            });
            banner.setAdapter(new BGABanner.Adapter<View, HomeIndex.ItemInfoListBean.ItemContentListBean>() {
                @Override
                public void fillBannerItem(BGABanner banner, View itemView, HomeIndex.ItemInfoListBean.ItemContentListBean model, int position) {
                    ImageView simpleDraweeView = itemView.findViewById(R.id.sdv_item_fresco_content);
//                    simpleDraweeView.setImageURI(Uri.parse(model.imageUrl));
                    ILFactory.getLoader().loadNet(simpleDraweeView, model.imageUrl, null);
                }
            });
            banner.setData(R.layout.homerecycle_top_banner_content, item.itemContentList, null);
        }
    }

    private class IconListHolder extends RecyclerView.ViewHolder {

        private IconListAdapter iconListAdapter;
        private ImageView mImageBackground;

        public IconListHolder(View itemView) {
            super(itemView);
            mImageBackground = itemView.findViewById(R.id.mImageBackground);
            RecyclerView recyclerView = itemView.findViewById(R.id.mRecyclerView);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 5, RecyclerView.VERTICAL, false);
            recyclerView.setLayoutManager(gridLayoutManager);
            iconListAdapter = new IconListAdapter(context);
            recyclerView.setAdapter(iconListAdapter);
        }

        public void bindData(HomeIndex.ItemInfoListBean model) {
            iconListAdapter.setData(model.itemContentList);
            if (!TextUtils.isEmpty(model.itemContentList.get(0).itemBackgroundImageUrl)) {
                mImageBackground.setVisibility(View.VISIBLE);
                ILFactory.getLoader().loadNet(mImageBackground, model.itemContentList.get(0).itemBackgroundImageUrl, null);
            } else {
                mImageBackground.setVisibility(View.GONE);
            }
        }
    }


    private class ShowEventHolder extends RecyclerView.ViewHolder {
        private final ImageView show_event_left_img;
        private final ImageView show_event_middle_img;
        private final ImageView show_event_right_img;

        public ShowEventHolder(View itemView) {
            super(itemView);
            show_event_left_img = itemView.findViewById(R.id.show_event_left_img);
            show_event_middle_img = itemView.findViewById(R.id.show_event_middle_img);
            show_event_right_img = itemView.findViewById(R.id.show_event_right_img);
        }

        public void bindData(HomeIndex.ItemInfoListBean item) {
            ILFactory.getLoader().loadNet(show_event_left_img, item.itemContentList.get(0).imageUrl, null);
            ILFactory.getLoader().loadNet(show_event_middle_img, item.itemContentList.get(1).imageUrl, null);
            ILFactory.getLoader().loadNet(show_event_right_img, item.itemContentList.get(2).imageUrl, null);
        }
    }


    private class NewUserHolder extends RecyclerView.ViewHolder {


        private final ImageView new_user_bg_img;
        private final ImageView new_user_red_envelopes;
        private final ImageView new_uer_free_postage;
        private final ImageView new_user_basic_necessities_of_life;
        private final ImageView new_user_packs;

        public NewUserHolder(View itemView) {
            super(itemView);

            new_user_bg_img = itemView.findViewById(R.id.new_user_bg_img);
            new_user_red_envelopes = itemView.findViewById(R.id.new_user_red_envelopes);
            new_uer_free_postage = itemView.findViewById(R.id.new_uer_free_postage);
            new_user_basic_necessities_of_life = itemView.findViewById(R.id.new_user_basic_necessities_of_life);
            new_user_packs = itemView.findViewById(R.id.new_user_packs);
        }

        public void bindData(HomeIndex.ItemInfoListBean item) {

            ILFactory.getLoader().loadNet(new_user_bg_img, item.itemContentList.get(0).imageUrl, null);
            ILFactory.getLoader().loadNet(new_user_red_envelopes, item.itemContentList.get(1).imageUrl, null);
            ILFactory.getLoader().loadNet(new_uer_free_postage, item.itemContentList.get(2).imageUrl, null);
            ILFactory.getLoader().loadNet(new_user_basic_necessities_of_life, item.itemContentList.get(3).imageUrl, null);
            ILFactory.getLoader().loadNet(new_user_packs, item.itemContentList.get(4).imageUrl, null);
        }
    }


    private class BulletinHolder extends RecyclerView.ViewHolder {

        private final ViewFlipper home_view_switcher;

        public BulletinHolder(View itemView) {
            super(itemView);
            home_view_switcher = itemView.findViewById(R.id.home_view_switcher);
            home_view_switcher.setFlipInterval(3000);
            home_view_switcher.setAutoStart(true);
            home_view_switcher.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_in));
            home_view_switcher.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_out));
        }

        public void bindData(HomeIndex.ItemInfoListBean item) {
            home_view_switcher.removeAllViews();
            home_view_switcher.stopFlipping();
            for (HomeIndex.ItemInfoListBean.ItemContentListBean itemContentListBean : item.itemContentList) {
                View view = LayoutInflater.from(context).inflate(R.layout.switch_view, null);
                TextView switch_title_text = view.findViewById(R.id.switch_title_text);
                TextView textview = view.findViewById(R.id.textview);
//                ImageView mImageFlitter = view.findViewById(R.id.mImageFlitter);
                switch_title_text.setText(itemContentListBean.itemTitle);
                textview.setText(itemContentListBean.itemSubTitle);
//                ILFactory.getLoader().loadNet(mImageFlitter, itemContentListBean.imageUrl, null);
                home_view_switcher.addView(view);
            }
            home_view_switcher.startFlipping();
        }
    }


    private class SpikeHolder extends RecyclerView.ViewHolder {
        private final SpikeContentAdapter spikeContentAdapter;

        public SpikeHolder(View itemView) {
            super(itemView);
            RecyclerView recyclerView = itemView.findViewById(R.id.mRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            spikeContentAdapter = new SpikeContentAdapter(context);
            recyclerView.setAdapter(spikeContentAdapter);
        }

        public void bindData(HomeIndex.ItemInfoListBean model) {

            spikeContentAdapter.setData(model.itemContentList);
        }
    }


    private class SpikeHeaderHolder extends RecyclerView.ViewHolder {

        private final TextView spike_time_field;
        private final TextView spike_time_hour;
        private final TextView spike_time_minute;
        private final TextView spike_time_seconds;
        private final TextView spike_header_desc;

        public SpikeHeaderHolder(View itemView) {
            super(itemView);
            spike_time_field = itemView.findViewById(R.id.spike_time_field);
            spike_time_hour = itemView.findViewById(R.id.spike_time_hour);
            spike_time_minute = itemView.findViewById(R.id.spike_time_minute);
            spike_time_seconds = itemView.findViewById(R.id.spike_time_seconds);
            spike_header_desc = itemView.findViewById(R.id.spike_header_desc);
        }

        public void bindData(HomeIndex.ItemInfoListBean item) {
            spike_time_field.setText(item.itemContentList.get(0).itemTitle);
            spike_header_desc.setText(item.itemContentList.get(0).itemSubTitle);

            Log.d(TAG, "bindData  hashCode() " + this.hashCode());
            if (downTimerSparseArray.get(this.hashCode()) == null) {
                CountDownTimer timer = new CountDownTimer(Long.parseLong(item.itemContentList.get(0).itemRecommendedLanguage), 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        Log.d(TAG, "onTick() " + millisUntilFinished);
                        long temp = millisUntilFinished / 1000;
                        long hours = temp / 3600;
                        long minutes = (temp - (3600 * hours)) / 60;
                        long seconds = temp - (3600 * hours) - (60 * minutes);
                        spike_time_hour.setText(hours > 9 ? "" + hours : "0" + hours);
                        spike_time_minute.setText(minutes > 9 ? "" + minutes : "0" + minutes);
                        spike_time_seconds.setText(seconds > 9 ? "" + seconds : "0" + seconds);
                    }

                    @Override
                    public void onFinish() {
                        Log.d(TAG, "onFinish() ");
                        spike_time_hour.setText("00");
                        spike_time_minute.setText("00");
                        spike_time_seconds.setText("00");
                    }
                };
                downTimerSparseArray.put(this.hashCode(), timer);
                timer.start();
            }
        }
    }


    private class TypeTitleHolder extends RecyclerView.ViewHolder {

        private final ImageView type_title_img;

        public TypeTitleHolder(View itemView) {
            super(itemView);
            type_title_img = itemView.findViewById(R.id.type_title_img);
        }

        public void bindData(HomeIndex.ItemInfoListBean item) {
            ILFactory.getLoader().loadNet(type_title_img, item.itemContentList.get(0).imageUrl, null);
//            ((ExpandImageView) helper.getView(R.id.type_title_img)).setImageURI(item.itemContentList.get(0).imageUrl);
//            if (!TextUtils.isEmpty(item.itemContentList.get(0).itemTitle)) {
//                helper.getView(R.id.type_title_more_ll).setVisibility(View.VISIBLE);
//                helper.setText(R.id.type_title_more_text, item.itemContentList.get(0).itemTitle);
//                if ("loveLife".equals(item.module) || "findGoodShop".equals(item.module) || "preferredList".equals(item.module)
//                        || "live".equals(item.module)) {
//                    ((ExpandImageView) helper.getView(R.id.type_title_arrow_img)).setImageResource(R.drawable.orange_arrow);
//                } else if ("goShopping".equals(item.module)) {
//                    ((ExpandImageView) helper.getView(R.id.type_title_arrow_img)).setImageResource(R.drawable.go_shopping_rt);
//                }
//            } else {
//                helper.getView(R.id.type_title_more_ll).setVisibility(View.GONE);
//            }

        }
    }


    private class Type22Holder extends RecyclerView.ViewHolder {

        private final ImageView type22_item_one_img;
        private final ImageView type22_item_two_img;
        private final TextView type22_item_one_title;
        private final TextView type22_item_one_sub_title;
        private final TextView type22_item_two_title;
        private final TextView type22_item_two_sub_title;

        public Type22Holder(View itemView) {
            super(itemView);
            type22_item_one_img = itemView.findViewById(R.id.type22_item_one_img);
            type22_item_two_img = itemView.findViewById(R.id.type22_item_two_img);
            type22_item_one_title = itemView.findViewById(R.id.type22_item_one_title);
            type22_item_one_sub_title = itemView.findViewById(R.id.type22_item_one_sub_title);
            type22_item_two_title = itemView.findViewById(R.id.type22_item_two_title);
            type22_item_two_sub_title = itemView.findViewById(R.id.type22_item_two_sub_title);
        }

        public void bindData(HomeIndex.ItemInfoListBean item) {

            ILFactory.getLoader().loadNet(type22_item_one_img, item.itemContentList.get(0).imageUrl, null);
            ILFactory.getLoader().loadNet(type22_item_two_img, item.itemContentList.get(1).imageUrl, null);

            type22_item_one_title.setText(item.itemContentList.get(0).itemTitle);
            type22_item_one_sub_title.setText(item.itemContentList.get(0).itemSubTitle);
            type22_item_two_title.setText(item.itemContentList.get(1).itemTitle);
            type22_item_two_sub_title.setText(item.itemContentList.get(1).itemSubTitle);
//            setRecommendedLanguage(helper, item, R.id.type22_item_one_recommendedLanguage, 0);
//            setRecommendedLanguage(helper, item, R.id.type22_item_two_recommendedLanguage, 1);

        }
    }

    private class Type1111Holder extends RecyclerView.ViewHolder {

        private final ImageView type1111_item_one_img;
        private final ImageView type1111_item_two_img;
        private final ImageView type1111_item_three_img;
        private final ImageView type1111_item_four_img;

        private final TextView type1111_item_one_title;
        private final TextView type1111_item_one_sub_title;
        private final TextView type1111_item_three_title;
        private final TextView type1111_item_three_sub_title;

        private final TextView type1111_item_two_title;
        private final TextView type1111_item_two_sub_title;
        private final TextView type1111_item_four_title;
        private final TextView type1111_item_four_sub_title;

        public Type1111Holder(View itemView) {
            super(itemView);
            type1111_item_one_img = itemView.findViewById(R.id.type1111_item_one_img);
            type1111_item_two_img = itemView.findViewById(R.id.type1111_item_two_img);
            type1111_item_three_img = itemView.findViewById(R.id.type1111_item_three_img);
            type1111_item_four_img = itemView.findViewById(R.id.type1111_item_four_img);

            type1111_item_one_title = itemView.findViewById(R.id.type1111_item_one_title);
            type1111_item_one_sub_title = itemView.findViewById(R.id.type1111_item_one_sub_title);
            type1111_item_three_title = itemView.findViewById(R.id.type1111_item_three_title);
            type1111_item_three_sub_title = itemView.findViewById(R.id.type1111_item_three_sub_title);

            type1111_item_two_title = itemView.findViewById(R.id.type1111_item_two_title);
            type1111_item_two_sub_title = itemView.findViewById(R.id.type1111_item_two_sub_title);
            type1111_item_four_title = itemView.findViewById(R.id.type1111_item_four_title);
            type1111_item_four_sub_title = itemView.findViewById(R.id.type1111_item_four_sub_title);
        }

        public void bindData(HomeIndex.ItemInfoListBean item) {

            ILFactory.getLoader().loadNet(type1111_item_one_img, item.itemContentList.get(0).imageUrl, null);
            ILFactory.getLoader().loadNet(type1111_item_two_img, item.itemContentList.get(1).imageUrl, null);
            ILFactory.getLoader().loadNet(type1111_item_three_img, item.itemContentList.get(2).imageUrl, null);
            ILFactory.getLoader().loadNet(type1111_item_four_img, item.itemContentList.get(3).imageUrl, null);

            type1111_item_one_title.setText(item.itemContentList.get(0).itemTitle);
            type1111_item_one_sub_title.setText(item.itemContentList.get(0).itemSubTitle);
            type1111_item_three_title.setText(item.itemContentList.get(2).itemTitle);
            type1111_item_three_sub_title.setText(item.itemContentList.get(2).itemSubTitle);

            type1111_item_two_title.setText(item.itemContentList.get(1).itemTitle);
            type1111_item_two_sub_title.setText(item.itemContentList.get(1).itemSubTitle);
            type1111_item_four_title.setText(item.itemContentList.get(3).itemTitle);
            type1111_item_four_sub_title.setText(item.itemContentList.get(3).itemSubTitle);
//            setRecommendedLanguage(helper, item, R.id.type1111_item_one_subscript, 0);
//            setRecommendedLanguage(helper, item, R.id.type1111_item_two_subscript, 1);
//            setRecommendedLanguage(helper, item, R.id.type1111_item_three_subscript, 2);
//            setRecommendedLanguage(helper, item, R.id.type1111_item_four_subscript, 3);
        }
    }


    /**
     * 清空资源
     */
    public void cancelAllTimers() {
        for (int i = 0; i < downTimerSparseArray.size(); i++) {
            CountDownTimer cdt = downTimerSparseArray.valueAt(i);
            cdt.cancel();
        }
    }
}

