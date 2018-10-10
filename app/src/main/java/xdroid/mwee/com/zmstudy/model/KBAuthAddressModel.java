package xdroid.mwee.com.zmstudy.model;

/**
 * Created by zhangmin on 2018/3/27.
 */

import com.mwee.android.tools.base.BusinessBean;

/**
 * 口碑获取应用授权地址 返回数据
 */
public class KBAuthAddressModel extends BusinessBean {


    //唯一码，用于区分请求
    public String code;

    //授权地址
    public String url;

}
