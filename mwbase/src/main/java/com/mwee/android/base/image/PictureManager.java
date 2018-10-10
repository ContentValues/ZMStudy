package com.mwee.android.base.image;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;

import com.mwee.android.base.GlobalCache;
import com.mwee.android.tools.LogUtil;
import com.mwee.android.tools.MD5Util;
import com.mwee.android.tools.SdcardUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PictureManager
 * Created by Virgil on 15/11/13.
 */
public class PictureManager {
    private static LruCache<String, Bitmap> imgCache;
//
//    public static void display(final View container, final String uri) {
//        display(container, uri, null, R.drawable.default_img_small);
//    }
//
//    public static void display(final View container, final String uri, final IDownloadImage callback) {
//        display(container, uri, callback, R.drawable.default_img_small);
//    }

    public static void display(final View container, final String url, final IDownloadImage callback, @DrawableRes int defaultImg) {

        final String uri = (url == null ? "" : url);

        if (TextUtils.isEmpty(url)) {
            setViewDrawable(container, defaultImg);
            if (callback != null) {
//                callback.fail(null);
                return;
            }
        }
        Bitmap bitmap = getBitmapFromMemCache(uri);
        if (bitmap != null) {
            if (callback != null) {
                ImageDownResult result = new ImageDownResult();
                result.mBitmap = bitmap;
                result.url = uri;
                callback.success(result);
            } else {
                if (container != null) {
                    setViewDrawable(container, bitmap);
                }
            }
        } else {
            setViewDrawable(container, defaultImg);
            IDownloadImage defaultCallback = callback;
            if (callback == null) {
                defaultCallback = new IDownloadImage() {
                    @Override
                    public void success(ImageDownResult result) {
                        if (container != null) {
                            Bitmap bitmap = null;
                            if (result != null) {
                                bitmap = result.mBitmap;
                            }
                            if (bitmap == null) {
                                bitmap = getBitmapFromMemCache(uri);
                            }
                            setViewDrawable(container, bitmap);
                        }
                    }

                    @Override
                    public boolean fail(ImageDownResult url) {
                        return false;
                    }
                };
            }
            ImageDownLoader.excute(defaultCallback, uri);
        }
    }

    /**
     * 设置Bitmap给View容器.
     * 如果是ImageView,则是指ImageBitmap;
     * 如果是其他View,则设置对应的Background{@link #setBackground(View, Drawable)}
     *
     * @param container View
     * @param bitmap    Bitmap
     */
    private static void setViewDrawable(View container, Bitmap bitmap) {
        if (container != null && bitmap != null) {
            if (container instanceof ImageView) {
                ((ImageView) container).setImageBitmap(bitmap);
            } else {
                setBackground(container, new BitmapDrawable(container.getResources(), bitmap));
            }
        }
    }

    /**
     * 这是View的默认图
     *
     * @param container  View
     * @param drawableID int
     */
    private static void setViewDrawable(View container, @DrawableRes int drawableID) {
        if (container != null) {
            if (container instanceof ImageView) {
                ((ImageView) container).setImageResource(drawableID);
            } else {
                setBackground(container, drawableID);
            }
        }
    }

    /**
     * 下载图片
     *
     * @param uri String
     */
    public static void downLoadBitmap(String... uri) {
        if (uri != null) {
            List<String> list = new ArrayList<String>();
            Collections.addAll(list, uri);
            downLoadBitmap(list, null);
        }
    }

    /**
     * 下载图片,重载.
     *
     * @param callback IDownloadImage
     * @param uri      String
     */
    @SuppressWarnings("unused")
    public static void downLoadBitmap(IDownloadImage callback, String... uri) {
        if (uri != null) {
            List<String> list = new ArrayList<String>();
            Collections.addAll(list, uri);
            downLoadBitmap(list, callback);
        }
    }

    /**
     * 下载图片
     *
     * @param uri List<String>
     */
    @SuppressWarnings("unused")
    public static void downLoadBitmap(List<String> uri) {
        downLoadBitmap(uri, null);
    }

    /**
     * 下载图片,重载,
     *
     * @param uri      List<String>
     * @param callback IDownloadImage
     */
    public static void downLoadBitmap(List<String> uri, IDownloadImage callback) {
        if (uri != null && !uri.isEmpty()) {
            ImageDownLoader.excute(callback, uri);
        }
    }

    /**
     * 根据图片URL获取Bitmap对象.
     * 方式为:判断本地IO上是有经存在这个相同URL图片的文件,如果不存在,则进行下载.
     * 这里会做一次兼容处理,如果已有的文件缓存加载为BitMap失败, 则会删除这个文件并重新下载.
     *
     * @param url String | 图片原始URL
     * @return Bitmap | 下载完成之后的加载出来的图片Bitmap对象
     */
    public static Bitmap getBitmapByUrl(String url) {
        if (imgCache == null) {
            initImgMemoryCache();
        }
        Bitmap bitmap = null;
        if (!TextUtils.isEmpty(url)) {
            bitmap = getBitmapFromMemCache(url);
            LogUtil.log("ImageDownload memCache result=" + (bitmap == null ? "false" : "true") + ";url=" + url);
            if (bitmap == null) {
                bitmap = gitBitmapFromDisk(url);
            }
        }
        return bitmap;
    }

    /**
     * 初始化MemoryCache
     */
    public synchronized static void initImgMemoryCache() {
        if (imgCache == null) {
            long maxMemory = Runtime.getRuntime().maxMemory() / 1024;
            int cacheSize = (int) maxMemory / 8;
            imgCache = new LruCache<String, Bitmap>(cacheSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    final int bitmapSize = getBitmapSize(value) / 1024;
                    return bitmapSize == 0 ? 1 : bitmapSize;
                }

            };
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static int getBitmapSize(Bitmap bitmap) {

        // From KitKat onward use getAllocationByteCount() as allocated bytes can potentially be
        // larger than bitmap byte count.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return bitmap.getAllocationByteCount();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            return bitmap.getByteCount();
        }

        // Pre HC-MR1
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    /**
     * 将Bitmap压入到MemCache里
     *
     * @param url    String
     * @param bitmap Bitmap
     */
    protected static void pushBitmapToMemCache(String url, Bitmap bitmap) {
        if (imgCache == null) {
            initImgMemoryCache();
        }
        if (!TextUtils.isEmpty(url) && bitmap != null) {
            imgCache.put(url, bitmap);
        }
    }

    /**
     * 存MemCache里获取Bitmap
     *
     * @param url String
     * @return Bitmap
     */
    private static Bitmap getBitmapFromMemCache(String url) {
        if (imgCache == null) {
            initImgMemoryCache();
        }
        if (!TextUtils.isEmpty(url)) {
            return imgCache.get(url);
        } else {
            return null;
        }
    }

    public static boolean diskCacheExist(String url) {
        boolean exist = false;
        if (!TextUtils.isEmpty(url)) {
            String imgPath = getDefaultImgPathByUrl(url);
            File file = new File(imgPath);
            if (file.exists() && file.isFile()) {
                exist = true;
            }
        }
        return exist;
    }

    /**
     * 从Disk里读取Bitmap
     *
     * @param url String
     * @return Bitmap
     */
    public static Bitmap gitBitmapFromDisk(String url) {
        Bitmap bitmap = null;
        if (!TextUtils.isEmpty(url)) {
            String imgPath = getDefaultImgPathByUrl(url);
            File file = new File(imgPath);
            if (file.exists() && file.isFile()) {
                LogUtil.log("ImageDownload getBitmapByUrl success,url=" + url);
                bitmap = BitmapFactory.decodeFile(imgPath);
            }
        }
        return bitmap;
    }

    /**
     * 获取图片url的本地缓存文件完整路径
     *
     * @param url String | 图片原始URL
     * @return String
     */
    public static String getDefaultImgPathByUrl(String url) {
        String path = "";
        if (!TextUtils.isEmpty(url)) {
            if (url.startsWith("file:///")) {
                path = url;
            } else {
                String imgName = MD5Util.getMD5String(url);
                if (SdcardUtil.isSDCardUsable(GlobalCache.getContext())) {
                    path = GlobalCache.getContext().getExternalCacheDir() + "/photo/" + imgName;
                } else {
                    path = GlobalCache.getContext().getCacheDir() + "/photo/" + imgName;
                }
            }
        }
        return path;
    }

    public static void setBackground(View imageView, Drawable drawable) {
        if (imageView != null) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                imageView.setBackground(drawable);
            } else {
                imageView.setBackgroundDrawable(drawable);
            }
        }
    }

    public static void setBackground(View imageView, @DrawableRes int drawableID) {
        if (imageView != null) {
            imageView.setBackgroundResource(drawableID);
        }
    }
}
