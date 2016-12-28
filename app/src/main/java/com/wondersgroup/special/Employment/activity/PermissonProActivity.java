package com.wondersgroup.special.employment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wondersgroup.special.employment.adapter.PermissionProAdapter;
import com.wondersgroup.special.employment.bean.EPermissionProInfo;
import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.activity.BaseActivity;
import com.wondersgroup.special.common.RecyclerBaseAdapter;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.widget.MyItemDecoration;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjy on 2016/12/22.
 */

public class PermissonProActivity extends BaseActivity implements RecyclerBaseAdapter.OnItemClickListener{
    private RecyclerView recyclerView;
    private ArrayList<EPermissionProInfo> datas;
    private PermissionProAdapter adapter;
    private String uuid;
    @Override
    protected void initView() {
        context = this;
        setContentView(R.layout.activity_employment_expand);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new MyItemDecoration(context,MyItemDecoration.VERTICAL_LIST));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mTitle.setText("许可项目信息");
        uuid = getIntent().getStringExtra("uuid");
        datas = new ArrayList<>();
        adapter = new PermissionProAdapter(context,datas);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);

        OkHttpUtils.get()
                .url(UrlConstant.QUERY_PERSON_CERT_PRO_INFO)
                .addParams("uuid",uuid)
                .build()
                .execute(new ResponseCallBack<EPermissionProInfo>() {
                    @Override
                    public void onResponse(List<EPermissionProInfo> t) {
                        super.onResponse(t);
                        datas.addAll(t);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(context,DetailActivity.class);
        intent.putExtra("uuid",datas.get(position).getUuid());
        intent.putExtra("type","22");
        startActivity(intent);
    }
}
