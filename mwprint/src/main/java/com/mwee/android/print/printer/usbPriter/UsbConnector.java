package com.mwee.android.print.printer.usbPriter;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.SystemClock;
import android.text.TextUtils;

import com.mwee.android.print.R;
import com.mwee.android.print.printer.PrintLog;
import com.mwee.android.print.printer.usbserial.driver.UsbSerialDriver;
import com.mwee.android.print.printer.usbserial.driver.UsbSerialProber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by virgil on 2016/11/24.
 *
 * @author virgil
 */
public class UsbConnector extends BroadcastReceiver {
    final static String ACTION_PERMISSION = "com.mwee.android.printer.REQUEST_USB_PERMISSION";

    /**
     * 获取所有符合usb打印机规则的UsbDevice列表
     *
     * @param context Context
     * @return List<UsbDevice>
     */
    public static List<UsbDevice> checkAllUsbPrinter(Context context) {
        if (context == null) {
            return new ArrayList<>(0);
        }
        UsbManager manager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        if (manager == null) {
            return new ArrayList<>(0);
        }
        HashMap<String, UsbDevice> devices = manager.getDeviceList();
        return search(context, devices);
    }

    /**
     * 获取所有串口转接的USB设备
     *
     * @param context Context
     * @return List<UsbDevice>
     */
    public static List<UsbDevice> checkAllUsbSerialPrinter(Context context) {
        if (context == null) {
            return new ArrayList<>(0);
        }
        UsbManager manager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        if (manager == null) {
            return new ArrayList<>(0);
        }
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        if (availableDrivers == null || availableDrivers.isEmpty()) {
            return new ArrayList<>(0);
        }
        List<UsbDevice> result = new ArrayList<>();
        for (UsbSerialDriver temp : availableDrivers) {
            result.add(temp.getDevice());
        }
        return result;
    }

    /**
     * 获取所有串口转接的USBDriver
     *
     * @param context Context
     * @return List<UsbSerialDriver>
     */
    public static List<UsbSerialDriver> checkAllUsbSerialDriver(Context context) {
        if (context == null) {
            return new ArrayList<>(0);
        }
        UsbManager manager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        if (manager == null) {
            return new ArrayList<>(0);
        }
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        if (availableDrivers == null || availableDrivers.isEmpty()) {
            return new ArrayList<>(0);
        }
        return availableDrivers;
    }

    /**
     * 根据设备ID返回{@link UsbDevice}对象，设备ID的规则是：productId_vendorId_serialno
     *
     * @param context Context
     * @param id      String | 设备ID
     * @return UsbDevice
     */
    public static UsbDevice getUsbDeivceByID(Context context, String id) {
        if (context == null) {
            return null;
        }
        UsbManager manager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        if (manager == null) {
            return null;
        }
        HashMap<String, UsbDevice> devices = manager.getDeviceList();
        //首先获取所有识别出来的设备
        List<UsbDevice> printerList = new ArrayList<>();
        printerList.addAll(devices.values());
        String hardSymbol = id.substring(0, id.indexOf("@"));
        if (id.contains("@")) {
            hardSymbol = id.substring(0, id.indexOf("@"));
        } else {
            hardSymbol = id;
        }
        if (printerList.size() > 0) {
            for (UsbDevice device : printerList) {
                if (TextUtils.equals(UsbBiz.buildSymbol(context, device), id)) {
                    requestPermission(context, device);
                    return device;
                }
            }
            //如果根据Symbole没有找到，则移除掉最后的usbpath进行匹配
            for (UsbDevice device : printerList) {
                String deviceFull = UsbBiz.buildSymbol(context, device);
                String deviceSymbol;
                if (deviceFull.contains("@")) {
                    deviceSymbol = deviceFull.substring(0, deviceFull.indexOf("@"));
                } else {
                    deviceSymbol = deviceFull;
                }
                if (TextUtils.equals(deviceSymbol, hardSymbol)) {
                    requestPermission(context, device);
                    return device;
                }
            }
            //如果没有能够根据ID匹配出USB打印机，则默认使用当前可用打印机列表的第一个打印机
            return null;
        } else {
            //如果没有能够匹配到USB打印机，则在目前连接的所有USB设备中进行匹配
            if (devices != null && devices.size() > 0) {
                for (UsbDevice device : devices.values()) {
                    if (TextUtils.equals(UsbBiz.buildSymbol(context, device), id)) {
                        requestPermission(context, device);
                        return device;
                    }
                }
                //如果根据Symbole没有找到，则移除掉最后的usbpath进行匹配
                for (UsbDevice device : devices.values()) {
                    String deviceFull = UsbBiz.buildSymbol(context, device);
                    String deviceSymbol;
                    if (deviceFull.contains("@")) {
                        deviceSymbol = deviceFull.substring(0, deviceFull.indexOf("@"));
                    } else {
                        deviceSymbol = deviceFull;
                    }
                    if (TextUtils.equals(deviceSymbol, hardSymbol)) {
                        requestPermission(context, device);
                        return device;
                    }
                }
            }
        }
        return null;
    }

    public static void requestPermission(Context context, UsbDevice device) {
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_PERMISSION), 0);
        UsbManager mUsbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        if (mUsbManager != null && !mUsbManager.hasPermission(device)) {
            PrintLog.w("UsbConnector", "has permission no " + device.toString());
            mUsbManager.requestPermission(device, pendingIntent);
        } else {
            PrintLog.w("UsbConnector", "has permission yes " + device.toString());
        }
    }

    /**
     * 遍历所有的usb设备，按照匹配规则{@link #isUsbPrinter(Context, UsbDevice, int)}，如果设备符合usb打印机的特征，则添加到list里返回。
     *
     * @param context Context
     * @param devices HashMap<String, UsbDevice>
     * @return List<UsbDevice>
     */
    private static List<UsbDevice> search(Context context, HashMap<String, UsbDevice> devices) {
        if (devices == null || devices.isEmpty()) {
            return new ArrayList<>();
        }
        List<UsbDevice> printerList = new ArrayList<>();
        for (UsbDevice device : devices.values()) {
            PrintLog.i("UsbConnector", "USBDevice info[" + device.getDeviceId() + "," + device.toString());
            if (isUsbPrinter(context, device, 1)) {
                requestPermission(context, device);
                printerList.add(device);
            }
        }
        return printerList;
    }

    /**
     * 判断当前usb设备是否是打印机
     *
     * @param context Context
     * @param device  UsbDevice
     * @param method  int | 匹配打印机的方案：
     *                第一套方案：匹配{@link UsbInterface#getInterfaceClass()}是否等于{@link UsbConstants#USB_CLASS_PRINTER}
     *                第二套方案：匹配{@link UsbDevice#getVendorId()}和{@link UsbDevice#getProductId()}是否和预置的设备列表里一致
     *                第三套方案：匹配{@link UsbDevice#getVendorId()}是否和预置的设备列表里一致
     * @return boolean
     */
    public static boolean isUsbPrinter(Context context, UsbDevice device, int method) {
        if (method > 3) {
            return false;
        }
        XmlResourceParser xrp = context.getResources().getXml(R.xml.devices);
        boolean isPrinter = false;

        switch (method) {
            case 1:
                for (int i = 0; i < device.getInterfaceCount(); i++) {
                    UsbInterface tempUsbInterface = device.getInterface(i);
                    isPrinter = tempUsbInterface.getInterfaceClass() == UsbConstants.USB_CLASS_PRINTER;
                    if (isPrinter) {
                        break;
                    }
                }
                break;
            case 2:
                return checkXML(xrp, device, false);
            case 3:
                return checkXML(xrp, device, true);
            default:
                break;
        }
        if (!isPrinter) {
            return isUsbPrinter(context, device, method + 1);
        }
        return true;
    }

    /**
     * 遍历已预置的打印机
     *
     * @param xrp          XmlResourceParser
     * @param device       UsbDevice
     * @param onlyVendorID boolean | 是否只要VenderID匹配就可以
     * @return boolean
     */
    private static boolean checkXML(XmlResourceParser xrp, UsbDevice device, boolean onlyVendorID) {
        // 判断是否到了文件的结尾
        try {
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                //文件的内容的起始标签开始，注意这里的起始标签是test.xml文件里面<resources>标签下面的第一个标签
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    if (TextUtils.equals(xrp.getName(), "usb-device")) {
                        boolean isprinter = xrp.getAttributeIntValue(null, "isprinter", 1) == 1;
                        int venderid = xrp.getAttributeIntValue(null, "vendor-id", 0);
                        if (onlyVendorID && venderid == device.getVendorId() && !isprinter) {
                            return true;
                        }
                        int product_id = xrp.getAttributeIntValue(null, "product-id", 0);
                        if (venderid == device.getVendorId() && product_id == device.getProductId() && isprinter) {
                            return true;
                        }
                    }
                }
                xrp.next();
            }
        } catch (Exception e) {
            PrintLog.e("","",e);
        }
        return false;
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        String action = intent.getAction();

        PrintLog.w("UsbConnector", "receiver action: " + action + ",boot elipsed=" + SystemClock.elapsedRealtime() / 1000);

        if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
            //如果开机时间少于80秒，则返回
            if (SystemClock.elapsedRealtime() < 80 * 1000) {
                return;
            }
            final UsbDevice device = intent.getParcelableExtra("device");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    requestPermission(context.getApplicationContext(), device);
                }
            }).start();

        } else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
//            closeConnect();
        } else if (ACTION_PERMISSION.endsWith(action)) {
            PrintLog.w("UsbConnector", "permission request result " + intent.getBooleanExtra("permission", false));
        }
    }


}
