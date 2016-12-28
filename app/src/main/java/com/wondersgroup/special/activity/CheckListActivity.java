package com.wondersgroup.special.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.adapter.CheckoutAdapter;
import com.wondersgroup.special.archive.ArchiveDetailActivity;
import com.wondersgroup.special.constant.ArchiveConstant;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.entity.CheckoutListResult;
import com.wondersgroup.special.widget.BaseRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Request;

public class CheckListActivity extends BaseRecyclerActivity {
    private HashMap<String, String> params;
    private CheckoutAdapter adapter;

    @Override
    protected void initView() {
        super.initView();
        mTitle.setText("检验档案列表");
        mRecyclerList.setOnItemClickListener(new BaseRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                bundle.putSerializable(Params.DATA, adapter.getItem(position));
                intent.putExtras(bundle);
                intent.setClass(CheckListActivity.this, CheckDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        params = (HashMap<String, String>) getIntent().getExtras().getSerializable(Params.DATA);
        getData();
    }

    @Override
    protected void getData() {
        params.put("pageNo", String.valueOf(pageNo));
        params.put("pageSize", "10");
        OkHttpUtils.get().url(UrlConstant.QUERY_CHECKOUT_INFO).params(params).build().execute(new ResponseCallBack<CheckoutListResult>() {
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
            public void onResponse(CheckoutListResult t) {
                super.onResponse(t);
                if (null != t) {
                    mCount.setText(String.format(getResources().getString(R.string.total_result), t.getTotalRecord()));
                    if (pageNo == 1) {
                        if (t.getResultList() == null
                                || (t.getResultList() != null
                                && t.getResultList().size() == 0)) {
                            showMsg("未查询到信息");
                        }
                        adapter = new CheckoutAdapter(CheckListActivity.this, t.getResultList());
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