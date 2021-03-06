package com.yuehai.android.main.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.yuehai.android.common.base.BaseFragment;
import com.yuehai.android.main.R;
import com.yuehai.android.main.contract.CarContract;

/**
 * 采购单
 * Created by 月海 on 2018/4/20.
 */

public class CarFragment extends BaseFragment implements CarContract.View {
    @Override
    protected void initInject() {

    }

    @Override
    protected int getViewResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        TextView home_tv = (TextView) findViewById(R.id.home_tv);
        home_tv.setText("采购单");
    }

    @Override
    public void changeTheme(int themeType) {

    }

    @Override
    public void showErrorView(String msg) {

    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void dismissLoadingView() {

    }
}
