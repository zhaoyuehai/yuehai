package com.yuehai.android.main.presenter;

import com.yuehai.android.common.base.RxPresenter;
import com.yuehai.android.main.contract.HomeContract;

import javax.inject.Inject;

public class HomePresenter extends RxPresenter<HomeContract.HomeView> {
    @Inject
    public HomePresenter() {
    }
}
