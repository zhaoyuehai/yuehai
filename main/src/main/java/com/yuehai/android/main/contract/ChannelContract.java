package com.yuehai.android.main.contract;

import com.yuehai.android.common.base.BasePresenter;
import com.yuehai.android.common.base.BaseView;
import com.yuehai.android.main.bean.TransResult;

/**
 * 频道契约类
 */
public class ChannelContract {

    public interface View extends BaseView {

        void setButtonEnable(boolean enable);

        void showResult(TransResult transResult);
    }

    public interface Presenter<T> extends BasePresenter<T> {

        void trans(String msg);

    }

}
