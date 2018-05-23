package com.yuehai.android.main.dagger;

import com.yuehai.android.common.api.ActivityScope;
import com.yuehai.android.common.api.BaseComponent;
import com.yuehai.android.main.ui.CenterFragment;
import com.yuehai.android.main.ui.ChannelFragment;
import com.yuehai.android.main.ui.HomeFragment;

import dagger.Component;

// 为了保证ApiManager实现真正的全局单例
//（因为使用@Singleton注解或者定制的@Scope只是局部范围单例，在同一个Activity/Fragment的生命周期中保持单例）
//
//
//
//1.依赖BaseComponent, 注入ApiManager实例
//2.注入Presenter
//
@ActivityScope
@Component(dependencies = BaseComponent.class)
public interface MainComponent {

    void inject(HomeFragment homeFragment);

    void inject(ChannelFragment channelFragment);

    void inject(CenterFragment centerFragment);

}
