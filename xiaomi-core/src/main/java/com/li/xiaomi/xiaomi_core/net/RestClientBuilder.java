package com.li.xiaomi.xiaomi_core.net;

import android.content.Context;


import com.li.xiaomi.xiaomi_core.net.callback.IError;
import com.li.xiaomi.xiaomi_core.net.callback.IFailure;
import com.li.xiaomi.xiaomi_core.net.callback.IRequest;
import com.li.xiaomi.xiaomi_core.net.callback.ISuccess;
import com.li.xiaomi.xiaomi_core.ui.LatteLoader;
import com.li.xiaomi.xiaomi_core.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/20
 * 内容：建造者
 * 最后修改：
 */

public class RestClientBuilder {

    private String mUrl;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private static final WeakHashMap<String, String> HEADS = RestCreator.getHeads();
    private static String mDownloadFileName;
    private static String mDownloadFilePath;
    private static String mDownloadFileExtensionName;
    private IRequest mIrequest;
    private ISuccess mIsuccess;
    private IFailure mIfailure;
    private IError mIerror;
    private RequestBody mRequestBody;
    private File mFile;
    private Context mContext;
    private LoaderStyle mLoaderStyle;

    public RestClientBuilder() {
    }


    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder heads(WeakHashMap<String, String> heads) {
        this.HEADS.putAll(heads);
        return this;
    }

    public final RestClientBuilder head(String key, String value) {
        this.HEADS.put(key, value);
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        this.PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder param(String key, Object value) {
        this.PARAMS.put(key, value);
        return this;
    }

    /**
     * 传递json串进来
     *
     * @param raw
     * @return
     */
    public final RestClientBuilder raw(String raw) {
        this.mRequestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mIsuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder request(IRequest iRequest) {
        this.mIrequest = iRequest;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIfailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIerror = iError;
        return this;
    }

    public final RestClientBuilder loadStyle(Context context, LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RestClientBuilder loadStyle(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LatteLoader.DEFAULT_LOADER;
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }


    public final RestClientBuilder file(String filePath) {
        this.mFile = new File(filePath);
        return this;
    }

    public final RestClientBuilder downloadFileExtensionName(String downloadFileExtensionName) {
        this.mDownloadFileExtensionName = downloadFileExtensionName;
        return this;
    }

    public final RestClientBuilder downloadFileName(String downloadFileName) {
        this.mDownloadFileName = downloadFileName;
        return this;
    }

    public final RestClientBuilder downloadFilePath(String downloadFilePath) {
        this.mDownloadFilePath = downloadFilePath;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, HEADS, PARAMS,mDownloadFileName,mDownloadFilePath,mDownloadFileExtensionName, mIrequest, mIsuccess, mIfailure, mIerror,
                mRequestBody, mFile, mContext, mLoaderStyle);
    }
}
