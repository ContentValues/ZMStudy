package com.mwee.android.print.printer.tsc;

import com.mwee.android.print.base.PrinterConfig;
import com.mwee.android.print.processor.PrintDataItem;

import java.io.UnsupportedEncodingException;
import java.util.List;


public class TscPrinter {

    public final static int WDITH = 360;
    private final static int FONT_UNIT = 20;
    private TscCommand command;
    private PrinterConfig config;
    private int offSet = 13;
    /**
     * 打印内容的方向
     */
    private int direction = 0;
    private int netLineY = 0;
    private int defaultX = 0;

    public TscPrinter(PrinterConfig printerConfig) {
        this.config = printerConfig;
        command = new TscCommand(config);
    }

    public static int getSBCCaseLength(String text) {
        if (text != null && text.length() != 0) {
            try {
                return text.getBytes("GBK").length;
            } catch (UnsupportedEncodingException var2) {
                var2.printStackTrace();
                return 0;
            }
        } else {
            return 0;
        }
    }

    public void addText(int x, int y, int textSizeX, int textSizeY, String content)
            throws UnsupportedEncodingException {
        String string = "TEXT " + x + "," + y + ",\"TSS24.BF2\",0," + textSizeX + "," + textSizeY + ",\""
                + content + "\"\n";

        command.sendcommand(string);
    }

    public void setup() {
        command.setup(40, 30, 2, offSet, direction);
    }

    //tested
    public void kickDrawer() throws Exception {
        byte[] tcmd = new byte[5];
        tcmd[0] = 0x1B;
        tcmd[1] = 0x70;
        tcmd[2] = 0x00;
        tcmd[3] = 0x40;
        tcmd[4] = 0x50;
        command.sendcommand(tcmd);
    }

    /**
     * 设置标签打印机的偏移量
     *
     * @param offSet int
     */
    public void setOffSet(int offSet) {
        if (offSet > 100) {
            offSet = 100;
        } else if (offSet < -100) {
            offSet = -100;
        }
        this.offSet = offSet;
    }

    public void setReservse(boolean reservse) {
        direction = reservse ? 1 : 0;
        defaultX = reservse ? -5 : 5;
    }

    public void printText(List<PrintDataItem> printList) throws Exception {
        setup();
        command.clearbuffer();

        int sizeX = 1;
        int sizeY = 1;
        int Y = 0;
        int X = defaultX;
        netLineY = 0;
        boolean containsBeep = false;
        for (PrintDataItem temp : printList) {
            if (temp.dataFormat == PrintDataItem.FORMAT_BEEP) {
                containsBeep = true;
                continue;
            }
            sizeX = 1;
            sizeY = temp.fontsize;
            Y = netLineY + temp.marginTop;

            if (temp.tscNextLine) {
                netLineY += FONT_UNIT * temp.fontsize + 4 + temp.marginTop;
            }
            switch (temp.textAlign) {
                case PrintDataItem.ALIGN_LEFT:
                    X = defaultX;
                    break;
                case PrintDataItem.ALIGN_CENTRE:
                    X = (WDITH - getSBCCaseLength(temp.text) * FONT_UNIT) / 2 - 20;
                    break;
                case PrintDataItem.ALIGN_RIGHT:
                    X = WDITH - getSBCCaseLength(temp.text) * (FONT_UNIT) - 30;
                    break;
                default:
                    break;
            }

            addText(X, Y, sizeX, sizeY, temp.text);

        }
        command.printlabel(1, 1);// 加入打印标签命令
//        command.addPush();
        if (containsBeep) {
            command.addBeep();
        }
        command.sendcommand("END\n");

//        command.customPush();
//        command.clearbuffer();
    }
}
