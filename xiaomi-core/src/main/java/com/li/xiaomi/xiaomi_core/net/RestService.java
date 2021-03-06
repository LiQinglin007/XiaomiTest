package com.li.xiaomi.xiaomi_core.net;

import java.util.WeakHashMap;

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
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/20
 * 内容：请求api接口
 * 最后修改：
 */
public interface RestService {

    /**
     * 说一下post请求和get请求的方法
     * 先说两个请求的方式，get方式是为了请求数据，post方式是为了给后台提交数据
     * 所以在get请求的时候，传递参数的时候使用@Query()关键字，
     * 而在post请求的时候应该使用@Field()关键字，这里需要注意，在post的请求使用@Field()关键字的时候，一定要在@POST下边加上 @FormUrlEncoded
     * <p>
     * <p>
     * 现在，可能有人会说，那就是还有使用post请求的时候还有可能不适用@Field()关键字呗
     * 是的，在post请求中，也可以使用@Query()关键字，这样也能请求到数据，这时候用不需要再@POST下边加上 @FormUrlEncoded
     * 也就是说 @FormUrlEncoded  和@Field()或者@FieldMap() 是同时出现的
     * <p>
     * 现在发现了一种情况啊，在post请求中，如果请求的url没有入参，这时候就不用@FormUrlEncoded
     */

    @GET
    Call<String> get(@Url String url, @QueryMap WeakHashMap<String, Object> params);

    @GET
    Call<String> get(@HeaderMap WeakHashMap<String, String> headers, @Url String url, @QueryMap WeakHashMap<String, Object> params);

    /**
     *  FormUrlEncoded注解:
     *
     * 用于修饰Field注解和FieldMap注解
     * 使用该注解,表示请求正文将使用表单网址编码。字段应该声明为参数，
     * 并用@Field注释或FieldMap注释。使用FormUrlEncoded注解的请求将具”application / x-www-form-urlencoded”
     * MIME类型。字段名称和值将先进行UTF-8进行编码,再根据RFC-3986进行URI编码.
     */


    /**
     * @FieldMap 作用于方法的参数
     * 用于发送一个表单请求
     * map中每一项的键和值都不能为空,否则抛出IllegalArgumentException异常
     * @FormUrlEncoded
     * @POST("/things") Call<ResponseBody> things(@FieldMap Map<String, String> fields);
     */
    @POST
    @FormUrlEncoded
    Call<String> post(@Url String url, @FieldMap WeakHashMap<String, Object> params);

    /**
     * 含有请求头的post请求
     *
     * @param headers 请求头
     * @param url     请求地址
     * @param params  参数
     * @return
     */
    @POST
    @FormUrlEncoded
    Call<String> post(@HeaderMap WeakHashMap<String, String> headers, @Url String url, @FieldMap WeakHashMap<String, Object> params);

    @POST
    Call<String> postBody(@Url String url, @Body RequestBody requestBody);

    @POST
    Call<String> postBody(@HeaderMap WeakHashMap<String, String> headers, @Url String url, @Body RequestBody requestBody);




    @POST
    @FormUrlEncoded
    Call<String> put(@Url String url, @FieldMap WeakHashMap<String, Object> params);

    @POST
    @FormUrlEncoded
    Call<String> put(@HeaderMap WeakHashMap<String, String> headers, @Url String url, @FieldMap WeakHashMap<String, Object> params);

    @POST
    Call<String> putBody(@Url String url, @Body RequestBody requestBody);

    @POST
    Call<String> putBody(@HeaderMap WeakHashMap<String, String> headers, @Url String url, @Body RequestBody requestBody);


    @DELETE
    Call<String> delete(@Url String url, @QueryMap WeakHashMap<String, Object> params);

    @DELETE
    Call<String> delete(@HeaderMap WeakHashMap<String, String> headers, @Url String url, @QueryMap WeakHashMap<String, Object> params);

    /**
     * @Streaming： 作用于方法
     * 一般用于文件的下载,
     * 这里下载的时候默认的是一次性下载到内存中没然后再写入到文件，
     * 当下载文件过大的时候，就会造成内存溢出，
     * 加上这个标签，就会一边下载一遍写入到文件，这样就会避免内存溢出
     * 这时候要在子线程中操作下载
     */
    @GET
    @Streaming
    Call<ResponseBody> download(@Url String url, @QueryMap WeakHashMap<String, Object> params);

    /**
     * @Part MultipartBody.Part代表文件，@Part("key") RequestBody代表参数
     */
    @POST
    @Multipart
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);

    @POST
    @Multipart
    Call<String> upload(@HeaderMap WeakHashMap<String, String> headers, @Url String url, @Part MultipartBody.Part file);

    /**
     * @Body
     * 作用于方法的参数
     * 使用该注解定义的参数不可为null
     * 当你发送一个post或put请求,但是又不想作为请求参数或表单的方式发送请求时,使用该注解定义的参数可以直接传入一个实体类,retrofit会通过convert把该实体序列化并将序列化后的结果直接作为请求体发送出去.
     *  @POST("/")
     *  Call<ResponseBody> sendNormal(@Body TestBean repo);
     */
//    @POST
//    Call<String> postRaw(@Url String url, @Body RequestBody body);

}
