// Book.aidl 定义parcelable对象，以供其他AIDL文件使用AIDL中非默认支持的数据类型。
//引入了一个序列化对象Book
//注意Book.ail与Book.java的包名应当是一样的
package com.yuehai.android.center.bean;

parcelable Book;
