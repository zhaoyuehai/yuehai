package com.yuehai.android.main.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.yuehai.android.common.base.BaseFragment;
import com.yuehai.android.common.dagger.ApiModule;
import com.yuehai.android.main.R;
import com.yuehai.android.main.bean.UserBean;
import com.yuehai.android.main.bean.UsersBean;
import com.yuehai.android.main.contract.HomeContract;
import com.yuehai.android.main.dagger.DaggerMainComponent;
import com.yuehai.android.main.dagger.MainApiModule;
import com.yuehai.android.main.presenter.HomePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * 首页
 * Created by 月海 on 2018/4/20.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    private ProgressDialog progressDialog;

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
        Log.e("yuehai", "home----init");
        EventBus.getDefault().register(this);
        EventBus.getDefault().post(1);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void GetRecommendEvent(Integer event) {
//        TextView home_tv = findViewById(R.id.home_tv);
//        home_tv.setText(event);
        mPresenter.findUserById(event);
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
        if (progressDialog == null)
            progressDialog = new ProgressDialog(getContext());
        progressDialog.show();
    }

    @Override
    public void dismissLoadingView() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void showRecommendList(List list) {

    }

    @Override
    public void showUsers(UsersBean users) {
        TextView home_tv = findViewById(R.id.home_tv);
        if (users.getCode() == 0) {
            home_tv.setText(users.getList().get(0).toString());
        } else {
            home_tv.setText(users.getMessage());
        }
    }

    @Override
    public void showUser(UserBean user) {
        TextView home_tv = findViewById(R.id.home_tv);
            if (user.getCode() == 0) {
            home_tv.setText(user.getUser().toString());
        } else {
            home_tv.setText(user.getMessage());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
