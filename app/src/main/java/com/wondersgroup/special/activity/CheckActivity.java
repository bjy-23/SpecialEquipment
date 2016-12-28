package com.wondersgroup.special.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.widget.ClearEditText;

import java.util.HashMap;

public class CheckActivity extends BaseActivity {
    private Button mBtnSearch;
    private ClearEditText mUnitCode, mUnitName, mRegCode;
    private TextView mType, mConclusion;
    private HashMap<String, String> params;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_check2);
        mTitle.setText("检验档案");
        mBtnSearch = (Button) findViewById(R.id.btn_search);
        mBtnSearch.setOnClickListener(this);
        mUnitCode = (ClearEditText) findViewById(R.id.unit_code);
        mUnitName = (ClearEditText) findViewById(R.id.unit_name);
        mRegCode = (ClearEditText) findViewById(R.id.reg_code);
        mType = (TextView) findViewById(R.id.type);
        mConclusion = (TextView) findViewById(R.id.conclusion);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        params = new HashMap<>();
    }

    private void getParams() {
        params.put("unitName", mUnitName.getText().toString());
        params.put("unitCode", mUnitCode.getText().toString());
        params.put("deviceNumber", mRegCode.getText().toString());
        params.put("deviceType", mType.getText().toString());
        params.put("checkResult", mConclusion.getText().toString());
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_search:
                getParams();
                Intent intent = new Intent(CheckActivity.this, CheckListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Params.DATA, params);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}
