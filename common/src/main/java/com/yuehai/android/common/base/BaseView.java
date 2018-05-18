package com.yuehai.android.common.base;

public interface BaseView {

    void changeTheme(int themeType);

    void showErrorView(String msg);

    void showEmptyView();

    void showLoadingView();

    void dismissLoadingView();

}
