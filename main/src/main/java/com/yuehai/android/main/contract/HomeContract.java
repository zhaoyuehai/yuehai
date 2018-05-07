package com.yuehai.android.main.contract;

import com.yuehai.android.common.base.BasePresenter;
import com.yuehai.android.common.base.BaseView;

import java.util.List;

/**
 * 类目契约类
 */
public class HomeContract {

    public interface View extends BaseView {
        void showRecommendList(List list);
    }

    public interface Presenter<T> extends BasePresenter<T> {
        void getRecommendList();
    }

}
