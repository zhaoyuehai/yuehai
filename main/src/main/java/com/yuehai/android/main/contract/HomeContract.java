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

        void postShowUsers(UsersBean users);
    }

    public interface Presenter extends BasePresenter<HomeContract.View> {
        void getRecommendList();

        void getUserList();

        void getUserList2();

        void getUserList3();

        void findUserById(int id);

        void addUser();

    }

}
