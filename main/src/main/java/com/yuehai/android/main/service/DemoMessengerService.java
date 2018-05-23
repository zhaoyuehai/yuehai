package com.yuehai.android.main.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.yuehai.android.common.config.Constants;

/**
 * Messenger进程间通信IPC的Demo
 * 服务端
 */
public class DemoMessengerService extends Service {

    @SuppressLint("HandlerLeak")
    private class MyHandle extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Log.e("service--what->", String.valueOf(msg.what));
            switch (msg.what) {
                case Constants.MSG_FROM_CLIENT:
                    //获取客服端发来的消息
                    Log.e("service--bundle->", msg.getData().getString(Constants.MSG_KEY));
                    //给客户端回复消息
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.MSG_KEY, "Hi! Client.");
                    replyMessageToClient(bundle, Constants.MSG_FROM_SERVICE);
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }

    private void replyMessageToClient(Bundle bundle, int what) {
        if (messenger == null) return;
        Message message = Message.obtain(null, what);
        message.setData(bundle);
        try {
            messenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private Messenger messenger;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if (messenger == null)
            messenger = new Messenger(new MyHandle());
        return messenger.getBinder();
    }
}
