package com.mwee.android.print.printer.usbserial;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.text.TextUtils;
import android.util.Log;

import com.mwee.android.print.base.PrinterConfig;
import com.mwee.android.print.printer.PrintLog;
import com.mwee.android.print.printer.usbPriter.PrinterConstants;
import com.mwee.android.print.printer.usbPriter.UsbConnector;
import com.mwee.android.print.printer.usbPriter.UsbPrinterOpenException;
import com.mwee.android.print.printer.usbserial.driver.UsbSerialDriver;
import com.mwee.android.print.printer.usbserial.driver.UsbSerialPort;
import com.mwee.android.tools.LogUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 串口转接的USB打印机的类型
 *
 * @author virgil
 */
public class UsbSerialPrinterConfig extends PrinterConfig {
    /**
     * 唯一标示
     */
    public String symbol = "";
    /**
     * 波特率
     */
    public int baudRate = 38400;
    private UsbManager mUsbManager;
    private UsbDevice mUsbDevice;
    private Context mContext;
    /**
     * 是否在等待授权
     */
    private boolean isWaitingPermission = false;
    /**
     * 等待授权的打印数据
     */
    private List<byte[]> waitingData = new ArrayList<>();
    private UsbSerialPort port = null;
    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if ("com.android.usb.USB_PERMISSION".equals(action)) {

                final UsbDevice device = intent.getParcelableExtra("device");
                if (mUsbDevice.equals(device)) {
                    if (intent.getBooleanExtra("permission", false)) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    prepareDevice();
                                    open();
                                    releaseWaitingData();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                isWaitingPermission = false;
                            }
                        }).start();
                    }
                    unregisterUsbReceiver();
                }
            }

        }
    };

    public UsbSerialPrinterConfig(Context context) {
        this.mContext = context;
        this.mUsbManager = (UsbManager) this.mContext.getSystemService(Context.USB_SERVICE);
    }

    @Override
    public String getUniq() {
        return "usbSerial_" + commandType + "_" + paperSize + "_"+ symbol;
    }

    @Override
    public void connect() throws Exception {
        if (port != null && mUsbDevice != null && mUsbManager.hasPermission(mUsbDevice)) {
            if (!open()) {
                throw new UsbPrinterOpenException("USB串口打印机[" + mUsbDevice.toString() + "]连接失败");
            }
        } else {
            prepareDevice();

            if (mUsbDevice == null || this.port == null) {
                throw new UsbPrinterOpenException("未能识别出USB串口打印机");
            } else {
                if (!open()) {
                    throw new UsbPrinterOpenException("USB串口打印机[" + mUsbDevice.toString() + "]连接失败");
                }
            }
        }
        if (!check()) {
            throw new UsbPrinterOpenException("USB串口打印机[" + mUsbDevice.toString() + "]检测连接失败");
        }


    }

    /**
     * 准备设备
     */
    private void prepareDevice() {
        List<UsbSerialDriver> printerList = UsbConnector.checkAllUsbSerialDriver(mContext);
        if (printerList != null && printerList.size() > 0) {
            if (TextUtils.isEmpty(symbol)) {
                UsbSerialDriver driver = printerList.get(0);
                this.mUsbDevice = driver.getDevice();
                if (driver.getPorts() != null && driver.getPorts().size() > 0) {
                    this.port = driver.getPorts().get(0);
                } else {
                    throw new UsbPrinterOpenException("未能识别出USB串口打印机Port");
                }
            } else {
                this.mUsbDevice = UsbConnector.getUsbDeivceByID(mContext, symbol);
                if (this.mUsbDevice != null) {
                    for (UsbSerialDriver temp : printerList) {
                        if (temp.getDevice().getDeviceId() == this.mUsbDevice.getDeviceId()) {
                            this.mUsbDevice = temp.getDevice();
                            if (temp.getPorts() != null && temp.getPorts().size() > 0) {
                                this.port = temp.getPorts().get(0);
                            }
                            break;
                        }
                    }
                } else {
                    UsbSerialDriver driver = printerList.get(0);
                    this.mUsbDevice = driver.getDevice();
                    if (driver.getPorts() != null && driver.getPorts().size() > 0) {
                        this.port = driver.getPorts().get(0);
                    } else {
                        throw new UsbPrinterOpenException("未能识别出USB串口打印机Port");
                    }
                }
            }
        } else {
            this.mUsbDevice = null;
        }
    }

    @Override
    public void closeConnect() {
        try {
            Thread.sleep(200);
            synchronized (UsbSerialPrinterConfig.class) {
                if (port != null && !port.isClosed()) {
                    port.close();
//                    port = null;
                }
            }
            PrintLog.e("UsbSerialPrinterConfig", "关闭成功 " +symbol);
        } catch (Exception e) {
            PrintLog.e("UsbSerialPrinterConfig", "关闭失败 "+symbol ,e);
        }

    }

    @Override
    public int write(byte[] data) throws IOException {
        PrintLog.w("UsbSerialPrinterConfig", "write call output ");
        synchronized (UsbSerialPrinterConfig.class) {

            if (this.port != null) {
                return print(data);

            } else {
                if (isWaitingPermission && !waitingData.contains(data)) {
                    waitingData.add(data);
                    return data.length;
                }
            }
        }
        return -1;
    }

    /**
     * 将byte流输出到USB设备上
     *
     * @param data
     */
    private int print(byte[] data) throws IOException {
        int result = -1;
        synchronized (UsbSerialPrinterConfig.class) {
            if (port == null) {
                return result;
            }
            result = port.write(data, 3000);
        }
        return result;
    }

    private void releaseWaitingData() throws IOException {
        synchronized (UsbSerialPrinterConfig.class) {
            if (isWaitingPermission && waitingData != null && waitingData.size() > 0) {
                for (byte[] temp : waitingData) {
                    print(temp);
                }
                waitingData.clear();
                isWaitingPermission = false;
            }
        }
    }

    @Override
    public byte[] read(int readLength) {


        return null;
    }

    /**
     * 检测打印机链接状况
     *
     * @return boolean
     */
    private boolean check() {
        //重置缓冲区
        try {
            byte[] tcmd = new byte[2];
            tcmd[0] = 0x1B;
            tcmd[1] = 0x40;
            write(tcmd);

            //发送打印机状态的实时回调
            tcmd = new byte[3];
            tcmd[0] = 0x10;
            tcmd[1] = 0x04;
            tcmd[2] = 0x04;
            write(tcmd);

            byte buffer[] = new byte[16];
            int numBytesRead = port.read(buffer, 1000);
            PrintLog.i("UsbSerialPrinterConfi","PrinterManager read " + numBytesRead);
            return numBytesRead > 0;
        } catch (IOException e) {
            PrintLog.e("","",e);
        }
        return false;
    }

    /**
     * 连接打印机，判断是否拥有权限
     *
     * @return boolean | true：有权限；false：没有权限
     */
    public boolean open() throws IOException {
        PrintLog.d("UsbSerialPrinterConfig", "connect to: " + this.mUsbDevice.getDeviceName());

        if (this.mUsbManager.hasPermission(this.mUsbDevice)) {
            isWaitingPermission = false;
            if (!port.isOpened()) {
                UsbDeviceConnection connection = mUsbManager.openDevice(this.mUsbDevice);
                port.open(connection);
                port.setParameters(baudRate, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);
            }
            return true;
        } else {
            requestPermission();
            return false;
        }

    }

    private void requestPermission() {
        PrintLog.e("UsbSerialPrinterConfig", "registerUsbReceiver ");
        isWaitingPermission = true;
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


}
