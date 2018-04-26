package com.yuehai.android.center.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yuehai.android.center.R;
import com.yuehai.android.common.base.ToolbarAppCompatActivity;

@Route(path = "/center/userInfo")
public class UserInfoActivity extends ToolbarAppCompatActivity {
    @Autowired
    String query;

    @Override
    protected void init(Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        TextView user_info_tv = findViewById(R.id.user_info_tv);
        if (this.query != null) {
            user_info_tv.setText(this.query);
            user_info_tv.setOnClickListener(view -> {
                setResult(RESULT_OK);
                finish();
            });
        }
        Button user_info_btn = findViewById(R.id.user_info_btn);
        user_info_btn.setOnClickListener(view -> {
            ARouter.getInstance().build("/main/main").navigation();
            finish();
        });
    }

    @Override
    protected String getToolbarTitle() {
        return "用户信息";
    }

    @Override
    protected boolean hasToolbar() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }
}
