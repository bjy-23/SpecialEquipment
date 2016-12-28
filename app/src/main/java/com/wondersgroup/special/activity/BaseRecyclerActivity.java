package com.wondersgroup.special.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.utils.EndlessRecyclerViewScrollListener;
import com.wondersgroup.special.widget.BaseRecyclerView;

public abstract class BaseRecyclerActivity extends BaseActivity {
    protected TextView mCount;
    protected BaseRecyclerView mRecyclerList;
    protected SwipeRefreshLayout mSwipeLayout;
    protected int pageNo = 1;
    protected EndlessRecyclerViewScrollListener mScrollListener;
    protected Intent intent;
    protected Bundle bundle;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_use_unit_list);
        mTitle.setText("使用单位列表");
        mCount = (TextView) findViewById(R.id.count);
        mRecyclerList = (BaseRecyclerView) findViewById(R.id.recycler);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeLayout.setColorSchemeColors(Color.RED, Color.BLUE);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        mSwipeLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        mScrollListener = new EndlessRecyclerViewScrollListener((LinearLayoutManager) mRecyclerList.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                pageNo = page + 1;
                getData();
            }
        };

        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNo = 1;
                mScrollListener.init();
                getData();
            }
        });
        mRecyclerList.addOnScrollListener(mScrollListener);
        mRecyclerList.setOnItemClickListener(new BaseRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
//                startActivity(new Intent(HomeDataActivity.this, UseUnitDetailActivity.class));
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
//        mSwipeLayout.setRefreshing(true);
        intent = new Intent();
        bundle = new Bundle();
    }

    protected abstract void getData();
}
