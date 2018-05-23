package com.yuehai.android.common.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yuehai.android.common.api.BaseComponent;
import com.yuehai.android.common.api.BaseModule;
import com.yuehai.android.common.api.DaggerBaseComponent;
import com.yuehai.android.common.config.AppConfig;

/**
 * Application基类
 * Created by 月海 on 2018/4/18.
 */

public abstract class BaseApplication extends Application {
    private static BaseApplication mContext;
    private BaseComponent baseComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        baseComponent = DaggerBaseComponent.builder().baseModule(new BaseModule()).build();
        //初始化工作
        if (AppConfig.getInstance().isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
    }

    public BaseComponent getBaseComponent() {
        return baseComponent;
    }

    public static BaseApplication getApplication() {
        return mContext;
    }
}
