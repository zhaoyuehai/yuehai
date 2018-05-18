package com.yuehai.android.common.config;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface Constant {

    //String BASE_URL = "http://api.zhuishushenqi.com";
    //翻译接口
    //String BASE_URL = "http://10.240.0.46:38080";
    //本地接口
    String BASE_URL = "http://192.168.1.110:8080";

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
