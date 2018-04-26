package com.yuehai.android.main.ui;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yuehai.android.common.base.ToolbarAppCompatActivity;
import com.yuehai.android.main.R;

@Route(path = "/main/setting")
public class SettingActivity extends ToolbarAppCompatActivity {

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected String getToolbarTitle() {
        return "设置";
    }

    @Override
    protected boolean hasToolbar() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }
}
