package com.yuehai.android.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yuehai.android.common.base.BaseFragment;
import com.yuehai.android.main.R;
import com.yuehai.android.main.contract.CategoryContract;

/**
 * 类目
 * Created by 月海 on 2018/4/20.
 */

public class CategoryFragment extends BaseFragment implements View.OnClickListener, CategoryContract.CategoryView {
    @Override
    protected int getViewResource() {
        return R.layout.fragment_category;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);
        tv1.setText("startActivity(new Intent(getApplication(),SettingActivity.class));");
        tv2.setText("ARouter.getInstance().build(\"/main/setting\").navigation();");
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv1) {
            startActivity(new Intent(getContext(),SettingActivity.class));
        } else if (view.getId() == R.id.tv2) {
            ARouter.getInstance().build("/main/setting").navigation(getContext());
        } else if (view.getId() == R.id.tv3) {
        }
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
