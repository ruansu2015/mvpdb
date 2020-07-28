package com.example.mvpdb.http;

import com.example.mvpdb.base.BaseModel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HttpApi {

    //不带参数get请求
//    @GET("users")
//    Call getUsers();

    //动态路径get请求
//    @GET("users/{groupId}")
//    Call getUsers(@Path("userId") String userId);

//    @GET("users/{groupId}")
//    Call getUsers(@Path("userId") String userId, @Query("age") int age);

    //直接把对象通过ConverterFactory转化成对应的参数
//    @POST("add")
//    Call<List<User>> addUser(@Body BaseModel user);

    //读参数进行urlEncoded
//    @POST("login")
//    @FormUrlEncoded
//    Call<User> login(@Field("userId") String username, @Field("password") String password);

    //读参数进行urlEncoded
//    @POST("login")
//    @FormUrlEncoded
//    Call<User> login(@FieldMap HashMap<String, String> paramsMap);

//    @Multipart
//    @POST("login")
//    Call<User> login(@Part("userId") String userId, @Part("password") String password);

//    @Headers("Cache-Control: max-age=640000")
//    @GET("users")//不带参数get请求
//    Call<List<User>> getUsers();

    //渠道列表
    @POST("channel/getList")
    Call<BaseModel<Object>> getChannelList(@Body RequestBody body);
}
