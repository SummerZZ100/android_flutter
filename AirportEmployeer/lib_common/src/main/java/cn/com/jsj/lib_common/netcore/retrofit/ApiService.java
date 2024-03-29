package cn.com.jsj.lib_common.netcore.retrofit;

import java.util.Map;

import cn.com.jsj.lib_common.netcore.bean.ZRequest;
import cn.com.jsj.lib_common.netcore.bean.ZResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 描述：retrofit的执行网络请求的接口service
 *
 * @author : jsj_android
 * @date : 2018/11/30
 */

public interface ApiService {

    @GET
    Call<ResponseBody> get(@Url String url, @HeaderMap Map<String, String> headers, @QueryMap Map<String, Object> params);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> post(@Url String url, @HeaderMap Map<String, String> headers, @FieldMap Map<String, Object> params);

    @POST
    Call<ResponseBody> postRaw(@Url String url, @HeaderMap Map<String, String> headers, @Body RequestBody body);

    @POST
    Call<ZResponse> postProto(@Url String url, @HeaderMap Map<String, String> headers, @Body ZRequest baseReq);


    @FormUrlEncoded
    @PUT
    Call<ResponseBody> put(@Url String url, @HeaderMap Map<String, String> headers, @FieldMap Map<String, Object> params);

    @DELETE
    Call<ResponseBody> delete(@Url String url, @QueryMap Map<String, Object> params);


    /**
     * 下载是直接到内存,所以需要 @Streaming
     *
     * @param url
     * @param headers 用于断点续传的请求头  range:bytes=300-
     * @return
     */
    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url, @HeaderMap Map<String, String> headers);

    /**
     * 上传文件
     *
     * @param url
     * @param file
     * @return
     */
    @Multipart
    @POST
    Call<ResponseBody> upload(@Url String url, @Part MultipartBody.Part file);


}
