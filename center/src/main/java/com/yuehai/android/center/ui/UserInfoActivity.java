package com.yuehai.android.center.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yuehai.android.center.IMyAidlInterface;
import com.yuehai.android.center.R;
import com.yuehai.android.center.service.DemoAIDLService;
import com.yuehai.android.common.base.ToolbarAppCompatActivity;
import com.yuehai.android.common.util.LogUtils;

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
//            ARouter.getInstance().build("/main/main").navigation();
//            finish();
            if (aidlConnection != null && iMyAidlInterface != null) {
                try {
                    int value = iMyAidlInterface.add(1, 1);
                    LogUtils.e("iMyAidl--value:" + value);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

        });
        bindAIDLService();
    }

    private IMyAidlInterface iMyAidlInterface;
    private ServiceConnection aidlConnection;

    //绑定服务端的Service
    private void bindAIDLService() {
        aidlConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
                LogUtils.e("onServiceConnected");
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                iMyAidlInterface = null;
            }
        };
//        Intent intent = new Intent();
//        intent.setComponent(new ComponentName(
//                "com.yuehai.android.center",
//                "com.yuehai.android.center.service.DemoAIDLService"));
//        bindService(intent, aidlConnection, Context.BIND_AUTO_CREATE);

        Intent intent = new Intent(this, DemoAIDLService.class);
        bindService(intent, aidlConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        if (aidlConnection != null) {
            unbindService(aidlConnection);
        }
        super.onDestroy();
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
