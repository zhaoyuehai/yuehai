package com.yuehai.android;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.yuehai.android.common.base.BaseApplication;

/**
 * 正式应用的Application
 * Created by 月海 on 2018/4/18.
 */

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // dex突破65535的限制
        MultiDex.install(this);
    }
}
