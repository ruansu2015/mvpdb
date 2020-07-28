package com.example.mvpdb.http;

import android.text.TextUtils;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.SPUtils;
import com.example.mvpdb.Constant;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpConfig {

    private static final int TIME_OUT_SECONDS = 15;
    private static final long CACHE_SIZE = 10 * 1024 * 1024; // 10MB
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private static Retrofit retrofit;
    private static HttpApi httpApi;

    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .callTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .cache(new Cache(new File(PathUtils.getInternalAppCachePath()), CACHE_SIZE))
                .addInterceptor(new RequestInterceptor())
                .retryOnConnectionFailure(true).build();
    }

    public static OkHttpClient getDownloadOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .callTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .retryOnConnectionFailure(true).build();
    }

    private static Retrofit getRetrofit() {
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.Http.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        return retrofit;
    }

    public static HttpApi getApi() {
        if (null == httpApi)
            httpApi = getRetrofit().create(HttpApi.class);
        return httpApi;
    }

    public static class RequestInterceptor implements Interceptor {

        @NotNull
        @Override
        public Response intercept(@NotNull Chain chain) throws IOException {
            Request request = addRequestHeader(chain.request());
            String requestBody = getRequestBody(request);
            Response response = chain.proceed(request);
            String responseBody = getResponseBody(response);
            if (TextUtils.isEmpty(responseBody))
                return response;
            else {
                LogUtils.iTag(HttpParams.HTTP_TAG, request.method(),
                        request.url(), request.headers(), requestBody, responseBody);
                return response.newBuilder()
                        .body(ResponseBody.create(responseBody, HttpConfig.JSON))
                        .build();
            }
        }

        private String getRequestBody(Request request) throws IOException {
            RequestBody requestBody = request.body();
            if (requestBody != null) {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                return buffer.readString(Charset.defaultCharset());
            }
            return null;
        }

        private String getResponseBody(Response response) throws IOException {
            ResponseBody responseBody = response.body();
            if (responseBody != null)
                return responseBody.string();
            return null;
        }

        private Request addRequestHeader(Request request) {
            Request.Builder builder = request.newBuilder()
                    .addHeader(HttpParams.CONTENT_TYPE, HttpParams.APPLICATION_JSON)
                    .addHeader(HttpParams.ACCEPT, HttpParams.APPLICATION_JSON)
                    .addHeader(HttpParams.PLATFORM, String.format("%s %s",
                            DeviceUtils.getManufacturer(), DeviceUtils.getModel()));
            String token = SPUtils.getInstance().getString(HttpParams.APP_TOKEN);
            if (!TextUtils.isEmpty(token))
                builder.addHeader(HttpParams.APP_TOKEN, token);
            return builder.build();
        }

    }

}
