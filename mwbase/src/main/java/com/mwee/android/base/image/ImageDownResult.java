package com.mwee.android.base.image;

import android.graphics.Bitmap;

import com.mwee.android.base.net.BusinessBean;


/**
 * ImageDownResult
 * Created by Virgil on 16/1/7.
 */
public class ImageDownResult extends BusinessBean {
    public String url = "";
    public String key = "";
    public Bitmap mBitmap = null;

    public ImageDownResult() {
    }
}
