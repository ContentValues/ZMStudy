package com.mwee.android.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

/**
 * NetWorkUtil
 */
public class NetWorkUtil {

    /**
     * 网络是否可用
     *
     * @return boolean｜true:可用；false：不可用
     */
    public static boolean isNetworkAvailable(Context context) {
        boolean flag = false;
        ConnectivityManager manager = (ConnectivityManager) (context.getSystemService(Context.CONNECTIVITY_SERVICE));
        if (manager != null) {
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if (networkInfo != null) {
                flag = networkInfo.isAvailable();
            }
        }
        return flag;
    }

    /**
     * 获取网络连接类型
     *
     * @return -1为无网络连接， 其他返回值参考ConnectivityManager的常量
     */
    public static int getNetworkType(Context context) {
        ConnectivityManager manager = (ConnectivityManager) (context.getSystemService(Context.CONNECTIVITY_SERVICE));
        if (manager != null) {
            NetworkInfo mNetworkInfo = manager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }

    /**
     * wifi是否可用
     *
     * @return boolean | true:当前网络使用的是wifi;false:不是wifi
     */
    public static boolean isWifiAvailable(Context context) {
        return ConnectivityManager.TYPE_WIFI == getNetworkType(context);
    }

    /**
     * 是否是以太网
     *
     * @param context Context
     * @return boolean
     */
    public static boolean isEthernetAvailable(Context context) {
        return ConnectivityManager.TYPE_ETHERNET == getNetworkType(context);
    }

    /**
     * 是否是移动网络
     *
     * @param context Context
     * @return boolean
     */
    public static boolean isMobileNet(Context context) {
        switch (getNetworkType(context)) {
            case ConnectivityManager.TYPE_MOBILE:
            case ConnectivityManager.TYPE_MOBILE_DUN:
            case ConnectivityManager.TYPE_MOBILE_HIPRI:
            case ConnectivityManager.TYPE_MOBILE_MMS:
            case ConnectivityManager.TYPE_MOBILE_SUPL:
                return true;

        }
        return false;
    }

    /**
     * 获取网络类型的描述
     *
     * @param context Context
     * @return String
     */
    public static String getNetWorkDes(Context context) {
        String info = "";
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                switch (networkInfo.getType()) {
                    case ConnectivityManager.TYPE_WIFI:
                        info = "wifi";
                        break;
                    case ConnectivityManager.TYPE_MOBILE:
                    case ConnectivityManager.TYPE_MOBILE_DUN:
                    case ConnectivityManager.TYPE_MOBILE_HIPRI:
                    case ConnectivityManager.TYPE_MOBILE_MMS:
                    case ConnectivityManager.TYPE_MOBILE_SUPL:
                        info = getMobileNetWorkTypeDes(context);
                        break;
                    case ConnectivityManager.TYPE_ETHERNET:
                        info = "ETHERNET";
                        break;
                    default:
                        info = "unknow";
                        break;
                }
            }
        }
        return info;
    }

    /**
     * 获取移动网络类型的描述
     *
     * @param context Context
     * @return String
     */
    public static String getMobileNetWorkTypeDes(Context context) {
        String info = "";
        if (context != null) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            switch (telephonyManager.getNetworkType()) {
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    info = "2G";
                    break;
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_UMTS:
                    info = "3G";
                    break;
                case TelephonyManager.NETWORK_TYPE_LTE:
                    info = "4G";
                    break;
                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                    info = "unknow";
                    break;
                default:
                    info = "unknow";
                    break;
            }
        }
        return info;
    }


    /**
     * 获取Wifi的SSID
     *
     * @param context Context
     * @return String
     */
    public String getWifiSSID(Context context) {
        if (context == null) {
            return "";
        }
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null && wifiManager.isWifiEnabled()) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if (null != wifiInfo && wifiInfo.getNetworkId() >= 0) {
                return wifiInfo.getSSID();
            }
        }
        return "";
    }
}
