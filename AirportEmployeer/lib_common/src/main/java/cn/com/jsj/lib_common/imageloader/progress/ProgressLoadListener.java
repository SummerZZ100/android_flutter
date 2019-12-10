package cn.com.jsj.lib_common.imageloader.progress;

/**
 * 通知图片加载进度
 *
 */
public interface ProgressLoadListener {

    void onException();

    void onResourceReady();
}
