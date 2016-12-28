package com.wondersgroup.special.activity.unit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.activity.AccidentListActivity;
import com.wondersgroup.special.activity.BDMapActivity;
import com.wondersgroup.special.activity.BaseActivity;
import com.wondersgroup.special.activity.CaseListActivity;
import com.wondersgroup.special.activity.EmploymentListActivity;
import com.wondersgroup.special.activity.EquipmentListActivity;
import com.wondersgroup.special.activity.RecordActivity;
import com.wondersgroup.special.activity.RoutineListActivity;
import com.wondersgroup.special.activity.UnitAwardActivity;
import com.wondersgroup.special.activity.UnitDetailActivity;
import com.wondersgroup.special.activity.VoteListActivity;
import com.wondersgroup.special.constant.ArchiveConstant;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.entity.CheckUnitListResult;

import java.util.HashMap;

public class CheckUnitDetailActivity extends BaseActivity {
    private TextView mEntName, mCreditCode, mOrganCode, mPersonScale, mOrganProperty, mAddress;
    private CheckUnitListResult.CheckList result;
    private RelativeLayout mRelativeBasicInfo, mRelativePermitInfo, mRelativeEquInfo, mRelativeEmployInfo,
            mRelativeRoutine, mRelativeVote, mRelativeCase, mRelativeAccident, mRelativeAward, mRelativeChange;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_check_unit_detail);
        mTitle.setText("检验检测单位详情");
        mCreditCode = (TextView) findViewById(R.id.credit_code);
        mEntName = (TextView) findViewById(R.id.ent_name);
        mOrganCode = (TextView) findViewById(R.id.organ_code);
        mPersonScale = (TextView) findViewById(R.id.person_scale);
        mOrganProperty = (TextView) findViewById(R.id.organ_property);
        mAddress = (TextView) findViewById(R.id.address);
        mRelativeBasicInfo = (RelativeLayout) findViewById(R.id.relative_basic_info);
        mRelativePermitInfo = (RelativeLayout) findViewById(R.id.relative_permit_info);
        mRelativeEquInfo = (RelativeLayout) findViewById(R.id.relative_equ_info);
        mRelativeEmployInfo = (RelativeLayout) findViewById(R.id.relative_employ_info);
        mRelativeRoutine = (RelativeLayout) findViewById(R.id.relative_routine);
        mRelativeVote = (RelativeLayout) findViewById(R.id.relative_vote);
        mRelativeCase = (RelativeLayout) findViewById(R.id.relative_case);
        mRelativeAccident = (RelativeLayout) findViewById(R.id.relative_accident);
        mRelativeAward = (RelativeLayout) findViewById(R.id.relative_award);
        mRelativeChange = (RelativeLayout) findViewById(R.id.relative_change);
        mRelativeBasicInfo.setOnClickListener(this);
        mRelativePermitInfo.setOnClickListener(this);
        mRelativeEquInfo.setOnClickListener(this);
        mRelativeEmployInfo.setOnClickListener(this);
        mRelativeRoutine.setOnClickListener(this);
        mRelativeVote.setOnClickListener(this);
        mRelativeCase.setOnClickListener(this);
        mRelativeAccident.setOnClickListener(this);
        mRelativeAward.setOnClickListener(this);
        mRelativeChange.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        result = (CheckUnitListResult.CheckList) getIntent().getSerializableExtra("DATA");
        if (null != result) {
            mEntName.setText(result.getUnitName());
            mCreditCode.setText("企业信用代码：" + result.getSocialCreditCode());
            mOrganCode.setText("行政区划：" + result.getUnitAdminAreaCode());
            mPersonScale.setText("单位级别：" + result.getOrganLevel());
            mOrganProperty.setText("单位性质：" + result.getOrganProperty());
            final String address = result.getUnitAddress();
            mAddress.setText("单位地址：" + address);
            if (!TextUtils.isEmpty(address)) {
                mAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CheckUnitDetailActivity.this, BDMapActivity.class);
                        intent.putExtra("ADDRESS", address);
                        startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        HashMap<String, String> params = new HashMap<>();
        params.put("unitId", result.getUuid());
        switch (v.getId()) {
            case R.id.relative_basic_info:
                bundle.putString(Params.UUID, result.getUuid());
                bundle.putString(Params.TYPE, ArchiveConstant.Unit.CHECK_UNIT);
                bundle.putString(Params.UNIT_TYPE, result.getUnitType());
                intent.putExtras(bundle);
                intent.setClass(CheckUnitDetailActivity.this, UnitDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_permit_info:
                intent.putExtra("type", 1);
                intent.putExtra("params", params);
                intent.setClass(CheckUnitDetailActivity.this, RecordActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_equ_info:
                bundle.putSerializable("PARAMS", params);
                intent.putExtras(bundle);
                intent.setClass(CheckUnitDetailActivity.this, EquipmentListActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_employ_info:
                bundle.putSerializable(Params.DATA, params);
                intent.putExtras(bundle);
                intent.setClass(CheckUnitDetailActivity.this, EmploymentListActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_routine:
                bundle.putSerializable(Params.DATA, params);
                intent.putExtras(bundle);
                intent.setClass(CheckUnitDetailActivity.this, RoutineListActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_vote:
                bundle.putSerializable(Params.DATA, params);
                intent.putExtras(bundle);
                intent.setClass(CheckUnitDetailActivity.this, VoteListActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_case:
                bundle.putSerializable(Params.DATA, params);
                intent.putExtras(bundle);
                intent.setClass(CheckUnitDetailActivity.this, CaseListActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_accident:
                bundle.putSerializable(Params.DATA, params);
                intent.putExtras(bundle);
                intent.setClass(CheckUnitDetailActivity.this, AccidentListActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_award:
                bundle.putSerializable(Params.DATA, params);
                intent.putExtras(bundle);
                intent.setClass(CheckUnitDetailActivity.this, UnitAwardActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_change:
                break;
            default:
                break;
        }
    }
}
