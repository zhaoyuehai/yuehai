package com.yuehai.android.main.ui;

import android.os.Bundle;

import com.yuehai.android.common.base.BasePresenterFragment;
import com.yuehai.android.main.R;
import com.yuehai.android.main.contract.HomeContract;

import org.greenrobot.eventbus.EventBus;

/**
 * 首页
 * Created by 月海 on 2018/4/20.
 */

public class HomeFragment extends BasePresenterFragment<HomeContract.HomePresenter> implements HomeContract.HomeView {

    @Override
    protected int getViewResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void changeTheme(int themeType) {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void showLoadingView() {

    }
}
