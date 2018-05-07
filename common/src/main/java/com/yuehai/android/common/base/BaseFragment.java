package com.yuehai.android.common.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

/**
 * Fragment基类
 * Created by 月海 on 2018/4/20.
 */

public abstract class BaseFragment<T1 extends BasePresenter> extends Fragment implements BaseView {

    @Inject
    protected T1 mPresenter;

    protected abstract void initInject();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getViewResource(), container, false);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T findViewById(@IdRes int id) {
        if (null == getView()) return null;
        return getView().findViewById(id);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initInject();
        attachView();
        init(savedInstanceState);
    }

    public abstract void attachView();

    @LayoutRes
    protected abstract int getViewResource();

    protected abstract void init(Bundle savedInstanceState);

}
