package com.mwee.android.print.printer.usbPriter;

import android.content.Context;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.util.Log;

import com.mwee.android.print.printer.PrintLog;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by virgil on 2016/11/24.
 *
 * @author virgil
 */

public class UsbBiz {
    protected static final int STD_USB_REQUEST_GET_DESCRIPTOR = 0x06;
    protected static final int LIBUSB_DT_STRING = 0x03;
    InitDeviceCallBack callBack;
    private Context context;
    private List<Device> usb = new ArrayList<>();
    /**
     * 是否检测串口转USB
     */
    private boolean checkSerial = false;

    public UsbBiz(Context context) {
        this.context = context;
    }


    public UsbBiz(Context context, boolean checkSerial) {
        this.context = context;
        this.checkSerial = checkSerial;
    }

    /**
     * 构建打印机的唯一标示符
     *
     * @param context Context
     * @param device  UsbDevice
     * @return String
     */
    public static String buildSymbol(Context context, UsbDevice device) {
        if (device == null) {
            return "";
        }
        String manufacturer = "", product = "", symbol = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            symbol = device.getProductId() + "_" + device.getVendorId() + "_" + device.getSerialNumber() + "_" + device.getProductName() + "@" + device.getDeviceName();
            return symbol;
        } else {
            UsbManager mUsbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);

            if (!mUsbManager.hasPermission(device)) {
                PrintLog.e("UsbBiz", "(no permission)[" + device.getProductId() + "_" + device.getVendorId() + "]");
                return "";
            }

            UsbInterface intf = device.getInterface(0);
            UsbDeviceConnection connection = mUsbManager.openDevice(device);
            if (null == connection) {
                PrintLog.e("UsbBiz", "(unable to establish connection)" + device.getProductId() + "_" + device.getVendorId() + "]");
            } else {
                connection.claimInterface(intf, true);
                byte[] rawDescs = connection.getRawDescriptors();
                String serialNo = "";

                try {
                    byte[] buffer = new byte[255];
                    int idxMan = rawDescs[14];
                    int idxPrd = rawDescs[15];
                    int idxSerialNO = rawDescs[16];

                    int rdo = connection.controlTransfer(UsbConstants.USB_DIR_IN, STD_USB_REQUEST_GET_DESCRIPTOR,
                            (LIBUSB_DT_STRING << 8) | idxMan, 0, buffer, 0xFF, 0);
                    if (rdo >= 2) {
                        manufacturer = new String(buffer, 2, rdo - 2, "UTF-16LE");
                    }

                    rdo = connection.controlTransfer(UsbConstants.USB_DIR_IN, STD_USB_REQUEST_GET_DESCRIPTOR,
                            (LIBUSB_DT_STRING << 8) | idxPrd, 0, buffer, 0xFF, 0);
                    if (rdo >= 2) {
                        product = new String(buffer, 2, rdo - 2, "UTF-16LE");
                    }

                    rdo = connection.controlTransfer(UsbConstants.USB_DIR_IN, STD_USB_REQUEST_GET_DESCRIPTOR,
                            (LIBUSB_DT_STRING << 8) | idxSerialNO, 0, buffer, 0xFF, 0);
                    if (rdo >= 2) {
                        serialNo = new String(buffer, 2, rdo - 2, "UTF-16LE");
                    }
                    symbol = device.getProductId() + "_" + device.getVendorId() + "_" + serialNo + "_" + product + "@" + device.getDeviceName();

                } catch (UnsupportedEncodingException e) {
                    PrintLog.e("","",e);
                }

            }
        }
        return symbol;
    }

    public void initRefreshUsbDevice(InitDeviceCallBack callBack) {
        this.callBack = callBack;
        new Thread(new Runnable() {
            @Override
            public void run() {
                initUsbDevice();
            }
        }).start();
    }

    public void initNormalUsbDeivce(InitDeviceCallBack callBack) {
        this.callBack = callBack;
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (context == null) {
                    return;
                }
                UsbManager manager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
                if (manager == null) {
                    return;
                }
                HashMap<String, UsbDevice> devices = manager.getDeviceList();
                List<UsbDevice> deviceList = new ArrayList<>();
                deviceList.addAll(devices.values());
                initUsbDevice(deviceList);
            }
        }).start();
    }

    private void initUsbDevice() {
        List<UsbDevice> deviceList = checkSerial ? UsbConnector.checkAllUsbSerialPrinter(context) : UsbConnector.checkAllUsbPrinter(context);
        initUsbDevice(deviceList);
    }

    private void initUsbDevice(List<UsbDevice> deviceList) {
        usb.clear();
        if (deviceList != null && !deviceList.isEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                for (UsbDevice temp : deviceList) {
                    Device product = new Device();
                    product.path = temp.getDeviceName();
                    product.productID = temp.getProductId();
                    product.productName = temp.getProductName();
                    product.venderName = temp.getManufacturerName();
                    product.deviceID = temp.getDeviceId();
                    product.venderID = temp.getVendorId();
                    product.serialNo = temp.getSerialNumber();
                    product.symbol = temp.getProductId() + "_" + temp.getVendorId() + "_" + temp.getSerialNumber() + "_" + temp.getProductName() + "@" + product.path;
                    product.name = product.deviceID + "_" + product.productName + "(" + product.venderName +"_"+temp.getProductId() + "_" + temp.getVendorId()+ ")";

                    usb.add(product);
                }
            } else {
                getUSBName(deviceList);
            }

        }

        if (callBack != null) {
            callBack.finish(usb);
        }
    }

    public void getUSBName(List<UsbDevice> prinerList) {
        if (context == null) {
            return;
        }
        UsbManager mUsbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);

        if (mUsbManager == null) {
            return;
        }
        for (UsbDevice device : prinerList) {
            if (!mUsbManager.hasPermission(device)) {
                UsbConnector.requestPermission(context, device);
                continue;
            }

            PrintLog.d("UsbBiz", "Name: " + device.getDeviceName() + "\n"
                    + "VID: " + device.getVendorId()
                    + "       PID: " + device.getProductId());

            UsbInterface intf = device.getInterface(0);
            int epc = 0;
            epc = intf.getEndpointCount();
            PrintLog.d("UsbBiz", "Endpoints:" + epc + "\n");


            UsbDeviceConnection connection = mUsbManager.openDevice(device);
            if (null == connection) {
                PrintLog.d("UsbBiz", "(unable to establish connection)\n");
            } else {

                // Claims exclusive access to a UsbInterface.
                // This must be done before sending or receiving data on
                // any UsbEndpoints belonging to the interface.
                connection.claimInterface(intf, true);

                // getRawDescriptors can be used to access descriptors
                // not supported directly via the higher level APIs,
                // like getting the manufacturer and product names.
                // because it returns bytes, you can get a variety of
                // different data types.
                byte[] rawDescs = connection.getRawDescriptors();
                String manufacturer = "", product = "", serialNo = "";

                try {
                    byte[] buffer = new byte[255];
                    int idxMan = rawDescs[14];
                    int idxPrd = rawDescs[15];
                    int idxSerialNO = rawDescs[16];

                    int rdo = connection.controlTransfer(UsbConstants.USB_DIR_IN, STD_USB_REQUEST_GET_DESCRIPTOR,
                            (LIBUSB_DT_STRING << 8) | idxMan, 0, buffer, 0xFF, 0);
                    if (rdo >= 2) {
                        manufacturer = new String(buffer, 2, rdo - 2, "UTF-16LE");
                    }

                    rdo = connection.controlTransfer(UsbConstants.USB_DIR_IN, STD_USB_REQUEST_GET_DESCRIPTOR,
                            (LIBUSB_DT_STRING << 8) | idxPrd, 0, buffer, 0xFF, 0);
                    if (rdo >= 2) {
                        product = new String(buffer, 2, rdo - 2, "UTF-16LE");
                    }

                    rdo = connection.controlTransfer(UsbConstants.USB_DIR_IN, STD_USB_REQUEST_GET_DESCRIPTOR,
                            (LIBUSB_DT_STRING << 8) | idxSerialNO, 0, buffer, 0xFF, 0);
                    if (rdo >= 2) {
                        serialNo = new String(buffer, 2, rdo - 2, "UTF-16LE");
                    }

                } catch (UnsupportedEncodingException e) {
                    PrintLog.e("","",e);
                }
                Device printDevice = new Device();
                printDevice.path = device.getDeviceName();
                printDevice.productID = device.getProductId();
                printDevice.productName = product;
                printDevice.venderName = manufacturer;
                printDevice.deviceID = device.getDeviceId();
                printDevice.venderID = device.getVendorId();
                printDevice.serialNo = serialNo;
                printDevice.symbol = device.getProductId() + "_" + device.getVendorId() + "_" + serialNo + "_" + product + "@" + printDevice.path;

                printDevice.name = printDevice.deviceID + "_" + printDevice.productName + "(" + printDevice.venderName +"_"+device.getProductId() + "_" + device.getVendorId() + ")";

                PrintLog.d("UsbBiz", "Manufacturer:" + manufacturer + "\n");
                PrintLog.d("UsbBiz", "Product:" + product + "\n");
                PrintLog.d("UsbBiz", "Serial#:" + connection.getSerial() + "\n");
                usb.add(printDevice);
            }

        }
    }
}
