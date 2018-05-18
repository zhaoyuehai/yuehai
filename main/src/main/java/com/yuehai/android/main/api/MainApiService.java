package com.yuehai.android.main.api;

import com.yuehai.android.main.bean.Recommend;
import com.yuehai.android.main.bean.TransResult;
import com.yuehai.android.main.bean.UserBean;
import com.yuehai.android.main.bean.UsersBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MainApiService {

    @GET("/book/recommend")
    Observable<Recommend> getRecommend(@Query("gender") String gender);

    //"from=auto&msg=\"" + string + "\"&to=[\"" + languageCode + "\"]";
    //    http://10.240.0.46:38080/trans/imtrans.action?from=zh_CN&msg=“你好”&to=["en"]
    @GET("/trans/imtrans.action")
    Observable<TransResult> trans(@Query("from") String from,
                                  @Query("msg") String msg,
                                  @Query("to") String to);

    @GET("/user/getUserList")
    Observable<UsersBean> getUserList();

    @GET("/user/findUserById")
    Observable<UserBean> findUserById(@Query("id") int id);
}
