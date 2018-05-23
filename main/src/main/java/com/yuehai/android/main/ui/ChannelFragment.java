package com.yuehai.android.main.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yuehai.android.common.base.BaseApplication;
import com.yuehai.android.common.base.BaseFragment;
import com.yuehai.android.common.bean.TransResult;
import com.yuehai.android.main.R;
import com.yuehai.android.main.contract.ChannelContract;
import com.yuehai.android.main.dagger.DaggerMainComponent;
import com.yuehai.android.main.presenter.ChannelPresenter;

/**
 * 频道
 * Created by 月海 on 2018/4/20.
 */

public class ChannelFragment extends BaseFragment<ChannelPresenter> implements ChannelContract.View {


    private TextView result_tv;
    private EditText trans_et;
    private Button trans_btn;
    private ProgressBar progress;

    @Override
    protected void initInject() {
        DaggerMainComponent.builder()
                .baseComponent(BaseApplication.getApplication().getBaseComponent())
                .build()
                .inject(this);
    }

    @Override
    protected int getViewResource() {
        return R.layout.fragment_channel;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        TextView tv1 = findViewById(R.id.tv1);
        tv1.setText("中英翻译");
        result_tv = findViewById(R.id.result_tv);
        progress = findViewById(R.id.progress);
        trans_et = findViewById(R.id.trans_et);
        trans_btn = findViewById(R.id.trans_btn);
        trans_btn.setOnClickListener(view -> {
            String text = trans_et.getText().toString().trim();
            if (text.length() > 0) {
                result_tv.setText("");
                mPresenter.trans("\"" + text + "\"");
            } else {
                trans_et.setError("请输入内容");
                trans_et.requestFocus();
            }
        });
    }

    @Override
    public void changeTheme(int themeType) {

    }

    @Override
    public void showErrorView(String msg) {
        result_tv.setText(msg);
    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void showLoadingView() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void setButtonEnable(boolean enable) {
        trans_btn.setEnabled(enable);
    }

    @Override
    public void dismissLoadingView() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showResult(TransResult transResult) {
        if (transResult.getCode().equals("000000")) {
            result_tv.setText(transResult.getResult().get(0).getDst());
        }
    }
}
