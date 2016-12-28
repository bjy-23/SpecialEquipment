package com.wondersgroup.special.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.adapter.StatisticsAdapter;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.datepicker.DatePickerDialog;
import com.wondersgroup.special.entity.StatisticsListResult;
import com.wondersgroup.special.utils.DateUtils;
import com.wondersgroup.special.widget.BaseRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

public class StatisticsUnitActivity extends BaseActivity {
    private BaseRecyclerView mRecyclerView;
    private View mDivider;
    private TextView mUnitDate;
    private DatePickerDialog mDialog;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_analysis_unit);
        mTitle.setText("从业单位统计");
        mRecyclerView = (BaseRecyclerView) findViewById(R.id.recycler);
        mDivider = findViewById(R.id.divider);
        mDivider.setVisibility(View.GONE);
        mUnitDate = (TextView) findViewById(R.id.unit_date);
        mUnitDate.setOnClickListener(this);
        mUnitDate.setText(DateUtils.getYesterday());
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getData();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.unit_date:
                String[] arr = DateUtils.getStrSplit(mUnitDate.getText().toString(), "-");
                if (null == mDialog || (mDialog != null && !mDialog.isShowing())) {
                    mDialog = new DatePickerDialog.Builder(StatisticsUnitActivity.this).setDayOff(false)
                            .setDate(Integer.valueOf(arr[0]), Integer.valueOf(arr[1]), Integer.valueOf(arr[2]))
                            .setNegativeButton("取消", null)
                            .setPositiveButton("确定", new DatePickerDialog.Builder.OnDatePickListener() {
                                @Override
                                public void onDatePick(DialogInterface dialog, int witch, int... args) {
                                    mUnitDate.setText(args[0] + "-" + args[1] + "-" + args[2]);
                                    getData();
                                }
                            }).create();
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                }
                break;
            default:
                break;
        }
    }

    private void getData() {
        OkHttpUtils.get()
                .url(UrlConstant.START_EUNIT)
                .addParams("endDate", mUnitDate.getText().toString())
                .build()
                .execute(new ResponseCallBack<StatisticsListResult>() {
                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                        showLoadingDialog();
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                        dismissLoadingDialog();
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        super.onError(call, e);
                        showMsg(e.getMessage());
                    }

                    @Override
                    public void onResponse(List<StatisticsListResult> t) {
                        super.onResponse(t);
                        if (null != t) {
                            mDivider.setVisibility(View.VISIBLE);
                            List<StatisticsListResult> data = new ArrayList<>();
                            StatisticsListResult result = new StatisticsListResult();
                            result.setAreaName("区域名");
                            result.setInspUnitNum("检验检测机构");
                            result.setProUnitNum("生产单位");
                            result.setTotalUnitNum("在用和停用设备的使用单位");
                            data.add(result);
                            data.addAll(t);
                            mRecyclerView.setAdapter(new StatisticsAdapter(StatisticsUnitActivity.this, data));
                        }
                    }
                });
    }
}
