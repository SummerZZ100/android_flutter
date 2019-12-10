package cn.com.jsj.lib_common.netcore.download;

import java.io.IOException;

import cn.com.jsj.lib_common.netcore.callback.IProgress;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 描述：网络拦截器，用于监听进度
 *
 * @author : jsj_android
 * @date : 2018/12/4
 */

public class ProgressInterceptor implements Interceptor {

    private IProgress listener;

    public ProgressInterceptor(IProgress listener) {
        this.listener = listener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder()
                .body(new ProgressResponseBody(originalResponse.body(), listener))
                .build();
    }

}
