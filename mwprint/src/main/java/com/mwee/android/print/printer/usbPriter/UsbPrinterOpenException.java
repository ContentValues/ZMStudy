package com.mwee.android.print.printer.usbPriter;

/**
 * Created by virgil on 2017/8/31.
 */

public class UsbPrinterOpenException extends RuntimeException {
    public UsbPrinterOpenException() {
        super();
    }

    public UsbPrinterOpenException(String msg) {
        super(msg);
    }
}
