package com.mwee.android.print.printer.usbPriter;

/**
 * 设备
 * Created by virgil on 2016/11/24.
 */

public class Device {
    public String symbol = "";
    public String name = "";
    public int venderID = 0;
    public String venderName = "";
    public int productID = 0;
    public String productName = "";
    public int deviceID = 0;
    public String serialNo="";
    public String path="";//设备路径
    public Device() {

    }

    @Override
    public String toString() {
        return name;
    }
}
