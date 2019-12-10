package cn.com.jsj.lib_common.netcore;


import cn.com.jsj.lib_common.netcore.bean.NetClient;
import cn.com.jsj.lib_common.netcore.bean.NetClientBuilder;
import cn.com.jsj.lib_common.netcore.callback.IRequest;

/**
 *  描述：网络请求的被调用的壳
 *
 * @author : jsj_android
 * @date : 2018/11/29
 */

public class NetHelper implements IRequest {

    private static NetHelper mHelper;
    private static IRequest mWorker;

    private NetHelper(IRequest iRequest) {
        mWorker = iRequest;
    }

    /**
     * 单例
     */
    private static final class Holder {
        private static final NetHelper init(IRequest iRequest) {
            return new NetHelper(iRequest);
        }
    }

    public static void init(IRequest iRequest) {
        mHelper = Holder.init(iRequest);
    }

    public static NetHelper getInstance() {
        return mHelper;
    }


    /**
     * 仅用于创建参数对象
     *
     * @param baseUrl
     * @param url
     * @param tag
     * @return
     */
    public static NetClientBuilder createBuilder(String baseUrl, String url, String tag) {
        return new NetClientBuilder(baseUrl, url, tag);
    }


    @Override
    public void request(NetClient client) {
        mWorker.request(client);
    }

    @Override
    public void cancel(String tag) {
        mWorker.cancel(tag);
    }
}
