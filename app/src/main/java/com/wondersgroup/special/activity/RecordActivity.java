package com.wondersgroup.special.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.adapter.QueryPersonLicenseAdapter;
import com.wondersgroup.special.adapter.QueryUnitLicenseAdapter;
import com.wondersgroup.special.adapter.QueryUnitUnitAdapter;
import com.wondersgroup.special.archive.ArchiveDetailActivity;
import com.wondersgroup.special.common.RecyclerBaseAdapter;
import com.wondersgroup.special.constant.ArchiveConstant;
import com.wondersgroup.special.constant.Constant;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.entity.PersonLicenseResult;
import com.wondersgroup.special.entity.UnitLicenseResult;
import com.wondersgroup.special.entity.UnitPermissionEnitity;
import com.wondersgroup.special.entity.UnitPermissionResult;
import com.wondersgroup.special.entity.UnitUnitResult;
import com.wondersgroup.special.utils.EndlessRecyclerViewScrollListener;
import com.wondersgroup.special.widget.MyItemDecoration;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by bjy on 2016/12/1.
 */

public class RecordActivity extends BaseActivity implements RecyclerBaseAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeLayout;
    private int pageNo = 1;
    private String pageSize = "10";
    private TextView count;
    private EndlessRecyclerViewScrollListener mScrollListener;
    private ArrayList datas;
    private HashMap<String, String> params;
    private String URL;
    private QueryUnitLicenseAdapter adapter1;
    private QueryPersonLicenseAdapter adapter2;
    private QueryUnitUnitAdapter adapter3;
    private int recordType;
    public final static int TYPE_1 = 1;
    public final static int TYPE_2 = 2;
    public final static int TYPE_3 = 3;
    private String[] titles = {"", "单位许可列表", "人员许可列表", "管理单元列表"};
    private String mParentType;

    @Override
    protected void initView() {
        recordType = getIntent().getIntExtra("type", 0);
        params = (HashMap<String, String>) getIntent().getSerializableExtra("params");
        mTitle.setText(titles[recordType]);
        datas = new ArrayList();

        setContentView(R.layout.activity_record);
        count = (TextView) findViewById(R.id.count);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecordActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new MyItemDecoration(RecordActivity.this, MyItemDecoration.VERTICAL_LIST));

        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeLayout.setColorSchemeColors(Color.RED, Color.BLUE);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        mSwipeLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        mScrollListener = new EndlessRecyclerViewScrollListener((LinearLayoutManager) recyclerView.getLayoutManager()) {
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
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

//        switch (recordType) {
//            case TYPE_1:
//                UnitLicenseResult result1 = (UnitLicenseResult) getIntent().getSerializableExtra("datas");
//                count.setText(String.format(getResources().getString(R.string.total_result), result1.getTotalRecord()));
//                datas.addAll(result1.getResultList());
//                adapter1 = new QueryUnitLicenseAdapter(datas, RecordActivity.this);
//                recyclerView.setAdapter(adapter1);
//                break;
//            case TYPE_2:
//                PersonLicenseResult result2 = (PersonLicenseResult) getIntent().getSerializableExtra("datas");
//                count.setText(String.format(getResources().getString(R.string.total_result), result2.getTotalRecord()));
//                datas.addAll(result2.getResultList());
//                adapter2 = new QueryPersonLicenseAdapter(RecordActivity.this, datas);
//                recyclerView.setAdapter(adapter2);
//                break;
//            case TYPE_3:
//                UnitUnitResult result3 = (UnitUnitResult) getIntent().getSerializableExtra("datas");
//                count.setText(String.format(getResources().getString(R.string.total_result), result3.getTotalRecord()));
//                datas.addAll(result3.getResultList());
//                adapter3 = new QueryUnitUnitAdapter(RecordActivity.this, datas);
//                recyclerView.setAdapter(adapter3);
//                break;
//        }
        mParentType = getIntent().getStringExtra(Params.PARENT_TYPE);
        switch (recordType) {
            case TYPE_1:
                URL = UrlConstant.QUERY_UNIT_LICENSE;
                break;
            case TYPE_2:
                URL = UrlConstant.QUERY_PERSON_LICENSE;
                break;
            case TYPE_3:
                URL = UrlConstant.QUERY_UNIT_UNIT;
                break;
        }
        if (!TextUtils.isEmpty(mParentType)) {
            switch (mParentType) {
                case Constant.Overview.HOME_PAGE:
                    URL = UrlConstant.QUERY_DATA_INFO;
                    break;
            }
        }
        getData();
    }

    public void getData() {
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize);
        switch (recordType) {
            case TYPE_1:

                OkHttpUtils.get()
                        .url(URL)
                        .params(params)
                        .build()
                        .execute(new ResponseCallBack<UnitPermissionResult>() {
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
                            public void onResponse(UnitPermissionResult unitLicenseResult) {
                                super.onResponse(unitLicenseResult);
                                if (null != unitLicenseResult) {
                                    count.setText(String.format(getResources().getString(R.string.total_result), unitLicenseResult.getTotalRecord()));
                                    if (pageNo == 1) {
                                        datas.clear();
                                        datas.addAll(unitLicenseResult.getResultList());
                                        adapter1 = new QueryUnitLicenseAdapter(datas, RecordActivity.this);
                                        recyclerView.setAdapter(adapter1);
                                        adapter1.setOnItemClickListener(RecordActivity.this);
                                    } else {
                                        datas.addAll(unitLicenseResult.getResultList());
                                        adapter1.notifyDataSetChanged();
                                    }
                                }
                            }
                        });
                break;
            case TYPE_2:

                OkHttpUtils.get()
                        .url(URL)
                        .params(params)
                        .build()
                        .execute(new ResponseCallBack<PersonLicenseResult>() {
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
                            public void onResponse(PersonLicenseResult personLicenseResult) {
                                super.onResponse(personLicenseResult);
                                if (null != personLicenseResult) {
                                    count.setText(String.format(getResources().getString(R.string.total_result), personLicenseResult.getTotalRecord()));
                                    if (pageNo == 1) {
                                        datas.clear();
                                        datas.addAll(personLicenseResult.getResultList());
                                        adapter2 = new QueryPersonLicenseAdapter(RecordActivity.this, datas);
                                        recyclerView.setAdapter(adapter2);
                                        adapter2.setOnItemClickListener(RecordActivity.this);
                                    } else {
                                        datas.addAll(personLicenseResult.getResultList());
                                        adapter2.notifyDataSetChanged();
                                    }
                                }
                            }
                        });
                break;
            case TYPE_3:

                OkHttpUtils.get()
                        .url(URL)
                        .params(params)
                        .build()
                        .execute(new ResponseCallBack<UnitUnitResult>() {
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
                            public void onResponse(UnitUnitResult unitUnitResult) {
                                super.onResponse(unitUnitResult);
                                if (null != unitUnitResult) {
                                    count.setText(String.format(getResources().getString(R.string.total_result), unitUnitResult.getTotalRecord()));
                                    if (pageNo == 1) {
                                        datas.clear();
                                        datas.addAll(unitUnitResult.getResultList());
                                        adapter3 = new QueryUnitUnitAdapter(RecordActivity.this, datas);
                                        recyclerView.setAdapter(adapter3);
                                        adapter3.setOnItemClickListener(RecordActivity.this);
                                    } else {
                                        datas.addAll(unitUnitResult.getResultList());
                                        adapter3.notifyDataSetChanged();
                                    }
                                }
                            }
                        });
                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (recordType) {
            case TYPE_1:
                bundle.putSerializable(Params.DATA, adapter1.getItem(position));
                intent.setClass(RecordActivity.this, EntPermitDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case TYPE_2:
                break;
            case TYPE_3:
                bundle.putString(Params.UUID, adapter3.getItem(position).getUuid());
                bundle.putString(Params.TYPE, ArchiveConstant.MANAGE);
                intent.setClass(RecordActivity.this, ArchiveDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}
