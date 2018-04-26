package com.yuehai.android.common.base;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.view.View;

/**
 * Activity基类
 * Created by 月海 on 2018/4/18.
 */

public abstract class BaseActivity extends Activity {
    /**
     * 封装的 findViewById
     *
     * @param id  view id
     * @param <T> View
     * @return View
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T $(@IdRes int id) {
        return (T) super.findViewById(id);
    }
}
