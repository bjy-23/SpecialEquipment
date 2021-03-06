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
import com.wondersgroup.special.entity.UseListResult;

import java.util.HashMap;

public class ProductUnitDetailActivity extends BaseActivity {
    private UseListResult.UseList result;
    private TextView mEntName, mWarningType, mEquipmentRegistrationNumber, mCreditCode, mEntType, mEntAddress;
    private TextView mBoiler, mVessel, mCylindex, mPipe, mElevator, mLifting, mVehicle, mFacility;
    private RelativeLayout mRelativeBasicInfo, mRelativePermitInfo, mRelativeEquInfo, mRelativeEmployInfo,
            mRelativeRoutine, mRelativeVote, mRelativeCase, mRelativeAccident, mRelativeAward, mRelativeChange;
    private TextView mAddress;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_use_unit_detail);
        mTitle.setText("生产单位查询详情");
        mEntName = (TextView) findViewById(R.id.ent_name);
        mWarningType = (TextView) findViewById(R.id.warning_type);
        mWarningType.setVisibility(View.GONE);
        mCreditCode = (TextView) findViewById(R.id.credit_code);
        mEquipmentRegistrationNumber = (TextView) findViewById(R.id.equipment_registration_number);
        mEntType = (TextView) findViewById(R.id.ent_type);
        mEntAddress = (TextView) findViewById(R.id.ent_address);
        mBoiler = (TextView) findViewById(R.id.boiler);
        mVessel = (TextView) findViewById(R.id.vessel);
        mCylindex = (TextView) findViewById(R.id.cylindex);
        mPipe = (TextView) findViewById(R.id.pipe);
        mElevator = (TextView) findViewById(R.id.elevator);
        mLifting = (TextView) findViewById(R.id.lifting);
        mVehicle = (TextView) findViewById(R.id.vehicle);
        mFacility = (TextView) findViewById(R.id.facility);
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
        mAddress = (TextView) findViewById(R.id.address);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        result = (UseListResult.UseList) getIntent().getSerializableExtra("DATA");
        if (null != result) {
            mEntName.setText(getString(result.getUnitName()));
            if (!TextUtils.isEmpty(result.getMonitorFlag()) && "true".equals(result.getMonitorFlag())) {
                mWarningType.setVisibility(View.VISIBLE);
            }
            mEntType.setText("(" + getString(result.getUnitType()) + ")");
            mBoiler.setText("锅炉：" + getString(result.getType1()));
            mVessel.setText("压力容器：" + getString(result.getType2()));
            mCylindex.setText("客运索道：" + getString(result.getType6()));
            mPipe.setText("压力管道：" + getString(result.getType3()));
            mElevator.setText("电梯：" + getString(result.getType4()));
            mLifting.setText("起重器械：" + getString(result.getType5()));
            mVehicle.setText("场内车辆：" + getString(result.getType8()));
            mFacility.setText("游乐设施：" + getString(result.getType7()));
            final String address = result.getUnitAddress();
            mAddress.setText(String.format(getString(R.string.unit_address), address));
            if (!TextUtils.isEmpty(address)) {
                mAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ProductUnitDetailActivity.this, BDMapActivity.class);
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
                bundle.putString(Params.TYPE, ArchiveConstant.Unit.PRO_UNIT);
                bundle.putString(Params.UNIT_TYPE, result.getUnitType());
                intent.putExtras(bundle);
                intent.setClass(ProductUnitDetailActivity.this, UnitDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_permit_info:
                intent.putExtra("type", 1);
                intent.putExtra("params", params);
                intent.setClass(ProductUnitDetailActivity.this, RecordActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_equ_info:
                bundle.putSerializable("PARAMS", params);
                intent.putExtras(bundle);
                intent.setClass(ProductUnitDetailActivity.this, EquipmentListActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_employ_info:
                bundle.putSerializable(Params.DATA, params);
                intent.putExtras(bundle);
                intent.setClass(ProductUnitDetailActivity.this, EmploymentListActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_routine:
                bundle.putSerializable(Params.DATA, params);
                intent.putExtras(bundle);
                intent.setClass(ProductUnitDetailActivity.this, RoutineListActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_vote:
                bundle.putSerializable(Params.DATA, params);
                intent.putExtras(bundle);
                intent.setClass(ProductUnitDetailActivity.this, VoteListActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_case:
                bundle.putSerializable(Params.DATA, params);
                intent.putExtras(bundle);
                intent.setClass(ProductUnitDetailActivity.this, CaseListActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_accident:
                bundle.putSerializable(Params.DATA, params);
                intent.putExtras(bundle);
                intent.setClass(ProductUnitDetailActivity.this, AccidentListActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_award:
                bundle.putSerializable(Params.DATA, params);
                intent.putExtras(bundle);
                intent.setClass(ProductUnitDetailActivity.this, UnitAwardActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_change:
                break;
            default:
                break;
        }
    }
}
