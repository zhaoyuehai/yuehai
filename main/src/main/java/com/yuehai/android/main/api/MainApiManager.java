package com.yuehai.android.main.api;

import com.yuehai.android.common.api.ApiManager;

public class MainApiManager {

    private MainApiService mMainApiService;

    private MainApiManager() {
    }

    private static class MainApiManagerHolder {
        private final static MainApiManager mainApiManager = new MainApiManager();
    }

    public static MainApiManager getInstance() {
        return MainApiManagerHolder.mainApiManager;
    }

    public MainApiService getMainApiService(ApiManager apiManager) {
        if (mMainApiService == null) {
            mMainApiService = apiManager.getApiService(MainApiService.class);
        }
        return mMainApiService;
    }
}
