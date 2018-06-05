package com.yuehai.android.search.jni;

public class MyJniTest {
    static {
        System.loadLibrary("NameProvider");
    }

    public native String getName();

    public native String getName2(String string);

}
