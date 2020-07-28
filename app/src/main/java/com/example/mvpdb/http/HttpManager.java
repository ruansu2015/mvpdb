package com.example.mvpdb.http;

import com.blankj.utilcode.util.GsonUtils;
import com.example.mvpdb.base.BaseModel;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Callback;

/**
 * Created by Ruansu
 * on 2020/7/27 5:43 PM
 */
public class HttpManager {

    private static RequestBody getRequestBody(Object object) {
        return RequestBody.create(GsonUtils.toJson(object), HttpConfig.JSON);
    }

    private static Map<String, Object> getBaseMap() {
        Map<String, Object> params = new HashMap<>();
        params.put(HttpParams.CHANNEL_CODE, "");
        return params;
    }

    public static void getChannelList(Callback<BaseModel<Object>> callback) {
        HttpConfig.getApi().getChannelList(getRequestBody(getBaseMap())).enqueue(callback);
    }

}
