package com.yuehai.android.main.presenter;

import com.yuehai.android.common.base.RxPresenter;
import com.yuehai.android.main.api.MainApi;
import com.yuehai.android.main.bean.TransResult;
import com.yuehai.android.main.contract.ChannelContract;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ChannelPresenter extends RxPresenter<ChannelContract.View> implements ChannelContract.Presenter{

    private MainApi mainApi;

    @Inject
    public ChannelPresenter(MainApi mainApi) {
        this.mainApi = mainApi;
    }

    private String from = "auto";
    private String to = "[\"en\"]";

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTos(String to) {
        this.to = to;
    }

    @Override
    public void trans(String msg) {
        mView.showLoadingView();
        mView.setButtonEnable(false);
        mainApi.trans(from, msg, to)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TransResult>() {
                    @Override
                    public void onCompleted() {
                        mView.setButtonEnable(true);
                        mView.dismissLoadingView();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.setButtonEnable(true);
                        mView.dismissLoadingView();
                        mView.showErrorView(e.getMessage());
                    }

                    @Override
                    public void onNext(TransResult transResult) {
                        mView.showResult(transResult);
                    }
                });

    }
}
