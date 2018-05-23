package com.yuehai.android.common.util;

import android.util.Log;

public class LogUtils {
    private static boolean isDebug = true;// 是否需要打印bug
    private static final String TAG = "YUE_HAI";

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

}
