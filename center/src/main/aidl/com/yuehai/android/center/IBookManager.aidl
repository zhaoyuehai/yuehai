// IBookManager.aidl
package com.yuehai.android.center;

import com.yuehai.android.common.bean.Book;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}
