package com.yuehai.android.common.api;

import com.yuehai.android.common.base.BaseApplication;
import com.yuehai.android.common.config.Constants;
import com.yuehai.android.common.bean.BaseBean2;
import com.yuehai.android.common.bean.Recommend;
import com.yuehai.android.common.bean.TransResult;
import com.yuehai.android.common.bean.UserBean;
import com.yuehai.android.common.bean.UsersBean;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class ApiManager {

    private static ApiService apiService;

    ApiManager() {
        File cacheDirectory = new File(BaseApplication.getApplication().getCacheDir(), "cache");
        int cacheSize = 10 * 1024 * 1024;
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        if (AppConfig.getInstance().isDebug()) {
//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//            builder.addInterceptor(logging);
//        }
        OkHttpClient okHttpClient = builder.connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new HeaderInterceptor())
                .cache(new Cache(cacheDirectory, cacheSize)).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public Observable<Recommend> getRecommend(String gender) {
        return apiService.getRecommend(gender);
    }

    /**
     * 翻译接口
     */
    public Observable<TransResult> trans(String from,
                                         String msg,
                                         String to) {
        return apiService.trans(from, msg, to);
    }

    /**
     * 获取用户列表
     */
    public Observable<UsersBean> getUserList() {
        return apiService.getUserList();
    }

    /**
     * 根据ID获取用户
     */
    public Observable<UserBean> findUserById(int id) {
        return apiService.findUserById(id);
    }

    public Observable<BaseBean2> addUser(RequestBody requestBody) {
        return apiService.addUser(requestBody);
    }
}
