package com.yuehai.android.main.api;

import com.yuehai.android.common.bean.BaseBean2;
import com.yuehai.android.main.bean.Recommend;
import com.yuehai.android.main.bean.TransResult;
import com.yuehai.android.main.bean.UserBean;
import com.yuehai.android.main.bean.UsersBean;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
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

//    @FormUrlEncoded
//@FormUrlEncoded将会自动将请求参数的类型调整为application/x-www-form-urlencoded，假如key传递的参数为”435678”，那么最后得到的请求体就是key=”435678”

    //提交Json数据
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("/user/addUser")
    Observable<BaseBean2> addUser(@Body RequestBody requestBody);
}
