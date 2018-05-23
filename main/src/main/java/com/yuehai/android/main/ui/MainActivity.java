package com.yuehai.android.main.ui;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yuehai.android.common.base.BaseAppCompatActivity;
import com.yuehai.android.common.config.Constants;
import com.yuehai.android.common.util.ToastUtils;
import com.yuehai.android.main.R;
import com.yuehai.android.main.service.DemoMessengerService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 主界面
 * Created by 月海 on 2018/4/18.
 */
@Route(path = "/main/main")
public class MainActivity extends BaseAppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private ServiceConnection serviceConnection;
    private Messenger messenger;
    private Messenger mReplyMessenger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    private void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        RadioGroup rg = findViewById(R.id.main_bottom_rg);
        rg.setOnCheckedChangeListener(this);
        RadioButton rb = findViewById(R.id.main_home_rb);
        rb.setChecked(true);

        bindMessengerService();
    }

    @Subscribe
    public void sendMessage(String content) {
        if (content == null) return;
        if (serviceConnection == null) return;
        Bundle bundle = new Bundle();
        bundle.putString(Constants.MSG_KEY, content);
        sendMessageToService(bundle, Constants.MSG_FROM_CLIENT);
    }

    //绑定服务端的Service
    private void bindMessengerService() {
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                //连上服务，用服务端提供的IBinder对象创建一个Messenger,通过这个Messenger就可以向服务端发Message类型的消息了
                messenger = new Messenger(iBinder);
                //发个消息给服务端
                Bundle bundle = new Bundle();
                bundle.putString(Constants.MSG_KEY, "Hello! This is client.");
                sendMessageToService(bundle, Constants.MSG_FROM_CLIENT);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
        Intent intent = new Intent(this, DemoMessengerService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void sendMessageToService(Bundle bundle, int what) {
        if (messenger == null) return;
        Message message = Message.obtain(null, what);
        message.setData(bundle);
        //设置服务端响应
        message.replyTo = getReplyMessenger();
        try {
            messenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    private Messenger getReplyMessenger() {
        if (mReplyMessenger == null)
            mReplyMessenger = new Messenger(new ReplyMessageHandler());
        return mReplyMessenger;
    }


    @SuppressLint("HandlerLeak")
    private class ReplyMessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Log.e("client--what->", String.valueOf(msg.what));
            switch (msg.what) {
                case Constants.MSG_FROM_SERVICE:
                    Log.e("client--bundle->", msg.getData().getString(Constants.MSG_KEY));
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (serviceConnection != null) {
            unbindService(serviceConnection);
        }
        EventBus.getDefault().unregister(this);
        super.onDestroy();
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
            Fragment home = getSupportFragmentManager().findFragmentByTag(HOME_FRAGMENT_TAG);
            if (home == null || home.isHidden()) {
                if (home == null) {
                    addFragment(new HomeFragment(), R.id.main_content_fl, HOME_FRAGMENT_TAG);
                } else {
                    showFragment(HOME_FRAGMENT_TAG);
                }
            }
        } else if (checkedId == R.id.main_category_rb) {
            String CATEGORY_FRAGMENT_TAG = "category";
            Fragment category = getSupportFragmentManager().findFragmentByTag(CATEGORY_FRAGMENT_TAG);
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
