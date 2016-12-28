package com.wondersgroup.special.archive;

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
import com.wondersgroup.special.adapter.DetailAdapter;
import com.wondersgroup.special.constant.ArchiveConstant;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.entity.CheckoutRecord;
import com.wondersgroup.special.entity.SupervisionContent;
import com.wondersgroup.special.entity.UnitApprovalEntity;
import com.wondersgroup.special.widget.BaseRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

public class ArchiveDetailListActivity extends BaseActivity {
    protected TextView mCount;
    protected BaseRecyclerView mRecyclerList;
    protected SwipeRefreshLayout mSwipeLayout;
    private String uuid, visionId, type, mUnitType;
    private HashMap<String, String> params;
    private DetailAdapter adapter;
    private ArrayList<LinkedHashMap<String, String>> data;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_use_unit_list);
        mCount = (TextView) findViewById(R.id.count);
        mCount.setVisibility(View.GONE);
        mRecyclerList = (BaseRecyclerView) findViewById(R.id.recycler);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeLayout.setColorSchemeColors(Color.RED, Color.BLUE);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        mSwipeLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mSwipeLayout.setRefreshing(true);
        params = new HashMap<>();
        data = new ArrayList<>();
        type = getIntent().getStringExtra(Params.TYPE);
        uuid = getIntent().getStringExtra(Params.UUID);
        visionId = getIntent().getStringExtra(Params.VISION_ID);
        mUnitType = getIntent().getStringExtra(Params.UNIT_TYPE);
        getData();
    }

    protected void getData() {
//        params.put("pageNo", String.valueOf(pageNo));
//        params.put("pageSize", "10");
        if (!TextUtils.isEmpty(type)) {
            switch (type) {
                case ArchiveConstant.UNIT_PERMIT:
                    mTitle.setText("核准项目列表");
                    params.put("uuid", uuid);
                    params.put("certUnitType", mUnitType);
                    getProData();
                    break;
                case ArchiveConstant.Checkout.RECORD:
                    mTitle.setText("检验记录");
                    params.put("visionId", visionId);
                    getCheckoutRecordData();
                    break;
                case ArchiveConstant.Routine.CONTENT:
                    mTitle.setText("检查内容");
                    params.put("uuid", uuid);
                    getSupervisionContentData();
                    break;
            }
        }
    }

    /**
     * 获取核准项目列表
     */
    private void getProData() {
        OkHttpUtils.get().
                url(UrlConstant.GET_LICENSE_PRO)
                .params(params)
                .build()
                .execute(new ResponseCallBack<UnitApprovalEntity>() {
                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                        showLoadingDialog();
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                        mSwipeLayout.setRefreshing(false);
                        dismissLoadingDialog();
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        super.onError(call, e);
                        showMsg(e.getMessage());
                    }

                    @Override
                    public void onResponse(List<UnitApprovalEntity> t) {
                        super.onResponse(t);
                        if (null != t) {
                            if (t.size() == 0) {
                                showMsg("未查询到信息");
                            }
                            HandleProData(t);
                            adapter = new DetailAdapter(ArchiveDetailListActivity.this, data);
                            mRecyclerList.setAdapter(adapter);
                        }
                    }
                });
    }

    /**
     * 处理返回的数据
     */
    private void HandleProData(List<UnitApprovalEntity> resultList) {
        data.clear();
        for (UnitApprovalEntity result : resultList) {
            data.add(EntPermitInfo.productEntList(result));
        }
    }

    /**
     * 检验记录数据
     */
    private void getCheckoutRecordData() {
        OkHttpUtils.get().
                url(UrlConstant.QUERY_CHECKOUT_RECORD)
                .params(params)
                .build()
                .execute(new ResponseCallBack<CheckoutRecord.Checkout>() {
                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                        showLoadingDialog();
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                        mSwipeLayout.setRefreshing(false);
                        dismissLoadingDialog();
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        super.onError(call, e);
                        showMsg(e.getMessage());
                    }

                    @Override
                    public void onResponse(List<CheckoutRecord.Checkout> t) {
                        super.onResponse(t);
                        if (null != t) {
                            if (t.size() == 0) {
                                showMsg("未查询到信息");
                            }
                            HandleCheckoutData(t);
                            adapter = new DetailAdapter(ArchiveDetailListActivity.this, data);
                            mRecyclerList.setAdapter(adapter);
                        }
                    }
                });
    }

    /**
     * 处理返回的数据
     */
    private void HandleCheckoutData(List<CheckoutRecord.Checkout> resultList) {
        data.clear();
        for (CheckoutRecord.Checkout result : resultList) {
            data.add(CheckInfo.recordInfo(result));
        }
    }

    /**
     * 监察档案内容
     */
    private void getSupervisionContentData() {
        OkHttpUtils.get().
                url(UrlConstant.GET_SUPERVISION_CONTENT)
                .params(params)
                .build()
                .execute(new ResponseCallBack<SupervisionContent>() {
                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                        showLoadingDialog();
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                        mSwipeLayout.setRefreshing(false);
                        dismissLoadingDialog();
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        super.onError(call, e);
                        showMsg(e.getMessage());
                    }

                    @Override
                    public void onResponse(List<SupervisionContent> t) {
                        super.onResponse(t);
                        if (null != t) {
                            if (t.size() == 0) {
                                showMsg("未查询到信息");
                            }
                            HandleSupervisionContentData(t);
                            adapter = new DetailAdapter(ArchiveDetailListActivity.this, data);
                            mRecyclerList.setAdapter(adapter);
                        }
                    }
                });
    }

    /**
     * 处理监察档案内容返回的数据
     */
    private void HandleSupervisionContentData(List<SupervisionContent> resultList) {
        data.clear();
        for (SupervisionContent result : resultList) {
            data.add(RoutineInfo.checkContentList(result));
        }
    }

}
