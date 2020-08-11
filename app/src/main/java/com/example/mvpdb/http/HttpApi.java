package com.example.mvpdb.http;

import com.example.mvpdb.base.BaseModel;
import com.example.mvpdb.ui.ChannelBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HttpApi {

    //渠道列表
    @POST("channel/getList")
    Call<BaseModel<Object>> getChannelList(@Body RequestBody body);

    //渠道列表
    @POST("channel/getList")
    Observable<BaseModel<List<ChannelBean>>> getChannel(@Body RequestBody body);

}
