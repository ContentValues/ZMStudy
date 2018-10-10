package com.mwee.android.print.printer.bluetoothPrinter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.ParcelUuid;
import android.text.TextUtils;

import com.mwee.android.print.base.PrinterConfig;
import com.mwee.android.print.printer.PrintLog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * 蓝牙打印机的相关连接
 * Created by Liming on 16/8/31.
 * @author virgil
 */
public class BluetoothPrinterConfig extends PrinterConfig {

    private final static String UUID_INNNER = "00001101-0000-1000-8000-00805f9b34fb";
    public String macAddress = null;
    Context mCtx;
    private BluetoothSocket mSocket;

    public BluetoothPrinterConfig(Context c) {
        mCtx = c;
    }

    @Override
    public String getUniq() {
        return "bluetooth" + "|" + macAddress;
    }

    @Override
    public void connect() throws Exception {
        if (macAddress == null) {
            macAddress = "";
        }
        List<BluetoothDevice> mPairedDevices = findPairedBtDevices();

        if (mPairedDevices != null) {
            try {
                //首先匹配蓝牙的mac地址
                for (BluetoothDevice temp : mPairedDevices) {
                    if (!TextUtils.isEmpty(temp.getAddress())) {
                        if (temp.getAddress().equalsIgnoreCase(macAddress)) {
                            openBtConnection(temp);
                            return;
                        }
                    }
                }
                //然后匹配蓝牙设备的DeviceClass
                for (BluetoothDevice temp : mPairedDevices) {
                    if (temp.getBluetoothClass() != null) {
                        if (1536 == temp.getBluetoothClass().getMajorDeviceClass()) {
                            openBtConnection(temp);
                            return;
                        }
                    }
                }
                //最后匹配UUID
                for (BluetoothDevice temp : mPairedDevices) {
                    ParcelUuid[] uuids = temp.getUuids();
                    if (uuids != null && uuids.length > 0) {
                        for (ParcelUuid tempUUID : uuids) {
                            if (UUID_INNNER.equalsIgnoreCase(tempUUID.toString())) {
                                openBtConnection(temp);
                                return;
                            }
                        }
                    }
                }
                //如果都不匹配，则直接连配对的第一个设备
                openBtConnection(mPairedDevices.get(0));

            } catch (Exception e) {
                BlueToothPrinterException runtimeException = new BlueToothPrinterException("蓝牙连接失败");
                runtimeException.setStackTrace(e.getStackTrace());
                throw runtimeException;
            }

        } else {
            throw new BlueToothPrinterException("请将指定的蓝牙打印机进行配对");

        }

    }

    @Override
    public void closeConnect() {
        try {
            if (outputStream != null) {
                outputStream.close();
            }
            outputStream = null;
            if (mSocket != null) {
                mSocket.close();
            }
            mSocket = null;
        } catch (Exception e) {
            PrintLog.e("","",e);
        }
    }


    private void openBtConnection(BluetoothDevice mDevice) throws Exception {
        UUID uuid = UUID.fromString(UUID_INNNER);
        mSocket = mDevice.createRfcommSocketToServiceRecord(uuid);
        mSocket.connect();
        outputStream = mSocket.getOutputStream();
        inputStream = mSocket.getInputStream();
    }

    @Override
    public int write(byte[] data) {
        if (outputStream != null) {
            try {
                outputStream.write(data);
                outputStream.flush();
                return data.length;
            } catch (IOException e) {
                PrintLog.e("","",e);
            }

        }
        return -1;
    }

    private List<BluetoothDevice> findPairedBtDevices() {
        List<BluetoothDevice> pairedBtDevices = new ArrayList<BluetoothDevice>();

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            throw new BlueToothPrinterException("使用蓝牙打印机前,请开启蓝牙");
        }
        if (!mBluetoothAdapter.isEnabled()) {
            throw new BlueToothPrinterException("使用蓝牙打印机前,请开启蓝牙");
        }

        Set<BluetoothDevice> bondedDevices = mBluetoothAdapter.getBondedDevices();
        if (bondedDevices != null && !bondedDevices.isEmpty()) {
            pairedBtDevices.addAll(bondedDevices);
        }

        return pairedBtDevices;
    }
}
