package com.yuehai.android.center.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.yuehai.android.center.IMyAidlInterface;
import com.yuehai.android.common.util.LogUtils;

/**
 * AIDL进程间通信IPC的Demo
 * 服务端
 */
public class DemoAIDLService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    private IBinder iBinder = new IMyAidlInterface.Stub(){

        @Override
        public int add(int a, int b) {
            LogUtils.e("收到了来自客户端的请求" + a + "+" + b );
            return a+b;
        }
    };
}
