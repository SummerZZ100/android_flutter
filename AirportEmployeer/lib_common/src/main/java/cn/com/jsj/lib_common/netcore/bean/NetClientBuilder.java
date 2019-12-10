package cn.com.jsj.lib_common.netcore.bean;

import java.io.File;
import java.util.Map;

import cn.com.jsj.lib_common.netcore.callback.IError;
import cn.com.jsj.lib_common.netcore.callback.IFailure;
import cn.com.jsj.lib_common.netcore.callback.IListener;
import cn.com.jsj.lib_common.netcore.callback.IProgress;
import cn.com.jsj.lib_common.netcore.callback.ISuccess;
import okhttp3.RequestBody;

/**
 * 描述：构建者模式的  网络请求参数实体
 *
 * @author : jsj_android
 * @date : 2018/11/29
 */

public class NetClientBuilder {

    private String baseurl;
    private String url;
    private String tag;
    private HttpMethod httpmethod;
    private Map<String, String> headers;
    private Map<String, Object> params;
    private RequestBody reqbody;
    private ZRequest zrequest;

    private IListener listener;
    private ISuccess success;
    private IFailure failure;
    private IError error;

    //上传  &  下载

    private File file;

    private String downloaddir;
    private String extension;
    private String filename;
    protected IProgress progress;


    private NetClientBuilder() {
    }

    public NetClientBuilder(String baseUrl, String url, String tag) {
        this.baseurl = baseUrl;
        this.url = url;
        this.tag = tag;
    }

    public final NetClientBuilder baseUrl(String val) {
        this.baseurl = val;
        return this;
    }

    public final NetClientBuilder url(String val) {
        this.url = val;
        return this;
    }

    public final NetClientBuilder tag(String val) {
        this.tag = val;
        return this;
    }

    public final NetClientBuilder httpMethod(HttpMethod val) {
        this.httpmethod = val;
        return this;
    }

    public final NetClientBuilder headers(Map<String, String> val) {
        this.headers = val;
        return this;
    }

    public final NetClientBuilder params(Map<String, Object> val) {
        this.params = val;
        return this;
    }

    public final NetClientBuilder reqBody(RequestBody val) {
        this.reqbody = val;
        return this;
    }

    public final NetClientBuilder zRequest(ZRequest val) {
        this.zrequest = val;
        return this;
    }

    public final NetClientBuilder listener(IListener val) {
        this.listener = val;
        return this;
    }

    public final NetClientBuilder success(ISuccess val) {
        this.success = val;
        return this;
    }

    public final NetClientBuilder failure(IFailure val) {
        this.failure = val;
        return this;
    }

    public final NetClientBuilder error(IError val) {
        this.error = val;
        return this;
    }


    /**
     * 上传文件   2 种入参
     *
     * @param val
     * @return
     */
    public final NetClientBuilder file(File val) {
        this.file = val;
        return this;
    }

    public final NetClientBuilder file(String val) {
        this.file = new File(val);
        return this;
    }

    /**
     * 下载文件   存放位置，后缀，文件名
     *
     * @param val
     * @return
     */
    public final NetClientBuilder downloaddir(String val) {
        this.downloaddir = val;
        return this;
    }

    public final NetClientBuilder extension(String val) {
        this.extension = val;
        return this;
    }

    public final NetClientBuilder filename(String val) {
        this.filename = val;
        return this;
    }

    public final NetClientBuilder progress(IProgress val) {
        this.progress = val;
        return this;
    }


    public final NetClient build() {
        return new NetClient(baseurl, url, tag, httpmethod, headers, params, reqbody, zrequest, listener, success, failure, error, file, downloaddir, extension, filename, progress);
    }


}
