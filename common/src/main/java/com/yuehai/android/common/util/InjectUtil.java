package com.yuehai.android.common.util;

/**
 * 获取view 核心运行类
 * Created by 月海 on 2018/4/19.
 */
//public class InjectUtil {

//    public static void injectView(Activity activity) {
//        //获取Activity对象
//        Class<? extends Activity> aClass = activity.getClass();
//        //反射获取所有字段属性
//        Field[] declaredFields = aClass.getDeclaredFields();
//        //遍历字段属性，寻找@GetView注解的
//        if (declaredFields == null || declaredFields.length <= 0) return;
//        for (Field filed : declaredFields
//                ) {
//            GetView annotation = filed.getAnnotation(GetView.class);
//            if (annotation != null) {
//                View viewById = activity.findViewById(annotation.value());
//                if (viewById != null) {
//                    filed.setAccessible(true);
//                    try {
//                        filed.set(activity, viewById);
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
//
//    public static void injectView(android.app.Fragment fragment) {
//        //获取Activity对象
//        Class<? extends android.app.Fragment> fClass = fragment.getClass();
//        //反射获取所有字段属性
//        Field[] declaredFields = fClass.getDeclaredFields();
//        //遍历字段属性，寻找@GetView注解的
//        if (declaredFields == null || declaredFields.length <= 0) return;
//        for (Field filed : declaredFields
//                ) {
//            GetView annotation = filed.getAnnotation(GetView.class);
//            if (annotation != null && fragment.getView() != null) {
//                View viewById = fragment.getView().findViewById(annotation.value());
//                if (viewById != null) {
//                    filed.setAccessible(true);
//                    try {
//                        filed.set(fragment, viewById);
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
//
//    public static void injectView(android.support.v4.app.Fragment fragment) {
//        //获取Activity对象
//        Class<? extends android.support.v4.app.Fragment> fClass = fragment.getClass();
//        //反射获取所有字段属性
//        Field[] declaredFields = fClass.getDeclaredFields();
//        //遍历字段属性，寻找@GetView注解的
//        if (declaredFields == null || declaredFields.length <= 0) return;
//        for (Field filed : declaredFields
//                ) {
//            GetView annotation = filed.getAnnotation(GetView.class);
//            if (annotation != null && fragment.getView() != null) {
//                View viewById = fragment.getView().findViewById(annotation.value());
//                if (viewById != null) {
//                    filed.setAccessible(true);
//                    try {
//                        filed.set(fragment, viewById);
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
//}
