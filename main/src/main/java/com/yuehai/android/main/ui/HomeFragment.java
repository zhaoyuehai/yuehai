package com.yuehai.android.main.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.yuehai.android.main.ui.adapter.HomeRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * 首页
 * Created by 月海 on 2018/4/20.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    private SwipeRefreshLayout srl;
    private RecyclerView rcv;
    private HomeRecyclerViewAdapter adapter;

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
        initView();
        Log.e("yuehai", "home----init");
//        EventBus.getDefault().register(this);
//        EventBus.getDefault().post(1);
        mPresenter.getUserList();
    }

    private void initView() {
        srl = findViewById(R.id.srl);
        rcv = findViewById(R.id.rcv);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HomeRecyclerViewAdapter(getContext());
        rcv.setAdapter(adapter);
        findViewById(R.id.btn).setOnClickListener(view -> mPresenter.addUser());
        srl.setOnRefreshListener(() -> {
            adapter.clear();
            mPresenter.getUserList();
        });
    }


//    @Subscribe(threadMode = ThreadMode.ASYNC)
//    public void GetRecommendEvent(Integer event) {
//        TextView home_tv = findViewById(R.id.home_tv);
//        home_tv.setText(event);
//        mPresenter.findUserById(event);
//    }

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
        srl.post(() -> srl.setRefreshing(true));
    }

    @Override
    public void dismissLoadingView() {
        srl.post(() -> srl.setRefreshing(false));
    }

    @Override
    public void showRecommendList(List list) {

    }

    @Override
    public void showUsers(UsersBean users) {
        adapter.addData(users.getList());
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
    public void postShowUsers(UsersBean users) {
        if (users == null) return;
        rcv.post(() -> {
            showUsers(users);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
