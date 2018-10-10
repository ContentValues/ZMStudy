package xdroid.mwee.com.posdinnerprinter.device;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.mwee.android.print.base.PrinterConfig;
import com.mwee.android.print.printer.bluetoothPrinter.BluetoothPrinterConfig;
import com.mwee.android.print.printer.ipPrinter.IpPrinterConfig;
import com.mwee.android.print.printer.serialPrinter.SerialPortPrinterConfig;
import com.mwee.android.print.printer.usbPriter.UsbPortPrinterConfig;
import com.mwee.android.print.printer.usbserial.UsbSerialPrinterConfig;
import com.mwee.android.sqlite.base.APPConfig;
import com.mwee.android.sqlite.base.DBSimpleUtil;
import com.mwee.android.tools.GlobalCache;
import com.mwee.android.tools.LogUtil;
import java.util.List;
import xdroid.mwee.com.posdinnerprinter.print.PrintUtil;
import xdroid.mwee.com.posdinnerprinter.print.PrinterDBModel;
import xdroid.mwee.com.posdinnerprinter.print.PrinterType;

/**
 * DeviceManager
 * Created by virgil on 16/7/5.
 */
public class DeviceManager {

    private final static DeviceManager instance = new DeviceManager();

    /**
     * 缓存的打印机map,Key为PrinterName,value为Printer实例
     */
    protected ArrayMap<String, PrinterConfig> printerMap = new ArrayMap<>();
    /**
     * 缓存的打印机map,Key为打印机硬件唯一标示,value为Printer实例
     */
    private ArrayMap<String, PrinterConfig> printerUniq = new ArrayMap<>();

    private DeviceManager() {

    }

    public static DeviceManager getInstance() {
        return instance;
    }

    /**
     * 注册打印机
     *
     * @param model PrinterDBModel
     */
    public synchronized void registPrinter(PrinterDBModel model) {
        checkPrinter(model);
    }

    /**
     * 重新构建所有的打印机实例
     */
    public synchronized void rebuildAllPrinter() {

        LogUtil.log("printer", "333");
        List<PrinterDBModel> printerList = DBSimpleUtil.queryList(APPConfig.DB_MAIN, "where fiStatus='1'", PrinterDBModel.class);
        DeviceManager.getInstance().clearPrinterCache();
        for (PrinterDBModel temp : printerList) {
            DeviceManager.getInstance().registPrinter(temp);
        }
    }


    public PrinterConfig getPrinterConfig(String printerName) {
        return printerMap.get(printerName);
    }

    /**
     * 检测是否已经有了指定的打印
     *
     * @param model PrinterDBModel
     */
    private void checkPrinter(PrinterDBModel model) {
        if (model != null && !TextUtils.isEmpty(model.fsPrinterName)) {
            buildPrinter(model);
        }
    }

    public synchronized void clearPrinterCache() {
        if (printerMap == null) {
            printerMap = new ArrayMap<>();
        }
        printerMap.clear();
        if (printerUniq == null) {
            printerUniq = new ArrayMap<>();
        }
    }

    private void checkPrinter(String printerName) {
        if (!TextUtils.isEmpty(printerName)) {
            if (printerMap.get(printerName) != null) {
                return;
            } else {
                PrinterDBModel printer = DBSimpleUtil.query(APPConfig.DB_MAIN, "where fsPrinterName='" + printerName + "'", PrinterDBModel.class);
                if (printer != null) {
                    buildPrinter(printer);
                }
            }
        }
    }

    /**
     * 构建打印机
     *
     * @param model PrinterDBModel
     */
    private synchronized void buildPrinter(PrinterDBModel model) {
        switch (model.fiPrinterCls) {
            case PrinterType.NET: {
                PrinterConfig config;

                config = new IpPrinterConfig();
                ((IpPrinterConfig) config).ip = model.fsIP;
                ((IpPrinterConfig) config).port = 9100;
                PrinterConfig temp = printerUniq.get(config.getUniq());
                if (temp != null) {
                    config = temp;
                } else {
                    printerUniq.put(config.getUniq(), config);
                }
                PrintUtil.buildPrinterInfo(config, model);
                printerMap.put(model.fsPrinterName, config);
            }
            break;
            case PrinterType.SERIAL: {
                SerialPortPrinterConfig config = new SerialPortPrinterConfig();
                config.baudRate = model.fiInt1;
                config.devicePath = "/dev/" + model.fsStr1;
                try {
                    PrinterConfig temp = printerUniq.get(config.getUniq());
                    if (temp != null) {
                        config = (SerialPortPrinterConfig) temp;
                    } else {
                        printerUniq.put(config.getUniq(), config);
                    }
                    PrintUtil.buildPrinterInfo(config, model);
                    printerMap.put(model.fsPrinterName, config);
                } catch (Exception e) {
                    LogUtil.logError(e);
                }
            }
            break;
            case PrinterType.USB: {
                UsbPortPrinterConfig config;

                config = new UsbPortPrinterConfig(GlobalCache.getContext());
                if (!TextUtils.isEmpty(model.fsStr1) && !model.fsStr1.startsWith("BYUSB")) {
                    config.symbol = model.fsStr1;
                }
                PrintUtil.buildPrinterInfo(config, model);
                PrinterConfig temp = printerUniq.get(config.getUniq());
                if (temp != null) {
                    config = (UsbPortPrinterConfig) temp;
                } else {
                    printerUniq.put(config.getUniq(), config);
                }
                PrintUtil.buildPrinterInfo(config, model);
                printerMap.put(model.fsPrinterName, config);
            }
            break;
            case PrinterType.USBSERIAL: {
                UsbSerialPrinterConfig config;

                config = new UsbSerialPrinterConfig(GlobalCache.getContext());
                if (!TextUtils.isEmpty(model.fsStr1) && !model.fsStr1.startsWith("BYUSB")) {
                    config.symbol = model.fsStr1;
                }
                config.baudRate = model.fiInt1;
                PrintUtil.buildPrinterInfo(config, model);

                PrinterConfig temp = printerUniq.get(config.getUniq());
                if (temp != null) {
                    config = (UsbSerialPrinterConfig) temp;
                } else {
                    printerUniq.put(config.getUniq(), config);
                }
                PrintUtil.buildPrinterInfo(config, model);
                printerMap.put(model.fsPrinterName, config);
            }
            break;
            case PrinterType.BLUETOOTH: {
                BluetoothPrinterConfig config;

                config = new BluetoothPrinterConfig(GlobalCache.getContext());
                config.macAddress = model.fsIP;
                PrinterConfig temp = printerUniq.get(config.getUniq());
                if (temp != null) {
                    config = (BluetoothPrinterConfig) temp;
                } else {
                    printerUniq.put(config.getUniq(), config);
                }
                PrintUtil.buildPrinterInfo(config, model);
                printerMap.put(model.fsPrinterName, config);
            }
            default:
                break;
        }
    }


}
