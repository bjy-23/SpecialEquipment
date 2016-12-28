package com.wondersgroup.special.activity.unit;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.activity.BaseRecyclerActivity;
import com.wondersgroup.special.adapter.UseUnitAdapter;
import com.wondersgroup.special.constant.Constant;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.entity.UseListResult;
import com.wondersgroup.special.widget.BaseRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Request;

public class UseUnitListActivity extends BaseRecyclerActivity {
    private HashMap<String, String> params;
    private UseUnitAdapter adapter;
    private String url;

    @Override
    protected void initView() {
        super.initView();
        mTitle.setText("从业单位列表");
        mRecyclerList.setOnItemClickListener(new BaseRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                bundle.putSerializable("DATA", adapter.getItem(position));
                intent.putExtras(bundle);
                intent.setClass(UseUnitListActivity.this, UseUnitDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        url = UrlConstant.QUERY_UNIT_INFO;
        params = (HashMap<String, String>) getIntent().getExtras().getSerializable("PARAMS");
        String type = getIntent().getExtras().getString(Params.PARENT_TYPE);
        if (!TextUtils.isEmpty(type)) {
            switch (type) {
                case Constant.Overview.HOME_PAGE:
                    url = UrlConstant.QUERY_DATA_INFO;
                    break;
                case Constant.HomeDateType.TYPE_DEVICE_USEINFO:
                    url = UrlConstant.QUERY_DATA_INFO_BY_USE_AREA;
                    break;
            }
        }
        getData();
    }

    @Override
    protected void getData() {
        params.put("pageNo", String.valueOf(pageNo));
        params.put("pageSize", "10");
        OkHttpUtils.get().url(url).params(params).build().execute(new ResponseCallBack<UseListResult>() {
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
            public void onResponse(UseListResult t) {
                super.onResponse(t);
                if (null != t) {
                    mCount.setText(String.format(getResources().getString(R.string.total_result), t.getTotalRecord()));
                    if (pageNo == 1) {
                        if (t.getResultList() == null
                                || (t.getResultList() != null
                                && t.getResultList().size() == 0)) {
                            showMsg("未查询到信息");
                        }
                        adapter = new UseUnitAdapter(UseUnitListActivity.this, t.getResultList());
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
