package com.yuehai.android.main.ui.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import com.yuehai.android.common.widget.BaseViewHolder;
import com.yuehai.android.main.R;
import com.yuehai.android.common.bean.UsersBean;

public class HomeRecyclerViewHolder extends BaseViewHolder<UsersBean.User> {
    private TextView textView;

    public HomeRecyclerViewHolder(ViewGroup parent) {
        super(parent, R.layout.recycler_view_item);
        textView = $(R.id.tv);
    }

    @Override
    public void setData(UsersBean.User data) {
        super.setData(data);
        textView.setText(data.toString());
    }
}
