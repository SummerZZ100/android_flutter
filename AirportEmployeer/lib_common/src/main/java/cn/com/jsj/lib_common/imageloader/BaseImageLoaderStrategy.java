package cn.com.jsj.lib_common.imageloader;

import android.content.Context;
import android.widget.ImageView;

import cn.com.jsj.lib_common.imageloader.listener.ImageBitmapLoaderListener;
import cn.com.jsj.lib_common.imageloader.listener.ImageDrawableLoaderListener;
import cn.com.jsj.lib_common.imageloader.listener.ImageSaveListener;
import cn.com.jsj.lib_common.imageloader.listener.SourceReadyListener;
import cn.com.jsj.lib_common.imageloader.progress.ProgressLoadListener;


/**
 * 图片加载策略基类
 * 新添加的图片加载方式需要实现该类
 */
public interface BaseImageLoaderStrategy {
    //默认占位图
    void loadImage(String url, ImageView imageView);

    //这里的context指定为ApplicationContext
    void loadImageWithAppCxt(String url, ImageView imageView);

    void loadImage(String url, int placeholder, ImageView imageView);

    //圆角
    void loadImage(String url, int placeholder, ImageView imageView, int corner);

    void loadGifImage(String url, ImageView imageView, int corner);

    void loadCircleImage(String url, int placeholder, ImageView imageView);

    void loadCircleImage(String url, ImageView imageView);

    void loadCircleBorderImage(String url, int placeholder, ImageView imageView, float borderWidth, int borderColor);

    void loadCircleBorderImage(String url, int placeholder, ImageView imageView, float borderWidth, int borderColor, int heightPx, int widthPx);


    void loadGifImage(String url, int placeholder, ImageView imageView);

    void loadGifImage(String url, ImageView imageView);

    void loadImageWithProgress(String url, ImageView imageView, ProgressLoadListener listener);

    void loadImageWithPrepareCall(String url, ImageView imageView, int placeholder, SourceReadyListener listener);

    void loadGifWithPrepareCall(String url, ImageView imageView, SourceReadyListener listener);

    //清除硬盘缓存
    void clearImageDiskCache(final Context context);

    //清除内存缓存
    void clearImageMemoryCache(Context context);

    //根据不同的内存状态，来响应不同的内存释放策略
    void trimMemory(Context context, int level);

    //获取缓存大小
    String getCacheSize(Context context);

     void saveImage(Context context, String url, String savePath, String saveFileName, ImageSaveListener listener);

    //获取图片
    void getImageDrawable(Context context, String url, ImageDrawableLoaderListener listener);

    void getImageBitmap(Context context, String url, ImageBitmapLoaderListener listener);

    String getImagePath(String imgUrl, Context context);
}
