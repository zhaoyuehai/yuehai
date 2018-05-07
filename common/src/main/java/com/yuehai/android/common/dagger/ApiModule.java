package com.yuehai.android.common.dagger;

import com.yuehai.android.common.api.HeaderInterceptor;
import com.yuehai.android.common.base.BaseApplication;
import com.yuehai.android.common.config.Constant;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    protected Retrofit provideRetrofit() {
        File cacheDirectory = new File(BaseApplication.getApplication().getCacheDir(), "cache");
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new HeaderInterceptor())
                .cache(new Cache(cacheDirectory, cacheSize))
                .build();
        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
