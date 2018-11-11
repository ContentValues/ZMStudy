package xdroid.mwee.com.mwcommon.dialog;

import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;

import com.mwee.android.tools.GlobalCache;

import java.lang.ref.WeakReference;

import xdroid.mwee.com.mwcommon.base.LifecycleActivity;
import xdroid.mwee.com.mwcommon.base.BaseFragment;


/**
 * DialogParamBundle
 */
@SuppressWarnings("unused")
public class DialogParamBundle {
    /**
     * View的mTag
     */
    protected String mTag = "";
    /**
     * 宿主Fragment
     */
    protected WeakReference<BaseFragment> mFragmentRef = null;
    /**
     * FragmentManager
     */
    protected FragmentManager mFragmentManager = null;
    /**
     * 是否可取消
     */
    protected boolean isCancelable = true;
    /**
     * 是否显示消除的x
     */
    protected boolean isShowDismissButton = false;
    /**
     * 加载的文案
     */
    protected String mLoadingText = "";
    /**
     * 回调:单击框的按钮
     */
    protected DialogResponseListener singleBtnClick = null;

    /**
     * 回调:点击双选框的确认按钮
     */
    protected DialogResponseListener positiveClick = null;
    /**
     * 回调:点击双选框的取消按钮
     */
    protected DialogResponseListener negitiveveClick = null;
    /**
     * 回调:点击消除按钮
     */
    protected DialogResponseListener cancelClick = null;
    /**
     * 回调:dialog消失
     */
    protected DialogResponseListener dismissListener = null;

    /**
     * 当宿主是Fragment的时候,如果有需要,可以选择传入RequestCode
     */
    protected int DIALOG_REQUEST_CODE = 0;
    /**
     * 双按钮框中"取消"按钮文字
     */
    protected String negativeBtnText = "";
    /**
     * 双按钮框中"确定"按钮文字
     */
    protected String positiveBtnText = "";
    /**
     * 单按钮框中按钮文字
     */
    protected String singleBtnText = "";
    /**
     * 对话框内的提示内容（只有单按钮框和双按钮框存在）
     */
    protected String contentText = "";
    /**
     * 对话框的title
     */
    protected String titleText = "";
    /**
     * 是否显示title
     */
    protected boolean hasTitle = false;

    protected DialogParamBundle() {
    }

    public static class Builder {
        private String tag = "";
        private boolean isCancelable = true;
        private boolean isShowDismissButton = false;
        private String loadingText = "";
        private DialogResponseListener singleBtnClick = null;
        private DialogResponseListener positiveClick = null;
        private DialogResponseListener negitiveveClick = null;
        private DialogResponseListener cancelClick = null;
        private DialogResponseListener dismissListener = null;
        private int DIALOG_REQUEST_CODE = 0;
        private FragmentManager fragmentManager;
        private String negativeBtnText = "";
        private String positiveBtnText = "";
        private String singleBtnText = "";
        private String contentText = "";
        private String titleText = "";
        private boolean hasTitle = false;
        private BaseFragment fragment;

        public Builder() {

        }

        public DialogParamBundle build() {
            DialogParamBundle bundle = new DialogParamBundle();
            bundle.mTag = this.tag;
            if (fragment != null) {
                bundle.mFragmentRef = new WeakReference<BaseFragment>(fragment);
            }
            bundle.mFragmentManager = this.fragmentManager;
            bundle.isCancelable = this.isCancelable;
            bundle.isShowDismissButton = this.isShowDismissButton;
            bundle.mLoadingText = this.loadingText;
            bundle.singleBtnClick = this.singleBtnClick;
            bundle.negitiveveClick = this.negitiveveClick;
            bundle.positiveClick = this.positiveClick;
            bundle.cancelClick = this.cancelClick;
            bundle.dismissListener = this.dismissListener;
            bundle.DIALOG_REQUEST_CODE = this.DIALOG_REQUEST_CODE;
            bundle.negativeBtnText = this.negativeBtnText;
            bundle.positiveBtnText = this.positiveBtnText;
            bundle.singleBtnText = this.singleBtnText;
            bundle.contentText = this.contentText;
            bundle.titleText = this.titleText;
            bundle.hasTitle = this.hasTitle;
            return bundle;
        }


        /**
         * View的key
         *
         * @param tag String
         * @return Builder
         */
        public Builder setTag(String tag) {
            this.tag = tag;
            return this;
        }

        /**
         * 宿主Activity
         *
         * @param activity WaiterBaseActivity
         * @return Builder
         */
        public Builder setActivity(LifecycleActivity activity) {
            if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
                fragmentManager = activity.getSupportFragmentManager();
            }
            return this;
        }

        /**
         * 宿主Fragment
         *
         * @param fragment WaiterBaseFragment
         * @return Builder
         */
        public Builder setFragment(BaseFragment fragment) {
            if (fragment != null && !fragment.isDetached() && !fragment.isRemoving()) {
                this.fragment = fragment;
                fragmentManager = fragment.getFragmentManager();
            }
            return this;
        }

        /**
         * 是否可取消
         *
         * @param cancelable boolean
         * @return Builder
         */
        public Builder setCancelable(boolean cancelable) {
            isCancelable = cancelable;
            return this;
        }

        /**
         * 加载的文案
         *
         * @param strID int
         * @return Builder
         */
        public Builder setLoadingText(@StringRes int strID) {
            return setLoadingText(GlobalCache.getContext().getString(strID));
        }

        /**
         * 加载的文案
         *
         * @param loadingText String
         * @return Builder
         */
        public Builder setLoadingText(String loadingText) {
            this.loadingText = loadingText;
            return this;
        }

        /**
         * 回调:点击单选框的按钮
         *
         * @param singleBtnClick DialogResponseListener
         * @return Builder
         */
        public Builder setSingleBtnClick(DialogResponseListener singleBtnClick) {
            this.singleBtnClick = singleBtnClick;
            return this;
        }

        /**
         * 回调:点击确认按钮
         *
         * @param positiveClick DialogResponseListener
         * @return Builder
         */
        public Builder setPositiveClick(DialogResponseListener positiveClick) {
            this.positiveClick = positiveClick;
            return this;
        }

        /**
         * 回调:点击取消按钮
         *
         * @param negitiveveClick DialogResponseListener
         * @return Builder
         */
        public Builder setNegitiveveClick(DialogResponseListener negitiveveClick) {
            this.negitiveveClick = negitiveveClick;
            return this;
        }

        /**
         * 回调:点击消除按钮
         *
         * @param dismissClick DialogResponseListener
         * @return Builder
         */
        public Builder setCancelClick(DialogResponseListener dismissClick) {
            this.cancelClick = dismissClick;
            return this;
        }

        /**
         * 回调:dialog消失
         *
         * @param dismissListener DialogResponseListener
         * @return Builder
         */
        public Builder setDismissListener(DialogResponseListener dismissListener) {
            this.dismissListener = dismissListener;
            return this;
        }

        /**
         * 当宿主是Fragment的时候,如果有需要,可以选择传入RequestCode
         *
         * @param DIALOG_REQUEST_CODE int
         * @return Builder
         */
        public Builder setDIALOG_REQUEST_CODE(int DIALOG_REQUEST_CODE) {
            this.DIALOG_REQUEST_CODE = DIALOG_REQUEST_CODE;
            return this;
        }

        /**
         * 是否显示可取消的按钮
         *
         * @param isShowDismissButton boolean
         * @return Builder
         */
        public Builder setIsShowDismissButton(boolean isShowDismissButton) {
            this.isShowDismissButton = isShowDismissButton;
            return this;
        }

        /**
         * 是否显示Title
         *
         * @param hasTitle boolean | true:显示;false:不显示
         * @return Builder
         */
        public Builder setHasTitle(boolean hasTitle) {
            this.hasTitle = hasTitle;
            return this;
        }

        /**
         * Title的内容
         *
         * @param titleText String
         * @return Builder
         */
        public Builder setTitleText(String titleText) {
            this.titleText = titleText;
            if (!TextUtils.isEmpty(titleText)) {
                this.hasTitle = true;
            }
            return this;
        }

        /**
         * Title的内容
         *
         * @param strID int
         * @return Builder
         */
        public Builder setTitleText(@StringRes int strID) {
            return setTitleText(GlobalCache.getContext().getString(strID));
        }

        /**
         * 设置dialog的正文
         *
         * @param contentText String
         * @return Builder
         */
        public Builder setContentText(String contentText) {
            this.contentText = contentText;
            return this;
        }

        /**
         * 设置dialog的正文
         *
         * @param strID int
         * @return Builder
         */
        public Builder setContentText(@StringRes int strID) {
            return setContentText(GlobalCache.getContext().getString(strID));
        }

        /**
         * 设置单选框按钮的文案
         *
         * @param strID int
         * @return Builder
         */
        public Builder setSingleBtnText(@StringRes int strID) {
            return setSingleBtnText(GlobalCache.getContext().getString(strID));
        }

        /**
         * 设置单选框按钮的文案
         *
         * @param singleBtnText String
         * @return Builder
         */
        public Builder setSingleBtnText(String singleBtnText) {
            this.singleBtnText = singleBtnText;
            return this;
        }

        /**
         * 设置"确定"按钮的文案
         *
         * @param positiveBtnText String
         * @return Builder
         */
        public Builder setPositiveBtnText(String positiveBtnText) {
            this.positiveBtnText = positiveBtnText;
            return this;
        }

        /**
         * 设置"确定"按钮的文案
         *
         * @param strID int
         * @return Builder
         */
        public Builder setPositiveBtnText(@StringRes int strID) {
            return setPositiveBtnText(GlobalCache.getContext().getString(strID));
        }

        /**
         * 设置"取消"按钮的文案
         *
         * @param negativeBtnText String
         * @return Builder
         */
        public Builder setNegativeBtnText(String negativeBtnText) {
            this.negativeBtnText = negativeBtnText;
            return this;
        }

        /**
         * 设置"取消"按钮的文案
         *
         * @param strID int
         * @return Builder
         */
        public Builder setNegativeBtnText(@StringRes int strID) {
            return setNegativeBtnText(GlobalCache.getContext().getString(strID));
        }

    }


}
