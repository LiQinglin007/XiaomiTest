package com.li.xiaomi.xiaomi_core.net;

import com.li.xiaomi.xiaomi_core.app.ConfigType;
import com.li.xiaomi.xiaomi_core.app.Latte;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/20
 * 内容：做一些单例的实例化操作
 * 初始化Retrofit和RestService，并且设置请求
 * 最后修改：
 */

public class RestCreator {

//        /**
//         * 最原始的写法
//         */
//        Retrofit mRetrofit = new Retrofit.Builder()
//                .baseUrl(RetrofitSendRequest.HanzhiDellBaseUrl)
//                //增加返回值为String的支持  需要加下边这个包才能用
////              compile 'com.squareup.retrofit2:converter-scalars:2.3.0'
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .build();
//
//        RetrofitApi mTestApi = mRetrofit.create(RetrofitApi.class);
//
//        Call<String> call = mTestApi.getShopDataList(1);
//
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Log.e("===", "return:" + response.body().toString());
//                LogUtils.Loge(response.body().toString() + "onResponse");
//                resulttv.setText("get请求结果onResponse：" + response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                LogUtils.Loge(t.toString() + "onFailure");
//                resulttv.setText("get请求结果onFailure：" + t.toString());
//            }
//        });

    /**
     * 参数容器
     */
    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }


    /**
     * 请求头容器
     */
    private static final class HeadsHolder {
        private static final WeakHashMap<String, String> HEADS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, String> getHeads() {
        return HeadsHolder.HEADS;
    }


    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.Retrofit_CLIENT.create(RestService.class);
    }

    private static final class RetrofitHolder {
        private static final String BASE_URL = Latte.getConfiguration(ConfigType.HOST_API);
        private static final Retrofit Retrofit_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OkHttp_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OkHttpHolder {
        private final static OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = Latte.getConfiguration(ConfigType.INTERCEPTOR);

        private final static long CONNECT_TIME_OUT = 20;
        private final static long WRITE_TIME_OUT = 20;
        private final static long READ_TIME_OUT = 20;

        //给okhttp添加拦截器
        private static final OkHttpClient.Builder addInterceptors() {
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    mBuilder.addInterceptor(interceptor);
                }
            }
            return mBuilder;
        }

        private static final OkHttpClient OkHttp_CLIENT = addInterceptors()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)//链接超时
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)//读取超时
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)//写入超时
                .sslSocketFactory(createSSLSocketFactory())//设置忽略证书 兼容https
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .build();
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }


    public static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

}
