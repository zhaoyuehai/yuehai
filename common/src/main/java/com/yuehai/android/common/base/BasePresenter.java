package com.yuehai.android.common.base;

public interface BasePresenter<T> {
    void attachView(T view);

    void detachView();
}
