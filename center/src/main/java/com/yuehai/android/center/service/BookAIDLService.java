package com.yuehai.android.center.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.yuehai.android.center.IBookManager;
import com.yuehai.android.center.bean.Book;
import com.yuehai.android.common.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class BookAIDLService extends Service {

    private List<Book> mBooks = new ArrayList<>();

    IBinder binder = new IBookManager.Stub() {

        @Override
        public List<Book> getBookList() {
            synchronized (this) {
                LogUtils.e("--BookAIDLService-->getBookList: " + mBooks.toString());
                if (mBooks != null) {
                    return mBooks;
                }
                return new ArrayList<>();
            }
        }

        @Override
        public void addBook(Book book) {
            synchronized (this) {
                if (mBooks == null) {
                    mBooks = new ArrayList<>();
                }
                if (book == null) {
                    book = new Book();
                }
                //尝试修改book的参数，主要是为了观察其到客户端的反馈
                book.setId(111);
                if (!mBooks.contains(book)) {
                    mBooks.add(book);
                }
                //打印mBooks列表，观察客户端传过来的值
                LogUtils.e("--BookAIDLService-->addBook: " + mBooks.toString());
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Book book = new Book();
        book.setBookName("Android开发艺术探索");
        book.setId(1);
        mBooks.add(book);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
