package com.yuehai.android.common.config;

public class AppConfig {

    private AppConfig() {
    }

    private static class AppConfigHolder {
        static AppConfig instance = new AppConfig();
    }

    public static AppConfig getInstance() {
        return AppConfigHolder.instance;
    }

    private boolean isDebug = true;

    public boolean isDebug() {
        return isDebug;
    }
}
