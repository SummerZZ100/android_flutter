package cn.com.jsj.lib_common.imageloader;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cn.com.jsj.lib_common.imageloader.listener.ImageBitmapLoaderListener;
import cn.com.jsj.lib_common.imageloader.listener.ImageDrawableLoaderListener;
import cn.com.jsj.lib_common.imageloader.listener.ImageSaveListener;
import cn.com.jsj.lib_common.imageloader.listener.SourceReadyListener;
import cn.com.jsj.lib_common.imageloader.progress.ProgressLoadListener;
import cn.com.jsj.lib_common.imageloader.transformation.GlideCircleTransform;


/**
 * glide加载策略
 */
public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy {

    /**
     * 需要完整参数的普通图片加载
     *
     * @param url
     * @param placeholder 传0不占位
     * @param imageView
     */
    @Override
    public void loadImage(String url, int placeholder, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(getDrawable(placeholder, imageView));

        Glide.with(imageView.getContext())
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }

    /**
     * 带圆角的
     *
     * @param url
     * @param placeholder 传0 不占位
     * @param imageView
     * @param corner
     */
    @Override
    public void loadImage(String url, int placeholder, ImageView imageView, int corner) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(getDrawable(placeholder, imageView))
                .transform(new RoundedCorners(corner));
        Glide.with(imageView.getContext())
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }

    /**
     * xml设置了src,会以设置的图片做占位图，没有则不占位， 且不需要context
     *
     * @param url
     * @param imageView
     */
    @Override
    public void loadImage(String url, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(imageView.getDrawable());
        Glide.with(imageView.getContext()).load(url)
                .apply(requestOptions)
                .into(imageView);
    }

    /**
     * @param url
     * @param placeholder
     * @param imageView
     */
    @Override
    public void loadCircleImage(String url, int placeholder, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(getDrawable(placeholder, imageView))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .transform(new GlideCircleTransform(imageView.getContext()))
                .dontAnimate();
        Glide.with(imageView.getContext())
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }

    @Override
    public void loadCircleImage(String url, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(imageView.getDrawable())
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .transform(new GlideCircleTransform(imageView.getContext()))
                .dontAnimate();
        Glide.with(imageView.getContext())
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }

    /**
     * 设置圆环的宽度和色彩
     *
     * @param url
     * @param placeholder
     * @param imageView
     * @param borderWidth
     * @param borderColor
     */
    @Override
    public void loadCircleBorderImage(String url, int placeholder, ImageView imageView, float borderWidth, int borderColor) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(getDrawable(placeholder, imageView))
                .transform(new GlideCircleTransform(imageView.getContext(), borderWidth, borderColor))
                .dontAnimate();
        Glide.with(imageView.getContext())
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }

    /**
     * 设置宽和高
     *
     * @param url
     * @param placeholder
     * @param imageView
     * @param borderWidth
     * @param borderColor
     * @param heightPx
     * @param widthPx
     */
    @Override
    public void loadCircleBorderImage(String url, int placeholder, ImageView imageView, float borderWidth, int borderColor, int heightPx, int widthPx) {

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(getDrawable(placeholder, imageView))
                .transform(new GlideCircleTransform(imageView.getContext(), borderWidth, borderColor, heightPx, widthPx))
                .dontAnimate();
        Glide.with(imageView.getContext())
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }

    /**
     * 圆角gif图片
     *
     * @param url
     * @param imageView
     * @param corner
     */
    @Override
    public void loadGifImage(String url, ImageView imageView, int corner) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(imageView.getDrawable())
                .transform(new RoundedCorners(corner));
        Glide.with(imageView.getContext())
                .asGif()
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }

    @Override
    public void loadGifImage(String url, int placeholder, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(getDrawable(placeholder, imageView));
        Glide.with(imageView.getContext())
                .asGif()
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }

    /**
     * 以全局的application 加载图片
     *
     * @param url
     * @param imageView
     */
    @Override
    public void loadImageWithAppCxt(String url, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(imageView.getDrawable());
        Glide.with(imageView.getContext().getApplicationContext())
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }


    /**
     * 不需要
     *
     * @param url
     * @param imageView
     */
    @Override
    public void loadGifImage(String url, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions()
                //资源不能为空
                .placeholder(imageView.getDrawable());
        Glide.with(imageView.getContext())
                .asGif()
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }


    /**
     * 监控普通图片加载状态
     *
     * @param url
     * @param imageView
     * @param listener
     */
    @Override
    public void loadImageWithProgress(String url, final ImageView imageView, final ProgressLoadListener listener) {
        RequestBuilder requestBuilder = Glide.with(imageView.getContext()).asDrawable();
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestBuilder.apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        listener.onException();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        listener.onResourceReady();
                        return false;
                    }
                }).load(url).into(imageView);
    }

    /**
     * @param url
     * @param imageView
     * @param listener
     */
    @Override
    public void loadGifWithPrepareCall(String url, ImageView imageView, final SourceReadyListener listener) {
        RequestBuilder<GifDrawable> requestBuilder = Glide.with(imageView.getContext()).asGif();
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestBuilder.apply(requestOptions)
                .listener(new RequestListener<GifDrawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (resource instanceof Animatable) {
                            resource.start();
                        }
                        listener.onResourceReady(resource.getIntrinsicWidth(), resource.getIntrinsicHeight());
                        return false;
                    }
                }).load(url).into(imageView);
    }

    /**
     * 获取加载图片原始尺寸
     *
     * @param url
     * @param imageView
     * @param placeholder
     * @param listener
     */
    @Override
    public void loadImageWithPrepareCall(String url, ImageView imageView, int placeholder, final SourceReadyListener listener) {
        RequestBuilder requestBuilder = Glide.with(imageView.getContext()).asDrawable();
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.placeholder(getDrawable(placeholder, imageView));
        requestBuilder.apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                        listener.onResourceReady(resource.getIntrinsicWidth(), resource.getIntrinsicHeight());
                        return false;
                    }
                }).load(url).into(imageView);
    }

    @Override
    public void clearImageDiskCache(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(context.getApplicationContext()).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(context.getApplicationContext()).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearImageMemoryCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(context.getApplicationContext()).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void trimMemory(Context context, int level) {
        Glide.get(context).trimMemory(level);
    }

    @Override
    public String getCacheSize(Context context) {
        try {
            return CommonUtils.getFormatSize(CommonUtils.getFolderSize(Glide.getPhotoCacheDir(context.getApplicationContext())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 需要在子线程获取
     *
     * @param context
     * @param url
     * @param savePath
     * @param saveFileName
     * @param listener
     */
    @Override
    public void saveImage(Context context, String url, String savePath, String saveFileName, ImageSaveListener listener) {
        if (!CommonUtils.isSDCardExsit() || TextUtils.isEmpty(url)) {
            listener.onSaveFail();
            return;
        }
        InputStream fromStream = null;
        OutputStream toStream = null;
        try {
            File cacheFile = Glide.with(context).load(url).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
            if (cacheFile == null || !cacheFile.exists()) {
                listener.onSaveFail();
                return;
            }
            File dir = new File(savePath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(dir, saveFileName + CommonUtils.getPicType(cacheFile.getAbsolutePath()));
            fromStream = new FileInputStream(cacheFile);
            toStream = new FileOutputStream(file);
            byte length[] = new byte[1024];
            int count;
            while ((count = fromStream.read(length)) > 0) {
                toStream.write(length, 0, count);
            }
            //用广播通知相册进行更新相册
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            context.sendBroadcast(intent);
            listener.onSaveSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            listener.onSaveFail();
        } finally {
            if (fromStream != null && toStream != null) {
                try {
                    fromStream.close();
                    toStream.close();
                    toStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                    fromStream = null;
                    toStream = null;
                }
            }
        }
    }


    @Override
    public void getImageDrawable(Context context, String url, final ImageDrawableLoaderListener listener) {

        SimpleTarget<Drawable> simpleTarget = Glide.with(context).asDrawable().load(url).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                if (listener != null) {
                    listener.getDrawable(resource);
                }
            }
        });

    }

    @Override
    public void getImageBitmap(Context context, String url, final ImageBitmapLoaderListener listener) {

        SimpleTarget<Bitmap> simpleTarget = Glide.with(context).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                if (listener != null) {
                    listener.getBitmap(resource);
                }
            }

        });

// Do something with the Bitmap and then when you're done with it:
        //    Glide.with(context).clear(simpleTarget);
    }


    Drawable getDrawable(int placeholder, ImageView imageView) {
        Drawable drawable;
        if (placeholder != 0) {
            try {
                drawable = imageView.getContext().getResources().getDrawable(placeholder);
            } catch (Resources.NotFoundException e) {
                drawable = imageView.getDrawable();
                e.printStackTrace();
            }
        } else {
            //可以为空
            drawable = imageView.getDrawable();
        }
        return drawable;
    }

    /**
     * 获取缓存地址
     *
     * @param imgUrl
     * @param context
     * @return
     */
    @Override
    public String getImagePath(String imgUrl, Context context) {
        String path = null;
        FutureTarget<File> future = Glide.with(context)
                .load(imgUrl)
                .downloadOnly(500, 500);
        try {
            File cacheFile = future.get();
            path = cacheFile.getAbsolutePath();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return path;
    }

}
