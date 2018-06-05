#include "com_yuehai_android_search_jni_MyJniTest.h"
JNIEXPORT jstring JNICALL Java_com_yuehai_android_search_jni_MyJniTest_getName
//env：JNI 接口指针。
        (JNIEnv *env, jobject job) {
    return env->NewStringUTF((char *) "哈哈啊哈");
};

JNIEXPORT jstring JNICALL Java_com_yuehai_android_search_jni_MyJniTest_getName2__Ljava_lang_String_2
        (JNIEnv *env, jobject job, jstring jstr) {
    return env->NewString(reinterpret_cast<const jchar *>(jstr), 1l);
};
