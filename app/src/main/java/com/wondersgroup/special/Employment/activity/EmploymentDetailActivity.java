package com.wondersgroup.special.employment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.activity.BaseActivity;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.entity.EmployListResult;

public class EmploymentDetailActivity extends BaseActivity {
    private TextView mEmploymentName, mIdCode, mTheNo, mEmploymentUnit;
    private EmployListResult.EmployList result;
    private RelativeLayout relativeLayoutBasic, relativeLayoutWork, relativeLayoutPermission, relativeLayoutReward, relativeLayoutTrain, relativeLayoutCheck, relativeLayoutChange;
    private String employType;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_employment_detail);
        mTitle.setText("人员详情");
        mEmploymentName = (TextView) findViewById(R.id.employment_name);
        mIdCode = (TextView) findViewById(R.id.id_code);
        mTheNo = (TextView) findViewById(R.id.the_no);
        mEmploymentUnit = (TextView) findViewById(R.id.employment_unit);
        relativeLayoutBasic = (RelativeLayout) findViewById(R.id.relative_basic);
        relativeLayoutBasic.setOnClickListener(this);
        relativeLayoutWork = (RelativeLayout) findViewById(R.id.relative_work);
        relativeLayoutWork.setOnClickListener(this);
        relativeLayoutPermission = (RelativeLayout) findViewById(R.id.relative_permission);
        relativeLayoutPermission.setOnClickListener(this);
        relativeLayoutReward = (RelativeLayout) findViewById(R.id.relative_reward);
        relativeLayoutReward.setOnClickListener(this);
        relativeLayoutTrain = (RelativeLayout) findViewById(R.id.relative_train);
        relativeLayoutTrain.setOnClickListener(this);
        relativeLayoutCheck = (RelativeLayout) findViewById(R.id.relative_check);
        relativeLayoutCheck.setOnClickListener(this);
        relativeLayoutChange = (RelativeLayout) findViewById(R.id.relative_change);
        relativeLayoutChange.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        result = (EmployListResult.EmployList) getIntent().getSerializableExtra("DATA");
        employType = getIntent().getStringExtra(Params.EMPLOY_TYPE);
        if (null != result) {
            mEmploymentName.setText(result.getName());
            mIdCode.setText(String.format(getString(R.string.item_id_code),
                    TextUtils.isEmpty(result.getCardId()) ? "" : result.getCardId()));
            mTheNo.setText("许可类型：" + result.getCertType());
            mEmploymentUnit.setText(String.format(getString(R.string.employment_unit), result.getUnitName()));
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = new Intent();
        intent.putExtra("uuid", result.getUuid());
        switch (v.getId()) {
            case R.id.relative_basic:
                intent.putExtra("type", "0");
                intent.setClass(EmploymentDetailActivity.this, DetailActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_work:
                intent.setClass(EmploymentDetailActivity.this,EmploymentExpandActivity.class);
                intent.putExtra("type","1");                startActivity(intent);
                break;
            case R.id.relative_permission:
                intent.setClass(EmploymentDetailActivity.this,EmploymentExpandActivity.class);
                intent.putExtra("type","2");                startActivity(intent);
                break;
            case R.id.relative_reward:
                intent.setClass(EmploymentDetailActivity.this,EmploymentExpandActivity.class);
                intent.putExtra("type","3");                startActivity(intent);
                break;
            case R.id.relative_train:
                intent.setClass(EmploymentDetailActivity.this,EmploymentExpandActivity.class);
                intent.putExtra("type","4");                startActivity(intent);
                break;
            case R.id.relative_check:
                intent.setClass(EmploymentDetailActivity.this,EmploymentExpandActivity.class);
                intent.putExtra("type","5");                startActivity(intent);
                break;
            case R.id.relative_change:
                intent.setClass(EmploymentDetailActivity.this,EmploymentExpandActivity.class);
                intent.putExtra("type","6");                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
