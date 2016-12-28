package com.wondersgroup.special.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.activity.BaseActivity;
import com.wondersgroup.special.archive.ArchiveDetailActivity;
import com.wondersgroup.special.archive.ArchiveDetailListActivity;
import com.wondersgroup.special.constant.ArchiveConstant;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.entity.CheckoutListResult;
import com.wondersgroup.special.entity.SupervisionListResult;

/**
 * 检验档案
 */
public class CheckDetailActivity extends BaseActivity {
    private View mBaseInfo, mRecordInfo;
    private TextView mEntName, mRegCode, mDeviceType, mCheckDate, mCheckUnit, mCheckResult;
    private CheckoutListResult.CheckoutList result;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_check);
        mTitle.setText("检验档案");
        mEntName = (TextView) findViewById(R.id.ent_name);
        mRegCode = (TextView) findViewById(R.id.reg_code);
        mDeviceType = (TextView) findViewById(R.id.device_type);
        mCheckDate = (TextView) findViewById(R.id.check_date);
        mCheckUnit = (TextView) findViewById(R.id.check_unit);
        mCheckResult = (TextView) findViewById(R.id.check_result);
        mBaseInfo = findViewById(R.id.base_info);
        ((TextView) mBaseInfo.findViewById(R.id.text)).setText("基本信息");
        mBaseInfo.setOnClickListener(this);
        mRecordInfo = findViewById(R.id.record_info);
        ((TextView) mRecordInfo.findViewById(R.id.text)).setText("检验记录");
        mRecordInfo.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        result = (CheckoutListResult.CheckoutList) getIntent().getSerializableExtra("DATA");
        if (null != result) {
            mEntName.setText(result.getUnitName());
            mRegCode.setText("设备注册代码：" + result.getCreditCode());
            mDeviceType.setText("设备类型：" + result.getDeviceType());
            mCheckDate.setText("检验日期：" + result.getCheckDaye());
            mCheckUnit.setText("检验单位：" + result.getCheckUnit());
            mCheckResult.setText("检验结论：" + result.getCheckResult());
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
                bundle.putString(Params.TYPE, ArchiveConstant.Checkout.BASE_INFO);
                intent.putExtras(bundle);
                intent.setClass(CheckDetailActivity.this, ArchiveDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.record_info:
                bundle.putString(Params.VISION_ID, result.getVisionId());
                bundle.putString(Params.TYPE, ArchiveConstant.Checkout.RECORD);
                intent.putExtras(bundle);
                intent.setClass(CheckDetailActivity.this, ArchiveDetailListActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
