package com.li.xiaomi.xiaomi_core.net;

import android.content.Context;

import com.li.xiaomi.xiaomi_core.net.callback.IError;
import com.li.xiaomi.xiaomi_core.net.callback.IFailure;
import com.li.xiaomi.xiaomi_core.net.callback.IRequest;
import com.li.xiaomi.xiaomi_core.net.callback.ISuccess;
import com.li.xiaomi.xiaomi_core.net.callback.RequestCallbacks;
import com.li.xiaomi.xiaomi_core.net.dowmload.DownHandler;
import com.li.xiaomi.xiaomi_core.ui.LatteLoader;
import com.li.xiaomi.xiaomi_core.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Callback;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/20
 * 内容：宿主类
 * 最后修改：
 */

public class RestClient {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private static final WeakHashMap<String, String> HEADS = RestCreator.getHeads();
    private final String DOWNLOADFILENAME;//下载文件的名称（带有后缀名的）
    private final String DOWNLOADFILEPATH;//下载路径
    private final String DOWNLOADFILEEXTENSIONNAME;//后缀名

    private final IRequest IREQUEST;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;
    private final RequestBody BODY;
    private final File FILE;
    private final Context CONTEXT;
    private final LoaderStyle LOADERSTYLE;


    public RestClient(String url,
                      WeakHashMap<String, String> heads,
                      WeakHashMap<String, Object> params,
                      String downloadFileName,
                      String downloadFilePath,
                      String downloadFileExtensionName,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      File file,
                      Context context,
                      LoaderStyle loaderstyle) {
        this.URL = url;
        this.DOWNLOADFILENAME = downloadFileName;
        this.DOWNLOADFILEPATH = downloadFilePath;
        this.DOWNLOADFILEEXTENSIONNAME = downloadFileExtensionName;
        this.HEADS.putAll(heads);
        this.PARAMS.putAll(params);
        this.IREQUEST = request;
        this.ISUCCESS = success;
        this.IFAILURE = failure;
        this.IERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADERSTYLE = loaderstyle;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) throws Exception {
        final RestService service = RestCreator.getRestService();
        retrofit2.Call<String> call = null;
        if (IREQUEST != null) {
            IREQUEST.onRequestStart();
        }
        if (LOADERSTYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADERSTYLE);
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case HEAD_GET:
                call = service.get(HEADS, URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case HEAD_POST:
                call = service.post(HEADS, URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postBody(URL, BODY);
                break;
            case HEAD_POST_RAW:
                call = service.postBody(HEADS, URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case HEAD_DELETE:
                call = service.delete(HEADS, URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putBody(URL, BODY);
                break;
            case HEAD_PUT_RAW:
                call = service.putBody(HEADS, URL, BODY);
                break;
            case HEAD_PUT:
                call = service.put(HEADS, URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
                break;
            case HEAD_UPLOAD:
                final RequestBody requestBody1 =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body1 =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody1);
                call = service.upload(HEADS, URL, body1);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(IREQUEST, ISUCCESS, IFAILURE, IERROR, LOADERSTYLE);
    }


    public final void get() throws Exception {
        request(HEADS != null ? HttpMethod.HEAD_GET : HttpMethod.GET);
    }

    public final void post() {
        try {
            if (BODY == null) {
                request(HEADS == null ? HttpMethod.POST : HttpMethod.HEAD_POST);
            } else {
                if (!PARAMS.isEmpty()) {
                    throw new RuntimeException("params must be null!");
                }
                request(HEADS == null ? HttpMethod.POST_RAW : HttpMethod.HEAD_POST_RAW);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Loading type is error!");
        }
    }

    public final void put() {
        try {
            if (BODY == null) {
                request(HEADS == null ? HttpMethod.PUT : HttpMethod.HEAD_PUT);
            } else {
                if (!PARAMS.isEmpty()) {
                    throw new RuntimeException("params must be null!");
                }
                request(HEADS == null ? HttpMethod.PUT_RAW : HttpMethod.HEAD_PUT_RAW);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Loading type is error!");
        }
    }

    public final void delete() {
        try {
            request(HEADS != null ? HttpMethod.HEAD_DELETE : HttpMethod.DELETE);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Loading type is error!");
        }
    }


    public final void upload() {
        try {
            request(HEADS != null ? HttpMethod.HEAD_UPLOAD : HttpMethod.UPLOAD);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Loading type is error!");
        }
    }

    public final void download() throws Exception {
        new DownHandler(URL, DOWNLOADFILENAME, DOWNLOADFILEPATH, DOWNLOADFILEEXTENSIONNAME,
                LOADERSTYLE, CONTEXT, IREQUEST, ISUCCESS, IFAILURE, IERROR).handleDownload();
    }

}
