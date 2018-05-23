package com.yuehai.android.common.api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

// 为了保证ApiManager实现真正的全局单例
//（因为使用@Singleton注解或者定制的@Scope只是局部范围单例，在同一个Activity/Fragment的生命周期中保持单例）
@Module
public class BaseModule {

    @Singleton
    @Provides
    protected ApiManager provideApiManager() {
        return new ApiManager();
    }
}
