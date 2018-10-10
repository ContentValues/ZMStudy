package com.mwee.android.print.printer.ipPrinter;


import android.os.SystemClock;
import android.text.TextUtils;

import com.mwee.android.print.base.PrinterConfig;
import com.mwee.android.print.printer.PrintLog;
import com.mwee.android.tools.LogUtil;

import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * IpPrinterConfig
 * Created by chris on 16/5/24.
 */
public class IpPrinterConfig extends PrinterConfig {

    public String ip = "";
    public int port = 0;
    private Socket socket;

    public IpPrinterConfig() {
    }

    @Override
    public String getUniq() {
        StringBuilder sb = new StringBuilder();
        sb.append(type).append("|").append(ip).append(":").append(port);
        return sb.toString();
    }

    @Override
    public void connect() throws Exception {

        socket = null;
        outputStream = null;

        if (TextUtils.isEmpty(ip) || port <= 0) {
            throw new NullPointerException("ip or port is illegal");
        }
        socket = new Socket();
//        if (socket.getSendBufferSize() > 1024) {
//            socket.setSendBufferSize(1024);
//        }
        socket.connect(new InetSocketAddress(ip, port), timeOut * 1000);
        socket.setSoTimeout(1000);
        outputStream = socket.getOutputStream();
        inputStream = socket.getInputStream();
    }

    @Override
    public void closeConnect() {
        try {
            if (outputStream != null) {
                outputStream.close();
            }
            outputStream = null;
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            socket = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int write(byte[] data) throws Exception {
        int writeLength = data.length;
        if (outputStream != null) {
            long start = SystemClock.elapsedRealtime();
            outputStream.write(data);

            outputStream.flush();
            long writeCost = SystemClock.elapsedRealtime() - start;
            if (writeCost > 10) {
                PrintLog.i("CommandEsc","NetPrinter steam write cost=" + writeCost);
            }

        }

        if (inputStream != null) {
//            int length = inputStream.read();
//            LogUtil.log("NetPrinter read=" + length);
        }
        return writeLength;
    }
}
