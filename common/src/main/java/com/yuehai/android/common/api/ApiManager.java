package com.yuehai.android.common.api;

import android.text.TextUtils;

import com.yuehai.android.common.base.BaseApplication;
import com.yuehai.android.common.config.AppConfig;
import com.yuehai.android.common.config.Constants;
import com.yuehai.android.common.util.LogUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 是通过BaseComponent和BaseApplication实现全局的单例
 */
public class ApiManager {

    private final Retrofit retrofit;

    ApiManager() {
        File cacheDirectory = new File(BaseApplication.getApplication().getCacheDir(), "cache");
        int cacheSize = 10 * 1024 * 1024;
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (AppConfig.getInstance().isDebug()) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(
                    message -> {
                        if (TextUtils.isEmpty(message)) return;
                        LogUtils.e("收到响应: " + message);
                    });
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
        //OKHttp默认三个超时时间是10s
        OkHttpClient okHttpClient = builder
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new HeaderInterceptor())
                .cache(new Cache(cacheDirectory, cacheSize)).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public <T> T getApiService(Class<T> apiServer) {
        return retrofit.create(apiServer);
    }

}
