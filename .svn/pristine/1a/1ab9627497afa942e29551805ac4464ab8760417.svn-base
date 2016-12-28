package com.wondersgroup.special.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.constant.Constant;

/**
 * 设备详情基本信息
 */
public class EquipmentBaseInfoActivity extends BaseActivity {
    private View mRegInfo, mIdentInfo, mDesignInfo, mProductInfo, mPropertyInfo, mUseInfo, mManageInfo,
            mSafeInfo, mBpviInfo, mMaintenanceInfo, mTecInfo, mTecExpaInfo;
    private TextView mRegText, mIdentText, mDesignText, mProductText, mPropertyText, mUseText, mManageText,
            mSafeText, mBpviText, mMaintenanceText, mTecText, mTecExpaText;
    private String type;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_equipment_base_info);
        mRegInfo = findViewById(R.id.reg_info);
        mRegText = (TextView) mRegInfo.findViewById(R.id.text);
        mIdentInfo = findViewById(R.id.ident_info);
        mIdentText = (TextView) mIdentInfo.findViewById(R.id.text);
        mDesignInfo = findViewById(R.id.design_info);
        mDesignText = (TextView) mDesignInfo.findViewById(R.id.text);
        mProductInfo = findViewById(R.id.product_info);
        mProductText = (TextView) mProductInfo.findViewById(R.id.text);
        mPropertyInfo = findViewById(R.id.property_info);
        mPropertyText = (TextView) mPropertyInfo.findViewById(R.id.text);
        mUseInfo = findViewById(R.id.use_info);
        mUseText = (TextView) mUseInfo.findViewById(R.id.text);
        mManageInfo = findViewById(R.id.manage_info);
        mManageText = (TextView) mManageInfo.findViewById(R.id.text);
        mSafeInfo = findViewById(R.id.safe_info);
        mSafeText = (TextView) mSafeInfo.findViewById(R.id.text);

        mBpviInfo = findViewById(R.id.bpvi_info);
        mBpviInfo.setVisibility(View.GONE);
        mBpviText = (TextView) mBpviInfo.findViewById(R.id.text);
        mMaintenanceInfo = findViewById(R.id.maintenance_info);
        mMaintenanceText = (TextView) mMaintenanceInfo.findViewById(R.id.text);
        mTecInfo = findViewById(R.id.tec_info);
        mTecText = (TextView) mTecInfo.findViewById(R.id.text);
        mTecExpaInfo = findViewById(R.id.tec_expa_info);
        mTecExpaText = (TextView) mTecExpaInfo.findViewById(R.id.text);
        mTecExpaInfo.setVisibility(View.GONE);
        mRegInfo.setOnClickListener(this);
        mIdentInfo.setOnClickListener(this);
        mDesignInfo.setOnClickListener(this);
        mProductInfo.setOnClickListener(this);
        mPropertyInfo.setOnClickListener(this);
        mUseInfo.setOnClickListener(this);
        mManageInfo.setOnClickListener(this);
        mSafeInfo.setOnClickListener(this);
        mBpviInfo.setOnClickListener(this);
        mMaintenanceInfo.setOnClickListener(this);
        mTecInfo.setOnClickListener(this);
        mTecExpaInfo.setOnClickListener(this);
        mRegText.setText("注册登记信息");
        mIdentText.setText("设备标识");
        mDesignText.setText("设计信息");
        mProductText.setText("制造信息");
        mPropertyText.setText("产权信息");
        mUseText.setText("使用信息");
        mManageText.setText("设备管理单位");
        mSafeText.setText("安全管理");
        mBpviText.setText("监检单位信息");
        mMaintenanceText.setText("维修保养信息");
        mTecText.setText("技术参数");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        switch (type) {
            case Constant.Overview.BOILER:
                mDesignInfo.setVisibility(View.GONE);
                mMaintenanceInfo.setVisibility(View.GONE);
                mTecText.setText("技术参数-基本参数");
                mTecExpaText.setText("技术参数-电站锅炉扩展参数");
                mTecExpaInfo.setVisibility(View.VISIBLE);
                break;
            case Constant.Overview.VESSEL:
                mMaintenanceInfo.setVisibility(View.GONE);
                mTecText.setText("技术参数-基本参数");
                mTecExpaText.setText("技术参数-扩展参数");
                mTecExpaInfo.setVisibility(View.VISIBLE);
                break;
            case Constant.Overview.ELEVATOR:
                mDesignInfo.setVisibility(View.GONE);
                mBpviInfo.setVisibility(View.VISIBLE);
                break;
            case Constant.Overview.LIFTING:
                mDesignInfo.setVisibility(View.GONE);
                mBpviInfo.setVisibility(View.VISIBLE);
                break;
            case Constant.Overview.VEHICLE:
                mBpviInfo.setVisibility(View.VISIBLE);
                break;
            case Constant.Overview.FACILITY:
                mDesignInfo.setVisibility(View.GONE);
                mMaintenanceInfo.setVisibility(View.GONE);
                break;
            case Constant.Overview.PIPE:
                mBpviInfo.setVisibility(View.VISIBLE);
                mMaintenanceInfo.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.reg_info:
                break;
            case R.id.ident_info:
                break;
            case R.id.design_info:
                break;
            case R.id.product_info:
                break;
            case R.id.property_info:
                break;
            case R.id.use_info:
                break;
            case R.id.manage_info:
                break;
            case R.id.safe_info:
                break;
            case R.id.bpvi_info:
                break;
            case R.id.maintenance_info:
                break;
            case R.id.tec_info:
                break;
            case R.id.tec_expa_info:
                break;
            default:
                break;
        }
    }
}
