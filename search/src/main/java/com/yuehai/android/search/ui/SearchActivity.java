package com.yuehai.android.search.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yuehai.android.common.base.ToolbarAppCompatActivity;
import com.yuehai.android.common.util.ToastUtils;
import com.yuehai.android.search.R;

@Route(path = "/search/search")
public class SearchActivity extends ToolbarAppCompatActivity {

    @Override
    protected void init(Bundle savedInstanceState) {
        SearchView search_sv = findViewById(R.id.search_sv);
        search_sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ARouter.getInstance().build("/center/userInfo").withString("query", query).navigation(SearchActivity.this, 1000);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1000:
                    finish();
                    break;
            }
        }
    }

    @Override
    protected boolean onMyOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ToastUtils.showMyToast("不finish");
                return false;
        }
        return super.onMyOptionsItemSelected(item);
    }

    @Override
    protected String getToolbarTitle() {
        return "搜索";
    }

    @Override
    protected boolean hasToolbar() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

}
