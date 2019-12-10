package cn.com.jsj.lib_common.netcore.callback;

/**
 * 描述：监听下载进度的接口
 *
 * @author : jsj_android
 * @date : 2018/12/4
 */

public interface IProgress {

    /**
     * 进度监听
     *
     * @param progress 当前进度
     * @param loaded   已经下载的length
     */
    void onProgress(int progress, long loaded);


}
