package com.wondersgroup.special.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.adapter.DetailAdapter;
import com.wondersgroup.special.archive.ArchiveDetailActivity;
import com.wondersgroup.special.constant.ArchiveConstant;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.entity.UnitAwardResult;
import com.wondersgroup.special.widget.BaseRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

/**
 * 企业获奖信息列表
 */
public class UnitAwardActivity extends BaseActivity {
    protected TextView mCount;
    protected BaseRecyclerView mRecyclerList;
    protected SwipeRefreshLayout mSwipeLayout;
    private HashMap<String, String> params;
    private DetailAdapter adapter;
    private ArrayList<LinkedHashMap<String, String>> data;
    private List<UnitAwardResult> result;
    private Intent intent;
    private Bundle bundle;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_use_unit_list);
        mTitle.setText("企业获奖情况列表");
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
        params = (HashMap<String, String>) getIntent().getExtras().getSerializable(Params.DATA);
        mSwipeLayout.setRefreshing(true);
        data = new ArrayList<>();
        result = new ArrayList<>();
        intent = new Intent();
        bundle = new Bundle();
        mRecyclerList.setOnItemClickListener(new BaseRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                if (result.size() != 0) {
                    bundle.putString(Params.UUID, result.get(position).getUuid());
                    bundle.putString(Params.TYPE, ArchiveConstant.UNIT_AWARD);
                    intent.putExtras(bundle);
                    intent.setClass(UnitAwardActivity.this, ArchiveDetailActivity.class);
                    startActivity(intent);
                }
            }
        });
        getData();
    }

    protected void getData() {
        OkHttpUtils.get().url(UrlConstant.QUERY_UNIT_AWARD).params(params).build().execute(new ResponseCallBack<UnitAwardResult>() {
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
            public void onResponse(List<UnitAwardResult> t) {
                super.onResponse(t);
                if (null != t) {
                    if (t.size() == 0) {
                        showMsg("未查询到信息");
                    }
                    result = t;
                    handleData(t);
                    adapter = new DetailAdapter(UnitAwardActivity.this, data);
                    mRecyclerList.setAdapter(adapter);
                }
            }

            @Override
            public void onError(Call call, Exception e) {
                super.onError(call, e);
                showMsg(e.getMessage());
            }
        });
    }

    private void handleData(List<UnitAwardResult> t) {
        data.clear();
        for (UnitAwardResult result : t) {
            data.add(getMap(result));
        }
    }

    private LinkedHashMap<String, String> getMap(UnitAwardResult result) {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("单位名称", result.getUnitName());
        data.put("成果名称", result.getAchievement());
        data.put("项目名称", result.getProName());
        data.put("获奖情况", result.getAwardStatus());
        data.put("受理局", result.getAcceptOrgan());
        return data;
    }
}
