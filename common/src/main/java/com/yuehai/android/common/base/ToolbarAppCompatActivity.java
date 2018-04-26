package com.yuehai.android.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.yuehai.android.common.R;

/**
 * 带ToolbarAppCompatActivity基类
 * Created by 月海 on 2018/4/18.
 */

public abstract class ToolbarAppCompatActivity extends AppCompatActivity {

    protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        if (hasToolbar()) {
            mToolbar = findViewById(R.id.toolbar);
            if (mToolbar != null) {
                if (getToolbarTitle() != null) {
                    mToolbar.setTitle(getToolbarTitle());
                }
                setSupportActionBar(mToolbar);
                if (getSupportActionBar() != null)
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
        init(savedInstanceState);
    }

    protected abstract void init(Bundle savedInstanceState);

    protected abstract String getToolbarTitle();

    protected abstract boolean hasToolbar();

    protected abstract int getLayoutId();

    protected boolean onMyOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (onMyOptionsItemSelected(item)) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    finish();
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
