package com.yuehai.android.common.base;

import javax.inject.Inject;

public abstract class BasePresenterFragment<P extends BasePresenter> extends BaseFragment {
    @Inject
    protected P mPresenter;

    @Override
    public final void attachView() {
        if(mPresenter!=null)
            mPresenter.attachView(toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mPresenter!=null)
            mPresenter.detachView();
    }
}
