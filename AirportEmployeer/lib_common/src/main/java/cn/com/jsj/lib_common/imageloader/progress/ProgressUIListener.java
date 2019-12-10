package cn.com.jsj.lib_common.imageloader.progress;

/**
 * 通知UI进度
 */
public interface ProgressUIListener {
    void update(int bytesRead, int contentLength);
}
