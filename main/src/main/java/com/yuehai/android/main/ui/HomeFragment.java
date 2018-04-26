package com.yuehai.android.main.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yuehai.android.common.base.BaseFragment;
import com.yuehai.android.main.R;
import com.yuehai.android.main.contract.HomeContract;

import javax.inject.Inject;

/**
 * 首页
 * Created by 月海 on 2018/4/20.
 */

public class HomeFragment extends BaseFragment implements HomeContract.HomeView {

    @Inject
    HomeContract.HomePresenter homePresenter;


    @Override
    protected int getViewResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        TextView home_tv = findViewById(R.id.home_tv);
        home_tv.setText("首页");
        home_tv.setOnClickListener(view -> {
            ARouter.getInstance().build("/search/search").navigation(getContext(), new NavCallback() {
                @Override
                public void onFound(Postcard postcard) {
                    Log.e("1818lao", "onArrival: 找到了 ");
                }

                @Override
                public void onLost(Postcard postcard) {
                    Log.e("1818lao", "onArrival: 找不到了 ");
                }

                @Override
                public void onArrival(Postcard postcard) {
                    Log.e("1818lao", "onArrival: 跳转完了 ");
                }

                @Override
                public void onInterrupt(Postcard postcard) {
                    Log.e("1818lao", "onArrival: 被拦截了 ");
                }
            });
        });
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
