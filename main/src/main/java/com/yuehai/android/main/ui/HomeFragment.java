package com.yuehai.android.main.ui;

import android.os.Bundle;

import com.yuehai.android.common.base.BaseFragment;
import com.yuehai.android.common.dagger.ApiModule;
import com.yuehai.android.main.R;
import com.yuehai.android.main.api.MainApi;
import com.yuehai.android.main.contract.HomeContract;
import com.yuehai.android.main.dagger.DaggerMainComponent;
import com.yuehai.android.main.dagger.MainApiModule;
import com.yuehai.android.main.presenter.HomePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

/**
 * 首页
 * Created by 月海 on 2018/4/20.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {
    @Inject
    MainApi mainApi;

    @Override
    protected void initInject() {
        DaggerMainComponent.builder()
                .apiModule(new ApiModule())
                .mainApiModule(new MainApiModule())
                .build()
                .inject(this);
    }

    @Override
    protected int getViewResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        EventBus.getDefault().post("GetRecommendEvent");
    }


    @Subscribe
    public void GetRecommendEvent(String event) {
        mPresenter.getRecommendList();
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

    @Override
    public void showRecommendList(List list) {

    }

    @Override
    public final void attachView() {
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null)
            mPresenter.detachView();
        EventBus.getDefault().unregister(this);
    }
}
