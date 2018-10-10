package com.mwee.android.print.printer.serialPrinter;


import com.mwee.android.print.base.PrinterConfig;

import java.io.File;

import android_serialport_api.SerialPort;

/**
 * SerialPortPrinterConfig
 * Created by chris on 16/5/24.
 */
public class SerialPortPrinterConfig extends PrinterConfig {
    public String deviceName = "";
    public String devicePath = "";
    public int baudRate = 0;
    private SerialPort serialPort;

    public SerialPortPrinterConfig() {
    }

    @Override
    public String getUniq() {
        StringBuilder sb = new StringBuilder();
        sb.append(type).append("|").append(":").append(devicePath).append(":").append(baudRate);
        return sb.toString();
    }

    @Override
    public void connect() throws Exception {
        closeConnect();
        serialPort = new SerialPort(new File(devicePath), baudRate, 0);
        outputStream = serialPort.getOutputStream();
        inputStream = serialPort.getInputStream();
    }

    @Override
    public void closeConnect() {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            outputStream = null;
        }
        if (serialPort != null) {
            try {
                serialPort.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            serialPort = null;
        }
        if(inputStream != null){
            try {
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            inputStream = null;
        }
    }

    @Override
    public int write(byte[] data) throws Exception {
        if (outputStream != null) {
            outputStream.write(data);
            outputStream.flush();
            return data.length;
        }
        return -1;
    }
}
