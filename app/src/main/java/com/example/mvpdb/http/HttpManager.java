package com.example.mvpdb.http;

import com.blankj.utilcode.util.GsonUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

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

    private static <T> void bindObserver(Observable<T> observable, Observer<T> observer) {
        if (observable != null && observer != null)
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
    }

    public static void getChannelList(Observer observer) {
        bindObserver(HttpConfig.getApi().getChannel(getRequestBody(getBaseMap())), observer);
    }
}
