package cn.com.jsj.lib_common.netcore.retrofit;

import android.text.TextUtils;

import java.util.concurrent.TimeUnit;

import cn.com.jsj.lib_common.netcore.callback.IProgress;
import cn.com.jsj.lib_common.netcore.download.ProgressInterceptor;
import cn.com.jsj.lib_common.netcore.util.ProtoConverterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  描述：创建retrofit的类
 *
 * @author : jsj_android
 * @date : 2018/11/30
 */

public class RetrofitCreator {

    private static class RetrofitHolder{
        private static String mBaseUrl;
        private static int times = 60;
        private static Retrofit INSTANCE ;
        private static Retrofit getRetrofit(String baseUrl, IProgress progress){

            if(TextUtils.equals(baseUrl,mBaseUrl) && INSTANCE!=null){
                return INSTANCE;
            }else{
                OkHttpClient client = new OkHttpClient.Builder().connectTimeout(times, TimeUnit.SECONDS).build();
                if(progress!=null){
                    //TODO 目前下载进度拦截器未发挥作用，待调整，已使用异步监听下载进度
                    ProgressInterceptor pi = new ProgressInterceptor(progress);
                    client = new OkHttpClient.Builder().addInterceptor(pi).connectTimeout(times, TimeUnit.SECONDS).build();
                }

                INSTANCE = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .client(client)
                        // 添加Rx适配器
                        // .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        //设置数据解析器  //一定要在gsonconvert的前面  【！！！必须引用本地的ProtoConverterFactory，否则构建失败！！！】
                        .addConverterFactory(ProtoConverterFactory.create())
                        //设置Json数据的转换器为Gson
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                mBaseUrl = baseUrl;
                return INSTANCE;
            }
        }
    }


    public static ApiService getService(String baseUrl){
        return RetrofitHolder.getRetrofit(baseUrl, null).create(ApiService.class);
    }

    public static ApiService getService(String baseUrl, IProgress progress){
        return RetrofitHolder.getRetrofit(baseUrl,progress).create(ApiService.class);
    }

}
