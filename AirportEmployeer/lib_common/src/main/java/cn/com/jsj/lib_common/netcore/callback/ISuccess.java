package cn.com.jsj.lib_common.netcore.callback;

/**
 * 描述：公共的SUCCESS接口
 *
 * @author : jsj_android
 * @date : 2018/11/29
 */
public interface ISuccess<T> {

    void onSuccess(T result);

}
