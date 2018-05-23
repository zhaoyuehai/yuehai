package com.yuehai.android.common.api;

import javax.inject.Singleton;

import dagger.Component;

/**
 *
 * 为了保证ApiManager实现真正的全局单例
 * （因为使用@Singleton注解或者定制的@Scope只是局部范围单例，在同一个Activity/Fragment的生命周期中保持单例）
 *
 * BaseComponent中不再需要写inject方法,
 * 因为这个component是用来让别的component来依赖的,
 * 只需要告诉别的component他可以提供哪些类型的依赖即可,
 * 这里我们提供一个全局 ApiManager
 */
@Singleton
@Component(modules = {BaseModule.class})
public interface BaseComponent {
    ApiManager provideApiManager();
}
