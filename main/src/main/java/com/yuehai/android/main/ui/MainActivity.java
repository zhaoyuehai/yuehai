package com.yuehai.android.main.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yuehai.android.common.base.BaseAppCompatActivity;
import com.yuehai.android.common.util.ToastUtils;
import com.yuehai.android.main.R;

/**
 * 主界面
 * Created by 月海 on 2018/4/18.
 */
@Route(path = "/main/main")
public class MainActivity extends BaseAppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        RadioGroup rg = findViewById(R.id.main_bottom_rg);
        rg.setOnCheckedChangeListener(this);
        rg.check(R.id.main_home_rb);
    }


    private boolean mIsExit = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            prepareExit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void prepareExit() {
        if (mIsExit) {
            exit();
        } else {
            ToastUtils.showMyToast(R.string.exit_app_tips);
            mIsExit = true;
            new Handler().postDelayed(() -> mIsExit = false, 2000);
        }
    }

    private void exit() {
        finish();
        System.exit(0);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        if (checkedId == R.id.main_home_rb) {
            String HOME_FRAGMENT_TAG = "home";
            android.support.v4.app.Fragment home = getSupportFragmentManager().findFragmentByTag(HOME_FRAGMENT_TAG);
            if (home == null || home.isHidden()) {
                if (home == null) {
                    addFragment(new HomeFragment(), R.id.main_content_fl, HOME_FRAGMENT_TAG);
                } else {
                    showFragment(HOME_FRAGMENT_TAG);
                }
            }
        } else if (checkedId == R.id.main_category_rb) {
            String CATEGORY_FRAGMENT_TAG = "category";
            android.support.v4.app.Fragment category = getSupportFragmentManager().findFragmentByTag(CATEGORY_FRAGMENT_TAG);
            if (category == null || category.isHidden()) {
                if (category == null) {
                    addFragment(new CategoryFragment(), R.id.main_content_fl, CATEGORY_FRAGMENT_TAG);
                } else {
                    showFragment(CATEGORY_FRAGMENT_TAG);
                }
            }
        } else if (checkedId == R.id.main_channel_rb) {
            String CHANNEL_FRAGMENT_TAG = "channel";
            android.support.v4.app.Fragment channel = getSupportFragmentManager().findFragmentByTag(CHANNEL_FRAGMENT_TAG);
            if (channel == null || channel.isHidden()) {
                if (channel == null) {
                    addFragment(new ChannelFragment(), R.id.main_content_fl, CHANNEL_FRAGMENT_TAG);
                } else {
                    showFragment(CHANNEL_FRAGMENT_TAG);
                }
            }
        } else if (checkedId == R.id.main_car_rb) {
            String CAR_FRAGMENT_TAG = "car";
            android.support.v4.app.Fragment car = getSupportFragmentManager().findFragmentByTag(CAR_FRAGMENT_TAG);
            if (car == null || car.isHidden()) {
                if (car == null) {
                    addFragment(new CarFragment(), R.id.main_content_fl, CAR_FRAGMENT_TAG);
                } else {
                    showFragment(CAR_FRAGMENT_TAG);
                }
            }
        } else if (checkedId == R.id.main_center_rb) {
            String CENTER_FRAGMENT_TAG = "center";
            android.support.v4.app.Fragment center = getSupportFragmentManager().findFragmentByTag(CENTER_FRAGMENT_TAG);
            if (center == null || center.isHidden()) {
                if (center == null) {
                    addFragment(new CenterFragment(), R.id.main_content_fl, CENTER_FRAGMENT_TAG);
                } else {
                    showFragment(CENTER_FRAGMENT_TAG);
                }
            }
        }
    }
}
