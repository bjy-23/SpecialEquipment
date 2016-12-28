package com.wondersgroup.special.employment.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wondersgroup.special.employment.adapter.ExpanChangeAdapter;
import com.wondersgroup.special.employment.adapter.ExpandCheckAdapter;
import com.wondersgroup.special.employment.adapter.ExpandPermissionAdapter;
import com.wondersgroup.special.employment.adapter.ExpandRewardAdapter;
import com.wondersgroup.special.employment.adapter.ExpandTrainAdapter;
import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.employment.adapter.ExpandWorkAdapter;
import com.wondersgroup.special.activity.BaseActivity;
import com.wondersgroup.special.common.RecyclerBaseAdapter;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.employment.bean.ECheckInfo;
import com.wondersgroup.special.employment.bean.ERewardInfo;
import com.wondersgroup.special.employment.bean.EPermissionInfo;
import com.wondersgroup.special.employment.bean.EChangeInfo;
import com.wondersgroup.special.employment.bean.ETrainInfo;
import com.wondersgroup.special.employment.bean.EWorkInfo;
import com.wondersgroup.special.widget.MyItemDecoration;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjy on 2016/12/20.
 */

public class EmploymentExpandActivity extends BaseActivity implements RecyclerBaseAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private RecyclerBaseAdapter adapter;
    private ArrayList<EWorkInfo> datas1;
    private ArrayList<EPermissionInfo> datas2;
    private ArrayList<ERewardInfo> datas3;
    private ArrayList<ETrainInfo> datas4;
    private ArrayList<ECheckInfo> datas5;
    private ArrayList<EChangeInfo> datas6;
    private String uuid;
    private String type;
    private Context context;
    private RequestCall call;

    @Override
    protected void initView() {
        context = this;
        setContentView(R.layout.activity_employment_expand);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new MyItemDecoration(context, MyItemDecoration.VERTICAL_LIST));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        uuid = getIntent().getStringExtra("uuid");
        type = getIntent().getStringExtra("type");
        switch (type) {
            case "1":
                mTitle.setText("工作履历");
                datas1 = new ArrayList<EWorkInfo>();
                adapter = new ExpandWorkAdapter(context, datas1);
                break;
            case "2":
                mTitle.setText("许可信息");
                datas2 = new ArrayList<EPermissionInfo>();
                adapter = new ExpandPermissionAdapter(context, datas2);
                break;
            case "3":
                mTitle.setText("奖惩信息");
                datas3 = new ArrayList<ERewardInfo>();
                adapter = new ExpandRewardAdapter(context, datas3);
                break;
            case "4":
                mTitle.setText("培训记录");
                datas4 = new ArrayList<ETrainInfo>();
                adapter = new ExpandTrainAdapter(context, datas4);
                break;
            case "5":
                mTitle.setText("考核信息");
                datas5 = new ArrayList<ECheckInfo>();
                adapter = new ExpandCheckAdapter(context, datas5);
                break;
            case "6":
                mTitle.setText("变更信息");
                datas6 = new ArrayList<EChangeInfo>();
                adapter = new ExpanChangeAdapter(context, datas6);
        }
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);

        call = OkHttpUtils.get()
                .url(UrlConstant.QUERY_PERSON_EXPAND_INFO)
                .addParams("uuid", uuid)
                .addParams("type", type + "")
                .build();

        switch (type) {
            case "1":
                getWorkInfo();
                break;
            case "2":
                getPermissionInfo();
                break;
            case "3":
                getRewardInfo();
                break;
            case "4":
                getTrainInfo();
                break;
            case "5":
                getCheckInfo();
                break;
            case "6":
                getChangeInfo();
                break;
        }
    }

    public void getWorkInfo() {
        call.execute(new ResponseCallBack<EWorkInfo>() {
            @Override
            public void onResponse(List<EWorkInfo> t) {
                super.onResponse(t);
                datas1.addAll(t);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void getPermissionInfo() {
        call.execute(new ResponseCallBack<EPermissionInfo>() {
            @Override
            public void onResponse(List<EPermissionInfo> t) {
                super.onResponse(t);
                datas2.addAll(t);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void getRewardInfo() {
        call.execute(new ResponseCallBack<ERewardInfo>() {
            @Override
            public void onResponse(List<ERewardInfo> t) {
                super.onResponse(t);
                datas3.addAll(t);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void getTrainInfo() {
        call.execute(new ResponseCallBack<ETrainInfo>() {
            @Override
            public void onResponse(List<ETrainInfo> t) {
                super.onResponse(t);
                datas4.addAll(t);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void getCheckInfo() {
        call.execute(new ResponseCallBack<ECheckInfo>() {
            @Override
            public void onResponse(List<ECheckInfo> t) {
                super.onResponse(t);
                datas5.addAll(t);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void getChangeInfo() {
        call.execute(new ResponseCallBack<EChangeInfo>() {
            @Override
            public void onResponse(List<EChangeInfo> t) {
                super.onResponse(t);
                datas6.addAll(t);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("type", type + "");
        switch (type) {
            case "1":
                intent.putExtra("uuid", datas1.get(position).getUuid());
                break;
            case "2":
                intent.putExtra("uuid", datas2.get(position).getUuid());
                intent.putExtra("certType", datas2.get(position).getCertType());
                intent.putExtra("type", "21");
                break;
            case "3":
                intent.putExtra("uuid", datas3.get(position).getUuid());
                break;
            case "4":
                intent.putExtra("uuid", datas4.get(position).getUuid());
                break;
            case "5":
                intent.putExtra("uuid", datas5.get(position).getUuid());
                break;
            case "6":
                intent.putExtra("uuid", datas6.get(position).getUuid());
                break;
        }
        startActivity(intent);
    }
}
