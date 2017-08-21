package com.sugon.sugonlive.net;

import android.content.Context;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sugon.sugonlive.BuildConfig;
import com.sugon.sugonlive.net.progress.ProgressInterceptor;
import com.sugon.sugonlive.net.progress.ProgressListener;
import com.sugon.sugonlive.net.service.LiveService;
import com.sugon.sugonlive.util.Utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by duke on 2016/12/6.
 */

public class NetClient {

    static String PROTOCOL = "http://";
    static String HTTP_IP = "17.0.200.111";
    static String HTTP_PORT = "8080";
    static {
        if (BuildConfig.DEBUG) {
            HTTP_IP = "10.0.110.110";//本机ip
            HTTP_PORT = "8088";
        }
    }
    public static String pushURL = "rtmp://" + HTTP_IP + "/live/room";
    static String baseURL = PROTOCOL + HTTP_IP + ":" + HTTP_PORT + "/sugonvideo/live/";

    public final static String getVersionUrl = PROTOCOL + HTTP_IP + ":" + HTTP_PORT + "/update/version.json";

    static Retrofit mGsonRetrofit = null;
    static LiveService mLiveService = null;

    public static Retrofit getRetrofit() {
        if (mGsonRetrofit == null) {
            Gson gson = new GsonBuilder()
                    //配置你的Gson
                    .setDateFormat("yyyy-MM-dd hh:mm:ss")
                    .create();
            mGsonRetrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return mGsonRetrofit;
    }

    public static LiveService getService(Context context) {
        if (!Utils.isNetworkAvailable(context)) {
            Utils.showToast(context, "网络不可用！");
        } else {
            if (mLiveService == null) {
                mLiveService = getRetrofit().create(LiveService.class);
            }
        }
        return mLiveService;
    }

    /**
     * 重写Retrofit client，实现上传/下载时进度条监听
     *
     * @param context
     * @param listener
     * @return
     */
    public static LiveService getService(Context context, ProgressListener listener) {
        if (!Utils.isNetworkAvailable(context)) {
            Utils.showToast(context, "网络不可用！");
            return null;
        }
        OkHttpClient httpClient = new OkHttpClient().newBuilder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new ProgressInterceptor(listener)).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(LiveService.class);
    }
}
