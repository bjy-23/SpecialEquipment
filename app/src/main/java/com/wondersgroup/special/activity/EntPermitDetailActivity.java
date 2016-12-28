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
import com.wondersgroup.special.entity.UnitPermissionEnitity;

/**
 * 单位许可详情
 */
public class EntPermitDetailActivity extends BaseActivity {
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private String uuid, mUnitType;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_ent_permit_detail);
        mTitle.setText("单位许可详情");
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        UnitPermissionEnitity data = (UnitPermissionEnitity) getIntent().getSerializableExtra(Params.DATA);
        if (null != data) {
            uuid = data.getUuid();
            mUnitType = data.getCertUnitType();
            tv1.setText("单位名称：" + data.getUnitName());
            tv2.setText("许可证编号：" + data.getCertCode());
            tv3.setText("许可类型：" + data.getCertType());
            tv4.setText("发证日期：" + data.getCertGrantDate());
            tv5.setText("证书有效期：" + data.getCertAvailableDate());
            findViewById(R.id.relative_basic_info).setOnClickListener(this);
            findViewById(R.id.relative_permit_info).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.relative_basic_info:
                bundle.putString(Params.UUID, uuid);
                bundle.putString(Params.UNIT_TYPE, mUnitType);
                bundle.putString(Params.TYPE, ArchiveConstant.UNIT_PERMIT);
                intent.putExtras(bundle);
                intent.setClass(EntPermitDetailActivity.this, ArchiveDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_permit_info:
                bundle.putString(Params.UUID, uuid);
                bundle.putString(Params.UNIT_TYPE, mUnitType);
                bundle.putString(Params.TYPE, ArchiveConstant.UNIT_PERMIT);
                intent.putExtras(bundle);
                intent.setClass(EntPermitDetailActivity.this, ArchiveDetailListActivity.class);
                startActivity(intent);
                break;
        }
    }
}
