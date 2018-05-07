package com.yuehai.android.common.base;

/**
 * 基于Rx的Presenter封装，控制订阅的生命周期
 */
public class RxPresenter<T extends BaseView> implements BasePresenter<T> {

    protected T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

}
