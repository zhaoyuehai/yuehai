package com.yuehai.android.main.api;

import com.yuehai.android.main.bean.Recommend;
import com.yuehai.android.main.bean.TransResult;
import com.yuehai.android.main.bean.UserBean;
import com.yuehai.android.main.bean.UsersBean;

import retrofit2.Retrofit;
import rx.Observable;

public class MainApi {

    private static MainApi instance;

    private static MainApiService mainApiService;

    private MainApi(Retrofit build) {
        mainApiService = build.create(MainApiService.class);
    }

    public static MainApi getInstance(Retrofit build) {
        if (instance == null) {
            instance = new MainApi(build);
        }
        return instance;
    }

    public Observable<Recommend> getRecommend(String gender) {
        return mainApiService.getRecommend(gender);
    }

    /**
     * 翻译接口
     */
    public Observable<TransResult> trans(String from,
                                         String msg,
                                         String to) {
        return mainApiService.trans(from, msg, to);
    }

    /**
     * 获取用户列表
     */
    public Observable<UsersBean> getUserList() {
        return mainApiService.getUserList();
    }

    /**
     * 根据ID获取用户
     */
    public Observable<UserBean> findUserById(int id) {
        return mainApiService.findUserById(id);
    }
}
