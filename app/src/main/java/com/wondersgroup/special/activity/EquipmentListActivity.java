package com.wondersgroup.special.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.adapter.EquipmentAdapter;
import com.wondersgroup.special.constant.Constant;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.entity.EquipmentListResult;
import com.wondersgroup.special.widget.BaseRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class EquipmentListActivity extends BaseRecyclerActivity {
    private HashMap<String, String> params;
    private EquipmentAdapter adapter;
    private String url;

    @Override
    protected void initView() {
        super.initView();
        mTitle.setText("特种设备列表");
        mRecyclerList.setOnItemClickListener(new BaseRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                Intent intent = new Intent(EquipmentListActivity.this, EquipmentDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Params.DATA, adapter.getItem(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        url = UrlConstant.QUERY_EQUIP_INFO;
        params = (HashMap<String, String>) getIntent().getExtras().getSerializable("PARAMS");
        String type = getIntent().getExtras().getString(Params.PARENT_TYPE);
        if (!TextUtils.isEmpty(type)) {
            switch (type) {
                case Constant.Overview.HOME_PAGE:
                    url = UrlConstant.QUERY_DATA_INFO;
                    break;
                case Constant.HomeDateType.TYPE_EMERGENCY:
                    url = UrlConstant.QUERY_DATA_INFO_BY_BURST;
                    break;
                case Constant.HomeDateType.TYPE_DEVICE_USEINFO:
                    url = UrlConstant.QUERY_DATA_INFO_BY_USE_AREA;
                    break;
                case Constant.HomeDateType.TYPE_ANALYSIS:
                    url = UrlConstant.QUERY_EQUIP_INFO_BY_CHART;
                    break;
            }
        }
        getData();
    }

    @Override
    protected void getData() {
        params.put("pageNo", String.valueOf(pageNo));
        params.put("pageSize", "10");
        OkHttpUtils.get()
                .url(url)
                .params(params)
                .build().execute(new ResponseCallBack<EquipmentListResult>() {

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
            public void onResponse(EquipmentListResult t) {
                super.onResponse(t);
                if (null != t) {
                    mCount.setText(String.format(getResources().getString(R.string.total_result), t.getTotalRecord()));
                    if (pageNo == 1) {
                        if (t.getResultList() == null
                                || (t.getResultList() != null
                                && t.getResultList().size() == 0)) {
                            showMsg("未查询到信息");
                        }
                        adapter = new EquipmentAdapter(EquipmentListActivity.this, t.getResultList());
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
