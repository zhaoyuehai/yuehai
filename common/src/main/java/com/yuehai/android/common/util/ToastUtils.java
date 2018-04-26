package com.yuehai.android.common.util;

import android.content.Context;
import android.widget.Toast;

import com.yuehai.android.common.base.BaseApplication;

public class ToastUtils {

    private static String oldMsg;
    protected static Toast toast = null;
    private static long oneTime = 0;
    private static long twoTime = 0;

    public static void showMyToast(Context context, String msg) {
        if (StringUtils.isNotEmpty(msg)) {
            if (toast == null) {
                toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
                toast.show();
                oneTime = System.currentTimeMillis();
            } else {
                twoTime = System.currentTimeMillis();
                if (msg.equals(oldMsg)) {
                    if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                        toast.show();
                    }
                } else {
                    oldMsg = msg;
                    toast.setText(msg);
                    toast.show();
                }
            }
            oneTime = twoTime;
        }
    }

    public static void showMyToast(Context context, int resId) {
        showMyToast(context, context.getString(resId));
    }

    public static void showMyToast(int resId) {
        if (BaseApplication.getApplication() != null) {
            showMyToast(BaseApplication.getApplication(), BaseApplication.getApplication().getString(resId));
        }
    }

    public static void showMyToast(String msg) {
        if (BaseApplication.getApplication() != null) {
            if (StringUtils.isNotEmpty(msg)) {
                showMyToast(BaseApplication.getApplication(), msg);
            }
        }
    }

}
