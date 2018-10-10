package com.mwee.android.print.printer.tsc;

import android.util.Log;

import com.mwee.android.print.base.PrinterConfig;
import com.mwee.android.print.printer.PrintLog;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;

public class TscCommand {

    private PrinterConfig config;
    public TscCommand(PrinterConfig config) {
        this.config = config;
    }


    public void sendcommand(String message) {
//        TempletLog.d("TSC", "printtext " + message + "");

        try {
            byte[] msgBuffer = message.getBytes("GB2312");
            sendcommand(msgBuffer);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void sendcommand(byte[] message) {
        try {
            this.config.write(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 打印机参数设置
     * @param width int
     * @param height int
     * @param sensor_distance int
     */
    public void setup(int width, int height,  int sensor_distance, int offset,int direction) {
        String message = "";
        String size = "SIZE " + width + " mm" + ", " + height + " mm";
        String sensor_value = "";
            sensor_value += "GAP " + sensor_distance + " mm,0 mm";
        message = size + "\nDENSITY 4\nSPEED 4\n" + sensor_value + "\n"+"DIRECTION "+direction+"\nREFERENCE "+offset+","+(offset)+"\nSHIFT 0\nSET PEEL OFF\nSET TEAR ON\n";

        PrintLog.d("TSC", "setup \n"+message+"");

        byte[] msgBuffer = message.getBytes();
        sendcommand(msgBuffer);
    }

    public void clearbuffer() {
        String message = "CLS\n";

        sendcommand(message);
    }

    public void barcode(int x, int y, String type, int height,
                        int human_readable, int rotation, int narrow, int wide,
                        String string) {
        String message = "";
        String barcode = "BARCODE ";
        String position = x + "," + y;
        String mode = "\"" + type + "\"";
        String height_value = height + "";
        String human_value = human_readable + "";
        String rota = rotation + "";
        String narrow_value = narrow + "";
        String wide_value = wide + "";
        String string_value = "\"" + string + "\"";

        message = barcode + position + " ," + mode + " ," + height_value + " ,"
                + human_value + " ," + rota + " ," + narrow_value + " ,"
                + wide_value + " ," + string_value + "\n";
        byte[] msgBuffer = message.getBytes();
        sendcommand(msgBuffer);
    }

    public void printerfont(int x, int y, String size, int rotation,
                            int x_multiplication, int y_multiplication, String string) {
        String message = "";
        String text = "TEXT ";
        String position = x + "," + y;
        String size_value = "\"" + size + "\"";
        String rota = rotation + "";
        String x_value = x_multiplication + "";
        String y_value = y_multiplication + "";
        String string_value = "\"" + string + "\"";

        message = text + position + " ," + size_value + " ," + rota + " ,"
                + x_value + " ," + y_value + " ," + string_value + "\n";
        sendcommand(message);
    }

    public void printlabel(int quantity, int copy) {
        String message = "";

        message = "PRINT " + quantity + ", " + copy + "\n";
        sendcommand(message);
    }

    public void formfeed() {
        String message = "";

        message = "FORMFEED\n";
        sendcommand(message);
    }
    public void addPush() {
        String message = "";

        message = "FEED 10\n";
        sendcommand(message);
    }
    public void nobackfeed() {
        String message = "";

        message = "SET TEAR OFF\n";
        sendcommand(message);
    }

    public void customPush() {
        String message = "";

        message = "SET PEEL \n";
        sendcommand(message);
    }

    /**
     * 添加蜂鸣
     */
    public void addBeep() {
        String message = "";

        message = "SOUND 3,150 \n";
        sendcommand(message);
    }
    public void sendfile(String filename) {
        try {
            FileInputStream fis = new FileInputStream("/sdcard/Download/"
                    + filename);

            byte[] data = new byte[fis.available()];
            while (fis.read(data) != -1) {
            }
            sendcommand(data);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downloadpcx(String filename) {
        try {
            FileInputStream fis = new FileInputStream("/sdcard/Download/"
                    + filename);

            byte[] data = new byte[fis.available()];

            String download = "DOWNLOAD F,\"" + filename + "\"," + data.length
                    + ",";
            byte[] download_head = download.getBytes();
            while (fis.read(data) != -1) {
            }
            sendcommand(download_head);
            sendcommand(data);


            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downloadbmp(String filename) {
        try {
            FileInputStream fis = new FileInputStream("/sdcard/Download/"
                    + filename);

            byte[] data = new byte[fis.available()];

            String download = "DOWNLOAD F,\"" + filename + "\"," + data.length
                    + ",";
            byte[] download_head = download.getBytes();
            while (fis.read(data) != -1) {
            }
            sendcommand(download_head);
            sendcommand(data);


            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downloadttf(String filename) {
        try {
            FileInputStream fis = new FileInputStream("/sdcard/Download/"
                    + filename);

            byte[] data = new byte[fis.available()];

            String download = "DOWNLOAD F,\"" + filename + "\"," + data.length
                    + ",";
            byte[] download_head = download.getBytes();
            while (fis.read(data) != -1) {
            }
            sendcommand(download_head);
            sendcommand(data);

            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
