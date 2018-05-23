package com.yuehai.android.main.contract;

import com.yuehai.android.common.base.BaseView;

/**
 * 类目契约类
 */
public class CenterContract {

    public interface View extends BaseView {

    }

    public interface Presenter {
        void demo(String str);
    }

}
