package com.yuehai.android.main.event;

import com.yuehai.android.center.bean.Book;

public class AddBookEvent {
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
