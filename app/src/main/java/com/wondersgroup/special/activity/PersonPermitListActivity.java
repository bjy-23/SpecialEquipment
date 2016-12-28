package com.wondersgroup.special.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wondersgroup.special.employment.activity.EmploymentDetailActivity;
import com.wondersgroup.special.R;
import com.wondersgroup.special.utils.EndlessRecyclerViewScrollListener;
import com.wondersgroup.special.widget.BaseRecyclerView;

public class PersonPermitListActivity extends BaseActivity {
    private TextView mCount;
    private BaseRecyclerView mRecyclerList;
    private SwipeRefreshLayout mSwipeLayout;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_use_unit_list);
        mTitle.setText("人员列表");
        mCount = (TextView) findViewById(R.id.count);
        mRecyclerList = (BaseRecyclerView) findViewById(R.id.recycler);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeLayout.setColorSchemeColors(Color.RED, Color.BLUE);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
//        mRecyclerList.setAdapter(new PersonPermitAdapter(this));
        mRecyclerList.setOnItemClickListener(new BaseRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                startActivity(new Intent(PersonPermitListActivity.this, EmploymentDetailActivity.class));
            }
        });
        mRecyclerList.addOnScrollListener(new EndlessRecyclerViewScrollListener((LinearLayoutManager) mRecyclerList.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                getData();
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    private void getData(){

    }
}