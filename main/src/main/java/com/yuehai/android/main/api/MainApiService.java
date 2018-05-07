package com.yuehai.android.main.api;

import com.yuehai.android.main.bean.Recommend;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MainApiService {

    @GET("/book/recommend")
    Observable<Recommend> getRecommend(@Query("gender") String gender);
}
