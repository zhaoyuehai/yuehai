package com.yuehai.android.common.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

//Scope的作用是保证依赖对象在作用范围内单例, 提供局部范围的单例,所谓局部范围就是它的生命周期范围
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
