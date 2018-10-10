package com.mwee.android.tools;

import android.Manifest;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

/**
 * DeviceUtil
 */
public class DeviceUtil {

    /**
     * 获取IMEI,并检测IMEI的正确性,如果IMEI不正确,则返回空字符串
     *
     * @param context Context
     * @return String
     */
    public static String getImei(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            if (PackageManager.PERMISSION_GRANTED == packageManager.checkPermission(Manifest.permission.READ_PHONE_STATE, context.getPackageName())) {
                imei = manager.getDeviceId();
            }
            if (!DeviceUtil.checkIMEI(imei)) {
                imei = "";
            }
        } catch (Exception e) {
            LogUtil.logError(e);
        }
        return imei;
    }

    /**
     * 获取IP地址
     *
     * @return String
     */
    public static String getLocalIpAddress() {
        try {
            Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces();
            if (en != null) {
                while (en.hasMoreElements()) {
                    NetworkInterface intf = en.nextElement();
                    if (!intf.supportsMulticast() || !intf.isUp()) {
                        continue;
                    }
                    for (Enumeration<InetAddress> enumIpAddr = intf
                            .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {

                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address && !inetAddress.getHostAddress().startsWith("127.0")) {
                            return inetAddress.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取IP地址列表
     *
     * @return List<String>
     */
    public static List<String> getLocalIpList() {
        List<String> ipList = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces();
            if (en != null) {
                while (en.hasMoreElements()) {
                    NetworkInterface intf = en.nextElement();
                    if (!intf.supportsMulticast() || !intf.isUp()) {
                        continue;
                    }
                    for (Enumeration<InetAddress> enumIpAddr = intf
                            .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address && !inetAddress.getHostAddress().startsWith("127.0")) {
                            ipList.add(inetAddress.getHostAddress());
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ipList;
    }

    /**
     * 获取Mac地址,先获取Wifi的mac地址,如果无法获取到,再返回以太网的网卡mac
     *
     * @param context String
     * @return String
     */
    public static String getMacAddress(Context context) {
        String macAddress = getMacaddressWifi(context);
        if (TextUtils.isEmpty(macAddress)) {
            macAddress = getMacAddressEthernet();
        }
        return macAddress;
    }

    public static String getMacaddressWifi(Context context) {
        String macAddress = "";
        PackageManager packageManager = context.getPackageManager();
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            if (PackageManager.PERMISSION_GRANTED == packageManager.checkPermission(Manifest.permission.ACCESS_WIFI_STATE, context.getPackageName())) {
                if (wifiManager != null && wifiManager.getConnectionInfo() != null) {
                    macAddress = wifiManager.getConnectionInfo().getMacAddress();
                }
            }
            if (!DeviceUtil.checkMacAddress(macAddress)) {
                macAddress = "";
            }
        } catch (Exception e) {
            LogUtil.logError(e);
        }
        return macAddress;
    }

    /**
     * 获取以太网卡的mac地址
     *
     * @return String
     */
    public static String getMacAddressEthernet() {
        ShellUtil.CommandResult result = ShellUtil.execCommand("cat /sys/class/net/eth0/address", false);
        return result.responseMsg;
    }

    /**
     * 获取DeviceID
     *
     * @param context Context
     * @return String
     */
    public static String getDeviceID(Context context) {
        String deviceId = "";
        PackageManager packageManager = context.getPackageManager();
        if (PackageManager.PERMISSION_GRANTED == packageManager.checkPermission(Manifest.permission.READ_PHONE_STATE, context.getPackageName())) {

            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = tm.getDeviceId();
            if (!TextUtils.isEmpty(deviceId) && deviceId.startsWith("0000000")) {
                return "";
            }
        }
        return deviceId;
    }

    /**
     * 获取序列号
     *
     * @return String
     */
    public static String getSerialNO() {
        String serial = Build.SERIAL;
        if ("unknown".equalsIgnoreCase(serial) || "none".equalsIgnoreCase(serial) || "null".equalsIgnoreCase(serial)) {
            serial = "";
        }
        return serial;
    }

    /**
     * 生成一个UUID
     *
     * @return String
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取屏幕信息
     *
     * @param context Activity
     * @return DisplayMetrics
     */
    public static DisplayMetrics getScreenMetrics(Activity context) {
        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric;
    }

    /**
     * 打印全部系统信息
     *
     * @param context Context
     * @return String
     */
    public static String printSystemInfo(Context context) {
        String time = DateUtil.getCurrentDateTime(DateUtil.DATE_VISUAL14FORMAT);
        StringBuilder sb = new StringBuilder();
        sb.append("_______  系统信息  ").append(time).append(" ______________");
        sb.append("\nID                 :").append(Build.ID);
        sb.append("\nBRAND              :").append(Build.BRAND);
        sb.append("\nMODEL              :").append(Build.MODEL);
        sb.append("\nRELEASE            :").append(Build.VERSION.RELEASE);
        sb.append("\nSDK                :").append(Build.VERSION.SDK);

        sb.append("\n_______ OTHER _______");
        sb.append("\nBOARD              :").append(Build.BOARD);
        sb.append("\nPRODUCT            :").append(Build.PRODUCT);
        sb.append("\nDEVICE             :").append(Build.DEVICE);
        sb.append("\nFINGERPRINT        :").append(Build.FINGERPRINT);
        sb.append("\nHOST               :").append(Build.HOST);
        sb.append("\nTAGS               :").append(Build.TAGS);
        sb.append("\nTYPE               :").append(Build.TYPE);
        sb.append("\nTIME               :").append(Build.TIME);
        sb.append("\nINCREMENTAL        :").append(Build.VERSION.INCREMENTAL);

        sb.append("\n_______ CUPCAKE-3 _______");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            sb.append("\nDISPLAY            :").append(Build.DISPLAY);
        }

        sb.append("\n_______ DONUT-4 _______");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            sb.append("\nSDK_INT            :").append(Build.VERSION.SDK_INT);
            sb.append("\nMANUFACTURER       :").append(Build.MANUFACTURER);
            sb.append("\nBOOTLOADER         :").append(Build.BOOTLOADER);
            sb.append("\nCPU_ABI            :").append(Build.CPU_ABI);
            sb.append("\nCPU_ABI2           :").append(Build.CPU_ABI2);
            sb.append("\nHARDWARE           :").append(Build.HARDWARE);
            sb.append("\nUNKNOWN            :").append(Build.UNKNOWN);
            sb.append("\nCODENAME           :").append(Build.VERSION.CODENAME);
        }

        sb.append("\n_______ GINGERBREAD-9 _______");

        sb.append("\nIMEI               :").append(getImei(context));
        sb.append("\nMacWifi            :").append(getMacaddressWifi(context));
        sb.append("\nMacEthernet        :").append(getMacAddressEthernet());
        sb.append("\nDeviceID           :").append(getDeviceID(context));
        sb.append("\nSeriaNO            :").append(getSerialNO());

        LogUtil.log(sb.toString());
        return sb.toString();
    }

    /**
     * 屏幕是否锁定
     *
     * @param context Context
     * @return boolean | true:屏幕已锁;false:屏幕未锁
     */
    public static boolean isScreenLocked(Context context) {
        KeyguardManager mKeyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        return mKeyguardManager.inKeyguardRestrictedInputMode();
    }


    /**
     * 检测IMEI是否合法
     *
     * @param str String | IMEI
     * @return boolean | true:合法;false:不合法
     */
    public static boolean checkIMEI(String str) {
        return !TextUtils.isEmpty(str) && !str.startsWith("00");
    }


    /**
     * 检测Mac地址是否合法
     *
     * @param str String | mac地址
     * @return boolean | true:合法;false:不合法
     */
    public static boolean checkMacAddress(String str) {
        return !TextUtils.isEmpty(str) && !TextUtils.equals(str, "02:00:00:00:00:00");
    }
}
