package cn.com.jsj.lib_common.netcore.callback;


import cn.com.jsj.lib_common.netcore.bean.NetClient;

/**
 * 描述：策略模式的调用接口
 *
 * @author : jsj_android
 * @date : 2018/11/29
 */

public interface IRequest {

    void request(NetClient client);

    void cancel(String tag);

}
