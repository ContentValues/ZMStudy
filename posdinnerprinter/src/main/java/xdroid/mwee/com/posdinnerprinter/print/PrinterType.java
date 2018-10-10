package xdroid.mwee.com.posdinnerprinter.print;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * PrinterType
 * Created by virgil on 16/7/11.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@IntDef(value = {PrinterType.NET, PrinterType.SERIAL, PrinterType.PARELL, PrinterType.USB, PrinterType.BLUETOOTH, PrinterType.USBSERIAL, PrinterType.CLOUD})
public @interface PrinterType {
    /**
     * 网口
     */
    int NET = 1;
    /**
     * 串口
     */
    int SERIAL = 2;
    /**
     * 并口
     */
    int PARELL = 3;
    /**
     * usb
     */
    int USB = 4;
    /**
     * 蓝牙
     */
    int BLUETOOTH = 7;
    /**
     * USB(串口转接)
     */
    int USBSERIAL = 8;
    /**
     * 云打印
     */
    int CLOUD = 9;
    /**
     * P433
     */
    int P433 = 10;
}
