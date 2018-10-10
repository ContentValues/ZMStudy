package com.mwee.android.print.printer.usbPriter;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.text.TextUtils;
import android.util.Log;

import com.mwee.android.print.base.PrinterConfig;
import com.mwee.android.print.printer.PrintLog;
import com.mwee.android.tools.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张敏 on 16/8/30.
 *
 * @author 张敏
 */
public class UsbPortPrinterConfig extends PrinterConfig {
    public String symbol = "";
    private UsbManager mUsbManager;
    private UsbDevice mUsbDevice;
    private UsbInterface usbInterface;
    private UsbEndpoint inEndpoint;
    private UsbEndpoint outEndpoint;
    private UsbDeviceConnection connection;
    private Context mContext;
    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            PrintLog.w("UsbSerialPrinterConfig", "receiver action: " + action);

            if ("com.android.usb.USB_PERMISSION".equals(action)) {
                synchronized (UsbPortPrinterConfig.class) {
                    UsbDevice device = (UsbDevice) intent.getParcelableExtra("device");
                    if (intent.getBooleanExtra("permission", false) && mUsbDevice.equals(device)) {
                        try {
                            startConnectDevice();
                        } catch (Exception e) {
                            PrintLog.e("", "", e);
                        }
                    } else {
                        PrintLog.e("UsbPrinterConfig", "permission denied for device " + device);
                    }
                }
            }

        }
    };

    public UsbPortPrinterConfig(Context context) {
        this.mContext = context;
        this.mUsbManager = (UsbManager) this.mContext.getSystemService(Context.USB_SERVICE);
    }

    @Override
    public String getUniq() {
        return "usb_" + commandType + "_" + paperSize + "_" + symbol;
    }

    @Override
    public void connect() throws RuntimeException {
        if (mUsbDevice != null && mUsbManager.hasPermission(mUsbDevice)) {
            if (open()) {
                return;
            } else {
                mUsbDevice = null;
            }
        }
        if (!TextUtils.isEmpty(symbol)) {
            this.mUsbDevice = UsbConnector.getUsbDeivceByID(mContext, symbol);
        } else {
            this.mUsbDevice = null;
        }
        if (this.mUsbDevice == null) {
            List<UsbDevice> printerList = UsbConnector.checkAllUsbPrinter(mContext);
            if (printerList != null && printerList.size() > 0) {
                this.mUsbDevice = printerList.get(0);
            } else {
                this.mUsbDevice = null;
            }
        }


        if (mUsbDevice == null) {
            throw new UsbPrinterOpenException("未能识别出USB打印机");
        } else {
            if (!open()) {
                throw new UsbPrinterOpenException("usb打印机[" + mUsbDevice.toString() + "]连接失败");
            }
        }
    }

    @Override
    public void closeConnect() {
        try {
//            if (this.connection != null) {
//                if (this.usbInterface != null) {
//                    this.connection.releaseInterface(this.usbInterface);
//                }
//                this.connection.close();
//                this.connection = null;
//                this.inEndpoint = null;
//                this.inEndpoint = null;
//            }
            PrintLog.i("UsbPrinterConfig", "关闭 " + symbol);
        } catch (Exception e) {
            PrintLog.e("UsbPrinterConfig", "关闭失败 " + symbol, e);
        }

    }

    @Override
    public int write(byte[] data) {
        PrintLog.i("UsbPrinterConfig", "write call output ");
        synchronized (UsbPortPrinterConfig.class) {
            if (this.connection != null) {
                return print(data);
            }
        }
        return -1;
    }

    /**
     * 将byte流输出到USB设备上
     *
     * @param data
     */
    private int print(byte[] data) {
        int result = -1;
        synchronized (UsbPortPrinterConfig.class) {
            result = this.connection.bulkTransfer(this.outEndpoint, data, data.length, 3000);
        }
        return result;
    }


    @Override
    public byte[] read(int targetLength) {

        if (this.connection != null) {

            byte[] retData = new byte[targetLength];
            byte[] result = new byte[targetLength];
            int readLen = 0;
            int totalReadLen = 0;
            while (totalReadLen < targetLength) {
                readLen = this.connection.bulkTransfer(this.inEndpoint, retData, targetLength, 3000);
                if (readLen > 0) {
                    PrintLog.i("PrinterStatus-USB", "len=" + readLen);
                    PrintLog.i("PrinterStatus-USB", retData);
                    System.arraycopy(retData, 0, result, totalReadLen, readLen);
                } else {
                    return result;
                }
                totalReadLen += readLen;


            }
            return result;
        }

        return null;
    }

    /**
     * 连接打印机，判断是否拥有权限
     *
     * @return boolean | true：有权限；false：没有权限
     */
    public boolean open() {
        PrintLog.d("UsbPrinterConfig", "connect to: " + this.mUsbDevice.getDeviceName());

        if (this.mUsbManager.hasPermission(this.mUsbDevice)) {

            try {
                if (this.startConnectDevice()) {
                    return true;
                }
            } catch (Exception e) {
                PrintLog.e("", "", e);
            }
            return false;
        } else {
            requestPermission();
            return false;
        }

    }

    private void requestPermission() {
        PrintLog.e("UsbPrinterConfig", "registerUsbReceiver ");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.mContext, 0, new Intent("com.android.usb.USB_PERMISSION"), 0);
        IntentFilter filter = new IntentFilter("com.android.usb.USB_PERMISSION");
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        this.mContext.registerReceiver(this.mUsbReceiver, filter);
        this.mUsbManager.requestPermission(this.mUsbDevice, pendingIntent);
    }

    private void unregisterUsbReceiver() {
        this.mContext.unregisterReceiver(this.mUsbReceiver);
    }


    private boolean startConnectDevice() {
        boolean hasError = true;
        if (mUsbManager.hasPermission(mUsbDevice)) {
            try {
                connection = mUsbManager.openDevice(mUsbDevice);
                if (mUsbDevice.getInterfaceCount() <= 0) {
                    return false;
                }
                usbInterface = mUsbDevice.getInterface(0);
                int type = usbInterface.getInterfaceClass();
                for (int e = 0; e < usbInterface.getEndpointCount(); ++e) {
                    UsbEndpoint ep = usbInterface.getEndpoint(e);
                    if (ep.getType() == UsbConstants.USB_ENDPOINT_XFER_BULK) {
                        if (ep.getDirection() == UsbConstants.USB_DIR_OUT) {
                            outEndpoint = ep;
                        } else if (ep.getDirection() == UsbConstants.USB_DIR_IN) {
                            inEndpoint = ep;
                        }
                    }
                }
                if (connection != null && connection.claimInterface(usbInterface, true)) {
                    hasError = false;
                }
            } catch (Exception e) {
                PrintLog.e("", "", e);
                return false;
            }
        }
        if (hasError) {
            PrintLog.i("UsbPrinterConfig", "连接失败 " + PrinterConstants.Connect.FAILED);
            closeConnect();
            return false;
        } else {
            PrintLog.i("UsbPrinterConfig", "连接成功 " + PrinterConstants.Connect.SUCCESS);
        }
        return true;
    }

}
