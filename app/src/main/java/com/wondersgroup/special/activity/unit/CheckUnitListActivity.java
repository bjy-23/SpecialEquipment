package com.wondersgroup.special.activity.unit;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.activity.BaseRecyclerActivity;
import com.wondersgroup.special.adapter.CheckUnitAdapter;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.entity.CheckUnitListResult;
import com.wondersgroup.special.widget.BaseRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Request;

public class CheckUnitListActivity extends BaseRecyclerActivity {
    private HashMap<String, String> params;
    private CheckUnitAdapter adapter;

    @Override
    protected void initView() {
        super.initView();
        mTitle.setText("检验检测单位列表");
        mRecyclerList.setOnItemClickListener(new BaseRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                intent.setClass(CheckUnitListActivity.this, CheckUnitDetailActivity.class);
                bundle.putSerializable("DATA", adapter.getItem(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        params = (HashMap<String, String>) getIntent().getExtras().getSerializable("PARAMS");
        getData();
    }

    @Override
    protected void getData() {
        params.put("pageNo", String.valueOf(pageNo));
        params.put("pageSize", "10");
        OkHttpUtils.get().url(UrlConstant.QUERY_CHECK_UNIT).params(params).build().execute(new ResponseCallBack<CheckUnitListResult>() {
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
            public void onResponse(CheckUnitListResult t) {
                super.onResponse(t);
                if (null != t) {
                    mCount.setText(String.format(getResources().getString(R.string.total_result), t.getTotalRecord()));
                    if (pageNo == 1) {
                        if (t.getResultList() == null
                                || (t.getResultList() != null
                                && t.getResultList().size() == 0)) {
                            showMsg("未查询到信息");
                        }
                        adapter = new CheckUnitAdapter(CheckUnitListActivity.this, t.getResultList());
                        mRecyclerList.setAdapter(adapter);
                    } else {
                        adapter.addItems(t.getResultList());
                    }
                }
            }

            @Override
            public void onError(Call call, Exception e) {
                super.onError(call, e);
                showMsg(e.getMessage());
            }
        });
    }
}
