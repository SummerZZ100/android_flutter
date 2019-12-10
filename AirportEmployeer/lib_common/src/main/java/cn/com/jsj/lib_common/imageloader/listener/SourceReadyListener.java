package cn.com.jsj.lib_common.imageloader.listener;

/**
 * 通知准备就绪
 */
public interface SourceReadyListener {

    void onResourceReady(int width, int height);
}
