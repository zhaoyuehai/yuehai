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
            switch (msg.what) {
                case Constants.MSG_FROM_CLIENT:
                    //获取客服端发来的消息
                    String msgStr = msg.getData().getString(Constants.MSG_KEY);
                    Log.e("客户端发来:msg-$$->", msgStr);
                    //给客户端回复消息（此处必须用Message带来的Messenger = msg.replyTo）
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.MSG_KEY, "收到了：" + msgStr);
                    Message message = Message.obtain(null, Constants.MSG_FROM_SERVICE);
                    message.setData(bundle);
                    try {
                        msg.replyTo.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
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
