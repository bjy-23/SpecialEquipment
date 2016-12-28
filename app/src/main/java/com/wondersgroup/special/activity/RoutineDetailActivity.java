package com.wondersgroup.special.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.archive.ArchiveDetailActivity;
import com.wondersgroup.special.archive.ArchiveDetailListActivity;
import com.wondersgroup.special.constant.ArchiveConstant;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.entity.SupervisionListResult;

import java.util.HashMap;

/**
 * 监察档案
 */
public class RoutineDetailActivity extends BaseActivity {
    private View mBaseInfo, mCheckOverview, mCheckSituation, mCheckContent, mCodeBook, mRecordInfo;
    private TextView mEntName, mRegCode, mDeviceType, mTaskType, mStartDate, mEndDate;
    private SupervisionListResult.SupervisionList result;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_routine);
        mTitle.setText("监察档案");
        mEntName = (TextView) findViewById(R.id.ent_name);
        mRegCode = (TextView) findViewById(R.id.reg_code);
        mDeviceType = (TextView) findViewById(R.id.device_type);
        mTaskType = (TextView) findViewById(R.id.task_type);
        mStartDate = (TextView) findViewById(R.id.start_date);
        mEndDate = (TextView) findViewById(R.id.end_date);
        mBaseInfo = findViewById(R.id.base_info);
        ((TextView) mBaseInfo.findViewById(R.id.text)).setText("基本信息");
        mBaseInfo.setOnClickListener(this);
        mCheckOverview = findViewById(R.id.check_overview);
        ((TextView) mCheckOverview.findViewById(R.id.text)).setText("检查概况");
        mCheckOverview.setOnClickListener(this);
        mCheckSituation = findViewById(R.id.check_situation);
        ((TextView) mCheckSituation.findViewById(R.id.text)).setText("检查情况");
        mCheckSituation.setOnClickListener(this);
        mCheckContent = findViewById(R.id.check_content);
        ((TextView) mCheckContent.findViewById(R.id.text)).setText("检查内容");
        mCheckContent.setOnClickListener(this);
        mCodeBook = findViewById(R.id.code_book);
        ((TextView) mCodeBook.findViewById(R.id.text)).setText("安全监察指令书");
        mCodeBook.setOnClickListener(this);
        mRecordInfo = findViewById(R.id.record_info);
        ((TextView) mRecordInfo.findViewById(R.id.text)).setText("检验记录");
        mRecordInfo.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        result = (SupervisionListResult.SupervisionList) getIntent().getSerializableExtra("DATA");
        if (null != result) {
            mEntName.setText(result.getUnitName());
            mRegCode.setText("设备注册代码：" + result.getCreditCode());
            mDeviceType.setText("设备类型：" + result.getDeviceType());
            mTaskType.setText("任务类型：" + result.getTaskType());
            mStartDate.setText("检查开始日期：" + result.getStartDate());
            mEndDate.setText("检查结束日期：" + result.getEndDate());
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.base_info:
                bundle.putString(Params.UUID, result.getUuid());
                bundle.putString(Params.TYPE, ArchiveConstant.Routine.BASE_INFO);
                intent.putExtras(bundle);
                intent.setClass(RoutineDetailActivity.this, ArchiveDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.check_overview:
                bundle.putString(Params.UUID, result.getUuid());
                bundle.putString(Params.TYPE, ArchiveConstant.Routine.OVERVIEW);
                intent.putExtras(bundle);
                intent.setClass(RoutineDetailActivity.this, ArchiveDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.check_situation:
                bundle.putString(Params.UUID, result.getUuid());
                bundle.putString(Params.TYPE, ArchiveConstant.Routine.SITUATION);
                intent.putExtras(bundle);
                intent.setClass(RoutineDetailActivity.this, ArchiveDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.check_content:
                bundle.putString(Params.UUID, result.getUuid());
                bundle.putString(Params.TYPE, ArchiveConstant.Routine.CONTENT);
                intent.putExtras(bundle);
                intent.setClass(RoutineDetailActivity.this, ArchiveDetailListActivity.class);
                startActivity(intent);
                break;
            case R.id.code_book:
                bundle.putString(Params.UUID, result.getUuid());
                intent.putExtras(bundle);
                intent.setClass(RoutineDetailActivity.this, BookActivity.class);
                startActivity(intent);
                break;
            case R.id.record_info:
                HashMap<String, String> params = new HashMap<>();
                params.put("visionId", result.getUuid());
                bundle.putSerializable(Params.DATA, params);
                intent.putExtras(bundle);
                intent.setClass(RoutineDetailActivity.this, CheckListActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}