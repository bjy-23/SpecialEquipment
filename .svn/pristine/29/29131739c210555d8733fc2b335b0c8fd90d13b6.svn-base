package com.wondersgroup.special.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.archive.ArchiveDetailActivity;
import com.wondersgroup.special.constant.ArchiveConstant;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.entity.EquipmentListResult;

import java.util.HashMap;

public class EquipmentDetailActivity extends BaseActivity {
    private View deviceMark, deviceProduct, deviceDesign, deviceProperty, mManageInfo, mCheckInfo, mAccidentInfo, designLine;
    private EquipmentListResult.ResultList result;
    private TextView mEquipmentName, mType, mCode, mRegNo, mAddress, mPingType, mChild, mWarningType, mDateType;
    private String type;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_equipment_detail);
        mTitle.setText("特种设备详情");
        mEquipmentName = (TextView) findViewById(R.id.equipment_name);
        mType = (TextView) findViewById(R.id.type);
        mCode = (TextView) findViewById(R.id.code);
        mRegNo = (TextView) findViewById(R.id.reg_no);
        mAddress = (TextView) findViewById(R.id.address);
        mPingType = (TextView) findViewById(R.id.ping_type);
        mChild = (TextView) findViewById(R.id.child);
        mWarningType = (TextView) findViewById(R.id.warning_type);
        mDateType = (TextView) findViewById(R.id.date_type);
        deviceMark = findViewById(R.id.device_mark);
        ((TextView) deviceMark.findViewById(R.id.text)).setText("标识信息");
        deviceProduct = findViewById(R.id.device_product);
        ((TextView) deviceProduct.findViewById(R.id.text)).setText("制造信息");
        deviceDesign = findViewById(R.id.device_design);
        ((TextView) deviceDesign.findViewById(R.id.text)).setText("设计信息");
        designLine = findViewById(R.id.design_line);
        deviceProperty = findViewById(R.id.device_property);
        ((TextView) deviceProperty.findViewById(R.id.text)).setText("产权信息");
        mManageInfo = findViewById(R.id.manage_info);
        ((TextView) mManageInfo.findViewById(R.id.text)).setText("监管信息");
        mCheckInfo = findViewById(R.id.check_info);
        ((TextView) mCheckInfo.findViewById(R.id.text)).setText("检验信息");
        mAccidentInfo = findViewById(R.id.accident_info);
        ((TextView) mAccidentInfo.findViewById(R.id.text)).setText("事故信息");

        deviceMark.setOnClickListener(this);
        deviceProduct.setOnClickListener(this);
        deviceDesign.setOnClickListener(this);
        deviceProperty.setOnClickListener(this);
        mManageInfo.setOnClickListener(this);
        mCheckInfo.setOnClickListener(this);
        mAccidentInfo.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        result = (EquipmentListResult.ResultList) getIntent().getExtras().getSerializable(Params.DATA);
        if (null != result) {
            mEquipmentName.setText(result.getDeviceName());
            mType.setText("设备类型：" + (result.getDeviceType1() == null ? "" : result.getDeviceType1()));
            mPingType.setText("设备品类：" + (result.getDeviceType2() == null ? "" : result.getDeviceType2()));
            mChild.setText("设备子类：" + (result.getDeviceType3() == null ? "" : result.getDeviceType3()));
            mCode.setText("设备代码：" + (result.getDeviceNumber() == null ? "" : result.getDeviceNumber()));
            mRegNo.setText("设备登记号：" + (result.getUseCerNum() == null ? "" : result.getUseCerNum()));
            mAddress.setText("设备地址：" + (result.getDeviceAddress() == null ? "" : result.getDeviceAddress()));
            if ("1".equals(result.getIfDanger()))
                mWarningType.setVisibility(View.VISIBLE);
            if ("1".equals(result.getIfCheck()))
                mDateType.setVisibility(View.VISIBLE);

            if (!"2000".equals(result.getDeviceType1()) && !"5000".equals(result.getDeviceType1())
                    && !"8000".equals(result.getDeviceType1())) {
                deviceDesign.setVisibility(View.GONE);
                designLine.setVisibility(View.GONE);
            }
            final String address = result.getDeviceAddress();
            if (!TextUtils.isEmpty(address)) {
                mAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(EquipmentDetailActivity.this, BDMapActivity.class);
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
        params.put("deviceId", result.getUuid());
        bundle.putSerializable(Params.DATA, params);
        intent.putExtras(bundle);
        switch (v.getId()) {
            case R.id.device_mark:
                type = ArchiveConstant.DeviceBaseInfo.DEVICE_MARK;
                startActivity(new Intent(EquipmentDetailActivity.this, ArchiveDetailActivity.class)
                        .putExtra(Params.TYPE, type)
                        .putExtra(Params.UUID, result.getUuid())
                        .putExtra(Params.UNIT_TYPE, result.getDeviceType1()));
                break;
            case R.id.device_product:
                type = ArchiveConstant.DeviceBaseInfo.DEVICE_PRODUCT;
                startActivity(new Intent(EquipmentDetailActivity.this, ArchiveDetailActivity.class)
                        .putExtra(Params.TYPE, type)
                        .putExtra(Params.UUID, result.getUuid())
                        .putExtra(Params.UNIT_TYPE, result.getDeviceType1()));
                break;
            case R.id.device_design:
                type = ArchiveConstant.DeviceBaseInfo.DEVICE_DESIGN;
                startActivity(new Intent(EquipmentDetailActivity.this, ArchiveDetailActivity.class)
                        .putExtra(Params.TYPE, type)
                        .putExtra(Params.UUID, result.getUuid())
                        .putExtra(Params.UNIT_TYPE, result.getDeviceType1()));
                break;
            case R.id.device_property:
                type = ArchiveConstant.DeviceBaseInfo.DEVICE_PROPERTY;
                startActivity(new Intent(EquipmentDetailActivity.this, ArchiveDetailActivity.class)
                        .putExtra(Params.TYPE, type)
                        .putExtra(Params.UUID, result.getUuid())
                        .putExtra(Params.UNIT_TYPE, result.getDeviceType1()));
                break;
            case R.id.manage_info:
                intent.setClass(EquipmentDetailActivity.this, RoutineListActivity.class);
                startActivity(intent);
                break;
            case R.id.check_info:
                intent.setClass(EquipmentDetailActivity.this, CheckListActivity.class);
                startActivity(intent);
                break;
            case R.id.accident_info:
                intent.setClass(EquipmentDetailActivity.this, AccidentListActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
