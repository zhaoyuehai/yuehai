package com.yuehai.android.main.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yuehai.android.main.R;
import com.yuehai.android.main.bean.UsersBean;

public class HomeRecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView tv;

    HomeRecyclerViewHolder(View itemView) {
        super(itemView);
        tv = itemView.findViewById(R.id.tv);
    }

    public void setData(UsersBean.User user) {
        tv.setText(user.toString());
    }
}
