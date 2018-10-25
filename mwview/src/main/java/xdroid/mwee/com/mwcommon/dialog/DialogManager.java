package xdroid.mwee.com.mwcommon.dialog;

import android.support.annotation.StringRes;

import xdroid.mwee.com.mwcommon.base.Host;


/**
 * 弹窗相关的
 */
@SuppressWarnings("unused")
public class DialogManager {


    public static void showCustomDialog(Host host, BaseDialogFragment fragment, String tag) {
        if (host == null) {
            return;
        }
        fragment.show(host.getFragmentManagerWithinHost(), tag);
    }

    /**
     * 弹出双按钮提示框
     *
     * @param host         Host
     * @param content      String | 弹框内容
     * @param postiveClick DialogResponseListener | 确定按钮的回调
     */
    public static BaseDialogFragment showExecuteDialog(Host host, String content, DialogResponseListener postiveClick) {
        DialogParamBundle.Builder builder = new DialogParamBundle.Builder();
        builder.setContentText(content).
                setPositiveClick(postiveClick).
                setCancelable(false);
        return showExecuteDialog(host, builder.build());
    }

    /**
     * 弹出双按钮提示框
     *
     * @param host         Host
     * @param content      String | 弹框内容
     * @param postiveClick DialogResponseListener | 确定按钮的回调
     */
    public static BaseDialogFragment showExecuteDialog(Host host, @StringRes int content, DialogResponseListener postiveClick) {
        DialogParamBundle.Builder builder = new DialogParamBundle.Builder();
        builder.setContentText(content).
                setPositiveClick(postiveClick).
                setCancelable(false);
        return showExecuteDialog(host, builder.build());
    }

    /**
     * 弹出双按钮提示框
     *
     * @param host         Host
     * @param content      String | 弹框内容
     * @param postiveClick DialogResponseListener | 确定按钮的回调
     */
    public static BaseDialogFragment showExecuteDialog(Host host, @StringRes int content, @StringRes int leftText, @StringRes int rightText,
                                                       DialogResponseListener postiveClick) {
        DialogParamBundle.Builder builder = new DialogParamBundle.Builder();
        builder.setContentText(content)
                .setNegativeBtnText(leftText)
                .setPositiveBtnText(rightText)
                .setPositiveClick(postiveClick)
                .setCancelable(false);
        return showExecuteDialog(host, builder.build());
    }

    /**
     * 弹出双按钮提示框
     *
     * @param host         Host
     * @param content      String | 弹框内容
     * @param postiveClick DialogResponseListener | 确定按钮的回调
     */
    public static BaseDialogFragment showExecuteDialog(Host host, String content, @StringRes int leftText, @StringRes int rightText,
                                                       DialogResponseListener postiveClick) {
        DialogParamBundle.Builder builder = new DialogParamBundle.Builder();
        builder.setContentText(content)
                .setNegativeBtnText(leftText)
                .setPositiveBtnText(rightText)
                .setPositiveClick(postiveClick)
                .setCancelable(false);
        return showExecuteDialog(host, builder.build());
    }

    /**
     * 弹出双按钮提示框
     *
     * @param host         Host
     * @param content      String | 弹框内容
     * @param postiveClick DialogResponseListener | 确定按钮的回调
     */
    public static BaseDialogFragment showExecuteDialog(Host host, String content, String leftText, String rightText,
                                                       DialogResponseListener postiveClick) {
        DialogParamBundle.Builder builder = new DialogParamBundle.Builder();
        builder.setContentText(content)
                .setNegativeBtnText(leftText)
                .setPositiveBtnText(rightText)
                .setPositiveClick(postiveClick)
                .setCancelable(false);
        return showExecuteDialog(host, builder.build());
    }

    public static BaseDialogFragment showExecuteDialog(Host host, String content, String leftText, String rightText,
                                                       DialogResponseListener postiveClick, DialogResponseListener
                                                               negativeClick) {
        DialogParamBundle.Builder builder = new DialogParamBundle.Builder();
        builder.setContentText(content)
                .setNegativeBtnText(leftText)
                .setPositiveBtnText(rightText)
                .setPositiveClick(postiveClick)
                .setNegitiveveClick(negativeClick)
                .setCancelable(false);
        return showExecuteDialog(host, builder.build());
    }

    public static BaseDialogFragment showExecuteDialog(Host host, @StringRes int content, String leftText, String rightText,
                                                       DialogResponseListener postiveClick, DialogResponseListener
                                                               negativeClick) {
        DialogParamBundle.Builder builder = new DialogParamBundle.Builder();
        builder.setContentText(content)
                .setNegativeBtnText(leftText)
                .setPositiveBtnText(rightText)
                .setPositiveClick(postiveClick)
                .setNegitiveveClick(negativeClick)
                .setCancelable(false);
        return showExecuteDialog(host, builder.build());
    }

    public static BaseDialogFragment showExecuteDialog(Host host, String title, String content, String leftText,
                                                       String rightText, DialogResponseListener postiveClick,
                                                       DialogResponseListener negativeClick) {
        DialogParamBundle.Builder builder = new DialogParamBundle.Builder();
        builder.setHasTitle(true).setTitleText(title)
                .setContentText(content)
                .setNegativeBtnText(leftText)
                .setPositiveBtnText(rightText)
                .setPositiveClick(postiveClick)
                .setNegitiveveClick(negativeClick)
                .setCancelable(false);
        return showExecuteDialog(host, builder.build());
    }

    /**
     * 弹出双按钮提示框
     *
     * @param host          Host
     * @param exchangeModel DialogParamBundle | 参数集
     */
    public static BaseDialogFragment showExecuteDialog(Host host, DialogParamBundle exchangeModel) {
        if (host == null || exchangeModel == null) {
            return null;
        }
        ExecuteDialogFragment executeDialogFragment = ExecuteDialogFragment.newInstance(exchangeModel);
        executeDialogFragment.show(host.getFragmentManagerWithinHost(), ExecuteDialogFragment.FRAGMENT_TAG);
        return executeDialogFragment;
    }

    /**
     * 弹出单击框
     *
     * @param host    Host
     * @param content String
     */
    public static BaseDialogFragment showSingleDialog(Host host, @StringRes int content) {
        DialogParamBundle.Builder builder = new DialogParamBundle.Builder();
        builder.setContentText(content).setCancelable(false);
        return showSingleDialog(host, builder.build());
    }

    /**
     * 弹出单击框
     *
     * @param host    Host
     * @param content String
     */
    public static BaseDialogFragment showSingleDialog(Host host, String content) {
        DialogParamBundle.Builder builder = new DialogParamBundle.Builder();
        builder.setContentText(content).setCancelable(false);
        return showSingleDialog(host, builder.build());
    }

    /**
     * 弹出单击框
     *
     * @param host    Host
     * @param content String
     * @param btnText String
     */
    public static BaseDialogFragment showSingleDialog(Host host, @StringRes int content, @StringRes int btnText) {

        DialogParamBundle.Builder builder = new DialogParamBundle.Builder();
        builder.setContentText(content)
                .setSingleBtnText(btnText).setCancelable(false);
        return showSingleDialog(host, builder.build());
    }

    /**
     * 弹出单击框
     *
     * @param host    Host
     * @param content String
     * @param btnText String
     */
    public static BaseDialogFragment showSingleDialog(Host host, String content, String btnText) {

        DialogParamBundle.Builder builder = new DialogParamBundle.Builder();
        builder.setContentText(content)
                .setSingleBtnText(btnText).setCancelable(false);
        return showSingleDialog(host, builder.build());
    }

    public static BaseDialogFragment showSingleDialog(Host host, String content, String title, String btnText) {
        DialogParamBundle.Builder builder = new DialogParamBundle.Builder();
        builder.setContentText(content)
                .setTitleText(title)
                .setSingleBtnText(btnText).setCancelable(false);
        return showSingleDialog(host, builder.build());

    }

    /**
     * 弹出单击框
     *
     * @param host                   Host
     * @param content                String
     * @param mSingleOnClickListener DialogResponseListener
     */
    public static BaseDialogFragment showSingleDialog(Host host, String content, DialogResponseListener mSingleOnClickListener) {

        return showSingleDialog(host, content, null, null, mSingleOnClickListener);
    }

    /**
     * 弹出单击框
     *
     * @param host                   Host
     * @param content                String
     * @param title                  String
     * @param btnText                String
     * @param mSingleOnClickListener 单击按钮回调
     * @return BaseDialogFragment
     */
    public static BaseDialogFragment showSingleDialog(Host host, String content, String title, String btnText,
                                                      DialogResponseListener mSingleOnClickListener) {
        DialogParamBundle.Builder builder = new DialogParamBundle.Builder();
        builder.setContentText(content)
                .setTitleText(title)
                .setSingleBtnText(btnText)
                .setSingleBtnClick(mSingleOnClickListener)
                .setCancelable(false);
        return showSingleDialog(host, builder.build());

    }

    /**
     * 弹出单选框, 不能点击其他区域退出
     *
     * @param host                   Host
     * @param content                String
     * @param mSingleOnClickListener DialogResponseListener
     * @return BaseDialogFragment
     */
    public static BaseDialogFragment showSingleWithoutCancelable(Host host, String content, DialogResponseListener
            mSingleOnClickListener) {
        DialogParamBundle.Builder builder = new DialogParamBundle.Builder();
        builder.setContentText(content)
                .setSingleBtnClick(mSingleOnClickListener)
                .setCancelable(false);
        return showSingleDialog(host, builder.build());
    }

    /**
     * 弹出单选框, 不能点击其他区域退出
     *
     * @param host                   Host
     * @param content                String
     * @param btnText                String
     * @param mSingleOnClickListener DialogResponseListener
     * @return BaseDialogFragment
     */
    public static BaseDialogFragment showSingleWithoutCancelable(Host host, String content,
                                                                 @StringRes int btnText, DialogResponseListener
                                                                         mSingleOnClickListener) {
        DialogParamBundle.Builder builder = new DialogParamBundle.Builder();
        builder.setContentText(content)
                .setSingleBtnText(btnText)
                .setSingleBtnClick(mSingleOnClickListener)
                .setCancelable(false);
        return showSingleDialog(host, builder.build());
    }

    /**
     * 弹出单选框, 不能点击其他区域退出
     *
     * @param host                   Host
     * @param content                String
     * @param btnText                String
     * @param mSingleOnClickListener DialogResponseListener
     * @return BaseDialogFragment
     */
    public static BaseDialogFragment showSingleWithoutCancelable(Host host, String content,
                                                                 String btnText, DialogResponseListener
                                                                         mSingleOnClickListener) {
        DialogParamBundle.Builder builder = new DialogParamBundle.Builder();
        builder.setContentText(content)
                .setSingleBtnText(btnText)
                .setSingleBtnClick(mSingleOnClickListener)
                .setCancelable(false);
        return showSingleDialog(host, builder.build());
    }

    /**
     * 弹出单选框, 不能点击其他区域退出
     *
     * @param host                   v
     * @param content                String
     * @param title                  String
     * @param btnText                String
     * @param mSingleOnClickListener DialogResponseListener
     * @return BaseDialogFragment
     */
    public static BaseDialogFragment showSingleWithoutCancelable(Host host, String content, String title,
                                                                 String btnText, DialogResponseListener
                                                                         mSingleOnClickListener) {
        DialogParamBundle.Builder builder = new DialogParamBundle.Builder();
        builder.setContentText(content)
                .setTitleText(title)
                .setSingleBtnText(btnText)
                .setSingleBtnClick(mSingleOnClickListener)
                .setCancelable(false);
        return showSingleDialog(host, builder.build());

    }

    /**
     * 弹出单击框
     *
     * @param host          Host
     * @param exchangeModel DialogParamBundle
     */
    public static BaseDialogFragment showSingleDialog(Host host, DialogParamBundle exchangeModel) {
        if (host == null || exchangeModel == null) {
            return null;
        }
        SingleDialogFragment fragment = SingleDialogFragment.newInstance(exchangeModel);
        fragment.show(host.getFragmentManagerWithinHost(), SingleDialogFragment.FRAGMENT_TAG);
        return fragment;
    }

}
