package cn.com.jsj.lib_common.imageloader;

import android.content.Context;
import android.widget.ImageView;

import cn.com.jsj.lib_common.imageloader.listener.ImageBitmapLoaderListener;
import cn.com.jsj.lib_common.imageloader.listener.ImageDrawableLoaderListener;
import cn.com.jsj.lib_common.imageloader.listener.ImageSaveListener;
import cn.com.jsj.lib_common.imageloader.listener.SourceReadyListener;
import cn.com.jsj.lib_common.imageloader.progress.ProgressLoadListener;


/**
 * 图片加载器
 */
public class ImageLoaderUtil {

    //图片默认加载类型 以后有可能有多种类型
    public static final int PIC_DEFAULT_TYPE = 0;

    //图片默认加载策略 以后有可能有多种图片加载策略
    public static final int LOAD_STRATEGY_DEFAULT = 0;

    private static ImageLoaderUtil mInstance;
    //本应该使用策略模式，用基类声明，但是因为Glide特殊问题
    //持续优化更新
    private BaseImageLoaderStrategy mStrategy;

    public ImageLoaderUtil() {
        mStrategy = new GlideImageLoaderStrategy();
    }

    //单例模式，节省资源
    public static ImageLoaderUtil getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoaderUtil.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoaderUtil();
                    return mInstance;
                }
            }
        }
        return mInstance;
    }

    /**
     * 加载占位图
     *
     * @param url
     * @param placeholder
     * @param imageView
     */
    public void loadImage(String url, int placeholder, ImageView imageView) {
        mStrategy.loadImage(url, placeholder, imageView);
    }

    /**
     * 圆角图片
     *
     * @param url
     * @param placeholder
     * @param imageView
     * @param corner      px
     */
    public void loadImageCorner(String url, int placeholder, ImageView imageView, int corner) {
        mStrategy.loadImage(url, placeholder, imageView, corner);
    }

    /**
     * 加载gif
     *
     * @param url
     * @param imageView
     */
    public void loadGifImage(String url, ImageView imageView) {
        mStrategy.loadGifImage(url, imageView);
    }

    /**
     * 圆角gif
     *
     * @param url
     * @param imageView
     * @param corner
     */
    public void loadGifImage(String url, ImageView imageView, int corner) {
        mStrategy.loadGifImage(url, imageView, corner);
    }

    /**
     * 圆角gif
     *
     * @param url
     * @param imageView
     */
    public void loadGifImage(String url, int placeholder, ImageView imageView) {
        mStrategy.loadGifImage(url, placeholder, imageView);
    }


    public void loadCircleImage(String url, int placeholder, ImageView imageView) {
        mStrategy.loadCircleImage(url, placeholder, imageView);
    }
    public void loadCircleImage(String url, ImageView imageView) {
        mStrategy.loadCircleImage(url, imageView);
    }


    /**
     * @param url
     * @param placeholder
     * @param imageView
     * @param borderWidth
     * @param borderColor
     */
    public void loadCircleBorderImage(String url, int placeholder, ImageView imageView, float borderWidth, int borderColor) {
        mStrategy.loadCircleBorderImage(url, placeholder, imageView, borderWidth, borderColor);
    }

    /**
     * 自定义设置圆环 宽度 色值 图片大小 占位图
     *
     * @param url
     * @param placeholder
     * @param imageView
     * @param borderWidth
     * @param borderColor
     * @param heightPX
     * @param widthPX
     */
    public void loadCircleBorderImage(String url, int placeholder, ImageView imageView, float borderWidth, int borderColor, int heightPX, int widthPX) {
        mStrategy.loadCircleBorderImage(url, placeholder, imageView, borderWidth, borderColor, heightPX, widthPX);
    }

    /**
     * 普通加载图片
     *
     * @param url
     * @param imageView
     */
    public void loadImage(String url, ImageView imageView) {
        mStrategy.loadImage(url, imageView);
    }

    /**
     * 全局变量加载
     *
     * @param url
     * @param imageView
     */
    public void loadImageWithAppCxt(String url, ImageView imageView) {
        mStrategy.loadImageWithAppCxt(url, imageView);
    }

    /**
     * 加载结果监听
     *
     * @param url
     * @param imageView
     * @param listener
     */
    public void loadImageWithProgress(String url, ImageView imageView, ProgressLoadListener listener) {
        mStrategy.loadImageWithProgress(url, imageView, listener);
    }

    public void loadGifWithPrepareCall(String url, ImageView imageView, SourceReadyListener listener) {
        mStrategy.loadGifWithPrepareCall(url, imageView, listener);
    }

    public void loadImageWithPrepareCall(String url, ImageView imageView, int placeholder, SourceReadyListener listener) {
        mStrategy.loadImageWithPrepareCall(url, imageView, placeholder, listener);
    }

    /**
     * 策略模式的注入操作
     *
     * @param strategy
     */
    public void setLoadImgStrategy(BaseImageLoaderStrategy strategy) {
        mStrategy = strategy;
    }


    /**
     * 清除图片磁盘缓存
     */
    public void clearImageDiskCache(final Context context) {
        mStrategy.clearImageDiskCache(context);
    }

    /**
     * 清除图片内存缓存
     */
    public void clearImageMemoryCache(Context context) {
        mStrategy.clearImageMemoryCache(context);
    }

    /**
     * 根据不同的内存状态，来响应不同的内存释放策略
     *
     * @param context
     * @param level
     */
    public void trimMemory(Context context, int level) {
        mStrategy.trimMemory(context, level);
    }

    /**
     * 清除图片所有缓存
     */
    public void clearImageAllCache(Context context) {
        clearImageDiskCache(context.getApplicationContext());
        clearImageMemoryCache(context.getApplicationContext());
    }

    /**
     * 获取缓存大小
     *
     * @return CacheSize
     */
    public String getCacheSize(Context context) {
        return mStrategy.getCacheSize(context);
    }

    public void saveImage(Context context, String url, String savePath, String saveFileName, ImageSaveListener listener) {
        mStrategy.saveImage(context, url, savePath, saveFileName, listener);
    }

    public void getImageBitmap(Context context, String url, ImageBitmapLoaderListener listener) {
        mStrategy.getImageBitmap(context, url, listener);
    }

    public void getImageDrawable(Context context, String url, ImageDrawableLoaderListener listener) {
        mStrategy.getImageDrawable(context, url, listener);
    }

    public String getPath(Context context, String url) {
      return   mStrategy.getImagePath(url, context);
    }
}
