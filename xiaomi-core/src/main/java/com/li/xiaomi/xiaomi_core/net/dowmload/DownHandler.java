package com.li.xiaomi.xiaomi_core.net.dowmload;

import android.content.Context;
import android.os.AsyncTask;


import com.li.xiaomi.xiaomi_core.net.RestCreator;
import com.li.xiaomi.xiaomi_core.net.callback.IError;
import com.li.xiaomi.xiaomi_core.net.callback.IFailure;
import com.li.xiaomi.xiaomi_core.net.callback.IRequest;
import com.li.xiaomi.xiaomi_core.net.callback.ISuccess;
import com.li.xiaomi.xiaomi_core.ui.LatteLoader;
import com.li.xiaomi.xiaomi_core.ui.LoaderStyle;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/22
 * 内容：处理文件的下载
 * 最后修改：
 */

public class DownHandler {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final String DOWNLOADFILENAME;//下载文件的名称（带有后缀名的）
    private final String DOWNLOADFILEPATH;//下载路径
    private final String DOWNLOADFILEEXTENSIONNAME;//后缀名

    private final IRequest IREQUEST;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;
    private final LoaderStyle LOADERSTYLE;
    private final Context CONTEXT;

    public DownHandler(String url,
                       String downloadFileName,
                       String downloadFilePath,
                       String downloadFileExtensionName,
                       LoaderStyle loaderstyle,
                       Context context,
                       IRequest iRequest,
                       ISuccess iSuccess,
                       IFailure iFailure,
                       IError iError) {
        this.URL = url;
        this.DOWNLOADFILENAME = downloadFileName;
        this.DOWNLOADFILEPATH = downloadFilePath;
        this.DOWNLOADFILEEXTENSIONNAME = downloadFileExtensionName;
        this.LOADERSTYLE = loaderstyle;
        this.CONTEXT = context;
        this.IREQUEST = iRequest;
        this.ISUCCESS = iSuccess;
        this.IFAILURE = iFailure;
        this.IERROR = iError;
    }


    public final void handleDownload() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        if (IREQUEST != null) {
            IREQUEST.onRequestStart();
        }

        if (LOADERSTYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADERSTYLE);
        }

        Call<ResponseBody> download = RestCreator.getRestService().download(URL, PARAMS);
        download.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    SaveFileAsyncTask saveFileAsyncTask = new SaveFileAsyncTask(ISUCCESS, IREQUEST);
                    final ResponseBody responseBody = response.body();
                    saveFileAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOADFILENAME, DOWNLOADFILEPATH, DOWNLOADFILEEXTENSIONNAME, responseBody);
                    //这里去检查Task是不是已经关闭了，一定要判断，要不然可能会导致文件下载的不全
                    if (saveFileAsyncTask.isCancelled()) {
                        if (IREQUEST != null) {
                            IREQUEST.onRequestEnd();
                        }
                    }
                } else {
                    if (IERROR != null) {
                        IERROR.onError(response.code(), response.message());
                    }
                }
                RestCreator.getParams().clear();
                RestCreator.getHeads().clear();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (IFAILURE != null) {
                    IFAILURE.onFailure(t);
                }
                RestCreator.getParams().clear();
                RestCreator.getHeads().clear();
                LatteLoader.stopLoading();
            }
        });
    }
}
