package com.yuehai.android.common.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * AppCompatActivity基类
 * Created by 月海 on 2018/4/18.
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity {

    // 添加Fragment
    protected void addFragment(BaseFragment fragment, int frameLayoutId, String fragmentTag) {
        Fragment fg = getSupportFragmentManager().findFragmentByTag(fragmentTag);
        if (null == fg) {
            FragmentTransaction ft = this.getSupportFragmentManager()
                    .beginTransaction();
            //隐藏其他的
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            for (Fragment f : fragments
                    ) {
                ft.hide(f);
            }
            ft.add(frameLayoutId, fragment, fragmentTag);
            ft.commitAllowingStateLoss();
        }
    }

    // 删除Fragment
    protected void removeFragment(String fragmentTag) {
        Fragment fg = getSupportFragmentManager().findFragmentByTag(fragmentTag);
        if (null == fg) return;
        FragmentTransaction ft = this.getSupportFragmentManager()
                .beginTransaction();
        ft.remove(fg);
        ft.commitAllowingStateLoss();
    }

    // 显示 Fragment
    protected void showFragment(String fragmentTag) {
        Fragment fg = getSupportFragmentManager().findFragmentByTag(fragmentTag);
        if (null == fg) return;
        if (fg.isHidden()) {
            FragmentTransaction ft = this.getSupportFragmentManager()
                    .beginTransaction();
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            for (Fragment fragment : fragments
                    ) {
                if (fg.equals(fragment)) {
                    ft.show(fg);
                } else {
                    ft.hide(fragment);
                }
            }
            ft.commitAllowingStateLoss();
        }
    }

}
