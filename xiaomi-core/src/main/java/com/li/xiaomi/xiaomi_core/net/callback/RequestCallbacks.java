package com.li.xiaomi.xiaomi_core.net.callback;



import com.li.xiaomi.xiaomi_core.net.RestCreator;
import com.li.xiaomi.xiaomi_core.ui.LatteLoader;
import com.li.xiaomi.xiaomi_core.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/20
 * 内容：自定义回调
 * 最后修改：
 */

public class RequestCallbacks implements Callback<String> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADERSTYLE;

    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error, LoaderStyle loaderstyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADERSTYLE = loaderstyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        finish();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure(t);
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        finish();
    }


    private void finish() {
        //请求结束之后,停止loading\清空heads\清空参数
        LatteLoader.stopLoading();
        RestCreator.getHeads().clear();
        RestCreator.getParams().clear();
    }
}
