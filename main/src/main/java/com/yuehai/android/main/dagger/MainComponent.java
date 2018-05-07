package com.yuehai.android.main.dagger;

import com.yuehai.android.common.dagger.ApiModule;
import com.yuehai.android.main.api.MainApi;
import com.yuehai.android.main.ui.HomeFragment;

import dagger.Component;

@Component(modules = {MainApiModule.class, ApiModule.class})
public interface MainComponent {

    MainApi getMainApi();

    void inject(HomeFragment homeFragment);

}
