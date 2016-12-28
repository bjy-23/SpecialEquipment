package com.wondersgroup.special.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.activity.unit.UseUnitListActivity;
import com.wondersgroup.special.constant.Constant;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.datepicker.DatePickerDialog;
import com.wondersgroup.special.datepicker.SectionDatePickerDialog;
import com.wondersgroup.special.entity.SectionOverviewResult;
import com.wondersgroup.special.entity.WholeOverviewResult;
import com.wondersgroup.special.homepage.HomeDataActivity;
import com.wondersgroup.special.utils.DateUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Request;

public class OverviewActivity extends BaseActivity {
    private String type;
    private TextView mTagEquipment, mTagUse, mTagManage, mTagOverdue;
    private TextView mOverviewDate, mTextEquipmentNo, mTextUseUnitNo, mTextManagementUnitNo,
            mTextOverdueRate;
    private TextView mIntervalStartDate, mIntervalEndDate, mTextCheck, mTextCheckEquipment, mTextHiddenRectificationRate, mTextAccident;
    private DatePickerDialog mDialog;
    private SectionDatePickerDialog mSectionDialog;
    private LinearLayout mLinearSection;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_overview);
        mTitle.setText("概览");
        mLinearSection = (LinearLayout) findViewById(R.id.linear_section);
        mLinearSection.setOnClickListener(this);
        mTagEquipment = (TextView) findViewById(R.id.text_tag_equipment);
        mTagUse = (TextView) findViewById(R.id.text_tag_use);
        mTagManage = (TextView) findViewById(R.id.text_tag_manage);
        mTagOverdue = (TextView) findViewById(R.id.text_tag_overdue);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        type = getIntent().getStringExtra("TYPE");
        type = TextUtils.isEmpty(type) ? "" : type;
        if (type.equals(Constant.Overview.CYLINDER)) {
            mTagOverdue.setVisibility(View.GONE);
            mTagEquipment.setText("在用气瓶数：");
            mTagUse.setText("在用气瓶充装单位：");
            mTagManage.setText("气瓶检验机构数：");
        }
        initWholeOverview();
        initSectionOverview();
    }

    /**
     * 加下划线
     *
     * @param tv
     */
    private void addUnderLine(TextView tv) {
        tv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tv.getPaint().setAntiAlias(true);//抗锯齿
        tv.setTextColor(getResources().getColor(R.color.theme));
    }

    private void initWholeOverview() {
        mOverviewDate = (TextView) findViewById(R.id.overall_data_overview_date);
        mOverviewDate.setText(DateUtils.getYesterday());
        mOverviewDate.setOnClickListener(this);
        mTextEquipmentNo = (TextView) findViewById(R.id.text_equipment_no);
//        mTextEquipmentNo.setOnClickListener(this);
        mTextUseUnitNo = (TextView) findViewById(R.id.text_use_unit_no);
//        mTextUseUnitNo.setOnClickListener(this);
        mTextManagementUnitNo = (TextView) findViewById(R.id.text_manage);
//        mTextManagementUnitNo.setOnClickListener(this);
        mTextOverdueRate = (TextView) findViewById(R.id.text_overdue_rate);
        if (type.equals(Constant.Overview.CYLINDER)) {
            mTextOverdueRate.setVisibility(View.GONE);
        }
        addUnderLine(mTextEquipmentNo);
        addUnderLine(mTextUseUnitNo);
        addUnderLine(mTextManagementUnitNo);
        findViewById(R.id.linear_equipment_no).setOnClickListener(this);
        findViewById(R.id.linear_use).setOnClickListener(this);
        findViewById(R.id.linear_manage).setOnClickListener(this);
        getWholeOverviewData();
    }

    /**
     * 初始化区间数据概览
     */
    private void initSectionOverview() {
        mIntervalStartDate = (TextView) findViewById(R.id.interval_start_date);
        mIntervalStartDate.setText(DateUtils.getFirstDay());
        mIntervalEndDate = (TextView) findViewById(R.id.interval_end_date);
        mIntervalEndDate.setText(DateUtils.getToday());
        mTextCheck = (TextView) findViewById(R.id.text_check);
        mTextCheck.setOnClickListener(this);
        mTextCheckEquipment = (TextView) findViewById(R.id.text_check_equipment);
//        mTextCheckEquipment.setOnClickListener(this);
        mTextHiddenRectificationRate = (TextView) findViewById(R.id.text_hidden_rectification_rate);
        mTextAccident = (TextView) findViewById(R.id.text_accident);
        mTextAccident.setOnClickListener(this);
        addUnderLine(mTextCheck);
        addUnderLine(mTextCheckEquipment);
        addUnderLine(mTextAccident);
        findViewById(R.id.linear_check_equ).setOnClickListener(this);
        getSectionOverviewData();
    }

    /**
     * 获取整体数据概览
     */
    private void getWholeOverviewData() {
        OkHttpUtils.get().url(UrlConstant.GET_WHOLE_OVERVIEW)
                .addParams("type", type)
                .addParams("endDate", mOverviewDate.getText().toString())
                .build()
                .execute(new ResponseCallBack<WholeOverviewResult>() {
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
                    public void onResponse(WholeOverviewResult result) {
                        super.onResponse(result);
                        if (null != result) {
                            mTextEquipmentNo.setText(result.getUseNum() + "台");
                            mTextUseUnitNo.setText(result.getComNum() + "家");
                            mTextManagementUnitNo.setText(result.getUnitNum() + "户");
                            if (type.equals(Constant.Overview.CYLINDER)) {
                                mTextManagementUnitNo.setText(result.getUnitNum() + "家");
                            }
                            mTextOverdueRate.setText((TextUtils.isEmpty(result.getRate()) ? "0" : result.getRate()) + "%");
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        super.onError(call, e);
                        mTextEquipmentNo.setText("0台");
                        mTextUseUnitNo.setText("0家");
                        mTextManagementUnitNo.setText("0户");
                        if (type.equals(Constant.Overview.CYLINDER)) {
                            mTextManagementUnitNo.setText("0家");
                        }
                        mTextOverdueRate.setText("0%");
                    }
                });
    }

    /**
     * 获取区间数据概览
     */
    private void getSectionOverviewData() {
        OkHttpUtils.get().url(UrlConstant.GET_SECTION_OVERVIEW)
                .addParams("type", type)
                .addParams("startDate", mIntervalStartDate.getText().toString())
                .addParams("endDate", mIntervalEndDate.getText().toString())
                .build()
                .execute(new ResponseCallBack<SectionOverviewResult>() {
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
                    public void onResponse(SectionOverviewResult result) {
                        super.onResponse(result);
                        if (null != result) {
                            mTextCheck.setText((TextUtils.isEmpty(result.getCheckNum()) ? "0" : result.getCheckNum()) + "家");
                            mTextCheckEquipment.setText((TextUtils.isEmpty(result.getEquipNum()) ? "0" : result.getEquipNum()) + "台");
                            mTextHiddenRectificationRate.setText((TextUtils.isEmpty(result.getRate()) ? "0" : result.getRate()) + "%");
                            mTextAccident.setText((TextUtils.isEmpty(result.getAccidentNum()) ? "0" : result.getAccidentNum()) + "件");
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        super.onError(call, e);
                        mTextCheck.setText("0家");
                        mTextCheckEquipment.setText("0台");
                        mTextAccident.setText("0件");
                        mTextHiddenRectificationRate.setText("0%");
                    }
                });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        HashMap<String, String> params = new HashMap<>();
        switch (v.getId()) {
            case R.id.linear_equipment_no:
                params.put("type", type);
                params.put("orderNum", Constant.DataInfo.EQUIPMENT_NO);
                params.put("endDate", mOverviewDate.getText().toString());
                bundle.putSerializable("PARAMS", params);
                bundle.putString(Params.PARENT_TYPE, Constant.Overview.HOME_PAGE);
                intent.setClass(this, EquipmentListActivity.class);
                intent.putExtras(bundle);
//                bundle.putString("PARENT_TYPE", type);
//                bundle.putString("CHILD_TYPE", Constant.DataInfo.EQUIPMENT_NO);
//                bundle.putString("END_YEAR", mOverviewDate.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.linear_use:
                params.put("type", type);
                params.put("orderNum", Constant.DataInfo.USE_NO);
                params.put("endDate", mOverviewDate.getText().toString());
                bundle.putSerializable("PARAMS", params);
                bundle.putString(Params.PARENT_TYPE, Constant.Overview.HOME_PAGE);
                intent.setClass(this, UseUnitListActivity.class);
                intent.putExtras(bundle);
//                bundle.putString("PARENT_TYPE", type);
//                bundle.putString("CHILD_TYPE", Constant.DataInfo.USE_NO);
//                bundle.putString("END_YEAR", mOverviewDate.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.linear_manage:
                params.put("type", type);
                params.put("orderNum", Constant.DataInfo.MANAGE_NO);
                params.put("endDate", mOverviewDate.getText().toString());
                bundle.putSerializable("params", params);
                bundle.putString(Params.PARENT_TYPE, Constant.Overview.HOME_PAGE);
                intent.putExtra("type", 3);
                intent.setClass(this, RecordActivity.class);
                intent.putExtras(bundle);
//                bundle.putString("PARENT_TYPE", type);
//                bundle.putString("CHILD_TYPE", Constant.DataInfo.MANAGE_NO);
//                bundle.putString("END_YEAR", mOverviewDate.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.text_check:
                params.put("type", type);
                params.put("orderNum", Constant.DataInfo.CHECK_NO);
                params.put("startDate",mIntervalStartDate.getText().toString());
                params.put("endDate", mIntervalEndDate.getText().toString());
                bundle.putSerializable("PARAMS", params);
                bundle.putString(Params.PARENT_TYPE, Constant.Overview.HOME_PAGE);
                intent.setClass(this, UseUnitListActivity.class);
                intent.putExtras(bundle);
//                bundle.putString("PARENT_TYPE", type);
//                bundle.putString("CHILD_TYPE", Constant.DataInfo.CHECK_NO);
//                bundle.putString("START_YEAR", mIntervalStartDate.getText().toString());
//                bundle.putString("END_YEAR", mIntervalEndDate.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.linear_check_equ:
                params.put("type", type);
                params.put("orderNum", Constant.DataInfo.CHECK_EQU_NO);
                params.put("startDate",mIntervalStartDate.getText().toString());
                params.put("endDate", mIntervalEndDate.getText().toString());
                bundle.putSerializable("PARAMS", params);
                bundle.putString(Params.PARENT_TYPE, Constant.Overview.HOME_PAGE);
                intent.setClass(this, EquipmentListActivity.class);
                intent.putExtras(bundle);
//                bundle.putString("PARENT_TYPE", type);
//                bundle.putString("CHILD_TYPE", Constant.DataInfo.CHECK_EQU_NO);
//                bundle.putString("START_YEAR", mIntervalStartDate.getText().toString());
//                bundle.putString("END_YEAR", mIntervalEndDate.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.text_accident:
                params.put("type", type);
                params.put("orderNum", Constant.DataInfo.ACCIDENT_NO);
                params.put("startDate",mIntervalStartDate.getText().toString());
                params.put("endDate", mIntervalEndDate.getText().toString());
                bundle.putSerializable(Params.DATA, params);
                bundle.putString(Params.PARENT_TYPE, Constant.Overview.HOME_PAGE);
                intent.setClass(this, AccidentListActivity.class);
                intent.putExtras(bundle);
//                bundle.putString("PARENT_TYPE", type);
//                bundle.putString("CHILD_TYPE", Constant.DataInfo.ACCIDENT_NO);
//                bundle.putString("START_YEAR", mIntervalStartDate.getText().toString());
//                bundle.putString("END_YEAR", mIntervalEndDate.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.overall_data_overview_date:
                String[] arr = DateUtils.getStrSplit(mOverviewDate.getText().toString(), "-");
                if (null == mDialog || (mDialog != null && !mDialog.isShowing())) {
                    mDialog = new DatePickerDialog.Builder(OverviewActivity.this).setDayOff(false)
                            .setDate(Integer.valueOf(arr[0]), Integer.valueOf(arr[1]), Integer.valueOf(arr[2]))
                            .setNegativeButton("取消", null)
                            .setPositiveButton("确定", new DatePickerDialog.Builder.OnDatePickListener() {
                                @Override
                                public void onDatePick(DialogInterface dialog, int witch, int... args) {
                                    mOverviewDate.setText(args[0] + "-" + args[1] + "-" + args[2]);
                                    getWholeOverviewData();
                                }
                            }).create();
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                }
                break;
            case R.id.linear_section:
                String[] arrStart = DateUtils.getStrSplit(mIntervalStartDate.getText().toString(), "-");
                String[] arrEnd = DateUtils.getStrSplit(mIntervalEndDate.getText().toString(), "-");
                if (null == mSectionDialog || (mSectionDialog != null && !mSectionDialog.isShowing())) {
                    mSectionDialog = new SectionDatePickerDialog.Builder(OverviewActivity.this)
                            .setStartDate(Integer.valueOf(arrStart[0]), Integer.valueOf(arrStart[1]), Integer.valueOf(arrStart[2]))
                            .setEndDate(Integer.valueOf(arrEnd[0]), Integer.valueOf(arrEnd[1]), Integer.valueOf(arrEnd[2]))
                            .setNegativeButton("取消", null).setPositiveButton("确定", new SectionDatePickerDialog.Builder.OnDatePickListener() {
                                @Override
                                public void onDatePick(DialogInterface dialog, int witch, int... args) {
                                    mIntervalStartDate.setText(args[0] + "-" + args[1] + "-" + args[2]);
                                    mIntervalEndDate.setText(args[3] + "-" + args[4] + "-" + args[5]);
                                    getSectionOverviewData();
                                }
                            }).create();
                    mSectionDialog.setCanceledOnTouchOutside(false);
                    mSectionDialog.show();
                }
                break;
            default:
                break;
        }
    }
}
