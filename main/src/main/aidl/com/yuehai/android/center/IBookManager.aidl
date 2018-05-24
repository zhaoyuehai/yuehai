// IBookManager.aidl
package com.yuehai.android.center;

import com.yuehai.android.center.bean.Book;
//定向 tag 是这样的： AIDL中的定向 tag 表示了在跨进程通信中数据的流向
//其中 in 表示数据只能由客户端流向服务端， out 表示数据只能由服务端流向客户端
//而 inout 则表示数据可在服务端与客户端之间双向流通。
interface IBookManager {
    List<Book> getBookList();
    //传参时，除了Java基本类型以及String,CharSequence之外的类型都需要在前面加上定向tag
    void addBook(in Book book);
}
