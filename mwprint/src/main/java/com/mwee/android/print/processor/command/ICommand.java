package com.mwee.android.print.processor.command;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseIntArray;

import com.mwee.android.print.base.PrinterConfig;
import com.mwee.android.print.encoding.Lang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by virgil on 2018/1/25.
 */

public abstract class ICommand {
    /* Underline */
    static public byte UNDERLINE_NONE = 0;
    static public byte UNDERLINE_SINGLE = 1;
    static public byte UNDERLINE_DOUBLE = 2;
    public static int GREY_EDGE = 0xbebebe;
    /* ASCII codes */
    static byte NUL = 0x00;
    static byte LF = 0x0A;
    public static byte ESC = 0x1B;
    static byte FS = 0x1C;
    static byte GS = 0x1D;
    static byte HT = 0x09;
    protected List<byte[]> cmdList = new ArrayList<>();
    public SparseIntArray cmdBitmapIndex = new SparseIntArray();
    Context mContext;


    protected static byte[] charToByte(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }

    public abstract void reset() throws Exception ;

    /**
     * 选择对齐方式
     * @param just | 0，左对齐；1，中间对齐；2，右对齐
     * @throws Exception |
     */
    public abstract void setAlign(byte just) throws Exception ;

    /**
     * 打印文字，默认中文
     * @param data String
     * @throws Exception |
     */
    public abstract void printText(String data) throws Exception ;


    /**
     * Add text to the buffer.
     * Text should either be followed by a line-break, or feed() should be called
     * after this to clear the print buffer.
     *
     * @param data $str Text to print
     * @param lang int  | see{@link Lang}
     * @throws Exception
     */
    public abstract void printText(String data, @Lang int lang) throws Exception;

    /**
     * 获取指定语言的encoding
     *
     * @param lang int  | see{@link Lang}
     * @return String
     */
    public abstract String getEncodeByLang(@Lang int lang) ;

    /**
     * 设置字体
     *
     * @param font byte
     * @throws Exception |
     */
    public abstract void setFont(byte font) throws Exception ;

    /**
     * 设置字号
     *
     * @param size int
     * @throws Exception |
     */
    public abstract void setFontSize(int size) throws Exception ;

    /**
     * 设置字体颜色
     *
     * @param color int | 0,黑色；1，红色
     * @throws Exception |
     */
    public abstract void setFontColor(byte color) throws Exception ;

    /**
     * 设置字号（针式打印机）
     *
     * @param size int
     * @throws Exception |
     */
    public abstract void setFontSizeFor76(int size) throws Exception ;

    /**
     * 设置下划线
     *
     * @param underline
     * @throws Exception
     */
    public abstract void setUnderline(byte underline) throws Exception ;

    /**
     * 设置为粗体
     * @param bold byte
     * @throws Exception |
     */
    public abstract void setBold(byte bold) throws Exception ;

    /**
     * 打印并走纸
     * @param lines byte
     * @throws Exception |
     */
    public abstract void feed(byte lines) throws Exception ;

    public abstract void margin(byte mm) throws Exception ;

    /**
     * 切纸
     */
    public abstract void cut() throws Exception ;

    /**
     * 开始蜂鸣，默认响3次，每次50*3毫秒
     */
    public abstract void beep() throws Exception ;

    /**
     * 开钱箱
     *
     * @throws Exception |
     */
    public abstract void kickDrawer() throws Exception ;


    /**
     * 打印图片
     * @param bm Bitmap
     * @throws Exception |
     */
    public abstract void printBitmap(Bitmap bm) throws Exception ;

    /**
     * 打印二维码的图片
     * @param bm Bitmap
     * @throws Exception |
     */
    public abstract void printQRBitmap(Bitmap bm) throws Exception ;

    public boolean isNumeric(String checkStr) {
        try {
            Integer.parseInt(checkStr);
            return true; // Did not throw, must be a number
        } catch (NumberFormatException err) {
            return false; // Threw, So is not a number
        }
    }
    /**
     * 开商米的钱箱
     *
     * @throws Exception |
     */
    public void kickDrawerSunmi() throws Exception {
        byte[] cmd = new byte[5];
        cmd[0] = 0x10;
        cmd[1] = 0x14;
        cmd[2] = 0x00;
        cmd[3] = 0x00;
        cmd[4] = 0x00;
        cmdList.add(cmd);
    }
    /**
     * 打印条形码
     *
     * @param barcode String | 条码内容
     * @param big     boolean | 是否是大条形码
     * @throws Exception
     */
    public abstract void printBarcode(String barcode, boolean big) throws Exception ;

    public abstract void printFoodBoxBarcode(String barcode) throws Exception ;

    public abstract void setChineseEncodingMode() throws IOException ;

    public abstract void setEncodingType(byte n) throws IOException ;

    public abstract void setLanguage() throws IOException ;

    public abstract void setJDWZ() throws IOException ;

    public abstract void setGprintText(String data) throws IOException;


    /**
     * 设置默认行间距
     *
     * @throws IOException
     */
    public abstract void reSetLine() throws IOException ;

    public abstract  void setLine(int margin) throws IOException ;

    public List<byte[]> getFinalCmdList() {
        return cmdList;
    }

    /**
     * 输出指令
     * @param printer PrinterConfig
     * @throws Exception
     */
    public abstract int startWrite(PrinterConfig printer, String uniq) throws Exception;
}
