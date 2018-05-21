package com.yuehai.android.main.ui.adapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.yuehai.android.common.widget.BaseRecycleAdapter;
import com.yuehai.android.common.widget.BaseViewHolder;
import com.yuehai.android.main.bean.UsersBean;

public class HomeRecyclerViewAdapter extends BaseRecycleAdapter<UsersBean.User> {

    @Override
    protected void onMyBindViewHolder(BaseViewHolder<UsersBean.User> holder) {
        holder.setData(getItem(holder.getAdapterPosition()));
    }

    @NonNull
    @Override
    public BaseViewHolder<UsersBean.User> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeRecyclerViewHolder(parent);
    }
}
