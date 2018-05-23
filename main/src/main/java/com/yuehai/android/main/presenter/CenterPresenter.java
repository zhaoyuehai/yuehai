package com.yuehai.android.main.presenter;

import com.yuehai.android.common.base.RxPresenter;
import com.yuehai.android.common.util.ToastUtils;
import com.yuehai.android.main.contract.CenterContract;

import javax.inject.Inject;

public class CenterPresenter extends RxPresenter<CenterContract.View> implements CenterContract.Presenter {

    @Inject
    CenterPresenter() {
    }

    @Override
    public void demo(String str) {
        ToastUtils.showMyToast(str);
    }
}
