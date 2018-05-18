package com.yuehai.android.main.presenter;

import com.yuehai.android.common.base.RxPresenter;
import com.yuehai.android.common.config.Constant;
import com.yuehai.android.main.api.MainApi;
import com.yuehai.android.main.bean.Recommend;
import com.yuehai.android.main.bean.UserBean;
import com.yuehai.android.main.bean.UsersBean;
import com.yuehai.android.main.contract.HomeContract;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomePresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter<HomeContract.View> {

    private MainApi mMainApi;

    @Inject
    public HomePresenter(MainApi mainApi) {
        this.mMainApi = mainApi;
    }

    @Override
    public void getRecommendList() {
        mMainApi.getRecommend(Constant.Gender.MALE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Recommend>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("......................");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("......................");
                    }

                    @Override
                    public void onNext(Recommend recommend) {
                        System.out.println("......................");
                    }
                });
    }

    @Override
    public void getUserList() {
        mView.showLoadingView();
        mMainApi.getUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UsersBean>() {
                    @Override
                    public void onCompleted() {
                        mView.dismissLoadingView();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dismissLoadingView();
                    }

                    @Override
                    public void onNext(UsersBean users) {
                        mView.showUsers(users);
                    }
                });
    }

    @Override
    public void findUserById(int id) {
        mView.showLoadingView();
        mMainApi.findUserById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserBean>() {
                    @Override
                    public void onCompleted() {
                        mView.dismissLoadingView();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dismissLoadingView();
                    }

                    @Override
                    public void onNext(UserBean user) {
                        mView.showUser(user);
                    }
                });
    }


}
