package com.sugon.sugonlive.net.service;

import com.sugon.sugonlive.net.BaseRes;
import com.sugon.sugonlive.net.model.LiveStreamBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by pjc on 2017/8/3.
 */

public interface LiveService {

    @FormUrlEncoded
    @POST("test")
    Call<BaseRes<LiveStreamBean>> test(@Field("userid") Integer userid, @Field("username") String username, @Field("url") String url);

    @Headers({"Content-type:text/plain;charset=UTF-8"})
    @POST("pushInfo")
    Call<BaseRes<LiveStreamBean>> pushInfo(@Body LiveStreamBean liveStreamBean);

    @GET("listLiveRooms")
    Call<BaseRes<LiveStreamBean>> listLiveRooms();

    @GET("getLiveURL")
    Call<BaseRes<LiveStreamBean>> getLiveURL(@Query("streamId") Integer streamId);

    @FormUrlEncoded
    @POST("startWatching")
    Call<BaseRes<String>> startWatching(@Field("userid") Integer userid, @Field("roomid") Integer roomid);
}
