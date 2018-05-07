package com.yuehai.android.main.dagger;

import com.yuehai.android.main.api.MainApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainApiModule {

    @Provides
    protected MainApi provideMainApi(Retrofit build) {
        return MainApi.getInstance(build);
    }
}
