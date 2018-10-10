package com.mwee.android.print.processor;

import android.graphics.Bitmap;

import com.mwee.android.print.printer.PrintLog;

/**
 * Created by virgil on 2017/5/26.
 *
 * @author virgil
 */

public class BarcodProcessor {
    protected static Bitmap createQRImage(String str, final int width, final int height) {
        try {
            return BarcodeUtil.createQRImage(str, width, height);
        } catch (Exception e) {
            PrintLog.e("","",e);
        } catch (Error e) {
            PrintLog.e("","",e);
        }
        return null;
    }
}
