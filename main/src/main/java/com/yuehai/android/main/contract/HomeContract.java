package com.yuehai.android.main.contract;

import com.yuehai.android.common.base.BasePresenter;
import com.yuehai.android.common.base.BaseView;
import com.yuehai.android.main.bean.UserBean;
import com.yuehai.android.main.bean.UsersBean;

import java.util.List;

/**
 * 类目契约类
 */
public class HomeContract {

    public interface View extends BaseView {
        void showRecommendList(List list);

        void showUsers(UsersBean users);

        void showUser(UserBean user);
    }

    public interface Presenter<T> extends BasePresenter<T> {
        void getRecommendList();

        void getUserList();

        void findUserById(int id);
    }

}
