package com.yuehai.android.common.config;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface Constant {

//    String BASE_URL = "http://api.zhuishushenqi.com";
    String BASE_URL = "http://10.240.0.46:38080";

    @StringDef({
            Gender.MALE,
            Gender.FEMALE
    })
    @Retention(RetentionPolicy.SOURCE)
    @interface Gender {
        String MALE = "male";

        String FEMALE = "female";
    }

}
