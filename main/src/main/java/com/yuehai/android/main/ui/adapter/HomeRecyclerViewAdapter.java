package com.yuehai.android.main.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.yuehai.android.main.R;
import com.yuehai.android.main.bean.UsersBean;

import java.util.ArrayList;
import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewHolder> {
    private Context mContext;

    private List<UsersBean.User> mList;

    public HomeRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void addData(List<UsersBean.User> list) {
        mList.addAll(list);
        notifyItemRangeInserted(getItemCount() - list.size(), list.size());
    }

    public void addData(UsersBean.User usersBean) {
        mList.add(usersBean);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeRecyclerViewHolder(View.inflate(mContext, R.layout.recycler_view_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerViewHolder holder, int position) {
        holder.setData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }
}
