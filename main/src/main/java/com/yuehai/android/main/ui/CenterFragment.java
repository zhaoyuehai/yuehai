package com.yuehai.android.main.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yuehai.android.common.base.BaseApplication;
import com.yuehai.android.common.base.BaseFragment;
import com.yuehai.android.main.R;
import com.yuehai.android.main.contract.CenterContract;
import com.yuehai.android.main.dagger.DaggerMainComponent;
import com.yuehai.android.main.presenter.CenterPresenter;

/**
 * 个人中心
 * Created by 月海 on 2018/4/20.
 */

public class CenterFragment extends BaseFragment<CenterPresenter> implements CenterContract.View {

    private EditText trans_et;
    private Button trans_btn;

    @Override
    protected void initInject() {
        DaggerMainComponent.builder()
                .baseComponent(BaseApplication.getApplication().getBaseComponent())
                .build()
                .inject(this);
    }

    @Override
    protected int getViewResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        TextView home_tv = findViewById(R.id.home_tv);
        home_tv.setText("个人中心");
        trans_et = findViewById(R.id.trans_et);
        trans_btn = findViewById(R.id.trans_btn);
        mPresenter.demo("个人中心");
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
