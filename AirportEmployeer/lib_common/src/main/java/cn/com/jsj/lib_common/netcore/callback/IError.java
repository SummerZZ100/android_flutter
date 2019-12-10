package cn.com.jsj.lib_common.netcore.callback;

/**
 * 描述：公共的ERROR接口
 *
 * @author : jsj_android
 * @date : 2018/11/29
 */

public interface IError {

    void onError(int code, String errorMsg);

}
