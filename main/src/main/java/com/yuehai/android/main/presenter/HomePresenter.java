package com.yuehai.android.main.presenter;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.yuehai.android.common.api.ApiManager;
import com.yuehai.android.common.base.RxPresenter;
import com.yuehai.android.common.config.Constants;
import com.yuehai.android.common.util.ToastUtils;
import com.yuehai.android.common.bean.BaseBean2;
import com.yuehai.android.common.bean.Recommend;
import com.yuehai.android.common.bean.UserBean;
import com.yuehai.android.common.bean.UsersBean;
import com.yuehai.android.main.contract.HomeContract;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 * HomeFragment 用的是  HomeP 用的是---> HomeP --impl--> H.P
 *
 * HomeP --impl--> H.P
 * HomeP --extd--> RxP
 *                 RxP --impl--> BaseP
 */
public class HomePresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter {

    private ApiManager mApiManager;

    @Inject
    public HomePresenter(ApiManager apiManager) {
        this.mApiManager = apiManager;
    }

    @Override
    public void getRecommendList() {
        mApiManager.getRecommend(Constants.Gender.MALE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Recommend>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("......................");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("......................");
                    }

                    @Override
                    public void onNext(Recommend recommend) {
                        System.out.println("......................");
                    }
                });
    }

    //标准的Retrofit封装的okhttp3核心的网络框架
    @Override
    public void getUserList() {
        mView.showLoadingView();
        mApiManager.getUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UsersBean>() {
                    @Override
                    public void onCompleted() {
                        mView.dismissLoadingView();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dismissLoadingView();
                    }

                    @Override
                    public void onNext(UsersBean users) {
                        mView.showUsers(users);
                    }
                });
    }

    //仅用okhttp3实现网络请求
    @Override
    public void getUserList2() {
//        mView.showLoadingView();
        //OkHttp网络请求不能再主线程(UI线程)进行
        OkHttpClient okHttpClient = new OkHttpClient();
        //url()里面不能为空，否则会抛出异常
        Request request = new Request.Builder().url("http://192.168.1.110:8080/user/getUserList").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                //注意：response.body().string()只能执行一次，否则会抛出okhttp异常java.lang.IllegalStateException: closed
                String data = response.body().string();
                if (data != null) {
                    mView.postShowUsers(new Gson().fromJson(data, UsersBean.class));
                } else {
                    mView.postShowUsers(null);
                }
            }
        });
//        mView.dismissLoadingView();
    }

    //使用android原生的HttpURLConnection进行网络请求
    @Override
    public void getUserList3() {
        try {
            URL url = new URL("http://192.168.1.110:8080/user/getUserList");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(15000);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = connection.getInputStream();
                String data = changeInputStream(inputStream, "utf-8");
                if (data != null) {
                    mView.postShowUsers(new Gson().fromJson(data, UsersBean.class));
                } else {
                    mView.postShowUsers(null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 把服务端返回的输入流转换成字符串
     *
     * @param inputStream 服务端返回的输入流
     * @param encode      编码格式
     * @return 转换后的字符串
     */
    public static String changeInputStream(InputStream inputStream, String encode) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len;
        String result = "";
        if (inputStream != null) {
            try {
                while ((len = inputStream.read(data)) != -1) {
                    outputStream.write(data, 0, len);
                }
                result = new String(outputStream.toByteArray(), encode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public void findUserById(int id) {
        mView.showLoadingView();
        mApiManager.findUserById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserBean>() {
                    @Override
                    public void onCompleted() {
                        mView.dismissLoadingView();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dismissLoadingView();
                    }

                    @Override
                    public void onNext(UserBean user) {
                        mView.showUser(user);
                    }
                });
    }

    @Override
    public void addUser() {
        mView.showLoadingView();
        UserBean.User user = new UserBean.User();
        user.setUserName("哈哈哈");
        user.setMobile("18511073583");
        user.setPassword("123456");
        String obj = new Gson().toJson(user);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), obj);
        mApiManager.addUser(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBean2>() {
                    @Override
                    public void onCompleted() {
                        mView.dismissLoadingView();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dismissLoadingView();
                    }

                    @Override
                    public void onNext(BaseBean2 user) {
                        ToastUtils.showMyToast(user.getMessage());
                    }
                });
    }

}
