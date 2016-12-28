package com.wondersgroup.special.homepage;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.activity.BaseActivity;
import com.wondersgroup.special.adapter.DataAdapter;
import com.wondersgroup.special.constant.Constant;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.entity.DataInfo;
import com.wondersgroup.special.utils.EndlessRecyclerViewScrollListener;
import com.wondersgroup.special.widget.BaseRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;

public class HomeDataActivity extends BaseActivity {
    private String url;
    private TextView mCount;
    private BaseRecyclerView mRecyclerList;
    private SwipeRefreshLayout mSwipeLayout;
    private String mHomeDataType;
    private String mParentType;
    private String mChildType;
    private String uuid;
    private String mAreaType;
    private String mStartYear, mEndYear;
    private Map<String, String> params;
    private int pageNo = 1;
    private DataAdapter adapter;
    private EndlessRecyclerViewScrollListener mScrollListener;

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
        url = UrlConstant.QUERY_DATA_INFO;
        Bundle bundle = getIntent().getExtras();
        uuid = bundle.getString("UUID");
        mAreaType = bundle.getString("AREA_TYPE");
        mHomeDataType = TextUtils.isEmpty(bundle.getString("HOME_DATA_TYPE")) ? "" : bundle.getString("HOME_DATA_TYPE");
        mParentType = bundle.getString("PARENT_TYPE");
        mChildType = bundle.getString("CHILD_TYPE");
        mStartYear = bundle.getString("START_YEAR");
        mEndYear = bundle.getString("END_YEAR");
        mStartYear = bundle.getString("START_YEAR");
        params = new HashMap<>();
        mSwipeLayout.setRefreshing(true);
        getData();
    }

    private void showTitle() {
        if (mChildType.equals(Constant.DataInfo.MANAGE_NO)) {
            mTitle.setText("单元列表");
        } else if (mChildType.equals(Constant.DataInfo.EQUIPMENT_NO) || mChildType.equals(Constant.DataInfo.CHECK_EQU_NO)) {
            mTitle.setText("设备列表");
        } else {
            mTitle.setText("单位列表");
        }
    }


    private void getData() {
        switch (mHomeDataType) {
            case Constant.HomeDateType.TYPE_DEVICE_OVERVIEW:
                mChildType = Constant.DataInfo.EQUIPMENT_NO;
                url = UrlConstant.QUERY_DATA_INFO_BY_EQUIP_OVERVIEW;
                params.put("type", uuid);
                params.put("startDate", mStartYear);
                params.put("endDate", mEndYear);
                break;
            case Constant.HomeDateType.TYPE_DEVICE_USEINFO:
                url = UrlConstant.QUERY_DATA_INFO_BY_USE_AREA;
                if (mAreaType.equals(Constant.AreaType.TYPE_EQU)) {
                    mChildType = Constant.DataInfo.EQUIPMENT_NO;
                } else {
                    mChildType = Constant.DataInfo.USE_NO;
                }
                params.put("type", mAreaType);
                params.put("organId", uuid);
                params.put("startDate", mStartYear);
                params.put("endDate", mEndYear);
                break;
            case Constant.HomeDateType.TYPE_ELEVATOR_USE:
                mChildType = Constant.DataInfo.EQUIPMENT_NO;
                url = UrlConstant.QUERY_DATA_INFO_BY_ELEVATOR_AREA;
                params.put("type", uuid);
                params.put("startDate", mStartYear);
                params.put("endDate", mEndYear);
                break;
            case Constant.HomeDateType.TYPE_EMERGENCY:
                mChildType = Constant.DataInfo.EQUIPMENT_NO;
                url = UrlConstant.QUERY_DATA_INFO_BY_BURST;
                params.put("type", uuid);
                params.put("endYear", mEndYear);
                break;
            default:
                params.put("type", mParentType);
                params.put("orderNum", mChildType);
                if (!TextUtils.isEmpty(mStartYear))
                    params.put("startDate", mStartYear);
                if (!TextUtils.isEmpty(mEndYear))
                    params.put("endDate", mEndYear);
                break;
        }
        showTitle();
        params.put("pageNo", String.valueOf(pageNo));
        params.put("pageSize", "10");
        OkHttpUtils.get().url(url)
                .params(params)
                .build()
                .execute(new ResponseCallBack<DataInfo>() {
                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                        mSwipeLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        super.onError(call, e);
                        showMsg(e.getMessage());
                    }

                    @Override
                    public void onResponse(DataInfo dataInfo) {
                        super.onResponse(dataInfo);
                        handleData(dataInfo);
                    }
                });
    }

    public void handleData(DataInfo dataInfo) {
        if (dataInfo != null) {
            mCount.setText(String.format(getResources().getString(R.string.total_result), dataInfo.getTotalRecord()));
            if (pageNo == 1) {
                if (dataInfo.getResultList().size() == 0) {
                    showMsg("未查询到信息");
                }
                adapter = new DataAdapter(HomeDataActivity.this, dataInfo.getResultList(), mChildType);
                mRecyclerList.setAdapter(adapter);
            } else {
                adapter.addItems(dataInfo.getResultList());
            }
        }
    }
}
